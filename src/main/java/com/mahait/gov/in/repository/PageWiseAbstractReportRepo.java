package com.mahait.gov.in.repository;

import java.util.List;
import java.util.Map;

import com.mahait.gov.in.model.DisplayPageWiseAbstractReportModel;

public interface PageWiseAbstractReportRepo {

	List<DisplayPageWiseAbstractReportModel> getAllDataForinnernew(String strddo, Long billNumber);

	List<Map<String, Object>> getempDetails(String billNo);

}
