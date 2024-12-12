package com.mahait.gov.in.repository;

import java.util.List;

import com.mahait.gov.in.entity.MstMenuRoleMappingEntity;
import com.mahait.gov.in.entity.MstSubMenuEntity;

public interface MstMenuRoleMappingRepo {
	
	public int saveMenuRoleMapping(MstMenuRoleMappingEntity mstMenuRoleMappingEntity);

	public List<MstMenuRoleMappingEntity> findMenuRoleMappingByMenuKey(int key);

	public List<MstSubMenuEntity> findSubMenuByMenuKeyAndRoleKey(int key, int roleKey);

	public MstMenuRoleMappingEntity findMenuRoleMappingByMenuKeyAndRoleKey(int key, int roleKey);

	public void updateMenuRoleMappingStatus(MstMenuRoleMappingEntity objMenu);

	public MstMenuRoleMappingEntity findMenuAndRoleMappingByKey(int key);

	public List<MstMenuRoleMappingEntity> findMenuRoleMappingByMenuKeyAndRoleKeyList(Integer menu_key,
			Integer role_key);

}
