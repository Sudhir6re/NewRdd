package com.mahait.gov.in.service;

import java.util.List;

import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.UpdateServiceEndDateModel;

import jakarta.validation.Valid;

public interface UpdateServiceEndDateService {

	List<UpdateServiceEndDateModel> findAllEmployee(String userName);

	int saveSED(@Valid UpdateServiceEndDateModel updateServiceEndDateModel, OrgUserMst messages);

}
