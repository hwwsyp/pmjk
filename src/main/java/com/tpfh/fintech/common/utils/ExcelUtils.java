package com.tpfh.fintech.common.utils;

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
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class ExcelUtils {
	private static final Logger log = LoggerFactory.getLogger(ExcelUtils.class);
	
	/**
	 * add by owen in 2022-03-04  
	 *   用于多个sheet合并为一个excel文件使用
	 * @param wb
	 * @param sheetName
	 * @param title
	 * @param contentList
	 * @return
	 */
	public static HSSFSheet getHSSFSheet(HSSFWorkbook wb, String sheetName, String []title,  List<List<String>> contentList) {
		if(wb==null) return null;
		
		// 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(sheetName);
        sheet.setDefaultColumnWidth(20);
		sheet.setDefaultRowHeightInPoints(16);

		HSSFCellStyle headerStyle = getHeaderStyle(wb);

        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
        HSSFRow row = sheet.createRow(0);

        HSSFCellStyle contentStyle = getContentStyle(wb);

        //声明列对象
        HSSFCell cell = null;

        //创建标题
        for(int i=0;i<title.length;i++){
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(headerStyle);
        }
        for (int i = 0;i < contentList.size();i++) {
        	List<String> content = contentList.get(i);
        	row = sheet.createRow(i + 1);
        	for(int j = 0;j < content.size();j++){
        		HSSFCell contentCell = row.createCell(j);
                //将内容按顺序赋给对应的列对象
        		contentCell.setCellValue(content.get(j));
        		contentCell.setCellStyle(contentStyle);
            }
		}
		setSizeColumn(sheet,title.length);
        return sheet;
	}
	
    /**
     * 导出Excel
     * @param sheetName sheet名称
     * @param title 标题
     * @param values 内容
     * @return
     */
    public static HSSFWorkbook getHSSFWorkbook(String sheetName, String []title,  List<List<String>> contentList){

        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
    	HSSFWorkbook wb = new HSSFWorkbook();


        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(sheetName);
        sheet.setDefaultColumnWidth(20);
		sheet.setDefaultRowHeightInPoints(16);

		HSSFCellStyle headerStyle = getHeaderStyle(wb);

        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
        HSSFRow row = sheet.createRow(0);

        HSSFCellStyle contentStyle = getContentStyle(wb);

        //声明列对象
        HSSFCell cell = null;

        //创建标题
        for(int i=0;i<title.length;i++){
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(headerStyle);
        }
        for (int i = 0;i < contentList.size();i++) {
        	List<String> content = contentList.get(i);
        	row = sheet.createRow(i + 1);
        	for(int j = 0;j < content.size();j++){
        		HSSFCell contentCell = row.createCell(j);
                //将内容按顺序赋给对应的列对象
        		contentCell.setCellValue(content.get(j));
        		contentCell.setCellStyle(contentStyle);
            }
		}
		setSizeColumn(sheet,title.length);
        return wb;
    }

	/**
	 * 导出数据合并的excel
	 * @param sheetName
	 * @param title
	 * @param contentList 要导出的数据
	 * @param mergeCellList 要合并的单元格
	 * @return
	 */
	public static HSSFWorkbook getHSSFWorkbookMergeCell(String sheetName, String[] title, List<List<String>> contentList, List<String> mergeCellList ) {
		// 第一步，创建一个HSSFWorkbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();


		// 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet(sheetName);
		sheet.setDefaultColumnWidth(20);
		sheet.setDefaultRowHeightInPoints(16);

		HSSFCellStyle headerStyle = getHeaderStyle(wb);

		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
		HSSFRow row = sheet.createRow(0);

		HSSFCellStyle contentStyle = getContentStyle(wb);

		//声明列对象
		HSSFCell cell = null;

		//创建标题
		for(int i=0;i<title.length;i++){
			cell = row.createCell(i);
			cell.setCellValue(title[i]);
			cell.setCellStyle(headerStyle);
		}
		for (int i = 0;i < contentList.size();i++) {
			List<String> content = contentList.get(i);
			row = sheet.createRow(i + 1);
			for(int j = 0;j < content.size();j++){
				HSSFCell contentCell = row.createCell(j);
				//将内容按顺序赋给对应的列对象
				contentCell.setCellValue(content.get(j));
				contentCell.setCellStyle(contentStyle);
			}
		}
		for(String item:mergeCellList){
			String[] cellArray = item.split(",");
			CellRangeAddress cra = new CellRangeAddress(Integer.valueOf(cellArray[0]), Integer.valueOf(cellArray[1]), Integer.valueOf(cellArray[2]), Integer.valueOf(cellArray[3]));
			sheet.addMergedRegion(cra);
			RegionUtil.setBorderLeft(BorderStyle.THIN, cra, sheet); // 左边框
			RegionUtil.setBorderRight(BorderStyle.THIN, cra, sheet); // 右边框
		}
		setSizeColumn(sheet,title.length);
		return wb;
	}

    /**
     * 合并表头
     * @param sheetName
     * @param level1Title
     * @param level2Title
     * @param contentList
     * @return
     */
	public static HSSFWorkbook getHSSFWorkbookMergeCell(String sheetName, String[] level1Title, String[] level2Title,List<List<String>> contentList) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		HSSFSheet sheet = workbook.createSheet(sheetName);
		sheet.setDefaultColumnWidth(20);
		sheet.setDefaultRowHeightInPoints(16);	
		
		HSSFCellStyle headerStyle = getHeaderStyle(workbook);
		//String[] header1 = new String[] {"0,1,0,0","0,1,1,1","0,1,2,2","0,1,3,3","0,1,4,4","0,1,5,5","0,1,6,6","0,1,7,7","0,1,8,8","0,1,9,9","0,1,10,10","0,1,11,11","0,1,12,12","0,1,13,13","0,1,14,14","0,1,15,15","0,1,16,16","0,0,17,24","0,0,25,27","0,0,28,29","0,0,30,31","0,0,32,33","0,0,34,35"};
		String[] header1 = new String[] {"0,1,0,0","0,1,1,1","0,1,2,2","0,1,3,3","0,1,4,4","0,1,5,5","0,1,6,6","0,1,7,7","0,1,8,8","0,1,9,9","0,1,10,10","0,1,11,11","0,1,12,12","0,1,13,13","0,1,14,14","0,0,15,22","0,0,23,25","0,0,26,27","0,0,28,29","0,0,30,31","0,0,32,33"};

		HSSFRow row = sheet.createRow(0);
		HSSFCell cell = row.createCell(0);
		// 动态合并单元格
		for (int i = 0; i < header1.length; i++) {
			String[] temp = header1[i].split(",");
			Integer startrow = Integer.parseInt(temp[0]);
			Integer overrow = Integer.parseInt(temp[1]);
			Integer startcol = Integer.parseInt(temp[2]);
			Integer overcol = Integer.parseInt(temp[3]);
			//sheet.addMergedRegion(new CellRangeAddress(startrow, overrow, startcol, overcol));
			CellRangeAddress cra = new CellRangeAddress(startrow, overrow, startcol, overcol);
			sheet.addMergedRegion(cra);	
			cell = row.createCell(startcol);			  
	        cell.setCellValue(level1Title[i]);
	        cell.setCellStyle(headerStyle);	            
		}
		
		int header2Colum = 15;
		row = sheet.createRow(1);
		for (int i = 0; i < level2Title.length; i++) {
			cell = row.createCell(i+header2Colum);			
	        cell.setCellValue(level2Title[i]);
	        cell.setCellStyle(headerStyle);
		}		
		setEmptyCellStyle(row,0,header2Colum,headerStyle);
		
		HSSFCellStyle contentStyle = getContentStyle(workbook);		
		for (int i = 0;i < contentList.size();i++) {
        	List<String> content = contentList.get(i);
        	row = sheet.createRow(i + 2);
        	for(int j = 0;j < content.size();j++){
        		HSSFCell contentCell = row.createCell(j);
                //将内容按顺序赋给对应的列对象
        		contentCell.setCellValue(content.get(j));
        		contentCell.setCellStyle(contentStyle);
            }
		}
		setSizeColumn(sheet,level1Title.length+level2Title.length);
		return workbook;
	}
	private static void setEmptyCellStyle(HSSFRow row, int startNum, int endNum, HSSFCellStyle style){
		for(int j=startNum;j<endNum;j++){
			HSSFCell cell = row.createCell(j);
			cell.setCellStyle(style);
		}
	}

	/**
	 * @Description:表格自适应宽度(中文支持)
	 * @Author:
	 * @param sheet sheet
	 * @param columnLength 列数
	 */
	private static void setSizeColumn(HSSFSheet sheet, int columnLength) {
		for (int columnNum = 0; columnNum <= columnLength; columnNum++) {
			int columnWidth = sheet.getColumnWidth(columnNum) / 256;
			for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
				HSSFRow currentRow; // 当前行未被使用过
				if (sheet.getRow(rowNum) == null) {
					currentRow = sheet.createRow(rowNum);
				} else {
					currentRow = sheet.getRow(rowNum);
				}
				if (currentRow.getCell(columnNum) != null) {
					HSSFCell currentCell = currentRow.getCell(columnNum);
					if (currentCell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
						int length = currentCell.getStringCellValue().getBytes().length;
						if (columnWidth < length) {
							columnWidth = length;
						}
					}
				}
			}
			sheet.setColumnWidth(columnNum, columnWidth * 256);
		}
	}

	//设置表头样式
	public static HSSFCellStyle getHeaderStyle(HSSFWorkbook workbook){
		// 第一行标题样式 表格title 黑色，14号字体，加粗，合并居中
		HSSFCellStyle headerStyle = workbook.createCellStyle();
		//设置字体
	    HSSFFont font = workbook.createFont();		
		font.setFontName("微软雅黑");
		font.setFontHeightInPoints((short) 11);
		headerStyle.setFont(font);
		headerStyle.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
		headerStyle.setFillBackgroundColor(IndexedColors.PALE_BLUE.getIndex());
		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		headerStyle.setBorderBottom(BorderStyle.THIN);// 设置边下框
		headerStyle.setBorderLeft(BorderStyle.THIN);// 设置左边框
		headerStyle.setBorderRight(BorderStyle.THIN);// 设置右边框
		headerStyle.setBorderTop(BorderStyle.THIN);// 设置上边框
		headerStyle.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());// 设置边框颜色
		headerStyle.setAlignment(HorizontalAlignment.CENTER);//水平居中
		headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
		return headerStyle;
	}
	//设置内容样式
	public static HSSFCellStyle getContentStyle(HSSFWorkbook workbook){
		HSSFCellStyle contentStyle = workbook.createCellStyle(); 
		//设置字体
	    HSSFFont font = workbook.createFont();
		font.setFontName("微软雅黑");
		font.setFontHeightInPoints((short) 11);			
		HSSFDataFormat format = workbook.createDataFormat();
        contentStyle.setDataFormat(format.getFormat("@"));
        contentStyle.setFont(font);
        contentStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        contentStyle.setBorderBottom(BorderStyle.THIN);
        contentStyle.setBorderLeft(BorderStyle.THIN);
        contentStyle.setBorderRight(BorderStyle.THIN);
        contentStyle.setBorderTop(BorderStyle.THIN);
        contentStyle.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        contentStyle.setAlignment(HorizontalAlignment.CENTER);//水平居中
        contentStyle.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
        return contentStyle;
	}
	/**
     * 发送响应流方法
     */
    public static void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}