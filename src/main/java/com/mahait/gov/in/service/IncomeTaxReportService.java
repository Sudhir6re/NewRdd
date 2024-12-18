package com.mahait.gov.in.service;

import java.util.List;

import com.mahait.gov.in.model.RegularReportModel;

public interface IncomeTaxReportService {

	List<RegularReportModel> findIncomeTaxDtls(Integer yearId, Integer monthId, Long billGroup, String ddoCode);

}
