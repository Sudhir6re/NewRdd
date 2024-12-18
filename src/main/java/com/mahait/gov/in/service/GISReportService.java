package com.mahait.gov.in.service;

import java.util.List;

import com.mahait.gov.in.model.RegularReportModel;

public interface GISReportService {

	List<RegularReportModel> findRptDtls(Integer monthId, Integer yearId, Long billGroup,Long gisAllowDeducCode);

}
