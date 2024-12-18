package com.mahait.gov.in.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.mahait.gov.in.model.DisplayBrokenPeriodReportModel;

public interface BrokenPeriodReportService {

	Page<DisplayBrokenPeriodReportModel> findPaginated(PageRequest of, Long billNumber, String ddoCode, Long billno);

	Long findbillsize(int monthName, int yearName, Long billNumber, String ddoCode);

}
