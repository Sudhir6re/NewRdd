package com.mahait.gov.in.controller;


import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mahait.gov.in.common.CommonConstants;
import com.mahait.gov.in.entity.MstEmployeeEntity;
import com.mahait.gov.in.entity.MstScheme;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.PaybillHeadMpgModel;
import com.mahait.gov.in.service.CommonHomeMethodsService;
import com.mahait.gov.in.service.DDOCityCategoryService;
import com.mahait.gov.in.service.MstEmployeeService;
import com.mahait.gov.in.service.MstSchemeService;
import com.mahait.gov.in.service.PaybillGenerationTrnService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/ddoast")
public class PaybillGenerateController  extends BaseController {
	// protected final Log logger = LogFactory.getLog(getClass());

	/*
	 * @Autowired PaybillGenerateService paybillGenerateService;
	 */
	@Autowired
	CommonHomeMethodsService commonHomeMethodsService;

	@Autowired
	PaybillGenerationTrnService paybillHeadMpgService;

	@Autowired
	MstSchemeService mstSchemeService;

	@Autowired
	DDOCityCategoryService dDOCityCategoryService;

	@Autowired
	MstEmployeeService mstEmployeeService;

	/*
	 * @Autowired BDSIntegrationService bdsintegrationservice;
	 * 
	 * 
	 */
	@GetMapping("/paybillGenerate")
	public String paybillGenerate(@ModelAttribute("paybillHeadMpgModel") PaybillHeadMpgModel paybillHeadMpgModel,
			Model model, Locale locale, HttpSession session) {

		String message = (String) model.asMap().get("message");

		model.addAttribute("paybillHeadMpgModel", paybillHeadMpgModel);

		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");

		model.addAttribute("lstBillTypes",
				commonHomeMethodsService.findCommonMstByCommonCode(CommonConstants.COMMONMSTTABLE.BILL_TYPE));

		String isCityCategory = dDOCityCategoryService.getCityCategoryMappedWithDDo(messages.getDdoCode());

		model.addAttribute("isCityCategory", isCityCategory);
		model.addAttribute("language", locale.getLanguage());
		model.addAttribute("lstGetAllMonths", commonHomeMethodsService.lstGetAllMonths());
		model.addAttribute("lstGetAllYear", commonHomeMethodsService.lstGetAllYears());
		model.addAttribute("ddoCode", messages.getDdoCode());
		addMenuAndSubMenu(model,messages);

		model.addAttribute("lstSchemeBillGroup", mstSchemeService
				.findAllMpgSchemeBillGroupByDDOCode(messages.getDdoCode(), messages.getMstRoleEntity().getRoleId()));
		return "/views/paybill/paybill-generate";
	}

