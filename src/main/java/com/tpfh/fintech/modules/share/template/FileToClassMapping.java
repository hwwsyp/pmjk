/*
 * Decompiled with CFR 0.152.
 */
package com.tpfh.fintech.modules.share.template;

import com.tpfh.fintech.modules.share.template.FileColumnToClassFiledMapping;
import java.util.List;

public class FileToClassMapping {
    private String bankCode;
    private String statementOrBalance;
    private String parserFileType;
    private Integer sheetNum;
    private String csvSeparatorChar;
    private String startLineStrategyType;
    private String startLineStrategyValue;
    private String endLineStrategyType;
    private String endLineStrategyValue;
    private String checkRowStrategyType;
    private String checkRowStrategyValue;
    private List<FileColumnToClassFiledMapping> list;

    public String getBankCode() {
        return this.bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getStatementOrBalance() {
        return this.statementOrBalance;
    }

    public void setStatementOrBalance(String statementOrBalance) {
        this.statementOrBalance = statementOrBalance;
    }

    public String getCsvSeparatorChar() {
        return this.csvSeparatorChar;
    }

    public void setCsvSeparatorChar(String csvSeparatorChar) {
        this.csvSeparatorChar = csvSeparatorChar;
    }

    public Integer getSheetNum() {
        return this.sheetNum;
    }

    public void setSheetNum(Integer sheetNum) {
        this.sheetNum = sheetNum;
    }

    public String getStartLineStrategyType() {
        return this.startLineStrategyType;
    }

    public void setStartLineStrategyType(String startLineStrategyType) {
        this.startLineStrategyType = startLineStrategyType;
    }

    public String getStartLineStrategyValue() {
        return this.startLineStrategyValue;
    }

    public void setStartLineStrategyValue(String startLineStrategyValue) {
        this.startLineStrategyValue = startLineStrategyValue;
    }

    public String getEndLineStrategyType() {
        return this.endLineStrategyType;
    }

    public void setEndLineStrategyType(String endLineStrategyType) {
        this.endLineStrategyType = endLineStrategyType;
    }

    public String getEndLineStrategyValue() {
        return this.endLineStrategyValue;
    }

    public void setEndLineStrategyValue(String endLineStrategyValue) {
        this.endLineStrategyValue = endLineStrategyValue;
    }

    public String getCheckRowStrategyType() {
        return this.checkRowStrategyType;
    }

    public void setCheckRowStrategyType(String checkRowStrategyType) {
        this.checkRowStrategyType = checkRowStrategyType;
    }

    public String getCheckRowStrategyValue() {
        return this.checkRowStrategyValue;
    }

    public void setCheckRowStrategyValue(String checkRowStrategyValue) {
        this.checkRowStrategyValue = checkRowStrategyValue;
    }

    public List<FileColumnToClassFiledMapping> getList() {
        return this.list;
    }

    public void setList(List<FileColumnToClassFiledMapping> list) {
        this.list = list;
    }

    public String getParserFileType() {
        return this.parserFileType;
    }

    public void setParserFileType(String parserFileType) {
        this.parserFileType = parserFileType;
    }
}

