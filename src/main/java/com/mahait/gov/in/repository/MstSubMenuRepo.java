package com.mahait.gov.in.repository;

import java.util.List;

import com.mahait.gov.in.entity.MstSubMenuEntity;

public interface MstSubMenuRepo {
	
	public int saveSubMenu(MstSubMenuEntity mstSubMenuEntity);

	public List<MstSubMenuEntity> findSubMenuByMenuKey(int key);

	public MstSubMenuEntity findSubMenuBySubMenuKeyMenuKeyAndRoleKey(int subMenuKey, int menuKey, int roleKey);

	public void updateSubMenu(MstSubMenuEntity mstSubMenuEntity);

	public MstSubMenuEntity findSubMenuBySubMenuKey(int subMenuKey);

}
