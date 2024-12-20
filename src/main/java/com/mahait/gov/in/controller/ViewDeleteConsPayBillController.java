/**
 * 
 */
package com.mahait.gov.in.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mahait.gov.in.entity.ConsolidatePayBillTrnEntity;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.ConsolidatePayBillModel;
import com.mahait.gov.in.model.LstConsolidatedPayBillModel;
import com.mahait.gov.in.repository.PaybillGenerationTrnRepo;
import com.mahait.gov.in.service.CommonHomeMethodsService;
import com.mahait.gov.in.service.MstSchemeService;
import com.mahait.gov.in.service.ViewDelConsolidatePayBillService;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/ddo")
public class ViewDeleteConsPayBillController extends BaseController{
//	protected final Log logger = LogFactory.getLog(getClass());
	@Autowired
	CommonHomeMethodsService commonHomeMethodsService;

	
	@Autowired
	MstSchemeService mstSchemeService;
	

	@Autowired
	ViewDelConsolidatePayBillService viewDelConsolidatePayBillService;
	
/*	@Autowired
	BDSIntegrationService bdsintegrationservice;*/
	
	@Autowired
	PaybillGenerationTrnRepo paybillHeadMpgRepo;
	
	@GetMapping("/viewDelconsolidatePayBill")
	public String consolidatePayBill(@ModelAttribute("consolidatePayBillModel") ConsolidatePayBillModel consolidatePayBillModel,
										Model model,Locale locale,HttpSession session) {
		
		String message = (String)model.asMap().get("message");
		OrgUserMst messages  = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		List<LstConsolidatedPayBillModel> lstConsolidatedPayBillModel = new ArrayList<>();
		
		
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yy"); // Just the year, with 2 digits
		String formattedDate = df.format(Calendar.getInstance().getTime());
		int currYear=Integer.parseInt(formattedDate)+1;
		//System.out.println(currYear);
		
		 String strDateFormat = "M";
	     SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
	     int currMonth=Integer.parseInt(sdf.format(date));
	     //logger.info("Current Month in M format : " + currMonth);
	     addMenuAndSubMenu(model,messages);	
		lstConsolidatedPayBillModel=viewDelConsolidatePayBillService.viewDelconsolidatePayBill(currMonth,currYear,messages.getDdoCode());
		model.addAttribute("LstConsolidatedPayBillModel", lstConsolidatedPayBillModel);
		
	
		model.addAttribute("lstMonths", commonHomeMethodsService.lstGetAllMonths());
		model.addAttribute("lstYears", commonHomeMethodsService.lstGetAllYears());
		model.addAttribute("language", locale.getLanguage());
		return "/views/paybill/view-delete-consolidated-paybill";
    }
	@GetMapping(value="/findAllConsolidatedPaybillListUsingFilter/{schemeCode}/{yearName}/{monthName}")
	public @ResponseBody List<Object[]> findAllConsolidatedPaybillListUsingFilter(@PathVariable String schemeCode,@PathVariable int yearName,@PathVariable int monthName,Model model,Locale locale,HttpSession session) {
		OrgUserMst messages  = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		List<Object[]> consolatedFilterList =  viewDelConsolidatePayBillService.findAllConsolidatedPaybillListUsingFilter(monthName,yearName,schemeCode,messages.getMstRoleEntity().getRoleId(),messages.getUserName());
		return consolatedFilterList;
    }
	@GetMapping(value="/findAllConsolidatedPaybillListWithoutFilter/{yearName}/{monthName}")
	public @ResponseBody List<Object[]> findAllConsolidatedPaybillListWithoutFilter(@PathVariable int yearName,@PathVariable int monthName,Model model,Locale locale,HttpSession session) {
		OrgUserMst messages  = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		List<Object[]> consolatedFilterList =  viewDelConsolidatePayBillService.findAllConsolidatedPaybillListWithoutFilter(monthName,yearName,messages.getMstRoleEntity().getRoleId(),messages.getUserName());
		return consolatedFilterList;
	}

	@GetMapping("/deleteConsolidateBill/{consPaybillGenerationTrnId}")
	public String deleteConsolidateBill(@ModelAttribute("consolidatePayBillTrnEntity") ConsolidatePayBillTrnEntity consolidatePayBillTrnEntity,@PathVariable int consPaybillGenerationTrnId,
			Model model,Locale locale,HttpSession session) {
		
		
		ConsolidatePayBillTrnEntity consolidatePayBillTrnEntity1 = viewDelConsolidatePayBillService.findDeleteBillById(consPaybillGenerationTrnId);
		
		if(consolidatePayBillTrnEntity1!=null)
		{
			model.addAttribute("is_changed","1");
			return "/views/paybill/view-delete-consolidated-paybill";
		}
		else
		{
			model.addAttribute("is_changed","0");
			return "/views/paybill/view-delete-consolidated-paybill";
		}
	}
	
	/* @SuppressWarnings("unchecked")
		@RequestMapping(value = "/approveBill/{consolidatedId}/{userId}", method=RequestMethod.POST)
		public ResponseEntity<String> approveBill(@ModelAttribute("consolidatePayBillTrnEntity") ConsolidatePayBillTrnEntity consolidatePayBillTrnEntity,
				Model model,Locale locale,HttpSession session,@PathVariable int consolidatedId,@PathVariable int userId,HttpServletRequest request) throws UnknownHostException {
			
			//   select * from consolidate_paybill_trn 
	    	consolidatePayBillTrnEntity=bdsintegrationservice.findPayBillInfoById(consolidatedId);
	    	consolidatePayBillTrnEntity.setStatus(11);
	    	consolidatePayBillTrnEntity.setIsActive(11);
			consolidatePayBillTrnEntity.setUpdatedDate(new Date());
			consolidatePayBillTrnEntity.setUpdatedUserId(userId);
			bdsintegrationservice.approvePayBill(consolidatePayBillTrnEntity);
			List<Object[]> lst=bdsintegrationservice.findPayBillDetails(consolidatedId);
			for (Object object[] : lst) {
				Integer payGenTrnId=Integer.parseInt(object[0].toString());	
				PaybillGenerationTrnEntity paybillGenerationTrnEntity=bdsintegrationservice.findPaybillObj(payGenTrnId);
				PaybillStatusEntity paybillStatusEntity = new PaybillStatusEntity();
				
				paybillGenerationTrnEntity.setIsActive(11);
				paybillGenerationTrnEntity.setUpdatedDate(new Date());
				paybillGenerationTrnEntity.setUpdatedUserId(userId);
				
				 String clientIP = request.getRemoteAddr();
					
				 String clientHostname = request.getRemoteHost();
				   
				 
				 String namePIp=clientHostname+"/"+clientIP;
				 
				 System.out.println(namePIp);
				  String clientIP = request.getRemoteAddr();
				
				  System.out.println(clientIP);
				   
				
				
				//paybill status updation//
				InetAddress local=null;
				try {
					local = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				paybillStatusEntity.setBillNo(payGenTrnId);
				paybillStatusEntity.setCreatedDate(new Date());
				paybillStatusEntity.setIsActive(11);
				paybillStatusEntity.setUserId(userId);
				paybillStatusEntity.setMacId(namePIp);
				
				bdsintegrationservice.approveBill(paybillGenerationTrnEntity);
				Serializable id3= paybillHeadMpgRepo.savePaybillStatus(paybillStatusEntity);
			}
			String msg="Bill Approved Successfully";
			return ResponseEntity.ok(msg);
		}*/
}
