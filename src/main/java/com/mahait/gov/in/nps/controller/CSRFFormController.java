
package com.mahait.gov.in.nps.controller;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.http.HttpHeaders;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mahait.gov.in.common.CommonConstants;
import com.mahait.gov.in.common.CommonConstants.STATUS;
import com.mahait.gov.in.common.CommonUtils;
import com.mahait.gov.in.controller.BaseController;
import com.mahait.gov.in.entity.CmnLookupMst;
import com.mahait.gov.in.entity.MstEmployeeEntity;
import com.mahait.gov.in.entity.MstNomineeDetailsEntity;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.MstDistrictModel;
import com.mahait.gov.in.model.MstStateModel;
import com.mahait.gov.in.nps.entity.MstEmployeeNPSEntity;
import com.mahait.gov.in.nps.entity.TrnNpsRegFileEntity;
import com.mahait.gov.in.nps.model.CSRFFormModel;
import com.mahait.gov.in.nps.service.CSRFFormService;
import com.mahait.gov.in.nps.service.NSDLIntegration;
import com.mahait.gov.in.service.CommonHomeMethodsService;
import com.mahait.gov.in.service.LocationMasterService;
import com.mahait.gov.in.service.MstBankService;
import com.mahait.gov.in.service.MstEmployeeService;

import cra.standalone.subsreg.RunSubsRegFvu;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Qualifier("abc")
@Controller
@RequestMapping("/ddo")
@PropertySource(value = { "classpath:application.properties" })
public class CSRFFormController  extends BaseController{
	// protected final Log logger = LogFactory.getLog(getClass());
	@Autowired
	CommonHomeMethodsService commonHomeMethodsService;

	@Autowired
	CSRFFormService csrfFormService;
	@Autowired
	LocationMasterService locationMasterService;

	@Autowired
	MstBankService mstBankService;

	@Autowired
	private Environment environment;

	@Autowired
	MstEmployeeService mstEmployeeService;

	@Autowired
	private NSDLIntegration nSDLIntegration;

	String ddoCode;
	String batchId;
	String url;
	String flag;
	String refCode;
	String dtoUserId;
	
	

	private static String OUTPUT_ZIP_FILE = "D:/output/MJP/";
	private static String OUTPUT_ZIP_Contri_FILE = "D:/output/MJP/Contribution/";

