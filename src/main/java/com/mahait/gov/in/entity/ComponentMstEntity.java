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

@Entity
@Data
@NoArgsConstructor
@Table(name = "component_mst")
public class ComponentMstEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="component_id")
	public Long id;

	@Column(unique = true , name ="component_name")
    public String componentName;
	
	@OneToMany(mappedBy = "componentMstEntity", cascade = CascadeType.ALL)
    private Set<ComponetMappingEntity> componetMappingEntity;

}

