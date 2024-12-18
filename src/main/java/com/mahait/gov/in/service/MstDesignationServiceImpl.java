package com.mahait.gov.in.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.entity.MstDesignationEntity;
import com.mahait.gov.in.entity.MstPayCommissionEntity;
import com.mahait.gov.in.model.MstCadreModel;
import com.mahait.gov.in.model.MstDesignationModel;
import com.mahait.gov.in.repository.MstDesignationRepo;
@Service
@Transactional
public class MstDesignationServiceImpl implements MstDesignationService{
	

	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@Autowired
	private MstDesignationRepo  mstDesignationRepo;

	
	@Override
	public List<MstPayCommissionEntity> findAllPayCommission() {
		// TODO Auto-generated method stub

		return mstDesignationRepo.findAllPayCommission();

	}

	@Override
	public List<MstDesignationModel> getDesignationMstData(String locale) {
		List<Object[]> lstprop = mstDesignationRepo.getDesignationMstData();
		List<MstDesignationModel> lstObj = new ArrayList<>();
        if (!lstprop.isEmpty()) {
            for (Object[] objLst : lstprop) {
            	MstDesignationModel obj = new MstDesignationModel();
                obj.setDesignationId(StringHelperUtils.isNullBigInteger(objLst[0]).longValue());
//                if(locale.equals("en")) {
//                	 obj.setDescFldDept(StringHelperUtils.isNullString(objLst[1]));
//                } else {
//                	 obj.setDescFldDept(StringHelperUtils.isNullString(objLst[2]));
//                }
                obj.setDesignationCode(StringHelperUtils.isNullBigInteger(objLst[1]));
                obj.setDesignation(StringHelperUtils.isNullString(objLst[2]));
                obj.setDesignationShortName(StringHelperUtils.isNullString(objLst[3]));
//                obj.setDescCadre(StringHelperUtils.isNullString(objLst[6]));
//                if(locale.equals("en")) {
//               	 obj.setDescPayCommission(StringHelperUtils.isNullString(objLst[7]));
//               } else {
//               	 obj.setDescPayCommission(StringHelperUtils.isNullString(objLst[8]));
//               }
                obj.setIsActive(StringHelperUtils.isNullInt(Integer.parseInt(String.valueOf(objLst[4]))));
                if(objLst[5]!=null)
                {
                	
                obj.setCadreName(StringHelperUtils.isNullString(objLst[5]));
                }
                lstObj.add(obj);
            }
            
        }
        return lstObj;
	}
	
	@Override
	public List<MstCadreModel> getCadre() {
		List<Object[]> lstprop = mstDesignationRepo.getCadre();
		List<MstCadreModel> lstObj = new ArrayList<>();
		if (!lstprop.isEmpty()) {
			for (Object[] objLst : lstprop) {
				MstCadreModel obj = new MstCadreModel();
				obj.setCadreCode(StringHelperUtils.isNullBigInteger(objLst[0]).longValue());
				obj.setCadreDescription(StringHelperUtils.isNullString(objLst[3]));
				lstObj.add(obj);
			}
		}
		return lstObj;
	}
	
	public Long saveDesignationMst(MstDesignationModel mstDesignationModel,long userId) {
		MstDesignationEntity mstDesignationEntity = new MstDesignationEntity();

		//mstDesignationEntity.setField_department(Integer.valueOf(mstDesignationModel.getFieldDepartment()));
		//mstDesignationEntity.setCadre(1);;
		//mstDesignationEntity.setPayCommission(mstDesignationModel.getPayCommission());
		mstDesignationEntity.setDesgination(mstDesignationModel.getDesignation().toUpperCase());
		mstDesignationEntity.setDesignationShortName(mstDesignationModel.getDesignationShortName().toUpperCase());
		mstDesignationEntity.setCadreCode(mstDesignationModel.getCadreCode());
		mstDesignationEntity.setIsActive('1');
		mstDesignationEntity.setCreatedDate(new Date());
		mstDesignationEntity.setCadreGroup(mstDesignationModel.getCadreGroup());
		
		Long saveId = mstDesignationRepo.saveDesignationMst(mstDesignationEntity);
		return saveId;
	}
	
