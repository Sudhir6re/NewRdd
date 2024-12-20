package com.mahait.gov.in.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.MstEmployeeModel;
import com.mahait.gov.in.model.NewRegDDOModel;
import com.mahait.gov.in.service.CommonHomeMethodsService;
import com.mahait.gov.in.service.EmpChangeDetailsService;
import com.mahait.gov.in.service.LocationMasterService;
import com.mahait.gov.in.service.MstBankService;
import com.mahait.gov.in.service.MstDesignationService;
import com.mahait.gov.in.service.MstEmployeeService;
import com.mahait.gov.in.service.NewRegDDOService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/ddo")
public class NewRegDDOController extends BaseController {

	@Autowired
	CommonHomeMethodsService commonHomeMethodsService;

	@Autowired
	NewRegDDOService newRegDDOService;

	@Autowired
	EmpChangeDetailsService empChangeDetailsService;

	@Autowired
	LocationMasterService locationMasterService;

	@Autowired
	MstEmployeeService mstEmployeeService;

	@Autowired
	MstDesignationService mstDesignationService;

	@Autowired
	MstBankService mstBankService;

	List<NewRegDDOModel> emplist = new ArrayList<>();

	@GetMapping("/viewFormsForwardedByAsstZpRepoDDO")
	public String viewFormsForwardedByAsstZpRepoDDO(@ModelAttribute("newRegDDOModel") NewRegDDOModel newRegDDOModel,
			Model model, Locale locale, HttpSession session) {
		/*
		 * UserInfo messages = (UserInfo) session.getAttribute("MY_SESSION_MESSAGES");
		 * int levelRoleVal = messages.getRole_id(); List<TopicModel> menuList = new
		 * ArrayList<>(); List<TopicModel> subMenuList = new ArrayList<>();
		 * 
		 * menuList =
		 * commonHomeMethodsService.findMenuNameByRoleID(levelRoleVal,locale.getLanguage
		 * ()); subMenuList =
		 * commonHomeMethodsService.findSubMenuByRoleID(levelRoleVal,locale.getLanguage(
		 * ));
		 * 
		 * model.addAttribute("menuList", menuList); model.addAttribute("subMenuList",
		 * subMenuList);
		 * 
		 * String message=(String) model.asMap().get("message");
		 * 
		 * 
		 * //model.addAttribute("", getDesignation()) emplist =
		 * empWiseCityClassService.findAllEmployee(messages.getUserName());
		 * newRegDDOModel.setEmplist(emplist);
		 * 
		 * 
		 * model.addAttribute("lstDDOOffice",newRegDDOService.findLvl1DDOCode(messages.
		 * getUserName()));
		 * model.addAttribute("empLst",newRegDDOService.findEmpLst(messages.getUserName(
		 * )));
		 */
		/*
		 * model.addAttribute("lstDistrictLst",empWiseCityClassService.lstGetAllDistrict
		 * ());
		 * model.addAttribute("lstTaluka",empWiseCityClassService.lstGetAllTaluka());
		 * ///model.addAttribute("lstTalukaLst",empWiseCityClassService.
		 * findCityClasssLst());
		 */ model.addAttribute("newRegDDOModel", newRegDDOModel);
		/// model.addAttribute("message", message);

		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(model, messages);
		return "/views/NewRegApprovedForms";

	}