	@GetMapping("/mstCSRFForm")
	public String mstCSRFForm(@ModelAttribute("csrfFormModel") CSRFFormModel csrfFormModel, Model model, Locale locale,
			HttpSession session) {

		String message = (String) model.asMap().get("message");

		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");

		addMenuAndSubMenu(model, messages);
		model.addAttribute("lstRelation", mstEmployeeService.getRelation());

		if (message != null && message.equals("SUCCESS")) {
			if (locale != null && locale.getLanguage().equalsIgnoreCase("en")) {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_ENGLSH, STATUS.SUCCESS, model);
			} else {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_MARATHI, STATUS.SUCCESS, model);
			}
		}
		if (message != null && message.equals("UPDATED")) {
			if (locale != null && locale.getLanguage().equalsIgnoreCase("en")) {
				model = CommonUtils.initModel(CommonConstants.Message.UPDATED_ENGLSH, STATUS.SUCCESS, model);
			} else {
				model = CommonUtils.initModel(CommonConstants.Message.UPDATED_ENGLSH, STATUS.SUCCESS, model);
			}
		}
		model.addAttribute("language", locale.getLanguage());
		model.addAttribute("lstEmpList", csrfFormService.findAllEmployees(messages.getDdoCode()));
		return "/views/nps/CSRF-form";
	}

	@GetMapping("/updateCSRFForm/{empId}")
	public ModelAndView updateCSRFForm(HttpServletRequest request, Model model, HttpServletResponse response,
			Locale locale, HttpSession session,
			@ModelAttribute("mstEmployeeEntity") MstEmployeeEntity mstEmployeeEntity, @PathVariable Long empId) {
		
		
		String message = (String) model.asMap().get("message");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("language", locale.getLanguage());
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");

		addMenuAndSubMenu(model, messages);
		
		modelAndView.addObject("context", request.getContextPath());
	
		List<CmnLookupMst> cityClassList = csrfFormService.findCityClass();
		model.addAttribute("cityClassList", cityClassList);

		model.addAttribute("lstGISGroup", mstEmployeeService.getGISGroup());

		List<MstStateModel> listStatemdl = new ArrayList<MstStateModel>();
		List<Object[]> listState = locationMasterService.findAllStates(1);
		for (Iterator iterator = listState.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			MstStateModel mstStateModel = new MstStateModel();
			if (objects[0] != null)
				mstStateModel.setStateCode(Integer.parseInt(String.valueOf(objects[0])));
			mstStateModel.setStateNameEn(String.valueOf(objects[1]));
			listStatemdl.add(mstStateModel);
		}

		model.addAttribute("lstAllState", listStatemdl);
		mstEmployeeEntity = csrfFormService.findEmployeeBySevaarthId(empId);
		System.out.println("--------------" + mstEmployeeEntity);
		String payScaleDesc = null;
		if (mstEmployeeEntity.getPayCommissionCode() == 700005) {
			payScaleDesc = "S_" + mstEmployeeEntity.getSevenPcLevel();
		} else {
			payScaleDesc = csrfFormService.getPayScale(mstEmployeeEntity.getPayScaleCode());
		}
		mstEmployeeEntity.setPayScaleDesc(payScaleDesc);

		model.addAttribute("lstRelation", mstEmployeeService.getRelation());

		System.out.println("--------------" + mstEmployeeEntity);
		List<MstDistrictModel> listDistrictemdl = new ArrayList<MstDistrictModel>();

		List<Object[]> listDistrict = locationMasterService.findAllDistricts(27);
		// logger.info("distric code list size="+listDistrict.size());
		for (Iterator iterator = listDistrict.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
		/*	MstDistrictModel mstDistrictModel = new MstDistrictModel();
			mstDistrictModel.setState(String.valueOf(objects[3]));
			mstDistrictModel.setStateName(String.valueOf(objects[4]));*/
		//	listDistrictemdl.add(mstDistrictModel);
			// logger.info("distric code list object4="+String.valueOf(objects[4]));
		}
		model.addAttribute("lstAllDistrict", listDistrictemdl);
		model.addAttribute("lstAllBankList", mstBankService.lstAllBank());
		model.addAttribute("lstAllBankBranch", commonHomeMethodsService.findbankBranch());

		List<MstNomineeDetailsEntity> mstNomineeDetailsEntity = csrfFormService.findNomineeDtls(empId);
		mstEmployeeEntity.setMstNomineeDetailsEntity(mstNomineeDetailsEntity);

		if (mstEmployeeEntity.getPayCommissionCode() == 8) {
			model.addAttribute("basicsalary", mstEmployeeEntity.getSevenPcBasic());
		} else {
			model.addAttribute("basicsalary",
					mstEmployeeEntity.getBasicPay() + mstEmployeeEntity.getGradePay().doubleValue());

		}
		
		String bankName = csrfFormService.findBankName(mstEmployeeEntity.getBankCode());
		String bankBranchName = csrfFormService.findBankBranchName(mstEmployeeEntity.getBankBranchCode());
		model.addAttribute("lstAllDistrict", listDistrictemdl);
		model.addAttribute("paycomission", mstEmployeeEntity.getPayCommissionCode());
		modelAndView.addObject("mstEmployeeEntity", mstEmployeeEntity);
		modelAndView.addObject("mstNomineeDetailsEntity", mstNomineeDetailsEntity);
		modelAndView.addObject("employeeBankName", bankName);
		modelAndView.addObject("employeeBankBranchName", bankBranchName);
		modelAndView.setViewName("/views/nps/UpdateCSRF-Form");
		return modelAndView;
	}

	@PostMapping("/saveCSRF")
	public String saveCSRF(@ModelAttribute("mstEmployeeEntity") @Valid MstEmployeeEntity mstEmployeeEntity,
			HttpSession session, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model,
			Locale locale, @RequestParam("files") MultipartFile[] files) {

		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		String ddoLevel2 = csrfFormService.getDDOLevel2FromDDO1(messages.getDdoCode());
		Long mstEmployeeNPSResponse = csrfFormService.saveCSRF(mstEmployeeEntity, files, ddoLevel2);

		/* if(afterSaveId > 0) { */
		if (mstEmployeeNPSResponse > 0) {
			redirectAttributes.addFlashAttribute("message", "SUCCESS");
		} else {
			mstEmployeeEntity = csrfFormService.findEmployeeBySevaarthId(mstEmployeeEntity.getEmployeeId());
			mstEmployeeEntity.setIsMappedWithNps('1');
			csrfFormService.updatemstEmployeeEntity(mstEmployeeEntity);
		}
		// model.addAttribute("lstDeptDataTable",
		// mstDepartmentService.findAllDepartment());
		return "redirect:/ddo/mstCSRFForm"; /* redirects to controller URL */
	}

	@GetMapping("/ApprovedCSRFFormEmp")
	public String ApprovedCSRFFormEmp(@ModelAttribute("csrfFormModel") CSRFFormModel csrfFormModel, Model model,
			Locale locale, HttpSession session) {

		String message = (String) model.asMap().get("message");

		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");

		if (message != null && message.equals("SUCCESS")) {
			if (locale != null && locale.getLanguage().equalsIgnoreCase("en")) {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_ENGLSH, STATUS.SUCCESS, model);
			} else {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_MARATHI, STATUS.SUCCESS, model);
			}
		}
		if (message != null && message.equals("UPDATED")) {
			if (locale != null && locale.getLanguage().equalsIgnoreCase("en")) {
				model = CommonUtils.initModel(CommonConstants.Message.UPDATED_ENGLSH, STATUS.SUCCESS, model);
			} else {
				model = CommonUtils.initModel(CommonConstants.Message.UPDATED_ENGLSH, STATUS.SUCCESS, model);
			}
		}
		model.addAttribute("language", locale.getLanguage());
		model.addAttribute("lstEmpList", csrfFormService.findAllCSRFApprovedEmployees());
		return "/views/ApprovedCSRFFormEmp";
	}

	@GetMapping("/viewApprovedCSRFForm/{empId}")
	public ModelAndView viewApprovedCSRFForm(HttpServletRequest request, Model model, HttpServletResponse response,
			Locale locale, HttpSession session,
			@ModelAttribute("mstEmployeeEntity") MstEmployeeEntity mstEmployeeEntity, @PathVariable Long empId) {
		String message = (String) model.asMap().get("message");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("language", locale.getLanguage());
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		modelAndView.addObject("message", message);

		mstEmployeeEntity = csrfFormService.findEmployeeBySevaarthId(empId);

		List<MstStateModel> listStatemdl = new ArrayList<MstStateModel>();
		List<Object[]> listState = locationMasterService.findAllStates(1);
		for (Iterator iterator = listState.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			MstStateModel mstStateModel = new MstStateModel();
			mstStateModel.setStateCode(Integer.parseInt(String.valueOf(objects[1])));
			mstStateModel.setStateNameEn(String.valueOf(objects[2]));
			listStatemdl.add(mstStateModel);
		}

		model.addAttribute("lstAllState", listStatemdl);
		System.out.println("--------------" + mstEmployeeEntity);
		List<MstDistrictModel> listDistrictemdl = new ArrayList<MstDistrictModel>();

		List<Object[]> listDistrict = locationMasterService.findAllDistricts(27);
		// logger.info("distric code list size="+listDistrict.size());
		for (Iterator iterator = listDistrict.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
		/*	MstDistrictModel mstDistrictModel = new MstDistrictModel();
			mstDistrictModel.setState(String.valueOf(objects[3]));
			mstDistrictModel.setStateName(String.valueOf(objects[4]));
			listDistrictemdl.add(mstDistrictModel);*/
			// logger.info("distric code list object4="+String.valueOf(objects[4]));
		}
		model.addAttribute("lstAllDistrict", listDistrictemdl);
		model.addAttribute("lstAllBankList", mstBankService.lstAllBank());

		MstEmployeeNPSEntity mstEmployeeNPSEntity = csrfFormService.findEmployeeDtlsBySevaarthId(empId);

		if (mstEmployeeNPSEntity != null) {
			mstEmployeeEntity.setBuildingName(mstEmployeeNPSEntity.getBuildingName());
			mstEmployeeEntity.setFlatUnitNo(mstEmployeeNPSEntity.getFlatUnitNo());
			mstEmployeeEntity.setLocality(mstEmployeeNPSEntity.getLocality());
			mstEmployeeEntity.setDistrict(mstEmployeeNPSEntity.getDistrict());
			mstEmployeeEntity.setState(mstEmployeeNPSEntity.getState());
			mstEmployeeEntity.setPinCode(mstEmployeeNPSEntity.getPinCode());
			mstEmployeeEntity.setEmpPermanentBuildingName(mstEmployeeNPSEntity.getEmpPermanentBuildingName());
			mstEmployeeEntity.setEmpPermanentFlatUnitNo(mstEmployeeNPSEntity.getEmpPermanentFlatUnitNo());
			mstEmployeeEntity.setEmpPermanentLocality(mstEmployeeNPSEntity.getEmpPermanentLocality());
			mstEmployeeEntity.setEmpPermanentDistrict(mstEmployeeNPSEntity.getEmpPermanentDistrict());
			mstEmployeeEntity.setEmpPermanentState(mstEmployeeNPSEntity.getEmpPermanentState());
			mstEmployeeEntity.setEmpPermanentPinCode(mstEmployeeNPSEntity.getEmpPermanentPinCode());
			mstEmployeeEntity.setEmployeeBankName(mstEmployeeNPSEntity.getEmployeeBankName());
			mstEmployeeEntity.setEmployeeBankBranchName(mstEmployeeNPSEntity.getEmployeeBankBranchName());
			mstEmployeeEntity.setEmployeeBankBankAddress(mstEmployeeNPSEntity.getEmployeeBankBankAddress());
			mstEmployeeEntity.setEmployeeBankPinCode(mstEmployeeNPSEntity.getEmployeeBankPinCode());
			mstEmployeeEntity.setIfscCode(mstEmployeeNPSEntity.getIFSCCode());
			mstEmployeeEntity.setEmployeeMotherName(mstEmployeeNPSEntity.getEmployeeMotherName());
			mstEmployeeEntity.setEmployeeFatherHubandName(mstEmployeeNPSEntity.getEmployeeFatherHubandName());
			mstEmployeeEntity.setEmployeeBirthPlace(mstEmployeeNPSEntity.getEmployeeBirthPlace());
			mstEmployeeEntity.setEmployeeSpouseName(mstEmployeeNPSEntity.getEmployeeSpouseName());
			mstEmployeeEntity.setPpanNo(mstEmployeeNPSEntity.getPpanNo());
		}
		if (mstEmployeeEntity.getGender() == '1') {
			mstEmployeeEntity.setGender('M');
		} else if (mstEmployeeEntity.getGender() == '2') {
			mstEmployeeEntity.setGender('F');
		} else if (mstEmployeeEntity.getGender() == '3') {
			mstEmployeeEntity.setGender('T');
		}

		if (mstEmployeeEntity.getPayCommissionCode() == 8) {
			model.addAttribute("basicsalary", mstEmployeeEntity.getSevenPcBasic());
		} else {
			model.addAttribute("basicsalary",
					mstEmployeeEntity.getBasicPay() + mstEmployeeEntity.getGradePay().doubleValue());

		}
		model.addAttribute("lstRelation", mstEmployeeService.getRelation());
		model.addAttribute("lstAllDistrict", listDistrictemdl);
		model.addAttribute("paycomission", mstEmployeeEntity.getPayCommissionCode());
		modelAndView.addObject("mstEmployeeEntity", mstEmployeeEntity);
		modelAndView.setViewName("/views/viewApprovedCSRFForm");
		return modelAndView;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/GenerateTextFile/{countEmp}", method = RequestMethod.GET)
	public ResponseEntity<InputStreamResource> GenerateTextFile(
			@ModelAttribute("mstEmployeeEntity") MstEmployeeEntity mstEmployeeEntity, Model model, Locale locale,
			HttpSession session, @PathVariable int countEmp) throws IOException {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		System.out.println("Employee Count Records--------" + countEmp);
		HashMap objectArgs = new HashMap();
		List<CSRFFormModel> lstCSRF = new ArrayList<>();
		List<CSRFFormModel> lstCSR = new ArrayList<>();
		String ddoAsst = messages.getUserName();
		String ddoLevel2 = csrfFormService.getDDOLevel2FromDDO1(messages.getUserName());
		// List<Object[]> lstEmp = csrfFormService.getEmpData(sevaarthId,ddoAsst);
		List<MstEmployeeNPSEntity> lstEmp1 = csrfFormService.getEmpDataForTextFile(ddoLevel2, countEmp);
		try {
			String lLngEmpId = NpsFileCreation(lstEmp1, ddoAsst, ddoLevel2, countEmp, messages);
			String SOURCE_FOLDER = OUTPUT_ZIP_FILE + ddoAsst + "/" + lLngEmpId;
			String[] inputParamaeters = { OUTPUT_ZIP_FILE + ddoAsst + "/" + lLngEmpId };
			RunSubsRegFvu craFVUpaosubcontr = new RunSubsRegFvu();
			craFVUpaosubcontr.main(inputParamaeters);

			File serverFile = new File(
					OUTPUT_ZIP_FILE + messages.getUserName() + "/" + lLngEmpId + "/Processed/subscriberRegNsdl.txt");
			// Download file with InputStreamResource

			if (serverFile.exists()) {

				FileInputStream fileInputStream = null;
				try {
					fileInputStream = new FileInputStream(serverFile);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				InputStreamResource inputStreamResource = new InputStreamResource(fileInputStream);

				return ResponseEntity.ok()
						.header(CommonConstants.Headers.CONTENT_DISPOSITION, "inline;filename=subscriberRegNsdl.txt")
						.contentType(MediaType.TEXT_PLAIN).contentLength(serverFile.length()).body(inputStreamResource);
			} else {
				serverFile = new File(OUTPUT_ZIP_FILE + messages.getUserName() + "/" + lLngEmpId
						+ "/Error/subscriberRegNsdl_Resp.html");
				FileInputStream fileInputStream = null;
				try {
					fileInputStream = new FileInputStream(serverFile);
				} catch (Exception e) {
					e.printStackTrace();
				}
				InputStreamResource inputStreamResource = new InputStreamResource(fileInputStream);
				return ResponseEntity.ok()
						.header(CommonConstants.Headers.CONTENT_DISPOSITION, "inline;filename=filename=subscriberRegNsdl_Resp.html")
						.contentType(MediaType.TEXT_HTML).contentLength(serverFile.length()).body(inputStreamResource);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
/*
	@GetMapping("/printCSRFForm/{employeeId}")
	public String printCSRFForm(HttpServletRequest request, Model model, HttpServletResponse response, Locale locale,
			HttpSession session, @ModelAttribute("mstEmployeeNPSModel") MstEmployeeNPSModel mstEmployeeNPSModel,
			@PathVariable int employeeId) {
		UserInfo messages = (UserInfo) session.getAttribute("MY_SESSION_MESSAGES");
		int levelRoleVal = messages.getRole_id();
		List<TopicModel> menuList = new ArrayList<>();
		List<TopicModel> subMenuList = new ArrayList<>();

		menuList = commonHomeMethodsService.findMenuNameByRoleID(levelRoleVal, locale.getLanguage());
		subMenuList = commonHomeMethodsService.findSubMenuByRoleID(levelRoleVal, locale.getLanguage());

		model.addAttribute("menuList", menuList);
		model.addAttribute("subMenuList", subMenuList);

		CSRFFormModel lstCSRFFormModel = new CSRFFormModel();

		
							
		
		String message = (String) model.asMap().get("message");
		model.addAttribute("language", locale.getLanguage());
		model.addAttribute("sessionMessages", messages.getUser_id());
		model.addAttribute("userName", messages.getFullName());
		model.addAttribute("context", request.getContextPath());
		menuList = commonHomeMethodsService.findMenuNameByRoleID(levelRoleVal, locale.getLanguage());
		subMenuList = commonHomeMethodsService.findSubMenuByRoleID(levelRoleVal, locale.getLanguage());

		mstEmployeeNPSModel = csrfFormService.findCSRFEmployeeBySevaarthId(employeeId);

		List<MstStateModel> listStatemdl = new ArrayList<MstStateModel>();
		List<Object[]> listState = locationMasterService.findAllStates(1);
		for (Iterator iterator = listState.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			MstStateModel mstStateModel = new MstStateModel();
			mstStateModel.setStateCode(Integer.parseInt(String.valueOf(objects[1])));
			mstStateModel.setStateNameEn(String.valueOf(objects[2]));
			listStatemdl.add(mstStateModel);
		}

		model.addAttribute("lstAllState", listStatemdl);
		System.out.println("--------------" + mstEmployeeNPSModel);
		List<MstDistrictModel> listDistrictemdl = new ArrayList<MstDistrictModel>();

		List<Object[]> listDistrict = locationMasterService.findAllDistricts(27);
		// logger.info("distric code list size="+listDistrict.size());
		for (Iterator iterator = listDistrict.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			MstDistrictModel mstDistrictModel = new MstDistrictModel();
			mstDistrictModel.setState(String.valueOf(objects[3]));
			mstDistrictModel.setStateName(String.valueOf(objects[4]));
			listDistrictemdl.add(mstDistrictModel);
			// logger.info("distric code list object4="+String.valueOf(objects[4]));
		}
		model.addAttribute("lstAllDistrict", listDistrictemdl);
		model.addAttribute("lstAllBankList", mstBankService.lstAllBank());

		MstEmployeeNPSEntity mstEmployeeNPSEntity = csrfFormService.findEmployeeDtlsBySevaarthId(employeeId);

		if (mstEmployeeNPSEntity != null) {
			mstEmployeeNPSModel.setBuildingName(mstEmployeeNPSEntity.getBuildingName());
			mstEmployeeNPSModel.setFlatUnitNo(mstEmployeeNPSEntity.getFlatUnitNo());
			mstEmployeeNPSModel.setLocality(mstEmployeeNPSEntity.getLocality());
			mstEmployeeNPSModel.setDistrict(mstEmployeeNPSEntity.getDistrict());
			mstEmployeeNPSModel.setState(mstEmployeeNPSEntity.getState());
			mstEmployeeNPSModel.setPinCode((mstEmployeeNPSEntity.getPinCode()));
			mstEmployeeNPSModel.setEmpPermanentBuildingName(mstEmployeeNPSEntity.getEmpPermanentBuildingName());
			mstEmployeeNPSModel.setEmpPermanentFlatUnitNo(mstEmployeeNPSEntity.getEmpPermanentFlatUnitNo());
			mstEmployeeNPSModel.setEmpPermanentLocality(mstEmployeeNPSEntity.getEmpPermanentLocality());
			mstEmployeeNPSModel.setEmpPermanentDistrict(mstEmployeeNPSEntity.getEmpPermanentDistrict());
			mstEmployeeNPSModel.setEmpPermanentState(mstEmployeeNPSEntity.getEmpPermanentState());
			mstEmployeeNPSModel.setEmpPermanentPinCode(mstEmployeeNPSEntity.getEmpPermanentPinCode());
			mstEmployeeNPSModel.setEmployeeBankName(mstEmployeeNPSEntity.getEmployeeBankName());
			mstEmployeeNPSModel.setEmployeeBankBranchName(mstEmployeeNPSEntity.getEmployeeBankBranchName());
			mstEmployeeNPSModel.setEmployeeBankBankAddress(mstEmployeeNPSEntity.getEmployeeBankBankAddress());
			mstEmployeeNPSModel.setEmployeeBankPinCode(mstEmployeeNPSEntity.getEmployeeBankPinCode());
			mstEmployeeNPSModel.setIFSCCode(mstEmployeeNPSEntity.getIFSCCode());
			mstEmployeeNPSModel.setEmployeeMotherName(mstEmployeeNPSEntity.getEmployeeMotherName());
			mstEmployeeNPSModel.setEmployeeFatherHubandName(mstEmployeeNPSEntity.getEmployeeFatherHubandName());
			mstEmployeeNPSModel.setEmployeeBirthPlace(mstEmployeeNPSEntity.getEmployeeBirthPlace());
			mstEmployeeNPSModel.setEmployeeBirthPlace(mstEmployeeNPSEntity.getEmployeeBirthPlace());
			mstEmployeeNPSModel.setEmpNominee1GuardName(mstEmployeeNPSEntity.getEmpNominee1GuardName());
			mstEmployeeNPSModel.setEmpNominee1GuardName(mstEmployeeNPSEntity.getEmpNominee1Name());
			mstEmployeeNPSModel.setEmployeeDOB(mstEmployeeNPSEntity.getEmployeeDOB());
			mstEmployeeNPSModel.setEmployeeSpouseName(mstEmployeeNPSEntity.getEmployeeSpouseName());
			mstEmployeeNPSModel.setPpanNo(mstEmployeeNPSEntity.getPpanNo());
			mstEmployeeNPSModel.setEmployeeMaritalStatus(mstEmployeeNPSEntity.getEmployeeMaritalStatus());
			mstEmployeeNPSModel.setEmpMobileNo(mstEmployeeNPSEntity.getEmpMobileNo());
			mstEmployeeNPSModel.setEmpNominee1Name(mstEmployeeNPSEntity.getEmpNominee1Name());
			mstEmployeeNPSModel.setEmpNominee1DOB(mstEmployeeNPSEntity.getEmpNominee1DOB());
			mstEmployeeNPSModel.setEmpNominee1relationship(mstEmployeeNPSEntity.getEmpNominee1relationship());
			mstEmployeeNPSModel.setEmployeeDOJ(mstEmployeeNPSEntity.getEmployeeDOJ());
			mstEmployeeNPSModel.setEmployeeDateOfRetirement(mstEmployeeNPSEntity.getEmployeeDateOfRetirement());
			mstEmployeeNPSModel.setPpanNo(mstEmployeeNPSEntity.getPpanNo());
			mstEmployeeNPSModel.setDdoRegNo(mstEmployeeNPSEntity.getDdoRegNo());
			mstEmployeeNPSModel.setDtoRegNo(mstEmployeeNPSEntity.getDtoRegNo());
			mstEmployeeNPSModel.setEmployeeGender(mstEmployeeNPSEntity.getEmployeeGender());
			mstEmployeeNPSModel.setSalutation(mstEmployeeNPSEntity.getSalutation());
			mstEmployeeNPSModel.setIncomeRange(mstEmployeeNPSEntity.getIncomeRange());
			mstEmployeeNPSModel.setSevaarthId(mstEmployeeNPSEntity.getSevaarthId());
			mstEmployeeNPSModel.setEmployeeId(mstEmployeeNPSEntity.getEmployeeId());

		}
		
		
		
		
		
		
		Boolean maleval=false;
		Boolean femaleval=false;
		Boolean transval=false;
		
		
		
		if (mstEmployeeNPSModel.getEmployeeGender() == 'M') {
			mstEmployeeNPSModel.setEmployeeGender('1');
			maleval=true;
		} else if (mstEmployeeNPSModel.getEmployeeGender() == 'F') {
			mstEmployeeNPSModel.setEmployeeGender('2');
			femaleval=true;
		} else if (mstEmployeeNPSModel.getEmployeeGender() == 'T') {
			mstEmployeeNPSModel.setEmployeeGender('3');
			transval=true;
		}
		
		model.addAttribute("maleval", maleval);
		model.addAttribute("femaleval", femaleval);
		model.addAttribute("transval", transval);
		
		
		
		
		
		
		if (mstEmployeeNPSModel.getSalutation().equals("1") || mstEmployeeNPSModel.getSalutation().equals("4")) {
			mstEmployeeNPSModel.setSalutation("S");
		} else if (mstEmployeeNPSModel.getSalutation().equals("2") || mstEmployeeNPSModel.getSalutation().equals("5")) {
			mstEmployeeNPSModel.setSalutation("M");
		} else if (mstEmployeeNPSModel.getSalutation().equals("3")) {
			mstEmployeeNPSModel.setSalutation("K");
		}

		if (mstEmployeeNPSModel.getPayCommissionCode() == 8) {
			model.addAttribute("basicsalary", mstEmployeeNPSModel.getSevenPcBasic());
		} else {
			model.addAttribute("basicsalary",
					mstEmployeeNPSModel.getBasicPay() + mstEmployeeNPSModel.getGradePay().doubleValue());

		}
		model.addAttribute("lstRelation", mstEmployeeService.getRelation());
		model.addAttribute("lstAllDistrict", listDistrictemdl);
		model.addAttribute("lstAllDistrict", listDistrictemdl);
		model.addAttribute("lstAllDistrict", listDistrictemdl);
		model.addAttribute("paycomission", mstEmployeeNPSModel.getPayCommissionCode());
		model.addAttribute("mstEmployeeNPSModel", mstEmployeeNPSModel);

		if (mstEmployeeNPSModel.getEmployeeDOB() != null) {
			SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
			String dateString = formater.format(mstEmployeeNPSModel.getEmployeeDOB());
			char dateStringArr[] = dateString.toCharArray();
			model.addAttribute("dateStringArr", dateStringArr);
		}
		if (mstEmployeeNPSModel.getEmployeeFirstName() != null) {

			char name[] = mstEmployeeNPSModel.getEmployeeFirstName().toCharArray();
			model.addAttribute("name", name);
//			char name[] = mstEmployeeNPSModel.getEmployeeFirstName().toCharArray();
		}
		if (mstEmployeeNPSModel.getEmployeeMiddleName() != null) {

			char Sname[] = mstEmployeeNPSModel.getEmployeeMiddleName().toCharArray();
			model.addAttribute("Sname", Sname);
//			char Sname[] = mstEmployeeNPSModel.getEmployeeMiddleName().toCharArray();
		}
		if (mstEmployeeNPSModel.getEmployeeLastName() != null) {

			char Lname[] = mstEmployeeNPSModel.getEmployeeLastName().toCharArray();
			model.addAttribute("Lname", Lname);
//			char Lname[] = mstEmployeeNPSModel.getEmployeeLastName().toCharArray();
		}
		if (mstEmployeeNPSModel.getEmployeeFatherHubandName() != null) {

			char fatherName[] = mstEmployeeNPSModel.getEmployeeFatherHubandName().toCharArray();
			model.addAttribute("fatherName", fatherName);
//			char fatherName[] = mstEmployeeNPSModel.getEmployeeFatherHubandName().toCharArray();
		}
		if (mstEmployeeNPSModel.getEmployeeMotherName() != null) {

			char motherName[] = mstEmployeeNPSModel.getEmployeeMotherName().toCharArray();
			model.addAttribute("motherName", motherName);
//			char motherName[] = mstEmployeeNPSModel.getEmployeeMotherName().toCharArray();
		}
		if (mstEmployeeNPSModel.getEmployeeBirthPlace() != null) {

			char birthPlace[] = mstEmployeeNPSModel.getEmployeeBirthPlace().toCharArray();
			model.addAttribute("birthPlace", birthPlace);
//			char birthPlace[] = mstEmployeeNPSModel.getEmployeeBirthPlace().toCharArray();
		}
		if (mstEmployeeNPSModel.getCountry() != null) {

			char country[] = mstEmployeeNPSModel.getCountry().toCharArray();
			model.addAttribute("country", country);
		}
//		char spouseName[] = mstEmployeeNPSModel.getEmployeeSpouseName().toCharArray();
//		char uid[] = mstEmployeeNPSModel.getEmployeeAadhar().toCharArray();
//		char empAddr1[] = mstEmployeeNPSModel.getAddress1().toCharArray();
//		char empAddr2[] = mstEmployeeNPSModel.getAddress2().toCharArray();
//		char empDist[] = mstEmployeeNPSModel.getDistrict().toCharArray();
//		char empState[] = mstEmployeeNPSModel.getState().toCharArray();
//		char pinCode[] = mstEmployeeNPSModel.getPinCode().toString().toCharArray();
//		char mobNo[] = mstEmployeeNPSModel.getEmpMobileNo().toCharArray();
		
		Integer age = 0;
		
		if(mstEmployeeNPSModel.getEmpNominee1DOB()!=null) {
			Calendar birthCalender = Calendar.getInstance();
			birthCalender.setTime(mstEmployeeNPSModel.getEmpNominee1DOB());

			Calendar today = Calendar.getInstance();
			today.setTime(new Date());

			LocalDate birthday = LocalDate.of(birthCalender.get(Calendar.YEAR), birthCalender.get(Calendar.MONTH) + 1,
					birthCalender.get(Calendar.DAY_OF_MONTH));

			LocalDate todayL = LocalDate.of(today.get(Calendar.YEAR), today.get(Calendar.MONTH) + 1,
					today.get(Calendar.DAY_OF_MONTH));

			Period period = Period.between(birthday, todayL);

			age = period.getYears();
			
			char ageArr[]=age.toString().toCharArray();

			model.addAttribute("ageArr", ageArr);
		}
		


		
		
		if (mstEmployeeNPSModel.getEmployeeSpouseName() != null) {

			char spouseName[] = mstEmployeeNPSModel.getEmployeeSpouseName().toCharArray();
			model.addAttribute("spouseName", spouseName);
		}
		if (mstEmployeeNPSModel.getEmployeeAadhar() != null) {

			char uid[] = mstEmployeeNPSModel.getEmployeeAadhar().toCharArray();
			
			int len=uid.length-4;
			
			for(int i=0;i<len;i++) {
				uid[i]='X';
			}
			
			
			
			model.addAttribute("uid", uid);
		}
		if (mstEmployeeNPSModel.getAddress1() != null) {

			char empAddr1[] = mstEmployeeNPSModel.getAddress1().toCharArray();
			model.addAttribute("empAddr1", empAddr1);
		}
		if (mstEmployeeNPSModel.getAddress2() != null) {

			char empAddr2[] = mstEmployeeNPSModel.getAddress2().toCharArray();
			model.addAttribute("empAddr2", empAddr2);
		}
		if (mstEmployeeNPSModel.getDistrict() != null) {

			char empDist[] = mstEmployeeNPSModel.getDistrict().toCharArray();
			model.addAttribute("empDist", empDist);
		}
		String empState = mstEmployeeNPSModel.getState();
		
		if (mstEmployeeNPSModel.getState() != null) {

			model.addAttribute("stateName", csrfFormService.getStateName(empState).toString().toCharArray());
//			model.addAttribute("empState", empState);
		}
		if (mstEmployeeNPSModel.getPinCode() != null) {
			
			char pinCode[] = mstEmployeeNPSModel.getPinCode().toString().toCharArray();
			model.addAttribute("pinCode", pinCode);
		}
		if (mstEmployeeNPSModel.getEmpMobileNo() != null) {
			
			char mobNo[] = mstEmployeeNPSModel.getEmpMobileNo().toCharArray();
			model.addAttribute("mobNo", mobNo);
		}
		if (mstEmployeeNPSModel.getEmpEmailId() != null) {
			
			char emailId[] = mstEmployeeNPSModel.getEmpEmailId().toCharArray();
			model.addAttribute("emailId", emailId);
		}
		if (mstEmployeeNPSModel.getIFSCCode() != null) {
			
			char ifscCode[] = mstEmployeeNPSModel.getIFSCCode().toCharArray();
			model.addAttribute("ifscCode", ifscCode);
		}
		
		
		if (mstEmployeeNPSModel.getEmpPhoneNo() != null) {

			char phoneNo[] = mstEmployeeNPSModel.getEmpPhoneNo().toCharArray();
			model.addAttribute("phoneNo", phoneNo);
		}
		
//		char phoneNo[] = mstEmployeeNPSModel.getEmpPhoneNo().toCharArray();
//		char emailId[] = mstEmployeeNPSModel.getEmpEmailId().toCharArray();
//		char ifscCode[] = mstEmployeeNPSModel.getIFSCCode().toCharArray();
//		char bankName[] = mstEmployeeNPSModel.getEmployeeBankName().toCharArray();
//		char accNo[] = mstEmployeeNPSModel.getEmployeeBankAccountNo().toString().toCharArray();
//		char panNo[] = mstEmployeeNPSModel.getPanNo().toCharArray();
//		char PpanNo[] = mstEmployeeNPSModel.getPpanNo().toCharArray();
		
		if (mstEmployeeNPSModel.getEmployeeBankName() != null) {

			char bankName[] = mstEmployeeNPSModel.getEmployeeBankName().toCharArray();
			model.addAttribute("bankName", bankName);
		}
		if (mstEmployeeNPSModel.getEmployeeBankAccountNo() != null) {

			char accNo[] = mstEmployeeNPSModel.getEmployeeBankAccountNo().toString().toCharArray();
			model.addAttribute("accNo", accNo);
		}
		if (mstEmployeeNPSModel.getPanNo() != null) {

			char panNo[] = mstEmployeeNPSModel.getPanNo().toCharArray();
			model.addAttribute("panNo", panNo);
		}
		if (mstEmployeeNPSModel.getPpanNo() != null) {

			char PpanNo[] = mstEmployeeNPSModel.getPpanNo().toCharArray();
			model.addAttribute("PpanNo", PpanNo);
		}
		

		if (mstEmployeeNPSModel.getSevaarthId() != null) {

			char sevaarthId[] = mstEmployeeNPSModel.getSevaarthId().toCharArray();
			model.addAttribute("sevaarthId", sevaarthId);
		}

		// char sevaarthId[] = mstEmployeeNPSModel.getSevaarthId().toCharArray();
		if (mstEmployeeNPSModel.getEmployeeDOJ() != null) {

//			char doj[] = mstEmployeeNPSModel.getEmployeeDOJ().toString().toCharArray();
			SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
			String dateString = formater.format(mstEmployeeNPSModel.getEmployeeDOJ());
			char doj[] = dateString.toCharArray();
			model.addAttribute("doj", doj);
		}
		if (mstEmployeeNPSModel.getEmployeeDateOfRetirement() != null) {

//			char dor[] = mstEmployeeNPSModel.getEmployeeDateOfRetirement().toString().toCharArray();
			SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
			String dateString = formater.format(mstEmployeeNPSModel.getEmployeeDateOfRetirement());
			char dor[] = dateString.toCharArray();
			model.addAttribute("dor", dor);
		}
		if (mstEmployeeNPSModel.getEmpNominee1Name() != null) {
			
			char nomieeName[] = mstEmployeeNPSModel.getEmpNominee1Name().toString().toCharArray();
			model.addAttribute("nomieeName", nomieeName);
		}
		if (mstEmployeeNPSModel.getEmpNominee1GuardName() != null) {
			
			char nomiNameGuard[] = mstEmployeeNPSModel.getEmpNominee1GuardName().toString().toCharArray();
			model.addAttribute("nomiNameGuard", nomiNameGuard);
		}
		if (mstEmployeeNPSModel.getEmpNominee1relationship() != null) {
			
			char nomiNameRel[] = mstEmployeeNPSModel.getEmpNominee1relationship().toString().toCharArray();
			model.addAttribute("nomiNameRel", nomiNameRel);
		}
//		char nomieeName[] = mstEmployeeNPSModel.getEmpNominee1Name().toCharArray();
//		char nomiNameGuard[] = mstEmployeeNPSModel.getEmpNominee1GuardName().toCharArray();
//		char nomiNameRel[] = mstEmployeeNPSModel.getEmpNominee1relationship().toCharArray();

		if (mstEmployeeNPSModel.getEmpNominee1DOB() != null) {

//			char nomiNameDob[] = mstEmployeeNPSModel.getEmpNominee1DOB().toString().toCharArray();
			SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
			String dateString = formater.format(mstEmployeeNPSModel.getEmpNominee1DOB());
			char nomiNameDob[] = dateString.toCharArray();
			model.addAttribute("nomiNameDob", nomiNameDob);
		}

		// model.addAttribute("sevaarthId", sevaarthId);

		model.addAttribute("nomieeName", nomieeName);
		model.addAttribute("nomiNameRel", nomiNameRel);
		model.addAttribute("nomiNameGuard", nomiNameGuard);

		if (mstEmployeeNPSModel.getDdoRegNo() != null) {

			char ddoRegNo[] = mstEmployeeNPSModel.getDdoRegNo().toCharArray();
			model.addAttribute("ddoRegNo", ddoRegNo);
		}

		if (mstEmployeeNPSModel.getDtoRegNo() != null) {

			char dtoRegNo[] = mstEmployeeNPSModel.getDtoRegNo().toCharArray();
			model.addAttribute("dtoRegNo", dtoRegNo);
		}

//		model.addAttribute("phoneNo", phoneNo);
		model.addAttribute("mstEmployeeNPSModel", mstEmployeeNPSModel);

		return "/views/print-csrf-form";
	}*/

	public String NpsFileCreation(List<MstEmployeeNPSEntity> mstNpsEmpList, String userName, String ddoLevel3,
			int records, OrgUserMst messages) throws Exception {
		boolean flag = false;
		BufferedWriter output = null;
		String ddoCode = userName; // trn_nps_reg_file_id_seq

		Long lLngEmpId = csrfFormService.getNextSeqNum("nps_txt_file_seq_no");
		String deptName = "";
		String offcName = "";
		String deptShortName = "";
		String scalDesc = "";
		BigDecimal basic = null;
		Integer basicPay = 0;
		Integer svnBasic = 0;
		BigInteger svnPcLvl = null;
		BigDecimal svnPcBasic = null;
		Integer svnLevel = 0;
		String empClass = "";
		String str = "";
		String strnew = "";
		String ackno = "";

		// important code for path start
		// Get Image start
		String key = "";
		String rootPath = "";
		String strOSName = System.getProperty("os.name");
		boolean test = strOSName.contains("Windows");
		if (strOSName.contains("Windows")) {
			// key = "serverempconfigimagepath";
		} else {
			key = "npsfilepathinLinusOS";
			OUTPUT_ZIP_FILE = environment.getRequiredProperty(key);
		}

		System.out.println("file path>>>" + OUTPUT_ZIP_FILE);
		// path code end //

		String ddoLevel2 = csrfFormService.getDDOLevel2FromDDO1(userName);
		List<Object[]> DeptLst = csrfFormService.getDeptNameFromDDO2(ddoLevel2);
		for (Object[] objects : DeptLst) {
			deptName = objects[0].toString();
			if (deptName.length() >= 40) {
				deptName = deptName.substring(0, 39);
			}
			offcName = objects[1].toString();
			if (offcName.length() >= 40) {
				offcName = offcName.substring(0, 39);
			}
			deptShortName = objects[2].toString();
		}
		try {
			// ddoCode="06130900002";

			String Path = OUTPUT_ZIP_FILE;
			String directoryName = Path.concat(ddoCode);

			String fileName = "subscriberRegNsdl.txt";

			final int THUMB_SIDE = 360;

			File directory = new File(directoryName);
			if (!directory.exists()) {
				directory.mkdir();
			}
			directoryName = directoryName.concat("/" + lLngEmpId.toString());
			directory = new File(directoryName);
			if (!directory.exists()) {
				directory.mkdir();
			}

			System.out.println("file creation path>>>" + directory);

			File statText = new File(directoryName + "/" + fileName);
			FileOutputStream is = new FileOutputStream(statText);
			OutputStreamWriter osw = new OutputStreamWriter(is);
			Long regSeq = 1L;
			Writer w = new BufferedWriter(osw);
			MstEmployeeNPSEntity mstNpsEmp;

			int empListSize = mstNpsEmpList.size();

			if (records != 0) {
				if (empListSize > records) {
					empListSize = records;
				}

			}

			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String strDate = formatter.format(date);
			System.out.println(strDate);

			Integer trnRegSeq = csrfFormService.getRegSeqNo(strDate);
			if (trnRegSeq != null) {
				regSeq = trnRegSeq + 1L;
			}
			Long sequence = csrfFormService.getNextSeqNum("employee_nps_mst_employee_nps_id_seq");

			w.write("000001^FH^PRAN^R^" + new SimpleDateFormat("MMddyyyy").format(new Date()) + "^"
					+ String.format("%03d", regSeq) + "^" + String.format("%06d", empListSize) + "^NCRA");

			int count = 1;
			String dto_reg_no = null;
			for (int listCnt = 0; listCnt < mstNpsEmpList.size(); listCnt++) {
				int num = listCnt + 1;
				String var = String.format("%06d", num);
				if (mstNpsEmpList.size() > 0 && listCnt < records) {

					count = count + 1;
					Long nextPran = csrfFormService.getNextSeqNum("pran_no_sequence");

					mstNpsEmp = mstNpsEmpList.get(listCnt);

					System.out.println("ddocode=" + mstNpsEmp.getDdoCode());

					// try {
					dto_reg_no = csrfFormService.getDtoRegNumber(mstNpsEmp.getDdoCode()); // mstNpsEmp.getDtoRegNo();

					// dtoRegNo=mstEmployeeService.getDtoRegNumber(messages.getUserName());
					String ddoRegNo = csrfFormService.getDdoRegNumber(messages.getUserName());

					/*
					 * }catch(Exception e) { System.out.println(e.getMessage());
					 * System.out.println(e.getStackTrace()); }
					 */
					// String ackNo=dto_reg_no+"100"+String.format("%07d", nextPran);

					String ackNo = dto_reg_no + "100" + String.format("%07d", nextPran);

					TrnNpsRegFileEntity trnNpsRegFile = new TrnNpsRegFileEntity();
					trnNpsRegFile.setBatchFixId(lLngEmpId.intValue());
					trnNpsRegFile.setAckNo(new BigInteger(ackNo));
					trnNpsRegFile.setNpsId(mstNpsEmp.getEmployeeNPSId());
					trnNpsRegFile.setIsActive("1");
					trnNpsRegFile.setCreatedDate(new Date());
					trnNpsRegFile.setCreatedUserId(messages.getUserId());
					trnNpsRegFile.setRefSeq(regSeq.intValue());
					trnNpsRegFile.setTotalEmpInBatch(records);

					csrfFormService.saveTrnNpsRegFile(trnNpsRegFile);

					// added by sudhir
					List<Object[]> empData = csrfFormService.getempData(mstNpsEmp.getSevaarthId());
					for (Object[] objects : empData) {
						if (objects[3] != null) {
							scalDesc = objects[3].toString();
						}
						if (objects[0] != null) {
							basic = (BigDecimal) objects[0];
							basicPay = basic.intValue();
						}
						if (objects[1] != null) {
							svnPcBasic = (BigDecimal) objects[1];
							svnBasic = svnPcBasic.intValue();
						}
						if (objects[2] != null) {
							svnPcLvl = (BigInteger) objects[2];
							svnLevel = svnPcLvl.intValue();
						}
						empClass = objects[4].toString();

					}

					Integer emppClassid = mstNpsEmp.getEmpClass();
					switch (emppClassid) {
					case 1:
						empClass = "A";
						break;
					case 2:
						empClass = "B";
						break;
					case 3:
						empClass = "BnGz";
						break;
					case 4:
						empClass = "C";
						break;
					case 5:
						empClass = "D";
						break;

					}

					// end by sudhir
					// trnNpsRegFile.setId(sequence.intValue());
					// csrfFormService.saveTrnNpsRegFile(trnNpsRegFile);
					String Title = mstNpsEmp.getSalutation();
					switch (Title) {
					case "1":
						mstNpsEmp.setTitle("Shri.");
						break;
					case "2":
						mstNpsEmp.setTitle("Smt.");
						break;
					case "3":
						mstNpsEmp.setTitle("Kum.");
						break;
					case "4":
						mstNpsEmp.setTitle("Mr.");
						break;
					case "5":
						mstNpsEmp.setTitle("Mrs.");
						break;
					case "6":
						mstNpsEmp.setTitle("Mast");
						break;
					case "7":
						mstNpsEmp.setTitle("Dr");
						break;
					}

					String telNo = mstNpsEmp.getEmpPhoneNo() != null ? mstNpsEmp.getEmpPhoneNo() : "";
					w.write("\r\n" + String.format("%06d", count) + "^" + "FD" // mstNpsEmp.getFileSecType() //FD/ND/DD
							+ "^" + "N" + "^" + var + "^^" + ackNo // mstNpsEmp.getAckNo()
							+ "^" + mstNpsEmp.getTitle() // MS//
							+ "^" + mstNpsEmp.getEmployeeFirstName() // mstNpsEmp.getSubcName()
							+ "^" + mstNpsEmp.getEmployeeLastName() // mstNpsEmp.getSubcName()
							+ "^" + "^" + mstNpsEmp.getEmployeeFatherHubandName() + "^^^" + dto_reg_no + "^" + ddoRegNo
							+ "^" + mstNpsEmp.getEmployeeGender() + "^"
							+ new SimpleDateFormat("MMddyyyy").format(mstNpsEmp.getEmployeeDOB()) + "^"
							+ mstNpsEmp.getEmployeePan() + "^" + mstNpsEmp.getFlatUnitNo() // getCoAdd1()
							+ "^"
							// + mstNpsEmp.getBuildingName() //getCoAdd2()
							+ "^"
							// + mstNpsEmp.getLocality() //getCoAdd3()
							+ "^" + "172"// mstNpsEmp.getDistrict() //getCoCity()
							+ "^"
							// + mstNpsEmp.getFlatUnitNo() // getCoRoad
							+ "^"
							// + mstNpsEmp.getLocality() //getCoLandmark
							+ "^" + "19"// mstNpsEmp.getState() //getCoState
							+ "^" + mstNpsEmp.getCountry() // getCoCountry
							+ "^" + mstNpsEmp.getPinCode() // getCoPin
							+ "^" + mstNpsEmp.getEmpPermanentFlatUnitNo() // getPerAdd1()
							+ "^"
							// + mstNpsEmp.getEmpPermanentBuildingName() //getPerAdd2()
							+ "^"
							// + mstNpsEmp.getEmpPermanentBuildingName() //getPerAdd3()
							+ "^" + "172" // mstNpsEmp.getEmpPermanentDistrict() //getPerCity
							+ "^"
							// + mstNpsEmp.getEmpPermanentFlatUnitNo() //getPerRoad
							+ "^"
							// + mstNpsEmp.getEmpPermanentLocality() //getPerLandmark
							+ "^" + "19" // mstNpsEmp.getEmpPermanentState() //getPerState
							+ "^" + "IN"// mstNpsEmp.getEmpPermanentCountry() //getPerCountry
							+ "^" + mstNpsEmp.getEmpPermanentPinCode() // getPerPin
							+ "^"
							// +telNo
							+ "^" + mstNpsEmp.getEmpMobileNo() + "^^"
							// + mstNpsEmp.getEmpEmailId()
							+ "^" + "Y" // mstNpsEmp.getSmsSFlag()
							+ "^" + "Y" // mstNpsEmp.getEmailSFlag()
							+ "^" + new SimpleDateFormat("MMddyyyy").format(mstNpsEmp.getEmployeeDOJ()) + "^"
							+ new SimpleDateFormat("MMddyyyy").format(mstNpsEmp.getEmployeeDateOfRetirement()) + "^"
							+ empClass + "^" + deptName + "^" + offcName // mstNpsEmp.getMinistry()
							+ "^" + offcName // mstNpsEmp.getDdoOffName()
							+ "^" + scalDesc // mstNpsEmp.getPayScaleDesc()
							+ "^" + basicPay + "^" + mstNpsEmp.getEmployeeId() + "^" + "N" // mstNpsEmp.getInBankFlag()
							+ "^" + "Savings" // mstNpsEmp.getBankType()
							+ "^" + mstNpsEmp.getEmployeeBankAccountNo() + "^" + mstNpsEmp.getEmployeeBankName() + "^"
							+ mstNpsEmp.getEmployeeBankBranchName() + "^"
							+ mstNpsEmp.getEmployeeBankBankAddress().replaceAll("[^a-zA-Z0-9]", " ") + "^" + "19" // mstNpsEmp.getBankState()
							+ "^" + "IN" // mstNpsEmp.getBankCountry()
							+ "^" + mstNpsEmp.getEmployeeBankPinCode() + "^^" + "1" // mstNpsEmp.getNumNominess()
							+ "^" + "000" // mstNpsEmp.getNumSchemes()
							+ "^^^^^^" + mstNpsEmp.getEmployeeMotherName() + "^^^" + "N" // mstNpsEmp.getHindiFlag()
							+ "^^^^^^^" + mstNpsEmp.getIFSCCode() + "^" + "N" // mstNpsEmp.getComFromFlag()
							+ "^" + "^^^^^^^^" // mstNpsEmp.getAadhar()

							+ "O" // mstNpsEmp.getSecTypeFlag()
							+ "^" + "S" // mstNpsEmp.getFundRatio()
							+ "^^^" + "N"// mstNpsEmp.getPpanNo()
							+ "^" + "4" // mstNpsEmp.getMinUploadIndicator()
							+ "^" + "F" // mstNpsEmp.getDisNameFlag()
							+ "^" + "00" // mstNpsEmp.getSotLanguageCode()
							+ "^^^" + "Y"
							// +mstNpsEmp.getDobProDoc()
							+ "^^^^"
							/*
							 * +"150" +"^"
							 */
							+ "119" + "^^^^^^^^^^^^^" + "N" + "^" + "RI" + "^^" + "M" // mstNpsEmp.getEmployeeMaritalStatus()
							+ "^^" + "Y" // mstNpsEmp.getAntiLaun() + "^"
							+ "^" + "N" // + mstNpsEmp.getDobProof()
							+ "^^^" + "6" // mstNpsEmp.getEmployeeBirthPlace()
							+ "^" + "IN" + "^" // mstNpsEmp .getIsoCountry()
							+ "R" + "^" // mstNpsEmp.getPerAddreddType() + "^"
							+ "R" + "^^" // + telNo + "^" // mstNpsEmp.getCorAddressType() + "^" + telNo + "^"
							+ "Y" // mstNpsEmp.getCancelledCheque() + "^"
							+ "^" + "RI" // mstNpsEmp.getNationality() + "^"
							+ "^" + "1" + "^" // mstNpsEmp.getFatcaDecCount() + "^"
							+ "Y" + "^" // mstNpsEmp.getFatcaDec() + "^N^"
							+ "N" + "^" + "N" + "\r\n"); // mstNpsEmp.getNodalConsent()

					// end sudhir

					if (mstNpsEmp.getEmpNominee1Name() != null) {
						count = count + 1;
						String gardian = mstNpsEmp.getEmpNominee1GuardName() != null
								? mstNpsEmp.getEmpNominee1GuardName()
								: "";
						String nom1Date = "";
						if (mstNpsEmp.getEmpNominee1DOB() != null) {
							nom1Date = new SimpleDateFormat("MMddyyyy").format(mstNpsEmp.getEmpNominee1DOB());
						}
						w.write(String.format("%06d", count) + "^ND^N^01^01^" +

								mstNpsEmp.getEmpNominee1Name() + "^^^" + nom1Date + "^"
								+ mstNpsEmp.getEmpNominee1relationship() + "^" + mstNpsEmp.getNominee1MajorMinor() + "^" // +
																															// mstNpsEmp.getNominee1MajorMinor()
																															// +
																															// "^"
								+ gardian + "^^^" +

								mstNpsEmp.getEmpNominee1Share() + "^^" + mstNpsEmp.getAddressOfTax() + "^^^^^^\r\n");
					}

					if (mstNpsEmp.getEmpNominee2Name() != null) {
						count = count + 1;
						String gardian = mstNpsEmp.getEmpNominee2GuardName() != null
								? mstNpsEmp.getEmpNominee2GuardName()
								: "";
						String nom2Date = "";
						if (mstNpsEmp.getEmpNominee2DOB() != null) {
							nom2Date = new SimpleDateFormat("MMddyyyy").format(mstNpsEmp.getEmpNominee2DOB());
						}
						w.write(String.format("%06d", count) + "^ND^N^02^02^" + mstNpsEmp.getEmpNominee2Name() + "^^^"
								+ nom2Date + "^" + mstNpsEmp.getEmpNominee2relationship() + "^"
								+ mstNpsEmp.getNominee2MajorMinor() + "^" // mstNpsEmp.getNominee2MajorMinor() + "^"
								+ gardian + "^^^" +
								// gardian+"^^^"+
								mstNpsEmp.getEmpNominee2Share() + "^^" + "Thane" + "^^^^^^\r\n"); // mstNpsEmp.getNom2add()
						// + mstNpsEmp.getAddressOfTax() + "^^^^^^\r\n"); //mstNpsEmp.getNom2add()
					}
					if (mstNpsEmp.getEmpNominee3Name() != null) {
						count = count + 1;
						String gardian = mstNpsEmp.getEmpNominee3GuardName() != null
								? mstNpsEmp.getEmpNominee3GuardName()
								: "";
						String nom3Date = "";
						if (mstNpsEmp.getEmpNominee3DOB() != null) {
							nom3Date = new SimpleDateFormat("MMddyyyy").format(mstNpsEmp.getEmpNominee3DOB());
						}

						w.write(String.format("%06d", count) + "^ND^N^03^03^" + mstNpsEmp.getEmpNominee3Name() + "^^^"
								+ nom3Date + "^" + mstNpsEmp.getEmpNominee3relationship() + "^"
								+ mstNpsEmp.getNominee3MajorMinor() + "^" // mstNpsEmp.getNominee2MajorMinor() + "^"
								+ gardian + "^^^" +

								mstNpsEmp.getEmpNominee3Share() + "^^" + mstNpsEmp.getAddressOfTax() + "^^^^^^\r\n"); // mstNpsEmp.getNom3add()
					}
					count = count + 1;

					w.write(String.format("%06d", count) + "^DD^N^01^IN^" + "1" + "^" // + mstNpsEmp.getResStatus() +
																						// "^"
							+ mstNpsEmp.getEmployeePan() + "^IN^^" + mstNpsEmp.getEmpPermanentState() + "^"
							+ mstNpsEmp.getEmpPermanentDistrict() + "^" + mstNpsEmp.getEmpPermanentPinCode());

					/*
					 * mstNpsEmp.setStatus(4L); mstNpsEmp.setNpsId(mstNpsEmp.getNpsId());
					 * npsDao.update(mstNpsEmp);
					 */
					Path photoPath = Paths.get(mstNpsEmp.getEmployeePhotoAttachment());
					Path signPath = Paths.get(mstNpsEmp.getEmployeeSignAttachment());

					System.out.println("db file path photoPath>>>" + mstNpsEmp.getEmployeePhotoAttachment());
					System.out.println("db file path signPath>>>" + mstNpsEmp.getEmployeeSignAttachment());

					try {
						if (signPath != null) {
							File img = new File(mstNpsEmp.getEmployeeSignAttachment());
							BufferedImage image = ImageIO.read(img);

							System.out.println("image readed from db path");

							BufferedImage thumbImage = new BufferedImage(THUMB_SIDE, THUMB_SIDE,
									BufferedImage.TYPE_INT_ARGB);
							Graphics2D g2d = thumbImage.createGraphics();
							g2d.drawImage(image.getScaledInstance(THUMB_SIDE, THUMB_SIDE, Image.SCALE_SMOOTH), 0, 0,
									THUMB_SIDE, THUMB_SIDE, null);
							g2d.dispose();
							String Path1 = OUTPUT_ZIP_FILE;
							String directoryName1 = Path1.concat(ddoCode + "/" + lLngEmpId);
							File directory1 = new File(directoryName1);
							if (!directory1.exists()) {
								directory1.mkdir();
							}
							String directoryName2 = null;
							if (directory1.exists()) {
								directoryName2 = directoryName1.concat("/subscriberRegNsdl_sig");
								File directory2 = new File(directoryName2);
								if (!directory2.exists()) {

									directory2.mkdir();
								}

							}
							File output1 = new File(directoryName2 + "/" + ackNo + "_sig.jpg");
							OutputStream out = new FileOutputStream(output1);
							ImageWriter writer = ImageIO.getImageWritersByFormatName("jpg").next();
							ImageOutputStream ios = ImageIO.createImageOutputStream(out);
							writer.setOutput(ios);

							ImageWriteParam param = writer.getDefaultWriteParam();

							if (param.canWriteCompressed()) {
								param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
								param.setCompressionQuality(0.005f);
							}
							writer.write(null, new IIOImage(thumbImage, null, null), param);
							out.close();
							ios.close();
							writer.dispose();
						}
					} catch (Exception e) {
						System.out.println(e.getMessage());

						System.out.println("error in file  reade");
					}
					try {
						if (photoPath != null) {
							File img = new File(mstNpsEmp.getEmployeePhotoAttachment());
							BufferedImage image = ImageIO.read(img);
							BufferedImage thumbImage = new BufferedImage(THUMB_SIDE, THUMB_SIDE,
									BufferedImage.TYPE_INT_ARGB);
							Graphics2D g2d = thumbImage.createGraphics();
							g2d.drawImage(image.getScaledInstance(THUMB_SIDE, THUMB_SIDE, Image.SCALE_SMOOTH), 0, 0,
									THUMB_SIDE, THUMB_SIDE, null);
							g2d.dispose();
							String Path1 = OUTPUT_ZIP_FILE;

							System.out.println("db file path signPath>>>" + OUTPUT_ZIP_FILE);

							String directoryName1 = Path1.concat(ddoCode + "/" + lLngEmpId);
							File directory1 = new File(directoryName1);
							if (!directory1.exists()) {
								directory1.mkdir();
							}
							String directoryName2 = null;
							if (directory1.exists()) {
								directoryName2 = directoryName1.concat("/subscriberRegNsdl_photo");
								File directory2 = new File(directoryName2);
								if (!directory2.exists()) {
									directory2.mkdir();
								}
							}

							File output1 = new File(directoryName2 + "/" + ackNo + "_photo.jpg");
							OutputStream out = new FileOutputStream(output1);
							ImageWriter writer = ImageIO.getImageWritersByFormatName("jpg").next();
							ImageOutputStream ios = ImageIO.createImageOutputStream(out);
							writer.setOutput(ios);

							ImageWriteParam param = writer.getDefaultWriteParam();
							if (param.canWriteCompressed()) {
								param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
								param.setCompressionQuality(0.005f);
							}

							writer.write(null, new IIOImage(thumbImage, null, null), param);

							out.close();
							ios.close();
							writer.dispose();
						}
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
			}
			w.close();
		} catch (IOException ioe) {
			System.out.println("Error while Creating File in Java" + ioe);
		}
		String successMsg = "File Created Successfully";
		return String.valueOf(lLngEmpId);
	}

	// send file to nsdl for pran number
	@PostMapping("/sendFile")
	public String sendFile(@ModelAttribute("trnNpsRegFileEntity") TrnNpsRegFileEntity trnNpsRegFileEntity, Model model,
			Locale locale, HttpSession session, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		// throws ServletException, KeyManagementException, NoSuchAlgorithmException,
		// ClassNotFoundException, SQLException, IOException

		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		String ddoCode = messages.getUserName();
		String refNo = null;
		String batchId = trnNpsRegFileEntity.getBatchFixId().toString();
		String dtoUserId = "112563230";

		String ddoLevel2 = csrfFormService.getDDOLevel2FromDDO1(ddoCode);

		String dtoRegNo = csrfFormService.getDtoRegNumber(ddoLevel2);

		try {
			String nsdlStatusCode = nSDLIntegration.sendFile(response, ddoCode, batchId, dtoUserId, dtoRegNo);

			if (nsdlStatusCode != null && !nsdlStatusCode.isEmpty()) {

				trnNpsRegFileEntity = csrfFormService.findTrnNpsFileEntityById(trnNpsRegFileEntity.getId());
				trnNpsRegFileEntity.setNsdlStatusCode(nsdlStatusCode);

				csrfFormService.updateTrnNpsFileEntity(trnNpsRegFileEntity);

				redirectAttributes.addFlashAttribute("message",
						"File send Successfully to nsdl having status code " + nsdlStatusCode);
			} else {
				redirectAttributes.addFlashAttribute("message", "Something went wrong Please try again !!!");
			}

		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "redirect:/master/getPranNumber";
	}

	// send file to nsdl for pran number
	@PostMapping("/getPranNumbers")
	public String getPranNumbers(@ModelAttribute("trnNpsRegFileEntity") TrnNpsRegFileEntity trnNpsRegFileEntity,
			Model model, Locale locale, HttpSession session, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		// throws ServletException, KeyManagementException, NoSuchAlgorithmException,
		// ClassNotFoundException, SQLException, IOException

		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		String ddoCode = messages.getUserName();
		String refNo = null;
		String batchId = trnNpsRegFileEntity.getBatchFixId().toString();
		String dtoUserId = "112563230";

		String ddoLevel2 = csrfFormService.getDDOLevel2FromDDO1(ddoCode);

		String dtoRegNo = csrfFormService.getDtoRegNumber(ddoLevel2);
		String nsdlStatuscode = trnNpsRegFileEntity.getNsdlStatusCode();

		try {
			// String nsdlStatusCode=nSDLIntegration.sendFile(response, ddoCode, batchId,
			// dtoUserId, dtoRegNo);
			String transactionno = nSDLIntegration.getStatus(response, ddoCode, nsdlStatuscode, dtoUserId, dtoRegNo);

			if (transactionno.equals("Success")) {
				redirectAttributes.addFlashAttribute("message", "Pran Number generated Successfully !!!");
			} else {
				redirectAttributes.addFlashAttribute("message", "error");
			}

			// csrfFormService.updateTrnNpsFileEntity(trnNpsRegFileEntity);

		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "redirect:/master/getPranNumber";
	}
	
	
	/*@GetMapping("/viewCSRFSign/{employeeId}")
	void viewCSRFSign(HttpServletResponse response, @PathVariable int employeeId) throws IOException {
		try {
			if (employeeId != 0) {
				String fileName = null;
				String filePath = null;
				List<Object[]> photodtl = csrfFormService.viewCSRFPhotoSign(employeeId);
				// mstEmployeeModel=empChangeDetailsService.getEmpData(empId);
				for (Object[] obj : photodtl) {
					fileName = (String) obj[1];
					filePath = obj[1].toString();
				}
				if (photodtl.size() > 0) {

					File file = new File(filePath);
					FileInputStream inputStream = new FileInputStream(file);

					if (filePath.contains("pdf")) {
						response.setContentType("application/pdf");
					} else if (filePath.contains("jpg") || filePath.contains("jpeg")) {
						response.setContentType("image/jpeg");
					} else if (filePath.contains("png")) {
						response.setContentType("image/png");
					} else if (filePath.contains("gif")) {
						response.setContentType("image/gif");
					} else if (filePath.contains("mp4")) {
						response.setContentType("video/mp4");
					}
					response.setContentLength((int) file.length());
					response.setHeader("Content-Disposition", "inline;filename=\"" + file.getName() + "\"");
					FileCopyUtils.copy(inputStream, response.getOutputStream());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@GetMapping("/viewCSRFPhoto/{employeeId}")
	void viewCSRFPhoto(HttpServletResponse response, @PathVariable int employeeId) throws IOException {
		try {
			if (employeeId != 0) {
				String fileName = null;
				String filePath = null;
				List<Object[]> photodtl = csrfFormService.viewCSRFPhotoSign(employeeId);
				// mstEmployeeModel=empChangeDetailsService.getEmpData(empId);
				for (Object[] obj : photodtl) {
					fileName = (String) obj[0];
					filePath = obj[0].toString();
				}
				if (photodtl.size() > 0) {
					
					File file = new File(filePath);
					FileInputStream inputStream = new FileInputStream(file);
					
					if (filePath.contains("pdf")) {
						response.setContentType("application/pdf");
					} else if (filePath.contains("jpg") || filePath.contains("jpeg")) {
						response.setContentType("image/jpeg");
					} else if (filePath.contains("png")) {
						response.setContentType("image/png");
					} else if (filePath.contains("gif")) {
						response.setContentType("image/gif");
					} else if (filePath.contains("mp4")) {
						response.setContentType("video/mp4");
					}
					response.setContentLength((int) file.length());
					response.setHeader("Content-Disposition", "inline;filename=\"" + file.getName() + "\"");
					FileCopyUtils.copy(inputStream, response.getOutputStream());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

}
