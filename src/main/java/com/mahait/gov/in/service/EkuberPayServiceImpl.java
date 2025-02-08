package com.mahait.gov.in.service;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.mahait.gov.in.common.ExcelExportHelper;
import com.mahait.gov.in.entity.HrPayEkuberRecordMst;
import com.mahait.gov.in.entity.HrPayEkuberRecordMstDtls;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.ColumnVo;
import com.mahait.gov.in.repository.EkuberPayRepo;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;


@PropertySource(value = { "classpath:application.properties" })
@Slf4j
@Transactional
@Service
public class EkuberPayServiceImpl implements EkuberPayService {
	
	@Autowired
	EkuberPayRepo ekuberPayRepo;
	
	
	@Autowired
	private Environment environment;

	
	private static String CIPHER_NAME = "AES/CBC/PKCS5PADDING";
	private static int CIPHER_KEY_LEN = 16; // 128 bits
	
	

	@Override
	public String generateEkuberFile(String authNo, OrgUserMst orgUserMst,HttpServletResponse response) {
	

		String bulkUploadFlag = "A";
		int paybillMonth = 0;
		int paybillYear = 0;
		String narration = "Salary";
		List lstPaybillId = null;
		List commonDetails = null;
		List commonJsonDetails = null;
		Object objPaybill[];
		long paybillGrpId = 0L;
		Long ngrAmount = 0L;
		List lstNgrAmount = null;
		String ddoEmail = "-";
		String ddoContact = "-";
		Object objTotal[] = null;
		Object objTotalJson[] = null;
		Object objNgrAmount[];
		String uidSeededFlag;
		String accountOrUIDNo = "";
		String uidNo;
		String accNo = "";
		String billIdentifier = "K";
		String fileName = "";
		String payBillGrpId = null;
		String treasuryCode = "";
		String ddoCode = "";
		String zpddoCode = "";
		String toDdoCode = "";
		String billNetAmount = "";
		String schemeCode = "";
		String ddoName = null;
		String ddoAccNo = "";
		String ddoIFSCode = "";
		String payBillId = "";
		List ddoDtls;
		Long empNetAmount = 0L;
		String empName = null;
		String sevaarthId = null;
		String ifscCode = null;
		String netAmountStr = null;
		String billdate = "";
		String billDateStr = "N";
		String ngrAmountStr = "";
		String ddoBillGrpId = "";
		Long totalDed = 0L;
		List otherDed = null;
		Object objCafoDtls[];
		String cafoName = null;
		String cafoAccNo = "";
		String cafoIFSCode = "";
		String cafoDdoCode = "";
		String cafoEmail = "";
		String cafoContact = "";
		String cmpFileFlag = "";

		final StringBuilder emptextFile = new StringBuilder();
		Map net = new HashMap();
		Object objTotalDed[];
	
		lstPaybillId = ekuberPayRepo.getPaybillIdFromAuthNo(authNo);

		if (lstPaybillId != null && lstPaybillId.size() > 0) {
			Iterator itPaybill = lstPaybillId.iterator();
			while (itPaybill.hasNext()) {
				objPaybill = (Object[]) itPaybill.next();
				if (objPaybill[0] != null) {
					paybillMonth = Integer.parseInt((objPaybill[0].toString()));
				}
				if (objPaybill[1] != null) {
					paybillYear = Integer.parseInt(objPaybill[1].toString());
				}
				if (objPaybill[2] != null) {
					paybillGrpId = Long.parseLong(objPaybill[2].toString());
				}
				if (objPaybill[4] != null) {
					cmpFileFlag = objPaybill[4].toString();
				}
			}
		}

		commonDetails = ekuberPayRepo.getCommonDetails(authNo);
		int noOfPayees = 0;


		JSONArray JSONArray1 = new JSONArray();
		JSONArray JSONArray = new JSONArray();
		JSONArray JSONArray2 = new JSONArray();

		JSONObject listFinal = new JSONObject();
		Map list1 = new LinkedHashMap();
		Map m = new LinkedHashMap();

		lstNgrAmount = ekuberPayRepo.getNonGovDeductionforDDO(authNo);
		Long npsAmount = ekuberPayRepo.getNPSAmount(authNo);

		String finYear = ekuberPayRepo.getFinancialYear(authNo);
		noOfPayees = ekuberPayRepo.checkBenificiaryCount(authNo);

		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		Date payDate;
		String payByDate = "";
		if (paybillMonth == 3 && paybillYear == 2015) {
			payByDate = "01/04/2015";
		} else {

			calendar.set(Calendar.MONTH, (paybillMonth) - 1);
			calendar.set(Calendar.YEAR, paybillYear);
			calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
			payDate = calendar.getTime();
			payByDate = sdf.format(payDate);
		}

		List<Object> empDetails = new ArrayList<Object>();
		List<Object> ddoDetails = new ArrayList<Object>();
		double billNetAmountD = 0.0;

		if (commonDetails != null && !commonDetails.isEmpty()) {

			if (!cmpFileFlag.equals("EK")) {
				ekuberPayRepo.modifyEkuberFile(authNo);
			}

			Iterator itTotal1 = commonDetails.iterator();

			while (itTotal1.hasNext()) {

				objTotalJson = (Object[]) itTotal1.next();
				billNetAmount = (objTotalJson[4] != null ? objTotalJson[4] : "-").toString();
				billNetAmountD = Double.parseDouble(billNetAmount.trim());
				toDdoCode = (objTotalJson[1] != null ? objTotalJson[1] : "").toString();
				schemeCode = (objTotalJson[2] != null ? objTotalJson[2] : "").toString();
				ddoCode = (objTotalJson[17] != null ? objTotalJson[17] : "").toString();
				zpddoCode = (objTotalJson[19] != null ? objTotalJson[19] : "").toString();

				m = new LinkedHashMap(7);

				m.put("Fin_Year", finYear);
				m.put("Payment_Type", "RDD");
				m.put("Authorization_Number", authNo);
				m.put("DDO_Code", toDdoCode);
				m.put("Total_Beneficiaries", noOfPayees);
				m.put("Total_Net_Amount", billNetAmountD);
				m.put("DDO_Payee_Code", toDdoCode);
				if (paybillMonth < 10) {
					m.put("Month", "0" + paybillMonth);
				} else {
					m.put("Month", paybillMonth);
				}
				m.put("Year", paybillYear);
				m.put("Scheme_Code", schemeCode.trim());

			}

			JSONArray.add(m);
			list1.put("Header", JSONArray);

			String empNetAmountStr = "";
			String noOfPayeess = "";
			String payrefNoo = "";
			List lstKBRList = ekuberPayRepo.getCMPAuthCount(authNo);
			log.info("lstKBRList---" + lstKBRList.size());

			if (lstKBRList.size() > 0 && lstKBRList != null) {

				log.info("lstKBRList--in if-" + lstKBRList.size());

				Iterator itTotal = lstKBRList.iterator();

				while (itTotal.hasNext()) {

					objTotal = (Object[]) itTotal.next();

					ddoCode = (objTotal[6] != null ? objTotal[6] : "-").toString();
					sevaarthId = (objTotal[1] != null ? objTotal[1] : "-").toString();
					empName = (objTotal[3] != null ? objTotal[3] : "-").toString();
					empName = empName.replaceAll("[^a-zA-Z0-9]", " ");
					ifscCode = (objTotal[5] != null ? objTotal[5] : "-").toString();
					billNetAmount = (objTotal[9] != null ? objTotal[9] : "-").toString();
					empNetAmountStr = (objTotal[2] != null ? objTotal[2] : "-").toString();
					payrefNoo = (objTotal[0] != null ? objTotal[0] : "-").toString();
					schemeCode = (objTotal[8] != null ? objTotal[8] : "-").toString();
					noOfPayeess = (objTotal[7] != null ? objTotal[7] : "-").toString();
					accountOrUIDNo = (objTotal[4] != null ? objTotal[4] : "-").toString();
					accountOrUIDNo = accountOrUIDNo.replaceAll("[^a-zA-Z0-9\\p{Punct}]", " ");

					double empNetAmountD = Long.valueOf(empNetAmountStr).doubleValue();
					m = new LinkedHashMap(6);
					m.put("Department_ID", payrefNoo);
					m.put("Sevaarth_PPO_ID", sevaarthId);
					m.put("Account_Number", accountOrUIDNo);
					m.put("IFSC_Code", ifscCode);
					m.put("Payee_Name", empName.trim());
					m.put("Net_Amount", empNetAmountD);
					JSONArray1.add(m);

				}
			} else {

				if (commonDetails != null && !commonDetails.isEmpty()) {

					Date currentDateTime =new Date();
					Iterator itTotal = commonDetails.iterator();

					while (itTotal.hasNext()) {

						objTotal = (Object[]) itTotal.next();

						accNo = (objTotal[9] != null ? objTotal[9] : "-").toString();
						accNo = accNo.replaceAll("[^a-zA-Z0-9]", "");
						uidNo = (objTotal[12] != null ? objTotal[12] : "-").toString();
						uidSeededFlag = (objTotal[13] != null ? objTotal[13] : "-").toString();

						if (uidSeededFlag.equals("S")) {
							if (!uidNo.equals("-"))
								accountOrUIDNo = uidNo;
							billIdentifier = "A";
						} else {
							if (!accNo.equals("-"))
								accountOrUIDNo = accNo;
						}
						treasuryCode = (objTotal[0] != null ? objTotal[0] : "").toString();
						ddoCode = (objTotal[17] != null ? objTotal[17] : "").toString();
						zpddoCode = (objTotal[19] != null ? objTotal[19] : "").toString();
						empName = (objTotal[8] != null ? objTotal[8] : "").toString();
						empName = empName.replaceAll("[^a-zA-Z0-9]", " ");
						sevaarthId = objTotal[16] != null ? objTotal[16].toString() : "";
						ifscCode = (objTotal[10] != null ? objTotal[10] : "").toString();

						Long empNet = objTotal[11] != null ? Long.parseLong(objTotal[11].toString()) : 0L;
						Long empNgr = objTotal[18] != null ? Long.parseLong(objTotal[18].toString()) : 0L;
						empNetAmount = empNet - empNgr;

						netAmountStr = empNetAmount.toString();

						payBillId = (objTotal[5] != null ? objTotal[5] : "").toString();

						Long payrefNo = ekuberPayRepo.getNxtSequenceNo(treasuryCode, noOfPayees);

						String newPayRefNo = treasuryCode + payrefNo;

						schemeCode = (objTotal[2] != null ? objTotal[2] : "").toString();
						payBillGrpId = (objTotal[6] != null ? objTotal[6] : "-").toString();
						billdate = (objTotal[3] != null ? objTotal[3] : "-").toString();
						if (billDateStr.equals("N"))
							billDateStr = billdate;
						billNetAmount = (objTotal[4] != null ? objTotal[4] : "-").toString();

						HrPayEkuberRecordMst EkuberRecord = new HrPayEkuberRecordMst();

						if (empName.length() > 50) {
							empName = empName.substring(0, 50);
						} else {
							empName = empName;
						}
						EkuberRecord.setAuthNo(authNo);
						EkuberRecord.setBulkUploadFlag(bulkUploadFlag);
						EkuberRecord.setBillId(billIdentifier);
						EkuberRecord.setTreasuryCode(treasuryCode);
						EkuberRecord.setDdoCode(zpddoCode);
						EkuberRecord.setSevarthId(sevaarthId);
						EkuberRecord.setBenefName(empName);
						EkuberRecord.setAccNo(accountOrUIDNo);
						EkuberRecord.setIfscCode(ifscCode);
						EkuberRecord.setAmount(netAmountStr);
						EkuberRecord.setPaymentRefNo(newPayRefNo);
						EkuberRecord.setPayBydate(payByDate);
						EkuberRecord.setSchemeCode(schemeCode);
						EkuberRecord.setDdoBillNo(payBillGrpId);
						EkuberRecord.setBillDate(billDateStr);
						EkuberRecord.setNarration("Salary");
						EkuberRecord.setNoOfPayees(String.valueOf(noOfPayees));
						EkuberRecord.setBillNetAmt(billNetAmount);
						EkuberRecord.setEkuberFlag("Y");
						EkuberRecord.setFinancialYear(finYear);
						EkuberRecord.setEmployeeType("Employee");
						EkuberRecord.setReptDdoCode(ddoCode);
						EkuberRecord.setToDdoCode(toDdoCode);
						EkuberRecord.setCreatedDate(currentDateTime);
						ekuberPayRepo.save(EkuberRecord);

						HrPayEkuberRecordMstDtls EkuberRecordDtls = new HrPayEkuberRecordMstDtls();

						EkuberRecordDtls.setAuthNo(authNo);
						EkuberRecordDtls.setBulkUploadFlag(bulkUploadFlag);
						EkuberRecordDtls.setBillId(billIdentifier);
						EkuberRecordDtls.setTreasuryCode(treasuryCode);
						EkuberRecordDtls.setDdoCode(zpddoCode);
						EkuberRecordDtls.setSevarthId(sevaarthId);
						EkuberRecordDtls.setBenefName(empName);
						EkuberRecordDtls.setAccNo(accountOrUIDNo);
						EkuberRecordDtls.setIfscCode(ifscCode);
						EkuberRecordDtls.setAmount(netAmountStr);
						EkuberRecordDtls.setPaymentRefNo(newPayRefNo);
						EkuberRecordDtls.setPayBydate(payByDate);
						EkuberRecordDtls.setSchemeCode(schemeCode);
						EkuberRecordDtls.setDdoBillNo(payBillGrpId);
						EkuberRecordDtls.setBillDate(billDateStr);
						EkuberRecordDtls.setNarration("Salary");
						EkuberRecordDtls.setNoOfPayees(String.valueOf(noOfPayees));
						EkuberRecordDtls.setBillNetAmt(billNetAmount);
						EkuberRecordDtls.setEkuberFlag("Y");
						EkuberRecordDtls.setFinancialYear(finYear);
						EkuberRecordDtls.setEmployeeType("Employee");
						EkuberRecordDtls.setReptDdoCode(ddoCode);
						EkuberRecordDtls.setToDdoCode(toDdoCode);
						EkuberRecordDtls.setCreatedDate(currentDateTime);
						ekuberPayRepo.save(EkuberRecordDtls);

						try {

							Long l = new Long(netAmountStr);
							double empNetAmountD = l.doubleValue();
							m = new LinkedHashMap(6);
							m.put("Department_ID", newPayRefNo);
							m.put("Sevaarth_PPO_ID", sevaarthId);
							m.put("Net_Amount", empNetAmountD);
							m.put("Payee_Name", empName.trim());
							m.put("Account_Number", accountOrUIDNo);
							m.put("IFSC_Code", ifscCode);
							JSONArray1.add(m);
						} catch (Exception e) {
							log.error("error while inserting " + e);
							e.printStackTrace();
						}

					}

					Long paybillFlag = ekuberPayRepo.getPaybillFlag(authNo);
					String tableName = "HR_PAY_PAYBILL";

					if (paybillFlag != null && paybillFlag == 1) {
						tableName = "PAYBILL_CMP";
					}

					String level1Ddocode = "";

					otherDed = ekuberPayRepo.getOtherDedForDDO(authNo, tableName, paybillFlag, paybillMonth,
							paybillYear);

					if (lstNgrAmount != null && !lstNgrAmount.isEmpty()) {
						Iterator itNgrAmount = lstNgrAmount.iterator();
						while (itNgrAmount.hasNext()) {
							objNgrAmount = (Object[]) itNgrAmount.next();
							if (objNgrAmount[0] != null)
								ngrAmount = Long.parseLong((objNgrAmount[0].toString()));
							if (objNgrAmount[2] != null)
								ddoBillGrpId = objNgrAmount[2].toString();
							if (objNgrAmount[1] != null)
								level1Ddocode = ((objNgrAmount[1].toString()));
							ddoDtls = ekuberPayRepo.getDdoDtls(level1Ddocode, authNo);
							if (ddoDtls != null && !ddoDtls.isEmpty()) {
								Iterator ddoIterator = ddoDtls.iterator();
								ddoDetails = new ArrayList();
								while (ddoIterator.hasNext()) {

									objTotal = (Object[]) ddoIterator.next();

									ddoName = (objTotal[0] != null ? objTotal[0] : "-").toString();
									ddoName = ddoName.replaceAll("[!@#$%^&*_=+\\\\\\[\\]{};?<>]", "");
									ddoAccNo = (objTotal[1] != null ? objTotal[1] : "-").toString();
									ddoIFSCode = (objTotal[2] != null ? objTotal[2] : "-").toString();
									ddoEmail = (objTotal[3] != null ? objTotal[3] : "-").toString();
									if (ddoEmail != null && !ddoEmail.equals("") && ddoEmail.length() > 40) {
										ddoEmail = "";
									}

									ddoContact = (objTotal[4] != null ? objTotal[4] : "-").toString();
									if (ddoContact != null && !ddoContact.equals("") && ddoContact.length() > 10) {
										ddoContact = "";
									}
								}
							}

							if (otherDed != null && !otherDed.isEmpty()) {
								final Iterator otherDedIterator = otherDed.iterator();
								new ArrayList();
								while (otherDedIterator.hasNext()) {
									objTotalDed = (Object[]) otherDedIterator.next();
									if (objTotalDed[1].equals(level1Ddocode)) {
										totalDed = Long.parseLong(
												(objTotalDed[0] != null ? objTotalDed[0] : "0").toString());
									}
								}
							}
							Long ddoNetAmt = Long.valueOf(ngrAmount) + Long.valueOf(totalDed);
							if (net.get(level1Ddocode) != null && !net.get(level1Ddocode).equals("")
									&& Long.parseLong(net.get(level1Ddocode).toString()) != 0) {
								ddoNetAmt = ddoNetAmt - Long.parseLong(net.get(level1Ddocode).toString());
							}

							if (ddoNetAmt > 0L && ddoNetAmt != null) {

								Long payrefNo = ekuberPayRepo.getNxtSequenceNo(treasuryCode, noOfPayees);

								String newPayRefNo = treasuryCode + payrefNo;
								ngrAmountStr = new Long(ddoNetAmt).toString();
								double ddoNetAmountD = ddoNetAmt.doubleValue();
								HrPayEkuberRecordMst EkuberRecord = new HrPayEkuberRecordMst();

								if (ddoName.length() > 50) {
									ddoName = ddoName.substring(0, 50);
								} else {
									ddoName = ddoName;
								}

								EkuberRecord.setAuthNo(authNo);
								EkuberRecord.setBulkUploadFlag(bulkUploadFlag);
								EkuberRecord.setBillId(billIdentifier);
								EkuberRecord.setTreasuryCode(treasuryCode);
								EkuberRecord.setDdoCode(level1Ddocode);
								EkuberRecord.setSevarthId("NA");
								EkuberRecord.setBenefName(ddoName.trim());
								EkuberRecord.setAccNo(ddoAccNo);
								EkuberRecord.setIfscCode(ddoIFSCode);
								EkuberRecord.setAmount(String.valueOf(ddoNetAmt));
								EkuberRecord.setPaymentRefNo(newPayRefNo);
								EkuberRecord.setPayBydate(payByDate);
								EkuberRecord.setSchemeCode(schemeCode);
								EkuberRecord.setDdoBillNo(payBillGrpId);
								EkuberRecord.setBillDate(billDateStr);
								EkuberRecord.setNarration("Salary");
								EkuberRecord.setNoOfPayees(String.valueOf(noOfPayees));
								EkuberRecord.setBillNetAmt(billNetAmount);
								EkuberRecord.setEkuberFlag("Y");
								EkuberRecord.setFinancialYear(finYear);
								EkuberRecord.setEmployeeType("DDO");
								EkuberRecord.setReptDdoCode(ddoCode);
								EkuberRecord.setToDdoCode(toDdoCode);
								EkuberRecord.setCreatedDate(currentDateTime);
								ekuberPayRepo.save(EkuberRecord);

								HrPayEkuberRecordMstDtls EkuberRecordDtls = new HrPayEkuberRecordMstDtls();

								EkuberRecordDtls.setAuthNo(authNo);
								EkuberRecordDtls.setBulkUploadFlag(bulkUploadFlag);
								EkuberRecordDtls.setBillId(billIdentifier);
								EkuberRecordDtls.setTreasuryCode(treasuryCode);
								EkuberRecordDtls.setDdoCode(level1Ddocode);
								EkuberRecordDtls.setSevarthId("NA");
								EkuberRecordDtls.setBenefName(ddoName.trim());
								EkuberRecordDtls.setAccNo(ddoAccNo);
								EkuberRecordDtls.setIfscCode(ddoIFSCode);
								EkuberRecordDtls.setAmount(String.valueOf(ddoNetAmt));
								EkuberRecordDtls.setPaymentRefNo(newPayRefNo);
								EkuberRecordDtls.setPayBydate(payByDate);
								EkuberRecordDtls.setSchemeCode(schemeCode);
								EkuberRecordDtls.setDdoBillNo(payBillGrpId);
								EkuberRecordDtls.setBillDate(billDateStr);
								EkuberRecordDtls.setNarration("Salary");
								EkuberRecordDtls.setNoOfPayees(String.valueOf(noOfPayees));
								EkuberRecordDtls.setBillNetAmt(billNetAmount);
								EkuberRecordDtls.setEkuberFlag("Y");
								EkuberRecordDtls.setFinancialYear(finYear);
								EkuberRecordDtls.setEmployeeType("DDO");
								EkuberRecordDtls.setReptDdoCode(ddoCode);
								EkuberRecordDtls.setToDdoCode(toDdoCode);
								EkuberRecordDtls.setCreatedDate(currentDateTime);
								ekuberPayRepo.save(EkuberRecordDtls);

								try {
									m = new LinkedHashMap(6);
									m.put("Department_ID", newPayRefNo);
									m.put("Sevaarth_PPO_ID", "NA");
									m.put("Net_Amount", ddoNetAmountD);
									m.put("Payee_Name", ddoName.trim());
									m.put("Account_Number", ddoAccNo);
									m.put("IFSC_Code", ddoIFSCode);

									JSONArray1.add(m);

								} catch (Exception e) {
									log.error("error while inserting " + e);
									e.printStackTrace();
								}

							}

						}

					}

					if (npsAmount > 0 && (paybillYear > 2018 || (paybillYear == 2018 && paybillMonth >= 3))) {
						List cafoDtlsList = ekuberPayRepo.getCAFODtls(level1Ddocode);
						final Iterator cafoDtlsIterator = cafoDtlsList.iterator();

						while (cafoDtlsIterator.hasNext()) {

							objCafoDtls = (Object[]) cafoDtlsIterator.next();
							cafoName = (objCafoDtls[0] != null ? objCafoDtls[0] : "").toString();
							cafoName = cafoName.replaceAll("[!@#$%^&*_=+\\\\\\[\\]{};?<>]", "");
							cafoAccNo = (objCafoDtls[1] != null ? objCafoDtls[1] : "").toString();
							cafoIFSCode = (objCafoDtls[2] != null ? objCafoDtls[2] : "").toString();
							cafoEmail = (objCafoDtls[3] != null ? objCafoDtls[3] : "").toString();
							cafoContact = (objCafoDtls[4] != null ? objCafoDtls[4] : "").toString();
							cafoDdoCode = (objCafoDtls[5] != null ? objCafoDtls[5] : "").toString();

							Long payrefNo = ekuberPayRepo.getNxtSequenceNo(treasuryCode, noOfPayees);

							String newPayRefNo = treasuryCode + payrefNo;

							if (cafoName.length() > 50) {
								cafoName = cafoName.substring(0, 50);
							} else {
								cafoName = cafoName;
							}
							HrPayEkuberRecordMst EkuberRecord = new HrPayEkuberRecordMst();

							double npsAmountD = npsAmount.doubleValue();
							EkuberRecord.setAuthNo(authNo);
							EkuberRecord.setBulkUploadFlag(bulkUploadFlag);
							EkuberRecord.setBillId(billIdentifier);
							EkuberRecord.setTreasuryCode(treasuryCode);
							EkuberRecord.setDdoCode(cafoDdoCode);
							EkuberRecord.setSevarthId("NA");
							EkuberRecord.setBenefName(cafoName.trim());
							EkuberRecord.setAccNo(cafoAccNo);
							EkuberRecord.setIfscCode(cafoIFSCode);
							EkuberRecord.setAmount(String.valueOf(npsAmount));
							EkuberRecord.setPaymentRefNo(newPayRefNo);
							EkuberRecord.setPayBydate(payByDate);
							EkuberRecord.setSchemeCode(schemeCode);
							EkuberRecord.setDdoBillNo(payBillGrpId);
							EkuberRecord.setBillDate(billDateStr);
							EkuberRecord.setNarration("Salary");
							EkuberRecord.setNoOfPayees(String.valueOf(noOfPayees));
							EkuberRecord.setBillNetAmt(billNetAmount);
							EkuberRecord.setEkuberFlag("Y");
							EkuberRecord.setFinancialYear(finYear);
							EkuberRecord.setEmployeeType("CAFO");
							EkuberRecord.setReptDdoCode(ddoCode);
							EkuberRecord.setToDdoCode(toDdoCode);
							EkuberRecord.setCreatedDate(currentDateTime);
							ekuberPayRepo.save(EkuberRecord);

							HrPayEkuberRecordMstDtls EkuberRecordDtls = new HrPayEkuberRecordMstDtls();

							EkuberRecordDtls.setAuthNo(authNo);
							EkuberRecordDtls.setBulkUploadFlag(bulkUploadFlag);
							EkuberRecordDtls.setBillId(billIdentifier);
							EkuberRecordDtls.setTreasuryCode(treasuryCode);
							EkuberRecordDtls.setDdoCode(cafoDdoCode);
							EkuberRecordDtls.setSevarthId("NA");
							EkuberRecordDtls.setBenefName(cafoName.trim());
							EkuberRecordDtls.setAccNo(cafoAccNo);
							EkuberRecordDtls.setIfscCode(cafoIFSCode);
							EkuberRecordDtls.setAmount(String.valueOf(npsAmount));
							EkuberRecordDtls.setPaymentRefNo(newPayRefNo);
							EkuberRecordDtls.setPayBydate(payByDate);
							EkuberRecordDtls.setSchemeCode(schemeCode);
							EkuberRecordDtls.setDdoBillNo(payBillGrpId);
							EkuberRecordDtls.setBillDate(billDateStr);
							EkuberRecordDtls.setNarration("Salary");
							EkuberRecordDtls.setNoOfPayees(String.valueOf(noOfPayees));
							EkuberRecordDtls.setBillNetAmt(billNetAmount);
							EkuberRecordDtls.setEkuberFlag("Y");
							EkuberRecordDtls.setFinancialYear(finYear);
							EkuberRecordDtls.setEmployeeType("CAFO");
							EkuberRecordDtls.setReptDdoCode(ddoCode);
							EkuberRecordDtls.setToDdoCode(toDdoCode);
							EkuberRecordDtls.setCreatedDate(currentDateTime);
							ekuberPayRepo.save(EkuberRecordDtls);

							try {

								m = new LinkedHashMap(6);
								m.put("Department_ID", newPayRefNo);
								m.put("Sevaarth_PPO_ID", "NA");
								m.put("Net_Amount", npsAmountD);
								m.put("Payee_Name", cafoName.trim());
								m.put("Account_Number", cafoAccNo);
								m.put("IFSC_Code", cafoIFSCode);

								JSONArray1.add(m);

							} catch (Exception e) {
								log.error("erro" + "r while inserting " + e);
								e.printStackTrace();
							}
						}
					}
				}
			}

			list1.put("Payee_Details", JSONArray1);

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			ekuberPayRepo.updateCMPFlag(authNo, "EK", dateFormat.format(date));

			String sha1 = "";
			billNetAmountD = Double.parseDouble(billNetAmount.trim());
			String Res = authNo.trim() + noOfPayees + billNetAmount.trim();
			log.info("Result---" + Res);

			try {
				MessageDigest digest = MessageDigest.getInstance("SHA-1");
				digest.reset();
				digest.update(Res.getBytes("utf8"));

				sha1 = org.apache.commons.codec.digest.DigestUtils.shaHex(Res);
			} catch (Exception e) {
				e.printStackTrace();
			}

			m = new LinkedHashMap(3);
			m.put("Total_Beneficiaries", noOfPayees);
			m.put("Total_Net_Amount", billNetAmountD);

			m.put("Hash", sha1);
			JSONArray2.add(m);
			list1.put("Validation", JSONArray2);

			listFinal.put("json_data", list1);

			OutputStream lOutStream = null;
			String encrypted = "";
			byte[] allBytesInBlob = null;
			try {
				lOutStream = response.getOutputStream();
				response.setContentType("text/plain;charset=UTF-8");
				response.addHeader("Content-disposition", "attachment; filename=" + authNo.trim() + ".txt");
				response.setCharacterEncoding("UTF-8");

				String key = "ZWt1YmVyYnVsa3Bh";
				String initVector = "1234567890123456";

				encrypted = encrypt(key, initVector, listFinal.toString());

				allBytesInBlob = encrypted.getBytes();

				lOutStream.write(allBytesInBlob);
				lOutStream.flush();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (lOutStream != null) {
					try {
						lOutStream.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

			/*
			 * String OUTPUT_ZIP_FILE = null; String OUTPUT_ZIP_Contri_FILE = null; if
			 * (System.getProperty("os.name").toLowerCase().split(" ")[0].equals("windows"))
			 * { OUTPUT_ZIP_Contri_FILE =
			 * integrationBundleConst.getString("EKuber.OUTPUT_FILE"); } else {
			 * OUTPUT_ZIP_Contri_FILE =
			 * integrationBundleConst.getString("EKuber.OUTPUT_FILE_SERVER"); }
			 */
			
			
			// Get Image start
			String key = "";
			String Path = "";
			String strOSName = System.getProperty("os.name");
			boolean test = strOSName.contains("Windows");
			if (strOSName.contains("Windows")) {
				key = "ekuberWindowsPath";
			} else {
				key = "ekuberLinuxPath";
			}
			Path = environment.getRequiredProperty(key);

			//String Path = OUTPUT_ZIP_Contri_FILE;

			String directoryName = Path.concat(authNo.toString());
			File directory = new File(directoryName);
			directory = new File(directoryName);
			if (!directory.exists()) {
				directory.mkdir();
			}

			String filePath = directoryName + "/" + authNo.concat(".json");
			String Encryptedfile = directoryName + "/" + authNo.concat(".txt");

			try (FileWriter file = new FileWriter(filePath)) {
			//	file.write(listFinal.toJSONString());   old 
				file.write(listFinal.toString());
				file.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try (FileWriter file = new FileWriter(Encryptedfile)) {
				file.write(encrypted);
				file.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			log.info("File successfully encrypted!");
			try {
				lOutStream = response.getOutputStream();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.setContentType("text/plain;charset=UTF-8");

			response.addHeader("Content-disposition", "attachment; filename=" + authNo.trim() + ".txt");

			response.setCharacterEncoding("UTF-8");

			try {
				lOutStream.write(allBytesInBlob);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				lOutStream.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			log.info("File successfully encrypted!");

			log.info("File successfully encrypted!");

		}

     return null;		
		
	}

	@Override
	public byte[] getExcelReportPrintFormat(Map<String, Object> inputParam, OrgUserMst orgUserMst, HttpServletResponse response) {
	    String consId = (String) inputParam.get("consId");
	    String schemeCode = (String) inputParam.get("schemeCode");
	    String authNo = (String) inputParam.get("authNo");

	    // Default empty values if parameters are null
	    consId = consId != null ? consId : "";
	    schemeCode = schemeCode != null ? schemeCode : "";
	    authNo = authNo != null ? authNo : "";

	    byte[] excelBytes = null;

	    try {
	        // Fetch required data from repositories
	        List<Object[]> ekuberDataForExcel = ekuberPayRepo.getEkuberDataForExcel(authNo);
	        List<Object[]> toDdoCodeAndNoOfPayees = ekuberPayRepo.getToDdoCodeAndNoOfPayees(authNo);

	        // Extract DDO code and number of payees from the retrieved data
	        String toDdoCode = "-";
	        String noOfPayees = "-";
	        if (toDdoCodeAndNoOfPayees != null && !toDdoCodeAndNoOfPayees.isEmpty()) {
	            Object[] row = toDdoCodeAndNoOfPayees.get(0);
	            toDdoCode = row.length > 1 ? (row[0] != null ? row[0].toString() : "") : "-";
	            noOfPayees = row.length > 1 ? (row[1] != null ? row[1].toString() : "") : "-";
	        }

	        // Calculate total amount from the data
	        BigDecimal totalAmount = BigDecimal.ZERO;
	        for (Object[] row : ekuberDataForExcel) {
	            Object objNetAmount = row[6];
	            if (objNetAmount != null) {
	                try {
	                    BigDecimal netAmount = (objNetAmount instanceof BigDecimal) ?
	                            (BigDecimal) objNetAmount : new BigDecimal(objNetAmount.toString());
	                    totalAmount = totalAmount.add(netAmount);
	                } catch (NumberFormatException e) {
	                    log.error("Failed to parse net amount: " + objNetAmount, e);
	                }
	            }
	        }

	        // Handle null or empty values in the dataset
	        for (Object[] row : ekuberDataForExcel) {
	            for (int j = 0; j < row.length; j++) {
	                if (row[j] == null || (row[j] instanceof String && ((String) row[j]).isEmpty())) {
	                    row[j] = "-";
	                }
	            }
	        }

	        // Add serial numbers to the data
	        List<List<Object>> dataWithSerial = new ArrayList<>();
	        for (int i = 0; i < ekuberDataForExcel.size(); i++) {
	            Object[] row = ekuberDataForExcel.get(i);
	            List<Object> rowWithSerial = new ArrayList<>(row.length + 1);
	            rowWithSerial.add(i + 1);
	            rowWithSerial.addAll(Arrays.asList(row));
	            dataWithSerial.add(rowWithSerial);
	        }

	        // Add the total amount row at the end
	        List<Object> totalAmountRow = new ArrayList<>(Collections.nCopies(6, ""));
	        totalAmountRow.add("Total Amount");
	        totalAmountRow.add(totalAmount);
	        dataWithSerial.add(totalAmountRow);

	        // Define column headers and formatting
	        ColumnVo[] excelColumns = new ColumnVo[]{
	            new ColumnVo("SR NO.", 2, 10, 0, false, true),
	            new ColumnVo("BENEFICIARY TYPE", 2, 23, 0, false, true),
	            new ColumnVo("DDO CODE", 2, 15, 0, false, true),
	            new ColumnVo("BENEFICIARY NAME", 2, 45, 0, false, true),
	            new ColumnVo("SEVARTH ID", 2, 15, 0, false, true),
	            new ColumnVo("ACCOUNT NO", 2, 20, 0, false, true),
	            new ColumnVo("IFSC CODE", 2, 15, 0, false, true),
	            new ColumnVo("NET AMOUNT", 2, 15, 1, false, true)
	        };

	        List<ColumnVo[]> columnValue = new ArrayList<>();
	        columnValue.add(excelColumns);

	        // Preparing report header and footer
	        String lSbHeader = "E-Kuber beneficiary wise report \n" +
	                "Authorisation Number - " + authNo +
	                " | Consolidated ID - " + consId +
	                " | Scheme Code - " + schemeCode +
	                " | DDO Code - " + toDdoCode;

	        List<String> lStrFooter = Arrays.asList(
	                "Note:-The same report shows all beneficiary wise net amounts which should be credited in the mentioned bank account details, " +
	                        "So please verify the same account details and then proceed for the E-Kuber.");

	        // Generate Excel Report
	        ExcelExportHelper excelExportHelper = new ExcelExportHelper();
	        excelBytes = excelExportHelper.getExcelReportPrintFormat(
	                Collections.singletonList(dataWithSerial), Collections.singletonList(columnValue),
	                new String[]{lSbHeader}, new ArrayList<>(), lStrFooter);

	        // Write Excel to response stream
	        String filename = authNo + ".xls";
	        response.setContentType("application/vnd.ms-excel");
	        response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
	        try (ServletOutputStream out = response.getOutputStream()) {
	            out.write(excelBytes);
	            out.flush();
	        }

	        // Add total amount to input parameters for further use if necessary
	        inputParam.put("totalAmount", totalAmount);
	    } catch (Exception e) {
	        log.error("Error occurred while generating Excel report", e);
	        // Handle exceptions more gracefully, possibly by sending a failure response.
	        excelBytes = new byte[0]; // Return empty byte array in case of failure
	    }

	    return excelBytes;
	}

	
	
	public static String encrypt(String key, String iv, String data) {

		try {
			IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));
			SecretKeySpec secretKey = new SecretKeySpec(fixKey(key).getBytes(StandardCharsets.UTF_8), "AES");

			Cipher cipher = Cipher.getInstance(CIPHER_NAME);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);

			byte[] encryptedData = cipher.doFinal((data.getBytes()));

			String encryptedDataInBase64 = Base64.getEncoder().encodeToString(encryptedData);
			String ivInBase64 = Base64.getEncoder().encodeToString(iv.getBytes(StandardCharsets.UTF_8));

			return encryptedDataInBase64;

		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	private static String fixKey(String key) {

		if (key.length() < CIPHER_KEY_LEN) {
			int numPad = CIPHER_KEY_LEN - key.length();

			for (int i = 0; i < numPad; i++) {
				key += "0"; // 0 pad to len 16 bytes
			}

			return key;

		}

		if (key.length() > CIPHER_KEY_LEN) {
			return key.substring(0, CIPHER_KEY_LEN); // truncate to 16 bytes
		}

		return key;
	}

	public static String decrypt(String key, String data) {

		try {

			String[] parts = data.split("/");

			IvParameterSpec iv = new IvParameterSpec(Base64.getDecoder().decode(parts[1]));
			SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");

			Cipher cipher = Cipher.getInstance(CIPHER_NAME);
			cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);

			byte[] decodedEncryptedData = Base64.getDecoder().decode(parts[0]);

			byte[] original = cipher.doFinal(decodedEncryptedData);

			return new String(original);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	private static SecretKeySpec getKey() {
		SecretKeySpec key = null;
		try {
			File keyFile = new File("F:/EKuber/encryptionkey.key");
			FileInputStream stream = new FileInputStream(keyFile);
			byte[] bytesArray = new byte[(int) keyFile.length()];
			stream.read(bytesArray);
			stream.close();
			key = new SecretKeySpec(bytesArray, "AES");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return key;
	}

	// Additional COde
	public static byte[] encrypt(byte[] plaintext, SecretKey key, byte[] IV) throws Exception {
		// Get Cipher Instance
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

		SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "AES");

		// Create IvParameterSpec
		IvParameterSpec ivSpec = new IvParameterSpec(IV);

		// Initialize Cipher for ENCRYPT_MODE
		cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

		// Perform Encryption
		byte[] cipherText = cipher.doFinal(plaintext);

		System.out.println("decripted" + cipherText);
		return cipherText;
	}

	private String insertExtraSpace(int startlenth, int endLength) {
		String whiteSpace = "";
		for (int i = startlenth; i < endLength; i++) {
			whiteSpace += " ";
		}
		return whiteSpace;
	}

	public static String decrypt(String algorithm, String cipherText, SecretKey key, IvParameterSpec iv)
			throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
			InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, key, iv);
		byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(cipherText));
		return new String(plainText);
	}

	

}
