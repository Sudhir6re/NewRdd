package com.mahait.gov.in.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mahait.gov.in.entity.AllowanceDeductionMstEntity;
import com.mahait.gov.in.entity.DeptEligibilityForAllowAndDeductEntity;
import com.mahait.gov.in.entity.MstPayCommissionEntity;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.DDOScreenModel;
import com.mahait.gov.in.model.TopicModel;
import com.mahait.gov.in.service.AllowanceDeductionWiseMstService;
import com.mahait.gov.in.service.CommonHomeMethodsService;
import com.mahait.gov.in.service.MstDesignationService;
import com.mahait.gov.in.service.UserService;
import com.mahait.gov.in.service.WelcomeService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/mdc")
public class AllowanceDeductionWiseMasterController  extends BaseController{

//	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserService userDetailsServiceImpl;

	@Autowired
	private CommonHomeMethodsService commonHomeMethodsService;

	@Autowired
	MstDesignationService mstDesignationService;

	@Autowired
	AllowanceDeductionWiseMstService allowanceDeductionWiseMstService;

	@Autowired
	WelcomeService welcomeService;

	@RequestMapping("/AllowanceDeductionWiseMaster")
	public ModelAndView AllowanceDeductionWiseMaster(HttpServletRequest request, Model model, HttpServletResponse response,
			Locale locale, HttpSession session,
			@ModelAttribute("allowanceDeductionMstEntity") AllowanceDeductionMstEntity allowanceDeductionMstEntity) {
		String message = (String)model.asMap().get("message");
		
		ModelAndView modelAndView = new ModelAndView();
		
		
		modelAndView.addObject("context", request.getContextPath());
		
	//	logger.info("context="+request.getContextPath());
		modelAndView.addObject("language", locale.getLanguage());
		request.getSession().setAttribute("MY_SESSION_MESSAGES",
				userDetailsServiceImpl.getUserIdbyUserName(request.getRemoteUser()));
//		UserInfo messages = (UserInfo) session.getAttribute("MY_SESSION_MESSAGES");
//		modelAndView.addObject("sessionMessages", messages.getUser_id());
//		modelAndView.addObject("userName", messages.getFullName());
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		session.setAttribute("roleId", messages.getMstRoleEntity().getRoleId());
		modelAndView.addObject("sessionMessages", messages.getMstRoleEntity().getRoleId());
		// logger.info(""+messages.getFullName());
		modelAndView.addObject("userName", messages.getUserName());
		int levelRoleVal = messages.getMstRoleEntity().getRoleId();

		session.setAttribute("levelRoleVal", messages.getMstRoleEntity().getRoleId());
//		int levelRoleVal = messages.getRole_id();
		List<TopicModel> menuList = new ArrayList<>();
		List<TopicModel> subMenuList = new ArrayList<>();
		menuList = commonHomeMethodsService.findMenuNameByRoleID(levelRoleVal, locale.getLanguage());
		subMenuList = commonHomeMethodsService.findSubMenuByRoleID(levelRoleVal, locale.getLanguage());
	//	List<MstPayCommissionEntity> lstddcPayCommission = mstDesignationService.findAllPayCommission();
		model.addAttribute("lstAllowanceDeductionMstEntity", allowanceDeductionWiseMstService.getAllallowanceDeductionWiseMst());
		List<MstPayCommissionEntity> lstddcPayCommission = mstDesignationService.findAllPayCommission();
		model.addAttribute("lstAllowanceDeductionMstEntity", allowanceDeductionWiseMstService.getAllallowanceDeductionWiseMst());
		model.addAttribute("lstddcPayCommission", lstddcPayCommission);
		modelAndView.addObject("menuList", menuList);
		modelAndView.addObject("subMenuList", subMenuList);
		modelAndView.addObject("message", message);
		modelAndView.addObject("allowanceDeductionMstEntity", allowanceDeductionMstEntity);
		
		
		addMenuAndSubMenu(modelAndView,messages);
		modelAndView.setViewName("/views/allowance-deduction-mst");
		return modelAndView;
	}

