package com.mahait.gov.in.service;

import java.math.BigInteger;
import java.util.List;

import com.mahait.gov.in.entity.MstDcpsBillGroup;
import com.mahait.gov.in.entity.MstScheme;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.MstSchemeModel;

public interface MstSchemeService {

	public List<MstSchemeModel> findAllScheme(String string);

	public List<MstDcpsBillGroup> findAllMpgSchemeBillGroupByDDOCode(String DDOCode,int roleid);

	public Long findNumberOfEmployeeInBillGroup(String logUser, BigInteger schemeBillGroupId, int monthName,
			int yearName, int paybillType);

	public List<MstScheme> findAllSchemeDetails(String data);

	public List<MstSchemeModel> findAllMpgSchemeBillGroupBylvl2DDOCode(String userName);

	public List<MstSchemeModel> findAllSchemeforConsolidate(String ddoCode);

	

}
