package com.mahait.gov.in.nps.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.nps.model.DcpsLegacyModel;
import com.mahait.gov.in.nps.repository.LegacyValidationRepo;
import com.mahait.gov.in.nps.repository.VerifyDcpsLegacyRepo;
import com.mahait.gov.in.response.MessageResponse;

import cra.standalone.paosubcontr.PAOFvu;
import jakarta.transaction.Transactional;



@PropertySource(value = { "classpath:application.properties" })
@Transactional
@Service
public class LegacyValidationServiceImpl implements LegacyValidationService {

	@Autowired
	LegacyValidationRepo legacyValidationRepo;

	@Autowired
	VerifyDcpsLegacyRepo verifyDcpsLegacyRepo;
	

	@Autowired
	private Environment environment;

	@Override
	public List<DcpsLegacyModel> findNsdlLegacyList(DcpsLegacyModel dcpsLegacyModel, OrgUserMst messages) {
		Long locId = verifyDcpsLegacyRepo.findLocId(messages.getLocId().toString());
		List<Object[]> lstObject = legacyValidationRepo.findNsdlLegacyList(dcpsLegacyModel, messages, locId);
		List<DcpsLegacyModel> lstDcpsLegacyModel = new ArrayList<>();
		if (lstObject.size() > 0) {
			for (Object[] object : lstObject) {
				DcpsLegacyModel dcpsLegacyModel1 = new DcpsLegacyModel();
				dcpsLegacyModel1.setFileName(StringHelperUtils.isNullString(object[0]));
				dcpsLegacyModel1.setBhEmpAmt(StringHelperUtils.isNullDouble(object[1]));
				dcpsLegacyModel1.setBhEmplrAmt(StringHelperUtils.isNullDouble(object[2]));
				dcpsLegacyModel1.setTransactionId(StringHelperUtils.isNullString(object[3]));
				dcpsLegacyModel1.setFileStatus(StringHelperUtils.isNullString(object[4]));
				dcpsLegacyModel1.setStatus(StringHelperUtils.isNullString(object[5]));
				dcpsLegacyModel1.setBhBatchFixId(StringHelperUtils.isNullString(object[6]));
				dcpsLegacyModel1.setVoucherNo(StringHelperUtils.isNullBigInteger(object[7]).longValue());

				if (object[8] != null) {
					dcpsLegacyModel1.setVoucherDate(StringHelperUtils.isNullTimestamp(object[8]));
				}

				dcpsLegacyModel1.setBdsNo(StringHelperUtils.isNullString(object[9]));
				dcpsLegacyModel1.setBankRefno(StringHelperUtils.isNullString(object[10]));
				dcpsLegacyModel1.setLocName(StringHelperUtils.isNullString(object[11]));
				lstDcpsLegacyModel.add(dcpsLegacyModel1);

			}
		}
		return lstDcpsLegacyModel;
	}

	@Override
	public String viewAndSaveLegacyFile(OrgUserMst messages, String fileNumber) {
		String dtoRegNo = legacyValidationRepo.getDtoRegNo(fileNumber);
		StringBuilder fileContent = new StringBuilder();
		fileContent.append("1^FH^P^").append(dtoRegNo).append("^1^^^^^^^").append("\r\n");
		String batchData = legacyValidationRepo.getBatchData(fileNumber);
		fileContent.append(batchData).append("\r\n");
		List<Object[]> dhData = legacyValidationRepo.getDHData(fileNumber);
		if (dhData != null && !dhData.isEmpty()) {
			for (Object[] record : dhData) {
				String dhDetails = record[0].toString();
				String ddoRegNo = record[1].toString();
				fileContent.append(dhDetails).append("\r\n");
				List<String> sdDetails = legacyValidationRepo.getSDDtls(fileNumber, ddoRegNo);
				for (String sdDetail : sdDetails) {
					fileContent.append(sdDetail).append("\r\n");
				}
			}
		}
		return fileContent.toString();
	}

