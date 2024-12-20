package com.mahait.gov.in.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mahait.gov.in.common.CommonConstants;
import com.mahait.gov.in.common.CommonConstants.STATUS;
import com.mahait.gov.in.common.CommonUtils;
import com.mahait.gov.in.entity.EmployeeIncrementEntity;
import com.mahait.gov.in.entity.MstEmployeeEntity;
import com.mahait.gov.in.entity.MstPayCommissionEntity;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.AnnualIncrementModel;
import com.mahait.gov.in.model.MpgSchemeBillGroupModel;
import com.mahait.gov.in.model.MstSchemeModel;
import com.mahait.gov.in.model.TopicModel;
import com.mahait.gov.in.service.AnnualIncrementService;
import com.mahait.gov.in.service.CommonHomeMethodsService;
import com.mahait.gov.in.service.DdoBillGroupService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/ddoast")
public class AnnualIncrementController  extends BaseController {
	// protected final Log logger = LogFactory.getLog(getClass());
	@Autowired
	DdoBillGroupService ddoBillGroupService;
	@Autowired
	CommonHomeMethodsService commonHomeMethodsService;

	@Autowired
	AnnualIncrementService annualIncrementService;

	List<AnnualIncrementModel> annualIncrementModel = new ArrayList<>();

	@GetMapping("/annualIncrement")
	public String annualIncrement(
			@ModelAttribute("mpgSchemeBillGroupModel") MpgSchemeBillGroupModel mpgSchemeBillGroupModel,
			@ModelAttribute("mstSchemeModel") MstSchemeModel mstSchemeModel, Model model, Locale locale,
			HttpSession session) {
		String message = (String) model.asMap().get("message");
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		List<TopicModel> menuList = new ArrayList<>();
		List<TopicModel> subMenuList = new ArrayList<>();
		List<MstPayCommissionEntity> payCommssionEntityList = new ArrayList<>();
		try {
			payCommssionEntityList = annualIncrementService.findAppPayCommssion();
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("menuList", menuList);
		model.addAttribute("subMenuList", subMenuList);
		model.addAttribute("payCommssionEntityList", payCommssionEntityList);

		model.addAttribute("lstAllBillGroup", ddoBillGroupService.lstBillName(messages.getUserName()));
		/*model.addAttribute("lstAllBillGroup", mpgSchemeBillGroupService
				.findAllMpgSchemeBillGroupByDDOCode(messages.getUserName(), messages.getRole_id()));*/
		/*
		 * model.addAttribute("lstMpgSchemeBillGroupDataTable",
		 * mpgSchemeBillGroupService.findAllMpgSchemeBillGroupByDDOCode(messages.
		 * getUserName()));
		 */
		model.addAttribute("language", locale.getLanguage());
		if (message != null && message.equals("SUCCESS")) {
			if (locale != null && locale.getLanguage().equalsIgnoreCase("en")) {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_ENGLSH, STATUS.SUCCESS, model);
			} else {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_MARATHI, STATUS.SUCCESS, model);
			}
		}
		
