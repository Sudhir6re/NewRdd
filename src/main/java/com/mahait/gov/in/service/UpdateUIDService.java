package com.mahait.gov.in.service;

import java.util.List;

import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.UpdateUIDModel;

import jakarta.validation.Valid;

public interface UpdateUIDService {

	List<UpdateUIDModel> findAllEmployee(String userName);


	int saveuidNo(@Valid UpdateUIDModel updateUIDModel, OrgUserMst messages);

}
