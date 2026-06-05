/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cn.hutool.core.util.ArrayUtil
 *  com.alibaba.fastjson.JSONObject
 *  com.google.common.base.Charsets
 *  com.opencsv.CSVParser
 *  com.opencsv.CSVParserBuilder
 *  com.opencsv.CSVReader
 *  com.opencsv.CSVReaderBuilder
 *  com.opencsv.ICSVParser
 *  org.springframework.web.multipart.MultipartFile
 */
package com.tpfh.fintech.modules.share.template;

import cn.hutool.core.util.ArrayUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Charsets;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.ICSVParser;
import com.tpfh.fintech.modules.share.template.FileColumnToClassFiledMapping;
import com.tpfh.fintech.modules.share.template.FileToClassMapping;
import com.tpfh.fintech.modules.share.template.ReadDataFromFileTemplate;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public class CSVReadDataFromFileTemplate<T>
extends ReadDataFromFileTemplate<T> {
    @Override
    public List<T> parseFile(MultipartFile file, FileToClassMapping fileToClassMapping, Class<T> clazz) throws Exception {
        ArrayList<Object> list = new ArrayList<Object>();
        char separator = this.getSeparatorChar(fileToClassMapping.getCsvSeparatorChar());
        CSVParser parser = new CSVParserBuilder().withSeparator(separator).build();
        CSVReaderBuilder builder = new CSVReaderBuilder((Reader)new InputStreamReader(file.getInputStream(), Charsets.UTF_8));
        CSVReader csvReader = builder.withCSVParser((ICSVParser)parser).build();
        int currentRowNum = 0;
        Integer startLineNum = null;
        Integer endLineNum = null;
        Object[] arr = null;
        while ((arr = csvReader.readNext()) != null) {
            ++currentRowNum;
            if (arr.length == 1 && "".equals(arr[0].trim())) continue;
            if (startLineNum == null) {
                startLineNum = this.getLocationRowNum(fileToClassMapping.getStartLineStrategyType(), fileToClassMapping.getStartLineStrategyValue(), currentRowNum, ArrayUtil.join((Object[])arr, (CharSequence)String.valueOf('\t')));
            }
            if (startLineNum == null || startLineNum > currentRowNum) continue;
            if (endLineNum == null) {
                endLineNum = this.getLocationRowNum(fileToClassMapping.getEndLineStrategyType(), fileToClassMapping.getEndLineStrategyValue(), currentRowNum, ArrayUtil.join((Object[])arr, (CharSequence)String.valueOf('\t')));
            }
            if (endLineNum != null && currentRowNum >= endLineNum) break;
            boolean isDiscard = this.checkIfCurrentRowDiscard((String[])arr, fileToClassMapping.getList());
            if (isDiscard) continue;
            JSONObject json = new JSONObject();
            List<FileColumnToClassFiledMapping> ecttfmList = fileToClassMapping.getList();
            for (FileColumnToClassFiledMapping ecttfm : ecttfmList) {
                Object filedValue = arr[ecttfm.getColumnNum() - 1];
                Object obj = this.getClassPropertieValueBySelfType(ecttfm.getClassType(), (String)filedValue);
                json.put(ecttfm.getFieldName(), obj);
            }
            Object t = json.toJavaObject(clazz);
            list.add(t);
        }
        try {
            try {
                csvReader.close();
            }
            catch (IOException iOException) {
                csvReader = null;
            }
        }
        finally {
            csvReader = null;
        }
        return list;
    }

    private char getSeparatorChar(String separator) {
        if ("EMPTY".equals(separator)) {
            return ' ';
        }
        if ("TAB".equals(separator)) {
            return '\t';
        }
        if ("COMMA".equals(separator)) {
            return ',';
        }
        return ',';
    }

    public static void main(String[] args) {
        Object[] arr = new String[]{"fs"};
        System.out.println(ArrayUtil.join((Object[])arr, (CharSequence)String.valueOf('\t')));
    }
}