		addMenuAndSubMenu(model,messages);	
		model.addAttribute("language", locale.getLanguage());
		return "/views/annual-increment";
	}

	@GetMapping(value = "/findAllEmpForDue/{schemeBillGroupId}/{payCommision}")
	public @ResponseBody List<Object[]> findAllEmpForDue(@PathVariable Long schemeBillGroupId,
			@PathVariable Long payCommision, HttpSession session) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		
		List<Object[]> empList = annualIncrementService.findAllEmpForDue(schemeBillGroupId.toString(),
				payCommision,messages.getUserName());
		return empList;
	}

	@GetMapping(value = "/findNextMatrix/{empId}/{basicPay}/{level}")
	public @ResponseBody List<Object[]> findNextMatrix(@PathVariable Integer empId, @PathVariable Integer basicPay,
			@PathVariable Integer level) {
		
		List<Object[]> nxtMatrix = annualIncrementService.findNextMatrix(empId, basicPay, level);
		return nxtMatrix;
	}

	@GetMapping(value = "/saveAnnualIncrementData/{empIds}/{sevaarthIds}/{basicPays}/{basicPaysIncrementes}/{certificateNumber}/{withEffectiveDate}/{remarks}/{month1}/{nextIncrementDate}/{payCommision}")
	public @ResponseBody List<Object> saveAnnualIncrementData(@PathVariable String[] empIds,
			@PathVariable String[] sevaarthIds, @PathVariable String[] basicPaysIncrementes,
			@PathVariable String[] basicPays, @PathVariable String certificateNumber,
			@PathVariable String withEffectiveDate, @PathVariable String[] remarks, @PathVariable Integer month1,@PathVariable String nextIncrementDate,@PathVariable Long payCommision,
			Locale locale, HttpSession session) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		String certificateNumber1 = certificateNumber;
		String orderDate1 = withEffectiveDate;
		String orderDate2 = nextIncrementDate;
		System.out.println("**********orderDate1*************" + orderDate1);
		Integer monthId = month1;
		for (int i = 0; i < empIds.length; i++) {
			String empId1 = empIds[i];
			String sevaarthIds1 = sevaarthIds[i];
			String basicPays1 = basicPays[i];
			String basicPaysIncrementes1 = basicPaysIncrementes[i];
			String remarks1 = remarks[i];
			try {
				Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(orderDate1);
				Date toIncDate = new SimpleDateFormat("yyyy-MM-dd").parse(orderDate2);
				

				Integer currentBasicLevelId = Integer
						.valueOf(annualIncrementService.oldMatrixId(sevaarthIds1, basicPays1));

				Integer incrementBasicLevelId = Integer
						.valueOf(annualIncrementService.newMatrixId(sevaarthIds1, basicPaysIncrementes1));

				EmployeeIncrementEntity employeeIncrementEntity = new EmployeeIncrementEntity();
				
				MstEmployeeEntity mstEmployeeEntity = annualIncrementService.getSevenPcBasicDetail(sevaarthIds1);

				// employeeIncrementEntity.setBasicPayIncrementId(basicPayIncrementId1);
				employeeIncrementEntity.setCurrentBasicPay(Double.parseDouble(basicPays1));
				employeeIncrementEntity.setEffective_from_date(date1);
				employeeIncrementEntity.setEmployeeId(Long.parseLong(empId1));
				employeeIncrementEntity.setIncrementBasicPaySal(Double.parseDouble(basicPaysIncrementes1));
				employeeIncrementEntity.setIncrementOrderDate(date1);
				employeeIncrementEntity.setIncrementOrderNo(certificateNumber1);
				employeeIncrementEntity.setPreBasicPay(Double.parseDouble(basicPays1));
				employeeIncrementEntity.setRemark(remarks1);
				employeeIncrementEntity.setSevaarthId(sevaarthIds1);
				employeeIncrementEntity.setCreatedDate(new Date());
				employeeIncrementEntity.setMonth(monthId);
				employeeIncrementEntity.setIsActive('1');
				employeeIncrementEntity.setDdoCode(messages.getDdoCode());
				employeeIncrementEntity.setTo_increment_date(toIncDate);
				employeeIncrementEntity.setCurrentBasicLevelId(Long.valueOf(currentBasicLevelId));
				employeeIncrementEntity.setIncrementBasicLevelId(Long.valueOf(incrementBasicLevelId));
				employeeIncrementEntity.setPaycommissionId(payCommision);

				if(!mstEmployeeEntity.equals(null)) {
					mstEmployeeEntity.setSevenPcBasic(Double.parseDouble(basicPaysIncrementes1));
				}
				
				EmployeeIncrementEntity previousIncrementDtlsObj = annualIncrementService
						.getPreIncDtsByempId(Integer.parseInt(empId1));
				if (previousIncrementDtlsObj != null) {
					previousIncrementDtlsObj.setTo_increment_date(subtractDate(date1, 1));
					annualIncrementService.updateEmpIncrementToDate(previousIncrementDtlsObj, messages);
				}

				annualIncrementService.saveAnnualIncrement(employeeIncrementEntity);
				
				annualIncrementService.saveAnnualIncrementBasicMst(mstEmployeeEntity);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		return null;
	}

	/*@GetMapping("/approvalAnnualIncrement")
	public String approvalAnnualIncrement(
			@ModelAttribute("annualIncrementModel") AnnualIncrementModel annualIncrementModel,
			@ModelAttribute("mstSchemeModel") MstSchemeModel mstSchemeModel, Model model, Locale locale,
			HttpSession session) {
		String message = (String) model.asMap().get("message");
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		List<TopicModel> menuList = new ArrayList<>();
		List<TopicModel> subMenuList = new ArrayList<>();
		List<MstPayCommissionEntity> payCommssionEntityList = new ArrayList<>();
		try {
			payCommssionEntityList = annualIncrementService.findAppPayCommssion();
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("menuList", menuList);
		model.addAttribute("subMenuList", subMenuList);
		model.addAttribute("payCommssionEntityList", payCommssionEntityList);
		model.addAttribute("lstAllBillGroup", ddoBillGroupService.lstBillName(messages.getUserName()));
		model.addAttribute("language", locale.getLanguage());
		if (message != null && message.equals("SUCCESS")) {
			if (locale != null && locale.getLanguage().equalsIgnoreCase("en")) {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_ENGLSH, STATUS.SUCCESS, model);
			} else {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_MARATHI, STATUS.SUCCESS, model);
			}
		}
		model.addAttribute("lstEmp", annualIncrementService.getEmpDataForIncrementApproval(messages.getUserName()));
		model.addAttribute("language", locale.getLanguage());
		model.addAttribute("lstGetAllMonths", commonHomeMethodsService.lstGetAllMonths());
		model.addAttribute("lstGetAllYear", commonHomeMethodsService.lstGetAllYears());
		return "/views/approvalAnnual-increment";
	}*/

	@GetMapping("/MTR21/{orderNo}/{incrementOrderDate}")
	public String MTR21(@ModelAttribute("annualIncrementModel") AnnualIncrementModel annualIncrementModel, Model model,
			Locale locale, HttpSession session, @PathVariable String orderNo,@PathVariable  String incrementOrderDate) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		List<TopicModel> menuList = new ArrayList<>();
		List<TopicModel> subMenuList = new ArrayList<>();

		model.addAttribute("systemdate", sdf.format(new Date()));
		model.addAttribute("officeName", annualIncrementService.officeName(messages.getUserName()));
		
		model.addAttribute("lstEmp", annualIncrementService.lstEmpforMTR21(orderNo,incrementOrderDate,messages.getUserName()));
		model.addAttribute("menuList", menuList);
		model.addAttribute("subMenuList", subMenuList);
		
		addMenuAndSubMenu(model,messages);	
		return "/views/report/MTR21Report";
	}

