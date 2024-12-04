package com.mahait.gov.in.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Entity
@Table(name = "org_user_mst")
public class OrgUserMst implements Serializable {

	private static final long serialVersionUID = 1L;


	@Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    
  
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CREATED_BY_POST", referencedColumnName = "POST_ID", nullable = false)
    @Fetch(FetchMode.SELECT)
    private OrgPostMst createdByPost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UPDATED_BY_POST", referencedColumnName = "POST_ID")
    @Fetch(FetchMode.SELECT)
    private OrgPostMst updatedByPost;

    
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CREATED_BY", referencedColumnName = "USER_ID", nullable = false)
    @Fetch(FetchMode.SELECT)
    private OrgUserMst createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UPDATED_BY", referencedColumnName = "USER_ID")
    @Fetch(FetchMode.SELECT)
    private OrgUserMst updatedBy;
    
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_lookup_id", nullable = false)
    @Fetch(FetchMode.SELECT)
    private CmnLookupMst cmnLookupMst;
    


    @Column(name = "user_name", length = 20, nullable = false)
    private String userName;

    @Column(name = "secret_que_other", length = 255, nullable = false)
    private String secretQueOther;

    @Column(name = "secret_answer", length = 255, nullable = false)
    private String secretAnswer;

    @Column(name = "secret_que_code", length = 30, nullable = false)
    private String secretQueCode;

    @Column(name = "password", length = 128, nullable = false)
    private String password;

    @Column(name = "password_sha", length = 128)
    private String passwordSha;

    @Column(name = "start_date", nullable = false)
    private Timestamp startDate;

    @Column(name = "end_date")
    private Timestamp endDate;

    @Column(name = "activate_flag", nullable = false)
    private Long activateFlag;

    @Column(name = "created_date", nullable = false)
    private Timestamp createdDate;

    @Column(name = "updated_date")
    private Timestamp updatedDate;

    @Column(name = "pwdchanged_date")
    private Timestamp pwdchangedDate;

    @Column(name = "unlock_time")
    private Timestamp unlockTime;

    @Column(name = "invalid_login_cnt")
    private Integer invalidLoginCnt;

    @Column(name = "ip_login")
    private Integer ipLogin;

    @Column(name = "always_login")
    private Integer alwaysLogin;
    
    
    @Column(name = "app_code")
    private Integer appCode;
    
    
    
    

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "firstlogin", length = 1)
    private String firstlogin;
    
    
    
    @Column(name = "ddo_code", length = 1)
    private String ddoCode;
    
    
    
  
  
    

    @OneToMany(mappedBy = "orgUserMst",  fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<OrgUserpostRlt> orgUserpostRlts;

    

   /* @OneToMany(mappedBy = "orgUserMst", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<OrgUserpostRlt> orgUserpostRlts;
    
   
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private OrgUserMst orgUserMst;
*/

    
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    private MstRoleEntity mstRoleEntity;
    
    
    


    /*@OneToMany(mappedBy = "orgUserMstByCreatedBy")
    private Set<OrgEmpMst> orgEmpMstsForCreatedBy;

    @OneToMany(mappedBy = "orgUserMstByUpdatedBy")
    private Set<OrgEmpMst> orgEmpMstsForUpdatedBy;
*/
	
 
    @Transient
  	private Long locId;
    
    
    @Transient
  	private Long postId;
    
    @Column(name = "reset_token")
    private String resetToken;
    
    @Column(name = "reset_token_expiry", length = 1)
    private LocalDateTime resetTokenExpiry;
    
    
    @Transient
  	private String emailId;
    

    
}