	@Override
	public MessageResponse deleteLegacyData(OrgUserMst messages, String fileNo, String batchId) {

		String ddoCode = legacyValidationRepo.getDdoCode(batchId, fileNo);
		String fileStatus = legacyValidationRepo.getFileStatus(ddoCode, fileNo, batchId);
		MessageResponse messageResponse = new MessageResponse();

		if ("2".equalsIgnoreCase(fileStatus) || "0".equalsIgnoreCase(fileStatus)) {
			
			String batch_id = batchId.substring(7,20);
			List<Object[]> legacyData = legacyValidationRepo.getLegacyDataDetailsHis(fileNo, ddoCode, batch_id);

			if (legacyData != null && !legacyData.isEmpty()) {
				for (Object[] tuple : legacyData) {
					/*
					 * legacyValidationRepo.insertLegacyDataHistoryDetails( (BigInteger) tuple[0],
					 * // NPS_ID (String) tuple[1], // DDO_CODE (String) tuple[2], // SEVARTH_ID
					 * (String) tuple[3], // DCPS_ID (BigInteger) tuple[4],// DCPS_EMP_ID (Double)
					 * tuple[5], // EMP_CONTRI (Double) tuple[6], // EMPLOYER_CONTRI (Double)
					 * tuple[7], // EMP_INT (Double) tuple[8], // EMPLOYER_INT (Double) tuple[9], //
					 * TOTAL (Short) tuple[10], // YEAR (Short) tuple[11], // MONTH (String)
					 * tuple[12], // STATUS (Date) tuple[13], // CREATED_DATE (BigInteger)
					 * tuple[14],// CREATED_POST_ID (Date) tuple[15], // UPDATED_DATE (BigInteger)
					 * tuple[16],// UPDATED_POST_ID (Date) tuple[17], // APPROVAL_DATE (String)
					 * tuple[18], // REMARKS (String) tuple[19], // PERIOD (Date) tuple[20], //
					 * CONTRI_START_DATE (Date) tuple[21], // CONTRI_END_DATE (String) tuple[22], //
					 * BATCH_ID (Date) tuple[23] // REJECTION_DATE );
					 */
				}
				legacyValidationRepo.deleteFileDetails(fileNo, ddoCode, batchId);

				messageResponse.setResponse("File deleted successfully.");
				messageResponse.setStyle("SUCCESS");
			} else {
				messageResponse.setResponse("No Legacy Data found for File:." + fileNo);
				messageResponse.setStyle("WARNING");
			}
		} else {
			messageResponse.setResponse("File cannot be deleted.");
			messageResponse.setStyle("ERROR");
		}
		return messageResponse;
	}

