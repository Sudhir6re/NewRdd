package com.mahait.gov.in.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mahait.gov.in.entity.ComponentMstEntity;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.repository.ComponentMstRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;


@Service
@Transactional
public class ComponentMstServiceImpl implements ComponentMstService{
 @Autowired
 ComponentMstRepository componentMstRepository;

	@Override
	public List<ComponentMstEntity> saveComponet(List<ComponentMstEntity> componentList) {
		 return componentMstRepository.saveAll(componentList);
	}

	@Override
	public List<ComponentMstEntity> lstAllComponent() {
		return componentMstRepository.lstAllComponent();
	}

	@Override
	public Object saveComponent(ComponentMstEntity componentParameterMstEntity) {
	          
	return	componentMstRepository.save(componentParameterMstEntity);
	}

	@Override
	public String saveEditMenu(@Valid ComponentMstEntity componentParameterMstEntity, OrgUserMst orgUserMst) {
		ComponentMstEntity objMenu = componentMstRepository.findComponentParameterByIdForEdit(componentParameterMstEntity.getId());
		if(objMenu != null) {
			objMenu.setId(componentParameterMstEntity.getId());
			objMenu.setComponentName(componentParameterMstEntity.getComponentName());
			componentMstRepository.updateComponentParameter(objMenu);
		}
		return "UPDATED";
	}

	@Override
	public Object findMenuByKeyForEdit(long id) {
		ComponentMstEntity objMenu = componentMstRepository.findComponentParameterByIdForEdit(id);
		return objMenu;
	}

	@Override
	public String findMenuByKeyForDelete(long id) {
		
		ComponentMstEntity objMenu = componentMstRepository.findMenuByidForDelete(id);
		if(objMenu != null) {	
		componentMstRepository.deleteComponent(objMenu);
		}
		return "DELETED";
	}



	
	}
	
		

	

