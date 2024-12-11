package com.mahait.gov.in.entity;



import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name="gpf_mst_details",schema="public")
public class MstGpfDetailsHistEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="gpf_id")
	private Long gpf_id;
	@Column(name="employee_id")
	private Long employeeId;
	@Column(name="account_maintain_by")
	private String accountmaintainby;
	@Column(name="pf_account_no")
	private String pfacno;
	@Column(name="pf_description")
	private String pfdescription;
	@Column(name="is_active")
	private String isactive;
	@Column(name="created_date")
	private Date createddate;
	@Column(name="created_id")
	private Long createdid;
	@Column(name="update_date")
	private Date updatedate;
	@Column(name="update_id")
	private Long updateid;
	@Column(name="sevaarth_id")
	private String sevaarthId;
	
	
/*	 @OneToMany(cascade = CascadeType.ALL, mappedBy = "mstGpfDetailsEntity", orphanRemoval = true) 
	  private List<MstEmployeeEntity> mstEmployeeEntity;*/
	
	 
/*	  @ManyToOne(fetch = FetchType.LAZY) 
	  @JoinColumn(name = "EMPLOYEE_ID",insertable = false, updatable = false) 
	  private MstEmployeeEntity mstEmployeeEntity; 
	  */
	  @OneToOne(fetch = FetchType.LAZY) 
	  @JoinColumn(name = "EMPLOYEE_ID",insertable = false, updatable = false) 
	  private MstEmployeeEntity mstEmployeeEntity; 
	

	
	
	
}
