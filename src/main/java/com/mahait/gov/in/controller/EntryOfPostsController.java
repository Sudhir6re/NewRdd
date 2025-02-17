package com.mahait.gov.in.controller;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.entity.DdoOffice;
import com.mahait.gov.in.entity.HrPayOrderMst;
import com.mahait.gov.in.entity.MstDcpsBillGroup;
import com.mahait.gov.in.entity.MstDesignationEntity;
import com.mahait.gov.in.entity.OrgDdoMst;
import com.mahait.gov.in.entity.OrgPostDetailsRlt;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.PostEntryModel;
import com.mahait.gov.in.repository.OrgDdoMstRepository;
import com.mahait.gov.in.repository.OrgPostDetailsRltRepository;
import com.mahait.gov.in.response.MessageResponse;
import com.mahait.gov.in.service.EntryOfPostsService;
import com.mahait.gov.in.service.LocationMasterService;
import com.mahait.gov.in.service.MstSchemeService;

import jakarta.servlet.http.HttpSession;

@RequestMapping(value={"/ddo","/mdp"})
@Controller
public class EntryOfPostsController extends BaseController {

	@Autowired
	OrgPostDetailsRltRepository orgPostDetailsRltRepository;

	@Autowired
	OrgDdoMstRepository orgDdoMstRepository;

	@Autowired
	EntryOfPostsService entryOfPostsService;

	@Autowired
	MstSchemeService mstSchemeService;
	
	
	@Autowired
	LocationMasterService locationMasterService;

	@GetMapping("/entryOfPosts")
	public String entryOfPosts(Model model, Locale locale, HttpSession session,@ModelAttribute("postEntryModel") PostEntryModel postEntryModel) {

		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");

		long langId = 0l;
		long locId = 0l;
		BigInteger loggedInPostId = null;
		OrgDdoMst ddoMst = null;
		String ddoCode = null;
		List locationList = null;

		MessageResponse messageResponse = (MessageResponse) model.asMap().get("messageResponse");
		if (messageResponse != null) {
			// model.addAttribute("messageResponse", messageResponse);
			model.addAttribute("message", messageResponse.getResponse());
		}
		
		model.addAttribute("lstDistrict", commonHomeMethodsService.lstGetAllDistrict());
		model.addAttribute("postEntryModel",postEntryModel);

		/*
		 * if (session.getAttribute("locationId") != null) { langId = 1l; locId =
		 * Long.parseLong((String) session.getAttribute("locationId")); //loggedInPostId
		 * = (BigInteger) session.getAttribute("loggedInPost");
		 * 
		 * Long loggedInPost = (Long) session.getAttribute("loggedInPost");
		 * loggedInPostId = BigInteger.valueOf(loggedInPost);
		 * 
		 * List<OrgDdoMst> ddoCodeList =
		 * entryOfPostsService.getDDOCodeByLoggedInlocId(locId);
		 * 
		 * model.addAttribute("filterDdoCodes", ddoCodeList);
		 * 
		 * Long lLngFieldDept = Long.parseLong(ddoCodeList.get(0).getHodLocCode());
		 * List<MstDesignationEntity> desgList =
		 * entryOfPostsService.getActiveDesig(lLngFieldDept);
		 * 
		 * model.addAttribute("Designation", desgList);
		 * 
		 * List billList = entryOfPostsService.getAllBillsFromLocation(locId);
		 * model.addAttribute("billList", billList);
		 * 
		 * if (ddoCodeList.size() > 0) ddoCode = ddoCodeList.get(0).getDdoCode();
		 * 
		 * List filterDdoCode = entryOfPostsService.findLevel1DddoByDdoCode(ddoCode);
		 * 
		 * model.addAttribute("ddoCode", ddoCode); model.addAttribute("filterDdoCode",
		 * filterDdoCode);
		 * 
		 * String lPostName = ""; String srNo = ""; String PsrNo = ""; String BillNo =
		 * ""; String Dsgn = ""; String ddoCode1 = ""; List getPostNameForDisplay =
		 * entryOfPostsService.getPostNameForDisplay(ddoCode);
		 * 
		 * model.addAttribute("getPostNameForDisplay", getPostNameForDisplay); }
		 */

		addMenuAndSubMenu(model, messages);
		return "/views/entry-of-posts";
	}

