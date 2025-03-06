package com.mahait.gov.in.entity;
import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "HR_PAY_ORDER_MST")
public class HrPayOrderMst implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private Long orderId;

    @Column(name = "ORDER_NAME", length = 100)
    private String orderName;

    @Column(name = "ORDER_DATE")
    private Timestamp orderDate;

    @Column(name = "CREATED_BY")
    private Long createdBy;

    @Column(name = "CREATED_BY_POST")
    private Long createdByPost;

    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @Column(name = "UPDATED_BY")
    private Long updatedBy;

    @Column(name = "UPDATED_BY_POST")
    private Long updatedByPost;

    @Column(name = "UPDATED_DATE")
    private Date updatedDate;

    @Column(name = "TRN_COUNTER")
    private Integer trnCounter;

    @Column(name = "LOCATION_CODE", length = 20)
    private String locationCode;

    @Column(name = "LANG_ID")
    private Short langId;

    @Column(name = "ATTACHMENT_ID")
    private Long attachmentId;

    @Column(name = "END_DATE")
    private Date endDate;

    @Column(name = "GR_TYPE", precision = 11, scale = 0)
    private BigDecimal grType;

    @Column(name = "DDO_CODE", length = 20)
    private String ddoCode;

   
}
