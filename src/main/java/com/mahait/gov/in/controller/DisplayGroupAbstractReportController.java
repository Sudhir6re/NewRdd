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
import com.mahait.gov.in.model.DisplayGroupAbstractReportModel;
import com.mahait.gov.in.model.RegularReportModel;
import com.mahait.gov.in.service.CommonHomeMethodsService;
import com.mahait.gov.in.service.CreateAdminOfficeService;
import com.mahait.gov.in.service.DeptEligibilityForAllowAndDeductService;
import com.mahait.gov.in.service.DisplayGroupAbstractReportService;
import com.mahait.gov.in.service.DisplayOuterReportService;
import com.mahait.gov.in.service.MstEmployeeService;
import com.mahait.gov.in.service.PaybillGenerationTrnService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/ddoast")
public class DisplayGroupAbstractReportController extends BaseController{
	
//	protected final Log logger = LogFactory.getLog(getClass());
	@Autowired
	CommonHomeMethodsService commonHomeMethodsService;
	@Autowired
	DisplayGroupAbstractReportService displayGroupAbstractReportService;
	@Autowired
	DeptEligibilityForAllowAndDeductService deptEligibilityForAllowAndDeductService;
	@Autowired
	CreateAdminOfficeService createAdminOfficeService;
	@Autowired
	MstEmployeeService mstEmployeeService;
	
	@Autowired
	PaybillGenerationTrnService paybillGenerationTrnService;
	
	@Autowired
	PdfGenaratorUtil pdfGenaratorUtil;
	
	@Autowired
	DisplayOuterReportService displayOuterReportService;
	
