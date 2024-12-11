package com.mahait.gov.in.entity;


import java.sql.Timestamp;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "TRN_DCPS_CONTRIBUTION",schema="public")
public class DcpsContributionEntity  {

    @Id
    @Column(name = "DCPS_CONTRIBUTION_ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dcpContributionId;

    @Column(name = "DCPS_EMP_ID", nullable = false)
    private Long dcpEmpId;

    @Column(name = "TREASURY_CODE", nullable = false)
    private Long treasuryCode;

    @Column(name = "DDO_CODE", length = 15)
    private String ddoCode;

    @Column(name = "BILL_GROUP_ID")
    private Long billGroupId;
    

    
    @Column(name = "SCHEME_CODE", length = 10)
    private String schemeCode;

    @Column(name = "PAY_COMMISSION", length = 20, nullable = false)
    private String payCommission;

    @Column(name = "TYPE_OF_PAYMENT", length = 20, nullable = false)
    private String typeOfPayment;

    @Column(name = "FIN_YEAR_ID", nullable = false)
    private Integer finYearId;

    @Column(name = "MONTH_ID", nullable = false)
    private Integer monthId;

    @Column(name = "BASIC_PAY")
    private Double basicPay;

    @Column(name = "DA")
    private Double da;
    
    @Column(name = "DP")
    private Double dp;

    @Column(name = "CONTRIBUTION")
    private Double contribution;

    @Column(name = "REG_STATUS")
    private Integer regStatus;

    @Column(name = "LANG_ID", nullable = false)
    private Long langId;

    @Column(name = "LOC_ID", nullable = false)
    private Long locId;

    @Column(name = "DB_ID", nullable = false)
    private Long dbId;

    @Column(name = "CREATED_POST_ID", nullable = false)
    private Long createdPostId;

    @Column(name = "CREATED_USER_ID", nullable = false)
    private Long createdUserId;

    @Column(name = "CREATED_DATE", nullable = false)
    private Timestamp createdDate;

    @Column(name = "UPDATED_POST_ID")
    private Long updatedPostId;

    @Column(name = "UPDATED_USER_ID")
    private Long updatedUserId;

    @Column(name = "UPDATED_DATE")
    private Timestamp updatedDate;

    @Column(name = "STARTDATE")
    private Date startDate;

    @Column(name = "ENDDATE")
    private Date endDate;

    @Column(name = "RLT_CONTRI_VOUCHER_ID", nullable = false)
    private Long rltContriVoucherId;

    @Column(name = "DELAYED_FIN_YEAR_ID")
    private Integer delayedFinYearId;

    @Column(name = "DELAYED_MONTH_ID")
    private Integer delayedMonthId;

    @Column(name = "EMPLOYER_CONTRI_FLAG", length = 1)
    private String employerContriFlag;

    @Column(name = "STATUS", length = 1)
    private String status;

    @Column(name = "VOUCHER_DATE")
    private Date voucherDate;

    @Column(name = "VOUCHER_NO", length = 40)
    private Integer voucherNo;
    
    @Column(name = "NPS_EMPLR_CONTRI_DED")
    private Double npsEmplrContriDed;
    
    @Column(name = "sevaarth_id")
    private String sevaarthId;
    
    @Column(name = "CONTRIBUTION_EMPR")
    private Float contributionEmpr;

}

