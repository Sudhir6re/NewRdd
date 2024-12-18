package com.mahait.gov.in.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.entity.CmnTalukaMst;
import com.mahait.gov.in.entity.OrgPostMst;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.entity.RltDCPSDdoSchemeEntity;
import com.mahait.gov.in.model.EmpWiseCityClassModel;
import com.mahait.gov.in.model.NewRegDDOModel;
import com.mahait.gov.in.model.OrgDdoMst;
import com.mahait.gov.in.repository.DDOSchemeRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DDOSchemeServiceImpl implements DDOSchemeService {
	
	
	@Autowired
	DDOSchemeRepo ddoSchemeRepo;

	@Override
	public List<EmpWiseCityClassModel> findAllEmployee(String userName) {
		 List<Object[]> list = ddoSchemeRepo.findAllEmployee(userName);
			
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
	public String getDdoCodeForDDO(OrgPostMst createdByPost) {
		// TODO Auto-generated method stub
		return ddoSchemeRepo.getDdoCodeForDDO(createdByPost);
	}

	@Override
	public List<OrgDdoMst> getDDOCodeByLoggedInlocId(long l) {
		 List<Object[]> list = ddoSchemeRepo.getDDOCodeByLoggedInlocId(1);
			
			List<OrgDdoMst> listobj = new ArrayList<>();
			if(!list.isEmpty())
			{
				
				//employee_id,sevaarth_id,employee_full_name_en,district_code,taluka_code,city_class
				for(Object[] obj:list ) //for (Object[] objLst : lstprop) {
				{/*
					EmpWiseCityClassModel obj1 = new EmpWiseCityClassModel();
					obj1.setEmployeeId(StringHelperUtils.isNullInt(obj[0]));
					obj1.setSevaarthId(StringHelperUtils.isNullString(obj[1]));
					obj1.setEmpName(StringHelperUtils.isNullString(obj[2]));
					obj1.setDistrictId(StringHelperUtils.isNullString(obj[3]));
					obj1.setTalukaId(StringHelperUtils.isNullString(obj[4]));
					if(obj[5]!=null)
					obj1.setCityClass(StringHelperUtils.isNullString(obj[5].toString()));
					
					listobj.add(obj1);
				*/}
			}
			return listobj;
	}

	@Override
	public List<NewRegDDOModel> getAllSchemesForDDO(String lStrDDOCode) {
		

		 List<Object[]> list = ddoSchemeRepo.getAllSchemesForDDO(lStrDDOCode);
			
			List<NewRegDDOModel> listobj = new ArrayList<>();
			if(!list.isEmpty())
			{
				
				//select rlt.Scheme_Code,mst.scheme_Name,rlt.sub_scheme_code,rlt.Ddo_Code
				for(Object[] obj:list ) //for (Object[] objLst : lstprop) {
				{
					NewRegDDOModel obj1 = new NewRegDDOModel();
					obj1.setSchemeCode(StringHelperUtils.isNullString(obj[0]));
					obj1.setSchemeName(StringHelperUtils.isNullString(obj[1]));
					obj1.setSubSchemeCode(StringHelperUtils.isNullString(obj[2]));
					obj1.setDdoCode(StringHelperUtils.isNullString(obj[3]));
					listobj.add(obj1);
				}
			}
			return listobj;
	}

	@Override
	public String districtName(String ddoCode) {
		// TODO Auto-generated method stub
		return ddoSchemeRepo.districtName(ddoCode);
	}

	@Override
	public List<CmnTalukaMst> allTaluka(String districtID) {
		// TODO Auto-generated method stub
		return ddoSchemeRepo.allTaluka(districtID);
	}

	@Override
	public List getSubDDOs(Long locId, String talukaId, String ddoSelected) {
		// TODO Auto-generated method stub
		return ddoSchemeRepo.getSubDDOs(locId,talukaId,ddoSelected);
	}

	@Override
	public List getpostRole(OrgPostMst createdByPost) {
		// TODO Auto-generated method stub
		return ddoSchemeRepo.getpostRole(createdByPost);
	}

	@Override
	public List<Object[]> CheckSubSchemeExist(String schemeCode, String subschemeCode) {
		// TODO Auto-generated method stub
		return ddoSchemeRepo.CheckSubSchemeExist(schemeCode,subschemeCode);
	}

	@Override
	public Long addSchemesAndBillGroupsToDdo(NewRegDDOModel newRegDDOModel, OrgUserMst messages) {
		
		Long saveEntry = 0L;
		Long id = 0L;
			String locId=ddoSchemeRepo.getlocid(messages.getUserName());
		if(newRegDDOModel!=null) {
			RltDCPSDdoSchemeEntity rltDCPSDdoSchemeEntity= new RltDCPSDdoSchemeEntity();
			rltDCPSDdoSchemeEntity.setDdoCode(newRegDDOModel.getInstDdoCode());
			rltDCPSDdoSchemeEntity.setLocId(Long.valueOf(locId));
			rltDCPSDdoSchemeEntity.setSchemeCode(newRegDDOModel.getInstSchemeCode());
			rltDCPSDdoSchemeEntity.setUpdatedUserId(messages.getUserId());
			rltDCPSDdoSchemeEntity.setUpdatedPostId(messages.getUpdatedByPost().getPostId());
			rltDCPSDdoSchemeEntity.setCreatedPostId(messages.getUpdatedByPost().getPostId());
			rltDCPSDdoSchemeEntity.setCreatedUserId(messages.getUserId());
			rltDCPSDdoSchemeEntity.setUpdatedUserId(messages.getUserId());
			rltDCPSDdoSchemeEntity.setLangId(1L);
			rltDCPSDdoSchemeEntity.setDbId(99L);
			rltDCPSDdoSchemeEntity.setCreatedDate(new Date());
			rltDCPSDdoSchemeEntity.setUpdatedDate(new Date());
			
			saveEntry = ddoSchemeRepo.saveScheme(rltDCPSDdoSchemeEntity);
		}
		
		id = (Long) saveEntry;
		return id;
	
		
		
		
		
	}

	@Override
	public List<Object[]> displaySchemeNameForCode(String schemeCode) {
		// TODO Auto-generated method stub
		return ddoSchemeRepo.displaySchemeNameForCode(schemeCode);
	}

}