	@GetMapping("/groupAbstractReport/{billNumber}/{ddoCode}/{month}/{year}")
	public String groupAbstractReport(@ModelAttribute("displayGroupAbstractReportModel") DisplayGroupAbstractReportModel displayGroupAbstractReportModel,@PathVariable Long billNumber,
			@PathVariable String ddoCode,@PathVariable int month,@PathVariable int year,Model model, Locale locale, HttpSession session) {

		String message = (String) model.asMap().get("message");
		

		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(model,messages);

		if(ddoCode.equals("1")) {
			ddoCode=messages.getUserName();
		}else {
			ddoCode=messages.getDdoCode();
		}
				long billNo = (long) billNumber;
		String officeDetails = commonHomeMethodsService.getOffice(ddoCode);
		String ddocode = "";
		String schemecode = "";
		BigDecimal grossamount =null;
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
		
		BigInteger currmonth = BigInteger.valueOf(month);
		BigInteger yearcurr = BigInteger.valueOf(year);
		
		List<Object[]>  monthinfo = paybillGenerationTrnService.findmonthinfo(currmonth);
		for (Object[] monthLst : monthinfo) {
			// String empName;
			monname = monthLst[4].toString();
			
		}
		
		List<Object[]>  yearinfo = paybillGenerationTrnService.findyearinfo(yearcurr);
		for (Object[] yearLst : yearinfo) {
			// String empName;
			curryear = yearLst[4].toString();
			finyear = yearLst[3].toString();
			
		}
		SimpleDateFormat sdf =new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String ddoname=null;
		String orgname=null;		
		String ofcname=null;
		String premonname=null;
		Long regid=null;
		Long ofcid=null;
		List<Object[]>  ddoinfo = paybillGenerationTrnService.findDDOinfo(ddoCode);

		for (Object[] objddoLst : ddoinfo) {
			// String empName;
			 ddoname = objddoLst[0].toString();
			 if(objddoLst[1]!=null)
			 ofcid = Long.parseLong(objddoLst[1].toString());
			
		}
		model.addAttribute("paybillNo", billNo);
		model.addAttribute("month", monname);
		model.addAttribute("year", curryear);
		model.addAttribute("totaldeduction", displayGroupAbstractReportService.getTotalDeductionGroupAbstract((double)billNo));

		//  start
		List<DisplayGroupAbstractReportModel> allowEdpList = new ArrayList<DisplayGroupAbstractReportModel>();// edpDao.getAllowCompoMpgData(locId);
		List<DisplayGroupAbstractReportModel> deducAgEdpList = new ArrayList<DisplayGroupAbstractReportModel>();// edpDao.getAGDeducCompoMpgData(locId);
		List<DisplayGroupAbstractReportModel> deducTyEdpList = new ArrayList<DisplayGroupAbstractReportModel>();// edpDao.getTRDeducCompoMpgData(locId);
		List<DisplayGroupAbstractReportModel> deducOthEdpList = new ArrayList<DisplayGroupAbstractReportModel>();// changes for other (nps)

		System.out.println(messages.getUserName());
		
		List<DisplayGroupAbstractReportModel> allEdpList = displayGroupAbstractReportService.getAllDataForAbstractnew(ddoCode,billNumber);
		
		DisplayGroupAbstractReportModel obj1 = new DisplayGroupAbstractReportModel();
    	obj1.setDeptalldetNm(StringHelperUtils.isNullString("Basic_Pay"));
        obj1.setType(1);
        obj1.setDeptallowdeducid(1);
        obj1.setTempvalue("0.00");
        obj1.setTempempty("0.00");
        allowEdpList.add(obj1);
		for (int i = 0; i < allEdpList.size(); i++) {

				if (allEdpList.get(i).getType() == 1) { // allowance
					allowEdpList.add(allEdpList.get(i));
				}else
				if (allEdpList.get(i).getType() == 2) { 
					
					if(allEdpList.get(i).getDeptallowdeducid()==36||allEdpList.get(i).getDeptallowdeducid()==37||allEdpList.get(i).getDeptallowdeducid()==38||allEdpList.get(i).getDeptallowdeducid()==39)
					deducAgEdpList.add(allEdpList.get(i)); //Deductions Adj. By CAFO/Supri./Admin.
					else
					deducTyEdpList.add(allEdpList.get(i)); //Adjust by Treasury
				}else {
					deducTyEdpList.add(allEdpList.get(i));
					System.out.println("------------"+allEdpList.get(i));
				}
		}
		
		List<Map<String,Object>> lstempdetails=displayGroupAbstractReportService.getempDetailsGroupAbstract(String.valueOf(billNo));
		List<Map<String,Object>> lstempdetailsA=displayGroupAbstractReportService.getempDetailsGroupAbstractA(String.valueOf(billNo));
		List<Map<String,Object>> lstempdetailsB=displayGroupAbstractReportService.getempDetailsGroupAbstractB(String.valueOf(billNo));
		List<Map<String,Object>> lstempdetailsC=displayGroupAbstractReportService.getempDetailsGroupAbstractC(String.valueOf(billNo));
		List<Map<String,Object>> lstempdetailsD=displayGroupAbstractReportService.getempDetailsGroupAbstractD(String.valueOf(billNo));
		List<Map<String,Object>> lstempdetailsE=displayGroupAbstractReportService.getempDetailsGroupAbstractE(String.valueOf(billNo));
		List<DisplayGroupAbstractReportModel> allowEdpList1 = new ArrayList();// edpDao.getAllowCompoMpgData(locId);
		List<DisplayGroupAbstractReportModel> deducAgEdpList1 = new ArrayList();// edpDao.getAGDeducCompoMpgData(locId);
		List<DisplayGroupAbstractReportModel> deducTyEdpList1 = new ArrayList();// edpDao.getTRDeducCompoMpgData(locId);
		List<DisplayGroupAbstractReportModel> deducOthEdpList1 = new ArrayList();// changes for other (nps)
		List<DisplayGroupAbstractReportModel> lstObj = new ArrayList<>();
		int empsize=lstempdetails.size();
		double  basicvalue=0d;
		double  basicvalueA=0d;
		double  basicvalueB=0d;
		double  basicvalueC=0d;
		double  basicvalueD=0d;
		double  basicvalueE=0d;
		double  deductionvalue=0d;
		/*double  deductionvalueA=0d;
		double  deductionvalueB=0d;
		double  deductionvalueC=0d;
		double  deductionvalueD=0d;
		double  deductionvalueE=0d;*/
		double  deducOthEdvalue=0d;
		double  deducOthEdvalueA=0d;
		double  deducOthEdvalueB=0d;
		double  deducOthEdvalueC=0d;
		double  deducOthEdvalueD=0d;
		double  deducOthEdvalueE=0d;
		double  deducnvalue=0d;
		double  deducvalueA=0d;
		double  deducvalueB=0d;
		double  deducvalueC=0d;
		double  deducvalueD=0d;
		double  deducvalueE=0d;
		double  deduction1value=0d;
		double  deduction2value=0d;
		double  allowavalue1=0d;
		double  deducOthEdpvalue=0d;
		double  deducOthEdpAvalue=0d;
		double  deducOthEdpBvalue=0d;
		double  deducOthEdpCvalue=0d;
		double  deducOthEdpDvalue=0d;
		double  deducOthEdpEvalue=0d;
		
		for (Iterator iterator = allowEdpList.iterator(); iterator.hasNext();) {
			DisplayGroupAbstractReportModel object = (DisplayGroupAbstractReportModel) iterator.next();
			String allname=object.getDeptalldetNm();
			String head=object.getHeadAccountCode();
			double  allowavalue=0d;
			double  allowavalueA=0d;
			double  allowavalueB=0d;
			double  allowavalueC=0d;
			double  allowavalueD=0d;
			double  allowavalueE=0d;
			
			
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
			for (Iterator iterator1 = lstempdetailsA.iterator(); iterator1.hasNext();) {
				Map<String, Object> map = (Map<String, Object>) iterator1.next();
				try {
						basicvalueA+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
						allowavalueA+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
				} catch (Exception e) {
					// TODO: handle exception
					allowavalueA+=0;
					basicvalueA=+basicvalueA;
				}
			}
			for (Iterator iterator1 = lstempdetailsB.iterator(); iterator1.hasNext();) {
				Map<String, Object> map = (Map<String, Object>) iterator1.next();
				try {
						basicvalueB+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
						allowavalueB+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
				} catch (Exception e) {
					// TODO: handle exception
					allowavalueB+=0;
					basicvalueB=+basicvalueB;
				}
			}
			for (Iterator iterator1 = lstempdetailsC.iterator(); iterator1.hasNext();) {
				Map<String, Object> map = (Map<String, Object>) iterator1.next();
				try {
						basicvalueC+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
						allowavalueC+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
				} catch (Exception e) {
					// TODO: handle exception
					allowavalueC+=0;
					basicvalueC=+basicvalueC;
				}
			}
			for (Iterator iterator1 = lstempdetailsD.iterator(); iterator1.hasNext();) {
				Map<String, Object> map = (Map<String, Object>) iterator1.next();
				try {
						basicvalueD+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
						allowavalueD+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
				} catch (Exception e) {
					// TODO: handle exception
					allowavalueD+=0;
					basicvalueD=+basicvalueD;
				}
			}
			for (Iterator iterator1 = lstempdetailsE.iterator(); iterator1.hasNext();) {
				Map<String, Object> map = (Map<String, Object>) iterator1.next();
				try {
						basicvalueE+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
						allowavalueE+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
				} catch (Exception e) {
					// TODO: handle exception
					allowavalueE+=0;
					basicvalueE=+basicvalueE;
				}
			}
			
			
			
//			DisplayInnerReportModel displayInnerReportModel=new DisplayInnerReportModel();
			DisplayGroupAbstractReportModel obj = new DisplayGroupAbstractReportModel();
            obj.setDeptalldetNm(StringHelperUtils.isNullString(object.getDeptalldetNm()));
            obj.setType(StringHelperUtils.isNullInt(object.getType()));
//            obj.setDeptallowdeducid(StringHelperUtils.isNullInt(object.getDeptallowdeducid()));
            obj.setAllowavalueA(StringHelperUtils.isNullString(String.valueOf(allowavalueA)));
            obj.setAllowavalueB(StringHelperUtils.isNullString(String.valueOf(allowavalueB)));
            obj.setAllowavalueC(StringHelperUtils.isNullString(String.valueOf(allowavalueC)));
            obj.setAllowavalueD(StringHelperUtils.isNullString(String.valueOf(allowavalueD)));
            obj.setAllowavalueE(StringHelperUtils.isNullString(String.valueOf(allowavalueE)));
            obj.setTempvalue(StringHelperUtils.isNullString(String.valueOf(allowavalue)));
            obj.setTempempty(StringHelperUtils.isNullString(object.getHeadAccountCode()));
			//logger.info("allname="+allname);
			//logger.info("allowavalue="+allowavalue);
			allowEdpList1.add(obj);
		}
		
		
		///End Allowance Code
		//Start Deduction AG Code
		
		for (Iterator iterator = deducAgEdpList.iterator(); iterator.hasNext();) {
			DisplayGroupAbstractReportModel object = (DisplayGroupAbstractReportModel) iterator.next();
			String allname=object.getDeptalldetNm();
			double  deducnvalue1=0d;
			double  deductionvalueA=0d;
			double  deductionvalueB=0d;
			double  deductionvalueC=0d;
			double  deductionvalueD=0d;
			double  deductionvalueE=0d;
			for (Iterator iterator1 = lstempdetails.iterator(); iterator1.hasNext();) {
				Map<String, Object> map = (Map<String, Object>) iterator1.next();
				try {
					deducnvalue1+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
					deductionvalue+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
				} catch (Exception e) {
					// TODO: handle exception
					deductionvalue+=0;
					deducnvalue1=+deducnvalue1;
				}
			}
			for (Iterator iterator1 = lstempdetailsA.iterator(); iterator1.hasNext();) {
				Map<String, Object> map = (Map<String, Object>) iterator1.next();
				try {
					deducvalueA+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
						deductionvalueA+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
				} catch (Exception e) {
					// TODO: handle exception
					deductionvalueA+=0;
					deducvalueA=+deducvalueA;
				}
			}
			for (Iterator iterator1 = lstempdetailsB.iterator(); iterator1.hasNext();) {
				Map<String, Object> map = (Map<String, Object>) iterator1.next();
				try {
					deducvalueB+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
						deductionvalueB+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
				} catch (Exception e) {
					// TODO: handle exception
					deductionvalueB+=0;
					deducvalueB=+deducvalueB;
				}
			}
			for (Iterator iterator1 = lstempdetailsC.iterator(); iterator1.hasNext();) {
				Map<String, Object> map = (Map<String, Object>) iterator1.next();
				try {
					deducvalueC+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
						deductionvalueC+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
				} catch (Exception e) {
					// TODO: handle exception
					deductionvalueC+=0;
					deducvalueC=+deducvalueC;
				}
			}
			for (Iterator iterator1 = lstempdetailsD.iterator(); iterator1.hasNext();) {
				Map<String, Object> map = (Map<String, Object>) iterator1.next();
				try {
					deducvalueD+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
						deductionvalueD+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
				} catch (Exception e) {
					// TODO: handle exception
					deductionvalueD+=0;
					deducvalueD=+deducvalueD;
				}
			}
			for (Iterator iterator1 = lstempdetailsE.iterator(); iterator1.hasNext();) {
				Map<String, Object> map = (Map<String, Object>) iterator1.next();
				try {
					deducvalueE+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
						deductionvalueE+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
				} catch (Exception e) {
					// TODO: handle exception
					deductionvalueE+=0;
					deducvalueE=+deducvalueE;
				}
			}
//			DisplayInnerReportModel displayInnerReportModel=new DisplayInnerReportModel();
			DisplayGroupAbstractReportModel obj = new DisplayGroupAbstractReportModel();
            obj.setDeptalldetNm(StringHelperUtils.isNullString(object.getDeptalldetNm()));
            obj.setType(StringHelperUtils.isNullInt(object.getType()));
//            obj.setDeptallowdeducid(StringHelperUtils.isNullInt(object.getDeptallowdeducid()));
            obj.setAllowavalueA(StringHelperUtils.isNullString(String.valueOf(deductionvalueA)));
            obj.setAllowavalueB(StringHelperUtils.isNullString(String.valueOf(deductionvalueB)));
            obj.setAllowavalueC(StringHelperUtils.isNullString(String.valueOf(deductionvalueC)));
            obj.setAllowavalueD(StringHelperUtils.isNullString(String.valueOf(deductionvalueD)));
            obj.setAllowavalueE(StringHelperUtils.isNullString(String.valueOf(deductionvalueE)));
            obj.setTempvalue(StringHelperUtils.isNullString(String.valueOf(deducnvalue1)));
            obj.setTempempty(StringHelperUtils.isNullString(object.getHeadAccountCode()));
			deducAgEdpList1.add(obj);}
		//End Deduction AG
	//	Start Deduction Treasury
		
		
		for (Iterator iterator = deducTyEdpList.iterator(); iterator.hasNext();) {
			DisplayGroupAbstractReportModel object = (DisplayGroupAbstractReportModel) iterator.next();
			String allname=object.getDeptalldetNm();
			double  deducOthEdvaluet=0d;
			double  deducOthEdvalueAt=0d;
			double  deducOthEdvalueBt=0d;
			double  deducOthEdvalueCt=0d;
			double  deducOthEdvalueDt=0d;
			double  deducOthEdvalueEt=0d;			
			
			for (Iterator iterator1 = lstempdetails.iterator(); iterator1.hasNext();) {
				Map<String, Object> map = (Map<String, Object>) iterator1.next();
				try {
					allowavalue1+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
					deducOthEdvaluet+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
				} catch (Exception e) {
					// TODO: handle exception
					allowavalue1+=0;
					deducOthEdvaluet=+deducOthEdvaluet;
				}
			}
			for (Iterator iterator1 = lstempdetailsA.iterator(); iterator1.hasNext();) {
				Map<String, Object> map = (Map<String, Object>) iterator1.next();
				try {
					deducOthEdvalueAt+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
						deducOthEdpAvalue+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
				} catch (Exception e) {
					// TODO: handle exception
					deducOthEdpAvalue+=0;
					deducOthEdvalueAt=+deducOthEdvalueA;
				}
			}
			for (Iterator iterator1 = lstempdetailsB.iterator(); iterator1.hasNext();) {
				Map<String, Object> map = (Map<String, Object>) iterator1.next();
				try {
					deducOthEdvalueBt+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
						deducOthEdpBvalue+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
				} catch (Exception e) {
					// TODO: handle exception
					deducOthEdpBvalue+=0;
					deducOthEdvalueBt=+deducOthEdvalueBt;
				}
			}
			for (Iterator iterator1 = lstempdetailsC.iterator(); iterator1.hasNext();) {
				Map<String, Object> map = (Map<String, Object>) iterator1.next();
				try {
					deducOthEdvalueCt+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
						deducOthEdpCvalue+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
				} catch (Exception e) {
					// TODO: handle exception
					deducOthEdpCvalue+=0;
					deducOthEdvalueCt=+deducOthEdvalueCt;
				}
			}
				for (Iterator iterator1 = lstempdetailsD.iterator(); iterator1.hasNext();) {
				Map<String, Object> map = (Map<String, Object>) iterator1.next();
				try {
					deducOthEdvalueDt+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
					deducOthEdpDvalue+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
				} catch (Exception e) {
					// TODO: handle exception
					deducOthEdpDvalue+=0;
					deducOthEdvalueDt=+deducOthEdvalueDt;
				}
			}
			
			for (Iterator iterator1 = lstempdetailsE.iterator(); iterator1.hasNext();) {
				Map<String, Object> map = (Map<String, Object>) iterator1.next();
				try {
					deducOthEdvalueEt+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
						deducOthEdpEvalue+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
				} catch (Exception e) {
					// TODO: handle exception
					deducOthEdpEvalue+=0;
					deducOthEdvalueEt=+deducOthEdvalueEt;
				}
			}
			
			DisplayGroupAbstractReportModel obj = new DisplayGroupAbstractReportModel();
            obj.setDeptalldetNm(StringHelperUtils.isNullString(object.getDeptalldetNm()));
            obj.setType(StringHelperUtils.isNullInt(object.getType()));
//            obj.setDeptallowdeducid(StringHelperUtils.isNullInt(object.getDeptallowdeducid()));
            obj.setAllowavalueA(StringHelperUtils.isNullString(String.valueOf(deducOthEdvalueAt)));
            obj.setAllowavalueB(StringHelperUtils.isNullString(String.valueOf(deducOthEdvalueBt)));
            obj.setAllowavalueC(StringHelperUtils.isNullString(String.valueOf(deducOthEdvalueCt)));
            obj.setAllowavalueD(StringHelperUtils.isNullString(String.valueOf(deducOthEdvalueDt)));
            obj.setAllowavalueE(StringHelperUtils.isNullString(String.valueOf(deducOthEdvalueEt)));
            obj.setTempvalue(StringHelperUtils.isNullString(String.valueOf(deducOthEdvaluet)));
            obj.setTempempty(StringHelperUtils.isNullString(object.getHeadAccountCode()));
			deducTyEdpList1.add(obj);
		}
		
		//End Deduction try
		//Start Deduction Other
		
		for (Iterator iterator = deducOthEdpList.iterator(); iterator.hasNext();) {
			DisplayGroupAbstractReportModel object = (DisplayGroupAbstractReportModel) iterator.next();
			String allname=object.getDeptalldetNm();
			double  allowavalue=0d;
			double  deducOthEdpvalue1=0d;
			double  deducOthEdpAvalue1=0d;
			double  deducOthEdpBvalue1=0d;
			double  deducOthEdpCvalue1=0d;
			double  deducOthEdpDvalue1=0d;
			double  deducOthEdpEvalue1=0d;
			for (Iterator iterator1 = lstempdetails.iterator(); iterator1.hasNext();) {
				Map<String, Object> map = (Map<String, Object>) iterator1.next();
				try {
					deducOthEdpvalue+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
					deducOthEdvalue+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
				} catch (Exception e) {
					// TODO: handle exception
					deducOthEdpvalue+=0;
					deducOthEdvalue=+deducOthEdvalue;
				}
			}
			for (Iterator iterator1 = lstempdetailsA.iterator(); iterator1.hasNext();) {
				Map<String, Object> map = (Map<String, Object>) iterator1.next();
				try {
					deducOthEdvalueA+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
						deducOthEdpAvalue+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
				} catch (Exception e) {
					// TODO: handle exception
					deducOthEdpAvalue+=0;
					deducOthEdvalueA=+deducOthEdvalueA;
				}
			}
			for (Iterator iterator1 = lstempdetailsB.iterator(); iterator1.hasNext();) {
				Map<String, Object> map = (Map<String, Object>) iterator1.next();
				try {
					deducOthEdvalueB+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
						deducOthEdpBvalue+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
				} catch (Exception e) {
					// TODO: handle exception
					deducOthEdpBvalue+=0;
					deducOthEdvalueB=+deducOthEdvalueB;
				}
			}
			for (Iterator iterator1 = lstempdetailsC.iterator(); iterator1.hasNext();) {
				Map<String, Object> map = (Map<String, Object>) iterator1.next();
				try {
					deducOthEdvalueC+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
						deducOthEdpCvalue+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
				} catch (Exception e) {
					// TODO: handle exception
					deducOthEdpCvalue+=0;
					deducOthEdvalueC=+deducOthEdvalueC;
				}
			}
			for (Iterator iterator1 = lstempdetailsD.iterator(); iterator1.hasNext();) {
				Map<String, Object> map = (Map<String, Object>) iterator1.next();
				try {
					deducOthEdvalueD+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
						deducOthEdpDvalue+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
				} catch (Exception e) {
					// TODO: handle exception
					deducOthEdpDvalue+=0;
					deducOthEdvalueD=+deducOthEdvalueD;
				}
			}
			for (Iterator iterator1 = lstempdetailsE.iterator(); iterator1.hasNext();) {
				Map<String, Object> map = (Map<String, Object>) iterator1.next();
				try {
					deducOthEdvalueE+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
						deducOthEdpEvalue+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
				} catch (Exception e) {
					// TODO: handle exception
					deducOthEdpEvalue+=0;
					deducOthEdvalueE=+deducOthEdvalueE;
				}
			}
//			DisplayInnerReportModel displayInnerReportModel=new DisplayInnerReportModel();
			DisplayGroupAbstractReportModel obj = new DisplayGroupAbstractReportModel();
            obj.setDeptalldetNm(StringHelperUtils.isNullString(object.getDeptalldetNm()));
            obj.setType(StringHelperUtils.isNullInt(object.getType()));
//            obj.setDeptallowdeducid(StringHelperUtils.isNullInt(object.getDeptallowdeducid()));
            obj.setAllowavalueA(StringHelperUtils.isNullString(String.valueOf(deducOthEdpAvalue)));
            obj.setAllowavalueB(StringHelperUtils.isNullString(String.valueOf(deducOthEdpBvalue)));
            obj.setAllowavalueC(StringHelperUtils.isNullString(String.valueOf(deducOthEdpCvalue)));
            obj.setAllowavalueD(StringHelperUtils.isNullString(String.valueOf(deducOthEdpDvalue)));
            obj.setAllowavalueE(StringHelperUtils.isNullString(String.valueOf(deducOthEdpEvalue)));
            obj.setTempvalue(StringHelperUtils.isNullString(String.valueOf(deducOthEdpvalue)));
            obj.setTempempty(StringHelperUtils.isNullString(object.getHeadAccountCode()));
			deducOthEdpList1.add(obj);
		}
		
	//	End Dudection Other 
		
		String billGrpname = commonHomeMethodsService.findbillGrpname(billNumber);
		String officename =commonHomeMethodsService.getOffice(ddoCode);
		
		
		int count=1;
		String word=CashWordConverter.doubleConvert(basicvalue-(deductionvalue+deduction1value+deduction2value+deducOthEdvalue));
		model.addAttribute("totalcount", deducTyEdpList.size()+allowEdpList.size()+deducOthEdpList.size()+deducAgEdpList.size());
		model.addAttribute("allowancecount", allowEdpList.size());
		model.addAttribute("otherdedectioncount", deducOthEdpList.size());
		model.addAttribute("dedecagcount", deducAgEdpList.size());
		model.addAttribute("allowEdpList", allowEdpList1);
		model.addAttribute("deducAgEdpList", deducAgEdpList1);
		model.addAttribute("deducOthEdpList", deducOthEdpList1);
		model.addAttribute("deducTyEdpList", deducTyEdpList1);
		model.addAttribute("lstDeptDataTable1", deptEligibilityForAllowAndDeductService.findDeptEligibilityForAllowAndDeductList());
		model.addAttribute("lstAllDepartment", createAdminOfficeService.lstAllDepartment());
		model.addAttribute("language", locale.getLanguage());
		model.addAttribute("basicvalue", basicvalue);
		model.addAttribute("basicvalueA", basicvalueA);
		model.addAttribute("basicvalueB", basicvalueB);
		model.addAttribute("basicvalueC", basicvalueC);
		model.addAttribute("basicvalueD", basicvalueD);
		model.addAttribute("basicvalueE", basicvalueE);
		model.addAttribute("deducvalueE", deducvalueE);
		model.addAttribute("deducvalueD", deducvalueD);
		model.addAttribute("deducvalueC", deducvalueC);
		model.addAttribute("deducvalueB", deducvalueB);
		model.addAttribute("deducvalueA", deducvalueA);
		model.addAttribute("deducnvalue", deducnvalue);
		model.addAttribute("deductionvalue", deductionvalue);
		model.addAttribute("deduction1value", deducOthEdvalue);
		model.addAttribute("deduction2value", deduction2value);
		model.addAttribute("deducOthEdvalue", allowavalue1);
		model.addAttribute("deducOthEdvalueA", deducOthEdpAvalue);
		model.addAttribute("deducOthEdvalueB", deducOthEdpBvalue);
		model.addAttribute("deducOthEdvalueC", deducOthEdpCvalue);
		model.addAttribute("deducOthEdvalueD", deducOthEdpDvalue);
		model.addAttribute("deducOthEdvalueE", deducOthEdpEvalue);
		
		model.addAttribute("currmonyer", monname+" "+curryear);
		model.addAttribute("ddoname", messages.getUserName());
		model.addAttribute("officename", officename);
		model.addAttribute("billGrpname", billGrpname);
		model.addAttribute("billNumber", billNumber);
		model.addAttribute("word", word);
		model.addAttribute("ofcname", officeDetails);
		model.addAttribute("systemdate", sdf.format(new Date()));
		model.addAttribute("year",monname+" "+curryear);
		////model.addAttribute("lstDeptDataTable", mstEmployeeService.findAllEmployees());

		//  end

		return "views/reports/groupAbstractreport";
	}

	
	@GetMapping("/viewgroupAbstractPDF/{billNumber}")
	public String viewgroupAbstractPDFReport(@ModelAttribute("displayGroupAbstractReportModel") DisplayGroupAbstractReportModel displayGroupAbstractReportModel,@PathVariable Long billNumber, Model model, Locale locale, HttpSession session,HttpServletResponse response) {

		String message = (String) model.asMap().get("message");
		

		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");


				long billNo = (long) billNumber;
		String month = "";
		String officeDetails = commonHomeMethodsService.getOffice(messages.getUserName());
		int year = 2020;
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
		Map objectArgs = new HashMap();
		objectArgs.put("paybillNo", billNo);// paramerter
		objectArgs.put("month", month); // parameter
		objectArgs.put("year", year); // parameter
		List lsouterrepheaddtls = displayGroupAbstractReportService.getReportHeaderDetails(String.valueOf(billNo));
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
		}
		
