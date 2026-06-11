package com.tpfh.fintech.modules.share.responsibility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 解析dd-MMM-yyyy，例如04-AUG-2022 样式的数据
 * @author Taiping
 * XXX 20220808发现一个bug，如果数据是4-AUG-22，使用当前的格式一样可以解析，但是日期最终被认为 0022-08-04。造成数据解析错误
 * 因此目前的做法是将，dd-MMM-yy的解析方式提前于 dd-MMM-yyyy进行解析
 */
public class USDatestringParser2 extends DateStringParser{
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
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
	
	/*public static void main(String[] args) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
		try {
			Date date = dateFormat.parse("25-Feb-2022");
			
			System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
*/
}
