package com.tpfh.fintech.modules.share.responsibility;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 解析  MM-dd-yyyy 类型的日期字符创
 * @author Taiping
 *
 */
public class MDYCrossbarDatestringParser extends DateStringParser{
	private SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");

	@Override
	public Date kernelHandle(String dateString) {
		sdf.setLenient(false);//严格校验
		try {
			return sdf.parse(dateString);
		}catch (Exception e) {
			return null;
		}
	}
	
}
