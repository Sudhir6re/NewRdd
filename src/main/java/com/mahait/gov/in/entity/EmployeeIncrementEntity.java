package com.mahait.gov.in.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "employee_increment", schema = "public")
public class EmployeeIncrementEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "basic_pay_increment_id")
	private Long basicPayIncrementId;
	
	@Column(name = "sevaarth_id")
	private String sevaarthId;

	@Column(name = "employee_id")
	private Long employeeId;

	@Column(name = "current_basic_pay")
	private Double currentBasicPay;

	@Column(name = "current_basic_level_id")
	private Long currentBasicLevelId;

	@Column(name = "pre_basic_pay")
	private Double preBasicPay;

	@Column(name = "increment_order_no")
	private String incrementOrderNo;

	@Column(name = "increment_order_date")
	private Date incrementOrderDate;

	@Column(name = "increment_basic_pay_sal")
	private Double incrementBasicPaySal;

	@Column(name = "increment_basic_level_id")
	private Long incrementBasicLevelId;

	@Column(name = "effective_from_date")
	private Date effective_from_date;

	@Column(name = "to_increment_date")
	private Date to_increment_date;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "updated_date")
	private Date updatedDate;

	@Column(name = "remark")
	private String remark;

	@Column(name = "is_active")
	private Character isActive;

	@Column(name = "month")
	private Integer month;
	
	@Column(name = "year")
	private Integer year;

	@Column(name = "CREATED_USER_ID")
	private Long createdUserId;

	@Column(name = "UPDATED_USER_ID")
	private Long updatedUserId;

	@Column(name = "DDO_CODE")
	private String ddoCode;
	
	@Column(name = "paycommission_id")
	private Long PaycommissionId;

}
