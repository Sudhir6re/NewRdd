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
@Table(name="gr_order_documents",schema="public")
public class GROrderDocumentEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="gr_document_id")
    private Long grDocId;  
	
	
	@Column(name="grOrder_id")
	private Long grOrderId;  
	
	@Column(name="file_path")
    private String filePath;

	@Column(name = "CREATED_USER_ID")
	private Long createdUserId;
	
	@Column(name = "CREATED_DATE")
	private Date createdDate;
	
	@Column(name = "UPDATED_USER_ID")
	private Long updatedUserId;

	@Column(name = "UPDATED_DATE")
	private Date updatedDate;
	
	@Column(name = "IS_ACTIVE")
	private char isActive;

	

	
	

	
}