	@RequestMapping(value = "/popUpEmpDtls/{employeeId}", method = { RequestMethod.GET, RequestMethod.POST })
	public String approveEmployeeConfiguration(/*
												 * @ModelAttribute("mstEmployeeEntity") MstEmployeeEntity
												 * mstEmployeeEntity,
												 */
			Model model, Locale locale, HttpSession session,
			@PathVariable int employeeId) {/*
											 * String message = (String) model.asMap().get("message");
											 * 
											 * UserInfo messages = (UserInfo)
											 * session.getAttribute("MY_SESSION_MESSAGES"); //
											 * model.addAttribute("brokenPeriodModel", brokenPeriodModel);
											 * List<TopicModel> menuList = new ArrayList<>(); List<TopicModel>
											 * subMenuList = new ArrayList<>(); //List<ChangeBasicDtlsModel>
											 * lstChangeBasic = new ArrayList<>();
											 * 
											 * menuList =
											 * commonHomeMethodsService.findMenuNameByRoleID(messages.getRole_id(),
											 * locale.getLanguage()); subMenuList =
											 * commonHomeMethodsService.findSubMenuByRoleID(messages.getRole_id(),
											 * locale.getLanguage()); //lstChangeBasic=
											 * empchangeBasicDtls.findEmpChangeBasicDtls(messages.getUserName());
											 * model.addAttribute("menuList", menuList);
											 * model.addAttribute("subMenuList", subMenuList);
											 * //model.addAttribute("lstChangeBasic", lstChangeBasic);
											 * 
											 * 
											 * if (message != null && message.equals("SUCCESS")) { if (locale != null &&
											 * locale.getLanguage().equalsIgnoreCase("en")) { model =
											 * CommonUtils.initModel(CommonConstants.Message.SAVEDRAFT, STATUS.SUCCESS,
											 * model); } else { model =
											 * CommonUtils.initModel(CommonConstants.Message.ADDED_MARATHI,
											 * STATUS.SUCCESS, model); } } model.addAttribute("language",
											 * locale.getLanguage()); mstEmployeeEntity =
											 * empChangeDetailsService.getEmployeeData(employeeId);
											 * 
											 * if(mstEmployeeEntity.getGender()!=null) {
											 * if(mstEmployeeEntity.getGender()=='M') {
											 * mstEmployeeEntity.setGender('1'); }else
											 * if(mstEmployeeEntity.getGender()=='F') {
											 * mstEmployeeEntity.setGender('2'); }else
											 * if(mstEmployeeEntity.getGender()=='T') {
											 * mstEmployeeEntity.setGender('3'); }else {
											 * mstEmployeeEntity.setGender(mstEmployeeEntity.getGender()); } }
											 * 
											 * if(mstEmployeeEntity.getBasicPay()!=null) {
											 * mstEmployeeEntity.setBasicPay(mstEmployeeEntity.getBasicPay()); }
											 * if(mstEmployeeEntity.getSevenPcBasic()!=null)
											 * mstEmployeeEntity.setBasicPay(mstEmployeeEntity.getSevenPcBasic().
											 * doubleValue());
											 * 
											 * if(mstEmployeeEntity.getPayCommissionCode()==8) {
											 * mstEmployeeEntity.setBasicPay(mstEmployeeEntity.getSevenPcBasic().
											 * doubleValue()); } if(mstEmployeeEntity.getPayCommissionCode()==2) {
											 * mstEmployeeEntity.setBasicPay(mstEmployeeEntity.getBasicPay()); }
											 * if(mstEmployeeEntity.getUidNo()!=null) {
											 * 
											 * mstEmployeeEntity.setUidNo1(Long.valueOf(mstEmployeeEntity.getUidNo().
											 * toString().substring(0,3)));
											 * mstEmployeeEntity.setUidNo2(Long.valueOf(mstEmployeeEntity.getUidNo().
											 * toString().substring(4,7)));
											 * mstEmployeeEntity.setUidNo3(Long.valueOf(mstEmployeeEntity.getUidNo().
											 * toString().substring(8,11))); } if (mstEmployeeEntity.getUidNo() != null)
											 * { String uidNoArray =
											 * Long.valueOf(mstEmployeeEntity.getUidNo()).toString();
											 * 
											 * System.out.println("---------UID-----------"+uidNoArray); if
											 * (uidNoArray.length() == 12) {
											 * 
											 * System.out.println("test uid"+uidNoArray.substring(8,12));
											 * 
											 * 
											 * 
											 * Long u1=Long.valueOf(uidNoArray.substring(0,4)); Long
											 * u2=Long.valueOf(uidNoArray.substring(4,8)); Long
											 * u3=Long.valueOf(uidNoArray.substring(8,12));
											 * 
											 * if(u1.toString().length()<=4) { String u11 = String.format("%04d", u1);
											 * // Use %0nd where n is the total width, adjust as needed. }
											 * 
											 * if(u2.toString().length()<=4) { String formattedResult =
											 * String.format("%012d", u2); // Use %0nd where n is the total width,
											 * adjust as needed. }
											 * 
											 * 
											 * if(u3.toString().length()<=4) { String formattedResult =
											 * String.format("%012d", u3); // Use %0nd where n is the total width,
											 * adjust as needed. }
											 * 
											 * 
											 * mstEmployeeEntity.setUidNo1(uidNoArray.substring(0,4));
											 * mstEmployeeEntity.setUidNo2(uidNoArray.substring(4,8));
											 * mstEmployeeEntity.setUidNo3(uidNoArray.substring(8,12));
											 * 
											 * } } BigInteger b= mstEmployeeEntity.getCadreCode(); Integer
											 * cadreCode=b.intValue();
											 * 
											 * mstEmployeeEntity.setPostdetailid(mstEmployeeEntity.getPostdetailid());
											 * 
											 * List<Object[]> employeeConfigurationService =
											 * mstEmployeeService.findAllGroupAndSuperAnnuationAge(cadreCode);
											 * 
											 * int superAnnAge=0; for (Object[] objects : employeeConfigurationService)
											 * {
											 * 
											 * BigDecimal age = (BigDecimal) objects[6];
											 * 
											 * superAnnAge =age.intValue(); }
											 * mstEmployeeEntity.setSuperAnnAge(superAnnAge);
											 * 
											 * List<DDOScreenModel> lstDepartment =
											 * mstEmployeeService.findDDOScreenDataTable(locale.getLanguage(),
											 * messages.getUserName().toString());
											 * 
											 * List<MstPayCommissionEntity> lstddcPayCommission =
											 * mstDesignationService.findAllPayCommission(); List<Object[]>
											 * payscalelevel = new ArrayList<Object[]>(); List<Object[]> lstsvnbasicpay
											 * = new ArrayList<Object[]>(); List<Object[]> listPfSeries = new
											 * ArrayList<Object[]>();
											 * 
											 * if(mstEmployeeEntity.getMstGpfDetailsEntity()!=null) listPfSeries=
											 * mstEmployeeService.getPfSeries(mstEmployeeEntity.getMstGpfDetailsEntity()
											 * .getAccountmaintainby());
											 * 
											 * if (mstEmployeeEntity.getPayCommissionCode() != null &&
											 * !mstEmployeeEntity.getPayCommissionCode().equals(8)) payscalelevel =
											 * mstEmployeeService.findEmployeeConfigurationGetSixPayScale(2500341); else
											 * payscalelevel =
											 * mstEmployeeService.findEmployeeConfigurationGetpayscale(mstEmployeeEntity
											 * .getPayCommissionCode());
											 * 
											 * if(mstEmployeeEntity.getPayCommissionCode().equals(8)) { payscalelevel =
											 * mstEmployeeService.findEmployeeConfigurationGetpayscale(8); } // if
											 * (mstEmployeeEntity.getPayCommissionCode() != null) // payscalelevel =
											 * mstEmployeeService.findEmployeeConfigurationGetpayscale(mstEmployeeEntity
											 * .getPayCommissionCode().intValue());
											 * 
											 * 
											 * if(mstEmployeeEntity.getPayScaleCode()!=null &&
											 * mstEmployeeEntity.getPayScaleCode().intValue()!=0) { lstsvnbasicpay =
											 * mstEmployeeService
											 * .findEmployeeConfigurationGetsvnbasicpayChangedetails(mstEmployeeEntity.
											 * getPayScaleCode().toString(),mstEmployeeEntity.getSevenPcBasic()); }
											 * 
											 * 
											 * if(mstEmployeeEntity.getPayCommissionCode()==8 &&
											 * mstEmployeeEntity.getSevenPcLevel()!=null &&
											 * mstEmployeeEntity.getSevenPcLevel().intValue()!=0) { lstsvnbasicpay =
											 * mstEmployeeService
											 * .findEmployeeConfigurationGetsvnbasicpayChangedetails(mstEmployeeEntity.
											 * getSevenPcLevel().toString(),mstEmployeeEntity.getSevenPcBasic()); }
											 * 
											 * model.addAttribute("lstCurrentPost",
											 * empChangeDetailsService.GetCurrentPostDesigation(mstEmployeeEntity.
											 * getPostdetailid()));
											 * 
											 * 
											 * 
											 * model.addAttribute("lstddcPayCommission", lstddcPayCommission);
											 * 
											 * 
											 * model.addAttribute("lstpfSeries", listPfSeries);
											 * 
											 * 
											 * 
											 * model.addAttribute("lstCadreMst",
											 * mstEmployeeService.getCadreMstData(locale.getLanguage()));
											 * model.addAttribute("lstGISGroup", mstEmployeeService.getGISGroup());
											 * model.addAttribute("lstCommonMstSalutation", commonHomeMethodsService
											 * .findCommonMstByCommonCode(CommonConstants.COMMONMSTTABLE.
											 * COMMONCODE_SALUTATION)); model.addAttribute("lstCommonMstGIS",
											 * commonHomeMethodsService
											 * .findCommonMstByCommonCode(CommonConstants.COMMONMSTTABLE.COMMONCODE_GIS)
											 * );
											 * 
											 * 
											 * List<MstStateModel> listStatemdl = new ArrayList<MstStateModel>();
											 * List<Object[]> listState = locationMasterService.findAllStates(1); for
											 * (Iterator iterator = listState.iterator(); iterator.hasNext();) {
											 * Object[] objects = (Object[]) iterator.next(); MstStateModel
											 * mstStateModel = new MstStateModel();
											 * mstStateModel.setStateCode(Integer.parseInt(String.valueOf(objects[1])));
											 * mstStateModel.setStateNameEn(String.valueOf(objects[2]));
											 * listStatemdl.add(mstStateModel); }
											 * 
											 * List<MstDistrictModel> listDistrictemdl = new
											 * ArrayList<MstDistrictModel>();
											 * 
											 * try { List<Object[]> listDistrict =
											 * locationMasterService.findAllDistricts(mstEmployeeEntity.getStateCode());
											 * // logger.info("distric code list size="+listDistrict.size()); for
											 * (Iterator iterator = listDistrict.iterator(); iterator.hasNext();) {
											 * Object[] objects = (Object[]) iterator.next(); MstDistrictModel
											 * mstDistrictModel = new MstDistrictModel();
											 * mstDistrictModel.setState(String.valueOf(objects[3]));
											 * mstDistrictModel.setStateName(String.valueOf(objects[4]));
											 * listDistrictemdl.add(mstDistrictModel); //
											 * logger.info("distric code list object4="+String.valueOf(objects[4])); }
											 * 
											 * } catch (Exception e) { // TODO: handle exception // e.printStackTrace();
											 * } List<MstCommonEntity> cityClassList = new ArrayList<>(); cityClassList
											 * = commonHomeMethodsService.findCommonMstByCommonCode("CITYCLASS");
											 * model.addAttribute("cityClassList", cityClassList);
											 * 
											 * model.addAttribute("lstAllDistrict", listDistrictemdl);
											 * model.addAttribute("lstAllState", listStatemdl);
											 * model.addAttribute("lstCurntDepartment", lstDepartment);
											 * model.addAttribute("lstDesignation",
											 * mstDesignationService.getDesignationMstData(locale.getLanguage()));
											 * model.addAttribute("lstAdminOfficeMst", lstDepartment);
											 * model.addAttribute("mstEmployeeEntity", mstEmployeeEntity);
											 * model.addAttribute("payscalelevel", payscalelevel);
											 * model.addAttribute("lstsvnbasicpay", lstsvnbasicpay);
											 * model.addAttribute("lstAllBankList", mstBankService.lstAllBank());
											 * model.addAttribute("lstAllBankBranchList",
											 * mstEmployeeService.getBankBranch(String.valueOf(mstEmployeeEntity.
											 * getBankCode().toString()))); model.addAttribute("lstCommonMstGender",
											 * commonHomeMethodsService.findCommonMstByCommonCode(CommonConstants.
											 * COMMONMSTTABLE.COMMONCODE_GENDER));
											 * model.addAttribute("lstDcpsAccnMaintainby",
											 * mstEmployeeService.getDcpsAccnMaintainby());
											 * model.addAttribute("lstAccountMaintainby",
											 * mstEmployeeService.getAccountMaintainby());
											 * model.addAttribute("lstGISApplicable",
											 * mstEmployeeService.getGISApplicable()); model.addAttribute("lstRelation",
											 * mstEmployeeService.getRelation()); List<ReligionMstEntity> mstReligionLst
											 * = new ArrayList<>(); mstReligionLst =
											 * commonHomeMethodsService.fetchAllReligions();
											 * model.addAttribute("mstReligionLst", mstReligionLst);
											 * model.addAttribute("roleId", messages.getRole_id());
											 * model.addAttribute("readonly",true); model.addAttribute("empId",
											 * employeeId);
											 */

		/*
		 * if(messages.getRole_id()==1) { return "/views/employee/Emp-Change-Dtls";
		 * }else {
		 */
		
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(model,messages);
		return "/views/employee/Approve-Emp-Change-Dtls";

		/// }
	}

