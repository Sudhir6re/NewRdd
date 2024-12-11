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
@EqualsAndHashCode(of = "gradeId")
@Entity
@Table(name = "org_grade_mst")
public class OrgGradeMst implements Serializable {

	@Id
	@Column(name = "Grade_Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long gradeId;

	@Column(name = "Grade_Name", length = 160, nullable = false)
	private String gradeName;

	@Column(name = "Grade_Desc", length = 400)
	private String gradeDesc;

	@Column(name = "Grade_Code", length = 15, nullable = false)
	private String gradeCode;

    
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
	  @JoinColumn(name = "Lang_Id", nullable = false) private CmnLanguageMst
	  cmnLanguageMst;
	  
	  
	  
	  
	 

	/*
	 * @ManyToOne(fetch = FetchType.LAZY)
	 * 
	 * @JoinColumn(name = "Created_By", nullable = false) private OrgUserMst
	 * orgUserMstByCreatedBy;
	 * 
	 * @Column(name = "Created_Date", nullable = false) private Timestamp
	 * createdDate;
	 * 
	 * @ManyToOne(fetch = FetchType.LAZY)
	 * 
	 * @JoinColumn(name = "Created_By_Post", nullable = false) private OrgPostMst
	 * orgPostMstByCreatedByPost;
	 * 
	 * @ManyToOne(fetch = FetchType.LAZY)
	 * 
	 * @JoinColumn(name = "Updated_By") private OrgUserMst orgUserMstByUpdatedBy;
	 * 
	 * 
	 * @Column(name = "Activate_flag", nullable = false) private Long activateFlag;
	 * 
	 * @ManyToOne(fetch = FetchType.LAZY)
	 * 
	 * @JoinColumn(name = "Updated_By_Post") private OrgPostMst
	 * orgPostMstByUpdatedByPost;
	 * 
	 * @OneToMany(mappedBy = "orgGradeMst") private Set<OrgEmpMst> orgEmpMsts;
	 */

	@Column(name = "Updated_Date")
	private Timestamp updatedDate;

	@Column(name = "Activate_flag", nullable = false)
	private Long activateFlag;

	
	
}
