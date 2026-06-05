/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.servlet.http.HttpServletResponse
 *  org.apache.poi.hssf.usermodel.HSSFCell
 *  org.apache.poi.hssf.usermodel.HSSFCellStyle
 *  org.apache.poi.hssf.usermodel.HSSFDataFormat
 *  org.apache.poi.hssf.usermodel.HSSFFont
 *  org.apache.poi.hssf.usermodel.HSSFRow
 *  org.apache.poi.hssf.usermodel.HSSFSheet
 *  org.apache.poi.hssf.usermodel.HSSFWorkbook
 *  org.apache.poi.ss.usermodel.BorderStyle
 *  org.apache.poi.ss.usermodel.FillPatternType
 *  org.apache.poi.ss.usermodel.HorizontalAlignment
 *  org.apache.poi.ss.usermodel.IndexedColors
 *  org.apache.poi.ss.usermodel.Sheet
 *  org.apache.poi.ss.usermodel.VerticalAlignment
 *  org.apache.poi.ss.util.CellRangeAddress
 *  org.apache.poi.ss.util.RegionUtil
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package com.tpfh.fintech.common.utils;

import java.net.URLEncoder;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExcelUtils {
    private static final Logger log = LoggerFactory.getLogger(ExcelUtils.class);

    public static HSSFSheet getHSSFSheet(HSSFWorkbook wb, String sheetName, String[] title, List<List<String>> contentList) {
        if (wb == null) {
            return null;
        }
        HSSFSheet sheet = wb.createSheet(sheetName);
        sheet.setDefaultColumnWidth(20);
        sheet.setDefaultRowHeightInPoints(16.0f);
        HSSFCellStyle headerStyle = ExcelUtils.getHeaderStyle(wb);
        HSSFRow row = sheet.createRow(0);
        HSSFCellStyle contentStyle = ExcelUtils.getContentStyle(wb);
        HSSFCell cell = null;
        int i = 0;
        while (i < title.length) {
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(headerStyle);
            ++i;
        }
        i = 0;
        while (i < contentList.size()) {
            List<String> content = contentList.get(i);
            row = sheet.createRow(i + 1);
            int j = 0;
            while (j < content.size()) {
                HSSFCell contentCell = row.createCell(j);
                contentCell.setCellValue(content.get(j));
                contentCell.setCellStyle(contentStyle);
                ++j;
            }
            ++i;
        }
        ExcelUtils.setSizeColumn(sheet, title.length);
        return sheet;
    }

    public static HSSFWorkbook getHSSFWorkbook(String sheetName, String[] title, List<List<String>> contentList) {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet(sheetName);
        sheet.setDefaultColumnWidth(20);
        sheet.setDefaultRowHeightInPoints(16.0f);
        HSSFCellStyle headerStyle = ExcelUtils.getHeaderStyle(wb);
        HSSFRow row = sheet.createRow(0);
        HSSFCellStyle contentStyle = ExcelUtils.getContentStyle(wb);
        HSSFCell cell = null;
        int i = 0;
        while (i < title.length) {
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(headerStyle);
            ++i;
        }
        i = 0;
        while (i < contentList.size()) {
            List<String> content = contentList.get(i);
            row = sheet.createRow(i + 1);
            int j = 0;
            while (j < content.size()) {
                HSSFCell contentCell = row.createCell(j);
                contentCell.setCellValue(content.get(j));
                contentCell.setCellStyle(contentStyle);
                ++j;
            }
            ++i;
        }
        ExcelUtils.setSizeColumn(sheet, title.length);
        return wb;
    }

    public static HSSFWorkbook getHSSFWorkbookMergeCell(String sheetName, String[] title, List<List<String>> contentList, List<String> mergeCellList) {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet(sheetName);
        sheet.setDefaultColumnWidth(20);
        sheet.setDefaultRowHeightInPoints(16.0f);
        HSSFCellStyle headerStyle = ExcelUtils.getHeaderStyle(wb);
        HSSFRow row = sheet.createRow(0);
        HSSFCellStyle contentStyle = ExcelUtils.getContentStyle(wb);
        HSSFCell cell = null;
        int i = 0;
        while (i < title.length) {
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(headerStyle);
            ++i;
        }
        i = 0;
        while (i < contentList.size()) {
            List<String> content = contentList.get(i);
            row = sheet.createRow(i + 1);
            int j = 0;
            while (j < content.size()) {
                HSSFCell contentCell = row.createCell(j);
                contentCell.setCellValue(content.get(j));
                contentCell.setCellStyle(contentStyle);
                ++j;
            }
            ++i;
        }
        for (String item : mergeCellList) {
            String[] cellArray = item.split(",");
            CellRangeAddress cra = new CellRangeAddress(Integer.valueOf(cellArray[0]).intValue(), Integer.valueOf(cellArray[1]).intValue(), Integer.valueOf(cellArray[2]).intValue(), Integer.valueOf(cellArray[3]).intValue());
            sheet.addMergedRegion(cra);
            RegionUtil.setBorderLeft((BorderStyle)BorderStyle.THIN, (CellRangeAddress)cra, (Sheet)sheet);
            RegionUtil.setBorderRight((BorderStyle)BorderStyle.THIN, (CellRangeAddress)cra, (Sheet)sheet);
        }
        ExcelUtils.setSizeColumn(sheet, title.length);
        return wb;
    }

    public static HSSFWorkbook getHSSFWorkbookMergeCell(String sheetName, String[] level1Title, String[] level2Title, List<List<String>> contentList) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(sheetName);
        sheet.setDefaultColumnWidth(20);
        sheet.setDefaultRowHeightInPoints(16.0f);
        HSSFCellStyle headerStyle = ExcelUtils.getHeaderStyle(workbook);
        String[] header1 = new String[]{"0,1,0,0", "0,1,1,1", "0,1,2,2", "0,1,3,3", "0,1,4,4", "0,1,5,5", "0,1,6,6", "0,1,7,7", "0,1,8,8", "0,1,9,9", "0,1,10,10", "0,1,11,11", "0,1,12,12", "0,1,13,13", "0,1,14,14", "0,0,15,22", "0,0,23,25", "0,0,26,27", "0,0,28,29", "0,0,30,31", "0,0,32,33"};
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        int i = 0;
        while (i < header1.length) {
            String[] temp = header1[i].split(",");
            Integer startrow = Integer.parseInt(temp[0]);
            Integer overrow = Integer.parseInt(temp[1]);
            Integer startcol = Integer.parseInt(temp[2]);
            Integer overcol = Integer.parseInt(temp[3]);
            CellRangeAddress cra = new CellRangeAddress(startrow.intValue(), overrow.intValue(), startcol.intValue(), overcol.intValue());
            sheet.addMergedRegion(cra);
            cell = row.createCell(startcol.intValue());
            cell.setCellValue(level1Title[i]);
            cell.setCellStyle(headerStyle);
            ++i;
        }
        int header2Colum = 15;
        row = sheet.createRow(1);
        int i2 = 0;
        while (i2 < level2Title.length) {
            cell = row.createCell(i2 + header2Colum);
            cell.setCellValue(level2Title[i2]);
            cell.setCellStyle(headerStyle);
            ++i2;
        }
        ExcelUtils.setEmptyCellStyle(row, 0, header2Colum, headerStyle);
        HSSFCellStyle contentStyle = ExcelUtils.getContentStyle(workbook);
        int i3 = 0;
        while (i3 < contentList.size()) {
            List<String> content = contentList.get(i3);
            row = sheet.createRow(i3 + 2);
            int j = 0;
            while (j < content.size()) {
                HSSFCell contentCell = row.createCell(j);
                contentCell.setCellValue(content.get(j));
                contentCell.setCellStyle(contentStyle);
                ++j;
            }
            ++i3;
        }
        ExcelUtils.setSizeColumn(sheet, level1Title.length + level2Title.length);
        return workbook;
    }

    private static void setEmptyCellStyle(HSSFRow row, int startNum, int endNum, HSSFCellStyle style) {
        int j = startNum;
        while (j < endNum) {
            HSSFCell cell = row.createCell(j);
            cell.setCellStyle(style);
            ++j;
        }
    }

    private static void setSizeColumn(HSSFSheet sheet, int columnLength) {
        int columnNum = 0;
        while (columnNum <= columnLength) {
            int columnWidth = sheet.getColumnWidth(columnNum) / 256;
            int rowNum = 0;
            while (rowNum < sheet.getLastRowNum()) {
                int length;
                HSSFCell currentCell;
                HSSFRow currentRow = sheet.getRow(rowNum) == null ? sheet.createRow(rowNum) : sheet.getRow(rowNum);
                if (currentRow.getCell(columnNum) != null && (currentCell = currentRow.getCell(columnNum)).getCellType() == 1 && columnWidth < (length = currentCell.getStringCellValue().getBytes().length)) {
                    columnWidth = length;
                }
                ++rowNum;
            }
            sheet.setColumnWidth(columnNum, columnWidth * 256);
            ++columnNum;
        }
    }

    public static HSSFCellStyle getHeaderStyle(HSSFWorkbook workbook) {
        HSSFCellStyle headerStyle = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setFontName("\u5fae\u8f6f\u96c5\u9ed1");
        font.setFontHeightInPoints((short)11);
        headerStyle.setFont(font);
        headerStyle.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
        headerStyle.setFillBackgroundColor(IndexedColors.PALE_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        return headerStyle;
    }

    public static HSSFCellStyle getContentStyle(HSSFWorkbook workbook) {
        HSSFCellStyle contentStyle = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setFontName("\u5fae\u8f6f\u96c5\u9ed1");
        font.setFontHeightInPoints((short)11);
        HSSFDataFormat format = workbook.createDataFormat();
        contentStyle.setDataFormat(format.getFormat("@"));
        contentStyle.setFont(font);
        contentStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        contentStyle.setBorderBottom(BorderStyle.THIN);
        contentStyle.setBorderLeft(BorderStyle.THIN);
        contentStyle.setBorderRight(BorderStyle.THIN);
        contentStyle.setBorderTop(BorderStyle.THIN);
        contentStyle.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        contentStyle.setAlignment(HorizontalAlignment.CENTER);
        contentStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        return contentStyle;
    }

    public static void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

