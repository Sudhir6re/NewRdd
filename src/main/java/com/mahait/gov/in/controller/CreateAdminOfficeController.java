package com.mahait.gov.in.controller;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.JsonObject;
import com.mahait.gov.in.entity.CmnDistrictMst;
import com.mahait.gov.in.entity.CmnTalukaMst;
import com.mahait.gov.in.entity.MstDesignationEntity;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.entity.ZpAdminNameMst;
import com.mahait.gov.in.model.ZpRltDdoMapModel;
import com.mahait.gov.in.service.CreateAdminOfficeService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RequestMapping("/mdc")
@Controller
public class CreateAdminOfficeController extends BaseController {

	@Autowired
	CreateAdminOfficeService createAdminOfficeService;

	@GetMapping("/createAdminOffice")
	public String CreateAdminOffice(Model model, Locale locale, HttpSession session,
			@ModelAttribute("zpRltDdoMapModel") ZpRltDdoMapModel zpRltDdoMapModel) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		List<ZpAdminNameMst> lstZpAdminNameMst = createAdminOfficeService.fetchAllOfficeList(messages);
		List<CmnTalukaMst> lstCmnTalukaMst = createAdminOfficeService.findAllTalukaList(messages);
		List<CmnDistrictMst> lstCmnDistrctMst = createAdminOfficeService.findAllDistrictList(messages);

		String districtName = null;
		String talukaNametName = null;
		String adminType = null;

		List<Object[]> lstZpRltDdoMapRlt = createAdminOfficeService.findZpRltDtls(messages, districtName,
				talukaNametName, adminType);
		model.addAttribute("lstZpAdminNameMst", lstZpAdminNameMst);
		model.addAttribute("lstZpRltDdoMapRlt", lstZpRltDdoMapRlt);
		model.addAttribute("lstCmnTalukaMst", lstCmnTalukaMst);
		model.addAttribute("lstCmnDistrctMst", lstCmnDistrctMst);

		if (model.asMap().get("uniqueId") != null) {
			String uniqeInstituteId = (String) model.asMap().get("uniqueId");
			model.addAttribute("uniqeInstituteId", uniqeInstituteId);
		}

		if (model.asMap().get("ddoCode") != null) {
			String uniqueId = (String) model.asMap().get("ddoCode");
			model.addAttribute("ddoCode", uniqueId);
		}

		addMenuAndSubMenu(model, messages);

