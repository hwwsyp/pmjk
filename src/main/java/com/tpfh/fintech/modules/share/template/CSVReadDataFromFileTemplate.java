package com.tpfh.fintech.modules.share.template;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson2.JSONObject;
import com.google.common.base.Charsets;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import cn.hutool.core.util.ArrayUtil;

/**
 * CSV文件的解析，并按照客户端的配置，获取指定的数据
 * @author Taiping
 *
 * @param <T>
 */
public class CSVReadDataFromFileTemplate<T> extends ReadDataFromFileTemplate<T>{

	@Override
	public List<T> parseFile(MultipartFile file, FileToClassMapping fileToClassMapping, Class<T> clazz) throws Exception {
		List<T> list = new ArrayList<>();
		//获取特定分隔符的CSV解析器
		char separator = this.getSeparatorChar(fileToClassMapping.getCsvSeparatorChar());
		
		CSVParser parser = new CSVParserBuilder().withSeparator(separator).build();
		CSVReaderBuilder builder = new CSVReaderBuilder(new InputStreamReader(file.getInputStream(), Charsets.UTF_8));
		CSVReader csvReader = builder.withCSVParser(parser).build();

		//当前行号
		int currentRowNum = 0;
		Integer startLineNum = null;
		Integer endLineNum = null;
		//当前解析的行内容
		String[] arr = null;
		//获取泛型的实际类型
		//开始读取数据行，根据配置的字段列，读取数据，注意。这里配置的列数字从1开始，但是数组索引从0开始。
		while((arr = csvReader.readNext()) != null) {
			currentRowNum ++;
			
			//测试发现如果当前行为空时，arr数组的长度为1，且内容为""; 因此对应的过滤掉
			if(arr.length==1 && "".equals(arr[0].trim())) continue;
			
			//如果获取的值为null，则当前行依然不是开始行；或者获得了开始行，但是大于当前当前行，则跳出当次循环，继续下一行的检查
			if(startLineNum==null) {
				//检查当前行是否是开始行
				startLineNum = this.getLocationRowNum(fileToClassMapping.getStartLineStrategyType(), fileToClassMapping.getStartLineStrategyValue()
						, currentRowNum, ArrayUtil.join(arr, String.valueOf('\t')));
			}
			if(startLineNum==null || startLineNum>currentRowNum) continue; //如果开始行》当前行，则舍弃当前数据的读取
			
			//获取结束行，如果结束行获取不到，或当前行是结束行，当前行已经大于等于结束行，那么结束数据的解析工作
			if(endLineNum==null) {
				endLineNum = this.getLocationRowNum(fileToClassMapping.getEndLineStrategyType(), fileToClassMapping.getEndLineStrategyValue(),
						currentRowNum, ArrayUtil.join(arr, String.valueOf('\t')));
			}
			if(endLineNum != null && currentRowNum >= endLineNum) break;
			
			//检查当前行是否要舍弃
			boolean isDiscard = this.checkIfCurrentRowDiscard(arr, fileToClassMapping.getList());
			if(isDiscard) continue;
			
			//解析当前行数据
			JSONObject json = new JSONObject();
			List<FileColumnToClassFiledMapping> ecttfmList = fileToClassMapping.getList();
			for(FileColumnToClassFiledMapping ecttfm : ecttfmList) {
				//获取文件中对应的数据值
				String filedValue = arr[ecttfm.getColumnNum()-1];
				//根据字段类型、获取的字符串，确认对应的类型解析
				Object obj = this.getClassPropertieValueBySelfType(ecttfm.getClassType(), filedValue);
				//以JSON串的方式
				json.put(ecttfm.getFieldName(), obj);
			}
			//利用Alibaba的JSON方法解析出对应的实体实例
			T t = json.to(clazz);
			list.add(t);
		}
		
		try {
			csvReader.close();
		}catch (IOException e) {
			//
		}finally {
			csvReader = null;
		}

		return list;
	}
	
	/**
	 * 根据配置获取分隔符
	 * @param separator
	 * @return
	 */
	private char getSeparatorChar(String separator) {
		if("EMPTY".equals(separator)) {
			return ' ';
		}else if("TAB".equals(separator)){
			return '\t';
		}else if("COMMA".equals(separator)) {
			return ',';
		}
		return ',';
	}

	public static void main(String[] args) {
		String[] arr = {"fs"};
		System.out.println(ArrayUtil.join(arr, String.valueOf('\t')));
	}
}
