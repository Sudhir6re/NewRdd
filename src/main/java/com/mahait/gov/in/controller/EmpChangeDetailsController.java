package com.mahait.gov.in.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mahait.gov.in.common.CommonConstants;
import com.mahait.gov.in.common.CommonConstants.STATUS;
import com.mahait.gov.in.common.CommonUtils;
import com.mahait.gov.in.entity.CmnLookupMst;
import com.mahait.gov.in.entity.MstDesignationEntity;
import com.mahait.gov.in.entity.MstEmployeeDetailEntity;
import com.mahait.gov.in.entity.MstEmployeeEntity;
import com.mahait.gov.in.entity.MstNomineeDetailsEntity;
import com.mahait.gov.in.entity.MstNomineeDetailsHistEntity;
import com.mahait.gov.in.entity.MstPayCommissionEntity;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.entity.ReligionMstEntity;
import com.mahait.gov.in.model.DDOScreenModel;
import com.mahait.gov.in.model.EmpChangeDetailsModel;
import com.mahait.gov.in.model.MstDistrictModel;
import com.mahait.gov.in.model.MstNomineeDetailsModel;
import com.mahait.gov.in.model.MstStateModel;
import com.mahait.gov.in.service.CommonHomeMethodsService;
import com.mahait.gov.in.service.EmpChangeDetailsService;
import com.mahait.gov.in.service.LocationMasterService;
import com.mahait.gov.in.service.MstBankService;
import com.mahait.gov.in.service.MstDesignationService;
import com.mahait.gov.in.service.MstEmployeeService;

import jakarta.mail.internet.ParseException;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping(value = { "/ddoast", "/ddo" })

public class EmpChangeDetailsController extends BaseController {

	// @Autowired
	// CommonHomeMethodsService commonHomeMethodsService;
	//
	@Autowired
	EmpChangeDetailsService empChangeDetailsService;
	//
	@Autowired
	CommonHomeMethodsService commonHomeMethodsService;

	@Autowired
	private Environment environment;

	//
	@Autowired
	LocationMasterService locationMasterService;
	//
	@Autowired
	MstEmployeeService mstEmployeeService;
	//
	@Autowired
	MstDesignationService mstDesignationService;

	@Autowired
	MstBankService mstBankService;

	//
	//
	//
	// List<EmpChangeDetailsModel> EmpChangeDetailsModelList = new ArrayList<>();
	//
	//
	@GetMapping("/changeDetails")
	public String changeDetails(@ModelAttribute("mstEmployeeEntity") EmpChangeDetailsModel empChangeDetailsModel,
			Model model, Locale locale, HttpSession session) throws ParseException {

		String message = (String) model.asMap().get("message");

		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");

		if (message != null && message.equals("SUCCESS")) {
			if (locale != null && locale.getLanguage().equalsIgnoreCase("en")) {
				model = CommonUtils.initModel(CommonConstants.Message.SAVEDRAFT, STATUS.SUCCESS, model);
			} else {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_MARATHI, STATUS.SUCCESS, model);
			}
		}
		model.addAttribute("language", locale.getLanguage());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		List<MstEmployeeEntity> employeeConfigurationService = empChangeDetailsService
				.getEmployeeDetails(messages.getDdoCode(), locale.getLanguage());

		model.addAttribute("empLst", employeeConfigurationService);

		model.addAttribute("mstEmployeeEntity", empChangeDetailsModel);
		model.addAttribute("roleId", messages.getMstRoleEntity().getRoleId());

		addMenuAndSubMenu(model, messages);

