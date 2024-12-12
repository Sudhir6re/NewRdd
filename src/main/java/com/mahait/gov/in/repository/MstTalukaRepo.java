package com.mahait.gov.in.repository;

import java.util.List;

import com.mahait.gov.in.entity.MstTalukaEntity;
import com.mahait.gov.in.model.MstTalukaModel;

public interface MstTalukaRepo {

	public List<Object[]> findAllTaluka(int distId);
	
	public List<MstTalukaEntity> findTalukaAll();
	
	//Added for district list//
	

		public List<MstTalukaModel> fetchDistrictByState(int stateCode);

		public List<MstTalukaEntity> findByTaluka(Integer subDepartmentCode);

		public List<Long> validateTalukaName(String talukaname, Integer districtcode);

}
