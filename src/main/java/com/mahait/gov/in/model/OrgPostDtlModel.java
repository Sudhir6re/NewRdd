package com.mahait.gov.in.model;

import java.io.Serializable;

import com.mahait.gov.in.entity.CmnLanguageMst;

import lombok.Data;


@Data
public class OrgPostDtlModel implements Serializable{
	
	private CmnLanguageMst cmnLanguageMst;

	private long postId;

	private String postName;



	


}
