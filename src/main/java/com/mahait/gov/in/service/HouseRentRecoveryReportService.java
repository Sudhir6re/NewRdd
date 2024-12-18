package com.mahait.gov.in.service;

import java.util.List;

import com.mahait.gov.in.model.RegularReportModel;

public interface HouseRentRecoveryReportService {

	List<RegularReportModel> findHRRDetails(Integer yearId, Integer monthId, Long billGroup, String ddoCode);

}