		BigInteger currmonth = BigInteger.valueOf(paymonth);
		BigInteger yearcurr = BigInteger.valueOf(payyear);
		
		List<Object[]>  monthinfo = paybillGenerationTrnService.findmonthinfo(currmonth);
		for (Object[] monthLst : monthinfo) {
			// String empName;
			monname = monthLst[1].toString();
			
		}
		
		List<Object[]>  yearinfo = paybillGenerationTrnService.findyearinfo(yearcurr);
		for (Object[] yearLst : yearinfo) {
			// String empName;
			curryear = yearLst[1].toString();
			finyear = yearLst[10].toString();
			
		}
		SimpleDateFormat sdf =new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String ddoname=null;
		String orgname=null;		
		String ofcname=null;
		String premonname=null;
		Long regid=null;
		Long ofcid=null;
		List<Object[]>  ddoinfo = paybillGenerationTrnService.findDDOinfo(messages.getUserName());

		for (Object[] objddoLst : ddoinfo) {
			// String empName;
			regid = Long.parseLong(objddoLst[0].toString());
			 ddoname = objddoLst[1].toString();
			 ofcid = Long.parseLong(objddoLst[2].toString());
			
		}
		List<Object[]>  regIdinfo = paybillGenerationTrnService.findregIdinfo(regid);
		for (Object[] objorgLst : regIdinfo) {
			// String empName;
			orgname = objorgLst[2].toString();
			
		}
		
