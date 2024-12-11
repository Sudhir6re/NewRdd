package com.mahait.gov.in.entity;


import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;


@Data
@Entity
@Table(name = "HR_EIS_GD_MPG")
public class HrEisGdMpg {

    @Id
    @Column(name = "GD_MAP_ID", precision = 22, scale = 0)
    private Long gdMapId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SGD_GRADE_ID")
    private OrgGradeMst orgGradeMst;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DESIGNATION_ID")
    private MstDesignationEntity orgDesignationMst;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "db_id")
    private CmnDatabaseMst cmnDatabaseMst;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by_post")
    private OrgPostMst orgPostMstByUpdatedByPost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by_post")
    private OrgPostMst orgPostMstByCreatedByPost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loc_id")
    private CmnLocationMst cmnLocationMst;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by")
    private OrgUserMst orgUserMstByUpdatedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private OrgUserMst orgUserMstByCreatedBy;

    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.DATE)
    private Date createdDate;

    @Column(name = "UPDATED_DATE")
    @Temporal(TemporalType.DATE)
    private Date updatedDate;

    @Column(name = "ELEMENT_CODE", precision = 22, scale = 0)
    private Long elementCode;

}