		return "/views/create-admin-office";
	}

	@GetMapping("/createAdminOfficeMaster")
	public String createAdminOfficeMaster(Model model, Locale locale, HttpSession session,
			@ModelAttribute("zpRltDdoMapModel") ZpRltDdoMapModel zpRltDdoMapModel) {
		zpRltDdoMapModel.setRadioFinalLevel("2");
		zpRltDdoMapModel.setRadioGender("M");
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		// List<ZpRltDdoMapModel> lstZpRltDdoMapModel =
		// createAdminOfficeService.findAllDdoMappedlist(messages);
		List<ZpAdminNameMst> lstZpAdminNameMst = createAdminOfficeService.fetchAllOfficeList(messages);

		List<CmnTalukaMst> lstCmnTalukaMst = createAdminOfficeService.findAllTalukaList(messages);
		List<CmnDistrictMst> lstCmnDistrctMst = createAdminOfficeService.findAllDistrictList(messages);

		List<Object[]> adminOfcLst = createAdminOfficeService.retriveDisctOfcList(messages, "");
		model.addAttribute("adminOfcLst", adminOfcLst);

		// model.addAttribute("lstZpRltDdoMapModel", lstZpRltDdoMapModel);

		String districtName = null;
		String talukaNametName = null;
		String adminType = null;

		List<Object[]> lstZpRltDdoMapRlt = createAdminOfficeService.findZpRltDtls(messages, districtName,
				talukaNametName, adminType);
		model.addAttribute("lstZpAdminNameMst", lstZpAdminNameMst);
		model.addAttribute("lstZpRltDdoMapRlt", lstZpRltDdoMapRlt);
		model.addAttribute("lstCmnTalukaMst", lstCmnTalukaMst);
		model.addAttribute("lstCmnDistrctMst", lstCmnDistrctMst);
		addMenuAndSubMenu(model, messages);
		return "/views/create-office";
	}

	@PostMapping("/saveCreateAdminOffice")
	public String saveCreateAdminOffice(Model model, Locale locale, HttpSession session,
			@ModelAttribute("zpRltDdoMapModel") @Valid ZpRltDdoMapModel zpRltDdoMapModel, BindingResult result,
			RedirectAttributes redirectAttribute) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		String uniqueId = createAdminOfficeService.saveCreateAdminOffice(zpRltDdoMapModel, messages);
		model.addAttribute("uniqueId", uniqueId);
		model.addAttribute("ddoCode", zpRltDdoMapModel.getTxtDDOCode());
		redirectAttribute.addFlashAttribute("uniqueId", uniqueId);
		redirectAttribute.addFlashAttribute("ddoCode", zpRltDdoMapModel.getTxtDDOCode());
		return "redirect:/mdc/createAdminOffice";
	}

	@RequestMapping(value = "/getAllTalukaByDistrictId/{districtId}", consumes = {
			"application/json" }, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CmnTalukaMst>> getAllTalukaByDistrictId(@PathVariable Long districtId) {
		return ResponseEntity.ok(createAdminOfficeService.getAllTalukaByDistrictId(districtId));
	}

	@RequestMapping(value = "/findZpRltDtls", consumes = {
			"application/json" }, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Object[]>> findZpRltDtls(@RequestBody Map<String, Object> requestData,
			HttpSession session) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");

		String districtId = null;
		String talukaId = null;
		String cmbAdminType = null;

		if (requestData.containsKey("districtId") && requestData.get("districtId") != null) {
			districtId = (String) requestData.get("districtId");
		}
		if (requestData.containsKey("stateId") && requestData.get("stateId") != null) {
			talukaId = (String) requestData.get("talukaId");
		}
		if (requestData.containsKey("cmbAdminType") && requestData.get("cmbAdminType") != null) {
			cmbAdminType = (String) requestData.get("cmbAdminType");
		}

		List<Object[]> lstZpRltDdoMapRlt = createAdminOfficeService.findZpRltDtls(messages, districtId, talukaId,
				cmbAdminType);

		return ResponseEntity.ok(lstZpRltDdoMapRlt);
	}

	@RequestMapping(value = "/fetchDistrictOfcByOffcId/{ofcId}", consumes = {
			"application/json" }, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Object[]>> fetchDistrictOfcByoffcId(@PathVariable String ofcId, HttpSession session) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");

		List<Object[]> lstZpRltDdoMapRlt = createAdminOfficeService.retriveDisctOfcList(messages, ofcId);

		return ResponseEntity.ok(lstZpRltDdoMapRlt);
	}

	@RequestMapping(value = "/fetchDdoDetails/{ddoCode}", consumes = {
			"application/json" }, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Object[]>> fetchDdoDetails(@PathVariable String ddoCode, HttpSession session) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");

		List<Object[]> lstZpRltDdoMapRlt = createAdminOfficeService.fetchDdoDetails(messages, ddoCode);

		return ResponseEntity.ok(lstZpRltDdoMapRlt);
	}

	@RequestMapping(value = "/getddoInfo", consumes = {
			"application/json" }, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> getddoInfo(@RequestParam String ddoCode) {
		Map<String, Object> response1 = createAdminOfficeService.findTrasuryDetails(ddoCode);
		return ResponseEntity.ok(response1);
	}

	@RequestMapping(value = "/generateDDOCode/{cmbSubTreasury}/{cmbAdminOffice}", consumes = {
			"application/json" }, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> generateDDOCode(@PathVariable String cmbSubTreasury,
			@PathVariable String cmbAdminOffice, HttpSession session) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		String ddoCode = createAdminOfficeService.generateDDOCode(cmbAdminOffice, cmbSubTreasury);
		JsonObject response = new JsonObject();
		response.addProperty("ddoCode", ddoCode);
		return ResponseEntity.ok(response.toString());
	}

	@RequestMapping(value = "/findDesignation", consumes = {
			"application/json" }, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MstDesignationEntity>> findDesignation(@RequestParam String txtDDODsgn) {
		List<MstDesignationEntity> response1 = createAdminOfficeService.findDesignation(txtDDODsgn);
		return ResponseEntity.ok(response1);
	}
	
	@RequestMapping(value = "/findDeptByDistOfcCode/{distOfcId}", consumes = {
	"application/json" }, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Object[]>> findDeptByDistOfcCode(@PathVariable String distOfcId) {
	//	OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		List<Object[]> response1 = createAdminOfficeService.findDeptByDistOfcCode(distOfcId);
		return ResponseEntity.ok(response1);
	}
	
	@RequestMapping(value = "/findLevel3DdoCode/{distOfcId}/{reptDdoCode}", consumes = {
	"application/json" }, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> findLevel3DdoCode(@PathVariable String distOfcId,@PathVariable String reptDdoCode) {
		String  response1 = createAdminOfficeService.findLevel3DdoCode(distOfcId,reptDdoCode);
		return ResponseEntity.ok(response1);
	}
	
	
	
	

}
