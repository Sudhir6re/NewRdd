package com.mahait.gov.in.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.mahait.gov.in.common.CommonConstants;
import com.mahait.gov.in.common.CommonConstants.STATUS;
import com.mahait.gov.in.common.CommonUtils;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.BrokenPeriodModel;
import com.mahait.gov.in.model.BrokenPeriodResponseModel;
import com.mahait.gov.in.service.BrokenPeriodService;
import com.mahait.gov.in.service.CommonHomeMethodsService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/ddoast")
@Controller
public class BrokenPeriodController  extends BaseController {
	
	/*@GetMapping("brokenPeriod")
	public String brokenPeriod(Model model, Locale locale, HttpSession session) {
		return "/views/broken-period";
	}*/
	

	@Autowired
	CommonHomeMethodsService commonHomeMethodsService;

	@Autowired
	private BrokenPeriodService brokenPeriodService;

	// protected final Log logger = LogFactory.getLog(getClass());
	// private static final Logger logger =
	// LogManager.getLogger(BrokenPeriodController.class);

	@RequestMapping(value = "/brokenPeriod", method = { RequestMethod.GET, RequestMethod.POST })
	public String brokenPeriod(@ModelAttribute("brokenPeriodModel") BrokenPeriodModel brokenPeriodModel, Model model,
			Locale locale, HttpSession session) {

		String message = (String) model.asMap().get("message");

		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		// brokenPeriodModel.setDdoCode(BigInteger.valueOf(Long.valueOf(messages.getUserName())));
		if (brokenPeriodModel.getAction() != null) {
			if (brokenPeriodModel.getAction().equals("SEARCH_EMP")) {
				brokenPeriodModel.setDdocode(messages.getDdoCode());
				List<BrokenPeriodResponseModel> brokenPeriodResponseModel = new ArrayList<BrokenPeriodResponseModel>();

				brokenPeriodResponseModel = brokenPeriodService.brokenPeriod(brokenPeriodModel,
						brokenPeriodResponseModel);
				for (Iterator iterator = brokenPeriodResponseModel.iterator(); iterator.hasNext();) {
					BrokenPeriodResponseModel brokenPeriodResponseModel2 = (BrokenPeriodResponseModel) iterator.next();
					model.addAttribute(brokenPeriodResponseModel2.getStatus(), brokenPeriodResponseModel2.getData());

				}
				brokenPeriodModel.setMonth(brokenPeriodModel.getMonth());
				model.addAttribute("status", "1");
			}  
		} else { 
			List<String> lstResult = new ArrayList<String>();
			for (int i = 0; i < 6; i++) {
				lstResult.add("");
				model.addAttribute("EmpDetail", lstResult);

			}
			model.addAttribute("status", "0");
		}

		model.addAttribute("brokenPeriodModel", brokenPeriodModel);
	

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
		
		

		model.addAttribute("ddoCode", messages.getDdoCode());
		model.addAttribute("lstMonths", commonHomeMethodsService.lstGetAllMonths());
		model.addAttribute("lstYears", commonHomeMethodsService.lstGetAllYears());

		model.addAttribute("language", locale.getLanguage());
		addMenuAndSubMenu(model,messages);	

		// model=brokenPeriodService.brokenPeriod(mstEmployeeModel, model, sevaarthid);

		return "/views/brokenperiod/broken-period";
	}

	@RequestMapping("/getBrokenPeriodEmpDtls/{sevaarthid}")
	public @ResponseBody List<BrokenPeriodResponseModel> getBrokenPeriodEmpDtls(@PathVariable String sevaarthid,
			Model model, Locale locale) {

		List<BrokenPeriodResponseModel> brokenPeriodResponseModel = new ArrayList<BrokenPeriodResponseModel>();
		//
		// brokenPeriodResponseModel =
		// brokenPeriodService.brokenPeriod(sevaarthid,brokenPeriodResponseModel);
		return brokenPeriodResponseModel;
	}

