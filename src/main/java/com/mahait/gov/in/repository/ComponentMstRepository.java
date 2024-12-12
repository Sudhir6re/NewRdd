package com.mahait.gov.in.repository;

import java.util.List;
import java.util.Optional;

import com.mahait.gov.in.entity.ComponentMstEntity;
import com.mahait.gov.in.entity.MstMenuEntity;

public interface ComponentMstRepository {

	Optional<ComponentMstEntity> findByParameterName(String parameterName);

	List<ComponentMstEntity> saveAll(List<ComponentMstEntity> componentList);

	public List<ComponentMstEntity> lstAllComponent();

	ComponentMstEntity save(ComponentMstEntity componentParameterMstEntity);



	void updateComponentParameter(ComponentMstEntity objMenu);

	ComponentMstEntity findComponentParameterByIdForEdit(Long Id);

	ComponentMstEntity findMenuByidForDelete(long id);

	void deleteComponent(ComponentMstEntity objMenu);





	

}
