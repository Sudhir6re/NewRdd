package com.mahait.gov.in.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mahait.gov.in.model.ColumnVo;

public class ExcelExportHelper {
	
    public static final int DATA_TYPE_BIGDECIMAL = 4;
    public byte[] getExcelReportPrintFormat(List mainData, List columndata, String[] param, List HeaderData, List footerdata) throws IOException {
        int nrowCount = 0;
        int ncolCount = 0;
        String strFilepath = "ExcelReport_" + System.currentTimeMillis() + ".xlsx";
        File reportFile = new File(strFilepath);

        // Create Workbook
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet1");

        // Create cell styles
        CellStyle titleStyle = createCellStyle(workbook, (short) 14, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, IndexedColors.WHITE.getIndex());
        CellStyle subtitleStyle = createCellStyle(workbook, (short) 12, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, IndexedColors.ROSE.getIndex());
        CellStyle headerStyle = createCellStyle(workbook, (short) 10, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, IndexedColors.WHITE.getIndex());
        CellStyle contentStyle = createCellStyle(workbook, (short) 10, false, HorizontalAlignment.LEFT, VerticalAlignment.CENTER, IndexedColors.WHITE.getIndex());

        byte[] data = null;

        try {
            // Iterate over mainData rows
            for (int count = 0; count < mainData.size(); count++) {
                ArrayList arrOuter1 = (ArrayList) mainData.get(count);
                String strParameterString = (count < param.length) ? param[count] : null;
                ColumnVo[] columnHeading = (ColumnVo[]) columndata.get(count);
                String Footer = (count < footerdata.size()) ? (String) footerdata.get(count) : null;
                String Header = (count < HeaderData.size()) ? (String) HeaderData.get(count) : null;

                // Header (Report Title)
                if (Header != null && !Header.isEmpty()) {
                    Row headerRow = sheet.createRow(nrowCount++);
                    Cell headerCell = headerRow.createCell(ncolCount);
                    headerCell.setCellValue(Header);
                    headerCell.setCellStyle(titleStyle);
                    sheet.addMergedRegion(new CellRangeAddress(nrowCount - 1, nrowCount - 1, ncolCount, columnHeading.length - 1));
                }

                // Parameter String
                if (strParameterString != null && !strParameterString.isEmpty()) {
                    Row paramRow = sheet.createRow(nrowCount++);
                    Cell paramCell = paramRow.createCell(ncolCount);
                    paramCell.setCellValue(strParameterString);
                    paramCell.setCellStyle(subtitleStyle);
                    sheet.addMergedRegion(new CellRangeAddress(nrowCount - 1, nrowCount - 1, ncolCount, columnHeading.length - 1));
                }

                // Column Headers
                Row headerRow = sheet.createRow(nrowCount++);
                for (int j = 0; j < columnHeading.length; j++) {
                    sheet.setColumnWidth(j, columnHeading[j].getWidth() * 256);  // Adjust column width
                    Cell headerCell = headerRow.createCell(j);
                    headerCell.setCellValue(columnHeading[j].getColumnName().toUpperCase());
                    headerCell.setCellStyle(subtitleStyle);
                }

                // Data Rows
                for (Object inner : arrOuter1) {
                    ArrayList arrInner1 = (ArrayList) inner;
                    Row dataRow = sheet.createRow(nrowCount++);
                    for (int i = 0; i < arrInner1.size(); i++) {
                        Cell cell = dataRow.createCell(i);
                        Object value = arrInner1.get(i);

                        // Handle different data types and formatting
                        if (value != null) {
                            if (columnHeading[i].getAlignMent() == 1) {
                                cell.setCellValue(value.toString());
                                cell.setCellStyle(contentStyle);
                            } else if (columnHeading[i].getAlignMent() == 2) {
                                try {
                                    if (columnHeading[i].isCurrancyFormated()) {
                                        double val = Double.parseDouble(value.toString());
                                        cell.setCellValue(val);
                                    } else {
                                        cell.setCellValue(value.toString());
                                    }
                                    cell.setCellStyle(contentStyle);
                                } catch (Exception e) {
                                    cell.setCellValue(value.toString());
                                    cell.setCellStyle(contentStyle);
                                }
                            } else if (columnHeading[i].getAlignMent() == 3) {
                                cell.setCellValue(value.toString());
                                cell.setCellStyle(headerStyle);
                            }
                        }
                    }
                }

                // Footer
                if (Footer != null && !Footer.isEmpty()) {
                    Row footerRow = sheet.createRow(nrowCount++);
                    Cell footerCell = footerRow.createCell(ncolCount);
                    footerCell.setCellValue(Footer);
                    footerCell.setCellStyle(contentStyle);
                    sheet.addMergedRegion(new CellRangeAddress(nrowCount - 1, nrowCount - 1, ncolCount, columnHeading.length - 1));
                }

                // Reset column count for the next iteration
                ncolCount = 0;
            }

            // Write to file
            try (FileOutputStream fileOut = new FileOutputStream(reportFile)) {
                workbook.write(fileOut);
            }

            // Read the file into a byte array
            try (FileInputStream fileInputStream = new FileInputStream(strFilepath)) {
                data = fileInputStream.readAllBytes();
            }

            // Clean up the temporary file
            new File(strFilepath).delete();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    private CellStyle createCellStyle(Workbook workbook, short fontSize, boolean bold, HorizontalAlignment align, VerticalAlignment vAlign, short bgColor) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontHeightInPoints(fontSize);
        font.setBold(bold);
        style.setFont(font);
        style.setAlignment(align);
        style.setVerticalAlignment(vAlign);
        style.setFillForegroundColor(bgColor);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return style;
    }

}