		List<Object[]>  ofcIdinfo = paybillGenerationTrnService.findofcIdinfo(ofcid);
		for (Object[] ofcLst : ofcIdinfo) {
			// String empName;
			ofcname = ofcLst[2].toString();
			
		}
		model.addAttribute("paybillNo", billNo);
		model.addAttribute("month", monname);
		model.addAttribute("year", curryear);
		model.addAttribute("totaldeduction", displayGroupAbstractReportService.getTotalDeductionGroupAbstract((double)billNo));

		//  start
		List<DisplayGroupAbstractReportModel> allowEdpList = new ArrayList<DisplayGroupAbstractReportModel>();// edpDao.getAllowCompoMpgData(locId);
		List<DisplayGroupAbstractReportModel> deducAgEdpList = new ArrayList<DisplayGroupAbstractReportModel>();// edpDao.getAGDeducCompoMpgData(locId);
		List<DisplayGroupAbstractReportModel> deducTyEdpList = new ArrayList<DisplayGroupAbstractReportModel>();// edpDao.getTRDeducCompoMpgData(locId);
		List<DisplayGroupAbstractReportModel> deducOthEdpList = new ArrayList<DisplayGroupAbstractReportModel>();// changes for other (nps)

		System.out.println(messages.getUserName());
		
		List<DisplayGroupAbstractReportModel> allEdpList = displayGroupAbstractReportService.getAllDataForAbstractnew(messages.getUserName(),billNumber);
		for (int i = 0; i < allEdpList.size(); i++) {

				if (allEdpList.get(i).getType() == 1) { // allowance
					allowEdpList.add(allEdpList.get(i));
				}else
				if (allEdpList.get(i).getType() == 2) { 
					
					if(allEdpList.get(i).getDeptallowdeducid()==36||allEdpList.get(i).getDeptallowdeducid()==37||allEdpList.get(i).getDeptallowdeducid()==38||allEdpList.get(i).getDeptallowdeducid()==39)
					deducAgEdpList.add(allEdpList.get(i)); //Deductions Adj. By CAFO/Supri./Admin.
					else
					deducTyEdpList.add(allEdpList.get(i)); //Adjust by Treasury
				}else {
					deducTyEdpList.add(allEdpList.get(i));
				}
		}
		
