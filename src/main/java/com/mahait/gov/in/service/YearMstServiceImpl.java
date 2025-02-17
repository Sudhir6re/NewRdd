package com.mahait.gov.in.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mahait.gov.in.entity.MstYearEntity;
import com.mahait.gov.in.repository.YearMstRepo;

import jakarta.transaction.Transactional;

@Transactional
@Service 
public class YearMstServiceImpl implements YearMstService {
	
	@Autowired
	YearMstRepo yearMstRepo;

	@Override
	public List<MstYearEntity> findAllYears() {
		// TODO Auto-generated method stub
		return yearMstRepo.findAllYears();
	}

	@Override
	public MstYearEntity findYearById(Integer yearId) {
		 return yearMstRepo.findYearById(yearId);
	}

	@Override
	public void updateYear(MstYearEntity mstYearEntity) {
		 yearMstRepo.updateYear(mstYearEntity);
	}

	@Override
	public void saveYear(MstYearEntity mstYearEntity) {
		mstYearEntity.setIsActive('1');
		yearMstRepo.saveYear(mstYearEntity);
	}

}
