
package com.mahait.gov.in.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

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
@Table(name="nominee_details_hst",schema="public")
public class MstNomineeDetailsHistEntity {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="nominee_id")
	private Long nomineeid;
	
	@Column(name="employee_id")
	private Long employeeId;
	@Column(name="relation")
	private String relation;
	@Column(name="nominee_name")
	private String nomineename;
	@Column(name="nominee_address")
	private String nomineeaddress;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="dob")
	private Date dob;
	
	
	@Column(name="percent_share")
	private Integer percent_share;
	
	@Column(name="guardian_name")
	private String guardianName;

	@Column(name="major_minor")
	private String majorMinor;
	
	
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
	
	
	@ManyToOne(fetch = FetchType.LAZY) 
	  @JoinColumn(name = "EMPLOYEE_ID",insertable = false, updatable = false) 
	  private MstEmployeeEntity mstEmployeeEntity; 

}