	@RequestMapping("/calculateEmployeeSalary/{sevaarthid}/{empId}/{month}/{year}/{noOfDays}/{paybillMonth}/{paybillYear}/{fromDate}/{toDate}")
	public @ResponseBody List<BrokenPeriodResponseModel> calculateEmployeeSalary(@PathVariable String sevaarthid,
			@PathVariable String empId, @PathVariable String month, @PathVariable String year,
			@PathVariable String noOfDays, @PathVariable String paybillMonth, @PathVariable String paybillYear,
			Model model, Locale locale, HttpSession session,@PathVariable String fromDate, @PathVariable String toDate) {

		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");

		HashMap hmInputParam = new HashMap();
		hmInputParam.put("month", month);
		hmInputParam.put("year", year);
		hmInputParam.put("empId", empId);
		hmInputParam.put("paybillMonth", paybillMonth);
		hmInputParam.put("paybillYear", paybillYear);
		hmInputParam.put("noOfDays", noOfDays);
		hmInputParam.put("fromDate", fromDate);
		hmInputParam.put("toDate", toDate);
		hmInputParam.put("ddocode", messages.getDdoCode());

		List<BrokenPeriodResponseModel> brokenPeriodResponseModel = new ArrayList<BrokenPeriodResponseModel>();

		brokenPeriodResponseModel = brokenPeriodService.calculateEmployeeSalary(sevaarthid, brokenPeriodResponseModel,
				hmInputParam);
		// brokenPeriodResponseModel =
		// brokenPeriodService.brokenPeriod(sevaarthid,brokenPeriodResponseModel);
		return brokenPeriodResponseModel;
	}

	@RequestMapping("/saveBrokenPeriodPay/{sevaarthid}/{empId}/{fromDates}/{toDates}/{noOfDays}/{paybillMonth}/{paybillYear}/{bacispay}/{allowancesCodes}/{allowancesValues}/{deductionCodes}/{deductionValues}/{netPays}/{reasons}/{remarks}/{basicForCalculation}/{ddoCode}")
	public @ResponseBody List<BrokenPeriodResponseModel> saveBrokenPeriodPay(@PathVariable String sevaarthid,
			@PathVariable String empId, @PathVariable String fromDates, @PathVariable String toDates,
			@PathVariable String noOfDays, @PathVariable String paybillMonth, @PathVariable String paybillYear,
			@PathVariable String bacispay, @PathVariable String allowancesCodes, @PathVariable String allowancesValues,
			@PathVariable String deductionCodes, @PathVariable String deductionValues, @PathVariable String netPays,
			@PathVariable String reasons, @PathVariable String remarks,@PathVariable String basicForCalculation,@PathVariable String ddoCode, Model model, Locale locale) {

		/*
		 * logger.info("sevaarthid=" + sevaarthid + " || empId=" + empId +
		 * " || fromDates=" + fromDates + " || toDates=" + toDates + " || noOfDays=" +
		 * noOfDays + " || paybillMonth=" + paybillMonth + " || paybillYear=" +
		 * paybillYear + " || bacispay=" + bacispay+ " || allowancesCodes=" +
		 * allowancesCodes+ " || allowancesValues=" + allowancesValues +
		 * " || deductionCodes=" + deductionCodes+ " || deductionValues=" +
		 * deductionValues+ " || netPays=" + netPays+ " || reasons=" + reasons+
		 * " || remarks=" + remarks);
		 */

		HashMap<String, String> hmInputParam = new HashMap<String, String>();
		hmInputParam.put("sevaarthid", sevaarthid);
		hmInputParam.put("fromDates", fromDates);
		hmInputParam.put("toDates", toDates);
		hmInputParam.put("empId", empId);
		hmInputParam.put("paybillMonth", paybillMonth);
		hmInputParam.put("paybillYear", paybillYear);
		hmInputParam.put("noOfDays", noOfDays);
		hmInputParam.put("bacispay", bacispay);
		hmInputParam.put("allowancesCodes", allowancesCodes);
		hmInputParam.put("allowancesValues", allowancesValues);
		hmInputParam.put("deductionCodes", deductionCodes);
		hmInputParam.put("deductionValues", deductionValues);
		hmInputParam.put("netPays", netPays);
		hmInputParam.put("reasons", reasons);
		hmInputParam.put("remarks", remarks);
		hmInputParam.put("basicForCalculation", basicForCalculation);
		hmInputParam.put("ddoCode", ddoCode);

		List<BrokenPeriodResponseModel> brokenPeriodResponseModel = new ArrayList<BrokenPeriodResponseModel>();

		///brokenPeriodResponseModel = brokenPeriodService.saveBrokenPeriodDAArrayPay(sevaarthid, brokenPeriodResponseModel,hmInputParam);
	brokenPeriodResponseModel = brokenPeriodService.saveBrokenPeriodPay(sevaarthid, brokenPeriodResponseModel,hmInputParam);
		// brokenPeriodResponseModel =
		// brokenPeriodService.brokenPeriod(sevaarthid,brokenPeriodResponseModel);
		brokenPeriodResponseModel = null;
		return brokenPeriodResponseModel;
	}

