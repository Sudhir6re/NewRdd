package com.mahait.gov.in.service;

import java.util.List;

import com.mahait.gov.in.entity.MstTalukaEntity;
import com.mahait.gov.in.model.MstTalukaModel;

public interface MstTalukaService {

	public List<Object[]> findAllTaluka(int distId);

	public List<MstTalukaEntity> findTalukaAll();

	// added for district list

	public List<MstTalukaModel> fetchDistrictByState(int stateCode);

	// Added for Saving

	public MstTalukaEntity saveTaluka(MstTalukaEntity mstTalukaEntity);

	public List<MstTalukaEntity> findByTaluka(Integer subDepartmentCode);

	public List<Long> validateTalukaName(String talukaname, Integer districtcode);

}
