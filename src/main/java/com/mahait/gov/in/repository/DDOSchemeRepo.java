package com.mahait.gov.in.repository;

import java.io.Serializable;
import java.util.List;

import com.mahait.gov.in.entity.CmnTalukaMst;
import com.mahait.gov.in.entity.MstScheme;
import com.mahait.gov.in.entity.OrgPostMst;
import com.mahait.gov.in.entity.RltDCPSDdoSchemeEntity;
import com.mahait.gov.in.model.OrgDdoMst;

public interface DDOSchemeRepo {

	List<Object[]> findAllEmployee(String userName);

	String getDdoCodeForDDO(OrgPostMst createdByPost);

	List<Object[]> getDDOCodeByLoggedInlocId(int i);

	List<Object[]> getAllSchemesForDDO(String lStrDDOCode);

	String districtName(String ddoCode);

	List<CmnTalukaMst> allTaluka(String districtID);

	List getSubDDOs(Long locId, String talukaId, String ddoSelected);

	List getpostRole(OrgPostMst createdByPost);

	Long CheckSubSchemeExist(String schemeCode, String subschemeCode);

	String getlocid(String userName);

	Long saveScheme(RltDCPSDdoSchemeEntity rltDCPSDdoSchemeEntity);

	List<Object[]> displaySchemeNameForCode(String schemeCode);

}
