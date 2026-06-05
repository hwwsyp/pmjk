/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.alibaba.druid.util.StringUtils
 *  org.springframework.web.multipart.MultipartFile
 */
package com.tpfh.fintech.modules.share.template;

import com.alibaba.druid.util.StringUtils;
import com.tpfh.fintech.modules.share.responsibility.ChainResponsibilityOfDateStringParser;
import com.tpfh.fintech.modules.share.template.FileColumnToClassFiledMapping;
import com.tpfh.fintech.modules.share.template.FileToClassMapping;
import com.tpfh.fintech.modules.share.template.LocationRowNumStrategyEnum;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public abstract class ReadDataFromFileTemplate<T> {
    private DecimalFormat decimalFormat = new DecimalFormat(",###.##");

    public abstract List<T> parseFile(MultipartFile var1, FileToClassMapping var2, Class<T> var3) throws Exception;

    public Boolean checkIfCurrentRowDiscard(String[] arr, List<FileColumnToClassFiledMapping> list) throws Exception {
        if (arr == null || list == null) {
            throw new Exception("arr or mapping lost");
        }
        for (FileColumnToClassFiledMapping mapping : list) {
            if (!mapping.getIsRequired().booleanValue()) continue;
            if (mapping.getColumnNum() > arr.length) {
                return true;
            }
            String value = arr[mapping.getColumnNum() - 1];
            if (value != null && !"".equals(value.trim())) continue;
            return true;
        }
        return false;
    }

    public Object getClassPropertieValueBySelfType(String classType, String value) throws Exception {
        Object obj = null;
        switch (classType) {
            case "String": {
                obj = value;
                break;
            }
            case "BigDecimal": {
                if (StringUtils.isNumber((String)value)) {
                    obj = new BigDecimal(value);
                    break;
                }
                if (value.indexOf(",") <= -1) break;
                obj = new BigDecimal(this.decimalFormat.parse(value).toString());
                break;
            }
            case "Date": {
                Date date = ChainResponsibilityOfDateStringParser.doHandle(value);
                if (date == null) break;
                obj = new SimpleDateFormat("yyyy-MM-dd").format(date);
                break;
            }
            default: {
                throw new Exception("parse value error");
            }
        }
        if (obj == null) {
            throw new Exception("parse value error");
        }
        return obj;
    }

    public Integer getLocationRowNum(String rowNumStrategyType, String rowNumStrategyValue, Integer currentLineNum, String currentLineValue) throws Exception {
        LocationRowNumStrategyEnum rowNumStrategyEnum = LocationRowNumStrategyEnum.valueOf(rowNumStrategyType);
        if (LocationRowNumStrategyEnum.NUMBER.equals((Object)rowNumStrategyEnum)) {
            if ("-1".equals(rowNumStrategyValue)) {
                return Integer.MAX_VALUE;
            }
            return Integer.parseInt(rowNumStrategyValue);
        }
        if (LocationRowNumStrategyEnum.VALUE_SEARCH_NEXT.equals((Object)rowNumStrategyEnum)) {
            if (currentLineValue != null && rowNumStrategyValue != null && currentLineValue.indexOf(rowNumStrategyValue) > -1) {
                return currentLineNum + 1;
            }
            return null;
        }
        if (LocationRowNumStrategyEnum.VALUE_SEARCH_CURRENT.equals((Object)rowNumStrategyEnum)) {
            if (currentLineValue != null && rowNumStrategyValue != null && currentLineValue.indexOf(rowNumStrategyValue) > -1) {
                return currentLineNum;
            }
            return null;
        }
        return null;
    }
}

