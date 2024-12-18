package com.mahait.gov.in.service;

import java.util.List;

import com.mahait.gov.in.model.RegularReportModel;

public interface GPFAbstractReportService {

	List<RegularReportModel> findgpfRptDtls(Integer monthId, Integer yearId, Long billGroup);

}
