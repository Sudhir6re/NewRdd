package com.mahait.gov.in.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.model.EmpWiseCityClassModel;
import com.mahait.gov.in.repository.OfflineContriRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OfflineContriServiceImpl implements OfflineContriService {
	
	
	@Autowired
	OfflineContriRepo offlineContriRepo;

	@Override
	public List<EmpWiseCityClassModel> findAllEmployee(String userName) {
		 List<Object[]> list = offlineContriRepo.findAllEmployee(userName);
			
			List<EmpWiseCityClassModel> listobj = new ArrayList<>();
			if(!list.isEmpty())
			{
				
				//employee_id,sevaarth_id,employee_full_name_en,district_code,taluka_code,city_class
				for(Object[] obj:list ) //for (Object[] objLst : lstprop) {
				{
					EmpWiseCityClassModel obj1 = new EmpWiseCityClassModel();
					obj1.setEmployeeId(StringHelperUtils.isNullInt(obj[0]));
					obj1.setSevaarthId(StringHelperUtils.isNullString(obj[1]));
					obj1.setEmpName(StringHelperUtils.isNullString(obj[2]));
					obj1.setDistrictId(StringHelperUtils.isNullString(obj[3]));
					obj1.setTalukaId(StringHelperUtils.isNullString(obj[4]));
					if(obj[5]!=null)
					obj1.setCityClass(StringHelperUtils.isNullString(obj[5].toString()));
					
					listobj.add(obj1);
				}
			}
			return listobj;
	}

	

}
