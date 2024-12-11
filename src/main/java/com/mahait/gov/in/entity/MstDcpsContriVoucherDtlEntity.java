package com.mahait.gov.in.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "MST_DCPS_CONTRI_VOUCHER_DTLS")
public class MstDcpsContriVoucherDtlEntity {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MST_DCPS_CONTRI_VOUCHER_DTLS")
    private Long mstDcpsContriVoucherDtls;
    
    @Column(name = "YEAR_ID")
    private Integer yearId;

    @Column(name = "MONTH_ID")
    private Integer monthId;

    @Column(name = "TREASURY_CODE")
    private Long treasuryCode;

    @Column(name = "DDO_CODE")
    private String ddoCode;

    @Column(name = "BILL_GROUP_ID")
    private Long billGroupId;

    @Column(name = "VOUCHER_NO")
    private Long voucherNo;

    @Column(name = "VOUCHER_DATE")
    private Timestamp voucherDate;

    @Column(name = "VOUCHER_AMOUNT")
    private Float voucherAmount;

    @Column(name = "VOUCHER_STATUS")
    private Long voucherStatus;

    @Column(name = "REASON_FOR_REVERSION")
    private String reasonForReversion;

    @Column(name = "REMARKS_FOR_REJECTION")
    private String remarksForRejection;

    @Column(name = "MANUALLY_MATCHED")
    private Long manuallyMatched;

    @Column(name = "CREATED_USER_ID")
    private Long createdUserId;

    @Column(name = "CREATED_POST_ID")
    private Long createdPostId;

    @Column(name = "CREATED_DATE")
    private Timestamp createdDate;

    @Column(name = "UPDATED_USER_ID")
    private Long updatedUserId;

    @Column(name = "UPDATED_POST_ID")
    private Long updatedPostId;

    @Column(name = "UPDATED_DATE")
    private Timestamp updatedDate;

    @Column(name = "POST_EMPLR_CONTRI_STATUS")
    private Long postEmplrContriStatus;

    @Column(name = "EMPLR_VOUCHER_NO")
    private Long emplrVoucherNo;

    @Column(name = "EMPLR_VOUCHER_DATE")
    private Timestamp emplrVoucherDate;

    @Column(name = "EMPLR_BILL_NO")
    private String emplrBillNo;

    @Column(name = "EMPLR_YEAR_ID")
    private Long emplrYearId;

    @Column(name = "EMPLR_VOUCHER_AMOUNT")
    private Float emplrVoucherAmount;

    @Column(name = "REVERSION_FLAG")
    private Long reversionFlag;

    @Column(name = "STATUS")
    private Character status;

    @Column(name = "NIL_BILL_VNO")
    private Integer nilBillVno;

    @Column(name = "NIL_BILL_DT")
    private Timestamp nilBillDt;

    @Column(name = "EMPR_CREDIT_DT")
    private Timestamp emprCreditDt;

    @Column(name = "SCHEDULER_USED")
    private Character schedulerUsed;

  
}
