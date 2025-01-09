package com.mahait.gov.in.nps.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.net.http.HttpHeaders;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mahait.gov.in.common.CommonConstants;
import com.mahait.gov.in.common.CommonConstants.STATUS;
import com.mahait.gov.in.common.CommonUtils;
import com.mahait.gov.in.controller.BaseController;
import com.mahait.gov.in.entity.CmnLocationMst;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.nps.entity.NSDLBHDtlsEntity;
import com.mahait.gov.in.nps.entity.NSDLDHDtlsEntity;
import com.mahait.gov.in.nps.entity.NSDLSDDtlsEntity;
import com.mahait.gov.in.nps.model.DdoWiseNpsContriModel;
import com.mahait.gov.in.nps.model.NSDLDetailsModel;
import com.mahait.gov.in.nps.service.CSRFFormService;
import com.mahait.gov.in.nps.service.NSDLDetailsService;
import com.mahait.gov.in.service.CommonHomeMethodsService;
import com.mahait.gov.in.service.MstEmployeeService;
import com.mahait.gov.in.service.MstTalukaService;
import com.mahait.gov.in.service.OnlineContributionService;
import com.mahait.gov.in.service.PaybillGenerationTrnService;

import cra.standalone.paosubcontr.PAOFvu;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/ddo")
public class NSDLDetailsController extends BaseController {
	// protected final Log logger = LogFactory.getLog(getClass());
	@Autowired
	CommonHomeMethodsService commonHomeMethodsService;

	@Autowired
	MstTalukaService mstTalukaService;

	@Autowired
	NSDLDetailsService nsdlDetailsService;

	@Autowired
	Environment environment;

	@Autowired
	MstEmployeeService mstEmployeeService;

	@Autowired
	PaybillGenerationTrnService paybillGenerationTrnService;

	@Autowired
	CSRFFormService csrfFormService;

	@Autowired
	OnlineContributionService onlineContributionService;

	List<NSDLDetailsModel> nsdlDetailsModel1 = new ArrayList<>();

	private static String OUTPUT_ZIP_FILE = "D:/output/MJP/";
	private static String OUTPUT_ZIP_Contri_FILE = "D:/output/MJP/Contribution/";

	@GetMapping("/NSDLDetails")
	public String NSDLDetails(@ModelAttribute("nsdlDetailsModel") NSDLDetailsModel nsdlDetailsModel, Model model,
			Locale locale, HttpSession session, HttpServletRequest request) {

		String message = (String) model.asMap().get("message");
		model.addAttribute("nsdlDetailsModel", nsdlDetailsModel);

		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		model.addAttribute("context", request.getContextPath());

		if (message != null && message.equals("SUCCESS")) {
			if (locale != null && locale.getLanguage().equalsIgnoreCase("en")) {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_ENGLSH, STATUS.SUCCESS, model);
			} else {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_MARATHI, STATUS.SUCCESS, model);
			}