	@Override
	public String validateLegacyData(OrgUserMst messages, String Fileno, Integer month, Integer year) {
		String lStrTempFromDate = null;
		String lStrTempToDate = null;
		String lStrFromDate = null;
		String lStrToDate = null;
		Date lDateFromDate = null;
		Date lDateToDate = null;
		List lListTotalDdowiseEntries = null;
		Long yearId = null;
		Long monthId = null;
		Long lLongEmployeeAmt = Long.valueOf(0L);
		Long lLongEmployerAmt = Long.valueOf(0L);
		Long TotalAmt = Long.valueOf(0L);
		String dhDtls = "";
		String ddoRegNo = "";
		StringBuilder sb11 = new StringBuilder();
		String errorData = " ";
		String ext = "";
		try {
			String ddocode = legacyValidationRepo.getDdoCode(Fileno);
			File f4 = null;
			f4 = new File(Fileno.concat(".txt"));
			f4.delete();
			f4.createNewFile();
				String key = "";
				String rootPath = "";
					String strOSName = System.getProperty("os.name");
					boolean test = strOSName.contains("Windows");
					String OUTPUT_ZIP_FILE=null;
			    	String	OUTPUT_ZIP_Contri_FILE=null;
			    	
			    	if(strOSName.contains("Windows")) {
			    		OUTPUT_ZIP_Contri_FILE=environment.getRequiredProperty("NPS.OUTPUT_FILE");
					}else 
					{
						OUTPUT_ZIP_Contri_FILE=environment.getRequiredProperty("NPS.OUTPUT_FILE_SERVER");
					}
			    	
					String Path = OUTPUT_ZIP_Contri_FILE;
					String directoryName = Path.concat(ddocode);
					File directory = new File(directoryName);
					if (!directory.exists()) {
						directory.mkdir();
					}
					directoryName=directoryName.concat("/"+Fileno.toString());
					 directory = new File(directoryName);
					if (!directory.exists()) {
						directory.mkdir();
					}
				String filePath =directoryName+"/"+ Fileno.concat(".txt");
			

			//String dtoRegNo = legacyValidationRepo.getDtoRegNo(Fileno.substring(1, 5));
			String dtoRegNo = legacyValidationRepo.getDtoRegNo(Fileno);

			FileWriter fw = new FileWriter(filePath);
			BufferedWriter br = new BufferedWriter(fw);

			br.write("1^");
			br.write("FH^");
			br.write("P^");
			br.write(dtoRegNo + "^");
			br.write("1^");
			br.write("^");
			br.write("A");
			br.write("^");
			br.write("^");
			br.write("^");
			br.write("^");
			br.write("^");
			br.write("\r\n");

			String bhData = legacyValidationRepo.getBatchData(Fileno);
			br.write(bhData);
			br.write("\r\n");

			List dhData = legacyValidationRepo.getDHData(Fileno);
			if ((dhData != null) && (dhData.size() > 0)) {
				Iterator IT = dhData.iterator();
				Object[] lObj = (Object[]) null;
				while (IT.hasNext()) {
					lObj = (Object[]) IT.next();
					dhDtls = lObj[0].toString();
					ddoRegNo = lObj[1].toString();
					br.write(dhDtls);

					br.write("\r\n");

					List sdDtls = legacyValidationRepo.getSDDtls(Fileno, ddoRegNo);
					for (int i = 0; i < sdDtls.size(); i++) {
						br.write(sdDtls.get(i).toString());
						br.write("\r\n");
					}
				}
			}

			br.close();


			String fvuFilePtah = filePath.replace("txt", "fvu");
			String errFilePtah = filePath.replace("txt", "err");
		
			String[] args = { filePath, errFilePtah, fvuFilePtah, "0", "1.44" };

			int fileStatus = 0;
		    PAOFvu.main(args);
		

			File f5 = null;
			f5 = new File(Fileno.concat(".txt"));
			System.out.println("File to be deleted" + f5);
			f5.deleteOnExit();

			File f = new File(new File(fvuFilePtah).getAbsolutePath());
			File f1 = new File(new File(errFilePtah).getAbsolutePath());

			if ((f.exists()) && (!f.isDirectory())) {
				BufferedReader br1 = new BufferedReader(new FileReader(
						new File(fvuFilePtah).getAbsolutePath()));

				ext = ".fvu";
				String line = br1.readLine();
				fileStatus = 1;
				while (line != null) {
					sb11.append(line);
					sb11.append("\r\n");
					System.out.println(sb11.toString());
					line = br1.readLine();
				}
			} else if ((f1.exists()) && (!f1.isDirectory())) {
				BufferedReader br1 = new BufferedReader(new FileReader(
						new File(errFilePtah).getAbsolutePath()));

				ext = ".err";
				String line = br1.readLine();
				fileStatus = 2;
				while (line != null) {
					sb11.append(line);
					sb11.append("\r\n");
					System.out.println(sb11.toString());
					line = br1.readLine();
				}
				errorData = sb11.toString();
			}
			legacyValidationRepo.updateFileStatus(fileStatus, Fileno, errorData);
		}catch(Exception e) {
			System.out.println("eroro");
		}
		return sb11+"-"+ext;
	}
}
