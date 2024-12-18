package com.mahait.gov.in.service;

import java.util.List;

import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.UpdateDOBModel;

import jakarta.validation.Valid;

public interface UpdateDOBService {

	List<UpdateDOBModel> findAllEmployee(String userName);

	int saveupdatedob(@Valid UpdateDOBModel updateDOBModel, OrgUserMst messages);


	List<UpdateDOBModel> getEmpDobBySevaarthId(String sevaarthId);

}
