package com.mahait.gov.in.service;

import java.util.List;

import com.mahait.gov.in.model.BankStatementReportModel;

public interface BankStatementReportService {

	List<BankStatementReportModel> findbankstatementinfo(int monthName, int yearName, String ddoCode, String billNo);

}
