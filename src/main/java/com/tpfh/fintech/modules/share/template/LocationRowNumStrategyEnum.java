package com.tpfh.fintech.modules.share.template;

public enum LocationRowNumStrategyEnum {
	NUMBER,//直接指定行号
	VALUE_SEARCH_CURRENT,//通过搜索符合指定字符串的所在第一行号来定位
	VALUE_SEARCH_NEXT;//通过搜索符合指定的字符串的所在行的下一行定位
	
	public static void main(String[] args) {
		System.out.println(LocationRowNumStrategyEnum.valueOf("NUMBER"));
	}
}
