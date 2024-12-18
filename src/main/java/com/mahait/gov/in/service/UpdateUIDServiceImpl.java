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
import com.mahait.gov.in.model.UpdateUIDModel;
import com.mahait.gov.in.repository.UpdateUIDRepo;

import jakarta.validation.Valid;

@Service
public class UpdateUIDServiceImpl implements UpdateUIDService  {
	
	@Autowired
	UpdateUIDRepo updateUIDRepo;

	@Override
	public List<UpdateUIDModel> findAllEmployee(String userName) {
		// TODO Auto-generated method stub
		
		List<Object[]> list = updateUIDRepo.findAllEmployee(userName);
		
		List<UpdateUIDModel> listobj = new ArrayList<>();
		if(!list.isEmpty())
		{
			for(Object[] obj:list ) //for (Object[] objLst : lstprop) {
			{
				UpdateUIDModel obj1 = new UpdateUIDModel();
				obj1.setEmployeeId(StringHelperUtils.isNullBigInteger(obj[0]).longValue());
				obj1.setSevaarthId(StringHelperUtils.isNullString(obj[1]));
				obj1.setEmployeeFullNameEn(StringHelperUtils.isNullString(obj[2]));
//				BigInteger uid = StringHelperUtils.isNullBigInteger(obj[3]);
				obj1.setUidNo(StringHelperUtils.isNullString(obj[3]));
				
				listobj.add(obj1);
			}
			
			
			
		}
		return listobj;
	}


	@Override
	public int saveuidNo(@Valid UpdateUIDModel updateUIDModel, OrgUserMst messages) {
		int id = 0;
		int i= 0;
		for(UpdateUIDModel model : updateUIDModel.getEmplist()) {
			if(model.isCheckboxid()==true) {
				MstEmployeeEntity mstEmployeeEntity = updateUIDRepo.findEmpData(model.getEmployeeId());
				
				/*UpdateUIDHistryEntity updateUIDHistryEntity = new UpdateUIDHistryEntity();
				updateUIDHistryEntity.setUidNo(mstEmployeeEntity.getUidNo());
				updateUIDHistryEntity.setEmployeeId(model.getEmployeeId());
				updateUIDHistryEntity.setCreatedDate(new Date());
				Integer usid = StringHelperUtils.isNullInt(messages.getUser_id());
				updateUIDHistryEntity.setCreatedUserId(usid);
				updateUIDHistryEntity.setSevaarthId(mstEmployeeEntity.getSevaarthId());
				updateUIDHistryEntity.setEmployeeFullNameEn(mstEmployeeEntity.getEmployeeFullNameEn());
				Serializable  updateUIDhistory = updateUIDRepo.saveupdateUIDhistory(updateUIDHistryEntity);*/

				mstEmployeeEntity.setUidNo(model.getUidNo().toString());
				mstEmployeeEntity.setUpdatedDate(new Date());
				
				Serializable  saveChangeBasicdtls= updateUIDRepo.saveupdateMobNo(mstEmployeeEntity);
				id = (int) saveChangeBasicdtls;
				i++;
				
			}
		
		}
			return  id;
	}

}
