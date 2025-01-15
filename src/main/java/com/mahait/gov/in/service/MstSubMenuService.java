package com.mahait.gov.in.service;

import java.util.List;

import com.mahait.gov.in.entity.MstRoleEntity;
import com.mahait.gov.in.entity.MstSubMenuEntity;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.MstSubMenuModel;

public interface MstSubMenuService {

	public int saveSubMenu(MstSubMenuModel mstSubMenuModel, OrgUserMst messages);

	public List<MstSubMenuEntity> findSubMenuByMenuKey(int key);

	public MstSubMenuEntity findSubMenuBySubMenuKeyMenuKeyAndRoleKey(int subMenuKey, int menuKey, int roleKey, int userId);

	public MstSubMenuEntity findSubMenuBySubMenuKey(int subMenuKey);

	public String saveEditSubMenu(MstSubMenuEntity mstSubMenuEntity, int user_id);

	public MstRoleEntity findRoleWiseUrl(Integer roleId);

}
