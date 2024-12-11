package com.mahait.gov.in.entity;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cmn_district_mst")
public class CmnDistrictMst {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "district_id")
    private Long districtId;

    @Column(name = "state_id")
    private Long stateId;

    @Column(name = "lang_id")
    private Integer langId;

    @Column(name = "district_name")
    private String districtName;

    @Column(name = "population")
    private Long population;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "updated_by")
    private Long updatedBy;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @Column(name = "created_by_post")
    private Long createdByPost;

    @Column(name = "updated_by_post")
    private Long updatedByPost;

    @Column(name = "district_code")
    private String districtCode;

    @Column(name = "district_type")
    private Long districtType;

    @Column(name = "activate_flag")
    private Integer activateFlag;

    @Column(name = "region_code")
    private String regionCode;

    @Column(name = "region_order")
    private String regionOrder;

}
