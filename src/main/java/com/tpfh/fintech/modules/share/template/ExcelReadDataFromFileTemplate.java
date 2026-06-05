/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cn.hutool.core.util.ArrayUtil
 *  com.alibaba.fastjson.JSONObject
 *  org.apache.poi.hssf.usermodel.HSSFDateUtil
 *  org.apache.poi.hssf.usermodel.HSSFWorkbook
 *  org.apache.poi.ss.usermodel.Cell
 *  org.apache.poi.ss.usermodel.CellType
 *  org.apache.poi.ss.usermodel.CellValue
 *  org.apache.poi.ss.usermodel.DateUtil
 *  org.apache.poi.ss.usermodel.Row
 *  org.apache.poi.ss.usermodel.Sheet
 *  org.apache.poi.ss.util.NumberToTextConverter
 *  org.apache.poi.xssf.usermodel.XSSFWorkbook
 *  org.springframework.web.multipart.MultipartFile
 */
package com.tpfh.fintech.modules.share.template;

import cn.hutool.core.util.ArrayUtil;
import com.alibaba.fastjson.JSONObject;
import com.tpfh.fintech.modules.share.template.FileColumnToClassFiledMapping;
import com.tpfh.fintech.modules.share.template.FileToClassMapping;
import com.tpfh.fintech.modules.share.template.ReadDataFromFileTemplate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

public class ExcelReadDataFromFileTemplate<T>
extends ReadDataFromFileTemplate<T> {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public List<T> parseFile(MultipartFile file, FileToClassMapping fileToClassMapping, Class<T> clazz) throws Exception {
        XSSFWorkbook workbook = null;
        if ("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet".equals(file.getContentType())) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if ("application/vnd.ms-excel".equals(file.getContentType())) {
            workbook = new HSSFWorkbook(file.getInputStream());
        }
        int sheetIndex = fileToClassMapping.getSheetNum() - 1;
        Sheet sheet = workbook.getSheetAt(sheetIndex);
        ArrayList<Object> list = new ArrayList<Object>();
        int rowNum = sheet.getLastRowNum() + 1;
        int currentRowNum = 0;
        Integer startLineNum = null;
        Integer endLineNum = null;
        int i = 0;
        while (i < rowNum) {
            ++currentRowNum;
            Row row = sheet.getRow(i);
            if (row != null) {
                Object[] arr = new String[]{};
                int j = 0;
                while (j <= row.getLastCellNum()) {
                    String cellValue = ExcelReadDataFromFileTemplate.getCellValue(row, j);
                    arr = (String[])ArrayUtil.insert((Object[])arr, (int)arr.length, (Object[])new String[]{cellValue});
                    ++j;
                }
                if (startLineNum == null) {
                    startLineNum = this.getLocationRowNum(fileToClassMapping.getStartLineStrategyType(), fileToClassMapping.getStartLineStrategyValue(), currentRowNum, ArrayUtil.join((Object[])arr, (CharSequence)String.valueOf('\t')));
                }
                if (startLineNum != null && startLineNum <= currentRowNum) {
                    if (endLineNum == null) {
                        endLineNum = this.getLocationRowNum(fileToClassMapping.getEndLineStrategyType(), fileToClassMapping.getEndLineStrategyValue(), currentRowNum, ArrayUtil.join((Object[])arr, (CharSequence)String.valueOf('\t')));
                    }
                    if (endLineNum != null && currentRowNum >= endLineNum) break;
                    boolean isDiscard = this.checkIfCurrentRowDiscard((String[])arr, fileToClassMapping.getList());
                    if (!isDiscard) {
                        JSONObject json = new JSONObject();
                        List<FileColumnToClassFiledMapping> ecttfmList = fileToClassMapping.getList();
                        for (FileColumnToClassFiledMapping ecttfm : ecttfmList) {
                            Object obj = null;
                            if (ecttfm.getColumnNum() > arr.length) {
                                obj = null;
                            } else {
                                Object filedValue = arr[ecttfm.getColumnNum() - 1];
                                obj = this.getClassPropertieValueBySelfType(ecttfm.getClassType(), (String)filedValue);
                            }
                            json.put(ecttfm.getFieldName(), obj);
                        }
                        Object t = json.toJavaObject(clazz);
                        list.add(t);
                    }
                }
            }
            ++i;
        }
        return list;
    }

    public static String getCellValue(Row row, int columnIndex) throws Exception {
        if (columnIndex > row.getLastCellNum()) {
            throw new Exception("out of index");
        }
        Cell cell = row.getCell(columnIndex);
        String cellStr = "";
        if (cell != null && !"".equals(cell.toString().trim())) {
            if (cell.getCellTypeEnum() == CellType.STRING) {
                cellStr = cell.getStringCellValue();
            } else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
                short format = cell.getCellStyle().getDataFormat();
                if (format == 14 || format == 31 || format == 57 || format == 58) {
                    Date date = DateUtil.getJavaDate((double)cell.getNumericCellValue());
                    cellStr = dateFormat.format(date);
                } else if (format == 165) {
                    Date date = HSSFDateUtil.getJavaDate((double)cell.getNumericCellValue());
                    cellStr = dateFormat.format(date);
                } else {
                    cellStr = NumberToTextConverter.toText((double)cell.getNumericCellValue());
                }
            } else if (cell.getCellTypeEnum() == CellType.FORMULA) {
                CellValue cellValue = row.getSheet().getWorkbook().getCreationHelper().createFormulaEvaluator().evaluate(cell);
                if (cellValue.getCellTypeEnum().equals((Object)CellType.NUMERIC)) {
                    cellStr = String.valueOf(cellValue.getNumberValue());
                } else if (cellValue.getCellTypeEnum().equals((Object)CellType.STRING)) {
                    cellStr = cellValue.getStringValue();
                }
            } else {
                throw new Exception("can't parse this cell");
            }
            cellStr = cellStr.trim();
        }
        return cellStr;
    }
}

