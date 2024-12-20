package com.mahait.gov.in.controller;

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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mahait.gov.in.common.CashWordConverter;
import com.mahait.gov.in.common.PdfGenaratorUtil;
import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.ConsolidatedReportModel;
import com.mahait.gov.in.service.CommonHomeMethodsService;
import com.mahait.gov.in.service.ConsolidatedReportService;
import com.mahait.gov.in.service.PaybillGenerationTrnService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/ddo")
public class ConsolidatedreportController  extends BaseController{
	
//	protected final Log logger = LogFactory.getLog(getClass());
	@Autowired
	CommonHomeMethodsService commonHomeMethodsService;
	
	@Autowired
	ConsolidatedReportService consolidatedReportService;
	
	
	@Autowired
	PaybillGenerationTrnService paybillGenerationTrnService;
	
	@Autowired
	PdfGenaratorUtil pdfGenaratorUtil;
	
	
	List<ConsolidatedReportModel> lstsvnpcbasicObj = new ArrayList<>();
	
		@GetMapping("/consolidatedreport/{yearName}/{monthName}/{billNumber}")
	public String getconsolidatedreport(@ModelAttribute("consolidatedReportModel") ConsolidatedReportModel consolidatedReportModel,@PathVariable int monthName,@PathVariable int yearName,@PathVariable Long billNumber, Model model, Locale locale, HttpSession session) {
			lstsvnpcbasicObj.clear();
		String message = (String) model.asMap().get("message");
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		SimpleDateFormat sdf =new SimpleDateFormat("dd/MM/yyyy");
		addMenuAndSubMenu(model,messages);	
		
		long billNo = (long) billNumber;
		String officeDetails = consolidatedReportService.getOffice(messages.getUserName());
		String ddocode = "";
		String schemename = "";
		String schemecode = "";
		double grossamount = 0d;
		double netamount= 0d;
		int paymonth = 0;
		int payyear = 0;
		Date billcreationdate = null;
		String demandcode = "";
		String majorhead = "";
		String submajorhead = "";
		String minorhead = "";
		String subminorhead = "";
		String subhead = "";
		String teasurycode = "";
		String teasuryname = "";
		String monname = "";
		String curryear = "";
		String finyear = "";
		String designation = "";
		String month = "";
		String year = "";
		Map objectArgs = new HashMap();
		objectArgs.put("paybillNo", billNo);// paramerter
		objectArgs.put("month", month); // parameter
		objectArgs.put("year", year); // parameter
		List lsouterrepheaddtls = consolidatedReportService.getReportHeaderCOnsolidateDetails(String.valueOf(billNo));
		for (Iterator iterator = lsouterrepheaddtls.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			ddocode = (String) objects[0];
			schemename = (String) objects[1];
			schemecode = (String) objects[2];
			grossamount = (double) objects[3];
			paymonth = (int) objects[4];
			billcreationdate = (Date) objects[5];
			demandcode = (String) objects[6];
			majorhead = (String) ((objects[7] != null) ? objects[7] : 1);
			submajorhead = (String) objects[8];
			minorhead = (String) objects[9];
			subminorhead = (String) objects[10];
			subhead = (String) objects[11];
			teasurycode = (String) objects[12];
			teasuryname = (String) objects[13];
			netamount=(double) objects[14];
			payyear=(int) objects[15];
			designation=(String) objects[16];
		}
		BigInteger bigInteger = BigInteger.valueOf(monthName);
		BigInteger yearcurr = BigInteger.valueOf(yearName);
		
		List<Object[]>  monthinfo = paybillGenerationTrnService.findmonthinfo(bigInteger);
		for (Object[] monthLst : monthinfo) {
			monname = monthLst[1].toString();
		}
		
		List<Object[]>  yearinfo = paybillGenerationTrnService.findyearinfo(yearcurr);
		for (Object[] yearLst : yearinfo) {
			curryear = yearLst[1].toString();
			finyear=yearLst[10].toString();
		}
		String orgname=null;		
		String ofcname=null;
		String ddoname=null;
		Long regid=null;
		Long ofcid=null;
		List<Object[]>  ddoinfo = paybillGenerationTrnService.findDDOinfo(messages.getUserName());
		for (Object[] objddoLst : ddoinfo) {
			regid = Long.parseLong(objddoLst[0].toString());
			 ddoname = objddoLst[1].toString();
			 ofcid = Long.parseLong(objddoLst[2].toString());
		}
		List<Object[]>  regIdinfo = paybillGenerationTrnService.findregIdinfo(regid);
		for (Object[] objorgLst : regIdinfo) {
			orgname = objorgLst[2].toString();
		}
		List<Object[]>  ofcIdinfo = paybillGenerationTrnService.findofcIdinfo(ofcid);
		for (Object[] ofcLst : ofcIdinfo) {
			ofcname = ofcLst[2].toString();
		}
		List<ConsolidatedReportModel> allowEdpList = new ArrayList<ConsolidatedReportModel>();
		List<ConsolidatedReportModel> allEdpList = consolidatedReportService.getAllDataForOuternew(messages.getUserName());
		for (int i = 0; i < allEdpList.size(); i++) {
			
			if(allEdpList.get(i).getDeptalldetNm().equalsIgnoreCase("gross_amt")||allEdpList.get(i).getDeptalldetNm().equalsIgnoreCase("Prov. Fund")||allEdpList.get(i).getDeptalldetNm().equalsIgnoreCase("Prov. Fund")
					||allEdpList.get(i).getDeptalldetNm().equalsIgnoreCase("Prov. Fund")||allEdpList.get(i).getDeptalldetNm().equalsIgnoreCase("Revenue Stamp")||allEdpList.get(i).getDeptalldetNm().equalsIgnoreCase("GIS")
					||allEdpList.get(i).getDeptalldetNm().equalsIgnoreCase("HRR")||allEdpList.get(i).getDeptalldetNm().equalsIgnoreCase("IT") ||allEdpList.get(i).getDeptalldetNm().equalsIgnoreCase("PT")
					||allEdpList.get(i).getDeptalldetNm().equalsIgnoreCase("DCPS_ARR")||allEdpList.get(i).getDeptalldetNm().equalsIgnoreCase("DCPS")||allEdpList.get(i).getDeptalldetNm().equalsIgnoreCase("Accidental Policy")) {
				allowEdpList.add(allEdpList.get(i));
			}
		}
		List<Map<String,Object>> lstempdetails=consolidatedReportService.getempDetails(String.valueOf(billNo));
		List<ConsolidatedReportModel> allowEdpList1= new ArrayList();// edpDao.getAllowCompoMpgData(locId);
			List<ConsolidatedReportModel> lstObj = new ArrayList<>();
		int empsize=lstempdetails.size();
		double  basicvalue=0d;
		for (Iterator iterator = allowEdpList.iterator(); iterator.hasNext();) {
			ConsolidatedReportModel object = (ConsolidatedReportModel) iterator.next();
			String allname=object.getDeptalldetNm();
			String head=object.getHeadAccountCode();
			double  allowavalue=0d;
			  for (Iterator iterator1 = lstempdetails.iterator(); iterator1.hasNext();) {
				Map<String, Object> map = (Map<String, Object>) iterator1.next();
				try {
						basicvalue+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
						allowavalue+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
				} catch (Exception e) {
					allowavalue+=0;
					basicvalue=+basicvalue;
				}
			}
			ConsolidatedReportModel obj = new ConsolidatedReportModel();
            obj.setDeptalldetNm(StringHelperUtils.isNullString(object.getDeptalldetNm()));
            obj.setType(StringHelperUtils.isNullInt(object.getType()));
            obj.setDeptallowdeducid(StringHelperUtils.isNullInt(object.getDeptallowdeducid()));
            obj.setTempvalue(StringHelperUtils.isNullString(String.valueOf(allowavalue)));
            obj.setTempempty(StringHelperUtils.isNullString(head));
			allowEdpList1.add(obj);
		}
		
		
	    Date createdate = commonHomeMethodsService.findbillCreateDate(billNumber);
        model.addAttribute("createddate", sdf.format(createdate));
        model.addAttribute("systemdate", sdf.format(new Date()));
		String word=CashWordConverter.doubleConvert(basicvalue);
		model.addAttribute("allowEdpList", allowEdpList1);
		model.addAttribute("paybillNo", billNo);
		model.addAttribute("month", monname);
		model.addAttribute("year", curryear);
		model.addAttribute("finyear", finyear);
		model.addAttribute("officeDetails", officeDetails);
		model.addAttribute("ddocode", messages.getUserName());
		model.addAttribute("schemename", schemename);
		model.addAttribute("schemecode", schemecode);
		model.addAttribute("grossamount", grossamount);
		model.addAttribute("netamount", netamount);
		model.addAttribute("paymonth", paymonth);
		model.addAttribute("billcreationdate", billcreationdate);
		model.addAttribute("demandcode", demandcode);
		model.addAttribute("majorhead", majorhead);
		model.addAttribute("submajorhead", submajorhead);
		model.addAttribute("minorhead", minorhead);
		model.addAttribute("subminorhead", subminorhead);
		model.addAttribute("subhead", subhead);
		model.addAttribute("teasurycode", teasurycode);
		model.addAttribute("teasuryname", teasuryname);
		model.addAttribute("designation", designation);
		model.addAttribute("basicvalue", basicvalue);
		model.addAttribute("word", word);
		model.addAttribute("curryear", curryear);
		model.addAttribute("currmonyer", monname+" "+curryear);
		model.addAttribute("ofcname", ofcname);
		model.addAttribute("orgname", orgname);
		model.addAttribute("ddoname", messages.getUserName());
		model.addAttribute("yearName",yearName);
		model.addAttribute("monthName",monthName);
		model.addAttribute("billNumber",billNumber);
		model.addAttribute("todaydate",sdf.format(new Date()));
		model.addAttribute("finyear",finyear);
		model.addAttribute("consolidatedReportModel", consolidatedReportModel);
		model.addAttribute("lstsvnpcbasicObj",lstsvnpcbasicObj);
		model.addAttribute("language", locale.getLanguage());
		return "/views/report/consolidatedreport";
		
	}

