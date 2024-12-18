package com.mahait.gov.in.service;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class NewRegDDOServiceImpl implements NewRegDDOService {/*
	
	@Autowired
	NewRegDDORepo newRegDDORepo;

	@OverrideS
	public List<EmpWiseCityClassModel> findAllEmployee(String userNamSe) {
		 List<Object[]> list = newRegDDORepo.findAllEmployee(userName);
			
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

	


	
	@Override
	public List<NewRegDDOModel> findLvl1DDOCode(String userName) {
		 List<Object[]> list = newRegDDORepo.findLvl1DDOCode(userName);
			
			List<NewRegDDOModel> listobj = new ArrayList<>();
			if(!list.isEmpty())
			{
				
				//b.ddo_code_level1,a.office_name ||'(' || b.ddo_code_level1 ||')'
				for(Object[] obj:list ) //for (Object[] objLst : lstprop) {
				{
					NewRegDDOModel obj1 = new NewRegDDOModel();
					obj1.setDdoCode(StringHelperUtils.isNullString(obj[0]));
					obj1.setOfficeName(StringHelperUtils.isNullString(obj[1]));
					
					
					listobj.add(obj1);
				}
			}
			return listobj;
	}

	@Override
	public List<NewRegDDOModel> findEmpLst(String userName) {
		 List<Object[]> list = newRegDDORepo.findEmpLst(userName);
			
			List<NewRegDDOModel> listobj = new ArrayList<>();
			if(!list.isEmpty())
			{
				
				//a.employee_id,a.employee_full_name_en,a.dob,a.gender,a.ddo_code
				for(Object[] obj:list ) //for (Object[] objLst : lstprop) {
				{
					NewRegDDOModel obj1 = new NewRegDDOModel();
					
					obj1.setEmployeeId(StringHelperUtils.isNullInt(obj[0]));
					obj1.setEmpName(StringHelperUtils.isNullString(obj[1]));
					obj1.setDob(StringHelperUtils.isNullDate(obj[2]));
					obj1.setGender(StringHelperUtils.isNullChar(obj[3]));
					obj1.setDdoCode(StringHelperUtils.isNullString(obj[4]));
					obj1.setDesgName(StringHelperUtils.isNullString(obj[5]));
					
					
					listobj.add(obj1);
				}
			}
			return listobj;
	}

*/}
