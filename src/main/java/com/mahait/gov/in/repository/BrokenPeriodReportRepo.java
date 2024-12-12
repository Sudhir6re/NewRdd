package com.mahait.gov.in.repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.mahait.gov.in.model.DisplayBrokenPeriodReportModel;

public interface BrokenPeriodReportRepo {

	List<DisplayBrokenPeriodReportModel> getAllDataForinnernew(String strddo, Integer month, Integer year,Long billNumber);

	List<Map<String, Object>> getbrokenPeriodDetails(Integer month, Integer year, String strddo, Long billNumber);

	List<Object[]> getbrokenPeriodvalue(BigInteger brokenPeriodId, String sevaarthId);

	Long findbillsize(int monthName, int yearName, Long billNumber, String ddoCode);

}