	@RequestMapping(value = "/AllowanceDeductionWiseMaster/{isType}", consumes = {
			"application/json" }, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DeptEligibilityForAllowAndDeductEntity>> fetchLstDeptEligibilityForAllowAndDeductEntityByType(
			@PathVariable int isType) {
		return ResponseEntity
				.ok(allowanceDeductionWiseMstService.fetchLstDeptEligibilityForAllowAndDeductEntityByType(isType));
	}
	
	@RequestMapping(value = "/isAllowanceDeductionWiseMasterDataPresent/{payCommision}/{startDate}/{endDate}", consumes = {
	"application/json" }, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> isAllowanceDeductionWiseMasterDataPresent(
			@PathVariable int payCommision,@PathVariable String startDate,@PathVariable String endDate) throws ParseException {
		AllowanceDeductionMstEntity allowanceDeductionMstEntity=new AllowanceDeductionMstEntity();
		
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
		/*allowanceDeductionMstEntity.setIsType(isType);
		allowanceDeductionMstEntity.setDepartmentAllowdeducCode(departmentAllowdeducCode);*/
		allowanceDeductionMstEntity.setStartDate(sdf.parse(startDate));
		allowanceDeductionMstEntity.setEndDate(sdf.parse(endDate));
		allowanceDeductionMstEntity.setPayCommissionCode(payCommision);
		return ResponseEntity.ok(allowanceDeductionWiseMstService.isAllowanceDeductionWiseMasterDataPresent(allowanceDeductionMstEntity));
	}
	
	

	@RequestMapping("/saveAllowanceDeductionWiseMaster")
	public ModelAndView saveAllowanceDeductionWiseMaster(HttpServletRequest request, Model model,
			HttpServletResponse response, Locale locale, HttpSession session,
			@ModelAttribute("allowanceDeductionMstEntity") AllowanceDeductionMstEntity allowanceDeductionMstEntity,RedirectAttributes redirectAttributes) throws ParseException {
		ModelAndView modelAndView=new ModelAndView();
		request.getSession().setAttribute("MY_SESSION_MESSAGES",
				userDetailsServiceImpl.getUserIdbyUserName(request.getRemoteUser()));
//		UserInfo messages = (UserInfo) session.getAttribute("MY_SESSION_MESSAGES");
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		allowanceDeductionMstEntity.setCreatedUserId( messages.getMstRoleEntity().getRoleId());
		int checkIsDataAlreadyPresent=allowanceDeductionWiseMstService.checkComponentAlreadyPresent(allowanceDeductionMstEntity);
		int isSave=0;
		
			allowanceDeductionMstEntity.setEndDate(null);
			 isSave=allowanceDeductionWiseMstService.saveAllowanceDeductionWiseMaster(allowanceDeductionMstEntity);
		if(isSave>0) {
			redirectAttributes.addFlashAttribute("message", "1");
		}else {
			redirectAttributes.addFlashAttribute("message", "0");
		}
		return new ModelAndView("redirect:/mdc/AllowanceDeductionWiseMaster");
	}
	
	@RequestMapping("/updateAllowanceDeductionWiseMaster")
	public ModelAndView updateAllowanceDeductionWiseMaster(HttpServletRequest request, Model model,
			HttpServletResponse response, Locale locale, HttpSession session,
			@ModelAttribute("allowanceDeductionMstEntity") AllowanceDeductionMstEntity allowanceDeductionMstEntity,RedirectAttributes redirectAttributes) throws ParseException {
		ModelAndView modelAndView=new ModelAndView();
		request.getSession().setAttribute("MY_SESSION_MESSAGES",
				userDetailsServiceImpl.getUserIdbyUserName(request.getRemoteUser()));
//		UserInfo messages = (UserInfo) session.getAttribute("MY_SESSION_MESSAGES");
		
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		session.setAttribute("roleId", messages.getMstRoleEntity().getRoleId());
		modelAndView.addObject("sessionMessages", messages.getMstRoleEntity().getRoleId());
		// logger.info(""+messages.getFullName());
		modelAndView.addObject("userName", messages.getUserName());

		session.setAttribute("levelRoleVal", messages.getMstRoleEntity().getRoleId());
		allowanceDeductionMstEntity.setCreatedUserId(messages.getMstRoleEntity().getRoleId());
		int isSave=0;
//		int checkIsDataAlreadyPresent=allowanceDeductionWiseMstService.checkComponentAlreadyPresent(allowanceDeductionMstEntity);
//		int isSave=0;
//		if(checkIsDataAlreadyPresent!=0) {
			AllowanceDeductionMstEntity AllowanceDeductionMstEntity1=allowanceDeductionWiseMstService.findAllowanceDeductionWiseMasterById(allowanceDeductionMstEntity.getAllowanceDeductionWiseId());
			if(allowanceDeductionMstEntity.getEndDate()!=null) {
				AllowanceDeductionMstEntity1.setEndDate(allowanceDeductionMstEntity.getEndDate());
				AllowanceDeductionMstEntity1.setStartDate(allowanceDeductionMstEntity.getStartDate());
				AllowanceDeductionMstEntity1.setPercentage(allowanceDeductionMstEntity.getPercentage());
			}else {
				///AllowanceDeductionMstEntity1.setEndDate(subtractDate(allowanceDeductionMstEntity.getStartDate(),1));
				AllowanceDeductionMstEntity1.setStartDate(allowanceDeductionMstEntity.getStartDate());
				AllowanceDeductionMstEntity1.setPercentage(allowanceDeductionMstEntity.getPercentage());
				AllowanceDeductionMstEntity1.setEndDate(allowanceDeductionMstEntity.getEndDate());
			}
			allowanceDeductionWiseMstService.updateAllowanceDeductionMstEntity(AllowanceDeductionMstEntity1);
		//}
		if(isSave>0) {
			redirectAttributes.addFlashAttribute("message", "1");
		}else {
			redirectAttributes.addFlashAttribute("message", "0");
		}
		return new ModelAndView("redirect:/mdc/AllowanceDeductionWiseMaster");
	}
	
	
	
	@RequestMapping("/editAllowanceDeductionWiseMaster/{id}")
	public ModelAndView editAllowanceDeductionWiseMaster(HttpServletRequest request, Model model, HttpServletResponse response,
			Locale locale, HttpSession session,@ModelAttribute("allowanceDeductionMstEntity") AllowanceDeductionMstEntity allowanceDeductionMstEntity,@PathVariable int id) {
		String message = (String)model.asMap().get("message");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("language", locale.getLanguage());
		request.getSession().setAttribute("MY_SESSION_MESSAGES",
				userDetailsServiceImpl.getUserIdbyUserName(request.getRemoteUser()));
//		UserInfo messages = (UserInfo) session.getAttribute("MY_SESSION_MESSAGES");
		
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		session.setAttribute("roleId", messages.getMstRoleEntity().getRoleId());
		modelAndView.addObject("sessionMessages", messages.getMstRoleEntity().getRoleId());
		// logger.info(""+messages.getFullName());
		modelAndView.addObject("userName", messages.getUserName());
		int levelRoleVal = messages.getMstRoleEntity().getRoleId();

		session.setAttribute("levelRoleVal", messages.getMstRoleEntity().getRoleId());
		
//		modelAndView.addObject("sessionMessages", messages.getUser_id());
//		modelAndView.addObject("userName", messages.getFullName());
		modelAndView.addObject("context", request.getContextPath());
//		int levelRoleVal = messages.getRole_id();
		List<TopicModel> menuList = new ArrayList<>();
		List<TopicModel> subMenuList = new ArrayList<>();
		menuList = commonHomeMethodsService.findMenuNameByRoleID(levelRoleVal, locale.getLanguage());
		subMenuList = commonHomeMethodsService.findSubMenuByRoleID(levelRoleVal, locale.getLanguage());
		model.addAttribute("lstAllowanceDeductionMstEntity", allowanceDeductionWiseMstService.getAllallowanceDeductionWiseMst());
	//	model.addAttribute("lstddcPayCommission", lstddcPayCommission);
		///List<MstPayCommissionEntity> lstddcPayCommission = mstDesignationService.findAllPayCommission();
		model.addAttribute("lstAllowanceDeductionMstEntity", allowanceDeductionWiseMstService.getAllallowanceDeductionWiseMst());
	///	model.addAttribute("lstddcPayCommission", lstddcPayCommission);
		modelAndView.addObject("menuList", menuList);
		modelAndView.addObject("subMenuList", subMenuList);
		modelAndView.addObject("message", message);
		allowanceDeductionMstEntity=allowanceDeductionWiseMstService.findAllowanceDeductionWiseMasterById(id);
//		List<DeptEligibilityForAllowAndDeductEntity> lstAllowancedeductName=allowanceDeductionWiseMstService.fetchLstDeptEligibilityForAllowAndDeductEntityByType(allowanceDeductionMstEntity.getIsType());
//		modelAndView.addObject("lstAllowancedeductName", lstAllowancedeductName);
		modelAndView.addObject("allowanceDeductionMstEntity", allowanceDeductionMstEntity);
		
		addMenuAndSubMenu(modelAndView,messages);
		modelAndView.setViewName("/views/edit-allowance-deduction-mst");
		return modelAndView;
	}
	@RequestMapping("/deleteAllowanceDeductionWiseMaster/{id}/{status}")
	public ModelAndView deleteAllowanceDeductionWiseMaster(@PathVariable int id,@PathVariable char status,Model model,Locale locale) {
		AllowanceDeductionMstEntity allowanceDeductionMstEntity=allowanceDeductionWiseMstService.deleteAllowanceDeductionWiseMasterById(id,status);
		if (allowanceDeductionMstEntity != null) {
			model.addAttribute("ddoScreenModel", new DDOScreenModel());
			model.addAttribute("language", locale.getLanguage());
		}
		return new ModelAndView("redirect:/mdc/AllowanceDeductionWiseMaster");
	}
	
	
	 public  Date subtractDate(Date date,int subtractDay) throws ParseException {
	        Calendar c = Calendar.getInstance();
	        c.setTime(date);
	        c.add(Calendar.DATE, -subtractDay);
	        date = c.getTime();
	        return date;
	    }
	 
	 @RequestMapping("/validateAllowDeduct/{allowdeductName}/{isType}") //validateAllowDeduct/"+allowdeductName+"/"+allowdeductCode
		public @ResponseBody List<Long> validateAllowDeduct(@PathVariable String allowdeductName,@PathVariable Integer isType, Model model, Locale locale) {

			List<Long> status = allowanceDeductionWiseMstService.validateAllowDeduct(allowdeductName,isType);
			return status;
		}

}
