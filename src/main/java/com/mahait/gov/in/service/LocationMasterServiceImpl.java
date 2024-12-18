package com.mahait.gov.in.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mahait.gov.in.repository.LocationMasterRepo;


@Service
@Transactional
public class LocationMasterServiceImpl implements LocationMasterService{
	@Autowired
	LocationMasterRepo locationMasterRepo;
	@Override
	public List<Object[]> findAllStates(int countryId) {
		// TODO Auto-generated method stub
		List<Object[]> findAllStatesList = locationMasterRepo.findAllStates(countryId);
		return findAllStatesList;
	}/*
	
	@Autowired
	LocationMasterRepo locationMasterRepo;
	
	@Override
	public List<CountryMasterEntity> findAllCountry() {
		return locationMasterRepo.findAllCountry();
	}
	
	@Override
	public List<Object[]> findAllStates(int countryId)
	{
		List<Object[]> findAllStatesList = locationMasterRepo.findAllStates(countryId);
		return findAllStatesList;
	}
	
	@Override
	public List<Object[]> findAllDistricts(int stateId)
	{
		List<Object[]> findAllDistrictList = locationMasterRepo.findAllDistricts(stateId);
		return findAllDistrictList;
	}
	
	@Override
	public List<Object[]> findAllTaluka(int districtId,int stateId)
	{
		List<Object[]> findAllTalukaList = locationMasterRepo.findAllTaluka(districtId,stateId);
		return findAllTalukaList;
	}
	
	@Override
	public int saveMstLocationData(MstLocationModel mstLocationModel) {
		
		MstLocationEntity mstLocationEntity = new MstLocationEntity();
		mstLocationEntity.setAddress(mstLocationModel.getAddress());
		mstLocationEntity.setCountryCode(mstLocationModel.getCountryCode());
		mstLocationEntity.setStateCode(mstLocationModel.getStateCode());
		mstLocationEntity.setDistrictCode(mstLocationModel.getDistrictCode());
		mstLocationEntity.setPincode(mstLocationModel.getPincode());
		mstLocationEntity.setVillageName(mstLocationModel.getVillageName());
		mstLocationEntity.setTalukaCode(mstLocationModel.getTalukaCode());
		mstLocationEntity.setLocationCode(mstLocationModel.getLocationCode());
		mstLocationEntity.setLocationNameEn(mstLocationModel.getLocationNameEn());
		mstLocationEntity.setIsActive('1');
		mstLocationEntity.setCreatedDate(new Date());
		mstLocationEntity.setCreatedUserId(1);
		
		int saveId = locationMasterRepo.saveMstLocationData(mstLocationEntity);
		return saveId;
		
	}

	@Override
	public List<Object[]> findAllSubCorporation(int parentFieldDepartmentId) {
		List<Object[]> findAllSubCorporation = locationMasterRepo.findAllSubCorporation(parentFieldDepartmentId);
		return findAllSubCorporation;
	}
	
*/
	@Override
	public List<Object[]> findAllDistricts(long stateId) {
		// TODO Auto-generated method stub
		List<Object[]> findAllDistrictList = locationMasterRepo.findAllDistricts(stateId);
		return findAllDistrictList;
	}
	}
