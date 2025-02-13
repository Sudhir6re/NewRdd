package com.mahait.gov.in.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.imageio.ImageIO;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mahait.gov.in.entity.BeamsIntegrationEntity;
import com.mahait.gov.in.entity.ConsolidatePayBillTrnEntity;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.service.BDSIntegrationService;
import com.mahait.gov.in.service.CommonHomeMethodsService;
import com.mahait.gov.in.service.MstSchemeService;
import com.mahait.gov.in.service.ViewDelConsolidatePayBillService;

import bds.authorization.BeamsIntegrationWebService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/ddo")
@PropertySource(value = { "classpath:Payroll.properties" })
public class BDSIntegrationController {
//	protected final Log logger = LogFactory.getLog(getClass());
//	private static final Logger logger = LogManager.getLogger(BDSIntegrationController.class);

	@Autowired
	CommonHomeMethodsService commonHomeMethodsService;

	@Autowired
	MstSchemeService mstSchemeService;

	@Autowired
	ViewDelConsolidatePayBillService viewDelConsolidatePayBillService;

	@Autowired
	BDSIntegrationService bdsintegrationservice;

	@Autowired
	private Environment environment;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/beams/frwdbilldatabeams/{consolidatePaybillTrnId}", method = RequestMethod.POST)
	public ResponseEntity<Object> forwardBillDataToBEAMS(Model model, Locale locale, HttpSession session,
			@PathVariable Long consolidatePaybillTrnId) {

		ModelAndView modelAndView = new ModelAndView();
		List<JSONObject> entities = new ArrayList<JSONObject>();
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");

		// logger.info("paybillGenerationTrnIdMMM############" + consolidateId);
		Long paybillid = 0l;
		String ddocode = messages.getDdoCode();
		int finyear1 = 0;
		int finyear2 = 0;
		String schemecode = "";
		Double grossamount = 0d;
		int beneficiarycount = 0;
		int billtype = 0;
		int paymonth = 0;
		Date billcreationdate = null;
		Double totalDeduction = 0d;
		Double pt = 0d;
		Double it = 0d;
		Double hrr = 0d;
		Double dcps = 0d;
		Double gis = 0d;
		Double gisZp = 0d;
		Double groupaccpolicy = 0d;
		Double pf = 0d;
		Double revenueStamp = 0d;

		List<Object[]> alBillDtls = bdsintegrationservice.getPaybillDtls(consolidatePaybillTrnId);
		for (Iterator iterator = alBillDtls.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			paybillid = (Long) objects[0];
			ddocode = (String) objects[1];
			finyear1 = (Integer.parseInt(objects[2].toString()));
			finyear2 = (Integer.parseInt(objects[3].toString()));
			schemecode = (String) objects[4];
			grossamount = (Double.parseDouble(objects[5].toString()));
			beneficiarycount = (Integer.parseInt(objects[6].toString()));
			billtype = (int) ((objects[7] != null) ? objects[7] : 1);
			paymonth = (int) objects[8];
			billcreationdate = (Date) objects[9];
			totalDeduction = (Double.parseDouble(objects[10].toString()));
			if (objects[11] != null) {
				pt = (Double.parseDouble(objects[11].toString()));
			}
			if (objects[12] != null) {
				it = (Double.parseDouble(objects[12].toString()));
			}

			if (objects[13] != null) {
				hrr = (Double.parseDouble(objects[13].toString()));
			}
			if (objects[14] != null) {
				dcps = (Double.parseDouble(objects[14].toString()));
			}
			if (objects[15] != null) {
				gis = (Double.parseDouble(objects[15].toString()));
			}
			if (objects[16] != null) {
				groupaccpolicy = (Double.parseDouble(objects[16].toString()));
			}
			if (objects[17] != null) {
				pf = (Double.parseDouble(objects[17].toString()));
			}

			if (objects[18] != null) {
				gisZp = (Double.parseDouble(objects[18].toString()));
			}

			/*
			 * for (int i = 0; i < objects.length; i++) { }
			 */

		}

		List<Object[]> alheadcodePF = bdsintegrationservice.getheadcodePF(consolidatePaybillTrnId);
		double gpfHeadActCodeOne = 0d;
		double gpfHeadActCodeTwo = 0d;
		double gpfHeadActCodeThree = 0d;

		final Iterator itera = alheadcodePF.iterator();

		while (itera.hasNext()) {
			Object[] obj = (Object[]) itera.next();
			if (obj[0] != null) {
				if (obj[1] == null)
					obj[1] = "0";

				if ((obj[0].toString()).equals("8336514701")) {
					gpfHeadActCodeOne = (Double.parseDouble(obj[1].toString()));
				} else if ((obj[0].toString()).equals("8336518301"))

				{
					gpfHeadActCodeTwo = (Double.parseDouble(obj[1].toString()));

				} else {
					gpfHeadActCodeThree = (Double.parseDouble(obj[1].toString()));
				}
			}
		}

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String strDate = "";
		if (billcreationdate != null) {
			strDate = formatter.format(billcreationdate);
		}

		String strMonth = "";

		if (paymonth < 10) {
			strMonth = "0" + paymonth;
		} else {
			strMonth = paymonth + "";
		}

		final Calendar cal = Calendar.getInstance();
		int month = 0;
		int year = 0;
		if (billcreationdate != null) {
			cal.setTime(billcreationdate);
			month = cal.get(Calendar.MONTH);
			year = cal.get(Calendar.YEAR);
		}

		int curYear = 0;

		if (month == 1 || month == 2 || month == 3) {
			curYear = year - 1;

		} else {
			curYear = year;
		}
		int nxtYear = curYear + 1;

		final Integer billType = 1;
		String strBillType = billType.toString();
		// if(billType<10)
		strBillType = "0" + billType;
		// obj.setComplaintDate(strDate);

		// Temp Data for Testing purpose
		/*
		 * PayrollBEAMSIntegrateWS payrollBEAMSIntegrateWSObj = new
		 * PayrollBEAMSIntegrateWS(); HashMap lMapBillDetailsMap = new HashMap();
		 * lMapBillDetailsMap.put("PaybillId", "99100008346"); // consolidate
		 * lMapBillDetailsMap.put("DDOCode", "9101005555");
		 * lMapBillDetailsMap.put("FinYear1", String.valueOf("2019"));
		 * lMapBillDetailsMap.put("FinYear2", String.valueOf("2020"));
		 * lMapBillDetailsMap.put("PayMonth", "11"); lMapBillDetailsMap.put("PayYear",
		 * Integer.parseInt("2019")); lMapBillDetailsMap.put("PaymentMode", "CMP");
		 * //DEFAULT lMapBillDetailsMap.put("BulkFlag", "Y"); //DEFAULT
		 * lMapBillDetailsMap.put("PayeeCount", "1"); // DEFAULT
		 * lMapBillDetailsMap.put("BillPortalName", "SAMAJSEVAARTH"); // default
		 * lMapBillDetailsMap.put("SchemeCode", String.valueOf("22104562"));
		 * //lMapBillDetailsMap.put("SubSchemeCode", String.valueOf(lStrSubSchemeCode));
		 * // NM lMapBillDetailsMap.put("DetailHead", String.valueOf("36")); // DEFAULT
		 * lMapBillDetailsMap.put("GrossAmount", String.valueOf("7332677"));
		 * lMapBillDetailsMap.put("BillCreationDate", "2019-11-18 13:18:55.158");
		 * lMapBillDetailsMap.put("BeneficiaryCount", Integer.valueOf(109));
		 * lMapBillDetailsMap.put("BillType",01); lMapBillDetailsMap.put("FormId",
		 * "MTR44"); // defualt lMapBillDetailsMap.put("TotalDeduction",
		 * String.valueOf(247700)); Map lMapDeducBifurcatedMapSuppzero = new HashMap();
		 * lMapDeducBifurcatedMapSuppzero.put("RC0028001201", 20400l);
		 * lMapDeducBifurcatedMapSuppzero.put("RC8009501201", 226700);
		 * lMapDeducBifurcatedMapSuppzero.put("RC8009517301", 600);
		 * 
		 * lMapBillDetailsMap.put("BifurcatedDedMapInnerMap",
		 * lMapDeducBifurcatedMapSuppzero);
		 * 
		 * 
		 * HashMap resultMap
		 * =payrollBEAMSIntegrateWSObj.getBillApprvFrmBEAMSWS(lMapBillDetailsMap, "");
		 * 
		 * logger.info("resultMap is ::: " + resultMap);
		 */

		// PayrollBEAMSIntegrateWS payrollBEAMSIntegrateWSObj = new
		// PayrollBEAMSIntegrateWS();

		HashMap lMapBillDetailsMap = new HashMap();
		lMapBillDetailsMap.put("PaybillId", String.valueOf(paybillid)); // Consolidate
		// lMapBillDetailsMap.put("DDOCode", ddocode);

		if (ddocode.equals("0437010002")) {
			lMapBillDetailsMap.put("DDOCode", "9101005555");
		} else {
			lMapBillDetailsMap.put("DDOCode", String.valueOf(ddocode));
		}

		lMapBillDetailsMap.put("FinYear1", String.valueOf(curYear));
		lMapBillDetailsMap.put("FinYear2", String.valueOf(nxtYear));
		lMapBillDetailsMap.put("PayMonth", strMonth);
		lMapBillDetailsMap.put("PayYear", curYear);

		// lMapBillDetailsMap.put("PaymentMode", "CMP"); // DEFAULT
		// lMapBillDetailsMap.put("BulkFlag", "Y"); // DEFAULT

		if (bdsintegrationservice.isEkuber(ddocode)) {
			lMapBillDetailsMap.put("PaymentMode", "CMP");
			lMapBillDetailsMap.put("BulkFlag", "Y");
			lMapBillDetailsMap.put("PaymentFile", "Y");// PaymentFile for eKuber
		} else {
			lMapBillDetailsMap.put("PaymentMode", "CMP");
			lMapBillDetailsMap.put("BulkFlag", "Y");
		}

		lMapBillDetailsMap.put("PayeeCount", "1"); // DEFAULT
		lMapBillDetailsMap.put("BillPortalName", "PANCHAYATRAJ"); // DEFAULT
		lMapBillDetailsMap.put("SchemeCode", String.valueOf(schemecode.trim()));
		// lMapBillDetailsMap.put("SubSchemeCode", String.valueOf(lStrSubSchemeCode));
		// // NM
		lMapBillDetailsMap.put("DetailHead", String.valueOf("36")); // DEFAULT
		lMapBillDetailsMap.put("GrossAmount", String.valueOf(grossamount));
		lMapBillDetailsMap.put("BillCreationDate", strDate);
		lMapBillDetailsMap.put("BeneficiaryCount", Integer.valueOf(beneficiarycount));
		lMapBillDetailsMap.put("BillType", strBillType);
		lMapBillDetailsMap.put("FormId", "MTR44"); // DEFAULT
		lMapBillDetailsMap.put("TotalDeduction", String.valueOf(totalDeduction));

		Map lMapDeducBifurcatedMapSuppzero = new HashMap();
		lMapDeducBifurcatedMapSuppzero.put("RC0028001201", String.valueOf(pt));
		// prov fund details
		/*
		 * lMapBillDetailsMap.put("RC8336514701", gpfHeadActCodeOne);
		 * lMapBillDetailsMap.put("RC8336518301", gpfHeadActCodeTwo);
		 * lMapBillDetailsMap.put("RC8336516501", gpfHeadActCodeThree);
		 */
		lMapDeducBifurcatedMapSuppzero.put("RC8336514701", gpfHeadActCodeOne);
		lMapDeducBifurcatedMapSuppzero.put("RC8336518301", gpfHeadActCodeTwo);
		lMapDeducBifurcatedMapSuppzero.put("RC8336516501", gpfHeadActCodeThree);
		lMapDeducBifurcatedMapSuppzero.put("RC8658518201", String.valueOf(it));
		lMapDeducBifurcatedMapSuppzero.put("RC0216006901", String.valueOf(hrr));
		// lMapDeducBifurcatedMapSuppzero.put("RC8342535701",String.valueOf(dcps));
		lMapDeducBifurcatedMapSuppzero.put("RC8011502301", String.valueOf(gis));
		lMapDeducBifurcatedMapSuppzero.put("RC8121507501", String.valueOf(groupaccpolicy));
		lMapDeducBifurcatedMapSuppzero.put("RC0030046401", String.valueOf(revenueStamp)); // evenue stamp

		if (finyear1 > 2018 || (finyear1 == 2018 && paymonth >= 3)) {
			totalDeduction = gpfHeadActCodeOne + pt + gpfHeadActCodeThree + gpfHeadActCodeTwo + gis + gisZp
					+ groupaccpolicy + revenueStamp;

		} else {
			totalDeduction = gpfHeadActCodeOne + pt + gpfHeadActCodeThree + gpfHeadActCodeTwo + gis + gisZp
					+ groupaccpolicy + dcps + revenueStamp;
			lMapBillDetailsMap.put("RC8342535701", String.valueOf(dcps));
		}

		lMapBillDetailsMap.put("TotalDeduction", String.valueOf(totalDeduction));

		/*
		 * lMapDeducBifurcatedMapSuppzero.put("RC8121507501",String.valueOf(
		 * groupaccpolicy));
		 */

		lMapBillDetailsMap.put("BifurcatedDedMapInnerMap", lMapDeducBifurcatedMapSuppzero);

		BeamsIntegrationWebService beamsIntegrationWebService = new BeamsIntegrationWebService();

		String param = "";
		String beamsUrl= "";
		String strOSName = System.getProperty("os.name");
		boolean test = strOSName.contains("Windows");
		if (strOSName.contains("Windows")) {
			param = "beamstestWsdlLoc";
		} else {
			param = "beamsliveWsdlLoc";
		}
		beamsUrl = environment.getRequiredProperty(param);

		
		// HashMap resultMap =
		// payrollBEAMSIntegrateWSObj.getBillApprvFrmBEAMSWS(lMapBillDetailsMap, "");
		HashMap resultMap = beamsIntegrationWebService.forwardPaybillToBeams(lMapBillDetailsMap,beamsUrl);

//		 HashMap resultMap =new HashMap();

		String authNo = null;
		String statusCode = null;
		byte[] pdfData = null;
		if (resultMap != null && !resultMap.isEmpty()) {
			authNo = resultMap.get("authNo") != null ? (String) resultMap.get("authNo") : null;
			statusCode = resultMap.get("statusCode") != null ? (String) resultMap.get("statusCode") : null;
			pdfData = resultMap.get("pdfData") != null ? (byte[]) resultMap.get("pdfData") : null;

			if (pdfData.length > 0)
				uploadAuthSlip(pdfData, "RDD", authNo);
		}

		@SuppressWarnings("rawtypes")
		final List finalMsg = new ArrayList();

		List<String> myList = new ArrayList<String>();

		if (statusCode != null && statusCode.length() > 0 && !"00".equals(statusCode)) {

			finalMsg.add("Bill is rejected by BEAMS.Reason of rejection,");
			final String[] stCode = statusCode.split("\\|");
			for (int cnt = 0; cnt < stCode.length; ++cnt) {
				final String key = "Status" + stCode[cnt];
				final String stMsg = String.valueOf(cnt + 1) + ") " + environment.getRequiredProperty(key);
				finalMsg.add(stMsg);
				myList.add(stMsg);

			}
			// objectArgs.put("finalMsg", finalMsg);
		}

		BeamsIntegrationEntity beamsIntegrationEntity = new BeamsIntegrationEntity();
		beamsIntegrationEntity.setConsolidateId(Integer.parseInt(lMapBillDetailsMap.get("PaybillId").toString()));
		beamsIntegrationEntity.setDdoCode((String) lMapBillDetailsMap.get("DDOCode"));
		beamsIntegrationEntity.setFinYear1(Integer.parseInt(lMapBillDetailsMap.get("FinYear1").toString()));
		beamsIntegrationEntity.setFinYear2(Integer.parseInt(lMapBillDetailsMap.get("FinYear2").toString()));
		beamsIntegrationEntity.setYearMonth(Integer.parseInt(lMapBillDetailsMap.get("PayMonth").toString()));
		// beamsIntegrationEntity.setlMapBillDetailsMap.get("PayYear");
		// beamsIntegrationEntity.setlMapBillDetailsMap.get("PaymentMode");
		//// Character temp =(Character) lMapBillDetailsMap.get("BulkFlag");
		beamsIntegrationEntity.setBillValidStatus((String) lMapBillDetailsMap.get("BulkFlag")); // statusCode
		beamsIntegrationEntity.setStatusCode(statusCode);
		// lMapBillDetailsMap.get("PayeeCount");
		// lMapBillDetailsMap.get("BillPortalName");
		beamsIntegrationEntity.setSchemeCode((String) lMapBillDetailsMap.get("SchemeCode"));
		// lMapBillDetailsMap.get("SubSchemeCode");
		// // NM
		beamsIntegrationEntity.setDtlHeadCode((String) lMapBillDetailsMap.get("DetailHead"));
		beamsIntegrationEntity.setBillGrossAmt((Double.parseDouble(lMapBillDetailsMap.get("GrossAmount").toString())));
		beamsIntegrationEntity.setBillCreationDate(billcreationdate);
		beamsIntegrationEntity
				.setNoOfBenifciary(Integer.parseInt(lMapBillDetailsMap.get("BeneficiaryCount").toString()));

		// lMapBillDetailsMap.get("BillType");
		// lMapBillDetailsMap.get("FormId");
		// beamsIntegrationEntity.setlMapBillDetailsMap.get("TotalDeduction");
		/*
		 * Session currentSession = manager.unwrap(Session.class);
		 * currentSession.save(beamsIntegrationEntity);
		 */

		ConsolidatePayBillTrnEntity consolidatePayBillTrnEntity1 = bdsintegrationservice
				.getConsolidatedPaybillDtls(consolidatePaybillTrnId, statusCode);
		// PaybillGenerationTrnEntity paybillGenerationTrnEntity1 =
		// bdsintegrationservice.getConsPaybillDtls(consolidatePaybillTrnId,statusCode);
		if (resultMap != null && !resultMap.isEmpty()) {
			List<String> data1 = bdsintegrationservice.getData(beamsIntegrationEntity, myList);
			consolidatePayBillTrnEntity1.setAuthNo(Integer.parseInt(authNo));

		}
		// return String.format(template, name);
		modelAndView.addObject(finalMsg);
		// modelAndView.setViewName("/views/paybill/paybill-view-approve-delete-bill");

		/* entities.add((JSONObject) finalMsg); */
		return new ResponseEntity<Object>(finalMsg, HttpStatus.OK);
	}

	public String uploadAuthSlip(byte[] bytes, String DeptNm, String authNo) {
		// department name/photo/employee_id/photo.jpg
		String path = null;
		if (bytes.length != 0) {
			int width = 963;
			int height = 640;

			try {

				if (bytes.length != 0) {
					BufferedImage image = null;
					File f = null;
					InputStream in = new ByteArrayInputStream(bytes);

					image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
					image = ImageIO.read(in);

					String key = "";
					String rootPath = "";
					String strOSName = System.getProperty("os.name");
					boolean test = strOSName.contains("Windows");
					if (strOSName.contains("Windows")) {
						key = "authSlipWindowsPath";
					} else {
						key = "authSlipLinuxPath";
					}
					rootPath = environment.getRequiredProperty(key);
					rootPath += DeptNm;
					File dir = new File(rootPath);
					if (!dir.exists())
						dir.mkdirs();

					String name = authNo + ".pdf";
					// Create the file on server
					File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();

					path = serverFile.getPath();
					return path;
				} else {
					return null;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return path;
	}

}
