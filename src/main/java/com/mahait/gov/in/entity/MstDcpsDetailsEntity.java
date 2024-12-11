package com.mahait.gov.in.entity;


import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="dcps_details_mst",schema="public")
public class MstDcpsDetailsEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="dcps_id")
	private Long dcpsid;
	@Column(name="employee_id")
	private Long employeeId;
	@Column(name="dcps_no")
	private String dcpsno;
	@Column(name="pf_ac_no")
	private String pfacno;
	@Column(name="account_maintain_by")
	private String accountmaintainby;
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

	
}
