package com.mahait.gov.in.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mahait.gov.in.common.CommonConstants;
import com.mahait.gov.in.entity.MstMenuEntity;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.MstMenuModel;
import com.mahait.gov.in.repository.MstMenuRepo;

@Service
@Transactional
public class MstMenuServiceImpl implements MstMenuService {

	@Autowired
	MstMenuRepo mstMenuRepo;

	//@CacheEvict(value = "menus", allEntries = true)
	@Override
	public int saveMenu(MstMenuModel mstMenuModel, OrgUserMst messages) {
		MstMenuEntity objMenuEntity = new MstMenuEntity();
		int saveId=0;
		if(mstMenuModel.getMenuNameEnglish()!=null) {
			
			objMenuEntity.setMenu_name_english(mstMenuModel.getMenuNameEnglish());
			objMenuEntity.setMenu_name_marathi(mstMenuModel.getMenuNameMarathi());
			objMenuEntity.setCreatedDate(new Date());
			objMenuEntity.setCreatedUserId(messages.getMstRoleEntity().getRoleId());
			objMenuEntity.setIs_active(CommonConstants.NUMBERS.ONE);
			 saveId = mstMenuRepo.saveMenu(objMenuEntity);
		}
		
		MstMenuEntity objMenuEntity1 = mstMenuRepo.findMenuByKeyForDelete(saveId);
		if(objMenuEntity1 != null) {
			objMenuEntity1.setMenuCode(saveId);
			mstMenuRepo.updateMenuStatus(objMenuEntity1);
		}
		return saveId;
	}

	@Override
	public MstMenuEntity findMenuByKeyForDelete(int key,int userId) {
		MstMenuEntity objMenu = mstMenuRepo.findMenuByKeyForDelete(key);
		if(objMenu != null) {
			objMenu.setIs_active("0");
			objMenu.setUpdatedDate(new Date());
			objMenu.setUpdatedUserId(userId);
			mstMenuRepo.updateMenuStatus(objMenu);
		}
		return objMenu;
	}

	@Override
	public MstMenuEntity findMenuByKeyForEdit(int key) {
		MstMenuEntity objMenu = mstMenuRepo.findMenuByKeyForEdit(key);
		return objMenu;
	}

//	@CacheEvict(value = "menus", allEntries = true)
	@Override
	public String saveEditMenu(MstMenuEntity mstMenuEntity,OrgUserMst orgUserMst) {
		MstMenuEntity objMenu = mstMenuRepo.findMenuByKeyForEdit(mstMenuEntity.getMenuId());
		if(objMenu != null) {
			objMenu.setMenu_name_english(mstMenuEntity.getMenu_name_english());
			objMenu.setMenu_name_marathi(mstMenuEntity.getMenu_name_marathi());
			objMenu.setUpdatedDate(new Date());
			objMenu.setUpdatedUserId(orgUserMst.getMstRoleEntity().getRoleId());
			mstMenuRepo.updateMenuStatus(objMenu);
		}
		return "UPDATED";
	}
}
