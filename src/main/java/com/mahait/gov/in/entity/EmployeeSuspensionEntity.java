package com.mahait.gov.in.entity;


import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="EMPLOYEE_SUSPENSION_RLT",schema="public")
public class EmployeeSuspensionEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="employee_suspension_id")
	private Integer employeeSuspensionId ;
	
	@Column(name="employee_id")
	private Integer employeeId ;
	
	
	@Column(name="sevaarth_id")
	private String sevaarthId ;
	
	@Column(name="DDO_CODE")
	private String ddocode ;
	
	@Column(name="ORDERNO")
	private String orderno ;
	
	
	@Column(name="PERCENTAGE")
	private Integer percentage;
    
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="ORDER_DATE")
	private Date orderDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="START_DATE")
	private Date startDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="END_DATE")
	private Date endDate;
	
	@Column(name="reason")
	private String reason;
	
	@Column(name = "created_user_id")
	private int createdUserId;
	
	@Column(name = "updated_user_id")
	private int updatedUserId;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "updated_date")
	private Date updatedDate;
	
	
	@Column(name = "is_active")
	private char isActive;


	public Integer getEmployeeSuspensionId() {
		return employeeSuspensionId;
	}


	public void setEmployeeSuspensionId(Integer employeeSuspensionId) {
		this.employeeSuspensionId = employeeSuspensionId;
	}


	public Integer getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	public String getReason() {
		return reason;
	}


	public void setReason(String reason) {
		this.reason = reason;
	}


	public int getCreatedUserId() {
		return createdUserId;
	}


	public void setCreatedUserId(int createdUserId) {
		this.createdUserId = createdUserId;
	}


	public int getUpdatedUserId() {
		return updatedUserId;
	}


	public void setUpdatedUserId(int updatedUserId) {
		this.updatedUserId = updatedUserId;
	}


	public Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	public Date getUpdatedDate() {
		return updatedDate;
	}


	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}


	public char getIsActive() {
		return isActive;
	}


	public void setIsActive(char isActive) {
		this.isActive = isActive;
	}





	public String getSevaarthId() {
		return sevaarthId;
	}


	public void setSevaarthId(String sevaarthId) {
		this.sevaarthId = sevaarthId;
	}


	public Integer getPercentage() {
		return percentage;
	}


	public void setPercentage(Integer percentage) {
		this.percentage = percentage;
	}


	public String getDdocode() {
		return ddocode;
	}


	public void setDdocode(String ddocode) {
		this.ddocode = ddocode;
	}


	public String getOrderno() {
		return orderno;
	}


	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}


	public Date getOrderDate() {
		return orderDate;
	}


	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}



	
}