	@RequestMapping("/isEmpMappedWithBillGroup/{searchName}")
	public @ResponseBody String isEmpMappedWithBillGroup(@PathVariable String searchName, Model model, Locale locale) {

		List<BrokenPeriodResponseModel> brokenPeriodResponseModel = new ArrayList<BrokenPeriodResponseModel>();
		//
		// brokenPeriodResponseModel =
		// brokenPeriodService.brokenPeriod(sevaarthid,brokenPeriodResponseModel);

		String flagEmpMapWithBillGrp = brokenPeriodService.isEmpMappedWithBillGroup(searchName);
		return flagEmpMapWithBillGrp;
	}

	// check is already bill is generated according to sevaarth id
	@GetMapping(value = "/getSevaarthIdMappedWithPaybill/{sevaarthid}/{monthName}/{yearName}")
	public @ResponseBody int getSevaarthIdMappedWithPaybill(@PathVariable String sevaarthid,
			@PathVariable int monthName, @PathVariable int yearName, HttpSession session) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		return brokenPeriodService.getSevaarthIdMappedWithBill(sevaarthid, monthName, yearName, messages.getUserName());
	}

	/*@RequestMapping("/calculateEmployeeSalaryBasedofBasic/{sevaarthid}/{empId}/{month}/{year}/{noOfDays}/{paybillMonth}/{paybillYear}/{basicForCalculation}/{fromDate}/{toDate}")
	public @ResponseBody List<BrokenPeriodResponseModel> calculateEmployeeSalaryBasedofBasic(
			@PathVariable String sevaarthid, @PathVariable String empId, @PathVariable String month,
			@PathVariable String year, @PathVariable String noOfDays, @PathVariable String paybillMonth,
			@PathVariable String paybillYear, @PathVariable String basicForCalculation, Model model, Locale locale,
			HttpSession session,@PathVariable String fromDate, @PathVariable String toDate) {

		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");

		HashMap<String, String> hmInputParam = new HashMap<String, String>();
		hmInputParam.put("month", month);
		hmInputParam.put("year", year);
		hmInputParam.put("empId", empId);
		hmInputParam.put("paybillMonth", paybillMonth);
		hmInputParam.put("paybillYear", paybillYear);
		hmInputParam.put("noOfDays", noOfDays);
		hmInputParam.put("fromDate", fromDate);
		hmInputParam.put("toDate", toDate);
		hmInputParam.put("basicForCalculation", basicForCalculation);
		hmInputParam.put("ddocode",messages.getDdoCode().trim());

		List<BrokenPeriodResponseModel> brokenPeriodResponseModel = new ArrayList<BrokenPeriodResponseModel>();

		brokenPeriodResponseModel = brokenPeriodService.calculateEmployeeSalaryBasedofBasic(sevaarthid,
				brokenPeriodResponseModel, hmInputParam);
		return brokenPeriodResponseModel;
	}*/

}
