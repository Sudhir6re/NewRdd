package com.mahait.gov.in.service;

import java.io.Serializable;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.entity.MstEmployeeEntity;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.ChangeBasicDtlsModel;
import com.mahait.gov.in.repository.EmpChangeBasicDtlsRepo;

@Service
@Transactional
public class EmpChangeBasicdtlsServiceImpl  implements EmpChangeBasicdtlsService{
	
	@Autowired
	EmpChangeBasicDtlsRepo empChangeBasicDtlsRepo;
	
	
	public List<ChangeBasicDtlsModel> findEmpChangeBasicDtls(String ddo) {
		List<ChangeBasicDtlsModel> lstObj = new ArrayList<>();
		List<Object[]> lstGenerateBillDetails = empChangeBasicDtlsRepo.findEmpChangeBasicDtls(ddo);
		lstObj = new ArrayList<>();

		
		//employee_full_name_en,sevaarth_id,seven_pc_basic
		if (!lstGenerateBillDetails.isEmpty()) {
			for (Object[] objLst : lstGenerateBillDetails) {
				ChangeBasicDtlsModel obj = new ChangeBasicDtlsModel();
				 
				obj.setEmpName(StringHelperUtils.isNullString(objLst[0]));
				obj.setSevaarthId(StringHelperUtils.isNullString(objLst[1]));
				if(objLst[2]!=null)
				obj.setBasicSal(StringHelperUtils.isNullDouble(Double.parseDouble(objLst[2].toString())));
				obj.setEmpId(StringHelperUtils.isNullBigInteger(objLst[3]).longValue());
				obj.setPercentage(StringHelperUtils.isNullInt(objLst[4]));
				if(objLst[5]!=null)
				obj.setRevisedSal(StringHelperUtils.isNullDouble(Double.parseDouble(objLst[5].toString())));
				
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
				
				if(objLst[6] !=null)
				{
				String withdate  = dateFormat.format(objLst[6]);
				obj.setWithEffDate(StringHelperUtils.isNullString(withdate));
				}
				lstObj.add(obj);
			}
		}
		return lstObj;
	}
	
	
	@Override
	public int saveChangeBasicdtls(ChangeBasicDtlsModel changeBasicDtlsModel, OrgUserMst messages) {
		int id = 0;
			int i= 0;
			for(ChangeBasicDtlsModel model : changeBasicDtlsModel.getChangeBasicDtlsModelsList()) {
				if(model.isCheckboxid()==true) {
					
					//SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date withEffDate = null;
					MstEmployeeEntity mstEmployeeEntity = empChangeBasicDtlsRepo.findEmpData(model.getEmpId());
					try {
						if(model.getWithEffDate()!=null) {
							
							withEffDate = sdf.parse(model.getWithEffDate());
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}
					mstEmployeeEntity.setUpdatedBasicPercent(model.getPercentage().longValue());
					mstEmployeeEntity.setRevisedBasic(model.getRevisedSal());
					mstEmployeeEntity.setUpdatedBasicDate(new Date());
					mstEmployeeEntity.setUpdatedBasicwitheffDate(withEffDate);
					mstEmployeeEntity.setUpdatedBasicUserId(messages.getUserId());
					Serializable  saveChangeBasicdtls= empChangeBasicDtlsRepo.saveChangeBasicdtls(mstEmployeeEntity);
					id = (int) saveChangeBasicdtls;
					i++;
				}
			}
			return  id;
		}
}
