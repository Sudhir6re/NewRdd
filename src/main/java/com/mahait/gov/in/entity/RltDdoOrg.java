package com.mahait.gov.in.entity;
import java.util.Date;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "RLT_DDO_ORG")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "ddoCache")
public class RltDdoOrg {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DDO_ORG_ID", precision = 20, scale = 0)
    private Long ddoOrgId;

    @Column(name = "ACTIVATE_FLAG", precision = 20, scale = 0, nullable = false)
    private Long activateFlag;

    @Column(name = "START_DATE", length = 7)
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(name = "END_DATE", length = 7)
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Column(name = "CREATED_USER_ID", precision = 20, scale = 0, nullable = false)
    private Long createdUserId;

    @Column(name = "CREATED_POST_ID", precision = 20, scale = 0, nullable = false)
    private Long createdPostId;

    @Column(name = "CREATED_DATE", length = 7, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "UPDATED_USER_ID", precision = 20, scale = 0)
    private Long updatedUserId;

    @Column(name = "UPDATED_POST_ID", precision = 20, scale = 0)
    private Long updatedPostId;

    @Column(name = "UPDATED_DATE", length = 7)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    @Column(name = "DB_ID", precision = 20, scale = 0)
    private Long dbId;

    @Column(name = "DDO_CODE", length = 15, nullable = false)
    private String ddoCode;

    @Column(name = "LOCATION_CODE", length = 10)
    private String locationCode;

    @Column(name = "TRN_COUNTER", precision = 11, scale = 0)
    private Integer trnCounter;
}
