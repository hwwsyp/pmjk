package com.tpfh.fintech.modules.share.responsibility;

import java.text.SimpleDateFormat;
import java.util.Date;

public class YMDCrossbarDatestringParser extends DateStringParser{
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public Date kernelHandle(String dateString) {
		dateFormat.setLenient(false);//严格校验
		
		try {
			return dateFormat.parse(dateString);
		}catch (Exception e) {
			return null;
		}
	}

}
