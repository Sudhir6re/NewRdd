package com.mahait.gov.in.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "component_parameter_mst")
public class ComponentParameterMstEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Component_Parameter_id")
	private Long id;
	private String CompntParameterName;
	
	@OneToMany(mappedBy = "componentParameterMstEntity", cascade = CascadeType.ALL)
    private Set<ComponetMappingEntity> componetMappingEntity;
}