	@GetMapping("/addPosts")
	public String addPosts(Model model, Locale locale, HttpSession session,
			@ModelAttribute("postEntryModel") PostEntryModel postEntryModel) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");

		long langId = 0l;
		long locId = 0l;
		BigInteger loggedInPostId = null;
		OrgDdoMst ddoMst = null;
		String ddoCode = null;
		List locationList = null;
		model.addAttribute("lstDistrict", commonHomeMethodsService.lstGetAllDistrict());
		
		List<MstDesignationEntity> desgList = entryOfPostsService.findAllDesignation();

		model.addAttribute("Designation", desgList);
		
		/*
		 * if (session.getAttribute("locationId") != null) {
		 * 
		 * langId = 1l; locId = Long.parseLong((String)
		 * session.getAttribute("locationId")); loggedInPostId =
		 * StringHelperUtils.isNullBigInteger(session.getAttribute("loggedInPost"));
		 * 
		 * model.addAttribute("ddoCode", ddoCode);
		 * 
		 * String talukaId = ""; String ddoSelected = ""; List DDOdtls =
		 * entryOfPostsService.getSubDDOsOffc(loggedInPostId, talukaId, ddoSelected);
		 * List<OrgDdoMst> ddoCodeList =
		 * entryOfPostsService.getDDOCodeByLoggedInlocId(locId);
		 * model.addAttribute("lstDistrict",
		 * commonHomeMethodsService.lstGetAllDistrict()); if (ddoCodeList.size() > 0)
		 * ddoCode = ddoCodeList.get(0).getDdoCode();
		 * 
		 * // model.addAttribute("DDOlist", DDOdtls);
		 * 
		 * List filterDdoCode = entryOfPostsService.findLevel1DddoByDdoCode(ddoCode);
		 * model.addAttribute("DDOlist", filterDdoCode);
		 * 
		 * List branchList_en = entryOfPostsService.getAllBranchList(1L);
		 * model.addAttribute("Branch", branchList_en);
		 * 
		 * List<HrPayOrderMst> orderList = entryOfPostsService.getAllOrderData(locId,
		 * ddoCode);
		 * 
		 * List billList = entryOfPostsService.getAllBillsFromLocation(locId); List
		 * officeList = entryOfPostsService.getAllOfficesFromDDO(ddoCode);
		 * 
		 * 
		 * // code to find the district String districtID =
		 * entryOfPostsService.districtName(ddoCode); // code to find the taluka List
		 * talukaList = entryOfPostsService.allTaluka(districtID);
		 * 
		 * // List<OrgDdoMst> ddoCodeList = //
		 * entryOfPostsService.getDDOCodeByLoggedInlocId(locId);
		 * 
		 * Long lLngFieldDept = Long.parseLong(ddoCodeList.get(0).getHodLocCode());
		 * 
		 * 
		 * 
		 * List<Object[]> subList = entryOfPostsService.getSubjectList();
		 * model.addAttribute("SubjectList", subList); //
		 * model.addAttribute("subOfficeList", subOfficeList);
		 * model.addAttribute("orderList", orderList); model.addAttribute("billList",
		 * billList); model.addAttribute("langId", Long.valueOf(langId));
		 * model.addAttribute("officeList", officeList); model.addAttribute("flag",
		 * "add"); model.addAttribute("talukaList", talukaList);
		 * model.addAttribute("talukaId", talukaId); //
		 * model.addAttribute("ddoSelected", ddoSelected); }
		 */

		addMenuAndSubMenu(model, messages);
		model.addAttribute("postEntryModel", postEntryModel);

