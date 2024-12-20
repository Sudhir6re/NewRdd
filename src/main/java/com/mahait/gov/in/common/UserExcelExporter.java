package com.mahait.gov.in.common;

import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mahait.gov.in.model.PayBillViewApprDelBillModel;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
 
public class UserExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<PayBillViewApprDelBillModel> lstPayBillObj;
     
    public UserExcelExporter(List<PayBillViewApprDelBillModel> lstPayBillObj) {
        this.lstPayBillObj = lstPayBillObj;
        workbook = new XSSFWorkbook();
    }
 
 
    private void writeHeaderLine() {
        sheet = workbook.createSheet("ChangeStatement");
         
        Row row = sheet.createRow(0);
         
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
         
        createCell(row, 0, "Sr.No.", style);      
        createCell(row, 1, "Employee Name and Designation", style);       
        createCell(row, 2, "Bill Type", style);    
        createCell(row, 3, "Last Month Amount", style);
        createCell(row, 4, "Current Month Amount", style);
        createCell(row, 5, "The Effect of Change on the Payment Amount (+/-)", style);
        createCell(row, 6, "Order No.and Date", style);
         
    }
     
    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }
     
    private void writeDataLines() {
        int rowCount = 1;
 
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        int no = 1;
        for (PayBillViewApprDelBillModel paBillModel : lstPayBillObj) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, no, style);
            createCell(row, columnCount++, paBillModel.getEmpName(), style);
            createCell(row, columnCount++, paBillModel.getComponentName(), style);
            createCell(row, columnCount++, paBillModel.getPreviousMonthAmount().toString(), style);
            createCell(row, columnCount++, paBillModel.getCurrentMonthAmount().toString(), style);
            createCell(row, columnCount++, paBillModel.getDifference().toString(), style);
            createCell(row, columnCount++, paBillModel.getDateAndTime(), style);
           no++;
             
        }
    }
     
    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();
         
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
         
        outputStream.close();
         
    }
}
