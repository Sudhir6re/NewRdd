package com.mahait.gov.in.repository;

import java.util.List;

public interface AquittanceRollReportRepo {

	List<Object[]> findAquittanceReportDtls(int monthName, int yearName, String ddoCode, Long billNumber);

}
