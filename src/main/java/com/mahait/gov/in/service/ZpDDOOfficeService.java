package com.mahait.gov.in.service;

import java.util.List;

import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.entity.ZpRltDdoMap;
import com.mahait.gov.in.model.NewRegDDOModel;

public interface ZpDDOOfficeService {

	List<ZpRltDdoMap> getAllDDOOfficeDtlsDataByPostID(String ddoCode);

	NewRegDDOModel getDDOinfo(String zpDdoCode);

	String getOfficeName(String zpDdoCode);

	OrgUserMst approveddoDtls(String zpDdoCode, int flag);

	List<ZpRltDdoMap> lstApprovedOffices(String ddoCode);

	List<ZpRltDdoMap>  lstRejectedOffices(String userName);

	
}
