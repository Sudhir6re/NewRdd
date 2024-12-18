package com.mahait.gov.in.service;

import java.util.List;

import com.mahait.gov.in.model.AquittanceRollReportModel;

public interface AquittanceRollReportService {

	List<AquittanceRollReportModel> findAquittanceReportDtls(int monthName, int yearName, String ddoCode, Long billNumber);

}
