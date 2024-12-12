package com.mahait.gov.in.repository;

import java.util.List;

import com.mahait.gov.in.entity.MstEmployeeEntity;

public interface ChangeDcpsGpfRepo {

	List<Object[]> getDOJ(String sevaarthId);

	MstEmployeeEntity getemp(String ddoCode);

	void savechangeDcpsGpf(MstEmployeeEntity lstprop);

}