	@Override
	public MstDesignationEntity findMstDesgByDesgId(Long designationId) {
		return mstDesignationRepo.findMstDesgByDesgId(designationId);
	}
	
	
	@Override
	public String editDesgSave(MstDesignationEntity mstDesgEntity,long userId) {
		MstDesignationEntity objDesg = mstDesignationRepo.findMstDesgByDesgId(mstDesgEntity.getDesginationId());
		if(objDesg != null) {
			objDesg.setDesginationCode(mstDesgEntity.getDesginationCode());
			objDesg.setDesgination(mstDesgEntity.getDesgination().toUpperCase());
			objDesg.setDesignationShortName(mstDesgEntity.getDesignationShortName().toUpperCase());
			objDesg.setCadreCode(mstDesgEntity.getCadreCode());
			objDesg.setIsActive(mstDesgEntity.getIsActive());	// UPDATED
			objDesg.setUpdatedDate(new Date());
			objDesg.setUpdatedUserId(userId);
			objDesg.setCadreGroup(mstDesgEntity.getCadreGroup());
			mstDesignationRepo.updateDesginationStatus(objDesg);
		}
		return "UPDATED";
	}

	@Override
	public MstDesignationEntity findMstDesgByIdForReject(long designationId,long userId) {
		MstDesignationEntity objDes = mstDesignationRepo.findMstDesgByDesgId(designationId);
		if(objDes != null) {
			objDes.setIsActive('0');	// REJECTED
			objDes.setUpdatedDate(new Date());
			objDes.setUpdatedUserId(userId);
			mstDesignationRepo.updateDesginationStatus(objDes);
		}
		return objDes;
	}

	@Override
	public List<Long> validateDesignationName(String desgname) {
		return mstDesignationRepo.validateDesignationName(desgname);
	}


	}
	

/*	@Override
>>>>>>> d62234043a6ef75399080141dbb407484af22d68
	public String getCadreDesc(Integer fieldDepartmrntID, String language) {
		CommonHelper helper = new CommonHelper();
		List<Object[] > lstprop = mstDesignationRepo.getCadreDesc(fieldDepartmrntID);
		Gson gson = new Gson();
		String str = gson.toJson(helper.getCadreDescDataHelper(lstprop,language));
		return JsonResponseHelper.getJSONResponseString(str);
	}

*/
	/*@Override
>>>>>>> Stashed changes
	public List<MstPayCommissionEntity> findAllPayCommission() {
		return mstDesignationRepo.findAllPayCommission();
	}*/
	

	/*@Override
	public List<MstDesignationModel> getDesignationMstData(String locale) {

		List<Object[]> lstprop = mstDesignationRepo.getDesignationMstData();
		List<MstDesignationModel> lstObj = new ArrayList<>();
        if (!lstprop.isEmpty()) {
            for (Object[] objLst : lstprop) {
            	MstDesignationModel obj = new MstDesignationModel();
                obj.setDesignationId(StringHelperUtils.isNullBigInteger(objLst[0]));
//                if(locale.equals("en")) {
//                	 obj.setDescFldDept(StringHelperUtils.isNullString(objLst[1]));
//                } else {
//                	 obj.setDescFldDept(StringHelperUtils.isNullString(objLst[2]));
//                }
                obj.setDesignationCode(StringHelperUtils.isNullBigInteger(objLst[1]));
                obj.setDesignation(StringHelperUtils.isNullString(objLst[2]));
                obj.setDesignationShortName(StringHelperUtils.isNullString(objLst[3]));
//                obj.setDescCadre(StringHelperUtils.isNullString(objLst[6]));
//                if(locale.equals("en")) {
//               	 obj.setDescPayCommission(StringHelperUtils.isNullString(objLst[7]));
//               } else {
//               	 obj.setDescPayCommission(StringHelperUtils.isNullString(objLst[8]));
//               }
                obj.setIsActive(StringHelperUtils.isNullInt(Integer.parseInt(String.valueOf(objLst[4]))));
                if(objLst[5]!=null)
                {
                	
                obj.setCadreName(StringHelperUtils.isNullString(objLst[5]));
                }
                lstObj.add(obj);
            }
        }
        return lstObj;
	}*/
	/*
	

	@Override
	public List<MstCadreEntity> findCadreDescByFldDeptId(Integer fldDeptId) {
		return mstDesignationRepo.findCadreDescByFldDeptId(fldDeptId); 
	}

	

	@Override
	public List<MstCadreEntity> getCadre() {
		return mstDesignationRepo.getCadre();
<<<<<<< HEAD
	}
<<<<<<< Updated upstream
<<<<<<< HEAD
	}*/

	
