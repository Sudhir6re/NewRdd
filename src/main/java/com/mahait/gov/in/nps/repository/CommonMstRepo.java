package com.mahait.gov.in.nps.repository;

import java.util.List;

import com.mahait.gov.in.entity.MstCommonEntity;

public interface CommonMstRepo {

	List<MstCommonEntity> findAllCommonConst();

	MstCommonEntity findCommonMstById(Integer commonId);

	void updateCommonMst(MstCommonEntity mstCommonEntity);

	void saveCommonMst(MstCommonEntity mstCommonEntity);

}