		List<Map<String,Object>> lstempdetails=displayGroupAbstractReportService.getempDetailsGroupAbstract(String.valueOf(billNo));
		List<Map<String,Object>> lstempdetailsA=displayGroupAbstractReportService.getempDetailsGroupAbstractA(String.valueOf(billNo));
		List<Map<String,Object>> lstempdetailsB=displayGroupAbstractReportService.getempDetailsGroupAbstractB(String.valueOf(billNo));
		List<Map<String,Object>> lstempdetailsC=displayGroupAbstractReportService.getempDetailsGroupAbstractC(String.valueOf(billNo));
		List<Map<String,Object>> lstempdetailsD=displayGroupAbstractReportService.getempDetailsGroupAbstractD(String.valueOf(billNo));
		List<Map<String,Object>> lstempdetailsE=displayGroupAbstractReportService.getempDetailsGroupAbstractE(String.valueOf(billNo));
		List<DisplayGroupAbstractReportModel> allowEdpList1 = new ArrayList();// edpDao.getAllowCompoMpgData(locId);
		List<DisplayGroupAbstractReportModel> deducAgEdpList1 = new ArrayList();// edpDao.getAGDeducCompoMpgData(locId);
		List<DisplayGroupAbstractReportModel> deducTyEdpList1 = new ArrayList();// edpDao.getTRDeducCompoMpgData(locId);
		List<DisplayGroupAbstractReportModel> deducOthEdpList1 = new ArrayList();// changes for other (nps)
		List<DisplayGroupAbstractReportModel> lstObj = new ArrayList<>();
		int empsize=lstempdetails.size();
		double  basicvalue=0d;
		double  basicvalueA=0d;
		double  basicvalueB=0d;
		double  basicvalueC=0d;
		double  basicvalueD=0d;
		double  basicvalueE=0d;
		double  deductionvalue=0d;
		double  deductionvalueA=0d;
		double  deductionvalueB=0d;
		double  deductionvalueC=0d;
		double  deductionvalueD=0d;
		double  deductionvalueE=0d;
		double  deducOthEdvalue=0d;
		double  deducOthEdvalueA=0d;
		double  deducOthEdvalueB=0d;
		double  deducOthEdvalueC=0d;
		double  deducOthEdvalueD=0d;
		double  deducOthEdvalueE=0d;
		double  deducnvalue=0d;
		double  deducvalueA=0d;
		double  deducvalueB=0d;
		double  deducvalueC=0d;
		double  deducvalueD=0d;
		double  deducvalueE=0d;
		double  deduction1value=0d;
		double  deduction2value=0d;
		for (Iterator iterator = allowEdpList.iterator(); iterator.hasNext();) {
			DisplayGroupAbstractReportModel object = (DisplayGroupAbstractReportModel) iterator.next();
			String allname=object.getDeptalldetNm();
			String head=object.getHeadAccountCode();
			double  allowavalue=0d;
			double  allowavalueA=0d;
			double  allowavalueB=0d;
			double  allowavalueC=0d;
			double  allowavalueD=0d;
			double  allowavalueE=0d;
			
			
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
			for (Iterator iterator1 = lstempdetailsA.iterator(); iterator1.hasNext();) {
				Map<String, Object> map = (Map<String, Object>) iterator1.next();
				try {
						basicvalueA+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
						allowavalueA+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
				} catch (Exception e) {
					// TODO: handle exception
					allowavalueA+=0;
					basicvalueA=+basicvalueA;
				}
			}
			for (Iterator iterator1 = lstempdetailsB.iterator(); iterator1.hasNext();) {
				Map<String, Object> map = (Map<String, Object>) iterator1.next();
				try {
						basicvalueB+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
						allowavalueB+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
				} catch (Exception e) {
					// TODO: handle exception
					allowavalueB+=0;
					basicvalueB=+basicvalueB;
				}
			}
			for (Iterator iterator1 = lstempdetailsC.iterator(); iterator1.hasNext();) {
				Map<String, Object> map = (Map<String, Object>) iterator1.next();
				try {
						basicvalueC+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
						allowavalueC+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
				} catch (Exception e) {
					// TODO: handle exception
					allowavalueC+=0;
					basicvalueC=+basicvalueC;
				}
			}
			for (Iterator iterator1 = lstempdetailsD.iterator(); iterator1.hasNext();) {
				Map<String, Object> map = (Map<String, Object>) iterator1.next();
				try {
						basicvalueD+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
						allowavalueD+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
				} catch (Exception e) {
					// TODO: handle exception
					allowavalueD+=0;
					basicvalueD=+basicvalueD;
				}
			}
			for (Iterator iterator1 = lstempdetailsE.iterator(); iterator1.hasNext();) {
				Map<String, Object> map = (Map<String, Object>) iterator1.next();
				try {
						basicvalueE+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
						allowavalueE+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
				} catch (Exception e) {
					// TODO: handle exception
					allowavalueE+=0;
					basicvalueE=+basicvalueE;
				}
			}
			
			
			
//			DisplayInnerReportModel displayInnerReportModel=new DisplayInnerReportModel();
			DisplayGroupAbstractReportModel obj = new DisplayGroupAbstractReportModel();
            obj.setDeptalldetNm(StringHelperUtils.isNullString(object.getDeptalldetNm()));
            obj.setType(StringHelperUtils.isNullInt(object.getType()));
//            obj.setDeptallowdeducid(StringHelperUtils.isNullInt(object.getDeptallowdeducid()));
            obj.setAllowavalueA(StringHelperUtils.isNullString(String.valueOf(allowavalueA)));
            obj.setAllowavalueB(StringHelperUtils.isNullString(String.valueOf(allowavalueB)));
            obj.setAllowavalueC(StringHelperUtils.isNullString(String.valueOf(allowavalueC)));
            obj.setAllowavalueD(StringHelperUtils.isNullString(String.valueOf(allowavalueD)));
            obj.setAllowavalueE(StringHelperUtils.isNullString(String.valueOf(allowavalueE)));
            obj.setTempvalue(StringHelperUtils.isNullString(String.valueOf(allowavalue)));
            obj.setTempempty(StringHelperUtils.isNullString(object.getHeadAccountCode()));
			allowEdpList1.add(obj);
		}
		
		
		for (Iterator iterator = deducAgEdpList.iterator(); iterator.hasNext();) {
			DisplayGroupAbstractReportModel object = (DisplayGroupAbstractReportModel) iterator.next();
			String allname=object.getDeptalldetNm();
			double  allowavalue=0d;
			for (Iterator iterator1 = lstempdetails.iterator(); iterator1.hasNext();) {
				Map<String, Object> map = (Map<String, Object>) iterator1.next();
				try {
					deducnvalue+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
					deductionvalue+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
				} catch (Exception e) {
					// TODO: handle exception
					deductionvalue+=0;
					deducnvalue=+deducnvalue;
				}
			}
			for (Iterator iterator1 = lstempdetailsA.iterator(); iterator1.hasNext();) {
				Map<String, Object> map = (Map<String, Object>) iterator1.next();
				try {
					deducvalueA+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
						deductionvalueA+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
				} catch (Exception e) {
					// TODO: handle exception
					deductionvalueA+=0;
					deducvalueA=+deducvalueA;
				}
			}
			for (Iterator iterator1 = lstempdetailsB.iterator(); iterator1.hasNext();) {
				Map<String, Object> map = (Map<String, Object>) iterator1.next();
				try {
					deducvalueB+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
						deductionvalueB+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
				} catch (Exception e) {
					// TODO: handle exception
					deductionvalueB+=0;
					deducvalueB=+deducvalueB;
				}
			}
			for (Iterator iterator1 = lstempdetailsC.iterator(); iterator1.hasNext();) {
				Map<String, Object> map = (Map<String, Object>) iterator1.next();
				try {
					deducvalueC+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
						deductionvalueC+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
				} catch (Exception e) {
					// TODO: handle exception
					deductionvalueC+=0;
					deducvalueC=+deducvalueC;
				}
			}
			for (Iterator iterator1 = lstempdetailsD.iterator(); iterator1.hasNext();) {
				Map<String, Object> map = (Map<String, Object>) iterator1.next();
				try {
					deducvalueD+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
						deductionvalueD+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
				} catch (Exception e) {
					// TODO: handle exception
					deductionvalueD+=0;
					deducvalueD=+deducvalueD;
				}
			}
			for (Iterator iterator1 = lstempdetailsE.iterator(); iterator1.hasNext();) {
				Map<String, Object> map = (Map<String, Object>) iterator1.next();
				try {
					deducvalueE+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
						deductionvalueE+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
				} catch (Exception e) {
					// TODO: handle exception
					deductionvalueE+=0;
					deducvalueE=+deducvalueE;
				}
			}
//			DisplayInnerReportModel displayInnerReportModel=new DisplayInnerReportModel();
			DisplayGroupAbstractReportModel obj = new DisplayGroupAbstractReportModel();
            obj.setDeptalldetNm(StringHelperUtils.isNullString(object.getDeptalldetNm()));
            obj.setType(StringHelperUtils.isNullInt(object.getType()));
//            obj.setDeptallowdeducid(StringHelperUtils.isNullInt(object.getDeptallowdeducid()));
            obj.setAllowavalueA(StringHelperUtils.isNullString(String.valueOf(deductionvalueA)));
            obj.setAllowavalueB(StringHelperUtils.isNullString(String.valueOf(deductionvalueB)));
            obj.setAllowavalueC(StringHelperUtils.isNullString(String.valueOf(deductionvalueC)));
            obj.setAllowavalueD(StringHelperUtils.isNullString(String.valueOf(deductionvalueD)));
            obj.setAllowavalueE(StringHelperUtils.isNullString(String.valueOf(deductionvalueE)));
            obj.setTempvalue(StringHelperUtils.isNullString(String.valueOf(deductionvalue)));
            obj.setTempempty(StringHelperUtils.isNullString(object.getHeadAccountCode()));
			deducAgEdpList1.add(obj);
		}
		
