package com.tpfh.fintech.modules.share.template;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson2.JSONObject;

import cn.hutool.core.util.ArrayUtil;

/**
 * excel解析银行流水
 * @author Taiping
 *
 */
public class ExcelReadDataFromFileTemplate<T> extends ReadDataFromFileTemplate<T> {
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	@Override
	public List<T> parseFile(MultipartFile file, FileToClassMapping fileToClassMapping, Class<T> clazz) throws Exception {
		Workbook workbook = null;
		if("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet".equals(file.getContentType())){
			workbook = new XSSFWorkbook(file.getInputStream());
		}else if("application/vnd.ms-excel".equals(file.getContentType())){
			workbook=new HSSFWorkbook(file.getInputStream());
		}
		
		int sheetIndex = fileToClassMapping.getSheetNum()-1;
		Sheet sheet = workbook.getSheetAt(sheetIndex);

		
		List<T> list = new ArrayList<>();

		// 总行数
		// 行号从零开始，因此需要加1
		int rowNum = sheet.getLastRowNum() + 1;
		int currentRowNum = 0;
		
		//存储当前文件的开始航、结束行
		Integer startLineNum = null;
		Integer endLineNum = null;
		
		for (int i = 0; i < rowNum; i++) {
			currentRowNum ++;
			
			Row row = sheet.getRow(i);
			if (row == null) {
				//如果当前行为空白，则直接抛弃
				continue;
			}
			
			String[] arr = {};
			//获取某一行的内容组合成为一个数组
			for(int j=0;j<=row.getLastCellNum();j++) {
				String cellValue = getCellValue(row, j);
				arr = ArrayUtil.insert(arr, arr.length, cellValue);
			}
			
			//如果获取的值为null，则当前行依然不是开始行；或者获得了开始行，但是大于当前当前行，则跳出当次循环，继续下一行的检查
			if(startLineNum==null) {
				//检查当前行是否是开始行
				startLineNum = this.getLocationRowNum(fileToClassMapping.getStartLineStrategyType(), fileToClassMapping.getStartLineStrategyValue()
						, currentRowNum, ArrayUtil.join(arr, String.valueOf('\t')));
			}
			if(startLineNum==null || startLineNum>currentRowNum) {
				//如果开始行》当前行，则舍弃当前数据的读取
				continue;
			}
			
			//获取结束行，如果结束行获取不到，或当前行是结束行，当前行已经大于等于结束行，那么结束数据的解析工作
			if(endLineNum==null) {
				endLineNum = this.getLocationRowNum(fileToClassMapping.getEndLineStrategyType(), fileToClassMapping.getEndLineStrategyValue(),
						currentRowNum, ArrayUtil.join(arr, String.valueOf('\t')));
			}
			if(endLineNum != null && currentRowNum >= endLineNum) {
				break;
			}
			
			//检查当前行是否要舍弃
			boolean isDiscard = this.checkIfCurrentRowDiscard(arr, fileToClassMapping.getList());
			if(isDiscard) continue;

			//解析当前有效数据行
			JSONObject json = new JSONObject();
			List<FileColumnToClassFiledMapping> ecttfmList = fileToClassMapping.getList();
			for(FileColumnToClassFiledMapping ecttfm : ecttfmList) {
				//modify by owen in 20220103 修复bug，因为可能是最后一列的数据容许为空，因此检查时不被抛弃，但在整行数据读取时，因为是最后一列，因此该arr数组的长度就不够了。
				//仅当最后n列容许为空时，才会发生
				//String filedValue = arr[ecttfm.getColumnNum()-1];
				//修改后的代码如下
				Object obj = null;
			    if(ecttfm.getColumnNum() > arr.length) {
			    	obj = null;
			    }else {
			    	String filedValue = arr[ecttfm.getColumnNum()-1];
			    	obj = this.getClassPropertieValueBySelfType(ecttfm.getClassType(), filedValue);
			    }
				json.put(ecttfm.getFieldName(), obj);
			}
			
			T t = json.to(clazz);
			list.add(t);
		}

		return list;
	}
	
	/**
	 * 给定行、列，解析该cell值
	 * @param row
	 * @param columnIndex
	 * @return
	 * @throws Exception
	 */
	public static String getCellValue(Row row, int columnIndex) throws Exception {
		//如果指定的列索引，比该行的最大列索引大，那么认为越界，抛异常
		if(columnIndex > row.getLastCellNum()) throw new Exception("out of index");
		//开始解析cell数据
		Cell cell = row.getCell(columnIndex);
		String cellStr = "";
		if (cell != null && !"".equals(cell.toString().trim())) {

			if(cell.getCellTypeEnum() == CellType.STRING) {//如果cell类型为文本
				cellStr = cell.getStringCellValue();
			}else if (cell.getCellTypeEnum() == CellType.NUMERIC) {//如果cell类型为数字
				short format = cell.getCellStyle().getDataFormat();
				if(format == 14 || format == 31 || format == 57 || format == 58){
					Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(cell.getNumericCellValue()); 
					cellStr=dateFormat.format(date);
				}else if(format == 165) {
					Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
					cellStr = dateFormat.format(date);
				}else {
					//double numericValue = cell.getNumericCellValue();
					//cellStr = nf.format(numericValue);//用于保持数字的精度，因为如果由double转string，会丢失精度
					
					//modify by owen in 20230503 实际测试中发现以上代码，无法同时满足小数、大整数的展现，
					cellStr = NumberToTextConverter.toText(cell.getNumericCellValue());
				}
			}else if (cell.getCellTypeEnum() == CellType.FORMULA) {//如果cell类型为公式
				CellValue cellValue = row.getSheet().getWorkbook().getCreationHelper().createFormulaEvaluator().evaluate(cell);
				if(cellValue.getCellTypeEnum().equals(CellType.NUMERIC)) {
					cellStr = String.valueOf(cellValue.getNumberValue());
				}else if(cellValue.getCellTypeEnum().equals(CellType.STRING)){
					cellStr = cellValue.getStringValue();
				}
			}else {
				//
				throw new Exception("can't parse this cell");
			}

			cellStr=cellStr.trim();
		}
		return cellStr;
	}
	
	
}
