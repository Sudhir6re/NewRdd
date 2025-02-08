package com.mahait.gov.in.service;

import java.util.Map;

import com.mahait.gov.in.entity.OrgUserMst;

import jakarta.servlet.http.HttpServletResponse;

public interface EkuberPayService {

	String generateEkuberFile(String authNo, OrgUserMst orgUserMst, HttpServletResponse httpResponse);


	byte[] getExcelReportPrintFormat(Map<String, Object> inputParam, OrgUserMst orgUserMst,
			HttpServletResponse response);

}
