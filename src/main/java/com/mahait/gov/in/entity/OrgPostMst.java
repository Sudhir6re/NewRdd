package com.mahait.gov.in.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@EqualsAndHashCode(of = "postId")
@Entity
@Table(name = "org_post_mst")
public class OrgPostMst implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@Id
	@Column(name = "post_id")
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long postId;
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATED_BY_POST", referencedColumnName = "POST_ID", nullable = false)
	@Fetch(FetchMode.SELECT)
	private OrgPostMst createdByPost;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UPDATED_BY_POST", referencedColumnName = "POST_ID")
	@Fetch(FetchMode.SELECT)
	private OrgPostMst updatedByPost;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATED_BY", referencedColumnName = "USER_ID", nullable = false)
	@Fetch(FetchMode.SELECT)
	private OrgUserMst createdBy;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UPDATED_BY", referencedColumnName = "USER_ID")
	@Fetch(FetchMode.SELECT)
	private OrgUserMst updatedBy;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lookup_id", nullable = false)
	private CmnLookupMst cmnLookupMst;

	/*
	 * @Column(name = "lookup_id") // Corrected mapping private Long lookupId;
	 */

	@Column(name = "parent_post_id", nullable = false)
	private Long parentPostId;

	@Column(name = "post_level_id", nullable = false)
	private Long postLevelId;

	@Column(name = "start_date", length = 19)
	private Timestamp startDate;

	@Column(name = "end_date", length = 19)
	private Timestamp endDate;

	@Column(name = "activate_flag", nullable = false)
	private Long activateFlag;

	@Column(name = "created_date", length = 19, nullable = false)
	private Timestamp createdDate;

	@Column(name = "updated_date", length = 19)
	private Timestamp updatedDate;

	@Column(name = "location_code", length = 20, nullable = false)
	private String locationCode;

	@Column(name = "branch_code", length = 20)
	private String branchCode;

	@Column(name = "dsgn_code", length = 40, nullable = false)
	private String dsgnCode;

	@Column(name = "order_id")
	private Long orderId;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "order_date")
	private Date orderDate;
	
    @Column(name = "DDO_CODE", length = 20)
    private String ddoCode;


