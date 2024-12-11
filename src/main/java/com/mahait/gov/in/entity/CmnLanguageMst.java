package com.mahait.gov.in.entity;

import java.util.Date;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "cmn_language_mst")
@Cacheable
public class CmnLanguageMst {

    @Id
    @Column(name = "lang_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long langId;

    @Column(name = "lang_name", length = 20, nullable = false)
    private String langName;

    @Column(name = "lang_short_name", length = 10, nullable = false)
    private String langShortName;

    @Column(name = "created_by", nullable = false)
    private Long createdBy;

    @Column(name = "created_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "created_by_post", nullable = false)
    private Long createdByPost;

    @Column(name = "updated_by", nullable = false)
    private Long updatedBy;

    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    @Column(name = "updated_by_post")
    private Long updatedByPost;

    @Column(name = "activate_flag")
    private Long activateFlag;
}
