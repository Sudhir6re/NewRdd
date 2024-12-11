package com.mahait.gov.in.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "INSTITUTE_TYPE")
public class InstituteType {

    @Id
    @Column(name = "INSTITUTE_TYPE_ID")
    private Long instituteTypeId;

    @Column(name = "INSTITUTE_TYPE_NAME", length = 50)
    private String instituteTypeName;

    @Column(name = "PARENT_INSTITUTE_ID")
    private Long parentInstituteId;

    
}