	@PostMapping("/savePaybillHeadMpg")
	public String savePaybillHeadMpg(
			@ModelAttribute("paybillHeadMpgModel") @Valid PaybillHeadMpgModel paybillHeadMpgModel,
			BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model, Locale locale,
			HttpSession session, HttpServletRequest request) {

		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		paybillHeadMpgModel.setCreatedUserId(messages.getUserId());
		paybillHeadMpgModel.setDdoCode(messages.getDdoCode());

		String clientIP = request.getRemoteAddr();

		String clientHostname = request.getRemoteHost();

		String namePIp = clientHostname + "/" + clientIP;

		System.out.println(namePIp);

		InetAddress local = null;
		try {
			local = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		paybillHeadMpgModel.setMacId(namePIp);

		Integer saveid = (Integer) paybillHeadMpgService.isPaybillExists(paybillHeadMpgModel.getSchemeBillgroupId(),
				paybillHeadMpgModel.getPaybillMonth(), paybillHeadMpgModel.getPaybillYear());
		Long afterSaveId = 0L;
		if (saveid == 0) {
			/*if (paybillHeadMpgModel.getBillTypeId() == 3) { // 1-->Regular,2-->Supplimentory
				afterSaveId = paybillHeadMpgService.saveSupPaybillHeadMpg(paybillHeadMpgModel);
			} else { // regular
			
			
                 			
*/				//afterSaveId = paybillHeadMpgService.savePaybillHeadMpg(paybillHeadMpgModel);
                afterSaveId = paybillHeadMpgService.generatePayBill(paybillHeadMpgModel);

			///}
/* * else if(paybillHeadMpgModel.getBillTypeId()==3){ // suspension bill
				 * afterSaveId =
				 * suspensionPaybillGenerationTrnService.saveSuspensionBill(paybillHeadMpgModel)
				 * ; }else if(paybillHeadMpgModel.getBillTypeId()==4){ //Nil Bill afterSaveId =
				 * nillBillGenerationTrnService.saveNilBill(paybillHeadMpgModel); }else
				 * if(paybillHeadMpgModel.getBillTypeId()==6){ //Nil Bill afterSaveId =
				 * arrearsBillGenerationTrnService.saveArrearsBill(paybillHeadMpgModel); }else
				 * if(paybillHeadMpgModel.getBillTypeId()==8){ afterSaveId =
				 * suspensionBrokenPaybillGenerationTrnService.saveSuspensionBrokenPaybillBill(
				 * paybillHeadMpgModel); } else { //Nil Bill afterSaveId =
				 * paybillGenerationpayForContractTrnService.savePaybillHeadMpgForContract(
				 * paybillHeadMpgModel); }
				 */
		}
		String success = "SUCCESS";
		if (afterSaveId > 0) {
			redirectAttributes.addFlashAttribute("message", success);
			redirectAttributes.addFlashAttribute("returnId", afterSaveId);
			redirectAttributes.addFlashAttribute("payBillMonth", paybillHeadMpgModel.getPaybillMonth());
			session.setAttribute("PAY_BILL_MONTH", paybillHeadMpgModel.getPaybillMonth());
		} else {
			redirectAttributes.addFlashAttribute("message", "FAILURE");
		}
		return "redirect:/ddoast/paybillGenerate";
	}

	@GetMapping(value = "/listSchemeDetails/{data}", consumes = { "application/json" }, produces = {
			"application/json" })
	public @ResponseBody List<MstScheme> multiply(@PathVariable String data, Model model, Locale locale) {

		List<MstScheme> MstScheme = mstSchemeService.findAllSchemeDetails(data);
		return MstScheme;
	}

	@GetMapping(value = "/getNumberOfEmployee/{logUser}/{schemeBillGroupId}/{monthName}/{yearName}/{paybillType}", consumes = {
			"application/json" }, produces = { "application/json" })
	public @ResponseBody Long getNumberOfEmployee(@PathVariable String logUser,
			@PathVariable BigInteger schemeBillGroupId, @PathVariable int monthName, @PathVariable int yearName,
			@PathVariable int paybillType, Model model, Locale locale) {

		/*
		 * RltDCPSDdoSchemeEntity RltDCPSDdoSchemeEntity = mstSchemeService
		 * .findAllMpgSchemeBillGroupbyParameter(schemeBillGroupId);
		 */
		String split[] = logUser.split("_");
		logUser = split[0];
		Long getNumberofEmployeeInBillGroup = mstSchemeService.findNumberOfEmployeeInBillGroup(logUser,
				schemeBillGroupId, monthName, yearName, paybillType);
		return getNumberofEmployeeInBillGroup;
	}

	@GetMapping(value = "/paybillgenerationIsExist/{billNumber}/{monthName}/{yearName}")
	public String paybillgenerationIsExist(@PathVariable BigInteger billNumber, @PathVariable int monthName,
			@PathVariable int yearName) {

		Integer saveid = (Integer) paybillHeadMpgService.getpaybilldata(billNumber, monthName, yearName);
		return saveid.toString();
	}

	@GetMapping(value = "/PaybillValidation/{billNumber}/{monthName}/{yearName}")
	public ResponseEntity<String> PaybillValidation(@PathVariable BigInteger billNumber, @PathVariable int monthName,
			@PathVariable int yearName, HttpSession session) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		List<Object[]> paybillgen = paybillHeadMpgService.findpaybill(billNumber, monthName, yearName, messages.getDdoCode());
		Integer existingData = paybillgen.size();
		String resJson = existingData.toString();
		return ResponseEntity.ok(resJson);
	}

