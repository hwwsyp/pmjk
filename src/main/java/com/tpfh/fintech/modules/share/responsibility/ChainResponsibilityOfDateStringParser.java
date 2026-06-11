package com.tpfh.fintech.modules.share.responsibility;

import java.util.Date;

/**
 * 日期格式处理责任链
 * @author Taiping
 *
 */
public class ChainResponsibilityOfDateStringParser {
	private static DateStringParser first; 
	
	//TODO 后续可以通过配置，反射来实现
	static {
		DateStringParser ymdCrossbarDatestringParser = new YMDCrossbarDatestringParser();
		DateStringParser ymdSlashDatestringParser = new YMDSlashDatestringParser();
		DateStringParser mdyCrossbarDatestringParser = new MDYCrossbarDatestringParser();
		DateStringParser usDatestringParser = new USDatestringParser();
		DateStringParser ymdDatestringParser = new YMDDatestringParser();
		DateStringParser usDatestringParser2 = new USDatestringParser2();
		DateStringParser usDatestringParser3 = new USDatestringParser3();
		DateStringParser mdySlashDatestringParser = new MDYSlashDatestringParser();
		
		ymdCrossbarDatestringParser.setNext(ymdSlashDatestringParser);
		ymdSlashDatestringParser.setNext(mdyCrossbarDatestringParser);
		mdyCrossbarDatestringParser.setNext(usDatestringParser);
		usDatestringParser.setNext(ymdDatestringParser);
		ymdDatestringParser.setNext(usDatestringParser3);
		usDatestringParser3.setNext(usDatestringParser2);
		usDatestringParser2.setNext(mdySlashDatestringParser);
		
		first = ymdCrossbarDatestringParser;
	}
	
	public static Date doHandle(String dateString) throws Exception {
		return first.doHandle(dateString);
	}
}
