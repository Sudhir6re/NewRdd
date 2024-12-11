package com.mahait.gov.in.entity;

import java.io.Serializable;
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
@Table(name = "RLT_DCPS_DDO_SCHEMES", schema = "")
public class RltDCPSDdoSchemeEntity implements Serializable {

	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DDO_SCHEMES_ID")
    private Long id;

    @Column(name = "SCHEME_CODE", length = 10, nullable = false)
    private String schemeCode;

    @Column(name = "DDO_CODE", length = 15, nullable = false)
    private String ddoCode;

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
    private Date createdDate;

    @Column(name = "UPDATED_POST_ID")
    private Long updatedPostId;

    @Column(name = "UPDATED_USER_ID")
    private Long updatedUserId;

    @Column(name = "UPDATED_DATE")
    private Date updatedDate;

    @Column(name = "SUB_SCHEME_CODE", length = 10)
    private String subSchemeCode;

    // Constructors (Omitted for brevity, but you should provide appropriate constructors)

    // Additional methods (e.g., equals, hashCode, toString) can be added as needed
}

