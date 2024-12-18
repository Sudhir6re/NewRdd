package com.mahait.gov.in.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.entity.MstDcpsBillGroup;
import com.mahait.gov.in.entity.OrgDdoMst;
import com.mahait.gov.in.model.RegularReportModel;
import com.mahait.gov.in.repository.CommonHomeMethodsRepo;
import com.mahait.gov.in.repository.RegularReportRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RegularReportServiceImpl implements RegularReportService {

	@Autowired
	RegularReportRepo regularReportRepo;

	@Autowired
	CommonHomeMethodsRepo commonHomeMethodsRepo;

	@Override
	public List<OrgDdoMst> getDDOName(String userName) {
		// TODO Auto-generated method stub
		return regularReportRepo.getDDOName(userName);
	}

	@Override
	public List<RegularReportModel> findDCPSRegularEmpLst(Integer yearId, Integer monthId, Long billGroup,
			String ddoCode, Long allowdeducId) {

		List<Object[]> lstprop = regularReportRepo.findDCPSRegularEmpLst(yearId, monthId, billGroup, ddoCode,
				allowdeducId);
		List<RegularReportModel> lstObj = new ArrayList<>();

		Double sum = 0d;
		if (!lstprop.isEmpty()) {
			for (Object[] objLst : lstprop) {
				RegularReportModel obj = new RegularReportModel();
				obj.setName(StringHelperUtils.isNullString(objLst[0]));
				obj.setPran(StringHelperUtils.isNullString(objLst[1]));

				if (objLst[2] instanceof BigInteger) {
					obj.setBasicpay(StringHelperUtils.isNullBigInteger(objLst[2]).doubleValue());
				} else {
					obj.setBasicpay(StringHelperUtils.isNullDouble(objLst[2]));
				}

				if (objLst[3] != null) {
					obj.setDp(StringHelperUtils.isNullDouble(objLst[3]));
				} else {
					obj.setDp(0d);
				}
				if (objLst[4] != null) {
					if (objLst[4] instanceof BigInteger) {
						obj.setSvnpcda(StringHelperUtils.isNullBigInteger(objLst[4]).doubleValue());
					} else {
						obj.setSvnpcda(StringHelperUtils.isNullDouble(objLst[4]));
					}

				} else {
					obj.setSvnpcda(0d);
				}
				if (objLst[5] != null) {
					if (objLst[5] instanceof Integer) {
						obj.setDcpsReg((double) StringHelperUtils.isNullInt(objLst[5]));
					} else {
						obj.setDcpsReg(StringHelperUtils.isNullDouble(objLst[5]));
					}
				} else {
					obj.setDcpsReg(0d);
				}

				if (objLst[6] != null) {
					if (objLst[6] instanceof BigInteger) {
						obj.setNpsEmployerDedu(StringHelperUtils.isNullBigInteger(objLst[6]).doubleValue());
					}else if (objLst[6] instanceof Integer) {
						obj.setNpsEmployerDedu((double)StringHelperUtils.isNullInt(objLst[6]));
					}else {
						obj.setNpsEmployerDedu(StringHelperUtils.isNullDouble(objLst[6]));
					}
				} else {
					obj.setNpsEmployerDedu(0d);
				}

				lstObj.add(obj);
			}
		}
		return lstObj;

	}

	@Override
	public List<Object[]> findbillgrp(Long billno) {
		// TODO Auto-generated method stub
		return regularReportRepo.findbillgrp(billno);
	}

	@Override
	public List<Object[]> findpaybill(Long billNumber, int monthName, int yearName, String ddo) {
		// TODO Auto-generated method stub
		return regularReportRepo.findpaybill(billNumber, monthName, yearName, ddo);
	}

	@Override
	public String getbillGroup(int billnumber) {
		// TODO Auto-generated method stub
		return regularReportRepo.getbillGroup(billnumber);
	}

	@Override
	public List<Object[]> checktheEntryForForm2Regular(int billNumber, int monthName, int yearName, String userName) {
		// TODO Auto-generated method stub
		return regularReportRepo.checktheEntryForForm2Regular(billNumber, monthName, yearName, userName);
	}

	@Override
	public List<MstDcpsBillGroup> lstBillDesc(String ddoCode) {
		// TODO Auto-generated method stub
		return regularReportRepo.lstBillDesc(ddoCode);
	}

	@Override
	public List<Object[]> findTrsyDtls(String ddoCode) {
		// TODO Auto-generated method stub
		return regularReportRepo.findTrsyDtls(ddoCode);
	}

}
