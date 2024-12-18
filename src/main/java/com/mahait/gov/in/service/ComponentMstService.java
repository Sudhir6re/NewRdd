package com.mahait.gov.in.service;

import java.util.List;

import com.mahait.gov.in.entity.ComponentMstEntity;
import com.mahait.gov.in.entity.OrgUserMst;

import jakarta.validation.Valid;

public interface ComponentMstService {

	List<ComponentMstEntity> lstAllComponent();
	
	public List<ComponentMstEntity> saveComponet(List<ComponentMstEntity> componentList);

	 Object saveComponent(ComponentMstEntity componentParameterMstEntity);

	String saveEditMenu(@Valid ComponentMstEntity componentParameterMstEntity, OrgUserMst orgUserMst);

	Object findMenuByKeyForEdit(long id);

	String findMenuByKeyForDelete(long id);

}
