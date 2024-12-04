package com.mahait.gov.in.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table(name = "Appointment")
public class AppoinmentEntity {
	
	    @Id
	    @Column(name = "APPOINTMENT_ID")
	    private Long appointmentId;

	  
	    @Column(name = "APPOINTMENT_NAME", nullable = false, length = 100)
	    private String appointmentName;
  
	}



