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
@Table(name = "hr_eis_scale_mst")
public class HrEisScaleMst {

    @Id
    @Column(name = "scale_id")
    private Long scaleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lang_id")
    private CmnLanguageMst cmnLanguageMst;

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

   /* @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commission_id")
    private HrPayCommissionMst hrPayCommissionMst;*/

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payband_id")
    private CmnLookupMst payBandId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PayScale_Type")
    private CmnLookupMst payScaleType;

    @Column(name = "scale_name", length = 40)
    private String scaleName;

    @Column(name = "scale_desc", length = 100)
    private String scaleDesc;

    @Column(name = "scale_start_amt")
    private Long scaleStartAmt;

    @Column(name = "scale_end_amt")
    private Long scaleEndAmt;

    @Column(name = "scale_incr_amt")
    private Long scaleIncrAmt;

    @Column(name = "element_code")
    private Long elementCode;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", length = 19)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "SCALE_EFF_FROM_DT")
    private Date scaleEffFromDt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "SCALE_EFF_TO_DT")
    private Date scaleEffToDt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "increment_date", length = 19)
    private Date incrementDate;

    @Column(name = "scale_higher_incr_amt")
    private Long scaleHigherIncrAmt;

    @Column(name = "scale_higher_end_amt")
    private Long scaleHigherEndAmt;

    @Column(name = "scale_2nd_higher_incr_amt")
    private Long scaleSecondHigherIncrAmt;

    @Column(name = "scale_2nd_higher_end_amt")
    private Long scaleSecondHigherEndAmt;

    @Column(name = "scale_3rd_higher_incr_amt")
    private Long scaleThirdHigherIncrAmt;

    @Column(name = "scale_3rd_higher_end_amt")
    private Long scaleThirdHigherEndAmt;

    @Column(name = "TRN_COUNTER")
    private Integer trnCounter;

    @Column(name = "SCALE_GRADE_PAY")
    private Long scaleGradePay;

    @Column(name = "MIGRATED_SCALE")
    private Integer migratedScale;

}