	@GetMapping("/viewFormsForwardedByAsst")
	public String viewFormsForwardedByAsst(@ModelAttribute("mstEmployeeModel") MstEmployeeModel mstEmployeeModel,
			Model model, Locale locale, HttpSession session) {
		String message = (String) model.asMap().get("message");
		model.addAttribute("mstEmployeeModel", mstEmployeeModel);

		/*
		 * UserInfo messages = (UserInfo) session.getAttribute("MY_SESSION_MESSAGES");
		 * 
		 * List<TopicModel> menuList = new ArrayList<>(); List<TopicModel> subMenuList =
		 * new ArrayList<>();
		 * 
		 * menuList =
		 * commonHomeMethodsService.findMenuNameByRoleID(messages.getRole_id(),
		 * locale.getLanguage()); subMenuList =
		 * commonHomeMethodsService.findSubMenuByRoleID(messages.getRole_id(),
		 * locale.getLanguage());
		 * 
		 * model.addAttribute("menuList", menuList); model.addAttribute("subMenuList",
		 * subMenuList);
		 * 
		 * if (message != null && message.equals("SUCCESS")) { if (locale != null &&
		 * locale.getLanguage().equalsIgnoreCase("en")) { model =
		 * CommonUtils.initModel(CommonConstants.Message.ADDED_ENGLSH, STATUS.SUCCESS,
		 * model); } else { model =
		 * CommonUtils.initModel(CommonConstants.Message.ADDED_MARATHI, STATUS.SUCCESS,
		 * model); } } // model.addAttribute("lstAdminOfficeMst", //
		 * createAdminOfficeService.lstAdminOfficeMst()); //
		 * model.addAttribute("lstCadreMst", //
		 * mstCadreService.getCadreMstData(locale.getLanguage()));
		 * model.addAttribute("language", locale.getLanguage()); String strddo =
		 * messages.getUserName();
		 */
		/*
		 * List<MstEmployeeModel> employeeConfigurationService =
		 * mstEmployeeService.getDcpsEmployeeDetails(strddo, locale.getLanguage()); //
		 * MstEmployeeEntity mm=employeeConfigurationService.get(0); //
		 * logger.info("employeeConfigurationService="+mm);
		 * model.addAttribute("employeedetails", employeeConfigurationService);
		 */
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(model,messages);
		return "/views/employee/dcps-employee-details";
	}

