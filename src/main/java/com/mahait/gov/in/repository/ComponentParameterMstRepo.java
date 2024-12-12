package com.mahait.gov.in.repository;

import java.util.List;
import java.util.Optional;

import com.mahait.gov.in.entity.ComponentParameterMstEntity;

public interface ComponentParameterMstRepo {

	public void transferData(List<ComponentParameterMstEntity> targetList);

	public Optional<ComponentParameterMstEntity> findById(Long compId);

	public Optional<ComponentParameterMstEntity> findByComponentName(String componentName);

	


}