package com.mahait.gov.in.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.entity.CmnDistrictMst;
import com.mahait.gov.in.entity.CmnLookupMst;
import com.mahait.gov.in.entity.CmnStateMst;
import com.mahait.gov.in.entity.CmnTalukaMst;
import com.mahait.gov.in.entity.DdoOffice;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.ApproveDDOHstModel;
import com.mahait.gov.in.model.NewRegDDOModel;
import com.mahait.gov.in.repository.DDOInfoRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DDOInfoServiceImpl implements DDOInfoService{

	
	@Autowired
	DDOInfoRepo ddoInfoRepo;

	@Override
	public String getDdoCodeForDDO(Long postId) {
		// TODO Auto-generated method stub
		return ddoInfoRepo.getDdoCodeForDDO(postId);
	}

	@Override
	public  List <ApproveDDOHstModel> getLevel1DDOList(String lStrDdoCode) {
		

		List<Object[]> lstprop = ddoInfoRepo.getLevel1DDOList(lStrDdoCode);
		List<ApproveDDOHstModel> lstObj = new ArrayList<>();
		if (!lstprop.isEmpty()) {
			for (Object[] objLst : lstprop) {
				ApproveDDOHstModel obj = new ApproveDDOHstModel();
			/*	obj.setDdoregid(StringHelperUtils.isNullInt(objLst[0]));
				obj.setOrginstname(StringHelperUtils.isNullString(objLst[1]));
				obj.setAddress(StringHelperUtils.isNullString(objLst[2]));
				obj.setDdoName(StringHelperUtils.isNullString(objLst[3]));*/

				lstObj.add(obj);
			}
		}
		return lstObj;
	
		// TODO Auto-generated method stub
		///return ddoInfoRepo.getLevel1DDOList(lStrDdoCode);
	}

	@Override
	public  List <Object[]> getDDoHistoryDetailsForApprove(String ddo) {
		// TODO Auto-generated method stub
		return ddoInfoRepo.getDDoHistoryDetailsForApprove(ddo);
	}
	
	@Override
	public List<CmnStateMst> getStateLst(long countryId) {
		// TODO Auto-generated method stub
		return ddoInfoRepo.getStateLst(countryId);
	}
	@Override
	public List<CmnDistrictMst> getDistrictlst(long stateId){
		return ddoInfoRepo.getDistrictlst(stateId);
	}

	@Override
	public List<DdoOffice> getAllOffices(String ddoCode) {
		// TODO Auto-generated method stub
		return ddoInfoRepo.getAllOffices(ddoCode);
	}

	@Override
	public String getDistrictId(String ddoCode) {
		// TODO Auto-generated method stub
		return ddoInfoRepo.getDistrictId(ddoCode);
	}

	@Override
	public List<CmnTalukaMst> getTalukalst() {
		// TODO Auto-generated method stub
		return ddoInfoRepo.getTalukalst();
	}

	@Override
	public DdoOffice getDdoOfficeDtls(Long dcpsDdoOfficeMstId) {
		// TODO Auto-generated method stub
		return ddoInfoRepo.getDdoOfficeDtls(dcpsDdoOfficeMstId);
	}

	@Override
	public int SaveApproveDdoOffice(NewRegDDOModel newRegDDOModel, OrgUserMst messages) {
		

		int id = 0;
		DdoOffice ddoOffice = ddoInfoRepo.getDdoOfficeDtls(newRegDDOModel.getDcpsDdoOfficeMstId());
		if(ddoOffice!=null) {
			ddoOffice.setStatusFlag(1L);
		}
		Serializable approveDDO = ddoInfoRepo.SaveApproveDdoOffice(ddoOffice);
		
		id = (int) approveDDO;
		return id;
	
		
		
		
		
	}

	@Override
	public List<CmnLookupMst> findDDOOffClass(Long lookupId) {
		// TODO Auto-generated method stub
		return ddoInfoRepo.findDDOOffClass(lookupId);
	}

	@Override
	public List<NewRegDDOModel> getDDOOffForApproval(String ddoCode) {
		

		List<Object[]> lstprop = ddoInfoRepo.getDDOOffForApproval(ddoCode);
		List<NewRegDDOModel> lstObj = new ArrayList<>();
		if (!lstprop.isEmpty()) {
			for (Object[] objLst : lstprop) {
				NewRegDDOModel obj = new NewRegDDOModel();
				//DCPS_DDO_OFFICE_MST_ID,OFF_NAME,ADDRESS1,ADDRESS2,STATUS_FLAG,ddo_code
				
				obj.setOfficeName(StringHelperUtils.isNullString(objLst[1]));
				String address=objLst[2] + " " + objLst[3];
				obj.setAddress(StringHelperUtils.isNullString(objLst[2]));
				obj.setDdoCode(StringHelperUtils.isNullString(objLst[5]));
				if(objLst[4].equals('0')) {
					obj.setStatus("Pending");
				}

				lstObj.add(obj);
			}
		}
		return lstObj;
	
		// TODO Auto-generated method stub
		///return ddoInfoRepo.getLevel1DDOList(lStrDdoCode);
	}

	@Override
	public List<Object[]> getAlreadySavedDataforDDO(String ddoCode) {
		// TODO Auto-generated method stub
		return ddoInfoRepo.getAlreadySavedDataforDDO(ddoCode);
	}

	@Override
	public DdoOffice updateApproveRejectStatus(String ddoCode, int flag, String cityClass) {
		// TODO Auto-generated method stub
	


		DdoOffice ddoOffice = ddoInfoRepo.findDdoData(ddoCode);
		if (ddoOffice != null) {
			
			if(flag==1) {
				ddoOffice.setStatusFlag(1L); 
				ddoOffice.setDcpsDdoOfficeCityClass(cityClass); 
			}else {
				ddoOffice.setStatusFlag(0L); 	
			}

			ddoInfoRepo.updateApproveRejectStatus(ddoOffice);
			
			
			OrgUserMst orgUserMst=ddoInfoRepo.findOrgUserMstByDdoCode(ddoCode);
			orgUserMst.setActivateFlag(1l);
			ddoInfoRepo.update(orgUserMst);
			
			
			
		}
		return ddoOffice;

}

	@Override
	public List<NewRegDDOModel> getLstTown() {
		

		List<Object[]> lstprop = ddoInfoRepo.getLstTown();
		List<NewRegDDOModel> lstObj = new ArrayList<>();
		if (!lstprop.isEmpty()) {
			for (Object[] objLst : lstprop) {
				NewRegDDOModel obj = new NewRegDDOModel();

				obj.setCityId(StringHelperUtils.isNullBigInteger(objLst[0]));
				obj.setCityName(StringHelperUtils.isNullString(objLst[1]));

				lstObj.add(obj);
			}
		}
		return lstObj;
	
		// TODO Auto-generated method stub
		///return ddoInfoRepo.getLevel1DDOList(lStrDdoCode);
	}

	@Override
	public Long validateAccNo(String accNo, OrgUserMst messages) {
		// TODO Auto-generated method stub
		return ddoInfoRepo.validateAccNo( accNo,  messages);
	}

	@Override
	public Long validateTelephone(String telPhone, OrgUserMst messages) {
		// TODO Auto-generated method stub
		return ddoInfoRepo.validateTelephone(telPhone,messages);
	}

	@Override
	public Long validateEmailAdd(String email, OrgUserMst messages) {
		// TODO Auto-generated method stub
		return ddoInfoRepo.validateEmailAdd(email,messages);
	}
}