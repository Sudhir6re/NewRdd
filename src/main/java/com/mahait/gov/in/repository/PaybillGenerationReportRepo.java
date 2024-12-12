package com.mahait.gov.in.repository;

import java.util.List;

import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.PaybillGenerationReportModel;

public interface PaybillGenerationReportRepo {

	List<Object[]> findRegionWiseData(PaybillGenerationReportModel paybillGenerationReportModel, OrgUserMst messages);

}
