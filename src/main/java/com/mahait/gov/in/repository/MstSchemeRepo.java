package com.mahait.gov.in.repository;

import java.math.BigInteger;
import java.util.List;

import com.mahait.gov.in.entity.MstDcpsBillGroup;
import com.mahait.gov.in.entity.MstScheme;
import com.mahait.gov.in.model.MstSchemeModel;

public interface MstSchemeRepo {

	List<MstScheme> findAllScheme();

	List<MstSchemeModel> findAllSchemename(String username);

	List<MstSchemeModel> findAllSchemename();

	List<MstDcpsBillGroup> findAllMpgSchemeBillGroupByDDOCode(String dDOCode, int roleid);

	Long findNumberOfEmployeeInBillGroup(String logUser, BigInteger schemeBillGroupId, int monthName, int yearName, int paybillType);

	List<MstSchemeModel> findAllMpgSchemeBillGroupBylvl2DDOCode(String userName);

	List<MstSchemeModel> findAllSchemeforConsolidate(String ddoCode);


}
