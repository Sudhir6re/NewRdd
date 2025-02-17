package com.mahait.gov.in.service;

import java.util.List;

import com.mahait.gov.in.entity.MstCommonEntity;

public interface CommonMstService {

	public List<MstCommonEntity> findAllCommonConst();

	public MstCommonEntity findCommonMstById(Integer commonId);

	public void updateCommonMst(MstCommonEntity mstCommonEntity);

	public void saveCommonMst(MstCommonEntity mstCommonEntity);

}
