package com.mahait.gov.in.service;

import java.util.List;

import com.mahait.gov.in.entity.CmnTalukaMst;
import com.mahait.gov.in.entity.MstScheme;
import com.mahait.gov.in.entity.OrgPostMst;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.EmpWiseCityClassModel;
import com.mahait.gov.in.model.NewRegDDOModel;
import com.mahait.gov.in.model.OrgDdoMst;

public interface DDOSchemeService {

	List<EmpWiseCityClassModel> findAllEmployee(String userName);

	String getDdoCodeForDDO(OrgPostMst createdByPost);

	List<OrgDdoMst> getDDOCodeByLoggedInlocId(long l);

	List<NewRegDDOModel> getAllSchemesForDDO(String lStrDDOCode);

	String districtName(String ddoCode);

	List<CmnTalukaMst> allTaluka(String districtID);
	
	public List getSubDDOs(Long locId, String talukaId, String ddoSelected);

	List getpostRole(OrgPostMst createdByPost);

	Long CheckSubSchemeExist(String schemeCode, String subschemeCode);

	Long addSchemesAndBillGroupsToDdo(NewRegDDOModel newRegDDOModel, OrgUserMst messages);

	List<Object[]> displaySchemeNameForCode(String schemeCode);


	
}
