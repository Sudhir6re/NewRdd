package com.mahait.gov.in.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "RLT_ZP_DDO_MAP")
@EqualsAndHashCode(of = "zpMapId")
public class ZpRltDdoMap implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ZP_MAP_ID", precision = 10, scale = 0)
	private Long zpMapId;

	@Column(name = "ZP_DDO_POST_ID", length = 10)
	private Long zpDdoPostId;

	@Column(name = "REPT_DDO_POST_ID", length = 10)
	private Long reptDdoPostId;

	@Column(name = "FINAL_DDO_POST_ID", length = 10)
	private Long finalDdoPostId;

	@Column(name = "SPECIAL_DDO_POST_ID", length = 10)
	private Long specialDdoPostId;

	@Column(name = "ZP_DDO_CODE", precision = 150)
	private String zpDdoCode;

	@Column(name = "FINAL_DDO_CODE", precision = 150)
	private String finalDdoCode;

	@Column(name = "REPT_DDO_CODE", precision = 150)
	private String reptDdoCode;

	@Column(name = "SPECIAL_DDO_CODE", precision = 150)
	private String specialDdoCode;

	@Column(name = "ZPLEVEL", precision = 150)
	private Long zplevel;

	@Column(name = "LANG_ID", precision = 10, scale = 0)
	private Long langId;

	@Column(name = "CREATED_USER_ID", precision = 10, scale = 0)
	private Long createdUserId;

	@Column(name = "CREATED_DATE", length = 20)
	private Timestamp createdDate;

	@Column(name = "UPDATED_USER_ID", precision = 10, scale = 0)
	private Long updatedUserId;

	@Column(name = "UPDATED_DATE", length = 20)
	private Timestamp updatedDate;

	@Column(name = "CREATED_POST_ID", precision = 10, scale = 0)
	private Long createdPostId;

	@Column(name = "UPDATED_POST_ID", precision = 10, scale = 0)
	private Long updatedPostId;

	@Column(name = "STATUS", precision = 150)
	private Long status;

	/*
	 * @ManyToMany(mappedBy = "zpRltDdoMap", fetch = FetchType.LAZY, cascade =
	 * CascadeType.ALL) private Set<DdoOffice> setDdoOffice;
	 */

}
