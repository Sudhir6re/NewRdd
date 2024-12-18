package com.mahait.gov.in.service;

import java.util.List;

import com.mahait.gov.in.model.GrossReportModel;


public interface GrossReportService {

	public List<GrossReportModel> lstGetDeptLst();

	public List<GrossReportModel> grossReport(int yearId, long locId);

}