		for (Iterator iterator = deducTyEdpList.iterator(); iterator.hasNext();) {
			DisplayGroupAbstractReportModel object = (DisplayGroupAbstractReportModel) iterator.next();
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
			DisplayGroupAbstractReportModel obj = new DisplayGroupAbstractReportModel();
            obj.setDeptalldetNm(StringHelperUtils.isNullString(object.getDeptalldetNm()));
            obj.setType(StringHelperUtils.isNullInt(object.getType()));
            obj.setDeptallowdeducid(StringHelperUtils.isNullInt(object.getDeptallowdeducid()));
            obj.setTempvalue(StringHelperUtils.isNullString(String.valueOf(allowavalue)));
            obj.setTempempty(StringHelperUtils.isNullString(object.getHeadAccountCode()));
			deducTyEdpList1.add(obj);
		}
		
		for (Iterator iterator = deducOthEdpList.iterator(); iterator.hasNext();) {
			DisplayGroupAbstractReportModel object = (DisplayGroupAbstractReportModel) iterator.next();
			String allname=object.getDeptalldetNm();
			double  allowavalue=0d;
			double  deducOthEdpvalue=0d;
			double  deducOthEdpAvalue=0d;
			double  deducOthEdpBvalue=0d;
			double  deducOthEdpCvalue=0d;
			double  deducOthEdpDvalue=0d;
			double  deducOthEdpEvalue=0d;
			for (Iterator iterator1 = lstempdetails.iterator(); iterator1.hasNext();) {
				Map<String, Object> map = (Map<String, Object>) iterator1.next();
				try {
					deducOthEdpvalue+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
					deducOthEdvalue+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
				} catch (Exception e) {
					// TODO: handle exception
					deducOthEdpvalue+=0;
					deducOthEdvalue=+deducOthEdvalue;
				}
			}
			for (Iterator iterator1 = lstempdetailsA.iterator(); iterator1.hasNext();) {
				Map<String, Object> map = (Map<String, Object>) iterator1.next();
				try {
					deducOthEdvalueA+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
						deducOthEdpAvalue+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
				} catch (Exception e) {
					// TODO: handle exception
					deducOthEdpAvalue+=0;
					deducOthEdvalueA=+deducOthEdvalueA;
				}
			}
			for (Iterator iterator1 = lstempdetailsB.iterator(); iterator1.hasNext();) {
				Map<String, Object> map = (Map<String, Object>) iterator1.next();
				try {
					deducOthEdvalueB+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
						deducOthEdpBvalue+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
				} catch (Exception e) {
					// TODO: handle exception
					deducOthEdpBvalue+=0;
					deducOthEdvalueB=+deducOthEdvalueB;
				}
			}
			for (Iterator iterator1 = lstempdetailsC.iterator(); iterator1.hasNext();) {
				Map<String, Object> map = (Map<String, Object>) iterator1.next();
				try {
					deducOthEdvalueC+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
						deducOthEdpCvalue+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
				} catch (Exception e) {
					// TODO: handle exception
					deducOthEdpCvalue+=0;
					deducOthEdvalueC=+deducOthEdvalueC;
				}
			}
			for (Iterator iterator1 = lstempdetailsD.iterator(); iterator1.hasNext();) {
				Map<String, Object> map = (Map<String, Object>) iterator1.next();
				try {
					deducOthEdvalueD+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
						deducOthEdpDvalue+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
				} catch (Exception e) {
					// TODO: handle exception
					deducOthEdpDvalue+=0;
					deducOthEdvalueD=+deducOthEdvalueD;
				}
			}
			for (Iterator iterator1 = lstempdetailsE.iterator(); iterator1.hasNext();) {
				Map<String, Object> map = (Map<String, Object>) iterator1.next();
				try {
					deducOthEdvalueE+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
						deducOthEdpEvalue+=Double.valueOf(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
				} catch (Exception e) {
					// TODO: handle exception
					deducOthEdpEvalue+=0;
					deducOthEdvalueE=+deducOthEdvalueE;
				}
			}
//			DisplayInnerReportModel displayInnerReportModel=new DisplayInnerReportModel();
			DisplayGroupAbstractReportModel obj = new DisplayGroupAbstractReportModel();
            obj.setDeptalldetNm(StringHelperUtils.isNullString(object.getDeptalldetNm()));
            obj.setType(StringHelperUtils.isNullInt(object.getType()));
//            obj.setDeptallowdeducid(StringHelperUtils.isNullInt(object.getDeptallowdeducid()));
            obj.setAllowavalueA(StringHelperUtils.isNullString(String.valueOf(deducOthEdpAvalue)));
            obj.setAllowavalueB(StringHelperUtils.isNullString(String.valueOf(deducOthEdpBvalue)));
            obj.setAllowavalueC(StringHelperUtils.isNullString(String.valueOf(deducOthEdpCvalue)));
            obj.setAllowavalueD(StringHelperUtils.isNullString(String.valueOf(deducOthEdpDvalue)));
            obj.setAllowavalueE(StringHelperUtils.isNullString(String.valueOf(deducOthEdpEvalue)));
            obj.setTempvalue(StringHelperUtils.isNullString(String.valueOf(deducOthEdpvalue)));
            obj.setTempempty(StringHelperUtils.isNullString(object.getHeadAccountCode()));
			deducOthEdpList1.add(obj);
		}
		
		int count=1;
		String word=CashWordConverter.doubleConvert(basicvalue-(deductionvalue+deduction1value+deduction2value));
		model.addAttribute("totalcount", deducTyEdpList.size()+allowEdpList.size()+deducOthEdpList.size()+deducAgEdpList.size());
		model.addAttribute("allowancecount", allowEdpList.size());
		model.addAttribute("otherdedectioncount", deducOthEdpList.size());
		model.addAttribute("dedecagcount", deducAgEdpList.size());
		model.addAttribute("allowEdpList", allowEdpList1);
		model.addAttribute("deducAgEdpList", deducAgEdpList1);
		model.addAttribute("deducOthEdpList", deducOthEdpList1);
		model.addAttribute("deducTyEdpList", deducTyEdpList1);
		model.addAttribute("lstDeptDataTable1", deptEligibilityForAllowAndDeductService.findDeptEligibilityForAllowAndDeductList());
		model.addAttribute("lstAllDepartment", createAdminOfficeService.lstAllDepartment());
		model.addAttribute("language", locale.getLanguage());
		model.addAttribute("basicvalue", basicvalue);
		model.addAttribute("basicvalueA", basicvalueA);
		model.addAttribute("basicvalueB", basicvalueB);
		model.addAttribute("basicvalueC", basicvalueC);
		model.addAttribute("basicvalueD", basicvalueD);
		model.addAttribute("basicvalueE", basicvalueE);
		model.addAttribute("deducvalueE", deducvalueE);
		model.addAttribute("deducvalueD", deducvalueD);
		model.addAttribute("deducvalueC", deducvalueC);
		model.addAttribute("deducvalueB", deducvalueB);
		model.addAttribute("deducvalueA", deducvalueA);
		model.addAttribute("deducnvalue", deducnvalue);
		model.addAttribute("deductionvalue", deductionvalue);
		model.addAttribute("deduction1value", deduction1value);
		model.addAttribute("deduction2value", deduction2value);
		model.addAttribute("deducOthEdvalue", deducOthEdvalue);
		model.addAttribute("deducOthEdvalueA", deducOthEdvalueA);
		model.addAttribute("deducOthEdvalueB", deducOthEdvalueB);
		model.addAttribute("deducOthEdvalueC", deducOthEdvalueC);
		model.addAttribute("deducOthEdvalueD", deducOthEdvalueD);
		model.addAttribute("deducOthEdvalueE", deducOthEdvalueE);
		model.addAttribute("word", word);
		model.addAttribute("ofcname", ofcname);
		model.addAttribute("systemdate", sdf.format(new Date()));
		model.addAttribute("year",monname+" "+curryear);
		///model.addAttribute("lstDeptDataTable", mstEmployeeService.findAllEmployees());

		//  end
		 Map<String,String> data = new HashMap<String,String>();
		try {
			pdfGenaratorUtil.createPdf("views/report/groupAbstractreport",model.asMap(),response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return "views/report/groupAbstractreport";
	}


}
