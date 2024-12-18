package com.mahait.gov.in.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.model.RegularReportModel;
import com.mahait.gov.in.repository.HouseRentRecoveryReportRepo;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class HouseRentRecoveryReportServiceImpl implements HouseRentRecoveryReportService{
	
	@Autowired
	HouseRentRecoveryReportRepo HouseRentRecoveryReportRepo;

	@Override
	public List<RegularReportModel> findHRRDetails(Integer yearId, Integer monthId, Long billGroup, String ddoCode) {

		

		List<Object[]> lstprop = HouseRentRecoveryReportRepo.findHRRDetails(yearId,monthId,billGroup,ddoCode);
		List<RegularReportModel> lstObj = new ArrayList<>();
		
		Double sum=0d;
		if (!lstprop.isEmpty()) {
			for (Object[] objLst : lstprop) {
				RegularReportModel obj = new RegularReportModel();
				/*a.employee_full_name_en,a.pran_no,b.basic_pay,b.dearness_pay,case when a.pay_commission_code =700005 then " + 
						"b.svn_pc_da else da end as DA, b.dcps_regular,nps_empr_deduct,c.created_date
*/				
				obj.setName(StringHelperUtils.isNullString(objLst[0]));
				obj.setPran(StringHelperUtils.isNullString(objLst[1]));
				obj.setBasicpay(StringHelperUtils.isNullDouble(objLst[2]));
				obj.setDp(StringHelperUtils.isNullDouble(objLst[3]));
				obj.setSvnpcda(StringHelperUtils.isNullDouble(objLst[4]));
				obj.setDcpsReg(StringHelperUtils.isNullDouble(objLst[5]));
				obj.setNpsEmployerDedu(StringHelperUtils.isNullDouble(objLst[6]));
				
				lstObj.add(obj);
			}
		}
		return lstObj;
	
	}

}