			model.addAttribute("message", message);
		}
		model.addAttribute("lstGetAllMonths", commonHomeMethodsService.lstGetAllMonths());
		model.addAttribute("lstGetAllYear", commonHomeMethodsService.lstGetAllYears());

		model.addAttribute("nsdlDetailsModel", nsdlDetailsModel);
		addMenuAndSubMenu(model, messages);
		model.addAttribute("language", locale.getLanguage());
		return "/views/nps/NSDLDetails";
	}

	@GetMapping("/NSDLEmpWiseReport/{filename}")
	public String NSDLEmpWiseReport(@ModelAttribute("nsdlDetailsModel") NSDLDetailsModel nsdlDetailsModel, Model model,
			HttpSession session, Locale locale, @PathVariable String filename) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		String message = (String) model.asMap().get("message");

		if (message != null && message.equals("SUCCESS")) {
			if (locale != null && locale.getLanguage().equalsIgnoreCase("en")) {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_ENGLSH, STATUS.SUCCESS, model);
			} else {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_MARATHI, STATUS.SUCCESS, model);
			}
		}

		nsdlDetailsModel1 = nsdlDetailsService.lstNsdlEmpDtls(filename);

		addMenuAndSubMenu(model, messages);

		nsdlDetailsModel.setNsdlDetailsModelList(nsdlDetailsModel1);
		model.addAttribute("nsdlDetailsModel", nsdlDetailsModel);

		return "/views/report/NSDLEmpWiseReport";
	}

	@GetMapping("/NSDLDDOWiseReport/{ddoCode}")
	public String NSDLDDOWiseReport(@ModelAttribute("nsdlDetailsModel") NSDLDetailsModel nsdlDetailsModel, Model model,
			HttpSession session, Locale locale, @PathVariable String ddoCode) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		String message = (String) model.asMap().get("message");

		nsdlDetailsModel1 = nsdlDetailsService.lstNsdlDDOWiseDtls(ddoCode);
		addMenuAndSubMenu(model, messages);
		nsdlDetailsModel.setNsdlDetailsModelList(nsdlDetailsModel1);
		model.addAttribute("nsdlDetailsModel", nsdlDetailsModel);

		return "/views/report/NSDLDDOWiseReport";
	}

	@GetMapping("/NSDLinput")
	public String NSDLinput(HttpSession session, @ModelAttribute("nsdlDetailsModel") NSDLDetailsModel nsdlDetailsModel,
			Model model, Locale locale) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		String message = (String) model.asMap().get("message");
		addMenuAndSubMenu(model, messages);
		model.addAttribute("lstGetAllMonths", commonHomeMethodsService.lstGetAllMonths());
		model.addAttribute("lstGetAllYear", commonHomeMethodsService.lstGetAllYears());

		List<CmnLocationMst> lstTreasury = onlineContributionService.findTreasuryList(messages);
		model.addAttribute("lstTreasury", lstTreasury);
		return "/views/nps/NSDLinput";
	}

	@GetMapping(value = "/getNSDLEmpDtls/{month}/{year}")
	public @ResponseBody List<Object[]> findPayBillByMonthYear(@PathVariable int month, @PathVariable int year,
			Model model, Locale locale, HttpSession session) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		List<Object[]> consolatedFilterList = nsdlDetailsService.getNsdlEmpData(month, year, messages.getUserName());
		return consolatedFilterList;
	}

	@GetMapping(value = "/searchDdoWiseContribution/{month}/{year}")
	public ResponseEntity<List<DdoWiseNpsContriModel>> searchDdoWiseContribution(@PathVariable int month,
			@PathVariable int year, Model model, Locale locale, HttpSession session) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		List<DdoWiseNpsContriModel> consolatedFilterList = nsdlDetailsService.searchDdoWiseContribution(month, year,
				messages);
		return ResponseEntity.ok(consolatedFilterList);
	}

	@GetMapping(value = "/getNSDLEmpDtlsForGenerate/{month}/{year}")
	public @ResponseBody List<Object[]> getNSDLEmpDtlsForGenerate(@PathVariable int month, @PathVariable int year,
			Model model, Locale locale, HttpSession session) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		List<Object[]> nsdEmpDtlsForGenerate = nsdlDetailsService.getNsdlEmpDataLevelwise(month, year,
				messages.getUserName());
		return nsdEmpDtlsForGenerate;
	}

	@PostMapping("/validateNsdlFile")
	public ResponseEntity<InputStreamResource> validateNsdlFile(
			@ModelAttribute("nsdlDetailsModel") NSDLDetailsModel nsdlDetailsModel, Model model, Locale locale,
			HttpSession session, HttpServletRequest request) {

		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		String lStrTempFromDate = null;
		String lStrTempToDate = null;
		String lStrFromDate = null;
		String lStrToDate = null;
		Date lDateFromDate = null;
		Date lDateToDate = null;
		List lListTotalDdowiseEntries = null;
		Long yearId = null;
		Long lLongEmployeeAmt = 0L;
		Long lLongEmployerAmt = 0L;
		Long TotalAmt = 0L;
		String dhDtls = "";
		String ddoRegNo = "";
		StringBuilder sb11 = new StringBuilder();
		String errorData = " ";
		String ext = "";

		try {
			String Fileno = nsdlDetailsModel.getFileId();
			File f4 = null;
			f4 = new File(Fileno.concat(".txt"));
			f4.delete();

			f4.createNewFile();

			String key = "";
			String rootPath = "";
			String strOSName = System.getProperty("os.name");
			boolean test = strOSName.contains("Windows");
			if (strOSName.contains("Windows")) {
				// key = "serverempconfigimagepath";
			} else {
				key = "serverContributionFolderPath";
				OUTPUT_ZIP_Contri_FILE = environment.getRequiredProperty(key);

				File directory = new File(OUTPUT_ZIP_Contri_FILE);
				if (!directory.exists()) {
					directory.mkdir();
				}
			}

			String Path = OUTPUT_ZIP_Contri_FILE;
			String directoryName = Path.concat(messages.getUserName());
			File directory = new File(directoryName);
			if (!directory.exists()) {
				directory.mkdir();
			}
			directoryName = directoryName.concat("/" + Fileno.toString());
			directory = new File(directoryName);
			if (!directory.exists()) {
				directory.mkdir();
			}

			String filePath = directoryName + "/" + Fileno.concat(".txt");

			String dtoRegNo = csrfFormService.getDtoRegNumber(messages.getUserName());
			FileWriter fw = new FileWriter(filePath);
			BufferedWriter br = new BufferedWriter(fw);

			Calendar cal = Calendar.getInstance(TimeZone.getDefault());
			String date = "ddMMyyyy";
			SimpleDateFormat sdf = new SimpleDateFormat(date);
			sdf.setTimeZone(TimeZone.getDefault());
			String currentdate = sdf.format(cal.getTime());

			br.write("1" + "^");
			br.write("FH" + "^");
			br.write("P" + "^");
			br.write(dtoRegNo + "^");
			br.write("1" + "^");
			br.write(currentdate);
			br.write("^");
			br.write("A");
			br.write("^");
			br.write("^");
			br.write("^");
			br.write("^");
			br.write("^");
			br.write("\r\n");

			String bhData = nsdlDetailsService.getBatchData(Fileno);
			br.write(bhData);
			br.write("\r\n");

			List dhData = nsdlDetailsService.getDHData(Fileno);

			if (dhData != null && dhData.size() > 0) {
				Iterator IT = dhData.iterator();

				Object[] lObj = null;
				while (IT.hasNext()) {
					lObj = (Object[]) IT.next();
					if (lObj[0] != null)
						dhDtls = lObj[0].toString();
					if (lObj[1] != null)
						ddoRegNo = lObj[1].toString();
					br.write(dhDtls);

					br.write("\r\n");

					List sdDtls = nsdlDetailsService.getSDDtls(Fileno, messages.getUserName());
					for (int i = 0; i < sdDtls.size(); i++) {
						br.write(sdDtls.get(i).toString());
						br.write("\r\n");

					}

				}

			}

			br.close();

			String fvuFilePtah = filePath.replace("txt", "fvu");
			String errFilePtah = filePath.replace("txt", "err");

			String[] args = { filePath, errFilePtah, fvuFilePtah, "0", "1.72" };

			int fileStatus = 0;
			PAOFvu.main(args);

			/*
			 * String ackNo=ddoRegNo+"100"+String.format("%07d", count);
			 * 
			 * TrnNpsRegFileEntity trnNpsRegFile=new TrnNpsRegFileEntity();
			 * 
			 * trnNpsRegFile.setBatchFixId(lLngEmpId.intValue()); trnNpsRegFile.setAckNo(new
			 * BigInteger(ackNo)); trnNpsRegFile.setNpsId(mstNpsEmp.getEmployeeNPSId());
			 * trnNpsRegFile.setIsActive("1"); trnNpsRegFile.setCreatedDate(new Date());
			 * trnNpsRegFile.setCreatedUserId(messages.getUser_id());
			 * trnNpsRegFile.setRefSeq(regSeq.intValue());
			 * trnNpsRegFile.setTotalEmpInBatch(records);
			 * 
			 * csrfFormService.saveTrnNpsRegFile(trnNpsRegFile);
			 * 
			 */

			File f5 = null;
			f5 = new File(Fileno.concat(".txt"));
			System.out.println("File to be deleted" + f5);
			f5.deleteOnExit();

			File f = new File(new File(fvuFilePtah).getAbsolutePath());
			File f1 = new File(new File(errFilePtah).getAbsolutePath());

			if (f.exists() && !f.isDirectory()) {
				BufferedReader br1 = new BufferedReader(new FileReader(new File(fvuFilePtah).getAbsolutePath()));

				ext = ".fvu";
				String line = br1.readLine();
				fileStatus = 1;
				while (line != null) {
					sb11.append(line);
					sb11.append("\r\n");
					line = br1.readLine();
				}
			}

			else if (f1.exists() && !f1.isDirectory()) {
				BufferedReader br1 = new BufferedReader(new FileReader(new File(errFilePtah).getAbsolutePath()));

				ext = ".err";
				String line = br1.readLine();
				fileStatus = 2;
				while (line != null) {
					sb11.append(line);
					sb11.append("\r\n");
					line = br1.readLine();

				}
				errorData = sb11.toString();

			}

			// nsdlDetailsService.updateFileStatus(fileStatus, Fileno, errorData);
			File serverFile = new File(filePath);
			// Download file with InputStreamResource
			FileInputStream fileInputStream = null;
			try {
				fileInputStream = new FileInputStream(serverFile);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			InputStreamResource inputStreamResource = new InputStreamResource(fileInputStream);

			return ResponseEntity.ok().header(CommonConstants.Headers.CONTENT_DISPOSITION, "attachment;filename=" + serverFile)
					.contentType(MediaType.TEXT_PLAIN).contentLength(serverFile.length()).body(inputStreamResource);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@GetMapping("/viewAndSaveFile/{month}/{year}/{treasuryId}/{fileId}")
	public ResponseEntity<InputStreamResource> viewAndSaveFile(@PathVariable Integer month, @PathVariable Integer year,
			@PathVariable Integer treasuryId, @PathVariable String fileId, Locale locale, HttpSession session,
			HttpServletRequest request) {
		NSDLDetailsModel nsdlDetailsModel = new NSDLDetailsModel();

		nsdlDetailsModel.setYear(year);
		nsdlDetailsModel.setMonth(month);
		nsdlDetailsModel.setFileId(fileId);
		nsdlDetailsModel.setTreasuryId(treasuryId);

		String lStrTempFromDate = null;
		String lStrTempToDate = null;
		String lStrFromDate = null;
		String lStrToDate = null;
		Date lDateFromDate = null;
		Date lDateToDate = null;
		List lListTotalDdowiseEntries = null;
		Long yearId = null;
		Long lLongEmployeeAmt = 0L;
		Long lLongEmployerAmt = 0L;
		Long TotalAmt = 0L;
		String dhDtls = "";
		String ddoRegNo = "";
		StringBuilder sb11 = new StringBuilder();
		String errorData = " ";
		String ext = "";

		try {
			String Fileno = nsdlDetailsModel.getFileId();
			File f4 = null;
			f4 = new File(Fileno.concat(".txt"));
			f4.delete();

			f4.createNewFile();

			OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
			String key = "";
			String rootPath = "";
			String strOSName = System.getProperty("os.name");
			boolean test = strOSName.contains("Windows");
			if (strOSName.contains("Windows")) {
				// key = "serverempconfigimagepath";
			} else {
				key = "serverContributionFolderPath";
				OUTPUT_ZIP_Contri_FILE = environment.getRequiredProperty(key);

				File directory = new File(OUTPUT_ZIP_Contri_FILE);
				if (!directory.exists()) {
					directory.mkdir();
				}

			}

			String Path = OUTPUT_ZIP_Contri_FILE;
			String directoryName = Path.concat(messages.getUserName());
			File directory = new File(directoryName);
			if (!directory.exists()) {
				directory.mkdir();
			}
			directoryName = directoryName.concat("/" + Fileno.toString());
			directory = new File(directoryName);
			if (!directory.exists()) {
				directory.mkdir();
			}

			String filePath = directoryName + "/" + Fileno.concat(".txt");

			String dtoRegNo = csrfFormService.getDtoRegNumber(messages.getUserName());
			FileWriter fw = new FileWriter(filePath);
			BufferedWriter br = new BufferedWriter(fw);

			Calendar cal = Calendar.getInstance(TimeZone.getDefault());
			String date = "ddMMyyyy";
			SimpleDateFormat sdf = new SimpleDateFormat(date);
			sdf.setTimeZone(TimeZone.getDefault());
			String currentdate = sdf.format(cal.getTime());

			br.write("1" + "^");
			br.write("FH" + "^");
			br.write("P" + "^");
			br.write(dtoRegNo + "^");
			br.write("1" + "^");
			br.write(currentdate);
			br.write("^");
			br.write("A");
			br.write("^");
			br.write("^");
			br.write("^");
			br.write("^");
			br.write("^");
			br.write("\r\n");

			String bhData = nsdlDetailsService.getBatchData(Fileno);
			br.write(bhData);
			br.write("\r\n");

			List dhData = nsdlDetailsService.getDHData(Fileno);

			if (dhData != null && dhData.size() > 0) {
				Iterator IT = dhData.iterator();

				Object[] lObj = null;
				while (IT.hasNext()) {
					lObj = (Object[]) IT.next();
					if (lObj[0] != null)
						dhDtls = lObj[0].toString();
					if (lObj[1] != null)
						ddoRegNo = lObj[1].toString();
					br.write(dhDtls);

					br.write("\r\n");

					List sdDtls = nsdlDetailsService.getSDDtls(Fileno, messages.getUserName());
					for (int i = 0; i < sdDtls.size(); i++) {
						br.write(sdDtls.get(i).toString());
						br.write("\r\n");

					}

				}

			}

			br.close();
			String fvuFilePtah = filePath.replace("txt", "fvu");
			String errFilePtah = filePath.replace("txt", "err");

			String[] args = { filePath, errFilePtah, fvuFilePtah, "0", "1.72" };

			// nsdlDetailsService.updateFileStatus(fileStatus, Fileno, errorData);
			File serverFile = new File(filePath);
			// Download file with InputStreamResource
			FileInputStream fileInputStream = null;
			try {
				fileInputStream = new FileInputStream(serverFile);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			InputStreamResource inputStreamResource = new InputStreamResource(fileInputStream);

			return ResponseEntity.ok().header(CommonConstants.Headers.CONTENT_DISPOSITION, "attachment;filename=" + serverFile)
					.contentType(MediaType.TEXT_PLAIN).contentLength(serverFile.length()).body(inputStreamResource);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/genNsdlTxtFile", method = RequestMethod.POST)
	public String genNsdlTxtFile(HttpSession session,
			@ModelAttribute("nsdlDetailsModel") NSDLDetailsModel nsdlDetailsModel, Model model, Locale locale) {
		Long Num = null;
		BufferedReader br = null;

		String empname = null;
		String dcpsid = null;
		String pranno = null;
		String govEmpContri = null;
		String subempContri = null;
		String Contritype = null;

		int countsum = 0;
		String govcontiSum = null;
		String subcontiSum = null;
		String TotalContri = null;
		String TotalContrisum = null;
		String extn = null;
		String aisType = null;

		StringBuilder Strbr = new StringBuilder();
		StringBuilder Strbr1 = new StringBuilder();
		String extnFlag = null;

		Object obj1[];
		String fromDate = null;
		String toDate = null;
		String dtoRegNo = null;
		String ddoRegNo = null;
		String sdAmnt = null;
		String prvDdoReg = "AAA";
		Integer treasuryyno = null;
		List<Object[]> lstemployee = new ArrayList<>();
		Integer yrCode = null;
		Integer month = null;
		List nsdlData = null;
		String BatchId = null;
		Long nwbatchId = null;
		String nwTranBatchId = null;
		String[] fyYrsplit = null;
		String tranId = null;
		String subTrCode = null;
		int trno = 0;

		String gLocId = "";

		extn = "txt";

		extnFlag = "text";
		String yearName = "";
		yrCode = nsdlDetailsModel.getYear();
		treasuryyno = nsdlDetailsModel.getTreasuryId();

		List<Object[]> yeard = commonHomeMethodsService.findyearinfo(BigInteger.valueOf(yrCode.longValue()));
		for (Object[] objects : yeard) {
			yearName = objects[1].toString();
		}
		month = nsdlDetailsModel.getMonth();

		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");

		dtoRegNo = csrfFormService.getDtoRegNumber(messages.getUserName());
		ddoRegNo = csrfFormService.getDdoRegNumber(messages.getUserName());
		trno = csrfFormService.gettrano(messages.getUserName());

		System.out.println("dto number is>>>" + dtoRegNo);

		String monthname = "";
		String midFix = "";
		String finalFix = "";
		if (String.valueOf(month).length() == 1) {
			midFix = "0";
			monthname = midFix + month;
		} else {
			monthname = month.toString();
		}
		String batchIdPrefix = trno + yearName + midFix + month;
		String countBatchId = nsdlDetailsService.getbatchIdCount(batchIdPrefix);
		if (countBatchId.length() == 1) {
			finalFix = "00";
		}
		if (countBatchId.length() == 2) {
			finalFix = "0";
		}

		BatchId = batchIdPrefix + finalFix + countBatchId;

		Calendar cal = Calendar.getInstance(TimeZone.getDefault());
		String date = "ddMMyyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(date);
		sdf.setTimeZone(TimeZone.getDefault());
		String currentdate = sdf.format(cal.getTime());

		String ddoName = messages.getUserName();

		lstemployee = nsdlDetailsService.getEmployeeListNsdl(yrCode, month, treasuryyno, ddoName);

		// Long countReg = nsdlDetailsService.getDDoRegCount(yrCode, month,treasuryyno);

		int i = 3;
		int k = 0;
		for (Object[] obje : lstemployee) {
			i++;
			k++;
			NSDLSDDtlsEntity nSDLSDDtlsEntity = new NSDLSDDtlsEntity();
			nSDLSDDtlsEntity.setSrno(i);
			nSDLSDDtlsEntity.setDdoRegNo(dtoRegNo);
			nSDLSDDtlsEntity.setFileRemark(BatchId);
			nSDLSDDtlsEntity.setHeaderName("SD");
			nSDLSDDtlsEntity.setIsLegacyData('Y');
			nSDLSDDtlsEntity.setSdEmpAmt(Double.valueOf(obje[7].toString()));
			nSDLSDDtlsEntity.setSdEmplrAmt(Double.valueOf(obje[6].toString()));
			nSDLSDDtlsEntity.setSdNo(Long.valueOf(1));
			nSDLSDDtlsEntity.setSdNo2(Long.valueOf(1));
			nSDLSDDtlsEntity.setSdNo3(Long.valueOf(k));
			nSDLSDDtlsEntity.setSdPranNo(obje[9].toString());
			nSDLSDDtlsEntity.setStatus("1");
			nSDLSDDtlsEntity.setSdStatus("A" + "^" + monthname + "^" + yearName);
			nSDLSDDtlsEntity.setDdoRegNo(messages.getUserName());
			String empCont = obje[7].toString();
			String emprCont = obje[6].toString();

			Double total = Double.parseDouble(empCont) + Double.parseDouble(emprCont);
			nSDLSDDtlsEntity.setSdRemark("cont for " + monthname + " and " + yearName);
			nSDLSDDtlsEntity.setSdTotalAmt(total);
			Integer saveId = nsdlDetailsService.saveSDDetail(nSDLSDDtlsEntity);
		}

		List<Object[]> lst = nsdlDetailsService.getNSDLEmpDtlsForGenerate(month, yrCode, ddoName);
		int j = 0;
		for (Object[] obj : lst) {
			j++;

			NSDLBHDtlsEntity nSDLBHDtlsEntity = new NSDLBHDtlsEntity();
			nSDLBHDtlsEntity.setSrno(2);
			nSDLBHDtlsEntity.setHeaderName("BH");
			nSDLBHDtlsEntity.setBhBatchFixId(dtoRegNo + BatchId);
			nSDLBHDtlsEntity.setBhCol2('R');
			nSDLBHDtlsEntity.setIsLegacyData("N");
			nSDLBHDtlsEntity.setBhEmpAmt(Double.valueOf(obj[3].toString()));
			nSDLBHDtlsEntity.setBhEmplrAmt(Double.valueOf(obj[2].toString()));
			nSDLBHDtlsEntity.setBhDate(currentdate);
			nSDLBHDtlsEntity.setBhddoCount(1);
			nSDLBHDtlsEntity.setBhFixNo(dtoRegNo);
			nSDLBHDtlsEntity.setBhNo('1');
			nSDLBHDtlsEntity.setBhPRANCount(String.valueOf(lstemployee.size()));
			nSDLBHDtlsEntity.setFileStatus("1");
			nSDLBHDtlsEntity.setFileName(BatchId);

			String empCont = obj[3].toString();
			String emprCont = obj[2].toString();

			Double total = Double.parseDouble(empCont) + Double.parseDouble(emprCont);

			nSDLBHDtlsEntity.setBhTotalAmt(total);
			// nSDLBHDtlsEntity.setFileName(fileName);
			nSDLBHDtlsEntity.setStatus("1");
			nSDLBHDtlsEntity.setFrnNo("0");
			nSDLBHDtlsEntity.setMonth(Integer.valueOf(monthname));
			nSDLBHDtlsEntity.setYear(yrCode);
			nSDLBHDtlsEntity.setYearName(Integer.valueOf(yearName));
			Integer saveId1 = nsdlDetailsService.saveBHDetail(nSDLBHDtlsEntity);

			NSDLDHDtlsEntity nSDLDHDtlsEntity = new NSDLDHDtlsEntity();
			nSDLDHDtlsEntity.setSrno(3);
			nSDLDHDtlsEntity.setHeaderName("DH");
			nSDLDHDtlsEntity.setDhDDORegno(ddoRegNo); // SVG
			nSDLDHDtlsEntity.setBhSDCnt(BigInteger.valueOf((lstemployee.size())));
			nSDLDHDtlsEntity.setDhCol2(String.valueOf(j));
			nSDLDHDtlsEntity.setDhEmpAmt(Double.valueOf(obj[3].toString()));
			nSDLDHDtlsEntity.setDhEmplrAmt(Double.valueOf(obj[2].toString()));
			nSDLDHDtlsEntity.setDhNo(String.valueOf(j));
			nSDLDHDtlsEntity.setDhStatus("1");
			// nSDLDHDtlsEntity.setFileName(fileName);
			nSDLDHDtlsEntity.setFileName(BatchId);
			nSDLDHDtlsEntity.setIsLegacyData('Y');
			nSDLDHDtlsEntity.setDhNo("1");

			Integer saveId2 = nsdlDetailsService.saveDHDetail(nSDLDHDtlsEntity);

		}
		return "redirect:/master/NSDLDetails";
	}

	@GetMapping("/empContriDtls/{ddoCode}/{month}/{year}")
	public String empContriDtls(@ModelAttribute("nsdlDetailsModel") NSDLDetailsModel nsdlDetailsModel, Model model,
			HttpSession session, Locale locale, @PathVariable String ddoCode, @PathVariable Integer month,
			@PathVariable Integer year) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		String message = (String) model.asMap().get("message");

		if (message != null && message.equals("SUCCESS")) {
			if (locale != null && locale.getLanguage().equalsIgnoreCase("en")) {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_ENGLSH, STATUS.SUCCESS, model);
			} else {
				model = CommonUtils.initModel(CommonConstants.Message.ADDED_MARATHI, STATUS.SUCCESS, model);
			}
		}

		/*
		 * nsdlDetailsModel1 = nsdlDetailsService.lstNsdlDDOWiseDtls(ddoCode);
		 * 
		 * nsdlDetailsModel.setNsdlDetailsModelList(nsdlDetailsModel1);
		 * model.addAttribute("nsdlDetailsModel", nsdlDetailsModel);
		 */
		String curryear = null;
		Integer monthName = nsdlDetailsModel.getMonth();
		Integer year1 = nsdlDetailsModel.getYear();
		BigInteger bigInteger = BigInteger.valueOf(nsdlDetailsModel.getMonth());

		List<Object[]> yearinfo = commonHomeMethodsService.findyearinfo(BigInteger.valueOf(year));
		for (Object[] yearLst : yearinfo) {
			curryear = yearLst[1].toString();
		}

		String monname = "";
		List<Object[]> monthinfo = paybillGenerationTrnService.findmonthinfo(bigInteger);
		for (Object[] monthLst : monthinfo) {
			// String empName;
			monname = monthLst[1].toString();

		}
		List<NSDLDetailsModel> lstemployee1 = nsdlDetailsService.getEmployeeListNsdl1(year, month, ddoCode);

		BigInteger yearcurr = BigInteger.valueOf(year);
		BigInteger nextMonth = BigInteger.valueOf(month + 1);
		String NextMon = "";
		String NextYear = "";
		BigInteger neyer = null;
		BigInteger nemon;
		BigInteger monthid = BigInteger.valueOf(month);

		if (monthid.equals(new BigInteger("12"))) {
			nextMonth = new BigInteger("1");
			BigInteger nextyer = new BigInteger("1");
			neyer = yearcurr.add(nextyer);
			NextYear = neyer.toString();
		} else {
			nextMonth = nextMonth;
			neyer = yearcurr;
		}
		List<Object[]> nextmonthinfo = paybillGenerationTrnService.findmonthinfo(nextMonth);
		for (Object[] monthLst : nextmonthinfo) {
			// String empName;
			NextMon = monthLst[1].toString();

		}

		List<Object[]> nextyearinfo = paybillGenerationTrnService.findyearinfo(neyer);
		for (Object[] yearLst : nextyearinfo) {
			// String empName;
			NextYear = yearLst[1].toString();

		}

		/*
		 * String empName = null; String sevaarthId = null; String gross = null; String
		 * net = null; String empAmt = null; String emprAmt = null;
		 * 
		 * for (Object[] objddoLst : lstemployee1) { // String empName; empName =
		 * objddoLst[0].toString(); sevaarthId = objddoLst[2].toString();
		 * 
		 * gross = objddoLst[3].toString(); net = objddoLst[4].toString(); empAmt =
		 * objddoLst[5].toString(); emprAmt = objddoLst[6].toString(); }
		 * 
		 * model.addAttribute("empName", empName); model.addAttribute("sevaarthId",
		 * sevaarthId); model.addAttribute("gross", gross); model.addAttribute("net",
		 * net); model.addAttribute("empAmt", empAmt);
		 */
		model.addAttribute("lstNSDLDetailsModel", lstemployee1);
		model.addAttribute("nextMonth", nextMonth);
		NextYear = "NPS Contribution of Month " + monname + " - " + curryear + " Paid In Month " + NextMon + "-"
				+ NextYear;
		model.addAttribute("NextYear", NextYear);

		return "/views/report/empContriDtls";
	}

	@GetMapping("/ddoWisePaybillContriReport")
	public String ddoWisePaybillContriReport(HttpSession session,
			@ModelAttribute("nsdlDetailsModel") NSDLDetailsModel nsdlDetailsModel, Model model, Locale locale) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		String message = (String) model.asMap().get("message");
		addMenuAndSubMenu(model, messages);
		
		model.addAttribute("lstGetAllMonths", commonHomeMethodsService.lstGetAllMonths());
		model.addAttribute("lstGetAllYear", commonHomeMethodsService.lstGetAllYears());

		List<CmnLocationMst> lstTreasury = onlineContributionService.findTreasuryList(messages);
		model.addAttribute("lstTreasury", lstTreasury);
		return "/views/nps/ddo-wise-emp-paybill-contri-report";
	}

}
