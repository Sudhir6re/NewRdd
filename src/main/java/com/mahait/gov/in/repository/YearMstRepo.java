package com.mahait.gov.in.repository;

import java.util.List;

import com.mahait.gov.in.entity.MstYearEntity;

public interface YearMstRepo {

	List<MstYearEntity> findAllYears();

	 MstYearEntity findYearById(Integer commonId);

	void updateYear(MstYearEntity mstYearEntity);

	void saveYear(MstYearEntity mstYearEntity);

}
