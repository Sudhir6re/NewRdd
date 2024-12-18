package com.mahait.gov.in.service;

import java.util.List;

import com.mahait.gov.in.model.RevenueStampReportModel;

public interface RevenueStampReportService {

	List<RevenueStampReportModel> revenueStampReportfind(int monthName, int yearName, String string, Long billNumber);

	List<Object[]> getddoLvl2byddo(String ddoCode);


}
