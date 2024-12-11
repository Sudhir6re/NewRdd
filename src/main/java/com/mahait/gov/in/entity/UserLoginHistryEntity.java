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
@Table(name="user_login_history_dtls",schema="public")
public class UserLoginHistryEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
    private int id;  
	
	@Column(name = "login_ip")
	private String loginIp;
	

	@Column(name = "username")
	private String username;
	
	
	@Column(name = "login_date")
	private Date loginDate;
	
	
	@Column(name = "CREATED_USER_ID")
	private int createdUserId;
	
	@Column(name = "CREATED_DATE")
	private Date createdDate;
	
	@Column(name = "UPDATED_USER_ID")
	private int updatedUserId;

	@Column(name = "UPDATED_DATE")
	private Date updatedDate;
	
	@Column(name = "IS_ACTIVE")
	private Integer isActive;
	
	
	@Column(name = "status")
	private String status;

	
	
}
