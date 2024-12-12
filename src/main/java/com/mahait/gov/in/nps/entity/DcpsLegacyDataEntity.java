package com.mahait.gov.in.nps.entity;

import java.sql.Timestamp;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "DCPS_LEGACY_DATA")
public class DcpsLegacyDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "NPS_ID", nullable = false, updatable = false)
    private Long npsId;

    @Column(name = "DDO_CODE", nullable = false, length = 15)
    private String ddoCode;

    @Column(name = "SEVARTH_ID", nullable = false, length = 21)
    private String sevarthId;

    @Column(name = "DCPS_ID", nullable = false, length = 21)
    private String dcpsId;

    @Column(name = "DCPS_EMP_ID", nullable = true)
    private Long dcpsEmpId;

    @Column(name = "EMP_CONTRI")
    private Float empContri;

    @Column(name = "EMPLOYER_CONTRI")
    private Float employerContri;

    @Column(name = "EMP_INT")
    private Float empInt;

    @Column(name = "EMPLOYER_INT")
    private Float employerInt;

    @Column(name = "TOTAL")
    private Float total;

    @Column(name = "YEAR", nullable = false)
    private Short year;

    @Column(name = "MONTH", nullable = false)
    private Short month;

    @Column(name = "STATUS", nullable = false, length = 1)
    private String status;

    @Column(name = "CREATED_DATE", nullable = false)
    private Timestamp createdDate;

    @Column(name = "CREATED_POST_ID")
    private Long createdPostId;

    @Column(name = "UPDATED_DATE")
    private Timestamp updatedDate;

    @Column(name = "UPDATED_POST_ID")
    private Long updatedPostId;

    @Column(name = "APPROVAL_DATE")
    private Timestamp approvalDate;

    @Column(name = "REMARKS", length = 100)
    private String remarks;

    @Column(name = "PERIOD", length = 25)
    private String period;

    @Column(name = "CONTRI_START_DATE")
    private Date contriStartDate;

    @Column(name = "CONTRI_END_DATE")
    private Date contriEndDate;

    @Column(name = "BATCH_ID", length = 14)
    private String batchId;

    @Column(name = "REJECTION_DATE")
    private Timestamp rejectionDate;

    @Column(name = "VOUCHER_DATE")
    private Timestamp voucherDate;

    @Column(name = "BANK_REFNO", length = 30)
    private String bankRefno = "0";

    @Column(name = "BDS_NO", length = 30)
    private String bdsNo = "0";

    @Column(name = "VOUCHER_NO")
    private Long voucherNo = 0L;

 
}

