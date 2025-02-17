package com.mahait.gov.in.service;

import java.util.List;

import com.mahait.gov.in.entity.MstYearEntity;

public interface YearMstService {

	List<MstYearEntity> findAllYears();

	MstYearEntity findYearById(Integer commonId);

	void updateYear(MstYearEntity mstYearEntity);

	void saveYear(MstYearEntity mstYearEntity);

}
