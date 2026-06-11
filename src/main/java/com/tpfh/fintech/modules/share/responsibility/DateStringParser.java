package com.tpfh.fintech.modules.share.responsibility;

import java.util.Date;

/**
 * 日期格式处理
 * @author Taiping
 *
 */
public abstract class DateStringParser {

	private DateStringParser next;

	public DateStringParser getNext() {
		return next;
	}

	public void setNext(DateStringParser next) {
		this.next = next;
	}

	public abstract Date kernelHandle(String dateString);
	
	public Date doHandle(String dateString) throws Exception{
		Date date = this.kernelHandle(dateString);
		if(date==null && next!=null) {
			return next.doHandle(dateString);
		}else {
			return date;
		}
	}
}