	@GetMapping("/loadFormListForDDO")
	public String loadFormListForDDO(@ModelAttribute("mstEmployeeModel") MstEmployeeModel mstEmployeeModel, Model model,
			Locale locale, HttpSession session) {
		String message = (String) model.asMap().get("message");
		model.addAttribute("mstEmployeeModel", mstEmployeeModel);

		/*
		 * UserInfo messages = (UserInfo) session.getAttribute("MY_SESSION_MESSAGES");
		 * 
		 * List<TopicModel> menuList = new ArrayList<>(); List<TopicModel> subMenuList =
		 * new ArrayList<>();
		 * 
		 * menuList =
		 * commonHomeMethodsService.findMenuNameByRoleID(messages.getRole_id(),
		 * locale.getLanguage()); subMenuList =
		 * commonHomeMethodsService.findSubMenuByRoleID(messages.getRole_id(),
		 * locale.getLanguage());
		 * 
		 * model.addAttribute("menuList", menuList); model.addAttribute("subMenuList",
		 * subMenuList);
		 * 
		 * if (message != null && message.equals("SUCCESS")) { if (locale != null &&
		 * locale.getLanguage().equalsIgnoreCase("en")) { model =
		 * CommonUtils.initModel(CommonConstants.Message.ADDED_ENGLSH, STATUS.SUCCESS,
		 * model); } else { model =
		 * CommonUtils.initModel(CommonConstants.Message.ADDED_MARATHI, STATUS.SUCCESS,
		 * model); } } // model.addAttribute("lstAdminOfficeMst", //
		 * createAdminOfficeService.lstAdminOfficeMst()); //
		 * model.addAttribute("lstCadreMst", //
		 * mstCadreService.getCadreMstData(locale.getLanguage()));
		 * model.addAttribute("language", locale.getLanguage()); String strddo =
		 * messages.getUserName(); List<MstEmployeeModel> employeeConfigurationService =
		 * mstEmployeeService.getDcpsEmployeeDetails(strddo, locale.getLanguage()); //
		 * MstEmployeeEntity mm=employeeConfigurationService.get(0); //
		 * logger.info("employeeConfigurationService="+mm);
		 * model.addAttribute("employeedetails", employeeConfigurationService);
		 */
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(model,messages);
		return "/views/employee/dcps-employee-details";
	}

}
