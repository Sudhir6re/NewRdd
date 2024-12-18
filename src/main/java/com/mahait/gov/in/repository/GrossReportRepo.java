package com.mahait.gov.in.repository;

import java.util.List;

public interface GrossReportRepo {

	List<Object[]> lstGetDeptLst();

	List<Object[]> grossReport(int yearId, long locId);

}
