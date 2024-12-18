package com.mahait.gov.in.service;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.entity.MstEmployeeEntity;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.UpdateServiceEndDateModel;
import com.mahait.gov.in.repository.UpdateServiceEndDateRepo;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
@Transactional
public class UpdateServiceEndDateServiceImpl implements UpdateServiceEndDateService {
	
	@Autowired
	UpdateServiceEndDateRepo updateServiceEndDateRepo;

	@Override
	public List<UpdateServiceEndDateModel> findAllEmployee(String userName) {
		List<Object[]> list = updateServiceEndDateRepo.findAllEmployee(userName);
		
		List<UpdateServiceEndDateModel> listobj = new ArrayList<>();
		if(!list.isEmpty())
		{
			for(Object[] obj:list ) //for (Object[] objLst : lstprop) {
			{
				UpdateServiceEndDateModel obj1 = new UpdateServiceEndDateModel();
				obj1.setEmployeeId(StringHelperUtils.isNullBigInteger(obj[0]).longValue());
				obj1.setSevaarthId(StringHelperUtils.isNullString(obj[1]));
				obj1.setEmployeeFullNameEn(StringHelperUtils.isNullString(obj[2]));
                obj1.setSed(StringHelperUtils.isNullDate(obj[3]));
                obj1.setDesignation(StringHelperUtils.isNullString(obj[4]));
                
                if(obj[5]!=null)
                obj1.setDob(StringHelperUtils.isNullDate(obj[5]));
                
                if(obj[6]!=null)
                obj1.setCadreid(StringHelperUtils.isNullLong(new BigInteger(obj[6].toString()).longValue()));
				listobj.add(obj1);
			}
		}
		return listobj;
	}

	@Override
	public int saveSED(@Valid UpdateServiceEndDateModel updateServiceEndDateModel, OrgUserMst messages) {
		int id = 0;
		int i= 0;
			// TODO Auto-generated method stub
			for(UpdateServiceEndDateModel model : updateServiceEndDateModel.getEmplist()) {
				if(model.isCheckboxid()==true) {
					MstEmployeeEntity mstEmployeeEntity = updateServiceEndDateRepo.findEmpData(model.getEmployeeId());
					

					mstEmployeeEntity.setSuperAnnDate(model.getSed());
					//mstEmployeeEntity.setUpdatedDate(new Date());
					
					
					Serializable  saveChangeBasicdtls= updateServiceEndDateRepo.saveupdateSED(mstEmployeeEntity);	
					id = (int) saveChangeBasicdtls;
					i++;
					
				}
			
			}
				return  id;
	}

}
