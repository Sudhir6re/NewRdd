package com.mahait.gov.in.controller;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.service.EkuberPayService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/ddo")
@Controller
public class EkuberPayController {

	@Autowired
	EkuberPayService ekuberPayService;

	@RequestMapping("/generateEkuberFile/{authNo}")
	public ResponseEntity<String> generateEkuberFile(@PathVariable String authNo, HttpSession httpSession,HttpServletResponse httpResponse) {
		OrgUserMst orgUserMst = (OrgUserMst) httpSession.getAttribute("MY_SESSION_MESSAGES");
		String response = ekuberPayService.generateEkuberFile(authNo, orgUserMst,httpResponse);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/download")
	public void downloadExcel(HttpServletResponse response, @RequestBody Map<String, Object> inputParam,
			HttpSession httpSession,HttpServletResponse httpResponse) throws IOException {

		OrgUserMst orgUserMst = (OrgUserMst) httpSession.getAttribute("MY_SESSION_MESSAGES");

		byte[] excelBytes = ekuberPayService.getExcelReportPrintFormat(inputParam, orgUserMst,httpResponse);

	}

}
