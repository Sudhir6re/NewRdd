package com.mahait.gov.in.repository;

import java.util.List;

public interface GISReportRepo {

	List<Object[]> findRptDtls(Integer monthId, Integer yearId, Long billGroup, Long gisAllowDeducCode);

}
