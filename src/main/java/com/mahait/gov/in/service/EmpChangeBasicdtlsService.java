package com.mahait.gov.in.service;
import java.util.List;

import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.ChangeBasicDtlsModel;

public interface EmpChangeBasicdtlsService {

	
	public List<ChangeBasicDtlsModel> findEmpChangeBasicDtls(String userName);

	public int saveChangeBasicdtls(ChangeBasicDtlsModel changeBasicDtlsModel, OrgUserMst messages);
	
}
