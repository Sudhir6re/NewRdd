package com.mahait.gov.in.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ZP_ADMIN_OFFICE_MST")
public class ZpAdminOfficeMst implements Serializable {

	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="OFC_ID")
	private Long ofcId;
	

    @Column(name = "OFFICE_NAME", length = 50)
    private String officeName;

    @Column(name = "OFFICE_CODE", length = 10)
    private String officeCode;

    @Column(name = "LANG_ID", precision = 10, scale = 0)
    private Long langId;

    @Column(name = "CREATED_DATE", length = 20)
    private Timestamp createdDate;

    @Column(name = "CREATED_BY_POST")
    private Long createdByPost;

    @Column(name = "UPDATED_BY_POST")
    private Long updatedByPost;

    
    
    @Column(name = "CREATED_BY")
    private Long createdBy;

    @Column(name = "UPDATED_BY")
    private Long updatedBy;
   

    @Column(name = "scheme_code", length = 20)
    private String schemeCode;

    
    @Column(name = "DCPS_OFFICE_NAME", length = 150)
    private String dcpsOffName;
    
    
    @Column(name = "is_active")
    private Integer isActive;
    
    
}
