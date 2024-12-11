package com.mahait.gov.in.entity;

import java.util.Date;
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
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cmn_location_mst")
public class CmnLocationMst {

    @Id
    @Column(name = "loc_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long locId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "lang_id", nullable = false)
    private CmnLanguageMst cmnLanguageMst;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_lookup_id", nullable = false)
    private CmnLookupMst cmnLookupMst;

    @Column(name = "loc_name", length = 100, nullable = false)
    private String locName;

    @Column(name = "loc_short_name", length = 15, nullable = false)
    private String locShortName;

    @Column(name = "department_id", nullable = false)
    private Long departmentId;

    @Column(name = "parent_loc_id", nullable = false)
    private Long parentLocId;

    @Column(name = "LOC_ADDR_1", length = 50)
    private String locAddr1;

    @Column(name = "LOC_ADDR_2", length = 50)
    private String locAddr2;

    @Column(name = "LOC_CITY_ID")
    private Long locCityId;

    @Column(name = "LOC_DISTRICT_ID")
    private Long locDistrictId;

    @Column(name = "LOC_TALUKA_ID")
    private Long locTalukaId;

    @Column(name = "LOC_STATE_ID")
    private Long locStateId;

    @Column(name = "loc_pin", length = 10, nullable = false)
    private String locPin;

    @Column(name = "start_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Column(name = "end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @Column(name = "activate_flag", nullable = false)
    private Long activateFlag;


    
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
    
    
    

    @Column(name = "created_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;


    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    @Column(name = "LOCATION_CODE", length = 20, nullable = false)
    private String locationCode;

    @Column(name = "OFFICE_CODE", length = 20)
    private String officeCode;

    @OneToMany(mappedBy = "cmnLocationMst", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CmnBranchMst> cmnBranchMsts;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "LOC_CATEGORY_ID")
    private CmnLookupMst cmnLookupMstLocCategory;
    
//    @OneToMany(mappedBy = "cmnLocationMst",  fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private Set<CmnLocationMst> cmnLocationMst;

}
