package com.mahait.gov.in.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mahait.gov.in.entity.MstCommonEntity;
import com.mahait.gov.in.nps.repository.CommonMstRepo;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class CommonMstServiceImpl  implements CommonMstService{

	@Autowired
	CommonMstRepo commonMstRepo;

	@Override
	public List<MstCommonEntity> findAllCommonConst() {
		// TODO Auto-generated method stub
		return commonMstRepo.findAllCommonConst();
	}

	@Override
	public MstCommonEntity findCommonMstById(Integer commonId) {
		// TODO Auto-generated method stub
		return commonMstRepo.findCommonMstById(commonId);
	}

	@Override
	public void updateCommonMst(MstCommonEntity mstCommonEntity) {
		commonMstRepo.updateCommonMst(mstCommonEntity);
	}

	@Override
	public void saveCommonMst(MstCommonEntity mstCommonEntity) {
		// TODO Auto-generated method stub
		commonMstRepo.saveCommonMst(mstCommonEntity);
	}

}
