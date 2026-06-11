package com.tpfh.fintech.modules.share.responsibility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 解析 dd MMM yyyy HH:mm z 样式的数据
 * @author Taiping
 *
 */
public class USDatestringParser extends DateStringParser{
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm z", Locale.US);
	@Override
	public Date kernelHandle(String dateString) {
		dateFormat.setLenient(false);
		Date date = null;
		if(dateString!=null && dateString.indexOf("GMT+8")>-1) {
			dateString = dateString.replace("GMT+8", "GMT+08:00");
		}
		try {
			date = dateFormat.parse(dateString);
		}catch (Exception e) {
			return null;
		}
		return date;
	}

}
