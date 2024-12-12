package com.mahait.gov.in.repository;

import java.util.List;

import com.mahait.gov.in.entity.ComponentParameterMstEntity;
import com.mahait.gov.in.entity.ComponetMappingEntity;

public interface ComponentMappingRepository {

	ComponetMappingEntity findByUser(Object componentParameterMstEntity);

	List<ComponetMappingEntity> save(List<ComponetMappingEntity> mappings);

}
