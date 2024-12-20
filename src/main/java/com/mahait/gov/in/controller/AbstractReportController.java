package com.mahait.gov.in.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itextpdf.text.DocumentException;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.AbstractReportModel;
import com.mahait.gov.in.service.CommonHomeMethodsService;
import com.mahait.gov.in.service.ConsolidatedReportService;
import com.mahait.gov.in.service.PayBillViewApprDelBillService;
import com.mahait.gov.in.service.PaybillGenerationTrnService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@Controller
@RequestMapping("/ddo")
public class AbstractReportController extends BaseController{
//	protected final Log logger = LogFactory.getLog(getClass());
	
	List<AbstractReportModel> lstabsBillObj = new ArrayList<>();
	
	@Autowired
	CommonHomeMethodsService commonHomeMethodsService;
	
	@Autowired
	PayBillViewApprDelBillService payBillViewApprDelBill;
	
	
	
	@Autowired
	PaybillGenerationTrnService paybillGenerationTrnService;
	
	/*
	 * @Autowired PdfGenaratorUtil pdfGenaratorUtil;
	 */
	
	
	@Autowired
	ConsolidatedReportService consolidatedReportService;

	
	
	SimpleDateFormat sdf =new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	
	@GetMapping("/viewabstractReport/{paybillGenerationTrnId}")
	public String viewabstractReport(
			@ModelAttribute("abstractReportModel") AbstractReportModel abstractReportModel,
			@PathVariable String paybillGenerationTrnId, Model model, Locale locale, HttpSession session,HttpServletRequest request)
			throws FileNotFoundException, DocumentException {
		
		String message = (String)model.asMap().get("message");
		model.addAttribute("abstractReportModel", abstractReportModel);
		

		model.addAttribute("context", request.getContextPath());

		OrgUserMst messages  = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		
		model.addAttribute("gotMonthName", session.getAttribute("PAY_BILL_MONTH"));
		 addMenuAndSubMenu(model,messages);	
		SimpleDateFormat sdf =new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		lstabsBillObj.clear();
		Date createddate = null;
		model.addAttribute("listA",paybillGenerationTrnService.getAbstractReport(paybillGenerationTrnId));
		Date createdate = consolidatedReportService.findbillCreateConsolidateDate(Long.valueOf(paybillGenerationTrnId));
		model.addAttribute("createddate", sdf.format(createdate));
		model.addAttribute("systemdate", sdf.format(new Date()));
		  model.addAttribute("lstabsBillObj",lstabsBillObj);
		  
		  
		  model.addAttribute("hideLoader",true);
		  
		  
		  return "/views/reports/abstractreport";
		
	}
	

