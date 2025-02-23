package com.mahait.gov.in.controller;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.TemplateEngine;

import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.PayslipModel;
import com.mahait.gov.in.model.PayslipReportModel;
import com.mahait.gov.in.repository.PayslipReportRepo;
import com.mahait.gov.in.service.CommonHomeMethodsService;
import com.mahait.gov.in.service.DDOCityCategoryService;
import com.mahait.gov.in.service.DisplayInnerReportService;
import com.mahait.gov.in.service.EmpPayslipService;
import com.mahait.gov.in.service.PayslipReportService;
import com.mahait.gov.in.service.RegularReportService;

import jakarta.servlet.http.HttpSession;



@Controller
@RequestMapping("/employee")
public class EmpPayslipController  extends BaseController {

	List<PayslipReportModel> listOfPayslipEmp = new ArrayList<>();

	@Autowired
	CommonHomeMethodsService commonHomeMethodsService;
	
	
	@Autowired
	DisplayInnerReportService displayInnerReportService;
	
	
	@Autowired
	EmpPayslipService empPayslipService;
	

	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	PayslipReportRepo payslipReportRepo;

	@Autowired
	PayslipReportService payslipReportService;

	@Autowired
	RegularReportService regularReportService;

	@Autowired
	DDOCityCategoryService dDOCityCategoryService;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	@GetMapping("/searchEmpPayslip")
	public String searchEmpPayslip(@ModelAttribute("PayslipReportModel") PayslipReportModel payslipReportModel,
			Model model, Locale locale, HttpSession session) {

		String message = (String) model.asMap().get("message");
		model.addAttribute("paybillHeadMpgModel", payslipReportModel);
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(model, messages);

		/// int isCityCategory =
		/// dDOCityCategoryService.getCityCategoryMappedWithDDo(messages.getUserName());

		model.addAttribute("ddoCode", messages.getUserName());
		/// model.addAttribute("isCityCategory", isCityCategory);
		model.addAttribute("lstGetAllMonths", commonHomeMethodsService.lstGetAllMonths());
		model.addAttribute("lstGetAllYear", commonHomeMethodsService.lstGetAllYears());

		model.addAttribute("lstBillDesc", empPayslipService.lstBillDesc(messages));
		return "/views/employeecorner/search-emp-payslip";
	}

	@PostMapping("/viewEmployeePayslip")
	public String viewEmployeePayslip(@ModelAttribute("PayslipReportModel") PayslipReportModel payslipReportModel,
			Model model, Locale locale, HttpSession session) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		List<PayslipReportModel> allowEdpList = new ArrayList<PayslipReportModel>();// edpDao.getAllowCompoMpgData(locId);
		List<PayslipReportModel> deducAgEdpList = new ArrayList<PayslipReportModel>();// edpDao.getAGDeducCompoMpgData(locId);
		List<PayslipReportModel> nongovdeducEdpList = new ArrayList<PayslipReportModel>();// edpDao.getnonGovDeducCompoMpgData(locId);

		List<PayslipReportModel> lstAllowPayslipModel = new ArrayList<PayslipReportModel>();// edpDao.getAllowCompoMpgData(locId);
		List<PayslipReportModel> lstDeducPayslipModel = new ArrayList<PayslipReportModel>();// edpDao.getAGDeducCompoMpgData(locId);

		ArrayList row = new ArrayList();
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(model, messages);

		//// int isCityCategory =
		//// dDOCityCategoryService.getCityCategoryMappedWithDDo(messages.getUserName());

		String savaarthid = "";
		Long payCommCode = null;
		Long payCommissionCode = null;
		String sevenPCLvl = null;

		allowEdpList.clear();
		deducAgEdpList.clear();
		nongovdeducEdpList.clear();
		List<PayslipReportModel> allEdpList = null;
		List<Map<String, Object>> lstempdetails1 = null;
		ArrayList allowance = null;
		ArrayList lstdedectionag = null;
		ArrayList lstnonGovdedection = null;
		ArrayList dedtotal = null;
		ArrayList algrosstotal = null;
		ArrayList alnetamt = null;
		
		List<Object[]> lstEmp = empPayslipService.getEmployeeData1(payslipReportModel.getSchemeBillgroupId(),
				payslipReportModel.getPaybillMonth(), payslipReportModel.getPaybillYear(), messages);

		Long hraPercent = null;
		Double daPercent = null;
		allowEdpList.clear();
		deducAgEdpList.clear();
		nongovdeducEdpList.clear();
		List<PayslipModel> ltsPayslipModel = new ArrayList<>();

