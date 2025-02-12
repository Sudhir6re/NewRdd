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
@Table(name = "HR_PAY_EKUBER_RECORD_MST_DTLS")
public class HrPayEkuberRecordMstDtls {

    @Id
    @Column(name = "PAYMENT_REF_NO")
    private String paymentRefNo;

    @Column(name = "BULK_UPLOAD_FLAG")
    private String bulkUploadFlag;

    @Column(name = "BILL_IDENTIFIER")
    private String billId;

    @Column(name = "TREASURY_CODE")
    private String treasuryCode;

    @Column(name = "DDO_CODE")
    private String ddoCode;

    @Column(name = "SEVARTH_ID")
    private String sevarthId;

    @Column(name = "BENEF_NAME")
    private String benefName;

    @Column(name = "ACC_NO")
    private String accNo;

    @Column(name = "IFSC_CODE")
    private String ifscCode;

    @Column(name = "AMOUNT")
    private String amount;

    @Column(name = "PAY_BY_DATE")
    private String payBydate;

    @Column(name = "SCHEME_CODE")
    private String schemeCode;

    @Column(name = "DDO_BILL_NO")
    private String ddoBillNo;

    @Column(name = "AUTH_NO")
    private String authNo;

    @Column(name = "BILL_DATE")
    private String billDate;

    @Column(name = "NARRATION")
    private String narration;

    @Column(name = "NO_OF_PAYEES")
    private String noOfPayees;

    @Column(name = "BILL_NET_AMT")
    private String billNetAmt;

    @Column(name = "EKUBER_FLAG")
    private String ekuberFlag;

    @Column(name = "FINANCIAL_YEAR")
    private String financialYear;

    @Column(name = "EMPLOYEE_TYPE")
    private String employeeType;

    @Column(name = "REPT_DDO_CODE")
    private String reptDdoCode;

    @Column(name = "TO_DDO_CODE")
    private String toDdoCode;
    
    

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_DATE")
    private Date createdDate;
    
    
}
