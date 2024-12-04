package com.mahait.gov.in.common;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JsonResponseHelper {
	
	private JsonResponseHelper() {
		
	}
	
	/*private static final String SUCCESS = "SUCCESS";
	private static final String FAIL = "FAIL";
	private static final String OTP_FAIL = "INVALID OTP";
	private static final String RESET_SUCCESS = "PASSWORD RESET SUCCESSFUL";
	private static final String RESET_FAIL = "PASSWORD RESET FAIL";
	private static final String VERIFY_OTP_SUCCESS = "OTP VERIFIED SUCCESSFULLY";
	private static final String VERIFY_OTP_FAIL = "ENTERED OTP IS INVALID";
	private static final String NEW_REG_SUCCESS = "REGISTRATION SUCCESSFUL";
	private static final String NEW_REG_FAIL= "REGISTRATION FAILED";
	private static final String PAY_FAIL = "PAYMENT NOT POSSIBLE AS OUTSTANDING IS NOT PAYABLE";
	private static final String OTP_SUCCESS = "OTP SUCCESSFULLY SENT TO MOBILE NO AND EMAILID";
	private static final String OTP_FAIL_FOR_MOBILE = "MOBILE NO/USERNAME ALREADY REGISTERED";
	private static final String MOBILE_NUMBER_FAIL = "MOBILE NUMBER IS NOT REGISTERED";
	private static final String SUCCESS_FOR_SAVE_RENEW_DATA = "DATA SAVED SUCCESSFUL";
	private static final String NO_DATA_FOR_LICENSE = "Data not found for the provided license number";
	private static final String SUCCESS_FOR_SAVE_PASS = "PASSWORD IS SUCCESSFULLY SET";
	private static final String FAIL_FOR_SAVE_PASS = "PASSWORD IS NOT SET";
	private static final String FAIL_FOR_UPDATE_PROFILE = "PROFILE CANNOT BE UPDATED";
	private static final String SUCCESS_FOR_UPDATE_PROFILE = "PROFILE UPDATED SUCCESSFULLY";
	private static final String SUCCESS_FOR_SAVE_FCMID = "FCMID UPDATED SUCCESSFULLY";
	private static final String FAIL_FOR_SAVE_FCMID = "FCMID CANNOT BE UPDATED";
	private static final String FAIL_CATCH_EXCEPTION = "SOMETHING WENT WRONG,PLEASE TRY AGAIN AFTER SOME TIME.............";
	private static final String SUCCESS_FOR_ACK = "ACKNOWLEDGEMENT GENERATED SUCCESSFULLY";
	private static final String SUCCESS_APP_SUBMIT = "APPLICATION SUBMITTED SUCCESSFULLY";
	private static final String FAIL_NO_FCMIDID = "PLEASE PROVIDE FCMIDID";
	private static final String FAIL_USER_NOT_FOUND="PLEASE PROVIDE USER lOGIN NAME";
	private static final String FAIL_EMAIL_ID_NOT_FOUND="PLEASE PROVIDE EMAIL ID";
	private static final String FAIL_NO_PASSWORD = "PLEASE PROVIDE PASSWORD";
	private static final String FAIL_NO_REQUEST_TYPE = "PLEASE PROVIDE REQUEST TYPE";
	private static final String FAIL_NO_RECEIPT = "RECEIPT NOT GENERATED"; */
	public static  final  String NO_DATA_FOUND = "Data Not Found";
	public static final String FAILED_SAVING = "Failed to Store Data";
	public static  final  String NO_DATA_FOUND_SRN_ID = "Data Not Found For Given Srn_Id";
	public static  final  String ERROR_WHILE_PROCESSING = "Error While Processing !!!   ";
	public static final String ERROR_IN_UPDATE = "Update Fail !!";
	public static final String UPDATE_SUCESS = "Update Successfully";
	public static final String SUCCESS_ADDED = "Successfully Added";
	public static final String ADD_FAIL = "Fail To Add";
	public static  final  String DATA_EXIST = "Data Already Exists";
	public static  final  String EMAIL_ID_MISSING = "Email Id Not Found!";
	
	public static String getJSONResponseString(String jsonVal) {
		Gson gson = new Gson();
		ArrayList<Object> list = gson.fromJson(jsonVal, new TypeToken<List<Object>>(){}.getType());
		JsonResponseBean jr = new JsonResponseBean();
		jr.setTotalRows(list.size());
		jr.setResultData(list);	
		return gson.toJson(jr);
	}
	/*public static String getListJSONResponseString(List<Object> list) {
		Gson gson = new Gson();
		String str = gson.toJson(list);
		return getJSONResponseString(str);
	}
	public static String getCountResponseString(int value) {
		JsonResponseBean jr = new JsonResponseBean();
		Gson gson = new Gson();
		jr.setMessage(SUCCESS);
		jr.setTotalRows(value);
		return gson.toJson(jr);
	}
	public static String getSucessResponseString(int value) {
		JsonResponseBean jr = new JsonResponseBean();
		Gson gson = new Gson();
		if(value > 0) {
			jr.setMessage(SUCCESS);
		}else {
			jr.setMessage(FAIL);
		}
		return gson.toJson(jr);
	}
	 
	public static String getFailuerResponseString() {
		JsonResponseBean jr = new JsonResponseBean();
		Gson gson = new Gson();
		jr.setMessage(FAIL);
		return gson.toJson(jr);
	}
	
	public static String getSucessResponseString() {
		JsonResponseBean jr = new JsonResponseBean();
		Gson gson = new Gson();
	    jr.setMessage(SUCCESS);
		return gson.toJson(jr);
	}
	
	public static String getSucessResponseForPassString() {
		JsonResponseBean jr = new JsonResponseBean();
		Gson gson = new Gson();
	    jr.setMessage(RESET_SUCCESS);
		return gson.toJson(jr);
	}
	
	public static String getFailuerResponsePassString() {
		JsonResponseBean jr = new JsonResponseBean();
		Gson gson = new Gson();
		jr.setMessage(RESET_FAIL);
		return gson.toJson(jr);
	}
	
	public static String getFailuerResponseForOtpString() {
		JsonResponseBean jr = new JsonResponseBean();
		Gson gson = new Gson();
		jr.setMessage(OTP_FAIL);
		return gson.toJson(jr);
	}
	
	public static String getSucessResponseVerifyOtpString() {
		JsonResponseBean jr = new JsonResponseBean();
		Gson gson = new Gson();
	    jr.setMessage(VERIFY_OTP_SUCCESS);
		return gson.toJson(jr);
	}
	
	public static String getFailuerResponseVerifyOtpString() {
		JsonResponseBean jr = new JsonResponseBean();
		Gson gson = new Gson();
		jr.setMessage(VERIFY_OTP_FAIL);
		return gson.toJson(jr);
	}
	
	public static String getSucessResponseNewUserString(int userId) {
		JsonNewRegBean jr = new JsonNewRegBean();
		Gson gson = new Gson();
		jr.setUserId(userId);
		jr.setMessage(NEW_REG_SUCCESS);
		return gson.toJson(jr);
	}
	
	public static String getFailuerResponseNewUserString() {
		JsonResponseBean jr = new JsonResponseBean();
		Gson gson = new Gson();
		jr.setMessage(NEW_REG_FAIL);
		return gson.toJson(jr);
	}
	
	public static String getSucessResponseTransIdString(List<String> res) {
		JsonResponseForPaymentBean jr = new JsonResponseForPaymentBean();
		Gson gson = new Gson();
		if(res!=null) {
			jr.setTransId(res.get(0));
			jr.setPaymentId(res.get(1));
			jr.setRandNo(res.get(2));
		}	
		return gson.toJson(jr);
	}
	public static String getFailuerResponsePayString() {
		JsonResponseBean jr = new JsonResponseBean();
		Gson gson = new Gson();
		jr.setMessage(PAY_FAIL);
		return gson.toJson(jr);
	}
	
	public static String getSuccesResponseWaterPayString(long rcptId,String rcptNo,Date rcptDate ) {
		JsonResonsePayResBean jr = new JsonResonsePayResBean();
		Gson gson = new Gson();
		jr.setRecptId(rcptId);
		jr.setRcptNo(rcptNo);
		jr.setRcptDate(rcptDate);
		return gson.toJson(jr);
	}
	
	public static String getSucessResponseForOtpString(int id) {
		JsonResponseForOtp jr = new JsonResponseForOtp();
		Gson gson = new Gson();
		jr.setOtpId(id);
		jr.setMessage(OTP_SUCCESS);
		return gson.toJson(jr);
	}
	
	public static String getFailuerResponseNewUserMobileString() {
		JsonResponseBean jr = new JsonResponseBean();
		Gson gson = new Gson();
		jr.setMessage(OTP_FAIL_FOR_MOBILE);
		return gson.toJson(jr);
	}
	
	public static String getFailuerResponseMobileString() {
		JsonResponseBean jr = new JsonResponseBean();
		Gson gson = new Gson();
		jr.setMessage(MOBILE_NUMBER_FAIL);
		return gson.toJson(jr);
	}
	
	public static String getSucessResponseForRenewChrgeString(Double charge,int taxId,String taxCode,int taxDetId) {
		JsonRenewalChargeBean jr = new JsonRenewalChargeBean();
		Gson gson = new Gson();
		jr.setCharges(charge);
		jr.setTaxId(taxId);
		jr.setTaxCode(taxCode);
		jr.setTaxDetId(taxDetId);
		return gson.toJson(jr);
	}
	
	public static String getSucessResponseForSaveRenewDataString(String str) {
		JsonSaveRenewDataBean jr = new JsonSaveRenewDataBean();
		Gson gson = new Gson();
		if(str.equalsIgnoreCase(NO_DATA_FOR_LICENSE)) {
			jr.setMsg(str);
		}else {
			jr.setRequestId(str);
			jr.setMsg(SUCCESS_FOR_SAVE_RENEW_DATA);
		}
		
		return gson.toJson(jr);
	}
	
	public static String getSucessResponseForSavePasswordString() {
		JsonCommonMsgBean jr = new JsonCommonMsgBean();
		Gson gson = new Gson();
		jr.setMessage(SUCCESS_FOR_SAVE_PASS);
		return gson.toJson(jr);
	}
	
	public static String getFailureResponseForSavePasswordString() {
		JsonCommonMsgBean jr = new JsonCommonMsgBean();
		Gson gson = new Gson();
		jr.setMessage(FAIL_FOR_SAVE_PASS);
		return gson.toJson(jr);
	}
	
	public static String getFailureResponseForMobileString() {
		JsonResponseBean jr = new JsonResponseBean();
		Gson gson = new Gson();
	    jr.setMessage(MOBILE_NUMBER_FAIL);
		return gson.toJson(jr);
	}
	
	public static String getFailuerResponseForProfileString() {
		JsonResponseBean jr = new JsonResponseBean();
		Gson gson = new Gson();
		jr.setMessage(FAIL_FOR_UPDATE_PROFILE);		
		return gson.toJson(jr);
	}
	
	public static String getSucessResponseForProfileString() {
		JsonResponseBean jr = new JsonResponseBean();
		Gson gson = new Gson();
	    jr.setMessage(SUCCESS_FOR_UPDATE_PROFILE);
		return gson.toJson(jr);
	}
	
	public static String getResponseForDocumentString(String msg) {
		JsonResponseBean jr = new JsonResponseBean();
		Gson gson = new Gson();
	    jr.setMessage(msg);
		return gson.toJson(jr);
	}
	
	public static String getSucessResponseSaveFcmidString(String notId) {
		JsonSaveFcmidBean jr = new JsonSaveFcmidBean();
		Gson gson = new Gson();
		jr.setId(notId);
		jr.setMsg(SUCCESS_FOR_SAVE_FCMID);
		return gson.toJson(jr);
	}
	
	public static String getResponseForDocumentString() {
		JsonResponseBean jr = new JsonResponseBean();
		Gson gson = new Gson();
	    jr.setMessage(FAIL_FOR_SAVE_FCMID);
		return gson.toJson(jr);
	}
	
	public static String getFailuerResponseForNotificationString(String msg) {
		JsonCommonMsgBean jr = new JsonCommonMsgBean();
		Gson gson = new Gson();
		jr.setMessage(msg);
		return gson.toJson(jr);
	}
	
	public static String getListJSONResponseStringWithStatus(String status, String jsonVal) {
		Gson gson = new Gson();
		ArrayList<Object> list = null;
		JsonResponseBean jr = new JsonResponseBean();
		if(jsonVal != null) {
			list = gson.fromJson(jsonVal, new TypeToken<List<Object>>(){}.getType());
			jr.setResultData(list);
			jr.setTotalRows(list.size());
			jr.setMessage("");
		}
		else {
			jr.setResultData(list);
			jr.setTotalRows(0);	
			jr.setMessage(NO_DATA_FOUND);
		}
		jr.setStatus(status);
		return gson.toJson(jr);		
	}
	
	public static String getSuccessResponseForSaveLicString(String flag, long l) {
		JsonMssgeFlagBean jr = new JsonMssgeFlagBean();
		Gson gson = new Gson();
		jr.setMessage(SUCCESS_FOR_SAVE_RENEW_DATA);
		jr.setFlag(flag);
		jr.setLicRequestId(l);
		return gson.toJson(jr);
	}
	
	public static String getSucessResponseForNewLicenseChrgeString(Double charge,int taxId,String taxCode, int taxDetId, String uomCategory,String units,String slabType) {
		JsonRenewalChargeBean jr = new JsonRenewalChargeBean();
		Gson gson = new Gson();
		jr.setCharges(charge);
		jr.setTaxId(taxId);
		jr.setTaxCode(taxCode);
		jr.setTaxDetId(taxDetId);
		jr.setUomCategory(uomCategory);
		jr.setUnits(units);
		jr.setSlabType(slabType);
		return gson.toJson(jr);
	}	
	
	public static String getFailureResponseForCatchString(String resultCode) {
		JsonResponseBean jr = new JsonResponseBean();
		Gson gson = new Gson();
		jr.setMessage(FAIL_CATCH_EXCEPTION);
		jr.setResultCode(resultCode);
		return gson.toJson(jr);
	}
	
	public static String getSuccessResponseForSaveLicDetString(long licRequestId) {
		JsonMssgeFlagBean jr = new JsonMssgeFlagBean();
		Gson gson = new Gson();
		jr.setLicRequestId(licRequestId);
		jr.setMessage(SUCCESS_FOR_SAVE_RENEW_DATA);
		return gson.toJson(jr);
	}
	
	public static String getSuccessResponseForAckGenString(String srNumber) {
		JsonAckGenerationBean jr = new JsonAckGenerationBean();
		Gson gson = new Gson();
		jr.setAckNumber(srNumber);
		jr.setMessage(SUCCESS_FOR_ACK);
		return gson.toJson(jr);
	}
	
	public static String getSuccessResponseForSaveCompString(String licenseId) {
		JsonCommonMsgBean jr = new JsonCommonMsgBean();
		Gson gson = new Gson();
		jr.setMessage(SUCCESS_APP_SUBMIT);
		return gson.toJson(jr);
	}
	
	public static String getSuccessResponseForWMCString(String transId,String paymentId) {
		JsonResponseForPaymentBean jr = new JsonResponseForPaymentBean();
		Gson gson = new Gson();
		jr.setTransId(transId);
		jr.setPaymentId(paymentId);
		return gson.toJson(jr);
	}
	
	public static String getFailuerResponseFcmidIdString() {
		JsonResponseBean jr = new JsonResponseBean();
		Gson gson = new Gson();
		jr.setMessage(FAIL_NO_FCMIDID);
		return gson.toJson(jr);
	}
	
	public static String getFailuerResponseNoUserString() {
		JsonResponseBean jr = new JsonResponseBean();
		Gson gson = new Gson();
		jr.setMessage(FAIL_USER_NOT_FOUND);
		return gson.toJson(jr);
	}
	
	public static String getFailuerResponseNoEmailString() {
		JsonResponseBean jr = new JsonResponseBean();
		Gson gson = new Gson();
		jr.setMessage(FAIL_EMAIL_ID_NOT_FOUND);
		return gson.toJson(jr);
	}	
	
	public static String getJSONResponseStringWithStatus(String status, String jsonVal,String msg) 
	{
		Gson gson = new Gson();
		Object obj = null;
		JsonResponseBean jr = new JsonResponseBean();
		if(jsonVal != null) {
			obj = gson.fromJson(jsonVal, new TypeToken<Object>(){}.getType());
			jr.setData(obj);	
			jr.setTotalRows(1);
			jr.setMessage(msg);
		}
		else {
			jr.setData(obj);
			jr.setTotalRows(0);
			jr.setMessage(msg);
		}
		jr.setStatus(status);
		return gson.toJson(jr);		
	}
	
	public static String getResponseForNewGenPassVerifyString(String msg, String status,String resultCode) {
		JsonResponseBean jr = new JsonResponseBean();
		Gson gson = new Gson();
		jr.setMessage(msg);
		jr.setStatus(status);
		jr.setResultCode(resultCode);
		return gson.toJson(jr);
	}
	
	public static String getFailureResponseNewGenPassString() {
		JsonResponseBean jr = new JsonResponseBean();
		Gson gson = new Gson();
		jr.setMessage(FAIL_NO_PASSWORD);
		return gson.toJson(jr);
	}
	
	public static String getFailureResponseForReqTypeString() {
		JsonResponseBean jr = new JsonResponseBean();
		Gson gson = new Gson();
		jr.setMessage(FAIL_NO_REQUEST_TYPE);
		return gson.toJson(jr);
	}
	
	public static String getResponseForSaveScrutinyQandA(String msg) {
		JsonResponseBean jr = new JsonResponseBean();
		Gson gson = new Gson();
		if(msg == "4") {
			jr.setMessage(FAILED_SAVING);
			jr.setResultCode(msg);
		}else if(msg == "9") {
			jr.setMessage(ERROR_IN_UPDATE);
			jr.setResultCode(msg);
		} else {
			jr.setMessage(SUCCESS_FOR_SAVE_RENEW_DATA);
			jr.setResultCode(msg);
		}
		return gson.toJson(jr);
	}
	
	public static String getSucessResponseSaveRelation() {
		JsonResponseBean jr = new JsonResponseBean();
		Gson gson = new Gson();
	    jr.setMessage(SUCCESS_ADDED);
		return gson.toJson(jr);
	}
	
	public static String getFailureResponseSaveRelation() {
		JsonResponseBean jr = new JsonResponseBean();
		Gson gson = new Gson();
	    jr.setMessage(ADD_FAIL);
		return gson.toJson(jr);
	}
	
	public static String getDataResponseSaveRelation() {
		JsonResponseBean jr = new JsonResponseBean();
		Gson gson = new Gson();
	    jr.setMessage(DATA_EXIST);
		return gson.toJson(jr);
	}
	
	*//********************************get two array list response*************************************//*
	public static String getJSONTwoListResponse(String status, String resultData,String charges,String msg) 
	{
		Gson gson = new Gson();
		JsonResponseBean jr = new JsonResponseBean();
		if(resultData != null) {
			ArrayList<Object> resultDataList = gson.fromJson(resultData, new TypeToken<List<Object>>(){}.getType());
			jr.setResultData(resultDataList);
		}
		if(resultData != null) {
			ArrayList<Object> chargesList = gson.fromJson(charges, new TypeToken<List<Object>>(){}.getType());
			jr.setCharges(chargesList);
		}
		jr.setMessage(msg);
		jr.setStatus(status);
		return gson.toJson(jr);		
	}
	public static String getDataResponseRelation(String val) {
		JsonResponseBean jr = new JsonResponseBean();
		Gson gson = new Gson();
	    jr.setMessage(val);
		return gson.toJson(jr);
	}
	
	public static String getResponseString(String value) {
		JsonResponseBean jr = new JsonResponseBean();
		Gson gson = new Gson();
		jr.setDataValue(value);
		return gson.toJson(jr);
	}
	
	public static String getResponseStringForNWC(String jsonVal,String jsonVal1) {
		Gson gson = new Gson();
		JsonResponseBean jr = new JsonResponseBean();
		if(!jsonVal.equalsIgnoreCase("Failed to save")) {
			jr.setDataValue(jsonVal);
			jr.setSrnId(jsonVal1);
			jr.setMessage(SUCCESS_FOR_SAVE_RENEW_DATA);	
		}else {
			jr.setMessage(jsonVal);
		}
		
		return gson.toJson(jr);
	}
	
	public static String getResponseStringNoDue(String val,String val1) {
		JsonNoDueCertBean jr = new JsonNoDueCertBean();
		Gson gson = new Gson();
		jr.setCertificateNo(val);
		jr.setCertificateDate(val1);
		return gson.toJson(jr);
	}
	
	public static String getSucessResponseForOtpString() {
		JsonResponseForOtp jr = new JsonResponseForOtp();
		Gson gson = new Gson();
		jr.setMessage(EMAIL_ID_MISSING);
		return gson.toJson(jr);
	}
	
	public static String getResponseForFlatSlab(String value) {
		JsonResponseBean jr = new JsonResponseBean();
		Gson gson = new Gson();
		jr.setDataValue(value);
		return gson.toJson(jr);
	}
	
	public static String getFailureResponseOnPropertyReceipt() {
		JsonResponseBean jr = new JsonResponseBean();
		Gson gson = new Gson();
	    jr.setMessage(FAIL_NO_RECEIPT);
		return gson.toJson(jr);
	}
	public static String setSuccessResponse(Long id, String msg, String status,String resultCode) {
		JsonResponseBean jr = new JsonResponseBean();
		Gson gson = new Gson();
		jr.setId(id);
		jr.setMessage(msg);
		jr.setStatus(status);
		jr.setResultCode(resultCode);
		return gson.toJson(jr);
	}*/
}
