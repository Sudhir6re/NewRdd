package com.mahait.gov.in.repository;

import java.util.List;

import com.mahait.gov.in.entity.CmnDistrictMst;
import com.mahait.gov.in.model.MstStateModel;

public interface MstDistrictRepo {
	
	public List<CmnDistrictMst> findAllDistrict();
	
	//public List<Object[]> findAllTaluka();
	
	public List<MstStateModel> fetchStateByCountry(int countryId);

	public List<Long> validateDistrictName(String districtname);

}
