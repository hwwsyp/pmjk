/*
 * Decompiled with CFR 0.152.
 */
package com.tpfh.fintech.modules.share.template;

public class FileColumnToClassFiledMapping {
    private Integer columnNum;
    private String fieldName;
    private String classType;
    private Boolean isRequired;

    public Boolean getIsRequired() {
        return this.isRequired;
    }

    public void setIsRequired(Boolean isRequired) {
        this.isRequired = isRequired;
    }

    public Integer getColumnNum() {
        return this.columnNum;
    }

    public void setColumnNum(Integer columnNum) {
        this.columnNum = columnNum;
    }

    public String getFieldName() {
        return this.fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getClassType() {
        return this.classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }
}

