package com.mahait.gov.in.repository;

import java.util.List;

public interface BankStatementReportRepo {

	List<Object[]> getAbstractReport(int monthName, int yearName, String ddoCode, String billNo);

}
