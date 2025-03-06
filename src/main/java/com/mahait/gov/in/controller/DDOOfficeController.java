package com.mahait.gov.in.controller;

import java.util.ArrayList;
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
import com.mahait.gov.in.entity.CmnDistrictMst;
import com.mahait.gov.in.entity.CmnLookupMst;
import com.mahait.gov.in.entity.CmnStateMst;
import com.mahait.gov.in.entity.CmnTalukaMst;
import com.mahait.gov.in.entity.DdoOffice;
import com.mahait.gov.in.entity.InstituteType;
import com.mahait.gov.in.entity.MstBankBranchEntity;
import com.mahait.gov.in.entity.MstBankEntity;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.MstDesnModel;
import com.mahait.gov.in.model.NewRegDDOModel;
import com.mahait.gov.in.service.CommonHomeMethodsService;
import com.mahait.gov.in.service.DDOInfoService;
import com.mahait.gov.in.service.OrganizationInstInfoService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/ddo")
public class DDOOfficeController extends BaseController {

	@Autowired
	CommonHomeMethodsService commonHomeMethodsService;

	@Autowired
	DDOInfoService ddoInfoService;

	@Autowired
	OrganizationInstInfoService organizationInstInfoService;

	List<NewRegDDOModel> emplist = new ArrayList<>();

	@GetMapping("/loadApproveDdoOffice")
	public String loadApproveDdoOffice(@ModelAttribute("newRegDDOModel") NewRegDDOModel newRegDDOModel, Model model,
			Locale locale, HttpSession session) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		String ddoCode = messages.getUserName();
		String lStrDdoOffice = "Yes";
		List<CmnStateMst> lstState = ddoInfoService.getStateLst(1L);
		List<CmnDistrictMst> lstDistrict = ddoInfoService.getDistrictlst(15L);
		List<MstDesnModel> lstdesgination = new ArrayList<>();
		List<MstBankEntity> bankName = new ArrayList<>();
		List<MstBankBranchEntity> lstBankBranch = new ArrayList<>();

		// List<CmnLookupMst>
		// dcpsOfficeClassId=commonHomeMethodsService.findCommonMstByLookupname(CommonConstants.COMMONMSTTABLE.DCPS_OFFICE_CLASS);
		// List<CmnLookupMst>ddoOffClass=ddoInfoService.findDDOOffClass(dcpsOfficeClassId.get(0).getLookupId());
		// model.addAttribute("ddoOffClass",
		// commonHomeMethodsService.findCommonMstByLookupname(CommonConstants.COMMONMSTTABLE.DCPS_OFFICE_CLASS));
		List<DdoOffice> lLstSavedOffices = ddoInfoService.getAllOffices(ddoCode);
		if (lLstSavedOffices != null) {
			if (lLstSavedOffices.get(0).getDcpsDdoOfficeDdoFlag() == "Yes") {
				lStrDdoOffice = "No";
			}
		}

		List<CmnLookupMst> lstOfficeClass = commonHomeMethodsService
				.getLookupValues(CommonConstants.COMMONMSTTABLE.DCPS_OFFICE_CLASS, CommonConstants.Languages.English);
		model.addAttribute("ddoOffClass", lstOfficeClass);

		bankName = commonHomeMethodsService.findBankName();
		lstBankBranch = commonHomeMethodsService.findbankBranch();
		lstdesgination = commonHomeMethodsService.findDesignation(messages.getUserName());
		String districtID = ddoInfoService.getDistrictId(ddoCode);
		List<CmnTalukaMst> lstTaluka = ddoInfoService.getTalukalst();
		/*
		 * String message=(String) model.asMap().get("message");
		 */
		/*
		 * List<ZpRltDdoMap> zpDDOOfficelst = zpDDOOfficeService
		 * .getAllDDOOfficeDtlsDataByPostID(messages.getCreatedByPost().getCreatedByPost
		 * ().getPostId());
		 * 
		 * 
		 * /// model.addAttribute("message", message); ///
		 * model.addAttribute("zpDDOOfficelst", zpDDOOfficelst);
		 */

		if (newRegDDOModel.getBankName() != null) {
			model.addAttribute("lstAllBankBranchList",
					commonHomeMethodsService.getBankBranch(newRegDDOModel.getBankName()));
			System.out.println("testingggg");
		}
		List<InstituteType> lstInstituteType = organizationInstInfoService.lstInstType();
		model.addAttribute("lstState", lstState);
		model.addAttribute("bankName", bankName);
		model.addAttribute("lstBankBranch", lstBankBranch);
		model.addAttribute("newRegDDOModel", newRegDDOModel);
		// model.addAttribute("ddoOffClass", ddoOffClass);
		model.addAttribute("lstDistrict", lstDistrict);
		model.addAttribute("lstInstituteType", lstInstituteType);
		model.addAttribute("lstTaluka", lstTaluka);
		model.addAttribute("lstdesgination", lstdesgination);
		model.addAttribute("lstTown", ddoInfoService.getLstTown());
		model.addAttribute("lstApprDdoOffice", ddoInfoService.getDDOOffForApproval(messages.getUserName()));

		addMenuAndSubMenu(model, messages);
		return "/views/ApproveDDOOffice";
	}

	/*
	 * @RequestMapping(value = "/getTalukabyDistrictId/{distId}", consumes =
	 * {"application/json" }, headers = "Accept=application/json", produces =
	 * MediaType.APPLICATION_JSON_VALUE) public ResponseEntity<List<CmnTalukaMst>>
	 * getIfscCodeByBranchId(@PathVariable String distId) { return
	 * ResponseEntity.ok(ddoInfoService.getTalukalst(distId)); }
	 */
	@GetMapping(value = "/getAlreadySavedDataforDDO/{ddoCode}")
	public @ResponseBody List<Object[]> getAlreadySavedDataforDDO(@PathVariable String ddoCode, Model model,
			Locale locale) {
		List<Object[]> lst = ddoInfoService.getAlreadySavedDataforDDO(ddoCode);
		return lst;
	}

	@GetMapping("/updateApproveRejectStatus/{ddoCode}/{flag}/{cityClass}")
	public ResponseEntity<String> updateApproveRejectStatus(
			@PathVariable String ddoCode, @PathVariable int flag, @PathVariable String cityClass, Model model,
			Locale locale, HttpSession session, HttpServletRequest request, Object paybillHeadMpgRepo) {
		DdoOffice orgUserMst = ddoInfoService.updateApproveRejectStatus(ddoCode, flag, cityClass);
		if (orgUserMst != null) {
			return ResponseEntity.ok("1");
		}
		return ResponseEntity.ok("1");
	}

}
