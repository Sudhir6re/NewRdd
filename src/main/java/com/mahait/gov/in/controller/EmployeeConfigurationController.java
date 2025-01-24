package com.mahait.gov.in.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mahait.gov.in.common.CommonConstants;
import com.mahait.gov.in.common.CommonConstants.STATUS;
import com.mahait.gov.in.common.CommonUtils;
import com.mahait.gov.in.entity.CmnLookupMst;
import com.mahait.gov.in.entity.MstDesignationEntity;
import com.mahait.gov.in.entity.MstEmployeeDetailEntity;
import com.mahait.gov.in.entity.MstGpfDetailsEntity;
import com.mahait.gov.in.entity.MstNomineeDetailsEntity;
import com.mahait.gov.in.entity.MstPayCommissionEntity;
import com.mahait.gov.in.entity.OrgDdoMst;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.entity.ReligionMstEntity;
import com.mahait.gov.in.fileupload.FileSecurityHandler;
import com.mahait.gov.in.model.DDOScreenModel;
import com.mahait.gov.in.model.MstDistrictModel;
import com.mahait.gov.in.model.MstEmployeeModel;
import com.mahait.gov.in.model.MstNomineeDetailsModel;
import com.mahait.gov.in.model.MstStateModel;
import com.mahait.gov.in.service.CommonHomeMethodsService;
import com.mahait.gov.in.service.CreateAdminOfficeService;
import com.mahait.gov.in.service.DDOScreenService;
import com.mahait.gov.in.service.EmpChangeDetailsService;
import com.mahait.gov.in.service.LocationMasterService;
import com.mahait.gov.in.service.MstBankService;
import com.mahait.gov.in.service.MstDepartmentService;
import com.mahait.gov.in.service.MstDesignationService;
import com.mahait.gov.in.service.MstEmployeeService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
// @RequestMapping("/ddoast")
@RequestMapping(value = { "/ddoast", "/ddo" ,"/level3"})
@PropertySource(value = { "classpath:application.properties" })
public class EmployeeConfigurationController extends BaseController {

	@Autowired
	CreateAdminOfficeService createAdminOfficeService;

	// @Autowired
	// MstCadreService mstCadreService;

	@Autowired
	MstEmployeeService mstEmployeeService;

	@Autowired
	MstDesignationService mstDesignationService;

	@Autowired
	MstBankService mstBankService;

	// @Autowired
	// MstBankBranchService mstBankBranchService;

	@Autowired
	CommonHomeMethodsService commonHomeMethodsService;

	@Autowired
	LocationMasterService locationMasterService;

	@Autowired
	MstDepartmentService mstDepartmentService;

	// @Autowired
	// MstSubDepartmentService mstSubDepartmentService;

	@Autowired
	private Environment environment;

	@Autowired
	EmpChangeDetailsService empChangeDetailsService;

	// @Autowired
	// MstSubCorporationService mstSubCorporationService;

	@Autowired
	DDOScreenService dDOScreenService;