		@GetMapping("/consolidatedreportPDF/{yearName}/{monthName}/{billNumber}")
	public String getconsolidatedreportPDF(@ModelAttribute("consolidatedReportModel") ConsolidatedReportModel consolidatedReportModel,@PathVariable int monthName,
			@PathVariable int yearName,@PathVariable Long billNumber, Model model, Locale locale, HttpSession session,HttpServletResponse response) {
			lstsvnpcbasicObj.clear();
		String message = (String) model.asMap().get("message");
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		SimpleDateFormat sdf =new SimpleDateFormat("dd/MM/yyyy");
		
		long billNo = (long) billNumber;
		String officeDetails = consolidatedReportService.getOffice(messages.getUserName());
		String ddocode = "";
		String schemename = "";
		String schemecode = "";
		double grossamount = 0d;
		double netamount= 0d;
		int paymonth = 0;
		int payyear = 0;
		Date billcreationdate = null;
		String demandcode = "";
		String majorhead = "";
		String submajorhead = "";
		String minorhead = "";
		String subminorhead = "";
		String subhead = "";
		String teasurycode = "";
		String teasuryname = "";
		String monname = "";
		String curryear = "";
		String finyear = "";
		String designation = "";
		String month = "";
		String year = "";
		Map objectArgs = new HashMap();
		objectArgs.put("paybillNo", billNo);// paramerter
		objectArgs.put("month", month); // parameter
		objectArgs.put("year", year); // parameter
		List lsouterrepheaddtls = consolidatedReportService.getReportHeaderCOnsolidateDetails(String.valueOf(billNo));
		for (Iterator iterator = lsouterrepheaddtls.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			ddocode = (String) objects[0];
			schemename = (String) objects[1];
			schemecode = (String) objects[2];
			grossamount = (double) objects[3];
			paymonth = (int) objects[4];
			billcreationdate = (Date) objects[5];
			demandcode = (String) objects[6];
			majorhead = (String) ((objects[7] != null) ? objects[7] : 1);
			submajorhead = (String) objects[8];
			minorhead = (String) objects[9];
			subminorhead = (String) objects[10];
			subhead = (String) objects[11];
			teasurycode = (String) objects[12];
			teasuryname = (String) objects[13];
			netamount=(double) objects[14];
			payyear=(int) objects[15];
			/*designation=(String) objects[16];*/
		}
		BigInteger bigInteger = BigInteger.valueOf(monthName);
		BigInteger yearcurr = BigInteger.valueOf(yearName);
		
		List<Object[]>  monthinfo = paybillGenerationTrnService.findmonthinfo(bigInteger);
		for (Object[] monthLst : monthinfo) {
			monname = monthLst[1].toString();
		}
		
		List<Object[]>  yearinfo = paybillGenerationTrnService.findyearinfo(yearcurr);
		for (Object[] yearLst : yearinfo) {
			curryear = yearLst[1].toString();
			finyear=yearLst[10].toString();
		}
		String orgname=null;		
		String ofcname=null;
		String ddoname=null;
		Long regid=null;
		Long ofcid=null;
		List<Object[]>  ddoinfo = paybillGenerationTrnService.findDDOinfo(messages.getUserName());
		for (Object[] objddoLst : ddoinfo) {
			regid = Long.parseLong(objddoLst[0].toString());
			 ddoname = objddoLst[1].toString();
			 ofcid = Long.parseLong(objddoLst[2].toString());
		}
		List<Object[]>  regIdinfo = paybillGenerationTrnService.findregIdinfo(regid);
		for (Object[] objorgLst : regIdinfo) {
			orgname = objorgLst[2].toString();
		}
		
		List<Object[]>  ofcIdinfo = paybillGenerationTrnService.findofcIdinfo(ofcid);
		for (Object[] ofcLst : ofcIdinfo) {
			ofcname = ofcLst[2].toString();
		}
		List<ConsolidatedReportModel> allowEdpList = new ArrayList<ConsolidatedReportModel>();
		List<ConsolidatedReportModel> allEdpList = consolidatedReportService.getAllDataForOuternew(messages.getUserName());
		for (int i = 0; i < allEdpList.size(); i++) {
			if(allEdpList.get(i).getDeptallowdeducid()==77||allEdpList.get(i).getDeptallowdeducid()==47||allEdpList.get(i).getDeptallowdeducid()==41
					||allEdpList.get(i).getDeptallowdeducid()==42 ||allEdpList.get(i).getDeptallowdeducid()==43 ||allEdpList.get(i).getDeptallowdeducid()==28
					||allEdpList.get(i).getDeptallowdeducid()==34 ||allEdpList.get(i).getDeptallowdeducid()==29) {
				allowEdpList.add(allEdpList.get(i));
			}
		}
		List<Map<String,Object>> lstempdetails=consolidatedReportService.getempDetails(String.valueOf(billNo));
		List<ConsolidatedReportModel> allowEdpList1= new ArrayList();// edpDao.getAllowCompoMpgData(locId);
			List<ConsolidatedReportModel> lstObj = new ArrayList<>();
		int empsize=lstempdetails.size();
		double  basicvalue=0d;
		for (Iterator iterator = allowEdpList.iterator(); iterator.hasNext();) {
			ConsolidatedReportModel object = (ConsolidatedReportModel) iterator.next();
			String allname=object.getDeptalldetNm();
			String head=object.getHeadAccountCode();
			double  allowavalue=0d;
			for (Iterator iterator1 = lstempdetails.iterator(); iterator1.hasNext();) {
				Map<String, Object> map = (Map<String, Object>) iterator1.next();
				try {
						basicvalue+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
						allowavalue+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
				} catch (Exception e) {
					allowavalue+=0;
					basicvalue=+basicvalue;
				}
			}
			ConsolidatedReportModel obj = new ConsolidatedReportModel();
            obj.setDeptalldetNm(StringHelperUtils.isNullString(object.getDeptalldetNm()));
            obj.setType(StringHelperUtils.isNullInt(object.getType()));
            obj.setDeptallowdeducid(StringHelperUtils.isNullInt(object.getDeptallowdeducid()));
            obj.setTempvalue(StringHelperUtils.isNullString(String.valueOf(allowavalue)));
            obj.setTempempty(StringHelperUtils.isNullString(object.getHeadAccountCode()));
			allowEdpList1.add(obj);
		}
		
		Date createdate = commonHomeMethodsService.findbillCreateDate(billNumber);
        model.addAttribute("createddate", sdf.format(createdate));
        model.addAttribute("systemdate", sdf.format(new Date()));
		String word=CashWordConverter.doubleConvert(basicvalue);
		model.addAttribute("allowEdpList", allowEdpList1);
		model.addAttribute("paybillNo", billNo);
		model.addAttribute("month", monname);
		model.addAttribute("year", curryear);
		model.addAttribute("finyear", finyear);
		model.addAttribute("officeDetails", officeDetails);
		model.addAttribute("ddocode", messages.getUserName());
		model.addAttribute("schemename", schemename);
		model.addAttribute("schemecode", schemecode);
		model.addAttribute("grossamount", grossamount);
		model.addAttribute("netamount", netamount);
		model.addAttribute("paymonth", paymonth);
		model.addAttribute("billcreationdate", billcreationdate);
		model.addAttribute("demandcode", demandcode);
		model.addAttribute("majorhead", majorhead);
		model.addAttribute("submajorhead", submajorhead);
		model.addAttribute("minorhead", minorhead);
		model.addAttribute("subminorhead", subminorhead);
		model.addAttribute("subhead", subhead);
		model.addAttribute("teasurycode", teasurycode);
		model.addAttribute("teasuryname", teasuryname);
		model.addAttribute("designation", designation);
		model.addAttribute("basicvalue", basicvalue);
		model.addAttribute("word", word);
		model.addAttribute("curryear", curryear);
		model.addAttribute("currmonyer", monname+" "+curryear);
		model.addAttribute("ofcname", ofcname);
		model.addAttribute("orgname", orgname);
		model.addAttribute("ddoname", messages.getUserName());
		model.addAttribute("yearName",yearName);
		model.addAttribute("monthName",monthName);
		model.addAttribute("billNumber",billNumber);
		model.addAttribute("todaydate",sdf.format(new Date()));
		model.addAttribute("finyear",finyear);
		model.addAttribute("ConsolidatedReportModel", consolidatedReportModel);
		model.addAttribute("lstsvnpcbasicObj",lstsvnpcbasicObj);
		model.addAttribute("language", locale.getLanguage());
		
		 Map<String,String> data = new HashMap<String,String>();
		    data.put("ddoname",ddoname);
		    try {
				pdfGenaratorUtil.createPdf("views/report/consolidatedreport",model.asMap(),response);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		
		return "/views/report/consolidatedreport";
		
	}
		
		

		@GetMapping("/consolidatePayBillTrnReport/{consolidatePayBillTrnId}")
	public String getconsolidatedreportNew(@ModelAttribute("consolidatedReportModel") ConsolidatedReportModel consolidatedReportModel,@PathVariable Long consolidatePayBillTrnId, Model model, Locale locale, HttpSession session) {
			lstsvnpcbasicObj.clear();
		String message = (String) model.asMap().get("message");
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		SimpleDateFormat sdf =new SimpleDateFormat("dd/MM/yyyy");
		
		long billNo = (long) consolidatePayBillTrnId;
		String officeDetails = consolidatedReportService.getOffice(messages.getUserName());
		String ddocode = "";
		String schemename = "";
		String schemecode = "";
		String grossamount = "";
		String netamount= "";
		int paymonth = 0;
		int payyear = 0;
		Date billcreationdate = null;
		String demandcode = "";
		String majorhead = "";
		String submajorhead = "";
		String minorhead = "";
		String subminorhead = "";
		String subhead = "";
		String teasurycode = "";
		String teasuryname = "";
		String monname = "";
		String curryear = "";
		String finyear = "";
		String designation = "";
		String month = "";
		String year = "";
		Map objectArgs = new HashMap();
		objectArgs.put("paybillNo", billNo);// paramerter
		objectArgs.put("month", month); // parameter
		objectArgs.put("year", year); // parameter
		List lsouterrepheaddtls = consolidatedReportService.getReportHeaderCOnsolidateDetails(String.valueOf(billNo));
		for (Iterator iterator = lsouterrepheaddtls.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			ddocode = (String) objects[0];
			schemename = (String) objects[1];
			schemecode = (String) objects[2];
			grossamount =  objects[3].toString();
			paymonth = (int) objects[4];
			billcreationdate = (Date) objects[5];
			demandcode = (String) objects[6];
			majorhead = (String) ((objects[7] != null) ? objects[7] : 1);
			submajorhead = (String) objects[8];
			minorhead = (String) objects[9];
			subminorhead = (String) objects[10];
			subhead = (String) objects[11];
			netamount= objects[12].toString();
			payyear=(int) objects[13];
		}
		Long monthName=0l;
		Long yearName=0l;
		List<Object[]> monthyear =consolidatedReportService.findbillBillNumber(consolidatePayBillTrnId);
		for (Object[] objects : monthyear) {
			if(objects[18]!=null)
			monthName=Long.parseLong(objects[18].toString());
			if(objects[19]!=null)
			yearName=Long.parseLong(objects[19].toString());
		}
		
		BigInteger bigInteger = BigInteger.valueOf(monthName);
		BigInteger yearcurr = BigInteger.valueOf(yearName);
		
		List<Object[]>  monthinfo = paybillGenerationTrnService.findmonthinfo(bigInteger);
		for (Object[] monthLst : monthinfo) {
			monname = monthLst[4].toString();
		}
		
		List<Object[]>  yearinfo = paybillGenerationTrnService.findyearinfo(yearcurr);
		for (Object[] yearLst : yearinfo) {
			curryear = yearLst[9].toString();
			finyear=yearLst[3].toString();
		}
		String orgname=null;		
		String ofcname=null;
		String ddoname=null;
		Long regid=null;
		Long ofcid=null;
		List<Object[]>  ddoinfo = paybillGenerationTrnService.findDDOinfo(messages.getUserName());
		for (Object[] objddoLst : ddoinfo) {
			/////regid = Long.parseLong(objddoLst[0].toString());
			 ddoname = objddoLst[0].toString();
			 if(objddoLst[1]!=null)
			 ofcid = Long.parseLong(objddoLst[1].toString());
		}
		/*List<Object[]>  regIdinfo = paybillGenerationTrnService.findregIdinfo(regid);
		for (Object[] objorgLst : regIdinfo) {
			orgname = objorgLst[2].toString();
		}
		List<Object[]>  ofcIdinfo = paybillGenerationTrnService.findofcIdinfo(ofcid);
		for (Object[] ofcLst : ofcIdinfo) {
			ofcname = ofcLst[2].toString();
		}*/
		List<ConsolidatedReportModel> allowEdpList = new ArrayList<ConsolidatedReportModel>();
		List<ConsolidatedReportModel> allEdpList = consolidatedReportService.getAllDataForOuternew(messages.getUserName());
		for (int i = 0; i < allEdpList.size(); i++) {
			
			if(allEdpList.get(i).getDeptalldetNm().equalsIgnoreCase("gross_amt")||allEdpList.get(i).getDeptalldetNm().equalsIgnoreCase("Prov.Fund")||allEdpList.get(i).getDeptalldetNm().equalsIgnoreCase("Prov.Fund")
					||allEdpList.get(i).getDeptalldetNm().equalsIgnoreCase("pf")||allEdpList.get(i).getDeptalldetNm().equalsIgnoreCase("Revenue Stamp")||allEdpList.get(i).getDeptalldetNm().equalsIgnoreCase("GIS")
					||allEdpList.get(i).getDeptalldetNm().equalsIgnoreCase("HRR")||allEdpList.get(i).getDeptalldetNm().equalsIgnoreCase("INCOME_TAX") ||allEdpList.get(i).getDeptalldetNm().equalsIgnoreCase("PT")
					||allEdpList.get(i).getDeptalldetNm().equalsIgnoreCase("DCPS_ARR")||allEdpList.get(i).getDeptalldetNm().equalsIgnoreCase("DCPS")||allEdpList.get(i).getDeptalldetNm().equalsIgnoreCase("GROUP_ACC_POLICY")
					||allEdpList.get(i).getDeptalldetNm().equalsIgnoreCase("total_deduction")||allEdpList.get(i).getDeptalldetNm().equalsIgnoreCase("net_amt")) {
				allowEdpList.add(allEdpList.get(i));
			}
		}
		
		///String headname= commonHomeMethodsService.headofcodes(consolidatePayBillTrnId);
		List<Map<String,Object>> lstempdetails=consolidatedReportService.getempDetails(String.valueOf(billNo));
		List<ConsolidatedReportModel> allowEdpList1= new ArrayList();// edpDao.getAllowCompoMpgData(locId);
			List<ConsolidatedReportModel> lstObj = new ArrayList<>();
		int empsize=lstempdetails.size();
		double  basicvalue=0d;
		double  basicgrossamt=0d;
		double  deductionvalue=0d;
		double alldeductionvalue=0d;
		for (Iterator iterator = allowEdpList.iterator(); iterator.hasNext();) {
			ConsolidatedReportModel object = (ConsolidatedReportModel) iterator.next();
			String allname=object.getDeptalldetNm();
			String head=object.getHeadAccountCode();
			double  allowavalue=0d;
			  for (Iterator iterator1 = lstempdetails.iterator(); iterator1.hasNext();) {
				Map<String, Object> map = (Map<String, Object>) iterator1.next();
				try {
					if(object.getDeptalldetNm().toLowerCase().equals("gross_amt")) {
						allowavalue = Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
						basicgrossamt = allowavalue;
					}else  if(object.getDeptalldetNm().toLowerCase().equals("total_deduction")) {
						allowavalue = Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
						alldeductionvalue = allowavalue;
					}
					else  if(object.getDeptalldetNm().toLowerCase().equals("pf")) {
						allowavalue = Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
						alldeductionvalue = allowavalue;
					}
					
					else{
						deductionvalue+= Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
						allowavalue+= Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
						
					}
				} catch (Exception e) {
					allowavalue+=0;
					deductionvalue= deductionvalue;
				}
			}
			  
			  basicvalue=basicgrossamt- (alldeductionvalue+deductionvalue);
			ConsolidatedReportModel obj = new ConsolidatedReportModel();
            obj.setDeptalldetNm(StringHelperUtils.isNullString(object.getDeptalldetNm()));
            obj.setType(StringHelperUtils.isNullInt(object.getType()));
            obj.setDeptallowdeducid(StringHelperUtils.isNullInt(object.getDeptallowdeducid()));
            obj.setTempvalue(StringHelperUtils.isNullString(String.valueOf(allowavalue)));
           	obj.setTempempty(StringHelperUtils.isNullString(object.getHeadAccountCode()));
			allowEdpList1.add(obj);
		}
		
		
	   Date createdate = consolidatedReportService.findbillCreateConsolidateDate(consolidatePayBillTrnId);
	  ///  model.addAttribute("headofcode",headname);*/
        model.addAttribute("createddate", sdf.format(createdate));
        model.addAttribute("systemdate", sdf.format(new Date()));
		String word=CashWordConverter.doubleConvert(basicvalue);
		model.addAttribute("allowEdpList", allowEdpList1);
		model.addAttribute("paybillNo", billNo);
		model.addAttribute("month", monname);
		model.addAttribute("year", curryear);
		model.addAttribute("finyear", finyear);
		model.addAttribute("officeDetails", officeDetails);
		model.addAttribute("ddocode", messages.getUserName());
		model.addAttribute("schemename", schemename);
		model.addAttribute("schemecode", schemecode);
		model.addAttribute("grossamount", grossamount);
		model.addAttribute("netamount", netamount);
		model.addAttribute("paymonth", paymonth);
		model.addAttribute("billcreationdate", billcreationdate);
		model.addAttribute("demandcode", demandcode);
		model.addAttribute("majorhead", majorhead);
		model.addAttribute("submajorhead", submajorhead);
		model.addAttribute("minorhead", minorhead);
		model.addAttribute("subminorhead", subminorhead);
		model.addAttribute("subhead", subhead);
		model.addAttribute("teasurycode", teasurycode);
		model.addAttribute("teasuryname", teasuryname);
		model.addAttribute("designation", designation);
		model.addAttribute("basicvalue", basicvalue);
		model.addAttribute("word", word);
		model.addAttribute("curryear", curryear);
		model.addAttribute("currmonyer", monname+" "+curryear);
		model.addAttribute("ofcname", ofcname);
		model.addAttribute("orgname", orgname);
		model.addAttribute("ddoname", messages.getUserName());
		model.addAttribute("yearName",yearName);
		model.addAttribute("monthName",monthName);
		model.addAttribute("billNumber",consolidatePayBillTrnId);
		model.addAttribute("todaydate",sdf.format(new Date()));
		model.addAttribute("finyear",finyear);
		model.addAttribute("consolidatedReportModel", consolidatedReportModel);
		model.addAttribute("lstsvnpcbasicObj",lstsvnpcbasicObj);
		model.addAttribute("language", locale.getLanguage());
		return "/views/reports/consolidatedreport";
		
	}

		@GetMapping("/consolidatePayBillTrnReportPDF/{consolidatePayBillTrnId}")
	public String getconsolidatedreportNewPDF(@ModelAttribute("consolidatedReportModel") ConsolidatedReportModel consolidatedReportModel,@PathVariable Long consolidatePayBillTrnId,
			Model model, Locale locale, HttpSession session,HttpServletResponse response) {
			lstsvnpcbasicObj.clear();
		String message = (String) model.asMap().get("message");
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		SimpleDateFormat sdf =new SimpleDateFormat("dd/MM/yyyy");
		long billNo = (long) consolidatePayBillTrnId;
		String officeDetails = consolidatedReportService.getOffice(messages.getUserName());
		String ddocode = "";
		String schemename = "";
		String schemecode = "";
		double grossamount = 0d;
		double netamount= 0d;
		int paymonth = 0;
		int payyear = 0;
		Date billcreationdate = null;
		String demandcode = "";
		String majorhead = "";
		String submajorhead = "";
		String minorhead = "";
		String subminorhead = "";
		String subhead = "";
		String teasurycode = "";
		String teasuryname = "";
		String monname = "";
		String curryear = "";
		String finyear = "";
		String designation = "";
		String month = "";
		String year = "";
		Map objectArgs = new HashMap();
		objectArgs.put("paybillNo", billNo);// paramerter
		objectArgs.put("month", month); // parameter
		objectArgs.put("year", year); // parameter
		List lsouterrepheaddtls = consolidatedReportService.getReportHeaderDetails(String.valueOf(billNo));
		for (Iterator iterator = lsouterrepheaddtls.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			ddocode = (String) objects[0];
			schemename = (String) objects[1];
			schemecode = (String) objects[2];
			grossamount = (double) objects[3];
			paymonth = (int) objects[4];
			billcreationdate = (Date) objects[5];
			demandcode = (String) objects[6];
			majorhead = (String) ((objects[7] != null) ? objects[7] : 1);
			submajorhead = (String) objects[8];
			minorhead = (String) objects[9];
			subminorhead = (String) objects[10];
			subhead = (String) objects[11];
			teasurycode = (String) objects[12];
			teasuryname = (String) objects[13];
			netamount=(double) objects[14];
			payyear=(int) objects[15];
			/*designation=(String) objects[16];*/
		}
		Long monthName=0l;
		Long yearName=0l;
		/*List<Object[]> monthyear =commonHomeMethodsService.findbillBillNumber(consolidatePayBillTrnId);
		for (Object[] objects : monthyear) {
			monthName=Long.parseLong(objects[4].toString());
			yearName=Long.parseLong(objects[5].toString());
		}*/
		
		BigInteger bigInteger = BigInteger.valueOf(monthName);
		BigInteger yearcurr = BigInteger.valueOf(yearName);
		
		List<Object[]>  monthinfo = paybillGenerationTrnService.findmonthinfo(bigInteger);
		for (Object[] monthLst : monthinfo) {
			monname = monthLst[1].toString();
		}
		
		List<Object[]>  yearinfo = paybillGenerationTrnService.findyearinfo(yearcurr);
		for (Object[] yearLst : yearinfo) {
			curryear = yearLst[1].toString();
			finyear=yearLst[10].toString();
		}
		String orgname=null;		
		String ofcname=null;
		String ddoname=null;
		Long regid=null;
		Long ofcid=null;
		List<Object[]>  ddoinfo = paybillGenerationTrnService.findDDOinfo(messages.getUserName());
		for (Object[] objddoLst : ddoinfo) {
			regid = Long.parseLong(objddoLst[0].toString());
			 ddoname = objddoLst[1].toString();
			 ofcid = Long.parseLong(objddoLst[2].toString());
		}
		List<Object[]>  regIdinfo = paybillGenerationTrnService.findregIdinfo(regid);
		for (Object[] objorgLst : regIdinfo) {
			orgname = objorgLst[2].toString();
		}
		List<Object[]>  ofcIdinfo = paybillGenerationTrnService.findofcIdinfo(ofcid);
		for (Object[] ofcLst : ofcIdinfo) {
			ofcname = ofcLst[2].toString();
		}
		List<ConsolidatedReportModel> allowEdpList = new ArrayList<ConsolidatedReportModel>();
		List<ConsolidatedReportModel> allEdpList = consolidatedReportService.getAllDataForOuternew(messages.getUserName());
		for (int i = 0; i < allEdpList.size(); i++) {
			
			if(allEdpList.get(i).getDeptalldetNm().equalsIgnoreCase("gross_amt")||allEdpList.get(i).getDeptalldetNm().equalsIgnoreCase("Prov. Fund")||allEdpList.get(i).getDeptalldetNm().equalsIgnoreCase("Prov. Fund")
					||allEdpList.get(i).getDeptalldetNm().equalsIgnoreCase("Prov. Fund")||allEdpList.get(i).getDeptalldetNm().equalsIgnoreCase("Revenue Stamp")||allEdpList.get(i).getDeptalldetNm().equalsIgnoreCase("GIS")
					||allEdpList.get(i).getDeptalldetNm().equalsIgnoreCase("HRR")||allEdpList.get(i).getDeptalldetNm().equalsIgnoreCase("IT") ||allEdpList.get(i).getDeptalldetNm().equalsIgnoreCase("PT")
					||allEdpList.get(i).getDeptalldetNm().equalsIgnoreCase("DCPS_ARR")||allEdpList.get(i).getDeptalldetNm().equalsIgnoreCase("DCPS")||allEdpList.get(i).getDeptalldetNm().equalsIgnoreCase("Accidental Policy")
					||allEdpList.get(i).getDeptalldetNm().equalsIgnoreCase("net_amt")) {
				allowEdpList.add(allEdpList.get(i));
			}
		}
		List<Map<String,Object>> lstempdetails=consolidatedReportService.getempDetails(String.valueOf(billNo));
		List<ConsolidatedReportModel> allowEdpList1= new ArrayList();// edpDao.getAllowCompoMpgData(locId);
			List<ConsolidatedReportModel> lstObj = new ArrayList<>();
		int empsize=lstempdetails.size();
		double  basicvalue=0d;
		for (Iterator iterator = allowEdpList.iterator(); iterator.hasNext();) {
			ConsolidatedReportModel object = (ConsolidatedReportModel) iterator.next();
			String allname=object.getDeptalldetNm();
			String head=object.getHeadAccountCode();
			double  allowavalue=0d;
			  for (Iterator iterator1 = lstempdetails.iterator(); iterator1.hasNext();) {
				Map<String, Object> map = (Map<String, Object>) iterator1.next();
				try {
						basicvalue+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
						allowavalue+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
				} catch (Exception e) {
					allowavalue+=0;
					basicvalue=+basicvalue;
				}
			}
			ConsolidatedReportModel obj = new ConsolidatedReportModel();
            obj.setDeptalldetNm(StringHelperUtils.isNullString(object.getDeptalldetNm()));
            obj.setType(StringHelperUtils.isNullInt(object.getType()));
            obj.setDeptallowdeducid(StringHelperUtils.isNullInt(object.getDeptallowdeducid()));
            obj.setTempvalue(StringHelperUtils.isNullString(String.valueOf(allowavalue)));
            obj.setTempempty(StringHelperUtils.isNullString(object.getHeadAccountCode()));
			allowEdpList1.add(obj);
		}
		
		
	    Date createdate = commonHomeMethodsService.findbillCreateDate(consolidatePayBillTrnId);
        model.addAttribute("createddate", sdf.format(createdate));
        model.addAttribute("systemdate", sdf.format(new Date()));
		String word=CashWordConverter.doubleConvert(basicvalue);
		model.addAttribute("allowEdpList", allowEdpList1);
		model.addAttribute("paybillNo", billNo);
		model.addAttribute("month", monname);
		model.addAttribute("year", curryear);
		model.addAttribute("finyear", finyear);
		model.addAttribute("officeDetails", officeDetails);
		model.addAttribute("ddocode", messages.getUserName());
		model.addAttribute("schemename", schemename);
		model.addAttribute("schemecode", schemecode);
		model.addAttribute("grossamount", grossamount);
		model.addAttribute("netamount", netamount);
		model.addAttribute("paymonth", paymonth);
		model.addAttribute("billcreationdate", billcreationdate);
		model.addAttribute("demandcode", demandcode);
		model.addAttribute("majorhead", majorhead);
		model.addAttribute("submajorhead", submajorhead);
		model.addAttribute("minorhead", minorhead);
		model.addAttribute("subminorhead", subminorhead);
		model.addAttribute("subhead", subhead);
		model.addAttribute("teasurycode", teasurycode);
		model.addAttribute("teasuryname", teasuryname);
		model.addAttribute("designation", designation);
		model.addAttribute("basicvalue", basicvalue);
		model.addAttribute("word", word);
		model.addAttribute("curryear", curryear);
		model.addAttribute("currmonyer", monname+" "+curryear);
		model.addAttribute("ofcname", ofcname);
		model.addAttribute("orgname", orgname);
		model.addAttribute("ddoname", messages.getUserName());
		model.addAttribute("yearName",yearName);
		model.addAttribute("monthName",monthName);
		model.addAttribute("billNumber",consolidatePayBillTrnId);
		model.addAttribute("todaydate",sdf.format(new Date()));
		model.addAttribute("finyear",finyear);
		model.addAttribute("consolidatedReportModel", consolidatedReportModel);
		model.addAttribute("lstsvnpcbasicObj",lstsvnpcbasicObj);
		model.addAttribute("language", locale.getLanguage());
		
		 Map<String,String> data = new HashMap<String,String>();
		    data.put("ddoname",ddoname);
		    try {
				pdfGenaratorUtil.createPdf("views/report/consolidatedreport",model.asMap(),response);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		return "/views/report/consolidatedreport";
		
	}
		
		
}
