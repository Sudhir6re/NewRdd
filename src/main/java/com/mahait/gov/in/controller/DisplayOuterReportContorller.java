package com.mahait.gov.in.controller;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mahait.gov.in.common.CashWordConverter;
import com.mahait.gov.in.common.CommonConstants;
import com.mahait.gov.in.common.CommonConstants.STATUS;
import com.mahait.gov.in.common.CommonUtils;
import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.DisplayOuterReportModel;
//import com.mahait.gov.in.entity.HrPayEdpCompoMpg;
//import com.mahait.gov.in.entity.MstSchemeEntity;
import com.mahait.gov.in.model.PayBillViewApprDelBillModel;
import com.mahait.gov.in.model.RegularReportModel;
import com.mahait.gov.in.model.TopicModel;
import com.mahait.gov.in.service.CommonHomeMethodsService;
import com.mahait.gov.in.service.CreateAdminOfficeService;
import com.mahait.gov.in.service.DeptEligibilityForAllowAndDeductService;
import com.mahait.gov.in.service.DisplayOuterReportService;
import com.mahait.gov.in.service.MstEmployeeService;
import com.mahait.gov.in.service.PaybillGenerationTrnService;
import com.mahait.gov.in.service.RegularReportService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/ddoast")
public class DisplayOuterReportContorller extends BaseController
{
//	protected final Log logger = LogFactory.getLog(getClass());
	@Autowired
	CommonHomeMethodsService commonHomeMethodsService;
	@Autowired
	DisplayOuterReportService displayOuterReportService;
	@Autowired
	DeptEligibilityForAllowAndDeductService deptEligibilityForAllowAndDeductService;
	@Autowired
	CreateAdminOfficeService createAdminOfficeService;
	@Autowired
	MstEmployeeService mstEmployeeService;
	@Autowired
	PaybillGenerationTrnService paybillGenerationTrnService;
	
	@Autowired
	RegularReportService regularReportService;
	
	@GetMapping("/outerreportsearch")
	public String searchOuterReport(
			@ModelAttribute("payBillViewApprDelBillModel") PayBillViewApprDelBillModel payBillViewApprDelBillModel,
			Model model, Locale locale, HttpSession session,HttpServletRequest request) {

		String message = (String) model.asMap().get("message");
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		model.addAttribute("payBillViewApprDelBillModel", payBillViewApprDelBillModel);


		addMenuAndSubMenu(model,messages);

		if (message != null && message.equals("SUCCESS")) {
			if (locale != null && locale.getLanguage().equalsIgnoreCase("en")) {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_ENGLSH, STATUS.SUCCESS, model);
			} else {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_MARATHI, STATUS.SUCCESS, model);
			}
		}

		model.addAttribute("lstMonths", commonHomeMethodsService.lstGetAllMonths());
		model.addAttribute("lstYears", commonHomeMethodsService.lstGetAllYears());
		model.addAttribute("lstBillDesc", regularReportService.lstBillDesc(messages.getDdoCode()));
		
		model.addAttribute("context", request.getContextPath());
		
		return "/views/reports/all_report";
	}


	@GetMapping("/outerreportsearch/{month}/{year}")
	public String searchOuterReport(
			@ModelAttribute("payBillViewApprDelBillModel") PayBillViewApprDelBillModel payBillViewApprDelBillModel,
			@PathVariable int month,@PathVariable int year,Model model, Locale locale, HttpSession session,HttpServletRequest request) {
		String message = (String) model.asMap().get("message");
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		model.addAttribute("payBillViewApprDelBillModel", payBillViewApprDelBillModel);
		
		addMenuAndSubMenu(model,messages);
		if(month!=0 && year!=0)

		if (message != null && message.equals("SUCCESS")) {
			if (locale != null && locale.getLanguage().equalsIgnoreCase("en")) {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_ENGLSH, STATUS.SUCCESS, model);
			} else {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_MARATHI, STATUS.SUCCESS, model);
			}
		}

		model.addAttribute("lstMonths", commonHomeMethodsService.lstGetAllMonths());
		model.addAttribute("lstYears", commonHomeMethodsService.lstGetAllYears());
		model.addAttribute("language", locale.getLanguage());
		
		model.addAttribute("context", request.getContextPath());
		
		return "/views/report/all_report";
	}
	
	@GetMapping(value = "/outerreportbilldtls/{month}/{year}", consumes = { "application/json" }, produces = { "application/json" })
	public @ResponseBody List<DisplayOuterReportModel> billDetails(@PathVariable int month,@PathVariable int year, Model model, Locale locale, HttpSession session) {
		
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		
		List<DisplayOuterReportModel>  lstbilldtls=null;;
		if(month!=0 && year!=0)
	    lstbilldtls =displayOuterReportService.findBillDescription(messages.getUserName(),month,year);
//		lstbilldtls =displayOuterReportService.findAllSchemeBillGroupByDDOCode(messages.getUserName(),messages.getRole_id());
		//List<MstSchemeEntity> mstSchemeEntity = mstSchemeService.findAllSchemeDetails(data);
		return lstbilldtls;
	}
