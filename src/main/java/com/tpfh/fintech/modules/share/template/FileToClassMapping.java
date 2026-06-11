package com.tpfh.fintech.modules.share.template;

import java.util.List;

/**
 * excel文件与数据库表关系表
 * @author Taiping
 *
 */
public class FileToClassMapping {
	private String bankCode;
	private String statementOrBalance;
	
	private String parserFileType;//excel,cvs
	private Integer sheetNum;//excel文件sheet的index
	private String csvSeparatorChar;//csv文件数据分割符
	
	//数据读取起始、结束行的获取策略类型、策略值
	private String startLineStrategyType;
	private String startLineStrategyValue;
	private String endLineStrategyType;
	private String endLineStrategyValue;
	
	//检查每行的数据是否合法的策略类型、策略值
	private String checkRowStrategyType;
	private String checkRowStrategyValue;
	
	//其每个列对应的表字段的对应关系
	private List<FileColumnToClassFiledMapping> list;
	
	public FileToClassMapping() {}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getStatementOrBalance() {
		return statementOrBalance;
	}

	public void setStatementOrBalance(String statementOrBalance) {
		this.statementOrBalance = statementOrBalance;
	}

	public String getCsvSeparatorChar() {
		return csvSeparatorChar;
	}

	public void setCsvSeparatorChar(String csvSeparatorChar) {
		this.csvSeparatorChar = csvSeparatorChar;
	}

	public Integer getSheetNum() {
		return sheetNum;
	}

	public void setSheetNum(Integer sheetNum) {
		this.sheetNum = sheetNum;
	}

	public String getStartLineStrategyType() {
		return startLineStrategyType;
	}

	public void setStartLineStrategyType(String startLineStrategyType) {
		this.startLineStrategyType = startLineStrategyType;
	}

	public String getStartLineStrategyValue() {
		return startLineStrategyValue;
	}

	public void setStartLineStrategyValue(String startLineStrategyValue) {
		this.startLineStrategyValue = startLineStrategyValue;
	}

	public String getEndLineStrategyType() {
		return endLineStrategyType;
	}

	public void setEndLineStrategyType(String endLineStrategyType) {
		this.endLineStrategyType = endLineStrategyType;
	}

	public String getEndLineStrategyValue() {
		return endLineStrategyValue;
	}

	public void setEndLineStrategyValue(String endLineStrategyValue) {
		this.endLineStrategyValue = endLineStrategyValue;
	}

	public String getCheckRowStrategyType() {
		return checkRowStrategyType;
	}

	public void setCheckRowStrategyType(String checkRowStrategyType) {
		this.checkRowStrategyType = checkRowStrategyType;
	}

	public String getCheckRowStrategyValue() {
		return checkRowStrategyValue;
	}

	public void setCheckRowStrategyValue(String checkRowStrategyValue) {
		this.checkRowStrategyValue = checkRowStrategyValue;
	}

	public List<FileColumnToClassFiledMapping> getList() {
		return list;
	}

	public void setList(List<FileColumnToClassFiledMapping> list) {
		this.list = list;
	}

	public String getParserFileType() {
		return parserFileType;
	}

	public void setParserFileType(String parserFileType) {
		this.parserFileType = parserFileType;
	}
	
}
