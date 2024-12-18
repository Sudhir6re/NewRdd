package com.mahait.gov.in.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mahait.gov.in.entity.MstSubMenuEntity;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.MstSubMenuModel;
import com.mahait.gov.in.repository.MstSubMenuRepo;

@Service
@Transactional
public class MstSubMenuServiceImpl implements MstSubMenuService {
	
	
	@Autowired
	private MstSubMenuRepo mstSubMenuRepo;

	//@CacheEvict(value = "submenus", allEntries = true)
	@Override
	public int saveSubMenu(MstSubMenuModel mstSubMenuModel, OrgUserMst messages) {
		MstSubMenuEntity mstSubMenuEntity = new MstSubMenuEntity();
		
		mstSubMenuEntity.setMenuCode(Integer.valueOf(mstSubMenuModel.getMenuName()));
		mstSubMenuEntity.setRoleId(Integer.valueOf(mstSubMenuModel.getRoleName()));
		mstSubMenuEntity.setSub_menu_name_english(mstSubMenuModel.getSubMenuEnglish());
		mstSubMenuEntity.setSub_menu_name_marathi(mstSubMenuModel.getSubMenuMarathi());
		mstSubMenuEntity.setController_name(mstSubMenuModel.getControllerName());
		mstSubMenuEntity.setLink_name(mstSubMenuModel.getLinkName());
		mstSubMenuEntity.setIs_active('1');
		mstSubMenuEntity.setCreatedUserId(1);

		int saveId = mstSubMenuRepo.saveSubMenu(mstSubMenuEntity);
		
		MstSubMenuEntity objSubMenuEntity1 = mstSubMenuRepo.findSubMenuBySubMenuKey(saveId);
		if(objSubMenuEntity1 != null) {
			objSubMenuEntity1.setSubMenuCode(saveId);
			mstSubMenuRepo.updateSubMenu(objSubMenuEntity1);
		}
		
		return saveId;
	}

	@Override
	public List<MstSubMenuEntity> findSubMenuByMenuKey(int key) {
		List<MstSubMenuEntity> lstSubMenu = mstSubMenuRepo.findSubMenuByMenuKey(key);
		return lstSubMenu;
	}

	@Override
	public MstSubMenuEntity findSubMenuBySubMenuKeyMenuKeyAndRoleKey(int subMenuKey, int menuKey, int roleKey,int UserId) {
		MstSubMenuEntity objSubMenu = mstSubMenuRepo.findSubMenuBySubMenuKeyMenuKeyAndRoleKey(subMenuKey, menuKey, roleKey);
		if(objSubMenu != null) {
			objSubMenu.setUpdatedDate(new Date());
			objSubMenu.setUpdatedUserId(UserId);
			objSubMenu.setIs_active('0');
		}
		return objSubMenu;
	}

	@Override
	public MstSubMenuEntity findSubMenuBySubMenuKey(int subMenuKey) {
		MstSubMenuEntity objSubMenu = mstSubMenuRepo.findSubMenuBySubMenuKey(subMenuKey);
		return objSubMenu;
	}

	//@CacheEvict(value = "submenus", allEntries = true)
	@Override
	public String saveEditSubMenu(MstSubMenuEntity mstSubMenuEntity, int user_id) {
		MstSubMenuEntity objSubMenu = mstSubMenuRepo.findSubMenuBySubMenuKey(mstSubMenuEntity.getSubMenuId());
		if(objSubMenu != null) {
			objSubMenu.setSub_menu_name_english(mstSubMenuEntity.getSub_menu_name_english());
			objSubMenu.setSub_menu_name_marathi(mstSubMenuEntity.getSub_menu_name_marathi());
			objSubMenu.setController_name(mstSubMenuEntity.getController_name());
			objSubMenu.setLink_name(mstSubMenuEntity.getLink_name());
			objSubMenu.setIs_active(mstSubMenuEntity.getIs_active());
			objSubMenu.setRoleId(mstSubMenuEntity.getRoleId());
			objSubMenu.setUpdatedDate(new Date());
			objSubMenu.setUpdatedUserId(user_id);
			objSubMenu.setMenuCode(mstSubMenuEntity.getMenuCode());
			mstSubMenuRepo.updateSubMenu(objSubMenu);
		}
		return "UPDATED";
	}
}
