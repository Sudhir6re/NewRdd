package com.mahait.gov.in.service;

import java.util.List;

import com.mahait.gov.in.entity.CmnDistrictMst;

public interface MstDistrictService {
	
	public List<CmnDistrictMst> findAllDistrict();
	
	public CmnDistrictMst saveDistrict(CmnDistrictMst mstDistrictEntity);
	
	//added for state list
	
	//public List<MstStateModel> fetchStateByCountry(int countryId);

	public List<Long> validateDistrictname(String districtname);
	
}
