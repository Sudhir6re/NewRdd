package com.mahait.gov.in.service;

import com.mahait.gov.in.entity.MstMenuEntity;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.MstMenuModel;

public interface MstMenuService {

	int saveMenu(MstMenuModel mstMenuModel,  OrgUserMst messages);

	public MstMenuEntity findMenuByKeyForDelete(int key,int userId);

	public MstMenuEntity findMenuByKeyForEdit(int key);

	public String saveEditMenu(MstMenuEntity mstMenuEntity, OrgUserMst orgUserMst);
}
