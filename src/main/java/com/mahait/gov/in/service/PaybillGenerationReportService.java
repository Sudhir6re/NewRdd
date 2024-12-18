package com.mahait.gov.in.service;

import java.util.List;

import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.PaybillGenerationReportModel;

public interface PaybillGenerationReportService {

	List<PaybillGenerationReportModel> findRegionWiseData(PaybillGenerationReportModel paybillGenerationReportModel,
			OrgUserMst messages);

}
