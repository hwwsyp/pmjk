package com.tpfh.fintech.modules.share.template;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.util.StringUtils;
import com.tpfh.fintech.modules.share.responsibility.ChainResponsibilityOfDateStringParser;

/**
 *  解析数据文件，返回数据列表
 * @author Taiping
 *
 * @param <T> 通用的数据对象，实现对不同数据的解析
 */
public abstract class ReadDataFromFileTemplate<T> {
	private DecimalFormat decimalFormat = new DecimalFormat(",###.##");
	/**
	 * 解析数据文件，返回指定的类实例列表
	 * @param file 数据文件
	 * @param mapping 配置文件
	 * @param clazz 指定的返回类型
	 * @return
	 * @throws Exception
	 */
	public abstract List<T> parseFile(MultipartFile file, FileToClassMapping mapping, Class<T> clazz) throws Exception;
	
	/**
	 * 检查当前行内容是否需要被舍弃，默认如果某个必填项缺失值，则舍弃
	 * @param arr
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public Boolean checkIfCurrentRowDiscard(String[] arr, List<FileColumnToClassFiledMapping> list) throws Exception{
		if(arr==null || list==null) throw new Exception("arr or mapping lost");
		for(FileColumnToClassFiledMapping mapping : list) {
			if(mapping.getIsRequired()) {
				//如果设定的取值列号，大于数组长度，说明缺少值了，必须舍弃
				if(mapping.getColumnNum() > arr.length) {
					return true;
				}
				String value = arr[mapping.getColumnNum()-1];
				if(value==null || "".equals(value.trim())) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 根据数据类型以及字符串值，返回其对应的数据
	 * @param classType
	 * @param value
	 * @return
	 * @throws Exception 
	 */
	public Object getClassPropertieValueBySelfType(String classType, String value) throws Exception {
		Object obj = null;
		
		switch(classType) {
			case "String": 
				obj = value;
				break;
				
			case "BigDecimal":
				if(StringUtils.isNumber(value)) {
					obj = new BigDecimal(value);
				}else {
					if(value.indexOf(",")>-1) {
						obj = new BigDecimal(decimalFormat.parse(value).toString());
					}
				}
				
				break;
			
			case "Date":
				//06 Oct 2021 14:30 GMT+8
				//2021/9/28
				//2021-10-6
				//10-06-2021
				//20211011
				Date date = ChainResponsibilityOfDateStringParser.doHandle(value);
				if(date!=null) {obj = new SimpleDateFormat("yyyy-MM-dd").format(date);}
				
				break;
			default:
				throw new Exception("parse value error");
		}
		
		if(obj == null) {
			throw new Exception("parse value error");
		}else {
			return obj;
		}
	}
	
	/**
	 * 根据行定位策略类型定位当前行是否匹配
	 * @param rowNumStrategyType
	 * @param rowNumStrategyValue
	 * @param currentLineNum
	 * @param currentLineValue
	 * @return
	 */
	public Integer getLocationRowNum(String rowNumStrategyType, String rowNumStrategyValue, Integer currentLineNum, String currentLineValue) throws Exception{
		LocationRowNumStrategyEnum rowNumStrategyEnum = LocationRowNumStrategyEnum.valueOf(rowNumStrategyType);
		if(LocationRowNumStrategyEnum.NUMBER.equals(rowNumStrategyEnum)) {
			if("-1".equals(rowNumStrategyValue)) {
				return Integer.MAX_VALUE;
			}else{
				return Integer.parseInt(rowNumStrategyValue);
			}
		}else if(LocationRowNumStrategyEnum.VALUE_SEARCH_NEXT.equals(rowNumStrategyEnum)) {
			if(currentLineValue != null && rowNumStrategyValue != null) {
				//目前的策略是包含即可，也许后续有必须全匹配的要求 TODO
				if(currentLineValue.indexOf(rowNumStrategyValue)>-1) {
					return currentLineNum + 1;//一般来说我们都是匹配某个标题，所以应该是它的下一行开始
				}
			}
			return null;
		}else if(LocationRowNumStrategyEnum.VALUE_SEARCH_CURRENT.equals(rowNumStrategyEnum)) {
			if(currentLineValue != null && rowNumStrategyValue != null) {
				//目前的策略是包含即可，也许后续有必须全匹配的要求 TODO
				if(currentLineValue.indexOf(rowNumStrategyValue)>-1) {
					return currentLineNum;
				}
			}
			return null;
		}

		return null;
	}
	
	/*public static void main(String[] args) {
		String dstr = "06 Oct 2021 14:30 GMT+08:00";
		dstr = "2021/12/28";
		//datdString = "Wed, 4 Jul 2001 12:08:56";
		//Date d = new Date(datdString);
		//System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(d));
		
		//SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy HH:mm z", Locale.US);
		SimpleDateFormat format = new SimpleDateFormat("yyyy/M/d");

		Date dateTrans = null;
		try {
			dateTrans = format.parse(dstr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(dateTrans));
	}*/
}