/*	@PostMapping("/approveAnnualIncrement")
	public String approveAnnualIncrement(
			@ModelAttribute("annualIncrementModel") @Valid AnnualIncrementModel annualIncrementModel,
			BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpSession session) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		ModelAndView modelAndView = new ModelAndView();
		int saveId = annualIncrementService.approveAnnualIncrement(annualIncrementModel, messages);

		if (saveId != 0) {
			redirectAttributes.addFlashAttribute("message", "SUCCESS");
		}
		return "redirect:/ddoast/approvalAnnualIncrement"; /// redirects to controller URL
	}*/

	@GetMapping(value = "/checkOrderNoAlreadyExists/{certificateNumber}")
	public ResponseEntity<String> PaybillValidation(@PathVariable String certificateNumber, HttpSession session) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		List<Object[]> orderNo = annualIncrementService.findOrderNoAlreadyExists(certificateNumber,
				messages.getUserName());
		Integer existingData = orderNo.size();
		String resJson = existingData.toString();
		return ResponseEntity.ok(resJson);
	}

	@GetMapping("/annualIncrementStatus")
	public String annualIncrementStatus(
			@ModelAttribute("annualIncrementModel") @Valid AnnualIncrementModel annualIncrementModel, Model model,
			Locale locale, HttpSession session) {
		String message = (String) model.asMap().get("message");
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		List<TopicModel> menuList = new ArrayList<>();
		List<TopicModel> subMenuList = new ArrayList<>();
		model.addAttribute("menuList", menuList);
		model.addAttribute("subMenuList", subMenuList);
		model.addAttribute("language", locale.getLanguage());
		if (message != null && message.equals("SUCCESS")) {
			if (locale != null && locale.getLanguage().equalsIgnoreCase("en")) {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_ENGLSH, STATUS.SUCCESS, model);
			} else {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_MARATHI, STATUS.SUCCESS, model);
			}
		}
		model.addAttribute("lstAnnualIncrementStatus", annualIncrementService.getEmpStatus(messages.getUserName()));
		model.addAttribute("language", locale.getLanguage());
		
		addMenuAndSubMenu(model,messages);	
		return "/views/annual-incrementStatus";
	}

	public Date subtractDate(Date date, int subtractDay) throws ParseException {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, -subtractDay);
		date = c.getTime();
		return date;
	}
}
