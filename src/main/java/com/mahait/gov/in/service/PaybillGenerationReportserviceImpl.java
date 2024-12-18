package com.mahait.gov.in.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.PaybillGenerationReportModel;
import com.mahait.gov.in.repository.PaybillGenerationReportRepo;

@Service
@Transactional
public class PaybillGenerationReportserviceImpl implements PaybillGenerationReportService {
	@Autowired
	PaybillGenerationReportRepo paybillGenerationReportRepo;

	@Override
	public List<PaybillGenerationReportModel> findRegionWiseData(
			PaybillGenerationReportModel paybillGenerationReportModel, OrgUserMst messages) {
		// TODO Auto-generated method stub
		List<Object[]> lst = paybillGenerationReportRepo.findRegionWiseData(paybillGenerationReportModel, messages);
		List<PaybillGenerationReportModel> lstPaybillGenerationReportModel= new ArrayList<>();
		
		for(Object[] object:lst) {
			PaybillGenerationReportModel paybillGenerationReportModel1 = new PaybillGenerationReportModel();
			
			paybillGenerationReportModel1.setTreasuryName(StringHelperUtils.isNullString(object[0]));
			
			lstPaybillGenerationReportModel.add(paybillGenerationReportModel1);
		}
		
		return lstPaybillGenerationReportModel;
	}

}
