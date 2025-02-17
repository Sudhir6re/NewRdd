package com.mahait.gov.in.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "mst_dcps_cadre")
public class DcpsCadreMst {

    @Id
    @Column(name = "CADRE_ID")
    private Long cadreId;

    @Column(name = "FIELD_DEPT_ID")
    private Long fieldDeptId;

    @Column(name = "GROUP_ID", length = 10)
    private Long groupId;

    @Column(name = "CADRE_CODE", length = 20)
    private Long cadreCode;

    @Column(name = "CADRE_NAME", length = 20)
    private String cadreName;

    @Column(name = "MINISTERIAL_FLAG", length = 1)
    private String ministerialFlag;

    @Column(name = "SUPER_ANTUN_AGE")
    private Long superAntunAge;

    @Column(name = "CNTRL_FIELD_DEPT_ID")
    private Long cntrlFieldDeptId;

    @Column(name = "CADRE_CNTRL_FLAG", length = 1)
    private String cadreCntrlFlag;

    @Column(name = "LANG_ID")
    private Long langId;

    @Column(name = "LOC_ID")
    private Long locId;

    @Column(name = "DB_ID")
    private Long dbId;

    @Column(name = "CREATED_POST_ID", length = 20)
    private Long createdPostId;

    @Column(name = "CREATED_USER_ID", length = 20)
    private Long createdUserId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @Column(name = "UPDATED_POST_ID", length = 20)
    private Long updatedPostId;

    @Column(name = "UPDATED_USER_ID", length = 20)
    private Long updatedUserId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATED_DATE")
    private Date updatedDate;

    
}
