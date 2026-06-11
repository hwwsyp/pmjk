package com.tpfh.fintech.modules.share.responsibility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 解析 dd-MMM-yy，例如04-AUG-22，标识2022-08-04 样式的数据
 * @author Taiping
 *
 */
public class USDatestringParser3 extends DateStringParser{
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy", Locale.US);
	@Override
	public Date kernelHandle(String dateString) {
		dateFormat.setLenient(false);
		Date date = null;
		try {
			date = dateFormat.parse(dateString);
		}catch (Exception e) {
			return null;
		}
		return date;
	}
}