	// check is employee not mapped with allowance deduction
	@GetMapping(value = "/getEmployeeMappedWithAllowanceDeduction/{schemeBillGroupId}")
	public @ResponseBody List<Object[]> getEmployeeMappedWithAllowanceDeduction(
			@PathVariable BigInteger schemeBillGroupId, HttpSession session) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		return dDOCityCategoryService.getEmployeeMappedWithAllowanceDeduction(schemeBillGroupId,
				messages.getDdoCode());
	}

	// check is already bill is generated according to sevaarth id
	@GetMapping(value = "/getSevaarthIdMappedWithBill/{billNumber}/{monthName}/{yearName}")
	public @ResponseBody List<Object[]> getSevaarthIdMappedWithBillUpdate(@PathVariable int monthName,
			@PathVariable int yearName, @PathVariable Long billNumber, HttpSession session) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		return dDOCityCategoryService.getSevaarthIdMappedWithBillUpdate(monthName, yearName, billNumber);
	}

	// check Broken Period according to sevaarth id
	@GetMapping(value = "/getSevaarthIdMappedWithBrokenPeriod/{noofemp}/{billNumber}/{monthName}/{yearName}")
	public @ResponseBody Object[] getSevaarthIdMappedWithBrokenPeriod(@PathVariable int noofemp,
			@PathVariable Long billNumber, @PathVariable int monthName, @PathVariable int yearName,
			HttpSession session) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		return paybillHeadMpgService.getSevaarthIdMappedWithBill(messages.getDdoCode(), noofemp, billNumber, monthName,
				yearName);
	}

	@GetMapping(value = "/getCheckIsBillInProcess/{monthName}/{yearName}/{schemeBillGroupId}/{paybillType}")
	public ResponseEntity<String> getCheckIsBillInProcess(@PathVariable int monthName, @PathVariable int yearName,
			@PathVariable BigInteger schemeBillGroupId, @PathVariable int paybillType, HttpSession session) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		int paybillgen = paybillHeadMpgService.getCheckIsBillInProcess(monthName, yearName, schemeBillGroupId,
				paybillType);
		Integer existingData = paybillgen;
		String resJson = existingData.toString();
		return ResponseEntity.ok(resJson);
	}

	@GetMapping(value = "/isEmpRetired/{monthName}/{yearName}/{schemeBillGroupId}/{paybillType}")
	public ResponseEntity<String> isEmpRetired(@PathVariable int monthName, @PathVariable int yearName,
			@PathVariable BigInteger schemeBillGroupId, @PathVariable int paybillType, HttpSession session) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");

		String existingData = paybillHeadMpgService.isEmpRetired(monthName, yearName, schemeBillGroupId, paybillType,
				messages.getDdoCode());

		System.out.println("response-----" + existingData);
		return ResponseEntity.ok(existingData);
	}

	@GetMapping(value = "/checkedBgisAndGisCatNull/{schemeBillGroupId}")
	public ResponseEntity<List<MstEmployeeEntity>> checkedBgisAndGisCatNull(@PathVariable String schemeBillGroupId,
			HttpSession session) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		List<MstEmployeeEntity> lstMstEmployeeEntity = paybillHeadMpgService.checkedBgisAndGisCatNull(schemeBillGroupId,
				messages.getUserName());
		return ResponseEntity.ok(lstMstEmployeeEntity);
	}
	


}