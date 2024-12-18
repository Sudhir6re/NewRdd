package com.mahait.gov.in.service;

import java.util.List;

import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.MstEmployeeModel;

import jakarta.validation.Valid;

public interface ChangeDcpsGpfService {

	List<Object[]> getDOJ(String sevaarthId);

	Long savechangeDcpsGpf(@Valid MstEmployeeModel mstEmployeeModel, OrgUserMst messages);


}
