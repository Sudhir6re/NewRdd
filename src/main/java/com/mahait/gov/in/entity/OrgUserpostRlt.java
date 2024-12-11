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
@EqualsAndHashCode(of = "empPostId")
@Entity
@Table(name = "org_userpost_rlt")
public class OrgUserpostRlt implements Serializable {

	@Id
	@Column(name = "emp_post_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long empPostId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private OrgUserMst orgUserMst;
	
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id", nullable = false)
	private OrgPostMst orgPostMstByPostId;


	@Column(name = "start_date", nullable = false)
	private Timestamp startDate;

	@Column(name = "end_date")
	private Timestamp endDate;

	@Column(name = "activate_flag", nullable = false)
	private Long activateFlag;
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATED_BY", referencedColumnName = "USER_ID", nullable = false)
	@Fetch(FetchMode.SELECT)
	private OrgUserMst createdBy;
	
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATED_BY_POST", referencedColumnName = "POST_ID", nullable = false)
	@Fetch(FetchMode.SELECT)
	private OrgPostMst createdByPost;
	
	

	@Column(name = "created_date", nullable = false)
	private Timestamp createdDate;
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UPDATED_BY", referencedColumnName = "USER_ID")
	@Fetch(FetchMode.SELECT)
	private OrgUserMst updatedBy;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UPDATED_BY_POST", referencedColumnName = "POST_ID")
	@Fetch(FetchMode.SELECT)
	private OrgPostMst updatedByPost;
	

	@Column(name = "updated_date")
	private Timestamp updatedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userpost_type_lookup_id",referencedColumnName = "lookup_id", nullable = false )
    private CmnLookupMst cmnLookupUserPostType;
 

}
