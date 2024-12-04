package com.mahait.gov.in.model;

import java.io.Serializable;
import java.sql.Timestamp;

import jakarta.validation.constraints.NotEmpty;

import lombok.Data;


@Data
public class ZpRltDdoMapModel implements Serializable {
    
    private Long zpMapId;

    private Long zpDdoPostId;

    private Long reptDdoPostId;

    private Long finalDdoPostId;

    private Long specialDdoPostId;

    private String zpDdoCode;

    private String finalDdoCode;

    private String reptDdoCode;

    private String specialDdoCode;

    private Long zplevel;

    private Long langId;

    private Long createdUserId;

    private Timestamp createdDate;

    private Long updatedUserId;

    private Timestamp updatedDate;

    private Long createdPostId;

    private Long updatedPostId;
    
    private Long desginationId;
 
    @NotEmpty(message = "User's email cannot be empty.")
    private String cmbAdminOffice;
    
    @NotEmpty(message = "User's email cannot be empty.")
    private String cmbDistOffice;
    
    @NotEmpty(message = "User's email cannot be empty.")
    private String radioFinalLevel;
    
    @NotEmpty(message = "User's email cannot be empty.")
    private String txtRepDDOCode;
    
    @NotEmpty(message = "User's email cannot be empty.")
    private String txtFinalDDOCode;
    
    @NotEmpty(message = "User's email cannot be empty.")
    private String txtSpecialDDOCode;
    
    @NotEmpty(message = "User's email cannot be empty.")
    private String radioSalutation;
    
    @NotEmpty(message = "User's email cannot be empty.")
    private String txtDDOName;
    
    @NotEmpty(message = "User's email cannot be empty.")
    private String radioGender;
    
    @NotEmpty(message = "User's email cannot be empty.")
    private String txtTreasuryName;
    
    @NotEmpty(message = "User's email cannot be empty.")
    private String txtTreasuryCode;
    
    @NotEmpty(message = "User's email cannot be empty.")
    private String cmbSubTreasury;
    
    
    private String txtDDODsgn;
    @NotEmpty(message = "User's email cannot be empty.")
    
    private String txtOfficeName;
    
    @NotEmpty(message = "User's email cannot be empty.")
    private String txtDDOCode;
    
    @NotEmpty(message = "User's email cannot be empty.")
    private String txtMobileNo;
    
    @NotEmpty(message = "User's email cannot be empty.")
    private String txtEmailId;
    
}