	@GetMapping("/viewabstractReportExecl/{paybillGenerationTrnId}")
	public String viewabstractReportExecl(
			@ModelAttribute("abstractReportModel") AbstractReportModel abstractReportModel,
			@PathVariable String paybillGenerationTrnId, Model model, Locale locale, HttpSession session,HttpServletResponse response)
			throws FileNotFoundException, DocumentException {
		
		String message = (String)model.asMap().get("message");
		model.addAttribute("abstractReportModel", abstractReportModel);

		OrgUserMst messages  = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		
		model.addAttribute("gotMonthName", session.getAttribute("PAY_BILL_MONTH"));
		 addMenuAndSubMenu(model,messages);	
		SimpleDateFormat sdf =new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		lstabsBillObj.clear();
		
				XSSFWorkbook workbook = new XSSFWorkbook();
				XSSFSheet sheet = workbook.createSheet("Data");
		List<AbstractReportModel> listA = paybillGenerationTrnService.getAbstractReport(paybillGenerationTrnId);
		if(listA!=null && listA.size()>0){
			 Row headerRow = sheet.createRow(0);
			 Cell headerCell = headerRow.createCell(0);
			 headerCell.setCellValue("Sr.No.");
			 headerCell = headerRow.createCell(1);
			 headerCell.setCellValue("Institude Name");
			 headerCell = headerRow.createCell(2);
			 headerCell.setCellValue("Bill Account No");
			 headerCell = headerRow.createCell(3);
			 headerCell.setCellValue("Bill Name");
			 headerCell = headerRow.createCell(4);
			 headerCell.setCellValue("Total Salary");
			 headerCell = headerRow.createCell(5);
			 headerCell.setCellValue("FA");
			 headerCell = headerRow.createCell(6);
			 headerCell.setCellValue("Exc.Pay Recovery");
			 headerCell = headerRow.createCell(7);
			 headerCell.setCellValue("Gross Salary");
			 headerCell = headerRow.createCell(8);
			 headerCell.setCellValue("GPF");
			 headerCell = headerRow.createCell(9);
			 headerCell.setCellValue("Revenue Stamp");
			 headerCell = headerRow.createCell(10);
			 headerCell.setCellValue("DCPS (Delayed)");
			 headerCell = headerRow.createCell(11);
			 headerCell.setCellValue("DCPS (Regular)");
			 headerCell = headerRow.createCell(12);
			 headerCell.setCellValue("IT");
			 headerCell = headerRow.createCell(13);
			 headerCell.setCellValue("DCPS (DA)");
			 headerCell = headerRow.createCell(14);
			 headerCell.setCellValue("PT");
			 headerCell = headerRow.createCell(15);
			 headerCell.setCellValue("Comp. Adv");
			 headerCell = headerRow.createCell(16);
			 headerCell.setCellValue("Other Deduction");
			 headerCell = headerRow.createCell(17);
			 headerCell.setCellValue("PLI");
			 headerCell = headerRow.createCell(18);
			 headerCell.setCellValue("Total Deduction");
			 headerCell = headerRow.createCell(19);
			 headerCell.setCellValue("GIS");
			 headerCell = headerRow.createCell(20);
			 headerCell.setCellValue("Net Payable");
			 headerCell = headerRow.createCell(21);
			 headerCell.setCellValue("NGR(RD)");
			 headerCell = headerRow.createCell(22);
			 headerCell.setCellValue("NGR(LIC)");
			 headerCell = headerRow.createCell(23);
			 headerCell.setCellValue("NGR(MISC)");
			 headerCell = headerRow.createCell(24);
			 headerCell.setCellValue("NGR (GSLIS)");
			 headerCell = headerRow.createCell(25);
			 headerCell.setCellValue("NGR (Other State GPF)");
			 headerCell = headerRow.createCell(26);
			 headerCell.setCellValue("NGR (Other State GIS)");
			 headerCell = headerRow.createCell(27);
			 headerCell.setCellValue("NGR (Quarter Rent)");
			 headerCell = headerRow.createCell(28);
			 headerCell.setCellValue("NGR (Bank Loan)");
			 headerCell = headerRow.createCell(29);
			 headerCell.setCellValue("NGR (Society Loan)");
			 headerCell = headerRow.createCell(30);
			 headerCell.setCellValue("NGR (Total Deductions))");
			 headerCell = headerRow.createCell(31);
			 headerCell.setCellValue("Salary Payable");
			 int i=1;
			 int  srNo=1;
			 
			for (Iterator iterator = listA.iterator(); iterator.hasNext();) {
				int rowNo = 0;
				Object obj[] = (Object[]) iterator.next();
				Row row = sheet.createRow(i++);
				 
				Cell cell = row.createCell(rowNo++);
				cell.setCellValue(srNo++);

				 for (int j = 0; j < obj.length; j++) {//Read Column from index 1
					 if(j==0){
						 cell = row.createCell(rowNo++);//For Institude Name
						 if(obj[j]!=null)
						 cell.setCellValue(obj[j].toString());
					 }
					 if(j==1){
						 cell = row.createCell(rowNo++);//For Bill Account No.
						 if(obj[j]!=null)
						 cell.setCellValue(obj[j].toString());
					 }
					 if(j==2){
						 cell = row.createCell(rowNo++);//For Bill Name.
						 if(obj[j]!=null)
						 cell.setCellValue(obj[j].toString());
					 }
					 if(j==3){
						 cell = row.createCell(rowNo++);//For Total Salary
						 if(obj[j]!=null)
						 cell.setCellValue(obj[j].toString());
					 }
					 if(j==4){
						 cell = row.createCell(rowNo++);//For FA
						 if(obj[j]!=null) {
							 cell.setCellValue(obj[j].toString());
						 }else {
							 cell.setCellValue(0); 
						 }
					 }
					 if(j==5){
						 cell = row.createCell(rowNo++);//For Exc.Pay Recovery
						 if(obj[j]!=null) {
							 cell.setCellValue(obj[j].toString());
						 }else {
							 cell.setCellValue(0); 
						 }
					 }
					 if(j==6){
						 cell = row.createCell(rowNo++);//For Gross Salary
						 if(obj[j]!=null) {
							 cell.setCellValue(obj[j].toString());
						 }else {
							 cell.setCellValue(0); 
						 }
					 }
					 if(j==7){
						 cell = row.createCell(rowNo++);//For GPF
						 if(obj[j]!=null) {
							 cell.setCellValue(obj[j].toString());
						 }else {
							 cell.setCellValue(0); 
						 }
					 }
					
					 if(j==8){
						 cell = row.createCell(rowNo++);//For Revenue Stamp
						 if(obj[j]!=null) {
							 cell.setCellValue(obj[j].toString());
						 }else {
							 cell.setCellValue(0); 
						 };
					 }
					 if(j==9){
						 cell = row.createCell(rowNo++);//For DCPS (Delayed)
						 if(obj[j]!=null) {
							 cell.setCellValue(obj[j].toString());
						 }else {
							 cell.setCellValue(0); 
						 }
					 }
					 if(j==10){
						 cell = row.createCell(rowNo++);//For DCPS (Regular)
						 if(obj[j]!=null) {
							 cell.setCellValue(obj[j].toString());
						 }else {
							 cell.setCellValue(0); 
						 }
					 }
					 if(j==11){
						 cell = row.createCell(rowNo++);//For IT
						 if(obj[j]!=null) {
							 cell.setCellValue(obj[j].toString());
						 }else {
							 cell.setCellValue(0); 
						 }
					 }
					 if(j==12){
						 cell = row.createCell(rowNo++);//For DCPS (DA)
						 if(obj[j]!=null) {
							 cell.setCellValue(obj[j].toString());
						 }else {
							 cell.setCellValue(0); 
						 }
					 }
					 if(j==13){
						 cell = row.createCell(rowNo++);//For PT
						 if(obj[j]!=null) {
							 cell.setCellValue(obj[j].toString());
						 }else {
							 cell.setCellValue(0); 
						 }
					 }
					 if(j==14){
						 cell = row.createCell(rowNo++);//For Comp. Adv
						 if(obj[j]!=null) {
							 cell.setCellValue(obj[j].toString());
						 }else {
							 cell.setCellValue(0); 
						 }
					 }
					 if(j==15){
						 cell = row.createCell(rowNo++);//For Other Deduction
						 if(obj[j]!=null) {
							 cell.setCellValue(obj[j].toString());
						 }else {
							 cell.setCellValue(0); 
						 }
					 }
					 if(j==16){
						 cell = row.createCell(rowNo++);//For PLI
						 if(obj[j]!=null) {
							 cell.setCellValue(obj[j].toString());
						 }else {
							 cell.setCellValue(0); 
						 }
					 }
					 if(j==17){
						 cell = row.createCell(rowNo++);//For GIS
						 if(obj[j]!=null) {
							 cell.setCellValue(obj[j].toString());
						 }else {
							 cell.setCellValue(0); 
						 }
					 }
					 if(j==18){
						 cell = row.createCell(rowNo++);//For Total Deduction
						 if(obj[j]!=null) {
							 cell.setCellValue(obj[j].toString());
						 }else {
							 cell.setCellValue(0); 
						 }
					 }
					 if(j==19){
						 cell = row.createCell(rowNo++);//For Net Payable
						 if(obj[j]!=null) {
							 cell.setCellValue(obj[j].toString());
						 }else {
							 cell.setCellValue(0); 
						 }
					 }
					 if(j==20){
						 cell = row.createCell(rowNo++);//For NGR(RD)
						 if(obj[j]!=null) {
							 cell.setCellValue(obj[j].toString());
						 }else {
							 cell.setCellValue(0); 
						 }
					 }
					 if(j==21){
						 cell = row.createCell(rowNo++);//For NGR(LIC)
						 if(obj[j]!=null) {
							 cell.setCellValue(obj[j].toString());
						 }else {
							 cell.setCellValue(0); 
						 }
					 }
					
					 if(j==22){
						 cell = row.createCell(rowNo++);//For NGR(MISC)
						 if(obj[j]!=null) {
							 cell.setCellValue(obj[j].toString());
						 }else {
							 cell.setCellValue(0); 
						 }
					 }
					 if(j==23){
						 cell = row.createCell(rowNo++);//For NGR (GSLIS)
						 if(obj[j]!=null) {
							 cell.setCellValue(obj[j].toString());
						 }else {
							 cell.setCellValue(0); 
						 }
					 }
					 if(j==24){
						 cell = row.createCell(rowNo++);//For NGR (Other State GPF)
						 if(obj[j]!=null) {
							 cell.setCellValue(obj[j].toString());
						 }else {
							 cell.setCellValue(0); 
						 }
					 }
					 if(j==25){
						 cell = row.createCell(rowNo++);//For NGR (Other State GIS)
						 if(obj[j]!=null) {
							 cell.setCellValue(obj[j].toString());
						 }else {
							 cell.setCellValue(0); 
						 }
					 }
					 if(j==26){
						 cell = row.createCell(rowNo++);//For NGR (Quarter Rent)
						 if(obj[j]!=null) {
							 cell.setCellValue(obj[j].toString());
						 }else {
							 cell.setCellValue(0); 
						 }
					 }
					 if(j==27){
						 cell = row.createCell(rowNo++);//For NGR (Bank Loan)
						 if(obj[j]!=null) {
							 cell.setCellValue(obj[j].toString());
						 }else {
							 cell.setCellValue(0); 
						 }
					 }
					 if(j==28){
						 cell = row.createCell(rowNo++);//For NGR (Society Loan)
						 if(obj[j]!=null) {
							 cell.setCellValue(obj[j].toString());
						 }else {
							 cell.setCellValue(0); 
						 }
					 }
					 if(j==29){
						 cell = row.createCell(rowNo++);//For NGR (Total Deductions)
						 if(obj[j]!=null) {
							 cell.setCellValue(obj[j].toString());
						 }else {
							 cell.setCellValue(0); 
						 }
					 }
					 if(j==30){
						 cell = row.createCell(rowNo++);//For Salary Payable
						 if(obj[j]!=null) {
							 cell.setCellValue(obj[j].toString());
						 }else {
							 cell.setCellValue(0); 
						 }
					 }
					
				}
			}
			try{
				String osName = System.getProperty( "os.name" );
				File F =null;// new File("D:\\EmployeeStatistics.xlsx");
				String filePath = "";
				if(osName.toLowerCase().contains("windows")) {
//					filePath = PropertyFetcher.getPropertyValue(FileName.APPLICATION_CONFIG,"EXCEL_REPORT");
					 F = new File("D:\\AbstractReport.xlsx");
					filePath = F.getAbsolutePath();
				}else {
//					filePath = PropertyFetcher.getPropertyValue(FileName.APPLICATION_CONFIG,"OTHER_EXCEL_REPORT");
					 F = new File("/home/fileUpload/AbstractReport.xlsx");
					filePath = F.getPath();
				}
		    	if (!new File(filePath).exists()) {
		    		new File(filePath).mkdirs();
		    	}
				FileOutputStream out = new FileOutputStream(new File(F,""));
			    workbook.write(out);
			    out.close();
				try {
					String fileName ="AbstractReport.xlsx";
					// set content type 
					response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
					// add response header
					response.addHeader("Content-Disposition", "attachment; filename=AbstractReport.xlsx");
					Path file = Paths.get(filePath, "");
					//copies all bytes from a file to an output stream
					Files.copy(file, response.getOutputStream());
					//flushes output stream
					response.getOutputStream().flush();
					
				} catch (Exception e) {
//					logger.info("Error :- " + e.getMessage());
				}
			}catch (Exception e){
			    e.printStackTrace();
			}
		}
		Date createdate = consolidatedReportService.findbillCreateConsolidateDate(Long.valueOf((paybillGenerationTrnId)));
		model.addAttribute("createddate", sdf.format(createdate));
		model.addAttribute("systemdate", sdf.format(new Date()));
		  model.addAttribute("lstabsBillObj",lstabsBillObj);
		  return "/views/report/abstractreport";
		
	}


	

}