		return "/views/add-posts";
	}

	@RequestMapping("/updatePosts")
	public String updatePosts(Model model, Locale locale, HttpSession session,
			@ModelAttribute("postEntryModel") PostEntryModel postEntryModel) {

		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");

		long langId = 0l;
		long locId = 0l;
		BigInteger loggedInPostId = null;
		OrgDdoMst ddoMst = null;
		String ddoCode = null;
		List locationList = null;

		if (session.getAttribute("locationId") != null) {
			langId = 1l;
			locId = Long.parseLong((String) session.getAttribute("locationId"));
			loggedInPostId = (BigInteger) session.getAttribute("loggedInPost");

			model.addAttribute("ddoCode", ddoCode);

			String talukaId = "";
			String ddoSelected = "";
			List DDOdtls = entryOfPostsService.getSubDDOsOffc(loggedInPostId, talukaId, ddoSelected);
			List<OrgDdoMst> ddoCodeList = entryOfPostsService.getDDOCodeByLoggedInlocId(locId);

			if (ddoCodeList.size() > 0)
				ddoCode = ddoCodeList.get(0).getDdoCode();

			model.addAttribute("DDOlist", DDOdtls);
		}

		if (postEntryModel.getAction() != null && postEntryModel.getAction().equals("search")) {
			List<OrgPostDetailsRlt> lst = entryOfPostsService.searchPostListByGrOrderId(locId,
					postEntryModel.getOldGrOrderId());
			model.addAttribute("attachedPostList", lst);
			model.addAttribute("style", "display:block;");
			HrPayOrderMst hrPayOrderMst = entryOfPostsService.findOrderMasterById(postEntryModel.getOldGrOrderId());
			postEntryModel.setCurrentOrder(hrPayOrderMst.getOrderName());
			postEntryModel.setCurrentOrderDate(hrPayOrderMst.getOrderDate());

		} else {
			model.addAttribute("attachedPostList", null);
			model.addAttribute("style", "display:none;");
		}

		Calendar cal = Calendar.getInstance();
		Date today = cal.getTime();
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		String TodaysDate = fmt.format(today);

		List<HrPayOrderMst> orderList = entryOfPostsService.getAllOrderDataByDate(locId, TodaysDate, ddoCode);
		model.addAttribute("orderList", orderList);

		List postExpiryList = entryOfPostsService.getExpiryData(locId, ddoCode);
		model.addAttribute("postExpiryList", postExpiryList);
		model.addAttribute("postEntryModel", postEntryModel);

		addMenuAndSubMenu(model, messages);
		return "/views/update-posts";
	}

	@PostMapping("/savePostEntry")
	public String savePostEntry(Model model, Locale locale, HttpSession session,
			@ModelAttribute("postEntryModel") PostEntryModel postEntryModel, RedirectAttributes redirectAttribute) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");

		long locId = 0l;
		BigInteger loggedInPostId = null;
		if (session.getAttribute("locationId") != null) {
			locId = Long.parseLong((String) session.getAttribute("locationId"));
			loggedInPostId = BigInteger.valueOf(Long.valueOf(session.getAttribute("loggedInPost").toString()));
			entryOfPostsService.savePostEntryDtl(postEntryModel, locId, loggedInPostId, messages);
			MessageResponse messageResponse = new MessageResponse();
			messageResponse.setResponse("Post Created Successfully");
			messageResponse.setStyle("alert alert-success");
			messageResponse.setStatusCode(200);
			redirectAttribute.addFlashAttribute("messageResponse", messageResponse);
			postEntryModel = new PostEntryModel();
			return "redirect:/mdp/entryOfPosts";

		} else {
			return "redirect:/user/login";
		}

	}

	@PostMapping("/renewPostEntry")
	public String renewPostEntry(Model model, Locale locale, HttpSession session,
			@ModelAttribute("postEntryModel") PostEntryModel postEntryModel, RedirectAttributes redirectAttribute) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		long locId = 0l;
		BigInteger loggedInPostId = null;
		if (session.getAttribute("locationId") != null) {
			locId = Long.parseLong((String) session.getAttribute("locationId"));
			loggedInPostId = (BigInteger) session.getAttribute("loggedInPost");
			entryOfPostsService.renewPostEntry(postEntryModel, locId, loggedInPostId, messages);
			MessageResponse messageResponse = new MessageResponse();
			messageResponse.setResponse("Post Renewed Successfully");
			messageResponse.setStyle("alert alert-success");
			messageResponse.setStatusCode(200);
			redirectAttribute.addFlashAttribute("messageResponse", messageResponse);
			return "redirect:/ddo/updatePosts";

		} else {
			return "redirect:/user/login";
		}

	}

	@RequestMapping(value = "/findGrOrderByGrOrderId/{grOrderId}", consumes = {
			"application/json" }, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<HrPayOrderMst>> findGrOrderByGrOrderId(@PathVariable Long grOrderId) {
		List<HrPayOrderMst> response1 = entryOfPostsService.findGrOrderDetails(grOrderId);
		return ResponseEntity.ok(response1);
	}

	@RequestMapping(value = "/findGrOrderByGrOrderByDdoCode/{ddoCode}", consumes = {
			"application/json" }, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<HrPayOrderMst>> findGrOrderByGrOrderByDdoCode(@PathVariable String ddoCode) {
		Calendar cal = Calendar.getInstance();
		Date today = cal.getTime();
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		String TodaysDate = fmt.format(today);
		Long locId = 1l;
		List<HrPayOrderMst> orderList = entryOfPostsService.getAllOrderDataByDate(locId, TodaysDate, ddoCode);
		return ResponseEntity.ok(orderList);
	}

	@RequestMapping(value = "/searchPostDetails", consumes = {
			"application/json" }, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List> searchPostDetails(@RequestParam String ddoCode,HttpSession session) {
		List getPostNameForDisplay = entryOfPostsService.getPostNameForDisplay(ddoCode);
		return ResponseEntity.ok(getPostNameForDisplay);
	}

	@RequestMapping(value = "/searchPostDetails/{orderId}", consumes = {
			"application/json" }, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List> searchPostListByGrOrderId(@RequestParam Long orderId, HttpSession session) {
		Long locId = Long.parseLong((String) session.getAttribute("locationId"));
		String PsrNo = "";
		List getPostNameForDisplay = entryOfPostsService.searchPostListByGrOrderId(locId, orderId);
		return ResponseEntity.ok(getPostNameForDisplay);
	}

	@RequestMapping(value = "/fetchBillGroupByDdoCode/{ddoCode}", consumes = {
			"application/json" }, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MstDcpsBillGroup>> fetchBillGroupByDdoCode(@PathVariable String ddoCode,
			HttpSession session) {
		Long locId = Long.parseLong((String) session.getAttribute("locationId"));
		String PsrNo = "";
		List<MstDcpsBillGroup> lstMstDcpsBillGroup = mstSchemeService.findAllMpgSchemeBillGroupByDDOCode(ddoCode, 0);
		return ResponseEntity.ok(lstMstDcpsBillGroup);
	}
	
	@RequestMapping(value = "/findLevelDdoCodeByDistrict/{districtId}", consumes = {
	"application/json" }, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Object[]>> findLevelDdoCodeByDistrict(@PathVariable String districtId,
			HttpSession session) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		List<Object[]> lstLevel1Ddo = entryOfPostsService.findLevelDdoCodeByDistrict(districtId, messages);
		return ResponseEntity.ok(lstLevel1Ddo);
	}
	@RequestMapping(value = "/getAllOfficesFromDDO/{ddoCode}", consumes = {
	"application/json" }, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DdoOffice>> getAllOfficesFromDDO(@PathVariable String ddoCode,HttpSession session) {
		List<DdoOffice> lstLevel1Ddo = entryOfPostsService.getAllOfficesFromDDO(ddoCode);
		return ResponseEntity.ok(lstLevel1Ddo);
	}
	
	@RequestMapping(value = "/getDesignationLstByDdoCode/{ddoCode}", consumes = {
	"application/json" }, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MstDesignationEntity>> getDesignationLstByDdoCode(@PathVariable String ddoCode,HttpSession session) {
		List<MstDesignationEntity> lstMstDesignationEntity = entryOfPostsService.getDesignationLstByDdoCode(ddoCode);
		return ResponseEntity.ok(lstMstDesignationEntity);
	}
	
	
	
	
	

}
