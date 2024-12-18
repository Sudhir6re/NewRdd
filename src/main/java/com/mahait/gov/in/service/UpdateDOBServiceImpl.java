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
import com.mahait.gov.in.model.UpdateDOBModel;
import com.mahait.gov.in.repository.UpdateDOBRepo;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
@Transactional
public class UpdateDOBServiceImpl implements UpdateDOBService {

	@Autowired
	UpdateDOBRepo updateDOBRepo;

	@Override
	public List<UpdateDOBModel> findAllEmployee(String userName) {
		List<Object[]> list = updateDOBRepo.findAllEmployee(userName);

		List<UpdateDOBModel> listobj = new ArrayList<>();
		if (!list.isEmpty()) {
			for (Object[] obj : list) // for (Object[] objLst : lstprop) {
			{
				UpdateDOBModel obj1 = new UpdateDOBModel();
				obj1.setEmployeeId(StringHelperUtils.isNullBigInteger(obj[0]).longValue());
				obj1.setSevaarthId(StringHelperUtils.isNullString(obj[1]));
				obj1.setEmployeeFullNameEn(StringHelperUtils.isNullString(obj[2]));
				obj1.setDob(StringHelperUtils.isNullDate(obj[3]));
				obj1.setDesignation(StringHelperUtils.isNullString(obj[4]));

				listobj.add(obj1);
			}
		}
		return listobj;
	}

	@Override
	public int saveupdatedob(@Valid UpdateDOBModel updateDOBModel, OrgUserMst messages) {
		int id = 0;
		MstEmployeeEntity mstEmployeeEntity = updateDOBRepo.findEmpData(updateDOBModel.getEmployeeId());
		mstEmployeeEntity.setDob(updateDOBModel.getDob());
		mstEmployeeEntity.setUpdatedDate(new Date());
		Serializable saveChangeBasicdtls = updateDOBRepo.saveupdatedob(mstEmployeeEntity);
		id = (int) saveChangeBasicdtls;
		return id;

	}

	@Override
	public List<UpdateDOBModel> getEmpDobBySevaarthId(String sevaarthId) {
		List<UpdateDOBModel> listEmpLoanModel = new ArrayList<>();
		List<Object[]> lst = updateDOBRepo.getEmpDobBySevaarthId(sevaarthId);
		for (Object[] objects : lst) {
			UpdateDOBModel updateDOBModel = new UpdateDOBModel();
			updateDOBModel.setSevaarthId(StringHelperUtils.isNullString(objects[0]));
			updateDOBModel.setDesignation(StringHelperUtils.isNullString(objects[1]));
			updateDOBModel.setEmployeeFullNameEn(StringHelperUtils.isNullString(objects[3]));

			updateDOBModel.setEmployeeId(StringHelperUtils.isNullBigInteger(objects[4]).longValue());
			updateDOBModel.setDob(StringHelperUtils.isNullDate(objects[2]));

			updateDOBModel.setDateString(objects[2].toString());

			listEmpLoanModel.add(updateDOBModel);
		}
		return listEmpLoanModel;
	}

}
