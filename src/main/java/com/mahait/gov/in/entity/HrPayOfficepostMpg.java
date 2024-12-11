package com.mahait.gov.in.entity;

import java.util.Date;

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
import lombok.Data;

@Data
@Entity
@Table(name = "HR_PAY_OFFICEPOST_MPG")
public class HrPayOfficepostMpg {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "OFFICEPOST_ID", precision = 20, scale = 0)
	private Long officePostId;
	

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
	@JoinColumn(name = "OFFICE_ID", nullable = false)
	private DdoOffice ddoOffice;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "POST_ID", nullable = false)
	private OrgPostMst orgPostMstByPostId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "db_id")
	private CmnDatabaseMst cmnDatabaseMst;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "loc_id")
	private CmnLocationMst cmnLocationMst;

	@Column(name = "START_DATE", nullable = false)
	private Date startDate;

	@Column(name = "END_DATE")
	private Date endDate;

	@Column(name = "CREATED_DATE")
	private Date createdDate;

	@Column(name = "UPDATED_DATE")
	private Date updatedDate;

}