		return "/views/changeDetails";
	}

	// @PostMapping("/editEmployeeConfiguration")
	// @RequestMapping(value = "/editEmployeeConfiguration", method = {
	// RequestMethod.GET, RequestMethod.POST })
	// public String
	// editEmployeeConfiguration(@ModelAttribute("empChangeDetailsModel")
	// EmpChangeDetailsModel empChangeDetailsModel,
	// Model model, Locale locale, HttpSession session) {
	@RequestMapping(value = "/changeEmpDtls/{empId}", method = { RequestMethod.GET, RequestMethod.POST })
	public String editEmployeeConfiguration(
			@ModelAttribute("empChangeDetailsModel") EmpChangeDetailsModel empChangeDetailsModel, Model model,
			Locale locale, HttpSession session, @PathVariable long empId) {

		String message = (String) model.asMap().get("message");
		empChangeDetailsModel = empChangeDetailsService.getEmployeeinfo(empId);

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

		rootPath += empChangeDetailsModel.getDeptNm() + "/" + empChangeDetailsModel.getEmployeeId();
		if (empChangeDetailsModel.getPhotoAttachmentId() != null)
			if (!empChangeDetailsModel.getPhotoAttachmentId().equals("")) {
				/*
				 * String strphotoimg = mstEmployeeService.findEmployeeConfigurationUploadImage(
				 * mstEmployeeModel.getPhotoAttachmentId(), "photo" +
				 * mstEmployeeModel.getDeptNm() + mstEmployeeModel.getEmployeeId() + ".jpg");
				 * mstEmployeeModel.setImagePath(strphotoimg);
				 */

				empChangeDetailsModel.setImagePath(rootPath + "/" + "photo.jpg");
			}
		if (empChangeDetailsModel.getSignatureAttachmentId() != null)
			if (!empChangeDetailsModel.getSignatureAttachmentId().equals("")) {
				/*
				 * String strsignimg = mstEmployeeService.findEmployeeConfigurationUploadImage(
				 * mstEmployeeModel.getSignatureAttachmentId(), "signature" +
				 * mstEmployeeModel.getDeptNm() + mstEmployeeModel.getEmployeeId() + ".jpg");
				 * mstEmployeeModel.setImagePathSign(strsignimg);
				 */

				empChangeDetailsModel.setImagePath(rootPath + "/" + "signature.jpg");
			}
		// String src1="Output1.jpg";

		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		long locId = mstEmployeeService.getLocationCode(empChangeDetailsModel.getDdoCode());
		model.addAttribute("sevaarthId", empChangeDetailsModel.getSevaarthId());

		// Get annuation age start
		if (empChangeDetailsModel.getCadreId() != null)
			if (empChangeDetailsModel.getCadreId().intValue() != 0) {
				List<Object[]> lstCadregroup = mstEmployeeService.getCadreGroupMstData(locale.getLanguage(),
						empChangeDetailsModel.getCadreId().toString());
				if (!lstCadregroup.isEmpty()) {
					for (Object[] objLst : lstCadregroup) {
						empChangeDetailsModel.setSuperannuationage(Long.valueOf(objLst[1].toString()));
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
		empChangeDetailsModel.setAction("Edit");
		// mstEmployeeModel.setDeptNm(mstdeptentity.getSubDepartmentShortName().toString());
		// mstEmployeeModel.setDeptNm(ddoScreenModel.getSubDeptShortName().toString());
		empChangeDetailsModel.setEmployeeId(empChangeDetailsModel.getEmployeeId());
		model.addAttribute("empChangeDetailsModel", empChangeDetailsModel);
		model.addAttribute("employeeId", empChangeDetailsModel.getEmployeeId());
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
		if (empChangeDetailsModel.getEmployeeId() != null) {
			lstNmnDtlsEntity = mstEmployeeService.getNominees(empChangeDetailsModel.getEmployeeId().toString());
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
			List<Object[]> listDistrict = locationMasterService.findAllDistricts(empChangeDetailsModel.getStateCode());
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
					mstEmployeeService.getBankBranch(String.valueOf(empChangeDetailsModel.getBankId().toString())));
			model.addAttribute("lstCurrentPost", mstEmployeeService.GetCurrentPostByLvlTwo(
					empChangeDetailsModel.getDesignationId(), empChangeDetailsModel.getDdoCode(), locId));

		} catch (Exception e) {
			// TODO: handle exception
			// e.printStackTrace();
		}

		model.addAttribute("lstAllDistrict", listDistrictemdl);

		List<Object[]> payscalelevel = new ArrayList<Object[]>();
		List<Object[]> lstsixpayscalelevel = new ArrayList<Object[]>();
		List<Object[]> lstsvnbasicpay = new ArrayList<Object[]>();
		List<Object[]> lstpfSeries = new ArrayList<Object[]>();
		if (empChangeDetailsModel.getPayCommissionCode() != null) {

			if (empChangeDetailsModel.getPayCommissionCode().equals(2500347)
					|| empChangeDetailsModel.getPayCommissionCode() != 700016) {
				lstsixpayscalelevel = mstEmployeeService.findEmployeeConfigurationGetSixPayScale(2500341);
			}

			if (empChangeDetailsModel.getPayCommissionCode().equals(2500347)
					|| empChangeDetailsModel.getPayCommissionCode() == 700016) {
				lstsixpayscalelevel = mstEmployeeService.findEmployeeConfigurationGetSixPayScale(2500341);
			}
			if (empChangeDetailsModel.getPayCommissionCode().equals(2500347)
					|| empChangeDetailsModel.getPayCommissionCode() == 700005) {
				payscalelevel = mstEmployeeService
						.findEmployeeConfigurationGetpayscale(empChangeDetailsModel.getPayCommissionCode().intValue());
			}
		}

		if (empChangeDetailsModel.getPayscalelevelId() != null)
			if (!empChangeDetailsModel.getPayscalelevelId().equals("")
					&& !empChangeDetailsModel.getPayscalelevelId().equals("0"))
				lstsvnbasicpay = mstEmployeeService
						.findEmployeeConfigurationGetsvnbasicpay(empChangeDetailsModel.getPayscalelevelId());
		if (empChangeDetailsModel.getAccountmaintainby() != null)
			if (!empChangeDetailsModel.getAccountmaintainby().equals("")
					&& !empChangeDetailsModel.getAccountmaintainby().equals("0"))
				lstpfSeries = mstEmployeeService.getPfSeries(empChangeDetailsModel.getAccountmaintainby());

		model.addAttribute("lstsvnbasicpay", lstsvnbasicpay);
		model.addAttribute("lstpayscalelevel", payscalelevel);
		model.addAttribute("lstsixpayscalelevel", lstsixpayscalelevel);
		model.addAttribute("lstpfSeries", lstpfSeries);
		model.addAttribute("language", locale.getLanguage());
		model.addAttribute("roleId", messages.getMstRoleEntity().getRoleId());

		addMenuAndSubMenu(model, messages);
		// return "/views/approve-employee-configuration";
		return "/views/edit-employee-configuration";
	}

	/*
	 * @GetMapping("/viewFilephoto/{employeeId}") void
	 * viewFilephoto(HttpServletResponse response, @PathVariable Long employeeId)
	 * throws IOException { try { if (employeeId != null) { String fileName = null;
	 * String filePath = null; List<Object[]> photodtl =
	 * empChangeDetailsService.getEmpSignPhoto(employeeId);
	 * 
	 * // mstEmployeeModel=empChangeDetailsService.getEmpData(empId); for (Object[]
	 * obj : photodtl) { fileName = (String) obj[0]; filePath = obj[0].toString(); }
	 * if (photodtl.size() > 0) {
	 * 
	 * File file = new File(filePath); FileInputStream inputStream = new
	 * FileInputStream(file);
	 * 
	 * if (filePath.contains("pdf")) { response.setContentType("application/pdf"); }
	 * else if (filePath.contains("jpg") || filePath.contains("jpeg")) {
	 * response.setContentType("image/jpeg"); } else if (filePath.contains("png")) {
	 * response.setContentType("image/png"); } else if (filePath.contains("gif")) {
	 * response.setContentType("image/gif"); } else if (filePath.contains("mp4")) {
	 * response.setContentType("video/mp4"); } response.setContentLength((int)
	 * file.length()); response.setHeader("Content-Disposition",
	 * "inline;filename=\"" + file.getName() + "\"");
	 * FileCopyUtils.copy(inputStream, response.getOutputStream()); } } } catch
	 * (Exception e) { e.printStackTrace(); } }
	 * 
	 * // View Stamp and signd Documents
	 * 
	 * @GetMapping("/viewFileSign/{employeeId}") void
	 * viewFileSign(HttpServletResponse response, @PathVariable Long employeeId)
	 * throws IOException { try { if (employeeId != null) { String fileName = null;
	 * String filePath = null; List<Object[]> photodtl =
	 * empChangeDetailsService.getEmpSignPhoto(employeeId);
	 * 
	 * // mstEmployeeModel=empChangeDetailsService.getEmpData(empId); for (Object[]
	 * obj : photodtl) { fileName = (String) obj[1]; filePath = obj[1].toString(); }
	 * if (photodtl.size() > 0) {
	 * 
	 * File file = new File(filePath); FileInputStream inputStream = new
	 * FileInputStream(file);
	 * 
	 * if (filePath.contains("pdf")) { response.setContentType("application/pdf"); }
	 * else if (filePath.contains("jpg") || filePath.contains("jpeg")) {
	 * response.setContentType("image/jpeg"); } else if (filePath.contains("png")) {
	 * response.setContentType("image/png"); } else if (filePath.contains("gif")) {
	 * response.setContentType("image/gif"); } else if (filePath.contains("mp4")) {
	 * response.setContentType("video/mp4"); } response.setContentLength((int)
	 * file.length()); response.setHeader("Content-Disposition",
	 * "inline;filename=\"" + file.getName() + "\"");
	 * FileCopyUtils.copy(inputStream, response.getOutputStream()); } } } catch
	 * (Exception e) { e.printStackTrace(); } }
	 */

	@RequestMapping(value = "/saveEmployeeChangeDetails", method = { RequestMethod.POST })
	public String saveEmployeeConfigurationChangeDetails(
			@ModelAttribute("empChangeDetailsModel") @Valid EmpChangeDetailsModel empChangeDetailsModel,
			BindingResult bindingResult, HttpSession session, @RequestParam("files") MultipartFile[] files,
			RedirectAttributes redirectAttributes, Model model, Locale locale) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");

		String strAction = empChangeDetailsModel.getAction();
		if (strAction.equals("EditBack")) {
			return "redirect:/ddoast/changeDetails";
		}
		if (strAction.equals("Edit")) {
			empChangeDetailsModel.setUpdatedUserId(messages.getUserId());
			empChangeDetailsModel.setDdoCode(messages.getDdoCode());
			long afterSaveId = empChangeDetailsService.updateEmployeeChangeDetails(empChangeDetailsModel, files);
			// int result=
			// mstEmployeeService.savePhotoSignature(files,mstEmployeeModel.getDeptNm(),mstEmployeeModel.getEmployeeId());
			if (afterSaveId > 0) {

				if (empChangeDetailsModel.getIsActive() == 2) {
					redirectAttributes.addFlashAttribute("message", "SUCCESS");
				} else {
					redirectAttributes.addFlashAttribute("message", "Change detail form is forwarded successfully");
				}
			}
			return "redirect:/ddoast/changeDetails";
			// return "redirect:/level1/employeeDetailsList/{"+message+"}";
		}
		return strAction;

	}

	@GetMapping("/ApprovOrRejectChngdtls")
	public String ApprovOrRejectChngdtls(
			@ModelAttribute("mstEmployeeDetailEntity") MstEmployeeDetailEntity mstEmployeeDetailEntity, Model model,
			Locale locale, HttpSession session) throws ParseException {

		String message = (String) model.asMap().get("message");

		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");

		addMenuAndSubMenu(model, messages);

		if (message != null && message.equals("SUCCESS")) {
			if (locale != null && locale.getLanguage().equalsIgnoreCase("en")) {
				model = CommonUtils.initModel(CommonConstants.Message.SAVEDRAFT, STATUS.SUCCESS, model);
			} else {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_MARATHI, STATUS.SUCCESS, model);
			}
		}
		model.addAttribute("language", locale.getLanguage());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		List<MstEmployeeDetailEntity> empLst = empChangeDetailsService
				.findEmpLstforApprovChngDtls(messages.getDdoCode());

		model.addAttribute("empLst", empLst);
		model.addAttribute("readonly", "readonly");
		model.addAttribute("mstEmployeeEntity", mstEmployeeDetailEntity);

		return "/views/ChangeDetailsDDologin";
	}

	@RequestMapping(value = "/changeEmpDtlsLvl2/{empId}", method = { RequestMethod.GET, RequestMethod.POST })
	public String approveEmployeeConfiguration(
			@ModelAttribute("empChangeDetailsModel") EmpChangeDetailsModel empChangeDetailsModel, Model model,
			Locale locale, HttpSession session, @PathVariable long empId) {

		String message = (String) model.asMap().get("message");
		empChangeDetailsModel = empChangeDetailsService.getEmployeeinfofordetails(empId);

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

		rootPath += empChangeDetailsModel.getDeptNm() + "/" + empChangeDetailsModel.getEmployeeId();
		if (empChangeDetailsModel.getPhotoAttachmentId() != null)
			if (!empChangeDetailsModel.getPhotoAttachmentId().equals("")) {
				/*
				 * String strphotoimg = mstEmployeeService.findEmployeeConfigurationUploadImage(
				 * mstEmployeeModel.getPhotoAttachmentId(), "photo" +
				 * mstEmployeeModel.getDeptNm() + mstEmployeeModel.getEmployeeId() + ".jpg");
				 * mstEmployeeModel.setImagePath(strphotoimg);
				 */

				empChangeDetailsModel.setImagePath(rootPath + "/" + "photo.jpg");
			}
		if (empChangeDetailsModel.getSignatureAttachmentId() != null)
			if (!empChangeDetailsModel.getSignatureAttachmentId().equals("")) {
				/*
				 * String strsignimg = mstEmployeeService.findEmployeeConfigurationUploadImage(
				 * mstEmployeeModel.getSignatureAttachmentId(), "signature" +
				 * mstEmployeeModel.getDeptNm() + mstEmployeeModel.getEmployeeId() + ".jpg");
				 * mstEmployeeModel.setImagePathSign(strsignimg);
				 */

				empChangeDetailsModel.setImagePath(rootPath + "/" + "signature.jpg");
			}
		// String src1="Output1.jpg";

		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		long locId = mstEmployeeService.getLocationCode(empChangeDetailsModel.getDdoCode());
		model.addAttribute("sevaarthId", empChangeDetailsModel.getSevaarthId());

		// Get annuation age start
		if (empChangeDetailsModel.getCadreId() != null)
			if (empChangeDetailsModel.getCadreId().intValue() != 0) {
				List<Object[]> lstCadregroup = mstEmployeeService.getCadreGroupMstData(locale.getLanguage(),
						empChangeDetailsModel.getCadreId().toString());
				if (!lstCadregroup.isEmpty()) {
					for (Object[] objLst : lstCadregroup) {
						empChangeDetailsModel.setSuperannuationage(Long.valueOf(objLst[1].toString()));
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
		empChangeDetailsModel.setAction("Edit");
		// mstEmployeeModel.setDeptNm(mstdeptentity.getSubDepartmentShortName().toString());
		// mstEmployeeModel.setDeptNm(ddoScreenModel.getSubDeptShortName().toString());
		empChangeDetailsModel.setEmployeeId(empChangeDetailsModel.getEmployeeId());
		model.addAttribute("empChangeDetailsModel", empChangeDetailsModel);
		model.addAttribute("employeeId", empChangeDetailsModel.getEmployeeId());
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
		List<MstNomineeDetailsHistEntity> lstNmnDtlsEntity = null;
		if (empChangeDetailsModel.getEmployeeId() != null) {
			lstNmnDtlsEntity = empChangeDetailsService.getNominees(empChangeDetailsModel.getEmployeeId().toString());
		}
		if (lstNmnDtlsEntity != null)
			for (Iterator iterator = lstNmnDtlsEntity.iterator(); iterator.hasNext();) {
				MstNomineeDetailsHistEntity mstNomineeDetailsEntity = (MstNomineeDetailsHistEntity) iterator.next();
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
			List<Object[]> listDistrict = locationMasterService.findAllDistricts(empChangeDetailsModel.getStateCode());
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
					mstEmployeeService.getBankBranch(String.valueOf(empChangeDetailsModel.getBankId().toString())));
			model.addAttribute("lstCurrentPost", empChangeDetailsService.GetCurrentPostByLvlTwoDetails(
					empChangeDetailsModel.getDesignationId(), empChangeDetailsModel.getDdoCode(), locId));

		} catch (Exception e) {
			// TODO: handle exception
			// e.printStackTrace();
		}

		model.addAttribute("lstAllDistrict", listDistrictemdl);

		List<Object[]> payscalelevel = new ArrayList<Object[]>();
		List<Object[]> lstsixpayscalelevel = new ArrayList<Object[]>();
		List<Object[]> lstsvnbasicpay = new ArrayList<Object[]>();
		List<Object[]> lstpfSeries = new ArrayList<Object[]>();

		if (empChangeDetailsModel.getPayCommissionCode() != null) {

			if (empChangeDetailsModel.getPayCommissionCode().equals(2500347)
					|| empChangeDetailsModel.getPayCommissionCode() != 700016) {
				lstsixpayscalelevel = mstEmployeeService.findEmployeeConfigurationGetSixPayScale(2500341);
			}

			if (empChangeDetailsModel.getPayCommissionCode().equals(2500347)
					|| empChangeDetailsModel.getPayCommissionCode() == 700016) {
				lstsixpayscalelevel = mstEmployeeService.findEmployeeConfigurationGetSixPayScale(2500341);
			}
			if (empChangeDetailsModel.getPayCommissionCode().equals(2500347)
					|| empChangeDetailsModel.getPayCommissionCode() == 700005) {
				payscalelevel = mstEmployeeService
						.findEmployeeConfigurationGetpayscale(empChangeDetailsModel.getPayCommissionCode().intValue());
			}
		}

		if (empChangeDetailsModel.getPayscalelevelId() != null)
			if (!empChangeDetailsModel.getPayscalelevelId().equals("")
					&& !empChangeDetailsModel.getPayscalelevelId().equals("0"))
				lstsvnbasicpay = mstEmployeeService
						.findEmployeeConfigurationGetsvnbasicpay(empChangeDetailsModel.getPayscalelevelId());
		if (empChangeDetailsModel.getAccountmaintainby() != null)
			if (!empChangeDetailsModel.getAccountmaintainby().equals("")
					&& !empChangeDetailsModel.getAccountmaintainby().equals("0"))
				lstpfSeries = mstEmployeeService.getPfSeries(empChangeDetailsModel.getAccountmaintainby());

		model.addAttribute("lstsvnbasicpay", lstsvnbasicpay);
		model.addAttribute("lstpayscalelevel", payscalelevel);
		model.addAttribute("lstsixpayscalelevel", lstsixpayscalelevel);
		model.addAttribute("lstpfSeries", lstpfSeries);
		model.addAttribute("language", locale.getLanguage());
		model.addAttribute("empChangeDetailsModel", empChangeDetailsModel);

		return "/views/Approve-Emp-Change-Dtls";

	}

	@PostMapping("/approveRejectChangeEmpDtls")
	public String approveRejectChangeEmpDtls(
			@ModelAttribute("empChangeDetailsModel") @Valid EmpChangeDetailsModel empChangeDetailsModel,
			HttpSession session, RedirectAttributes redirectAttributes, Model model, Locale locale,
			@RequestParam("files") MultipartFile[] files) {

		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");

		long mstEmployeeEntityResponse = empChangeDetailsService.updateChangeEmpDtls(empChangeDetailsModel, files);

		if (mstEmployeeEntityResponse > 0) {
			redirectAttributes.addFlashAttribute("message", "SUCCESS");
			redirectAttributes.addFlashAttribute("message", "Change detail approved");
		}
		return "redirect:/ddo/ApprovOrRejectChngdtls"; /* redirects to controller URL */
	}

}