	// protected final Log logger = LogFactory.getLog(getClass());
	// @PostMapping("/employeeConfiguration")
	@RequestMapping(value = "/employeeConfiguration", method = { RequestMethod.GET })
	public String employeeConfiguration(@ModelAttribute("mstEmployeeModel") MstEmployeeModel mstEmployeeModel,
			Model model, Locale locale, HttpSession session) {

		String message = (String) model.asMap().get("message");
		mstEmployeeModel.setAction("Save");

		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		mstEmployeeModel.setDdoCode(messages.getDdoCode());
		long locId = Long.parseLong((String) session.getAttribute("locationId"));

		List<OrgDdoMst> lstDDO = dDOScreenService.findDDOByUsername(messages.getUserName());


		List<DDOScreenModel> lstDepartment = mstEmployeeService.findDDOScreenDataTable(locale.getLanguage(), locId);

		List<Object[]> districtListAgainstStateId = locationMasterService.findAllDistricts(15);
		model.addAttribute("districtListAgainstStateId", districtListAgainstStateId);
		mstEmployeeModel.setStateCode(15l); // Default state maharashtra
		model.addAttribute("mstEmployeeModel", mstEmployeeModel);

		List<ReligionMstEntity> mstReligionLst = new ArrayList<>();
		mstReligionLst = commonHomeMethodsService.fetchAllReligions();
		model.addAttribute("mstReligionLst", mstReligionLst);

		List<CmnLookupMst> lookupLst = new ArrayList<>();
		lookupLst = commonHomeMethodsService
				.findCommonMstByLookupname(CommonConstants.COMMONMSTTABLE.COMMONCODE_SALUTATIONS);
		model.addAttribute("lstCommonMstSalutation", lookupLst);

		if (message != null && message.equals("SUCCESS")) {
			if (locale != null && locale.getLanguage().equalsIgnoreCase("en")) {
				model = CommonUtils.initModel(CommonConstants.Message.SAVEDRAFT, STATUS.SUCCESS, model);
			} else {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_MARATHI, STATUS.SUCCESS, model);
			}
		}
		if (message != null && message.equals("FRWDDDO")) {
			if (locale != null && locale.getLanguage().equalsIgnoreCase("en")) {
				model = CommonUtils.initModel(CommonConstants.Message.FRWDDDO, STATUS.SUCCESS, model);
			} else {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_MARATHI, STATUS.SUCCESS, model);
			}
		}

		if (message != null && message.equals("DRAFTCASE")) {
			if (locale != null && locale.getLanguage().equalsIgnoreCase("en")) {
				model = CommonUtils.initModel(CommonConstants.Message.DRAFTCASE, STATUS.SUCCESS, model);
			} else {
				model = CommonUtils.initModel(CommonConstants.Message.DRAFTCASE, STATUS.SUCCESS, model);
			}
		}


		model.addAttribute("lstAdminOfficeMst", lstDepartment);

		model.addAttribute("lstCadreMst", mstEmployeeService.getCadreMstData(locale.getLanguage(), locId));
		model.addAttribute("language", locale.getLanguage());

		List<MstPayCommissionEntity> lstddcPayCommission = mstDesignationService.findAllPayCommission();
		model.addAttribute("lstddcPayCommission", lstddcPayCommission);
		model.addAttribute("lstDesignation", mstEmployeeService.getDesignationMstData(locale.getLanguage(), locId));
		model.addAttribute("lstAppointment", mstEmployeeService.getAppoitnment(locale.getLanguage()));
	//	model.addAttribute("lstQualification", mstEmployeeService.getQualification(locale.getLanguage()));

		List<CmnLookupMst> lstDcpccnMaintainby = new ArrayList<>();
		lstDcpccnMaintainby = commonHomeMethodsService
				.findCommonMstByLookupname(CommonConstants.COMMONMSTTABLE.COMMONCODE_DCPSACCMAINTAINEDBY);
		model.addAttribute("lstDcpsAccnMaintainby", lstDcpccnMaintainby);


		List<CmnLookupMst> lstAccnMaintainby = new ArrayList<>();
		lstAccnMaintainby = commonHomeMethodsService
				.findCommonMstByLookupname(CommonConstants.COMMONMSTTABLE.COMMONCODE_GPFACCMAINTAINEDBY);
		model.addAttribute("lstAccountMaintainby", lstAccnMaintainby);
		model.addAttribute("lstCurntDepartment", lstDepartment);

		List<CmnLookupMst> lstgisapplicable = new ArrayList<>();
		lstgisapplicable = commonHomeMethodsService
				.findCommonMstByLookupname(CommonConstants.COMMONMSTTABLE.COMMONCODE_GISAPPLICABLE);
		model.addAttribute("lstGISApplicable", lstgisapplicable);

		model.addAttribute("lstGISGroup", mstEmployeeService.getGISGroup());

		model.addAttribute("lstRelation", mstEmployeeService.getRelation());
		model.addAttribute("lstAllBankList", mstBankService.lstAllBank());
		// model.addAttribute("lstCommonMstSalutation", commonHomeMethodsService
		// .findCommonMstByCommonCode(CommonConstants.COMMONMSTTABLE.COMMONCODE_SALUTATIONS));
		model.addAttribute("lstCommonMstGIS",
				commonHomeMethodsService.findCommonMstByCommonCode(CommonConstants.COMMONMSTTABLE.COMMONCODE_GIS));
		// model.addAttribute("lstCommonMstGender",
		// commonHomeMethodsService.findCommonMstByCommonCode(CommonConstants.COMMONMSTTABLE.COMMONCODE_GENDER));
		List<MstStateModel> listStatemdl = new ArrayList<MstStateModel>();
		List<Object[]> listState = locationMasterService.findAllStates(1);
		for (Iterator iterator = listState.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			MstStateModel mstStateModel = new MstStateModel();
			mstStateModel.setStateCode(Integer.parseInt(String.valueOf(objects[0])));
			mstStateModel.setStateNameEn(String.valueOf(objects[1]));
			listStatemdl.add(mstStateModel);
		}

		model.addAttribute("lstAllState", listStatemdl);

		model.addAttribute("language", locale.getLanguage());
		LocalDate date = LocalDate.now();
		model.addAttribute("currentDate", date);

		addMenuAndSubMenu(model, messages);
		return "/views/employee-configuration";
	}

	/* @GetMapping(value="/employeeConfiguration/{cadreId}") */

	@GetMapping(value = "/fetchDistricts/{stateId}")
	public @ResponseBody List<Object[]> fetchDistricts(@PathVariable long stateId, Model model, Locale locale) {
		List<Object[]> locationMasterStateList = locationMasterService.findAllDistricts(stateId);
		return locationMasterStateList;
	}

	@RequestMapping("/fetchPayscale/{payCommission}")
	public @ResponseBody List<Object[]> employeeConfigurationGetpayScale(@PathVariable int payCommission, Model model,
			Locale locale) {
		List<Object[]> employeeConfigurationService = null;
		if (payCommission == 2500341 || payCommission == 700016) {
			employeeConfigurationService = mstEmployeeService.findEmployeeConfigurationGetSixPayScale(2500341);
		}
		if (payCommission == 2500347 || payCommission == 700005) {
			employeeConfigurationService = mstEmployeeService.findEmployeeConfigurationGetpayscale(payCommission);
		} else
			return employeeConfigurationService;
		return employeeConfigurationService;
	}

	@RequestMapping("/fetchcadregroupdtls/{cadreid}")
	public @ResponseBody List<Object[]> fetchcadregroupdtls(@PathVariable String cadreid, Model model, Locale locale) {

		List<Object[]> employeeConfigurationService = mstEmployeeService.getCadreGroupMstData(locale.getLanguage(),
				cadreid);
		
		return employeeConfigurationService;
	}

	@RequestMapping("/fetchcadregroupdtlsDate/{cadreid}/{dob}")
	public @ResponseBody List<MstEmployeeModel> fetchcadregroupdtlsDate(@PathVariable String cadreid,
			@PathVariable String dob, Model model, Locale locale) {

		List<MstEmployeeModel> employeeConfigurationService = mstEmployeeService.getCadreGroupMstDataNew(cadreid, dob);
		return employeeConfigurationService;
	}

	@RequestMapping("/fetchpayScaleSeven/{payScaleSeven}")
	public @ResponseBody List<Object[]> employeepayScaleSevenScale(@PathVariable int payScaleSeven, Model model,
			Locale locale) {

		List<Object[]> employeeConfigurationService = mstEmployeeService
				.findEmployeeConfigurationpayScaleSeven(payScaleSeven);
		return employeeConfigurationService;
	}

	@RequestMapping("/fetchsvnpaybasic/{payscale}")
	public @ResponseBody List<Object[]> employeeConfigurationGetsvnbasicpay(@PathVariable String payscale, Model model,
			Locale locale) {

		List<Object[]> employeeConfigurationService = mstEmployeeService
				.findEmployeeConfigurationGetsvnbasicpay(payscale);
		return employeeConfigurationService;
	}

	@RequestMapping("/employeeConfigurationGetCurrentPost/{designationId}/{postdetailid}")
	public @ResponseBody List<Object[]> employeeConfigurationGetCurrentPost(@PathVariable Long designationId,
			@PathVariable String postdetailid, Model model, Locale locale, HttpSession session) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		long locId = Long.parseLong((String) session.getAttribute("locationId"));
		List<Object[]> employeeConfigurationService = mstEmployeeService.findEmployeeConfigurationGetCurrentPost(
				designationId, messages.getUserName(), postdetailid != null ? postdetailid : "", locId);
		return employeeConfigurationService;
	}

	@RequestMapping("/employeeConfigurationGetCurrenOffice/{postdetailid}")
	public @ResponseBody List<Object[]> employeeConfigurationGetCurrenOffice(@PathVariable long postdetailid,
			Model model, Locale locale, HttpSession session) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		long locId = Long.parseLong((String) session.getAttribute("locationId"));
		List<Object[]> employeeConfigurationService = mstEmployeeService
				.employeeConfigurationGetCurrenOffice(postdetailid, messages.getUserName(), locId);
		return employeeConfigurationService;
	}

	@RequestMapping("/employeeConfigurationGetCurrenOfficeAddress/{adminDepartmentId}")
	public @ResponseBody List<Object[]> employeeConfigurationGetCurrenOfficeAddress(
			@PathVariable long adminDepartmentId, Model model, Locale locale, HttpSession session) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		long locId = Long.parseLong((String) session.getAttribute("locationId"));
		List<Object[]> employeeConfigurationService = mstEmployeeService
				.employeeConfigurationGetCurrenOfficeAddress(adminDepartmentId, messages.getUserName(), locId);
		return employeeConfigurationService;
	}

	@RequestMapping("/fetchbankbranch/{bankid}")
	public @ResponseBody List<Object[]> getBankBranch(@PathVariable String bankid, Model model, Locale locale) {

		List<Object[]> employeeConfigurationService = mstEmployeeService.getBankBranch(bankid);
		return employeeConfigurationService;
	}

	@RequestMapping("/PfSeries/{accmainby}")
	public @ResponseBody List<Object[]> getPfSeries(@PathVariable String accmainby, Model model, Locale locale) {

		List<Object[]> employeeConfigurationService = mstEmployeeService.getPfSeries(accmainby);
		return employeeConfigurationService;
	}

	// @RequestMapping(value = "/getIfscCodeByBranchId/{branchId}", consumes = {
	// "application/json" }, headers = "Accept=application/json", produces =
	// MediaType.APPLICATION_JSON_VALUE)
	// public ResponseEntity<List<MstBankBranchEntity>> getIfscCodeByBranchId(
	// @PathVariable int branchId) {
	// return ResponseEntity
	// .ok(mstEmployeeService.getIfscCodeByBranchId(branchId));
	// }
	@RequestMapping(value = "/getIfscCodeByBranchIdForEmp/{branchId}", consumes = {
			"application/json" }, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getIfscCodeByBranchId(@PathVariable int branchId) {
		return ResponseEntity.ok(commonHomeMethodsService.getIfscCodeByBranchId(branchId));
	}

	@RequestMapping(value = "/saveEmployeeConfiguration", method = { RequestMethod.POST })
	public String saveEmployeeConfiguration(
			@ModelAttribute("mstEmployeeModel") @Valid MstEmployeeModel mstEmployeeModel, BindingResult bindingResult,
			HttpSession session, @RequestParam("files") MultipartFile[] files, RedirectAttributes redirectAttributes,
			Model model, Locale locale) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		
		
		boolean isPhotoSignSafe=FileSecurityHandler.isValidFile("IMAGE", files);

		if(!isPhotoSignSafe) {
			redirectAttributes.addFlashAttribute("message", "Invalid Photo/Signature");
			return "redirect:/ddoast/employeeConfiguration";
		}
		

		String strAction = mstEmployeeModel.getAction();
		if (strAction.equals("EditBack")) {
			return "redirect:/ddoast/employeeDetails";
		}
		if (strAction.equals("Edit")) {
			mstEmployeeModel.setUpdatedUserId(messages.getUserId());
			long afterSaveId = mstEmployeeService.updateEmployeeConfiguration(mstEmployeeModel, files);
			// int result=
			// mstEmployeeService.savePhotoSignature(files,mstEmployeeModel.getDeptNm(),mstEmployeeModel.getEmployeeId());
			if (afterSaveId > 0) {
				if (mstEmployeeModel.getIsActive() == 2) {
					redirectAttributes.addFlashAttribute("message", "SUCCESS");
				} else {
					redirectAttributes.addFlashAttribute("message", "Registration form is forwarded successfully");
				}
			}
			return "redirect:/ddoast/employeeDetails";
			// return "redirect:/level1/employeeDetailsList/{"+message+"}";
		} else {
			mstEmployeeModel.setCreatedUserId(messages.getUserId());
			long afterSaveId = mstEmployeeService.saveEmployeeConfiguration(mstEmployeeModel, files);
			// int saveimage=
			// mstEmployeeService.savePhotoSignature(files,mstEmployeeModel.getDeptNm(),mstEmployeeModel.getEmployeeId());
			if (afterSaveId > 0) {
				if (strAction.equals("saveAsDraft")) {
					redirectAttributes.addFlashAttribute("message", "DRAFTCASE");
				} else {
					redirectAttributes.addFlashAttribute("message", "SUCCESS");
				}
			} else
				redirectAttributes.addFlashAttribute("message", "Registration form is forwarded successfully");
			return "redirect:/ddoast/employeeConfiguration";
		}

	}

	@RequestMapping(value = "/approveEmployeeDetail", method = { RequestMethod.GET, RequestMethod.POST })
	public String getApproveEmployeeDetail(@ModelAttribute("mstEmployeeModel") MstEmployeeModel mstEmployeeModel,
			BindingResult bindingResult, Model model, Locale locale, HttpSession session) {
		String message = (String) model.asMap().get("message");
		model.addAttribute("mstEmployeeModel", mstEmployeeModel);

		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		long locId = Long.parseLong((String) session.getAttribute("locationId"));

		if (message != null && message.equals("SUCCESS")) {
			if (locale != null && locale.getLanguage().equalsIgnoreCase("en")) {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_ENGLSH, STATUS.SUCCESS, model);
			} else {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_MARATHI, STATUS.SUCCESS, model);
			}
		}
		// model.addAttribute("lstAdminOfficeMst",
		// createAdminOfficeService.lstAdminOfficeMst());
		// model.addAttribute("lstCadreMst",
		// mstCadreService.getCadreMstData(locale.getLanguage()));
		model.addAttribute("language", locale.getLanguage());
		String strddo = messages.getDdoCode();

		List<MstEmployeeModel> employeeConfigurationService = mstEmployeeService.getApproveEmployeeDetails(strddo,
				locale.getLanguage(), locId);

		// MstEmployeeEntity mm=employeeConfigurationService.get(0);
		// logger.info("employeeConfigurationService="+mm);
		model.addAttribute("employeedetails", employeeConfigurationService);
		addMenuAndSubMenu(model, messages);
		return "/views/approve-employee-details";
	}

	@RequestMapping(value = "/approveEmployeeConfiguration", method = { RequestMethod.GET, RequestMethod.POST })
	public String approveEmployeeConfiguration(@ModelAttribute("mstEmployeeModel") MstEmployeeModel mstEmployeeModel,
			Model model, Locale locale, HttpSession session) {

		String message = (String) model.asMap().get("message");
		mstEmployeeModel = mstEmployeeService.getEmployeeinfo(mstEmployeeModel.getEmployeeId());

		// Get Image start
		String key = "";
		String rootPath = "";
		String strOSName = System.getProperty("os.name");
		boolean test = strOSName.contains("Windows");
		if (strOSName.contains("Windows")) {
			key = "serverempconfigimagepath";
		} else {
			key = "serverempconfigimagepathLinuxOS";
		}
		rootPath = environment.getRequiredProperty(key);

		rootPath += mstEmployeeModel.getDeptNm() + "/" + mstEmployeeModel.getEmployeeId();
		if (mstEmployeeModel.getPhotoAttachmentId() != null)
			if (!mstEmployeeModel.getPhotoAttachmentId().equals("")) {
				/*
				 * String strphotoimg = mstEmployeeService.findEmployeeConfigurationUploadImage(
				 * mstEmployeeModel.getPhotoAttachmentId(), "photo" +
				 * mstEmployeeModel.getDeptNm() + mstEmployeeModel.getEmployeeId() + ".jpg");
				 * mstEmployeeModel.setImagePath(strphotoimg);
				 */

				mstEmployeeModel.setImagePath(rootPath + "/" + "photo.jpg");
			}
		if (mstEmployeeModel.getSignatureAttachmentId() != null)
			if (!mstEmployeeModel.getSignatureAttachmentId().equals("")) {
				/*
				 * String strsignimg = mstEmployeeService.findEmployeeConfigurationUploadImage(
				 * mstEmployeeModel.getSignatureAttachmentId(), "signature" +
				 * mstEmployeeModel.getDeptNm() + mstEmployeeModel.getEmployeeId() + ".jpg");
				 * mstEmployeeModel.setImagePathSign(strsignimg);
				 */

				mstEmployeeModel.setImagePath(rootPath + "/" + "signature.jpg");
			}
		// String src1="Output1.jpg";

		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		long locId = mstEmployeeService.getLocationCode(mstEmployeeModel.getDdoCode());
		// Get Institution Details start
		// List<Object[]> lstInstitueDtls =
		// mstEmployeeService.getInstitueDtls(messages.getUserName().toString());
		// if (!lstInstitueDtls.isEmpty()) {
		// for (Object[] objLst : lstInstitueDtls) {
		// mstEmployeeModel.setInstName(objLst[0].toString());
		// mstEmployeeModel.setInsttelnoone(objLst[1].toString());
		// mstEmployeeModel.setInsttelnotwo(objLst[2].toString());
		// mstEmployeeModel.setInstemail(objLst[3].toString());
		// }
		// }
		// // Get Institution Details end

		// Get annuation age start
		if (mstEmployeeModel.getCadreId() != null)
			if (mstEmployeeModel.getCadreId().intValue() != 0) {
				List<Object[]> lstCadregroup = mstEmployeeService.getCadreGroupMstData(locale.getLanguage(),
						mstEmployeeModel.getCadreId().toString());
				if (!lstCadregroup.isEmpty()) {
					for (Object[] objLst : lstCadregroup) {
						mstEmployeeModel.setSuperannuationage(Long.valueOf(objLst[1].toString()));
					}
				}
			}

		if (message != null && message.equals("SUCCESS")) {
			if (locale != null && locale.getLanguage().equalsIgnoreCase("en")) {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_ENGLSH, STATUS.SUCCESS, model);
			} else {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_MARATHI, STATUS.SUCCESS, model);
			}
		}

		List<ReligionMstEntity> mstReligionLst = new ArrayList<>();
		mstReligionLst = commonHomeMethodsService.fetchAllReligions();
		model.addAttribute("mstReligionLst", mstReligionLst);

		// model.addAttribute("lstAdminOfficeMst",
		// mstEmployeeService.findDDOScreenDataTable(locale.getLanguage(),
		// messages.getUserName().toString()));
		DDOScreenModel ddoScreenModel = new DDOScreenModel();
		// List<DDOScreenModel> lstAdminOfficeMst =
		// mstEmployeeService.findDDOScreenDataTable(locale.getLanguage(),
		// messages.getUserName().toString());
		List<DDOScreenModel> lstAdminOfficeMst = mstEmployeeService.findDDOScreenDataTable(locale.getLanguage(), locId);
		model.addAttribute("lstAdminOfficeMst", lstAdminOfficeMst);
		// MstSubDepartmentEntity mstdeptentity =
		// mstSubDepartmentServicee.findMstSubDeptByDeptId(23);
		ddoScreenModel = lstAdminOfficeMst.get(0);
		mstEmployeeModel.setAction("Edit");
		// mstEmployeeModel.setDeptNm(mstdeptentity.getSubDepartmentShortName().toString());
		// mstEmployeeModel.setDeptNm(ddoScreenModel.getSubDeptShortName().toString());
		mstEmployeeModel.setEmployeeId(mstEmployeeModel.getEmployeeId());
		model.addAttribute("mstEmployeeModel", mstEmployeeModel);
		model.addAttribute("employeeId", mstEmployeeModel.getEmployeeId());
		// model.addAttribute("lstAdminOfficeMst",
		// createAdminOfficeService.lstAdminOfficeMst());
		// model.addAttribute("lstCadreMst",
		// mstEmployeeService.getCadreMstData(locale.getLanguage()),locId);
		model.addAttribute("lstCadreMst", mstEmployeeService.getCadreMstData(locale.getLanguage(), locId));
		model.addAttribute("language", locale.getLanguage());

		List<MstPayCommissionEntity> lstddcPayCommission = mstDesignationService.findAllPayCommission();
		model.addAttribute("lstddcPayCommission", lstddcPayCommission);
		// model.addAttribute("lstDesignation",
		// mstDesignationService.getDesignationMstData(locale.getLanguage()));
		List<MstDesignationEntity> designation = mstEmployeeService.getDesignationMstData(locale.getLanguage(), locId);
		model.addAttribute("lstDesignation", designation);
		model.addAttribute("lstAllBankList", mstBankService.lstAllBank());
		// model.addAttribute("lstCommonMstSalutation", commonHomeMethodsService
		// .findCommonMstByCommonCode(CommonConstants.COMMONMSTTABLE.COMMONCODE_SALUTATION));

		List<CmnLookupMst> lookupLst = new ArrayList<>();
		lookupLst = commonHomeMethodsService
				.findCommonMstByLookupname(CommonConstants.COMMONMSTTABLE.COMMONCODE_SALUTATIONS);
		model.addAttribute("lstCommonMstSalutation", lookupLst);

		model.addAttribute("lstCommonMstGIS",
				commonHomeMethodsService.findCommonMstByCommonCode(CommonConstants.COMMONMSTTABLE.COMMONCODE_GIS));
		model.addAttribute("lstCommonMstGender",
				commonHomeMethodsService.findCommonMstByCommonCode(CommonConstants.COMMONMSTTABLE.COMMONCODE_GENDER));
		// model.addAttribute("lstDcpsAccnMaintainby",
		// mstEmployeeService.getDcpsAccnMaintainby());
		List<CmnLookupMst> lstDcpccnMaintainby = new ArrayList<>();
		lstDcpccnMaintainby = commonHomeMethodsService
				.findCommonMstByLookupname(CommonConstants.COMMONMSTTABLE.COMMONCODE_DCPSACCMAINTAINEDBY);
		model.addAttribute("lstDcpsAccnMaintainby", lstDcpccnMaintainby);

		List<CmnLookupMst> lstAccnMaintainby = new ArrayList<>();
		lstAccnMaintainby = commonHomeMethodsService
				.findCommonMstByLookupname(CommonConstants.COMMONMSTTABLE.COMMONCODE_GPFACCMAINTAINEDBY);
		model.addAttribute("lstAccountMaintainby", lstAccnMaintainby);
		// model.addAttribute("lstAccountMaintainby",
		// mstEmployeeService.getAccountMaintainby());
		// model.addAttribute("lstCurntDepartment",
		// mstDepartmentService.findMstDeptByDeptId(15));
		model.addAttribute("lstCurntDepartment", lstAdminOfficeMst);
		List<CmnLookupMst> lstgisapplicable = new ArrayList<>();
		lstgisapplicable = commonHomeMethodsService
				.findCommonMstByLookupname(CommonConstants.COMMONMSTTABLE.COMMONCODE_GISAPPLICABLE);
		model.addAttribute("lstGISApplicable", lstgisapplicable);
		model.addAttribute("lstGISGroup", mstEmployeeService.getGISGroup());
		// model.addAttribute("lstGISApplicable",
		// mstEmployeeService.getGISApplicable());
		model.addAttribute("lstRelation", mstEmployeeService.getRelation());
		model.addAttribute("lstAppointment", mstEmployeeService.getAppoitnment(locale.getLanguage()));
		model.addAttribute("lstQualification", mstEmployeeService.getQualification(locale.getLanguage()));
		// Nominee Dtls Implementation start

		// List<MstNomineeDetailsEntity>
		// lstNmnDtls=mstEmployeeService.getNominees(mstEmployeeModel.getEmployeeId().toString());
		// model.addAttribute("lstNmnDtls", lstNmnDtls);
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		List<MstNomineeDetailsModel> lstNmnDtls = new ArrayList<MstNomineeDetailsModel>();
		List<MstNomineeDetailsEntity> lstNmnDtlsEntity = null;
		if (mstEmployeeModel.getEmployeeId() != null) {
			lstNmnDtlsEntity = mstEmployeeService.getNominees(mstEmployeeModel.getEmployeeId().toString());
		}
		if (lstNmnDtlsEntity != null)
			for (Iterator iterator = lstNmnDtlsEntity.iterator(); iterator.hasNext();) {
				MstNomineeDetailsEntity mstNomineeDetailsEntity = (MstNomineeDetailsEntity) iterator.next();
				MstNomineeDetailsModel mstNomineeDetailsModel = new MstNomineeDetailsModel();
				mstNomineeDetailsModel.setNomineename(mstNomineeDetailsEntity.getNomineename());
				mstNomineeDetailsModel.setPercent_share(mstNomineeDetailsEntity.getPercent_share());
				mstNomineeDetailsModel.setRelation(mstNomineeDetailsEntity.getRelation());
				mstNomineeDetailsModel.setDob(formatter.format(mstNomineeDetailsEntity.getDob()));
				mstNomineeDetailsModel.setNomineeaddress(mstNomineeDetailsEntity.getNomineeaddress());
				lstNmnDtls.add(mstNomineeDetailsModel);

			}
		model.addAttribute("lstNmnDtls", lstNmnDtls);
		// Nominee Dtls Implementation end

		List<MstStateModel> listStatemdl = new ArrayList<MstStateModel>();
		List<Object[]> listState = locationMasterService.findAllStates(1);
		for (Iterator iterator = listState.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			MstStateModel mstStateModel = new MstStateModel();
			mstStateModel.setStateCode(Integer.parseInt(String.valueOf(objects[0])));
			mstStateModel.setStateNameEn(String.valueOf(objects[1]));
			listStatemdl.add(mstStateModel);
		}

		model.addAttribute("lstAllState", listStatemdl);
		List<MstDistrictModel> listDistrictemdl = new ArrayList<MstDistrictModel>();
		try {
			List<Object[]> listDistrict = locationMasterService.findAllDistricts(mstEmployeeModel.getStateCode());
			// logger.info("distric code list size="+listDistrict.size());
			for (Iterator iterator = listDistrict.iterator(); iterator.hasNext();) {
				Object[] objects = (Object[]) iterator.next();
				MstDistrictModel mstDistrictModel = new MstDistrictModel();
				mstDistrictModel.setDistrictId(String.valueOf(objects[0]));
				mstDistrictModel.setDistrictName(String.valueOf(objects[1]));
				listDistrictemdl.add(mstDistrictModel);
				// logger.info("distric code list object4="+String.valueOf(objects[4]));
			}

		} catch (Exception e) {
			// TODO: handle exception
			// e.printStackTrace();
		}

		try {

			model.addAttribute("lstAllBankBranchList",
					mstEmployeeService.getBankBranch(String.valueOf(mstEmployeeModel.getBankId().toString())));
			model.addAttribute("lstCurrentPost", mstEmployeeService
					.GetCurrentPostByLvlTwo(mstEmployeeModel.getDesignationId(), mstEmployeeModel.getDdoCode(), locId));

		} catch (Exception e) {
			// TODO: handle exception
			// e.printStackTrace();
		}

		model.addAttribute("lstAllDistrict", listDistrictemdl);

		List<Object[]> payscalelevel = new ArrayList<Object[]>();
		List<Object[]> lstsixpayscalelevel = new ArrayList<Object[]>();
		List<Object[]> lstsvnbasicpay = new ArrayList<Object[]>();
		List<Object[]> lstpfSeries = new ArrayList<Object[]>();
				
		if(mstEmployeeModel.getPayCommissionCode() != null) {
			if (mstEmployeeModel.getPayCommissionCode() != null && !mstEmployeeModel.getPayCommissionCode().equals(2500347))
				lstsixpayscalelevel = mstEmployeeService.findEmployeeConfigurationGetSixPayScale(2500341);
			if(mstEmployeeModel.getPayCommissionCode().equals(2500347)) {
				lstsixpayscalelevel = mstEmployeeService.findEmployeeConfigurationGetSixPayScale(2500341);
			}
			if(mstEmployeeModel.getPayCommissionCode().equals(2500347)) {
				payscalelevel = mstEmployeeService.findEmployeeConfigurationGetpayscale(2500347);
			}
			if (mstEmployeeModel.getPayCommissionCode() != null && !mstEmployeeModel.getPayCommissionCode().equals(8))
				lstsixpayscalelevel = mstEmployeeService.findEmployeeConfigurationGetSixPayScale(2500341);
			if (mstEmployeeModel.getPayCommissionCode().equals(700005)) {
				lstsixpayscalelevel = mstEmployeeService.findEmployeeConfigurationGetSixPayScale(2500341);
			}
			if (mstEmployeeModel.getPayCommissionCode().equals(700005)) {
				payscalelevel = mstEmployeeService.findEmployeeConfigurationGetpayscale(8);
			}
		}
		
		

		if (mstEmployeeModel.getPayscalelevelId() != null)
			if (!mstEmployeeModel.getPayscalelevelId().equals("") && !mstEmployeeModel.getPayscalelevelId().equals("0"))
				lstsvnbasicpay = mstEmployeeService
						.findEmployeeConfigurationGetsvnbasicpay(mstEmployeeModel.getPayscalelevelId());
		if (mstEmployeeModel.getAccountmaintainby() != null)
			if (!mstEmployeeModel.getAccountmaintainby().equals("")
					&& !mstEmployeeModel.getAccountmaintainby().equals("0")
					&& !(mstEmployeeModel.getAccountmaintainby() != null))
				lstpfSeries = mstEmployeeService.getPfSeries(mstEmployeeModel.getAccountmaintainby());

		model.addAttribute("lstsvnbasicpay", lstsvnbasicpay);
		model.addAttribute("lstpayscalelevel", payscalelevel);
		model.addAttribute("lstsixpayscalelevel", lstsixpayscalelevel);
		model.addAttribute("lstpfSeries", lstpfSeries);
		model.addAttribute("language", locale.getLanguage());

		addMenuAndSubMenu(model, messages);
		return "/views/approve-employee-configuration";
	}
	
	@RequestMapping("/approveEmpDtls/{empid}/{sevaarthid}/{dcpsgpfflg}")
	public @ResponseBody List<String> approveEmployeeConfiguration(@PathVariable String empid,
			@PathVariable String sevaarthid, @PathVariable String dcpsgpfflg, Model model, Locale locale,
			HttpSession session) {

		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		long curentIdCount = mstEmployeeService.getCount(sevaarthid);
		String lStrSevarthEmpCode = "";
		long tempIdCount = curentIdCount + 01;
		String tempCountVar = String.format("%2s", tempIdCount).replace(' ', '0');
		MstEmployeeModel mstEmployeeModel = mstEmployeeService.getEmployeeinfo(Long.valueOf(empid));
		

		if (curentIdCount == 0) {
			lStrSevarthEmpCode = lStrSevarthEmpCode + "01";
		}
		lStrSevarthEmpCode = sevaarthid + tempCountVar;

		// Integer uid1 = Integer.valueOf(uid);
		// logger.info("uid1="+uid1);
		if(mstEmployeeModel != null)
		{
			MstEmployeeDetailEntity mstEmployeeDetailEntity = mstEmployeeService.updateEmployeeDetails(Long.valueOf(empid));
		}
		List<Long> status1 = mstEmployeeService.approveEmployeeConfiguration(empid, sevaarthid, dcpsgpfflg);

		int empcount = mstEmployeeService.getSevaarthid(sevaarthid);
	
		if(empcount!=0) {
		}else {
			mstEmployeeService.createNewUser(sevaarthid,messages,mstEmployeeModel);
		}
		//end new user creation
		
	
		//add entry into gpf mst details table
	
		//	mstGpfDetailsEntity

		if (empcount != 0) {
		} else {
			mstEmployeeService.createNewUser(sevaarthid, messages, mstEmployeeModel);
		}
		// end new user creation

		// add entry into gpf mst details table
		int isSevaarthPresent = mstEmployeeService.checkSevaarthIdExistInGpfDetailMst(empid);
		if (isSevaarthPresent == 0 && dcpsgpfflg.equals("N")) {
			MstGpfDetailsEntity mstGpfDetailsEntity = new MstGpfDetailsEntity();
			// mstGpfDetailsEntity
			mstGpfDetailsEntity.setSevaarthId(sevaarthid);
			mstGpfDetailsEntity.setPfacno("0");
			mstGpfDetailsEntity.setAccountmaintainby("0");
			mstGpfDetailsEntity.setIsactive("2");
			mstGpfDetailsEntity.setEmployeeId(Long.valueOf(empid));
			mstEmployeeService.saveGpfDetails(mstGpfDetailsEntity);
			
		}
		// end entry into gpf mst details table

		// end gpf opening balance as zero

		List<String> status = new ArrayList<String>();
		status.add(lStrSevarthEmpCode);
		return status;
	}

	@RequestMapping(value = "/dcpsEmployeeDetails", method = { RequestMethod.GET, RequestMethod.POST })
	public String getDcpsEmployeeDetails(@ModelAttribute("mstEmployeeModel") MstEmployeeModel mstEmployeeModel,
			Model model, Locale locale, HttpSession session) {
		String message = (String) model.asMap().get("message");
		model.addAttribute("mstEmployeeModel", mstEmployeeModel);

		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		long locId = Long.parseLong((String) session.getAttribute("locationId"));

		if (message != null && message.equals("SUCCESS")) {
			if (locale != null && locale.getLanguage().equalsIgnoreCase("en")) {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_ENGLSH, STATUS.SUCCESS, model);
			} else {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_MARATHI, STATUS.SUCCESS, model);
			}
		}
		// model.addAttribute("lstAdminOfficeMst",
		// createAdminOfficeService.lstAdminOfficeMst());
		// model.addAttribute("lstCadreMst",
		// mstCadreService.getCadreMstData(locale.getLanguage()));
		model.addAttribute("language", locale.getLanguage());
		String strddo = messages.getDdoCode();
		List<MstEmployeeModel> employeeConfigurationService = mstEmployeeService.getDcpsEmployeeDetails(strddo,
				locale.getLanguage(), locId);
		// MstEmployeeEntity mm=employeeConfigurationService.get(0);
		// logger.info("employeeConfigurationService="+mm);
		model.addAttribute("employeedetails", employeeConfigurationService);
		addMenuAndSubMenu(model, messages);
		return "/views/dcps-employee-details";
	}

	@RequestMapping(value = "/approveDCPSEmployeeConfiguration", method = { RequestMethod.GET, RequestMethod.POST })
	public String approveDCPSEmployeeConfiguration(
			@ModelAttribute("mstEmployeeModel") MstEmployeeModel mstEmployeeModel, Model model, Locale locale,
			HttpSession session) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		String message = (String) model.asMap().get("message");
		mstEmployeeModel = mstEmployeeService.getEmployeeinfo(mstEmployeeModel.getEmployeeId());
		long locId = mstEmployeeService.getLocationCode(mstEmployeeModel.getDdoCode());
		//mstEmployeeModel.setDdoCode(messages.getUserName());
		// Get Image start
		String key = "";
		String rootPath = "";
		String strOSName = System.getProperty("os.name");
		boolean test = strOSName.contains("Windows");
		if (strOSName.contains("Windows")) {
			key = "serverempconfigimagepath";
		} else {
			key = "serverempconfigimagepathLinuxOS";
		}
		rootPath = environment.getRequiredProperty(key);

		rootPath += mstEmployeeModel.getDeptNm() + "/" + mstEmployeeModel.getEmployeeId();

		if (mstEmployeeModel.getPhotoAttachmentId() != null)
			if (!mstEmployeeModel.getPhotoAttachmentId().equals("")) {
				/*
				 * String strphotoimg = mstEmployeeService.findEmployeeConfigurationUploadImage(
				 * mstEmployeeModel.getPhotoAttachmentId(), "photo" +
				 * mstEmployeeModel.getDeptNm() + mstEmployeeModel.getEmployeeId() + ".jpg");
				 * mstEmployeeModel.setImagePath(strphotoimg);
				 */

				mstEmployeeModel.setImagePath(rootPath + "/" + "photo.jpg");
			}
		if (mstEmployeeModel.getSignatureAttachmentId() != null)
			if (!mstEmployeeModel.getSignatureAttachmentId().equals("")) {
				/*
				 * String strsignimg = mstEmployeeService.findEmployeeConfigurationUploadImage(
				 * mstEmployeeModel.getSignatureAttachmentId(), "signature" +
				 * mstEmployeeModel.getDeptNm() + mstEmployeeModel.getEmployeeId() + ".jpg");
				 * mstEmployeeModel.setImagePathSign(strsignimg);
				 */

				mstEmployeeModel.setImagePath(rootPath + "/" + "signature.jpg");
			}
		// String src1="Output1.jpg";

		// Get Institution Details start
		// List<Object[]> lstInstitueDtls =
		// mstEmployeeService.getInstitueDtls(messages.getUserName().toString());
		// if (!lstInstitueDtls.isEmpty()) {
		// for (Object[] objLst : lstInstitueDtls) {
		// mstEmployeeModel.setInstName(objLst[0].toString());
		// mstEmployeeModel.setInsttelnoone(objLst[1].toString());
		// mstEmployeeModel.setInsttelnotwo(objLst[2].toString());
		// mstEmployeeModel.setInstemail(objLst[3].toString());
		// }
		// }
		// Get Institution Details end

		// Get annuation age start
		if (mstEmployeeModel.getCadreId() != null)
			if (mstEmployeeModel.getCadreId().intValue() != 0) {
				List<Object[]> lstCadregroup = mstEmployeeService.getCadreGroupMstData(locale.getLanguage(),
						mstEmployeeModel.getCadreId().toString());
				if (!lstCadregroup.isEmpty()) {
					for (Object[] objLst : lstCadregroup) {
						mstEmployeeModel.setSuperannuationage(Long.valueOf(objLst[1].toString()));
					}
				}
			}

		if (message != null && message.equals("SUCCESS")) {
			if (locale != null && locale.getLanguage().equalsIgnoreCase("en")) {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_ENGLSH, STATUS.SUCCESS, model);
			} else {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_MARATHI, STATUS.SUCCESS, model);
			}
		}
		DDOScreenModel ddoScreenModel = new DDOScreenModel();
		List<DDOScreenModel> lstAdminOfficeMst = mstEmployeeService.findDDOScreenDataTable(locale.getLanguage(), locId);
		model.addAttribute("lstAdminOfficeMst", lstAdminOfficeMst);
		// MstSubDepartmentEntity mstdeptentity =
		// mstSubDepartmentService.findMstSubDeptByDeptId(23);
		// ddoScreenModel = lstAdminOfficeMst.get(0);
		mstEmployeeModel.setAction("Edit");
		// mstEmployeeModel.setDeptNm(mstdeptentity.getSubDepartmentShortName().toString());
		// mstEmployeeModel.setDeptNm(ddoScreenModel.getSubDeptShortName().toString());
		mstEmployeeModel.setDdoCode(mstEmployeeModel.getDdoCode());

		// Get annuation age end
		model.addAttribute("mstEmployeeModel", mstEmployeeModel);

		// model.addAttribute("lstAdminOfficeMst",
		// createAdminOfficeService.lstAdminOfficeMst());
		// model.addAttribute("lstCadreMst",
		// mstEmployeeService.getCadreMstData(locale.getLanguage()));
		model.addAttribute("lstCadreMst", mstEmployeeService.getCadreMstData(locale.getLanguage(), locId));
		model.addAttribute("language", locale.getLanguage());

		List<MstPayCommissionEntity> lstddcPayCommission = mstDesignationService.findAllPayCommission();
		model.addAttribute("lstddcPayCommission", lstddcPayCommission);
		List<MstDesignationEntity> designation = mstEmployeeService.getDesignationMstData(locale.getLanguage(), locId);
		model.addAttribute("lstDesignation", designation);
		model.addAttribute("lstAllBankList", mstBankService.lstAllBank());
		List<CmnLookupMst> lookupLst = new ArrayList<>();
		lookupLst = commonHomeMethodsService
				.findCommonMstByLookupname(CommonConstants.COMMONMSTTABLE.COMMONCODE_SALUTATIONS);
		model.addAttribute("lstCommonMstSalutation", lookupLst);

		model.addAttribute("lstCommonMstGIS",
				commonHomeMethodsService.findCommonMstByCommonCode(CommonConstants.COMMONMSTTABLE.COMMONCODE_GIS));
		model.addAttribute("lstCommonMstGender",
				commonHomeMethodsService.findCommonMstByCommonCode(CommonConstants.COMMONMSTTABLE.COMMONCODE_GENDER));
		// model.addAttribute("lstDcpsAccnMaintainby",
		// mstEmployeeService.getDcpsAccnMaintainby());
		List<CmnLookupMst> lstDcpccnMaintainby = new ArrayList<>();
		lstDcpccnMaintainby = commonHomeMethodsService
				.findCommonMstByLookupname(CommonConstants.COMMONMSTTABLE.COMMONCODE_DCPSACCMAINTAINEDBY);
		model.addAttribute("lstDcpsAccnMaintainby", lstDcpccnMaintainby);

		List<CmnLookupMst> lstAccnMaintainby = new ArrayList<>();
		lstAccnMaintainby = commonHomeMethodsService
				.findCommonMstByLookupname(CommonConstants.COMMONMSTTABLE.COMMONCODE_GPFACCMAINTAINEDBY);
		model.addAttribute("lstAccountMaintainby", lstAccnMaintainby);
		// model.addAttribute("lstCurntDepartment",
		// mstDepartmentService.findMstDeptByDeptId(15));
		model.addAttribute("lstCurntDepartment", lstAdminOfficeMst);

		model.addAttribute("lstGISGroup", mstEmployeeService.getGISGroup());

		List<CmnLookupMst> lstgisapplicable = new ArrayList<>();
		lstgisapplicable = commonHomeMethodsService
				.findCommonMstByLookupname(CommonConstants.COMMONMSTTABLE.COMMONCODE_GISAPPLICABLE);
		model.addAttribute("lstGISApplicable", lstgisapplicable);
		model.addAttribute("lstRelation", mstEmployeeService.getRelation());
		model.addAttribute("lstAppointment", mstEmployeeService.getAppoitnment(locale.getLanguage()));
		model.addAttribute("lstQualification", mstEmployeeService.getQualification(locale.getLanguage()));
		List<ReligionMstEntity> mstReligionLst = new ArrayList<>();
		mstReligionLst = commonHomeMethodsService.fetchAllReligions();
		model.addAttribute("mstReligionLst", mstReligionLst);

		// Nominee Dtls Implementation start

		// List<MstNomineeDetailsEntity>
		// lstNmnDtls=mstEmployeeService.getNominees(mstEmployeeModel.getEmployeeId().toString());
		// model.addAttribute("lstNmnDtls", lstNmnDtls);
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		List<MstNomineeDetailsModel> lstNmnDtls = new ArrayList<MstNomineeDetailsModel>();
		List<MstNomineeDetailsEntity> lstNmnDtlsEntity = null;
		if (mstEmployeeModel.getEmployeeId() != null) {
			lstNmnDtlsEntity = mstEmployeeService.getNominees(mstEmployeeModel.getEmployeeId().toString());
		}
		if (lstNmnDtlsEntity != null && lstNmnDtlsEntity.size() > 0)
			for (Iterator iterator = lstNmnDtlsEntity.iterator(); iterator.hasNext();) {
				MstNomineeDetailsEntity mstNomineeDetailsEntity = (MstNomineeDetailsEntity) iterator.next();
				MstNomineeDetailsModel mstNomineeDetailsModel = new MstNomineeDetailsModel();
				mstNomineeDetailsModel.setNomineename(mstNomineeDetailsEntity.getNomineename());
				mstNomineeDetailsModel.setPercent_share(mstNomineeDetailsEntity.getPercent_share());
				mstNomineeDetailsModel.setRelation(mstNomineeDetailsEntity.getRelation());
				mstNomineeDetailsModel.setDob(formatter.format(mstNomineeDetailsEntity.getDob()));
				mstNomineeDetailsModel.setNomineeaddress(mstNomineeDetailsEntity.getNomineeaddress());
				lstNmnDtls.add(mstNomineeDetailsModel);

			}
		model.addAttribute("lstNmnDtls", lstNmnDtls);
		// Nominee Dtls Implementation end

		List<MstStateModel> listStatemdl = new ArrayList<MstStateModel>();
		List<Object[]> listState = locationMasterService.findAllStates(1);
		for (Iterator iterator = listState.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			MstStateModel mstStateModel = new MstStateModel();
			mstStateModel.setStateCode(Integer.parseInt(String.valueOf(objects[0])));
			mstStateModel.setStateNameEn(String.valueOf(objects[1]));
			listStatemdl.add(mstStateModel);
		}

		model.addAttribute("lstAllState", listStatemdl);
		List<MstDistrictModel> listDistrictemdl = new ArrayList<MstDistrictModel>();

		try {
			List<Object[]> listDistrict = locationMasterService.findAllDistricts(mstEmployeeModel.getStateCode());
			// logger.info("distric code list size="+listDistrict.size());
			for (Iterator iterator = listDistrict.iterator(); iterator.hasNext();) {
				Object[] objects = (Object[]) iterator.next();
				MstDistrictModel mstDistrictModel = new MstDistrictModel();
				mstDistrictModel.setDistrictId(String.valueOf(objects[0]));
				mstDistrictModel.setDistrictName(String.valueOf(objects[1]));
				listDistrictemdl.add(mstDistrictModel);
				// logger.info("distric code list object4="+String.valueOf(objects[4]));
			}

		} catch (Exception e) {
			// TODO: handle exception
			// e.printStackTrace();
		}

		try {
			model.addAttribute("lstAllBankBranchList",
					mstEmployeeService.getBankBranch(String.valueOf(mstEmployeeModel.getBankId().toString())));
			model.addAttribute("lstCurrentPost", mstEmployeeService
					.GetCurrentPostByLvlTwo(mstEmployeeModel.getDesignationId(), mstEmployeeModel.getDdoCode(), locId));

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		// model.addAttribute("lstCurrentPost",
		// empChangeDetailsService.GetCurrentPostDesigation(mstEmployeeModel.getPostdetailid()));
		model.addAttribute("lstAllDistrict", listDistrictemdl);

		List<Object[]> payscalelevel = new ArrayList<Object[]>();
		List<Object[]> lstsixpayscalelevel = new ArrayList<Object[]>();
		List<Object[]> lstsvnbasicpay = new ArrayList<Object[]>();
		List<Object[]> lstpfSeries = new ArrayList<Object[]>();
		if (mstEmployeeModel.getPayCommissionCode() != null
				&& !mstEmployeeModel.getPayCommissionCode().equals(700005)) {
			lstsixpayscalelevel = mstEmployeeService.findEmployeeConfigurationGetSixPayScale(2500341);
		}
		/*
		 * if(mstEmployeeModel.getPayCommissionCode().equals(2)) { lstsixpayscalelevel =
		 * mstEmployeeService.findEmployeeConfigurationGetSixPayScale(2500341); }
		 */
		if (mstEmployeeModel.getPayCommissionCode() != null && mstEmployeeModel.getPayCommissionCode().equals(700005)) {
			payscalelevel = mstEmployeeService.findEmployeeConfigurationGetpayscale(700005);
		}

		if (mstEmployeeModel.getPayscalelevelId() != null)
			if (!mstEmployeeModel.getPayscalelevelId().equals("") && !mstEmployeeModel.getPayscalelevelId().equals("0"))
				lstsvnbasicpay = mstEmployeeService
						.findEmployeeConfigurationGetsvnbasicpay(mstEmployeeModel.getPayscalelevelId());
		if (mstEmployeeModel.getAccountmaintainby() != null)
			if (!mstEmployeeModel.getAccountmaintainby().equals("")
					&& !mstEmployeeModel.getAccountmaintainby().equals("0"))
				lstpfSeries = mstEmployeeService.getPfSeries(mstEmployeeModel.getAccountmaintainby());

		model.addAttribute("lstsvnbasicpay", lstsvnbasicpay);
		model.addAttribute("lstpayscalelevel", payscalelevel);
		model.addAttribute("lstsixpayscalelevel", lstsixpayscalelevel);
		model.addAttribute("lstpfSeries", lstpfSeries);
		model.addAttribute("language", locale.getLanguage());

		addMenuAndSubMenu(model, messages);

		return "/views/approve-dcps-employee-configuration";
	}

	@RequestMapping("/approveDcpsEmpDtls/{empid}/{dcpsnum}/{sevaarthid}/{dcpsgpfflg}")
	public @ResponseBody List<String> approveDcpsEmployeeConfiguration(@PathVariable String empid,
			@PathVariable String dcpsnum, @PathVariable String sevaarthid, @PathVariable String dcpsgpfflg, Model model,
			Locale locale, HttpSession session) {
		// Sevaarth Id Creation start
		long curentIdCount = mstEmployeeService.getCount(sevaarthid);
		String lStrSevarthEmpCode = "";
		OrgUserMst message = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		lStrSevarthEmpCode = sevaarthid;
		dcpsnum = dcpsnum + lStrSevarthEmpCode.substring(lStrSevarthEmpCode.length() - 8);
		Character incrementvalue = mstEmployeeService.getLastDigit(dcpsnum);
		MstEmployeeModel mstEmployeeModel = mstEmployeeService.getEmployeeinfo(Long.valueOf(empid));

		List<ReligionMstEntity> mstReligionLst = new ArrayList<>();
		mstReligionLst = commonHomeMethodsService.fetchAllReligions();
		model.addAttribute("mstReligionLst", mstReligionLst);

		dcpsnum = dcpsnum + incrementvalue;

		if(mstEmployeeModel != null)
		{
			MstEmployeeDetailEntity mstEmployeeDetailEntity = mstEmployeeService.updateEmployeeDetails(Long.valueOf(empid));
		}

		List<Long> messages = mstEmployeeService.approveDcpsEmployeeConfiguration(empid, dcpsnum, lStrSevarthEmpCode,
				dcpsgpfflg);
		int empcount = mstEmployeeService.getSevaarthid(sevaarthid);
		if (empcount != 0) {
		} else {
			mstEmployeeService.createNewUser(sevaarthid, message, mstEmployeeModel);
		}
		List<String> status = new ArrayList<String>();
		status.add(dcpsnum);
		status.add(lStrSevarthEmpCode);
		return status;
	}

	@RequestMapping(value = "/draftCaseList", method = { RequestMethod.GET })
	public String draftCaseList(@ModelAttribute("mstEmployeeModel") MstEmployeeModel mstEmployeeModel, Model model,
			Locale locale, HttpSession session) {

		String message = (String) model.asMap().get("message");

		if (message != null && message.equals("SUCCESS")) {
			if (locale != null && locale.getLanguage().equalsIgnoreCase("en")) {
				model = CommonUtils.initModel(CommonConstants.Message.SAVEDRAFT, STATUS.SUCCESS, model);
			} else {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_MARATHI, STATUS.SUCCESS, model);
			}
		}
		if (message != null && message.equals("FRWDDDO")) {
			if (locale != null && locale.getLanguage().equalsIgnoreCase("en")) {
				model = CommonUtils.initModel(CommonConstants.Message.FRWDDDO, STATUS.SUCCESS, model);
			} else {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_MARATHI, STATUS.SUCCESS, model);
			}
		}

		if (message != null && message.equals("DRAFTCASE")) {
			if (locale != null && locale.getLanguage().equalsIgnoreCase("en")) {
				model = CommonUtils.initModel(CommonConstants.Message.DRAFTCASE, STATUS.SUCCESS, model);
			} else {
				model = CommonUtils.initModel(CommonConstants.Message.DRAFTCASE, STATUS.SUCCESS, model);
			}
		}

		mstEmployeeModel.setAction("Save");

		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(model, messages);
		Long CASE_STATUS = 0l;

		List<MstEmployeeModel> employeeDraftCaseLst = mstEmployeeService.findDraftCaseList(messages, CASE_STATUS);

		model.addAttribute("employeeDraftCaseLst", employeeDraftCaseLst);

		return "/views/employee-draft-case-list";
	}

	@RequestMapping(value = "/viewDraftCaseByEmployeeId/{employeeId}", method = { RequestMethod.GET })
	public String viewDraftCaseByEmployeeId(@PathVariable Long employeeId,
			@ModelAttribute("mstEmployeeModel") MstEmployeeModel mstEmployeeModel, Model model, Locale locale,
			HttpSession session) {

		String message = (String) model.asMap().get("message");

		mstEmployeeModel = mstEmployeeService.getEmployeeinfo(mstEmployeeModel.getEmployeeId());
		mstEmployeeModel.setAction("updateDraftCase");

		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		mstEmployeeModel.setDdoCode(messages.getDdoCode());
		long locId = Long.parseLong((String) session.getAttribute("locationId"));

		List<OrgDdoMst> lstDDO = dDOScreenService.findDDOByUsername(messages.getUserName());
		model.addAttribute("lstGISGroup", mstEmployeeService.getGISGroup());

		List<DDOScreenModel> lstDepartment = mstEmployeeService.findDDOScreenDataTable(locale.getLanguage(), locId);

		List<Object[]> districtListAgainstStateId = locationMasterService.findAllDistricts(15);
		model.addAttribute("districtListAgainstStateId", districtListAgainstStateId);
		mstEmployeeModel.setStateCode(15l); // Default state maharashtra
		model.addAttribute("mstEmployeeModel", mstEmployeeModel);

		List<ReligionMstEntity> mstReligionLst = new ArrayList<>();
		mstReligionLst = commonHomeMethodsService.fetchAllReligions();
		model.addAttribute("mstReligionLst", mstReligionLst);

		List<CmnLookupMst> lookupLst = new ArrayList<>();
		lookupLst = commonHomeMethodsService
				.findCommonMstByLookupname(CommonConstants.COMMONMSTTABLE.COMMONCODE_SALUTATIONS);
		model.addAttribute("lstCommonMstSalutation", lookupLst);

		model.addAttribute("lstAdminOfficeMst", lstDepartment);

		model.addAttribute("lstCadreMst", mstEmployeeService.getCadreMstData(locale.getLanguage(), locId));
		model.addAttribute("language", locale.getLanguage());

		List<MstPayCommissionEntity> lstddcPayCommission = mstDesignationService.findAllPayCommission();
		model.addAttribute("lstddcPayCommission", lstddcPayCommission);
		model.addAttribute("lstDesignation", mstEmployeeService.getDesignationMstData(locale.getLanguage(), locId));
		model.addAttribute("lstAppointment", mstEmployeeService.getAppoitnment(locale.getLanguage()));
		model.addAttribute("lstQualification", mstEmployeeService.getQualification(locale.getLanguage()));

		List<CmnLookupMst> lstDcpccnMaintainby = new ArrayList<>();
		lstDcpccnMaintainby = commonHomeMethodsService
				.findCommonMstByLookupname(CommonConstants.COMMONMSTTABLE.COMMONCODE_DCPSACCMAINTAINEDBY);
		model.addAttribute("lstDcpsAccnMaintainby", lstDcpccnMaintainby);

		List<CmnLookupMst> lstAccnMaintainby = new ArrayList<>();
		lstAccnMaintainby = commonHomeMethodsService
				.findCommonMstByLookupname(CommonConstants.COMMONMSTTABLE.COMMONCODE_GPFACCMAINTAINEDBY);
		model.addAttribute("lstAccountMaintainby", lstAccnMaintainby);
		model.addAttribute("lstCurntDepartment", lstDepartment);

		List<CmnLookupMst> lstgisapplicable = new ArrayList<>();
		lstgisapplicable = commonHomeMethodsService
				.findCommonMstByLookupname(CommonConstants.COMMONMSTTABLE.COMMONCODE_GISAPPLICABLE);
		model.addAttribute("lstGISApplicable", lstgisapplicable);

		model.addAttribute("lstGISGroup", mstEmployeeService.getGISGroup());

		model.addAttribute("lstRelation", mstEmployeeService.getRelation());
		model.addAttribute("lstAllBankList", mstBankService.lstAllBank());
		model.addAttribute("lstCommonMstGIS",
				commonHomeMethodsService.findCommonMstByCommonCode(CommonConstants.COMMONMSTTABLE.COMMONCODE_GIS));
		List<MstStateModel> listStatemdl = new ArrayList<MstStateModel>();
		List<Object[]> listState = locationMasterService.findAllStates(1);
		for (Iterator iterator = listState.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			MstStateModel mstStateModel = new MstStateModel();
			mstStateModel.setStateCode(Integer.parseInt(String.valueOf(objects[0])));
			mstStateModel.setStateNameEn(String.valueOf(objects[1]));
			listStatemdl.add(mstStateModel);
		}

		model.addAttribute("lstAllState", listStatemdl);

		model.addAttribute("language", locale.getLanguage());
		LocalDate date = LocalDate.now();
		model.addAttribute("currentDate", date);

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		List<MstNomineeDetailsModel> lstNmnDtls = new ArrayList<MstNomineeDetailsModel>();
		List<MstNomineeDetailsEntity> lstNmnDtlsEntity = null;
		if (mstEmployeeModel.getEmployeeId() != null) {
			lstNmnDtlsEntity = mstEmployeeService.getNominees(mstEmployeeModel.getEmployeeId().toString());
		}
		if (lstNmnDtlsEntity != null)
			for (Iterator iterator = lstNmnDtlsEntity.iterator(); iterator.hasNext();) {
				MstNomineeDetailsEntity mstNomineeDetailsEntity = (MstNomineeDetailsEntity) iterator.next();
				MstNomineeDetailsModel mstNomineeDetailsModel = new MstNomineeDetailsModel();
				mstNomineeDetailsModel.setNomineename(mstNomineeDetailsEntity.getNomineename());
				mstNomineeDetailsModel.setPercent_share(mstNomineeDetailsEntity.getPercent_share());
				mstNomineeDetailsModel.setRelation(mstNomineeDetailsEntity.getRelation());
				mstNomineeDetailsModel.setDob(formatter.format(mstNomineeDetailsEntity.getDob()));
				mstNomineeDetailsModel.setNomineeaddress(mstNomineeDetailsEntity.getNomineeaddress());
				lstNmnDtls.add(mstNomineeDetailsModel);

			}
		model.addAttribute("lstNmnDtls", lstNmnDtls);

		model.addAttribute("lstAllState", listStatemdl);
		List<MstDistrictModel> listDistrictemdl = new ArrayList<MstDistrictModel>();
		try {
			List<Object[]> listDistrict = locationMasterService.findAllDistricts(mstEmployeeModel.getStateCode());
			// logger.info("distric code list size="+listDistrict.size());
			for (Iterator iterator = listDistrict.iterator(); iterator.hasNext();) {
				Object[] objects = (Object[]) iterator.next();
				MstDistrictModel mstDistrictModel = new MstDistrictModel();
				mstDistrictModel.setDistrictId(String.valueOf(objects[0]));
				mstDistrictModel.setDistrictName(String.valueOf(objects[1]));
				listDistrictemdl.add(mstDistrictModel);
				// logger.info("distric code list object4="+String.valueOf(objects[4]));
			}

		} catch (Exception e) {
			// TODO: handle exception
			// e.printStackTrace();
		}

		try {

			model.addAttribute("lstAllBankBranchList",
					mstEmployeeService.getBankBranch(String.valueOf(mstEmployeeModel.getBankId().toString())));
			model.addAttribute("lstCurrentPost", mstEmployeeService
					.GetCurrentPostByLvlTwo(mstEmployeeModel.getDesignationId(), mstEmployeeModel.getDdoCode(), locId));

		} catch (Exception e) {
			// TODO: handle exception
			// e.printStackTrace();
		}

		model.addAttribute("lstAllDistrict", listDistrictemdl);

		List<Object[]> payscalelevel = new ArrayList<Object[]>();
		List<Object[]> lstsixpayscalelevel = new ArrayList<Object[]>();
		List<Object[]> lstsvnbasicpay = new ArrayList<Object[]>();
		List<Object[]> lstpfSeries = new ArrayList<Object[]>();

		/*if (mstEmployeeModel.getPayCommissionCode() != null && mstEmployeeModel.getPayCommissionCode()!=700005l)
			lstsixpayscalelevel = mstEmployeeService.findEmployeeConfigurationGetSixPayScale(2500341);
		if (mstEmployeeModel.getPayCommissionCode()==700005l) {
			lstsixpayscalelevel = mstEmployeeService.findEmployeeConfigurationGetSixPayScale(2500341);
		}
		if (mstEmployeeModel.getPayCommissionCode()==700005) {
			payscalelevel = mstEmployeeService.findEmployeeConfigurationGetpayscale(8);
		}
*/
		
		if(mstEmployeeModel.getPayCommissionCode().equals(2500347)    || mstEmployeeModel.getPayCommissionCode().equals(700016)) {
			lstsixpayscalelevel = mstEmployeeService.findEmployeeConfigurationGetSixPayScale(2500341);
		}
		if(mstEmployeeModel.getPayCommissionCode().equals(2500347)  || mstEmployeeModel.getPayCommissionCode().equals(700005) ) {
			payscalelevel = mstEmployeeService.findEmployeeConfigurationGetpayscale(mstEmployeeModel.getPayCommissionCode().intValue());
		}
		
		
		if (mstEmployeeModel.getPayscalelevelId() != null)
			if (!mstEmployeeModel.getPayscalelevelId().equals("") && !mstEmployeeModel.getPayscalelevelId().equals("0"))
				lstsvnbasicpay = mstEmployeeService
						.findEmployeeConfigurationGetsvnbasicpay(mstEmployeeModel.getPayscalelevelId());
		if (mstEmployeeModel.getAccountmaintainby() != null)
			if (!mstEmployeeModel.getAccountmaintainby().equals("")
					&& !mstEmployeeModel.getAccountmaintainby().equals("0")
					&& !(mstEmployeeModel.getAccountmaintainby() != null))
				lstpfSeries = mstEmployeeService.getPfSeries(mstEmployeeModel.getAccountmaintainby());

		model.addAttribute("lstsvnbasicpay", lstsvnbasicpay);
		model.addAttribute("lstpayscalelevel", payscalelevel);
		model.addAttribute("lstsixpayscalelevel", lstsixpayscalelevel);
		model.addAttribute("lstpfSeries", lstpfSeries);
		model.addAttribute("language", locale.getLanguage());

		addMenuAndSubMenu(model, messages);
		return "/views/employee-configuration-draft-form";
	}

	@RequestMapping(value = "/updateDraftCase", method = { RequestMethod.POST })
	public String updateDraftCase(@ModelAttribute("mstEmployeeModel") @Valid MstEmployeeModel mstEmployeeModel,
			BindingResult bindingResult, HttpSession session, @RequestParam("files") MultipartFile[] files,
			RedirectAttributes redirectAttributes, Model model, Locale locale) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		mstEmployeeModel.setCreatedUserId(messages.getUserId());
		long afterSaveId = mstEmployeeService.updateDraftCase(mstEmployeeModel, files);
		mstEmployeeModel.setCreatedUserId(messages.getUserId());
		if (afterSaveId > 0) {
			if (mstEmployeeModel.getAction().equals("saveAsDraft")) {
				redirectAttributes.addFlashAttribute("message", "DRAFTCASE");
			} else {
				redirectAttributes.addFlashAttribute("message", "SUCCESS");
			}
		} else
			redirectAttributes.addFlashAttribute("message", "Registration form is forwarded successfully");
		return "redirect:/ddoast/draftCaseList";

	}

	// View Stamp and signd Documents
	@GetMapping("/viewFileSign/{employeeId}")
	void viewFileSign(HttpServletResponse response, @PathVariable Long employeeId) throws IOException {
		try {
			if (employeeId != null) {
				String fileName = null;
				String filePath = null;
				List<Object[]> photodtl = empChangeDetailsService.getEmpSignPhoto(employeeId);

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

	// View Stamp and signd Documents
	@GetMapping("/viewFilephoto/{employeeId}")
	void viewFilephoto(HttpServletResponse response, @PathVariable Long employeeId) throws IOException {
		try {
			if (employeeId != null) {
				String fileName = null;
				String filePath = null;
				List<Object[]> photodtl = empChangeDetailsService.getEmpSignPhoto(employeeId);

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
	}

	@DeleteMapping("/deleteDraftCase")
	public ResponseEntity<Integer> deleteDraftCase(@RequestBody List<Long> employeeIds,HttpSession session) {
		OrgUserMst orgUserMst = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		Integer isDeleted=mstEmployeeService.deleteEmployeesByIds(employeeIds,orgUserMst);
		return ResponseEntity.ok(isDeleted);
	}

	@RequestMapping("/rejectEmpDtls/{empid}")
	public @ResponseBody List<Long> rejectEmployeeConfiguration(@PathVariable String empid, Model model,
			Locale locale) {
		List<Long> status = mstEmployeeService.rejectEmployeeConfiguration(empid);
		return status;
	}
	

}