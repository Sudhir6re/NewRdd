package com.mahait.gov.in.repository;

import java.util.List;

public interface RevenueStampReportRepo {

	List<Object[]> findRevenueStampEmpDtls(int monthName, int yearName, String ddoCode, Long billNumber);

	List<Object[]> getddoLvl2byddo(String ddoCode);

}
