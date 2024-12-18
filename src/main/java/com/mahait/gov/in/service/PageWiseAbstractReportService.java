package com.mahait.gov.in.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.mahait.gov.in.model.DisplayPageWiseAbstractReportModel;

public interface PageWiseAbstractReportService {

	Page<DisplayPageWiseAbstractReportModel> findPaginated(PageRequest of, Long billNumber, String ddoCode);

}
