package com.mahait.gov.in.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.entity.MstEmployeeEntity;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.UpdatePanNoModel;
import com.mahait.gov.in.repository.UpdatePanNoRepo;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
@Transactional
public class UpdatePanNoServiceImpl implements UpdatePanNoService{

	@Autowired
	UpdatePanNoRepo updatePanNoRepo;
	
	@Override
	public List<UpdatePanNoModel> findAllEmployee(String userName) {
		// TODO Auto-generated method stub
       List<Object[]> list = updatePanNoRepo.findAllEmployee(userName);
		
		List<UpdatePanNoModel> listobj = new ArrayList<>();
		if(!list.isEmpty())
		{
			for(Object[] obj:list ) //for (Object[] objLst : lstprop) {
			{
				UpdatePanNoModel obj1 = new UpdatePanNoModel();
				obj1.setEmployeeId(StringHelperUtils.isNullBigInteger(obj[0]).longValue());
				obj1.setSevaarthId(StringHelperUtils.isNullString(obj[1]));
				obj1.setEmployeeFullNameEn(StringHelperUtils.isNullString(obj[2]));
                obj1.setPanNo(StringHelperUtils.isNullString(obj[3]));
                obj1.setDesignation(StringHelperUtils.isNullString(obj[4]));
				
				listobj.add(obj1);
			}
		}
		return listobj;
		
	}

	@Override
	public int saveupdateMobNo(@Valid UpdatePanNoModel updatePanNoModel, OrgUserMst messages) {int id = 0;
	int i= 0;
		// TODO Auto-generated method stub
		for(UpdatePanNoModel model : updatePanNoModel.getEmplist()) {
			if(model.isCheckboxid()==true) {
				MstEmployeeEntity mstEmployeeEntity = updatePanNoRepo.findEmpData(model.getEmployeeId());
				
				/*UpdatePanNoHistryEntity updatePanNoHistryEntity = new UpdatePanNoHistryEntity();
				updatePanNoHistryEntity.setPanNo(mstEmployeeEntity.getPanNo());
				updatePanNoHistryEntity.setEmployeeId(model.getEmployeeId());
				updatePanNoHistryEntity.setCreatedDate(new Date());
				updatePanNoHistryEntity.setSevaarthId(mstEmployeeEntity.getSevaarthId());
				updatePanNoHistryEntity.setEmployeeFullNameEn(mstEmployeeEntity.getEmployeeFullNameEn());
				
				Integer usid = StringHelperUtils.isNullInt(messages.getUser_id());
				
				
				
				updatePanNoHistryEntity.setCreatedUserId(usid);
				Serializable  updatePANhistory = updatePanNoRepo.saveupdatePANhistory(updatePanNoHistryEntity);*/

				mstEmployeeEntity.setPanNo(model.getPanNo());
				mstEmployeeEntity.setUpdatedDate(new Date());
				
				
				Serializable  saveChangeBasicdtls= updatePanNoRepo.saveupdateMobNo(mstEmployeeEntity);	
				id = (int) saveChangeBasicdtls;
				i++;
				
			}
		
		}
			return  id;
			
	     }
	}