//    @OneToMany(mappedBy = "updatedByPost", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private Set<OrgUserMst> orgUserMstsForUpdatedByPost;
//
//    @OneToMany(mappedBy = "updatedByPost", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private Set<MstDesignationEntity> orgDesignationMstsForUpdatedByPost;
//
//    @OneToMany(mappedBy = "updatedByPost", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private Set<OrgEmpMst> orgEmpMstsForUpdatedByPost;
//
//    @OneToMany(mappedBy = "updatedByPost", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private Set<OrgPostMst> orgPostMstsForUpdatedByPost;
//
//    @OneToMany(mappedBy = "createdByPost", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private Set<OrgEmpcontactMst> orgEmpcontactMstsForCreatedByPost;
//
//    @OneToMany(mappedBy = "createdByPost", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private Set<OrgEmpaddressMst> orgEmpaddressMstsForCreatedByPost;
//
//    @OneToMany(mappedBy = "updatedByPost", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private Set<OrgUserpostRlt> orgUserpostRltsForUpdatedByPost;
//    
//    @OneToMany(mappedBy = "createdByPost", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private Set<OrgUserMst> orgUserMstsForCreatedByPost;
//
//    @OneToMany(mappedBy = "orgPostMstByPostId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private Set<OrgUserpostRlt> orgUserpostRltsForPostId;
//
//    @OneToMany(mappedBy = "createdByPost", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private Set<MstDesignationEntity> orgDesignationMstsForCreatedByPost;
//
//    @OneToMany(mappedBy = "createdByPost", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private Set<OrgPostMst> orgPostMstsForCreatedByPost;
//
//    @OneToMany(mappedBy = "createdByPost", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private Set<OrgEmpMst> orgEmpMstsForCreatedByPost;
//
//    @OneToMany(mappedBy = "createdByPost", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private Set<OrgDepartmentMst> orgDepartmentMstsForCreatedByPost;
//
//    @OneToMany(mappedBy = "updatedByPost", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private Set<OrgEmpcontactMst> orgEmpcontactMstsForUpdatedByPost;
//
//    @OneToMany(mappedBy = "updatedByPost", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private Set<OrgEmpaddressMst> orgEmpaddressMstsForUpdateByPost;
//
//    @OneToMany(mappedBy = "orgPostMst", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private Set<OrgPostDetailsRlt> orgPostDetailsRlt;*/

    
    /*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_type_lookup_id")
    private CmnLookupMst postTypeLookupId;
    
    */
    

    @Column(name = "OFFICE_ID")
    private Long officeId;
    
    
    @Column(name = "status_lookup_id")
    private Long statusLookupId;
    
    
    
    

	/*
	 * @OneToMany(mappedBy = "createdByPost", fetch = FetchType.LAZY, cascade =
	 * CascadeType.ALL) private Set<OrgDepartmentMst>
	 * orgDepartmentMstsForUpdatedByPost;
	 * 
	 * @OneToMany(mappedBy = "createdByPost", fetch = FetchType.LAZY, cascade =
	 * CascadeType.ALL) private Set<OrgUserpostRlt> orgUserpostRltsForCreatedByPost;
	 * 
	 * @OneToMany(mappedBy = "updatedByPost", fetch = FetchType.LAZY, cascade =
	 * CascadeType.ALL) private Set<OrgUserMst> orgUserMstsForUpdatedByPost;
	 * 
	 * @OneToMany(mappedBy = "updatedByPost", fetch = FetchType.LAZY, cascade =
	 * CascadeType.ALL) private Set<MstDesignationEntity>
	 * orgDesignationMstsForUpdatedByPost;
	 * 
	 * @OneToMany(mappedBy = "updatedByPost", fetch = FetchType.LAZY, cascade =
	 * CascadeType.ALL) private Set<OrgEmpMst> orgEmpMstsForUpdatedByPost;
	 * 
	 * @OneToMany(mappedBy = "updatedByPost", fetch = FetchType.LAZY, cascade =
	 * CascadeType.ALL) private Set<OrgPostMst> orgPostMstsForUpdatedByPost;
	 * 
	 * @OneToMany(mappedBy = "createdByPost", fetch = FetchType.LAZY, cascade =
	 * CascadeType.ALL) private Set<OrgEmpcontactMst>
	 * orgEmpcontactMstsForCreatedByPost;
	 * 
	 * @OneToMany(mappedBy = "createdByPost", fetch = FetchType.LAZY, cascade =
	 * CascadeType.ALL) private Set<OrgEmpaddressMst>
	 * orgEmpaddressMstsForCreatedByPost;
	 * 
	 * @OneToMany(mappedBy = "updatedByPost", fetch = FetchType.LAZY, cascade =
	 * CascadeType.ALL) private Set<OrgUserpostRlt> orgUserpostRltsForUpdatedByPost;
	 * 
	 * @OneToMany(mappedBy = "createdByPost", fetch = FetchType.LAZY, cascade =
	 * CascadeType.ALL) private Set<OrgUserMst> orgUserMstsForCreatedByPost;
	 * 
	 * @OneToMany(mappedBy = "orgPostMstByPostId", fetch = FetchType.LAZY, cascade =
	 * CascadeType.ALL) private Set<OrgUserpostRlt> orgUserpostRltsForPostId;
	 * 
	 * @OneToMany(mappedBy = "createdByPost", fetch = FetchType.LAZY, cascade =
	 * CascadeType.ALL) private Set<MstDesignationEntity>
	 * orgDesignationMstsForCreatedByPost;
	 * 
	 * @OneToMany(mappedBy = "createdByPost", fetch = FetchType.LAZY, cascade =
	 * CascadeType.ALL) private Set<OrgPostMst> orgPostMstsForCreatedByPost;
	 * 
	 * @OneToMany(mappedBy = "createdByPost", fetch = FetchType.LAZY, cascade =
	 * CascadeType.ALL) private Set<OrgEmpMst> orgEmpMstsForCreatedByPost;
	 * 
	 * @OneToMany(mappedBy = "createdByPost", fetch = FetchType.LAZY, cascade =
	 * CascadeType.ALL) private Set<OrgDepartmentMst>
	 * orgDepartmentMstsForCreatedByPost;
	 * 
	 * @OneToMany(mappedBy = "updatedByPost", fetch = FetchType.LAZY, cascade =
	 * CascadeType.ALL) private Set<OrgEmpcontactMst>
	 * orgEmpcontactMstsForUpdatedByPost;
	 * 
	 * @OneToMany(mappedBy = "updatedByPost", fetch = FetchType.LAZY, cascade =
	 * CascadeType.ALL) private Set<OrgEmpaddressMst>
	 * orgEmpaddressMstsForUpdateByPost;
	 * 
	 * @OneToMany(mappedBy = "orgPostMst", fetch = FetchType.LAZY, cascade =
	 * CascadeType.ALL) private Set<OrgPostDetailsRlt> orgPostDetailsRlt;
	 */

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_type_lookup_id")
	private CmnLookupMst postTypeLookupId;

}
