package com.mahait.gov.in.service;

import java.util.List;

import com.mahait.gov.in.entity.MstMenuRoleMappingEntity;
import com.mahait.gov.in.entity.MstSubMenuEntity;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.MstMenuRoleMappingModel;

public interface MstMenuRoleMappingService {

	public int saveMenuRoleMapping(MstMenuRoleMappingModel mstMenuRoleMappingModel, OrgUserMst messages);

	public List<MstMenuRoleMappingEntity> findMenuRoleMappingByMenuKey(int key);

	public List<MstSubMenuEntity> findSubMenuByMenuKeyAndRoleKey(int key, int roleKey);

	public MstMenuRoleMappingEntity findMenuRoleMappingByMenuKeyAndRoleKey(int key, int roleKey,int userId);

	public MstMenuRoleMappingEntity findMenuAndRoleMappingByKey(int key);

	public String saveEditMenuRoleMapping(MstMenuRoleMappingEntity mstMenuRoleMappingEntity, int user_id);

}
