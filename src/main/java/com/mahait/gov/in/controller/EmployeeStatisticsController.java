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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itextpdf.text.DocumentException;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.EmployeeStatisticsModel;
import com.mahait.gov.in.service.CommonHomeMethodsService;
import com.mahait.gov.in.service.EmployeeStatisticsService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = { "/ddoast", "/mdc" })
public class EmployeeStatisticsController extends BaseController {

	// protected final Log logger = LogFactory.getLog(getClass());
	List<EmployeeStatisticsModel> lstempstcBillObj = new ArrayList<>();

	@Autowired
	CommonHomeMethodsService commonHomeMethodsService;

	@Autowired
	EmployeeStatisticsService employeeStatisticsService;

	//
	// @Autowired
	// PdfGenaratorUtil pdfGenaratorUtil;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	@GetMapping("/employeeStatisticsReport")
	public String employeeStatisticsReport(
			@ModelAttribute("employeeStatisticsModel") EmployeeStatisticsModel employeeStatisticsModel, Model model,
			Locale locale, HttpSession session) throws FileNotFoundException, DocumentException {

		String message = (String) model.asMap().get("message");
		model.addAttribute("employeeStatisticsModel", employeeStatisticsModel);

		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");

		int roleId = messages.getMstRoleEntity().getRoleId();
		model.addAttribute("gotMonthName", session.getAttribute("PAY_BILL_MONTH"));

		model.addAttribute("language", locale.getLanguage());

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

		model.addAttribute("createddate", sdf.format(new Date()));
		model.addAttribute("systemdate", sdf.format(new Date()));
		model.addAttribute("empList", employeeStatisticsService.findEmployeeStatistics(messages.getDdoCode(), roleId));
		addMenuAndSubMenu(model, messages);
		return "/views/reports/employeeStatistics";

	}

	@GetMapping("/employeeStatisticsExcelReport")
	public Map<String, Object> employeeStatisticsExcelReport(
			@ModelAttribute("employeeStatisticsModel") EmployeeStatisticsModel employeeStatisticsModel, Model model,
			Locale locale, HttpSession session, HttpServletResponse response)
			throws FileNotFoundException, DocumentException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			String message = (String) model.asMap().get("message");
			model.addAttribute("employeeStatisticsModel", employeeStatisticsModel);

			OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");

			model.addAttribute("gotMonthName", session.getAttribute("PAY_BILL_MONTH"));

			model.addAttribute("language", locale.getLanguage());

			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("Data");
			int roleId = messages.getMstRoleEntity().getRoleId();
			List<EmployeeStatisticsModel> lstResult = employeeStatisticsService
					.findEmployeeStatistics(messages.getDdoCode(), roleId);

