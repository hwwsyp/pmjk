package com.tpfh.fintech.modules.share.template;

public class FileColumnToClassFiledMapping {
	private Integer columnNum;
	private String fieldName;
	private String classType;
	private Boolean isRequired;

	public FileColumnToClassFiledMapping() {}

	public Boolean getIsRequired() {
		return isRequired;
	}

	public void setIsRequired(Boolean isRequired) {
		this.isRequired = isRequired;
	}


	public Integer getColumnNum() {
		return columnNum;
	}

	public void setColumnNum(Integer columnNum) {
		this.columnNum = columnNum;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}
	
}
