package com.mahait.gov.in.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.entity.ZpRltDdoMap;
import com.mahait.gov.in.model.NewRegDDOModel;
import com.mahait.gov.in.repository.ZpDDOOfficeRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ZpDDOOfficeServiceImpl implements ZpDDOOfficeService {
	
	
	@Autowired
	ZpDDOOfficeRepo zpDDOOfficeRepo;

	@Override
	public List<ZpRltDdoMap> getAllDDOOfficeDtlsDataByPostID(String username) {
		// TODO Auto-generated method stub
		return zpDDOOfficeRepo.getAllDDOOfficeDtlsDataByPostID(username);
	}

	@Override
	public NewRegDDOModel getDDOinfo(String zpDdoCode) {
		

		List<Object[]> lstprop = zpDDOOfficeRepo.getDDOinfo(zpDdoCode);
		NewRegDDOModel obj = new NewRegDDOModel();
		if (!lstprop.isEmpty()) {
			for (Object[] objLst : lstprop) {
				///zp.ZP_DDO_CODE,zp.REPT_DDO_CODE,zp.FINAL_DDO_CODE,zp.SPECIAL_DDO_CODE,org1.DDO_PERSONAL_NAME 
				obj.setLvl1ddoCode(StringHelperUtils.isNullString(objLst[0]));
				obj.setLvl2ddoCode(StringHelperUtils.isNullString(objLst[1]));
				obj.setLvl3ddoCode(StringHelperUtils.isNullString(objLst[2]));
				obj.setLvl1ddoName(StringHelperUtils.isNullString(objLst[4]));
				obj.setLvl2ddoName(StringHelperUtils.isNullString(objLst[5]));
			/*	obj.setDdoregid(StringHelperUtils.isNullInt(objLst[0]));
				obj.setOrginstname(StringHelperUtils.isNullString(objLst[1]));
				obj.setAddress(StringHelperUtils.isNullString(objLst[2]));
				obj.setDdoName(StringHelperUtils.isNullString(objLst[3]));*/
			}
		}
		return obj;
	
		// TODO Auto-generated method stub
		///return ddoInfoRepo.getLevel1DDOList(lStrDdoCode);
	}

	@Override
	public String getOfficeName(String zpDdoCode) {
		// TODO Auto-generated method stub
		return zpDDOOfficeRepo.getOfficeName(zpDdoCode);
	}

	@Override
	public OrgUserMst approveddoDtls(String zpDdoCode, int flag) {
		// TODO Auto-generated method stub`
	


		OrgUserMst objOrgUserMst = zpDDOOfficeRepo
				.findddo(zpDdoCode);
		
		ZpRltDdoMap zpRltDdoMap = zpDDOOfficeRepo
				.findddoinZPRlt(zpDdoCode);
		if (objOrgUserMst != null) {
				objOrgUserMst.setActivateFlag(1L); 
			zpDDOOfficeRepo.updateApproveStatus(objOrgUserMst);
		}if(zpRltDdoMap!=null) {
			if(flag==1) {
				zpRltDdoMap.setStatus(1L);
			}else{
				zpRltDdoMap.setStatus(-1L);
			}
			zpDDOOfficeRepo.updateApproveStatusinZpRlt(zpRltDdoMap);
		}
		return objOrgUserMst;
	
		
	/*	return zpDDOOfficeRepo.approveChangeStatement(zpDdoCode,flag);*/
	}

	@Override
	public List<ZpRltDdoMap> lstApprovedOffices(String ddoCode) {
		// TODO Auto-generated method stub
		return zpDDOOfficeRepo.lstApprovedOffices(ddoCode);
	}

	@Override
	public List<ZpRltDdoMap> lstRejectedOffices(String userName) {
		// TODO Auto-generated method stub
		return zpDDOOfficeRepo.lstRejectedOffices(userName);
	}

	
}