		if (lstEmp.size() > 0) {
			ltsPayslipModel.clear();
			for (Object object[] : lstEmp) {
				PayslipModel payslipModel = new PayslipModel();
				payslipModel.setSevaarthId(StringHelperUtils.isNullString(object[0]));
				payslipModel.setEmpName(StringHelperUtils.isNullString(object[1]));
				payslipModel.setDesgnName(StringHelperUtils.isNullString(object[2]));

				if (object[3] != null)
					payslipModel.setDoj(StringHelperUtils.isNullDate(object[3]));

				if (object[4] != null)
					payslipModel.setDob(StringHelperUtils.isNullDate(object[4]));

				payslipModel.setMobileNo1(StringHelperUtils.isNullLong(object[5]));

				payslipModel.setPanNo(StringHelperUtils.isNullString(object[6]));
				payslipModel.setPayCommisionCode(StringHelperUtils.isNullLong(object[7]));
				payCommissionCode = payslipModel.getPayCommisionCode();

				payslipModel.setUidNo(StringHelperUtils.isNullString(object[8]));
				payslipModel.setIfsc(StringHelperUtils.isNullString(object[9]));

				payslipModel.setBankAccNo(StringHelperUtils.isNullBigInteger(object[10]));
				String finalAccNo = "";

				if (object[10] != null) {

					String lastfourdigit = payslipModel.getBankAccNo().toString()
							.substring((payslipModel.getBankAccNo().toString().length()) - 4);
					String maskAccNo = "";
					for (int i = 0; i < payslipModel.getBankAccNo().toString().length() - 4; i++) {
						maskAccNo = maskAccNo + "x";
					}

					finalAccNo = maskAccNo + lastfourdigit;
					payslipModel.setAccountNo(finalAccNo);
				}
				payslipModel.setDdoCode(StringHelperUtils.isNullString(object[11]));

				
				payslipModel.setGrossTotalAmt(StringHelperUtils.isNullDouble(object[12]));
				payslipModel.setTotalDeduction(StringHelperUtils.isNullDouble(object[13]));
				payslipModel.setTotalNetAmt(StringHelperUtils.isNullDouble(object[14]));

				if (object[14] != null)
					payslipModel.setNetAmtInWord(convertToIndianCurrency(object[14].toString()));

				payslipModel.setGpfNdcpsNo(StringHelperUtils.isNullString(object[15]));

				payslipModel.setBillGroupName(StringHelperUtils.isNullString(object[16]));
				payslipModel.setDor(StringHelperUtils.isNullDate(object[17]));
				/*payslipModel.setHraPercent(StringHelperUtils.isNullInt(object[18]));*/
				/// hraPercent = (Integer) object[20];
			///	payslipModel.setDaPercent(StringHelperUtils.isNullInt(object[18]));
				//// daPercent = StringHelperUtils.isNullInt(object[21]);
				if (object[18] != null) {
					payslipModel.setSevenPcLvl(StringHelperUtils.isNullLong(object[18]));
					payslipModel.setLvl("S_" + object[18]);
				}

				if (payCommissionCode.equals(700005)) {
					payslipModel.setPayComm("7th Pay Commission");
					model.addAttribute("payComm", "7th Pay Commission");
				} else if (payCommissionCode.equals(700016)) {
					payslipModel.setPayComm("6th Pay Commission");
				}
				/*
				 * BigDecimal basic = (BigDecimal) object[21]; Double basicPay =
				 * basic.doubleValue();
				 */
				payslipModel.setBasic(StringHelperUtils.isNullDouble(object[19]));
				payslipModel.setVoucherNo(StringHelperUtils.isNullString(object[20]));
				payslipModel.setVoucherDate(StringHelperUtils.isNullDate(object[21]));
				payslipModel.setBillNo(StringHelperUtils.isNullLong(object[22]));
				ltsPayslipModel.add(payslipModel);
			}
		}
		model.addAttribute("ltsPayslipModel", ltsPayslipModel);
		model.addAttribute("ddoCode", messages.getDdoCode());

