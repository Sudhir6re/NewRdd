package com.mahait.gov.in.entity;


import java.io.Serializable;
import java.sql.Timestamp;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "postDetailId")
@Entity
@Table(name = "org_post_details_rlt")
public class OrgPostDetailsRlt implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "post_detail_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postDetailId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private OrgPostMst orgPostMst;
   

    @Column(name = "post_name", length = 100, nullable = false)
    private String postName;

    @Column(name = "post_short_name", length = 60, nullable = false)
    private String postShortName;

    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loc_id", nullable = false)
    private CmnLocationMst cmnLocationMst;
    
    
  /*  @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dsgn_id", nullable = false)
    private OrgDesignationMst orgDesignationMst;*/
    
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dsgn_id", nullable = false)
    private MstDesignationEntity orgDesignationMst;
    
    
//    /lObjOrgDesigmMst
    
    
    
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id")
    private CmnBranchMst cmnBranchMst;
    
    


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lang_id", nullable = false)
    private CmnLanguageMst cmnLanguageMst;

    
    

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CREATED_BY", referencedColumnName = "USER_ID", nullable = false)
    @Fetch(FetchMode.SELECT)
    private OrgUserMst createdBy;
    
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CREATED_BY_POST", referencedColumnName = "POST_ID", nullable = false)
    @Fetch(FetchMode.SELECT)
    private OrgPostMst createdByPost;

    
    
    @Column(name = "CREATED_DATE", length = 19, nullable = false)
    private Timestamp createdDate;

    


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UPDATED_BY", referencedColumnName = "USER_ID")
    @Fetch(FetchMode.SELECT)
    private OrgUserMst updatedBy;
    

    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UPDATED_BY_POST", referencedColumnName = "POST_ID")
    @Fetch(FetchMode.SELECT)
    private OrgPostMst updatedByPost;

    

    @Column(name = "UPDATED_DATE", length = 19)
    private Timestamp updatedDate;
    
    
	@Column(name = "IS_VACANT")
	private Integer  isVancant;
    
    /*

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_category")
    private CmnLookupMst postCategory;
    */
/*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lookup_id", nullable = false)
    private CmnLookupMst cmnLookupMst;
    
    

    @JoinColumn(name = "userpost_type_lookup_id", referencedColumnName = "lookup_id", nullable = true)
    private CmnLookupMst cmnLookupMst;
    
    
    @JoinColumn(name = "lookup_id", referencedColumnName = "lookup_id", nullable = true)
    private CmnLookupMst cmnLookupMst1;

    
    
    */
    
    
    
    
}
