package com.mahait.gov.in.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "componet_mapping")
public class ComponetMappingEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "component_parameter_id", referencedColumnName = "Component_Parameter_id")
	private ComponentParameterMstEntity componentParameterMstEntity;

	@ManyToOne
	@JoinColumn(name = "component_mst_id", referencedColumnName = "Component_id")
	ComponentMstEntity componentMstEntity;

	private Boolean isMandatory;

}