		for (Object[] objects : lstEmp) {
			savaarthid = objects[0].toString();
			payCommCode =StringHelperUtils.isNullLong(objects[7]);
			daPercent = StringHelperUtils.isNullDouble(objects[19]);
			hraPercent = StringHelperUtils.isNullLong(objects[18]);

			allEdpList = empPayslipService.getAllDataForinnernew(savaarthid);
			lstempdetails1 = empPayslipService.getempDetails(payslipReportModel.getSchemeBillgroupId(),
					payslipReportModel.getPaybillYear(), payslipReportModel.getPaybillMonth(), savaarthid);

			// lstEmp[11]=allEdpList;
			allowEdpList.clear();
			deducAgEdpList.clear();
			nongovdeducEdpList.clear();
			
			PayslipReportModel obj = new PayslipReportModel();
			obj.setDeptalldetNm("Basic_Pay");
			obj.setType(1);
			obj.setDeptallowdeducid(1);
			obj.setTempvalue("0.00");
			obj.setTempempty("0.00");
			allEdpList.add(0, obj);
			
			
			

			// for allowances
			allowance = new ArrayList();
			row = new ArrayList();
			for (int i = 1; i <= allowEdpList.size(); i++) {
				row.add(String.valueOf(i));
			}
			Iterator iterator1 = lstempdetails1.iterator();
			Map<String, Double> mapAllowanceSum = new HashMap<>();
			Map<String, Object> map = (Map<String, Object>) iterator1.next();
			row = new ArrayList();

			int loopCount = 0;
			for (Iterator iterator = allEdpList.iterator(); iterator.hasNext();) {

				Double amount = 0d;
				PayslipReportModel object = (PayslipReportModel) iterator.next();
				String allname = object.getDeptalldetNm();
				if (object.getDeptalldetNm().toUpperCase().trim() != null) {
					if (map.get(object.getDeptalldetNm().toLowerCase().trim()) != null) {
						row.add(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());

						amount = Double.parseDouble(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
						mapAllowanceSum.put(object.getDeptalldetNm().toUpperCase().trim(), amount.doubleValue());
					}
				}

				PayslipReportModel pm = new PayslipReportModel();
				String componentName = object.getDeptalldetNm().toUpperCase().trim().toString();
				if (amount > 0) {
					pm.setSevaarthId(savaarthid);
					pm.setComponentname(object.getDeptalldetNm().replace("_", " "));
					pm.setComponentValue(
							Double.parseDouble(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString()));
					pm.setIsType(allEdpList.get(loopCount).getType());
					lstAllowPayslipModel.add(pm);
				} else {
					pm.setSevaarthId(savaarthid);
					pm.setComponentname(allname.replace("_", " "));
					pm.setComponentValue(0d);
					pm.setIsType(allEdpList.get(loopCount).getType());
					lstAllowPayslipModel.add(pm);
				}
				loopCount++;

			}
			allowance.add(mapAllowanceSum);

			// for deductions
			lstdedectionag = new ArrayList<>();
			row = new ArrayList();
			for (int i = 1; i <= deducAgEdpList.size(); i++) {
				row.add(String.valueOf(i));
			}
			Iterator iterator2 = lstempdetails1.iterator();
			Map<String, Double> maplstdedectionagSum = new HashMap<>();
			row = new ArrayList();
			for (Iterator iterator = deducAgEdpList.iterator(); iterator.hasNext();) {

				Double amount = 0d;
				PayslipReportModel object = (PayslipReportModel) iterator.next();
				String allname = object.getDeptalldetNm();
				if (object.getDeptalldetNm().toUpperCase().trim() != null) {
					if (map.get(object.getDeptalldetNm().toLowerCase().trim()) != null) {
						row.add(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
						amount = Double.parseDouble(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
						mapAllowanceSum.put(object.getDeptalldetNm().toUpperCase().trim(), amount.doubleValue());
					}
				}

			}
			lstdedectionag.add(maplstdedectionagSum);

			lstnonGovdedection = new ArrayList();

			// for non government deductions

			row = new ArrayList();
			for (int i = 1; i <= nongovdeducEdpList.size(); i++) {
				row.add(String.valueOf(i));
			}

			Iterator iterator3 = lstempdetails1.iterator();

			Map<String, Double> maplstnonGovdedectionSum = new HashMap<>();
			row = new ArrayList();
			for (Iterator iterator = nongovdeducEdpList.iterator(); iterator.hasNext();) {

				Double amount = 0d;
				PayslipReportModel object = (PayslipReportModel) iterator.next();
				String allname = object.getDeptalldetNm();
				if (object.getDeptalldetNm().toUpperCase().trim() != null) {
					if (map.get(object.getDeptalldetNm().toLowerCase().trim()) != null) {
						row.add(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
						amount = Double.parseDouble(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());

						mapAllowanceSum.put(object.getDeptalldetNm().toUpperCase().trim(), amount.doubleValue());
					}
				}

			}
			lstnonGovdedection.add(maplstnonGovdedectionSum);

			algrosstotal = new ArrayList<>();
			Iterator iteratorgrosstotal = lstempdetails1.iterator();
			Double algrosstotalSum = 0d;

			Map<String, Object> map2 = (Map<String, Object>) iteratorgrosstotal.next();
			algrosstotal.add(map2.get("gross_amt").toString());
			algrosstotalSum += Double.parseDouble(map2.get("gross_amt").toString());

			alnetamt = new ArrayList<>();
			Iterator iteratornetamt = lstempdetails1.iterator();
			Double alnetamtSum = 0d;

			Map<String, Object> map3 = (Map<String, Object>) iteratornetamt.next();
			alnetamt.add(map3.get("payslip_net").toString());
			alnetamtSum += Double.parseDouble(map3.get("payslip_net").toString());

			dedtotal = new ArrayList<>();
			Iterator iteratordedtotal = lstempdetails1.iterator();
			Double dedtotalSum = 0d;

			Map<String, Object> map4 = (Map<String, Object>) iteratordedtotal.next();
			dedtotal.add(map4.get("payslip_total_deduction").toString());
			dedtotalSum += Double.parseDouble(map4.get("payslip_total_deduction").toString());

			String SalaryInWords = PayslipReportController.convertToIndianCurrency(alnetamtSum.toString());

			System.out.println(SalaryInWords + " 888888888888866663258");

			model.addAttribute("lstEmp", lstEmp);
			model.addAttribute("algrosstotalSum", algrosstotal);
			model.addAttribute("dedtotalSum", dedtotal);
			model.addAttribute("alnetamtSum", alnetamt);
			model.addAttribute("payCommCode", payCommCode);
			model.addAttribute("lstdedectionag", lstdedectionag);
			model.addAttribute("lstnonGovdedection", lstnonGovdedection);
			model.addAttribute("allowance", allowance);
			model.addAttribute("SalaryInWords", SalaryInWords);

			String monname = "";
			String curryear = "";

			Integer monthId = payslipReportModel.getPaybillMonth();
			Integer yearId = payslipReportModel.getPaybillYear();

			monname = new DateFormatSymbols().getMonths()[monthId - 1];

			model.addAttribute("paySlipHead", "  " + monname + " " + (yearId + 1999));

			alnetamtSum = null;
			SalaryInWords = "";

			payslipReportModel.setLstPayslipModel(lstAllowPayslipModel);

			model.addAttribute("payslipReportModel", payslipReportModel);
		}

		return "/views/employeecorner/emp-payslip";

	}

	public static String convertToIndianCurrency(String num) {
		BigDecimal bd = new BigDecimal(num);
		long number = bd.longValue();
		long no = bd.longValue();
		int decimal = (int) (bd.remainder(BigDecimal.ONE).doubleValue() * 100);
		int digits_length = String.valueOf(no).length();
		int i = 0;
		ArrayList<String> str = new ArrayList<>();
		HashMap<Integer, String> words = new HashMap<>();
		words.put(0, "");
		words.put(1, "One");
		words.put(2, "Two");
		words.put(3, "Three");
		words.put(4, "Four");
		words.put(5, "Five");
		words.put(6, "Six");
		words.put(7, "Seven");
		words.put(8, "Eight");
		words.put(9, "Nine");
		words.put(10, "Ten");
		words.put(11, "Eleven");
		words.put(12, "Twelve");
		words.put(13, "Thirteen");
		words.put(14, "Fourteen");
		words.put(15, "Fifteen");
		words.put(16, "Sixteen");
		words.put(17, "Seventeen");
		words.put(18, "Eighteen");
		words.put(19, "Nineteen");
		words.put(20, "Twenty");
		words.put(30, "Thirty");
		words.put(40, "Forty");
		words.put(50, "Fifty");
		words.put(60, "Sixty");
		words.put(70, "Seventy");
		words.put(80, "Eighty");
		words.put(90, "Ninety");
		String digits[] = { "", "Hundred", "Thousand", "Lakh", "Crore" };
		while (i < digits_length) {
			int divider = (i == 2) ? 10 : 100;
			number = no % divider;
			no = no / divider;
			i += divider == 10 ? 1 : 2;
			if (number > 0) {
				int counter = str.size();
				String plural = (counter > 0 && number > 9) ? "s" : "";
				String tmp = (number < 21) ? words.get(Integer.valueOf((int) number)) + " " + digits[counter] + plural
						: words.get(Integer.valueOf((int) Math.floor(number / 10) * 10)) + " "
								+ words.get(Integer.valueOf((int) (number % 10))) + " " + digits[counter] + plural;
				str.add(tmp);
			} else {
				str.add("");
			}
		}

		Collections.reverse(str);
		String Rupees = String.join(" ", str).trim();

		String paise = (decimal) > 0
				? " And Paise " + words.get(Integer.valueOf(decimal - decimal % 10)) + " "
						+ words.get(Integer.valueOf(decimal % 10))
				: "";
		return "In Word :" + Rupees + paise + " Rupees";

	}

	@GetMapping(value = "/isPaybillGenerated/{billNumber}/{monthName}/{yearName}")
	public ResponseEntity<String> isPaybillGenerated(@PathVariable Long billNumber, @PathVariable int monthName,
			@PathVariable int yearName, HttpSession session) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		List<Object[]> paybillgen = empPayslipService.isPaybillGenerated(billNumber, monthName, yearName,messages);
		Integer existingData = paybillgen.size();
		String resJson = existingData.toString();
		return ResponseEntity.ok(resJson);
	}

}