//[/ddoast/outerreport/52/06130900121_AST]
	@GetMapping("/outerreport/{billNumber}/{ddoCode}/{month}/{year}")
	public String getOuterReport(@PathVariable Long billNumber,@PathVariable int month,@PathVariable int year, Model model, Locale locale, HttpSession session,@PathVariable String ddoCode,HttpServletRequest request) {

		String message = (String) model.asMap().get("message");
		// model.addAttribute("payBillViewApprDelBillModel",
		// payBillViewApprDelBillModel);

		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");

		List<TopicModel> menuList = new ArrayList<>();
	 

		

		if(ddoCode.equals("1")) {
			ddoCode=messages.getUserName();
		}else {
			ddoCode=messages.getDdoCode();
		}
		Long billNo =  billNumber;
		String officeDetails = displayOuterReportService.getOffice(ddoCode);
		String billDetails = commonHomeMethodsService.findbillGrpname(billNumber);
		String ddocode = "";
		BigDecimal grossamount = null;
		BigDecimal netamount= null;
		Date billcreationdate = null;
		String monname = "";
		String curryear = "";
		String finyear = "";
		Map objectArgs = new HashMap();
		objectArgs.put("paybillNo", billNo);// paramerter
		objectArgs.put("month", month); // parameter
		objectArgs.put("year", year); // parameter
	List<RegularReportModel> lsouterrepheaddtls = displayOuterReportService.getReportHeaderDetails(String.valueOf(billNo));

		Double totalNetAmount = displayOuterReportService.gettotalNetAmount(String.valueOf(billNo));
		
		
		BigInteger currmonth = BigInteger.valueOf(month);
		BigInteger yearcurr = BigInteger.valueOf(year);
		
		
		List<Object[]>  monthinfo = paybillGenerationTrnService.findmonthinfo(currmonth);
		for (Object[] monthLst : monthinfo) {
			monname = monthLst[4].toString();
		}
			

		List<Object[]>  yearinfo = commonHomeMethodsService.findyearinfo(BigInteger.valueOf(year));
		for (Object[] yearLst : yearinfo) {
			curryear = yearLst[1].toString();
			finyear = yearLst[2].toString();
			
		}
		
		
		
		List<Object[]> cardecode = displayOuterReportService.getcardecode(ddoCode);
		String carde="";
		for (Object[] objects : cardecode) {
			if(carde != "") {
				carde+=","+objects[3].toString();;
			}else {
				carde=objects[3].toString();
			}
		}
	
		SimpleDateFormat sdf =new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date createdate = commonHomeMethodsService.findbillCreateDate(billNumber);
        model.addAttribute("createddate", sdf.format(createdate));
//		logger.info("officeDetails=" + officeDetails);
		model.addAttribute("paybillNo", billNo);
		model.addAttribute("month", monname);
		model.addAttribute("year", curryear);
		model.addAttribute("finyear", finyear);
		model.addAttribute("billDetails", billDetails);
		model.addAttribute("carde", carde);
		model.addAttribute("officeDetails", officeDetails);// f
		model.addAttribute("ddocode", messages.getUserName());
		model.addAttribute("lsouterrepheaddtls", lsouterrepheaddtls);
		model.addAttribute("totalNetAmount", totalNetAmount);
		model.addAttribute("totaldeduction", displayOuterReportService.getTotalDeduction((double)billNo));

		//  start
		List<DisplayOuterReportModel> allowEdpList = new ArrayList<DisplayOuterReportModel>();// edpDao.getAllowCompoMpgData(locId);
		List<DisplayOuterReportModel> deducAgEdpList = new ArrayList<DisplayOuterReportModel>();// edpDao.getAGDeducCompoMpgData(locId);
		List<DisplayOuterReportModel> deducTyEdpList = new ArrayList<DisplayOuterReportModel>();// edpDao.getTRDeducCompoMpgData(locId);
		List<DisplayOuterReportModel> deducOthEdpList = new ArrayList<DisplayOuterReportModel>();// changes for other (nps)
		
		//Dynamic process start
		List<DisplayOuterReportModel> allEdpList = displayOuterReportService.getAllDataForOuternew(ddoCode,billNumber);
//		List<DeptEligibilityForAllowAndDeductEntity> dptallDeduc = deptEligibilityForAllowAndDeductService.findDeptEligibilityForAllowAndDeductList();
		
		DisplayOuterReportModel orElse = allEdpList.stream().filter(displayInnerReportModel->"BLWF".equals(displayInnerReportModel.getDeptalldetNm())).findAny().orElse(null);
		if(orElse==null) {
			DisplayOuterReportModel obj1 = new DisplayOuterReportModel();
        	obj1.setDeptalldetNm(StringHelperUtils.isNullString("BLWF"));
        	obj1.setType(2);
        	obj1.setDeptallowdeducid(2);
        	obj1.setTempvalue("0");
        	obj1.setTempempty("0");
        	allEdpList.add(obj1);
		}
		
		
		
		DisplayOuterReportModel objnew = new DisplayOuterReportModel();
		objnew.setDeptalldetNm(StringHelperUtils.isNullString("Basic_Pay"));
		objnew.setType(1);
		objnew.setDeptallowdeducid(1);
		objnew.setTempvalue("0");
		objnew.setTempempty("0");
        allowEdpList.add(objnew);
		for (int i = 0; i < allEdpList.size(); i++) {
//			if (allEdpList.get(i).getType() != null) {
				if (allEdpList.get(i).getType() == 1) { // allowance
					allowEdpList.add(allEdpList.get(i));
				}/*else
				if (allEdpList.get(i).getType() == 2) { 
					
					if(allEdpList.get(i).getDeptallowdeducid()==36||allEdpList.get(i).getDeptallowdeducid()==37||allEdpList.get(i).getDeptallowdeducid()==38||allEdpList.get(i).getDeptallowdeducid()==39)
					deducAgEdpList.add(allEdpList.get(i)); //Deductions Adj. By CAFO/Supri./Admin.
					else
					deducTyEdpList.add(allEdpList.get(i)); //Adjust by Treasury
				}*/else {
//				if (allEdpList.get(i).getLOOKUP_ID().equals("2500383")) { //Deduction like fax
					deducOthEdpList.add(allEdpList.get(i));
				}
//				if (allEdpList.get(i).getLOOKUP_ID().equals("2500381")) { 
//					deducTyEdpList.add(allEdpList.get(i));
//				}
//			}
		}
		// Dynamic Process end
		
		//dynamic amount start
		List<Map<String,Object>> lstempdetails=displayOuterReportService.getempDetails(String.valueOf(billNo));
//		for (Iterator iterator = lstempdetails.iterator(); iterator.hasNext();) {
//			Map<String, Object> map = (Map<String, Object>) iterator.next();
//			map.get(object.getDeptalldetNm().toLowerCase().trim()).toString())
//		}
		List<DisplayOuterReportModel> allowEdpList1 = new ArrayList();// edpDao.getAllowCompoMpgData(locId);
		List<DisplayOuterReportModel> deducAgEdpList1 = new ArrayList();// edpDao.getAGDeducCompoMpgData(locId);
		List<DisplayOuterReportModel> deducTyEdpList1 = new ArrayList();// edpDao.getTRDeducCompoMpgData(locId);
		List<DisplayOuterReportModel> deducOthEdpList1 = new ArrayList();// changes for other (nps)
		List<DisplayOuterReportModel> lstObj = new ArrayList<>();
		int empsize=lstempdetails.size();
		double  basicvalue=0d;
		double  deductionvalue=0d;
		double  deduction1value=0d;
		double  deduction2value=0d;
		for (Iterator iterator = allowEdpList.iterator(); iterator.hasNext();) {
			DisplayOuterReportModel object = (DisplayOuterReportModel) iterator.next();
			String allname=object.getDeptalldetNm();
			String head=object.getHeadAccountCode();
			double  allowavalue=0d;
			
			for (Iterator iterator1 = lstempdetails.iterator(); iterator1.hasNext();) {
				Map<String, Object> map = (Map<String, Object>) iterator1.next();
				try {
						basicvalue+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
						allowavalue+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
				} catch (Exception e) {
					// TODO: handle exception
					allowavalue+=0;
					basicvalue=+basicvalue;
				}
			}
//			DisplayInnerReportModel displayInnerReportModel=new DisplayInnerReportModel();
			DisplayOuterReportModel obj = new DisplayOuterReportModel();
            obj.setDeptalldetNm(StringHelperUtils.isNullString(object.getDeptalldetNm()));
            obj.setType(StringHelperUtils.isNullInt(object.getType()));
            obj.setDeptallowdeducid(StringHelperUtils.isNullInt(object.getDeptallowdeducid()));
            obj.setTempvalue(StringHelperUtils.isNullString(String.valueOf(allowavalue)));
            obj.setTempempty(StringHelperUtils.isNullString(object.getHeadAccountCode()));
			allowEdpList1.add(obj);
		}
		
		
		for (Iterator iterator = deducAgEdpList.iterator(); iterator.hasNext();) {
			DisplayOuterReportModel object = (DisplayOuterReportModel) iterator.next();
			String allname=object.getDeptalldetNm();
			double  allowavalue=0d;
			for (Iterator iterator1 = lstempdetails.iterator(); iterator1.hasNext();) {
				Map<String, Object> map = (Map<String, Object>) iterator1.next();
				try {
					allowavalue+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
					deductionvalue+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
				} catch (Exception e) {
					// TODO: handle exception
					allowavalue+=0;
					deductionvalue=+deductionvalue;
				}
			}
			
			
			
//			DisplayInnerReportModel displayInnerReportModel=new DisplayInnerReportModel();
			DisplayOuterReportModel obj = new DisplayOuterReportModel();
            obj.setDeptalldetNm(StringHelperUtils.isNullString(object.getDeptalldetNm()));
            obj.setType(StringHelperUtils.isNullInt(object.getType()));
            obj.setDeptallowdeducid(StringHelperUtils.isNullInt(object.getDeptallowdeducid()));
            obj.setTempvalue(StringHelperUtils.isNullString(String.valueOf(allowavalue)));
            obj.setTempempty(StringHelperUtils.isNullString(object.getHeadAccountCode()));
			deducAgEdpList1.add(obj);
		}
		System.out.println("deductionvalue----"+deductionvalue);
		for (Iterator iterator = deducTyEdpList.iterator(); iterator.hasNext();) {
			DisplayOuterReportModel object = (DisplayOuterReportModel) iterator.next();
			String allname=object.getDeptalldetNm();
			double  allowavalue=0d;
			for (Iterator iterator1 = lstempdetails.iterator(); iterator1.hasNext();) {
				Map<String, Object> map = (Map<String, Object>) iterator1.next();
				try {
					allowavalue+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
					deduction1value+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
				} catch (Exception e) {
					// TODO: handle exception
					allowavalue+=0;
					deduction1value=+deduction1value;
				}
			}
			
			
			
			
//			DisplayInnerReportModel displayInnerReportModel=new DisplayInnerReportModel();
			DisplayOuterReportModel obj = new DisplayOuterReportModel();
            obj.setDeptalldetNm(StringHelperUtils.isNullString(object.getDeptalldetNm()));
            obj.setType(StringHelperUtils.isNullInt(object.getType()));
            obj.setDeptallowdeducid(StringHelperUtils.isNullInt(object.getDeptallowdeducid()));
            obj.setTempvalue(StringHelperUtils.isNullString(String.valueOf(allowavalue)));
            obj.setTempempty(StringHelperUtils.isNullString(object.getHeadAccountCode()));
			deducTyEdpList1.add(obj);
		}
		System.out.println("deduction1value---"+deduction1value);
		for (Iterator iterator = deducOthEdpList.iterator(); iterator.hasNext();) {
			DisplayOuterReportModel object = (DisplayOuterReportModel) iterator.next();
			String allname=object.getDeptalldetNm();
			double  allowavalue=0d;
			for (Iterator iterator1 = lstempdetails.iterator(); iterator1.hasNext();) {
				Map<String, Object> map = (Map<String, Object>) iterator1.next();
				try {
					allowavalue+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
					deduction2value+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
				} catch (Exception e) {
					// TODO: handle exception
					allowavalue+=0;
					deduction2value=+deduction2value;
				}
			}
			
			
//			DisplayInnerReportModel displayInnerReportModel=new DisplayInnerReportModel();
			DisplayOuterReportModel obj = new DisplayOuterReportModel();
            obj.setDeptalldetNm(StringHelperUtils.isNullString(object.getDeptalldetNm()));
            obj.setType(StringHelperUtils.isNullInt(object.getType()));
            obj.setDeptallowdeducid(StringHelperUtils.isNullInt(object.getDeptallowdeducid()));
            obj.setTempvalue(StringHelperUtils.isNullString(String.valueOf(allowavalue)));
            obj.setTempempty(StringHelperUtils.isNullString(object.getHeadAccountCode()));
			deducOthEdpList1.add(obj);
		}
		System.out.println("deduction2value---"+deduction2value);
		String officeName=commonHomeMethodsService.getOffice(ddoCode);
		//dynamic amount end

		int count=1;
		String word=CashWordConverter.doubleConvert(basicvalue-(deductionvalue+deduction1value+deduction2value));
	/*	String treasury =commonHomeMethodsService.getTreasury(ddoCode);
		String treasurycode =commonHomeMethodsService.getTreasuryCode(ddoCode);
		model.addAttribute("treasury",treasury);
		model.addAttribute("treasurycode",treasurycode);*/
		model.addAttribute("totalcount", deducTyEdpList.size()+allowEdpList.size()+deducOthEdpList.size()+deducAgEdpList.size());
		model.addAttribute("allowancecount", allowEdpList.size());
		model.addAttribute("otherdedectioncount", deducOthEdpList.size());
		model.addAttribute("dedecagcount", deducAgEdpList.size());
		model.addAttribute("allowEdpList", allowEdpList1);
		model.addAttribute("officeName", officeName);
		model.addAttribute("deducAgEdpList", deducAgEdpList1);
		model.addAttribute("deducOthEdpList", deducOthEdpList1);
		model.addAttribute("deducTyEdpList", deducTyEdpList1);
		model.addAttribute("lstDeptDataTable1", deptEligibilityForAllowAndDeductService.findDeptEligibilityForAllowAndDeductList());
		//model.addAttribute("lstDDOCode", createAdminOfficeService.lstAllDDO());
		model.addAttribute("lstAllDepartment", createAdminOfficeService.lstAllDepartment());
		model.addAttribute("language", locale.getLanguage());
		model.addAttribute("basicvalue", basicvalue);
		///model.addAttribute("officeName", officeName);
		model.addAttribute("deductionvalue", deductionvalue);
		model.addAttribute("deduction1value", deduction1value);
		model.addAttribute("deduction2value", deduction2value);
		model.addAttribute("word", word);
	///	model.addAttribute("lstDeptDataTable", mstEmployeeService.findAllEmployees());
		
		model.addAttribute("context", request.getContextPath());

		//  end

		return "views/reports/OuterReportDesign";
	}
	
	@GetMapping(value = "/btnShowReport/{yearName}/{monthName}/{billNumber}")
	public ResponseEntity<String> PaybillValidation(@PathVariable Long billNumber, @PathVariable int monthName,
			@PathVariable int yearName, HttpSession session) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		long billNo = (long) billNumber;
		Long billno=0l;
		List<Object[]>  billcreateornotbill = displayOuterReportService.findbillcrateornot(monthName,yearName,messages.getDdoCode().toString(),String.valueOf(billNo));
		Integer existingData = billcreateornotbill.size();
		for (Object[] bill : billcreateornotbill) {
		//	BigInteger billId = (BigInteger) (bill[0]);
			billno=StringHelperUtils.isNullLong(bill[0]);
		}
		//billno=billno;
		String resJson = billno.toString();
		return ResponseEntity.ok(resJson);
	}
	
	
	
	@GetMapping("/reportdesignsearch")
	public String reportdesignsearch(
			@ModelAttribute("payBillViewApprDelBillModel") PayBillViewApprDelBillModel payBillViewApprDelBillModel,
			Model model, Locale locale, HttpSession session,HttpServletRequest request) {

		String message = (String) model.asMap().get("message");
		model.addAttribute("payBillViewApprDelBillModel", payBillViewApprDelBillModel);

		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");


		if (message != null && message.equals("SUCCESS")) {
			if (locale != null && locale.getLanguage().equalsIgnoreCase("en")) {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_ENGLSH, STATUS.SUCCESS, model);
			} else {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_MARATHI, STATUS.SUCCESS, model);
			}
		}
		
		
		model.addAttribute("context", request.getContextPath());

		model.addAttribute("language", locale.getLanguage());
		return "/views/report/reportdesign";
	}



}
