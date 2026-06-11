package com.tpfh.fintech.modules.share.responsibility;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 解析  yyyyMMdd 格式的日期数据
 * @author Taiping
 *
 */
public class YMDDatestringParser extends DateStringParser{
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

	@Override
	public Date kernelHandle(String dateString) {
		dateFormat.setLenient(false);
		try {
			return dateFormat.parse(dateString);
		}catch (Exception e) {
			return null;
		}
	}

}
