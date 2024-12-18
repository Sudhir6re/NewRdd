package com.mahait.gov.in.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mahait.gov.in.entity.MstMenuRoleMappingEntity;
import com.mahait.gov.in.entity.MstSubMenuEntity;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.MstMenuRoleMappingModel;
import com.mahait.gov.in.repository.MstMenuRoleMappingRepo;
import com.mahait.gov.in.repository.MstSubMenuRepo;

@Service
@Transactional
public class MstMenuRoleMappingServiceImpl implements MstMenuRoleMappingService {
	
	@Autowired
	private MstMenuRoleMappingRepo mstMenuRoleMappingRepo;

	@Autowired
	private MstSubMenuRepo mstSubMenuRepo;
	
	@Override
	public int saveMenuRoleMapping(MstMenuRoleMappingModel mstMenuRoleMappingModel, OrgUserMst messages) {
		MstMenuRoleMappingEntity mstMenuRoleMappingEntity = new MstMenuRoleMappingEntity();
		mstMenuRoleMappingEntity.setMenuCode(Integer.valueOf(mstMenuRoleMappingModel.getMenuName()));
		mstMenuRoleMappingEntity.setRoleId(Integer.valueOf(mstMenuRoleMappingModel.getRoleName()));
		mstMenuRoleMappingEntity.setIs_active('1');
		mstMenuRoleMappingEntity.setCreatedUserId( messages.getMstRoleEntity().getRoleId());
		
		int saveId = mstMenuRoleMappingRepo.saveMenuRoleMapping(mstMenuRoleMappingEntity);
		return saveId;
	}

	@Override
	public List<MstMenuRoleMappingEntity> findMenuRoleMappingByMenuKey(int key) {
		List<MstMenuRoleMappingEntity> lstMenuRoleMapp = mstMenuRoleMappingRepo.findMenuRoleMappingByMenuKey(key);
		return lstMenuRoleMapp;
	}

	@Override
	public List<MstSubMenuEntity> findSubMenuByMenuKeyAndRoleKey(int key, int roleKey) {
		List<MstSubMenuEntity> lstMenuRoleMapp = mstMenuRoleMappingRepo.findSubMenuByMenuKeyAndRoleKey(key,roleKey);
		return lstMenuRoleMapp;
	}

	@Override
	public MstMenuRoleMappingEntity findMenuRoleMappingByMenuKeyAndRoleKey(int key, int roleKey,int userId) {

		MstMenuRoleMappingEntity objMenu = mstMenuRoleMappingRepo.findMenuRoleMappingByMenuKeyAndRoleKey(key,roleKey);
		if(objMenu != null) {
			objMenu.setIs_active('0');
			objMenu.setUpdatedDate(new Date());
			objMenu.setUpdatedUserId(userId);
			mstMenuRoleMappingRepo.updateMenuRoleMappingStatus(objMenu);
		}
		return objMenu;
	}

	@Override
	public MstMenuRoleMappingEntity findMenuAndRoleMappingByKey(int key) {
		MstMenuRoleMappingEntity objMenuRoleMap = mstMenuRoleMappingRepo.findMenuAndRoleMappingByKey(key);
		return objMenuRoleMap;
	}

	@Override
	public String saveEditMenuRoleMapping(MstMenuRoleMappingEntity mstMenuRoleMappingEntity, int user_id) {
		
		List<MstMenuRoleMappingEntity> lstCheckAlreadyExistsOrNot = mstMenuRoleMappingRepo.findMenuRoleMappingByMenuKeyAndRoleKeyList(Integer.valueOf(mstMenuRoleMappingEntity.getMenuCode()),
																						Integer.valueOf(mstMenuRoleMappingEntity.getRoleId()));
		if(!lstCheckAlreadyExistsOrNot.isEmpty()) {
			return "ALLREADYEXISTS";
		} else {
			MstMenuRoleMappingEntity objMenuRoleMap = mstMenuRoleMappingRepo.findMenuAndRoleMappingByKey(mstMenuRoleMappingEntity.getMenuMapID());
			
			if(objMenuRoleMap != null) {
				List<MstSubMenuEntity> lstSubMenuRoleMapping = mstMenuRoleMappingRepo.findSubMenuByMenuKeyAndRoleKey(objMenuRoleMap.getMenuCode(), objMenuRoleMap.getRoleId());
			
				if(!lstSubMenuRoleMapping.isEmpty()) {
					
					for (MstSubMenuEntity mstSubMenuEntity : lstSubMenuRoleMapping) {
						mstSubMenuEntity.setMenuCode(mstMenuRoleMappingEntity.getMenuCode());
						mstSubMenuEntity.setRoleId(mstMenuRoleMappingEntity.getRoleId());
						mstSubMenuEntity.setUpdatedDate(new Date());
						mstSubMenuEntity.setUpdatedUserId(user_id);
						mstSubMenuRepo.updateSubMenu(mstSubMenuEntity);
					}
				}
			
				objMenuRoleMap.setMenuCode(mstMenuRoleMappingEntity.getMenuCode());
				objMenuRoleMap.setRoleId(mstMenuRoleMappingEntity.getRoleId());
				objMenuRoleMap.setUpdatedDate(new Date());
				objMenuRoleMap.setUpdatedUserId(user_id);
				mstMenuRoleMappingRepo.updateMenuRoleMappingStatus(objMenuRoleMap);
			}
			return "UPDATED";
		}
	}
}
