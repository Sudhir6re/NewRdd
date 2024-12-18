package com.mahait.gov.in.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mahait.gov.in.entity.PaybillGenerationTrnEntity;
import com.mahait.gov.in.model.DisplayInnerReportModel;

public interface DisplayInnerReportService {
	public List<DisplayInnerReportModel> getAllDataForinnernew(Long billNumber,String strddo,int currentPage);
	public List<Map<String,Object>> getempDetails(String bill_no);
	public Page<DisplayInnerReportModel> findPaginated(Pageable pageable,Long billNumber,String strddo);
	Date findbillCreateDate(Long billNumber);
	String getbillDetails(Long billNumber);
	public PaybillGenerationTrnEntity findPayBilldetailByPaybillid(Long billNumber);
}