			if (lstResult != null && lstResult.size() > 0) {
				Row headerRow = sheet.createRow(0);
				Cell headerCell = headerRow.createCell(0);
				headerCell.setCellValue("Employee Name");
				headerCell = headerRow.createCell(1);
				headerCell.setCellValue("Sevarth Id");
				headerCell = headerRow.createCell(2);
				headerCell.setCellValue("Date of Birth");
				headerCell = headerRow.createCell(3);
				headerCell.setCellValue("Employee Type");
				headerCell = headerRow.createCell(4);
				headerCell.setCellValue("Cadrex");
				headerCell = headerRow.createCell(5);
				headerCell.setCellValue("Post Name");
				headerCell = headerRow.createCell(6);
				headerCell.setCellValue("Post Type");
				headerCell = headerRow.createCell(7);
				headerCell.setCellValue("Post Start Date");
				headerCell = headerRow.createCell(8);
				headerCell.setCellValue("Post end Date");
				headerCell = headerRow.createCell(9);
				headerCell.setCellValue("Date of Joining");
				headerCell = headerRow.createCell(10);
				headerCell.setCellValue("Date of Service Expiry");
				headerCell = headerRow.createCell(11);
				headerCell.setCellValue("Scale Description");
				headerCell = headerRow.createCell(12);
				headerCell.setCellValue("Basic Salary");
				headerCell = headerRow.createCell(13);
				headerCell.setCellValue("Date of Service Expiry");
				headerCell = headerRow.createCell(14);
				headerCell.setCellValue("Scale Description");
				headerCell = headerRow.createCell(15);
				headerCell.setCellValue("Basic Salary");
				headerCell = headerRow.createCell(16);
				headerCell.setCellValue("Seven PC Basic");
				headerCell = headerRow.createCell(17);
				headerCell.setCellValue("7PC Level");
				headerCell = headerRow.createCell(18);
				headerCell.setCellValue("PF Series");
				headerCell = headerRow.createCell(19);
				headerCell.setCellValue("GPF/DCPS Account No");
				headerCell = headerRow.createCell(20);
				headerCell.setCellValue("GIS Group");
				headerCell = headerRow.createCell(21);
				headerCell.setCellValue("GIS Value");
				headerCell = headerRow.createCell(22);
				headerCell.setCellValue("Physically Handicapped");
				headerCell = headerRow.createCell(23);
				headerCell.setCellValue("Bank name");
				headerCell = headerRow.createCell(24);
				headerCell.setCellValue("Bank Branch Name");
				headerCell = headerRow.createCell(25);
				headerCell.setCellValue("Account No");
				headerCell = headerRow.createCell(26);
				headerCell.setCellValue("Pran No");
				/// headerCell = headerRow.createCell(26);
				int i = 1;
				int srNo = 1;

				for (Iterator iterator = lstResult.iterator(); iterator.hasNext();) {
					int rowNo = 0;
					Object obj[] = (Object[]) iterator.next();
					Row row = sheet.createRow(i++);

					Cell cell;

					for (int j = 0; j < obj.length; j++) {// Read Column from index 1
						if (j == 0) {
							cell = row.createCell(rowNo++);// For Employee Name
							if (obj[j] != null)
								cell.setCellValue(obj[j].toString());
						}
						if (j == 1) {
							cell = row.createCell(rowNo++);// For sevaarthId
							if (obj[j] != null)
								cell.setCellValue(obj[j].toString());
						}
						if (j == 2) {
							cell = row.createCell(rowNo++);// For Date of Birth
							if (obj[j] != null)
								cell.setCellValue(obj[j].toString());
						}
						if (j == 3) {
							cell = row.createCell(rowNo++);// For Cadre
							if (obj[j] != null)
								cell.setCellValue(obj[j].toString());
						}
						if (j == 4) {
							cell = row.createCell(rowNo++);// For Post Name
							if (obj[j] != null)
								cell.setCellValue(obj[j].toString());
						}
						if (j == 5) {
							cell = row.createCell(rowNo++);// For Post Type
							if (obj[j] != null)
								cell.setCellValue(obj[j].toString());
						}
						if (j == 6) {

							cell = row.createCell(rowNo++);// For Start Date
							if (obj[j] != null)
								cell.setCellValue(obj[j].toString());
							/*
							 * cell = row.createCell(rowNo++);//For Employee Type if(obj[j]!=null)
							 * if(obj[j].toString()=="P") { cell.setCellValue("Permanent"); }else
							 * if(obj[j].toString()=="B") { cell.setCellValue("Both"); }else {
							 * cell.setCellValue("Temporary"); }
							 * 
							 */}

						if (j == 7) {
							cell = row.createCell(rowNo++);// For End Date
							if (obj[j] != null)
								cell.setCellValue(obj[j].toString());
						}

						if (j == 8) {
							cell = row.createCell(rowNo++);// For Date of joining
							if (obj[j] != null)
								if (obj[j] != null)
									cell.setCellValue(obj[j].toString());
						}
						if (j == 9) {
							cell = row.createCell(rowNo++);// For Service End Date
							if (obj[j] != null)
								if (obj[j] != null)
									cell.setCellValue(obj[j].toString());
						}
						if (j == 10) {
							cell = row.createCell(rowNo++);// For Scale Desc
							if (obj[j] != null)
								cell.setCellValue(obj[j].toString());
						}
						if (j == 11) {
							cell = row.createCell(rowNo++);// For Basic Pay
							if (obj[j] != null)
								cell.setCellValue(obj[j].toString());
						}
						if (j == 12) {
							cell = row.createCell(rowNo++);// For 7Pc Basic
							if (obj[j] != null)
								cell.setCellValue(obj[j].toString());
						}
						if (j == 13) {
							cell = row.createCell(rowNo++);// For 7PC Level
							if (obj[j] != null)
								cell.setCellValue(obj[j].toString());
						}
						/// d.bank_branch_name,a.bank_acnt_no,a.pran_no
						if (j == 14) {
							cell = row.createCell(rowNo++);// For PF Series
							if (obj[j] != null)
								cell.setCellValue(obj[j].toString());
						}
						if (j == 15) {
							cell = row.createCell(rowNo++);// For GPF/DCPS Account No
							if (obj[j] != null)
								cell.setCellValue(obj[j].toString());
						}
						if (j == 16) {
							cell = row.createCell(rowNo++);// For GIS Group
							if (obj[j] != null)
								cell.setCellValue((obj[j].toString()));
						}
						if (j == 17) {
							cell = row.createCell(rowNo++);// For GIS Value
							if (obj[j] != null)
								cell.setCellValue(obj[j].toString());
						}
						if (j == 18) {
							cell = row.createCell(rowNo++);// For Physically Handicapped
							if (obj[j] != null)
								cell.setCellValue(obj[j].toString());
						}
						if (j == 19) {
							cell = row.createCell(rowNo++);// For Bank Name
							if (obj[j] != null)
								cell.setCellValue(obj[j].toString());
						}
						if (j == 20) {
							cell = row.createCell(rowNo++);// For Branch Name
							if (obj[j] != null)
								cell.setCellValue(obj[j].toString());
						}
						if (j == 21) {
							cell = row.createCell(rowNo++);// For Account no
							if (obj[j] != null) {
								cell.setCellValue(obj[j].toString());
							}
						}
						if (j == 22) {
							cell = row.createCell(rowNo++);// For Pran No
							if (obj[j] != null)
								cell.setCellValue(obj[j].toString());
						}

					}
				}
			}
			try {
				String osName = System.getProperty("os.name");
				File F = null;// new File("D:\\EmployeeStatistics.xlsx");
				String filePath = "";
				if (osName.toLowerCase().contains("windows")) {
					// filePath =
					// PropertyFetcher.getPropertyValue(FileName.APPLICATION_CONFIG,"EXCEL_REPORT");
					F = new File("D:\\EmployeeStatistics.xlsx");
					filePath = F.getAbsolutePath();
				} else {
					// filePath =
					// PropertyFetcher.getPropertyValue(FileName.APPLICATION_CONFIG,"OTHER_EXCEL_REPORT");
					F = new File("/home/fileUpload/EmployeeStatistics.xlsx");
					filePath = F.getPath();
				}
				if (!new File(filePath).exists()) {
					new File(filePath).mkdirs();
				}
				FileOutputStream out = new FileOutputStream(new File(F, ""));
				workbook.write(out);
				out.close();
				try {
					String fileName = "EmployeeStatistics.xlsx";
					// set content type
					response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
					// add response header
					response.addHeader("Content-Disposition", "attachment; filename=EmployeeStatistics.xlsx");
					Path file = Paths.get(filePath, "");
					// copies all bytes from a file to an output stream
					Files.copy(file, response.getOutputStream());
					// flushes output stream
					response.getOutputStream().flush();

				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

}
