package com.mahait.gov.in.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Table(name="reset_password_mst")
@Data
@Entity
public class ResetPasswordEntity {
  
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;
    private LocalDateTime expiryDate;

    @OneToOne
    private OrgUserMst user;

}