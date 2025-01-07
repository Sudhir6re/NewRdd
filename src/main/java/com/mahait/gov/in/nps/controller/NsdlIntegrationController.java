package com.mahait.gov.in.nps.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.mahait.gov.in.controller.BaseController;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.MstDistrictModel;
import com.mahait.gov.in.model.MstStateModel;
import com.mahait.gov.in.nps.entity.MstEmployeeNPSEntity;
import com.mahait.gov.in.nps.entity.TrnNpsRegFileEntity;
import com.mahait.gov.in.nps.model.CSRFFormModel;
import com.mahait.gov.in.nps.model.MstEmployeeNPSModel;
import com.mahait.gov.in.nps.service.CSRFFormService;
import com.mahait.gov.in.nps.service.NsdlIntegrationService;
import com.mahait.gov.in.service.AllowanceDeductionWiseMstService;
import com.mahait.gov.in.service.CommonHomeMethodsService;
import com.mahait.gov.in.service.LocationMasterService;
import com.mahait.gov.in.service.MstBankService;
import com.mahait.gov.in.service.MstDesignationService;
import com.mahait.gov.in.service.MstEmployeeService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class NsdlIntegrationController extends BaseController{

	@Autowired
	NsdlIntegrationService nsdlIntegrationService;
	
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
	private CommonHomeMethodsService commonHomeMethodsService;

	@Autowired
	MstDesignationService mstDesignationService;

	@Autowired
	AllowanceDeductionWiseMstService allowanceDeductionWiseMstService;

	@GetMapping("/master/getPranNumber")
	public String getPranNumber(@ModelAttribute("trnNpsRegFileEntity") TrnNpsRegFileEntity trnNpsRegFileEntity,
			Model model, HttpServletRequest request, HttpServletResponse response, Locale locale, HttpSession session) {
		List<TrnNpsRegFileEntity> lstTrnNpsRegFileEntity = new ArrayList<TrnNpsRegFileEntity>();
		String message = (String) model.asMap().get("message");

		if (message != null) {
			model.addAttribute("message", message);
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("context", request.getContextPath());
		modelAndView.addObject("language", locale.getLanguage());
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		modelAndView.addObject("sessionMessages", messages.getUserId());
		modelAndView.addObject("userName", messages.getUserName());
		
		addMenuAndSubMenu(model,messages);

		model.addAttribute("trnNpsRegFileEntity", trnNpsRegFileEntity);
		model.addAttribute("lstTrnNpsRegFileEntity", nsdlIntegrationService.getBatchList());
		return "views/get-pran-number";
	}

	@GetMapping("/master/getNpsEmpDetailsById/{id}")
	public String getNpsEmpDetailsById(Model model, HttpServletRequest request, HttpServletResponse response,
			@PathVariable int id, Locale locale, HttpSession session) {
		String message = (String) model.asMap().get("message");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("context", request.getContextPath());
		modelAndView.addObject("language", locale.getLanguage());

		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		modelAndView.addObject("sessionMessages", messages.getUserId());
		modelAndView.addObject("userName", messages.getUserName());
	
		
		addMenuAndSubMenu(model,messages);
		
		model.addAttribute("message", message);
		List<MstEmployeeNPSEntity> lstMstEmployeeNPSEntity = new ArrayList<MstEmployeeNPSEntity>();
		lstMstEmployeeNPSEntity = nsdlIntegrationService.findNpsEmpDtlById(id);
		model.addAttribute("lstMstEmployeeNPSEntity", lstMstEmployeeNPSEntity);
		return "views/nps-emp-details";
	}

	@GetMapping("/master/printCSRFForm/{employeeId}")
	public String printCSRFForm(HttpServletRequest request, Model model, HttpServletResponse response, Locale locale,
			HttpSession session, @ModelAttribute("mstEmployeeNPSModel") MstEmployeeNPSModel mstEmployeeNPSModel,
			@PathVariable Long employeeId) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
	
		//modelAndView.addObject("sessionMessages", messages.getUserId());
		//modelAndView.addObject("userName", messages.getUserName());
	
		
		
		
		addMenuAndSubMenu(model,messages);

		CSRFFormModel lstCSRFFormModel = new CSRFFormModel();

		
							
		
		String message = (String) model.asMap().get("message");
		model.addAttribute("language", locale.getLanguage());
		model.addAttribute("context", request.getContextPath());
		

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
			mstDistrictModel.setDistrictCode(String.valueOf(objects[3]));
			mstDistrictModel.setDistrictName(String.valueOf(objects[4]));
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
			mstEmployeeNPSModel.setPinCode((mstEmployeeNPSEntity.getPinCode().toString()));
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
			mstEmployeeNPSModel.setAddress1(mstEmployeeNPSEntity.getEmpPermanentFlatUnitNo()+mstEmployeeNPSEntity.getEmpPermanentBuildingName());
			mstEmployeeNPSModel.setAddress2(mstEmployeeNPSEntity.getEmpPermanentLocality()+mstEmployeeNPSEntity.getEmpPermanentDistrict());

		}
		
		
		if (mstEmployeeNPSModel.getAddress1() != null) {

			char empAddr1[] = mstEmployeeNPSModel.getAddress1().toCharArray();
			model.addAttribute("empAddr1", empAddr1);
		}
		if (mstEmployeeNPSModel.getAddress2() != null) {

			char empAddr2[] = mstEmployeeNPSModel.getAddress2().toCharArray();
			model.addAttribute("empAddr2", empAddr2);
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

	/*	model.addAttribute("nomieeName", nomieeName);
		model.addAttribute("nomiNameRel", nomiNameRel);
		model.addAttribute("nomiNameGuard", nomiNameGuard);
*/
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
	}

	@GetMapping("/master/viewCSRFSign/{employeeId}")
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
	@GetMapping("/master/viewCSRFPhoto/{employeeId}")
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
	}
}
