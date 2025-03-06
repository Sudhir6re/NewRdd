package com.mahait.gov.in.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.entity.MstDcpsBillGroup;
import com.mahait.gov.in.entity.MstEmployeeEntity;
import com.mahait.gov.in.entity.MstScheme;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.entity.RltBillgroupClassgroup;
import com.mahait.gov.in.model.BillgroupMaintainanceModel;
import com.mahait.gov.in.model.MpgSchemeBillGroupModel;
import com.mahait.gov.in.model.MstEmployeeModel;
import com.mahait.gov.in.model.Rltdcpsbillgroupclassmodel;
import com.mahait.gov.in.repository.DdoBillGroupRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DdoBillGroupServiceImpl implements DdoBillGroupService{
	@Autowired
	private DdoBillGroupRepo ddoBillGroupRepo;
	


@Override
public List<MstDcpsBillGroup> lstBillName(String username) {
	// TODO Auto-generated method stub
	return ddoBillGroupRepo.lstBillName(username);
}

@Override
public long saveBillGroupMaintainance(BillgroupMaintainanceModel billgroupMaintainanceModel, OrgUserMst messages,long locId) {

	
	//Boolean lBlGroupExistOrNotForBG = false;
	long savedId =0l;
	
	MstDcpsBillGroup mstBillGroupEntity = ddoBillGroupRepo.findDcpsBillGroupById(billgroupMaintainanceModel.getBillGroupId()==null?0:billgroupMaintainanceModel.getBillGroupId());
	
		if(mstBillGroupEntity==null)
		{
			mstBillGroupEntity=new MstDcpsBillGroup();
		}
			
		        mstBillGroupEntity.setDcpsDdoSchemeCode(billgroupMaintainanceModel.getSchemeCode());
				mstBillGroupEntity.setDescription(billgroupMaintainanceModel.getDescription());
				mstBillGroupEntity.setDcpsDdoBillTypeOfPost(billgroupMaintainanceModel.getTypeOfPost());
				mstBillGroupEntity.setDcpsDdoSchemeCode(billgroupMaintainanceModel.getDcpsDdoSchemeCode());
				mstBillGroupEntity.setDcpsDdoBillSchemeName(billgroupMaintainanceModel.getSchemeName());
				mstBillGroupEntity.setDcpsDdoSubSchemeCode(billgroupMaintainanceModel.getDcpsDdoSubSchemeCode());
				mstBillGroupEntity.setDcpsDdoBillTypeOfPost(billgroupMaintainanceModel.getDcpsDdoBillTypeOfPost());
				mstBillGroupEntity.setDcpsDdoCode(messages.getDdoCode());
				mstBillGroupEntity.setLocId(locId);
				mstBillGroupEntity.setLangId(1l);
				mstBillGroupEntity.setDbId(99l);
				mstBillGroupEntity.setCreatedDate(new Date());
				mstBillGroupEntity.setPostId(messages.getCreatedByPost().getPostId());
				mstBillGroupEntity.setUserId(messages.getUserId());
				mstBillGroupEntity.setUpdatedPostId(messages.getCreatedByPost().getPostId());
				mstBillGroupEntity.setUpdatedUserId(messages.getUserId());
				mstBillGroupEntity.setUpdatedDate(new Date());
		
				

				savedId	= ddoBillGroupRepo.saveBillGroupMaintainance(mstBillGroupEntity);
	
				if(billgroupMaintainanceModel.getBillGroupId()!=null)
				{
				int billcount = ddoBillGroupRepo.checkGroupExistsOrNotForBG(billgroupMaintainanceModel.getBillGroupId());

				if (billcount > 0) {
					ddoBillGroupRepo.deleteClassGroupsForGivenBGId(billgroupMaintainanceModel.getBillGroupId());
				}

				}
				List<Rltdcpsbillgroupclassmodel> lstBillgroupMaintainanceModel=billgroupMaintainanceModel.getBillgroupclass();
				
		
				for (Rltdcpsbillgroupclassmodel billgroupclass : lstBillgroupMaintainanceModel) {
					RltBillgroupClassgroup rltBillgroupClassgroup = new RltBillgroupClassgroup();
					if(billgroupclass.getBillgroupclass() !=null)
					{
					rltBillgroupClassgroup.setDcpsClassGroup(billgroupclass.getBillgroupclass()); 
					rltBillgroupClassgroup.setDcpsBillGroupId(mstBillGroupEntity.getDcpsDdoBillGroupId());
					rltBillgroupClassgroup.setLangId(1l);
					rltBillgroupClassgroup.setDbId(99l);
					rltBillgroupClassgroup.setLocId(locId);
					rltBillgroupClassgroup.setUserId(messages.getUserId());
					rltBillgroupClassgroup.setCreatedDate(new Date());
					rltBillgroupClassgroup.setPostId(messages.getCreatedByPost().getPostId());
					rltBillgroupClassgroup.setUserId(messages.getUserId());
					rltBillgroupClassgroup.setUpdatedPostId(messages.getCreatedByPost().getPostId());
					rltBillgroupClassgroup.setUpdatedUserId(messages.getUserId());
					rltBillgroupClassgroup.setUpdatedDate(new Date());
					
					savedId	= ddoBillGroupRepo.saveDcpsBillGroupMpg(rltBillgroupClassgroup);
					}
				}
			
			
		
				return  savedId;

}

@Override
public List<MstScheme> getSchemeCodeAgainstName(String schemeId) {
	// TODO Auto-generated method stub
	return ddoBillGroupRepo.getSchemeCodeAgainstName(schemeId);
}


@Override
public List<MstEmployeeModel> findAllEmployeesByDDOName(String ddoCode) {
	List<Object[]> lstprop = ddoBillGroupRepo.findAllEmployeesByDDOName(ddoCode);
	List<MstEmployeeModel> lstObj = new ArrayList<>();
	if (!lstprop.isEmpty()) {
		for (Object[] objLst : lstprop) {
			MstEmployeeModel obj = new MstEmployeeModel();
			obj.setSevaarthId(StringHelperUtils.isNullString(objLst[0]));
			obj.setEmployeeFullName(StringHelperUtils.isNullString(objLst[1]));
			obj.setDesignationName(StringHelperUtils.isNullString(objLst[2]));
			obj.setDepartmentNameEn(StringHelperUtils.isNullString(objLst[3]));
			
			/*BigInteger b = (BigInteger) objLst[4];
			obj.setEmployeeId(b.longValue());

			BigInteger paycomm=(BigInteger) objLst[5];
			Long lngPaycomm=paycomm.longValue();
			obj.setPayCommissionCode(lngPaycomm);

			
<<<<<<< HEAD
			BigInteger payComm = (BigInteger) objLst[5];
			Long lngpaycomm=payComm.longValue();
			obj.setPayCommissionCode(lngpaycomm);
=======
			int payComm = (int) objLst[5];
			Long lngpaycomm=(long) payComm;
			obj.setPayCommissionCode(lngpaycomm);*/
			obj.setEmployeeId(StringHelperUtils.isNullBigInteger(objLst[4]).longValue());

			obj.setPayCommissionCode(StringHelperUtils.isNullBigInteger(objLst[5]).longValue());
			obj.setPayCommissionName(StringHelperUtils.isNullString(objLst[6]));
			obj.setEmpServiceEndDate(StringHelperUtils.isNullDate(objLst[8]));
			obj.setBillDesc(StringHelperUtils.isNullString(objLst[9]));
			if (objLst[7] != null && !objLst[7].equals("")) {
				if (objLst[7].equals("Y")) {
					obj.setDcpsgpfflag("DCPS");
				} else {
					obj.setDcpsgpfflag("GPF");
				}
			}
			lstObj.add(obj);
		}
	}
	return lstObj;
}

@Override
public List<MstEmployeeEntity> firstgetfindAllEmployeeByddoCode(String ddoCode) {
	return ddoBillGroupRepo.firstgetfindAllEmployeeByddoCode(ddoCode);
}

@Override
public List<Object[]> findAttachedEmployee(String ddocode, String scmebillgroupid) {
	return ddoBillGroupRepo.findAttachedEmployee(ddocode, scmebillgroupid);
}


@Override
public List<Object[]> findDettachEmployee(String ddocode, String scmebillgroupid) {
	return ddoBillGroupRepo.findDettachEmployee(ddocode, scmebillgroupid);
}

//@Override
//public List<Object[]> getBillDtlsForAlreadySaved(String billGrpId) {
//	List<Object[]> lst = ddoBillGroupRepo.getBillDtlsForAlreadySaved(billGrpId);
//	return lst;
//}

@Override
public Map<String, Object> getBillDtlsForAlreadySaved(String billGrpId) {

	List lst = ddoBillGroupRepo.getBillDtlsForAlreadySaved(billGrpId);
	List groupdetils = ddoBillGroupRepo.getBillgroupDtlsForAlreadySaved(billGrpId);
	
	Long count=ddoBillGroupRepo.billGroupMappedWithPost(billGrpId);
	if(lst.size()>0 && lst!=null) {
		Map<String, Object> response = new HashMap<>();
		
		response.put("billdetails", lst);
		response.put("grpdtls", groupdetils);
		response.put("billGrpMapped", count);
		return response;
	}else {
		Map<String, Object> response = new HashMap<>();
		response.put("billGrpMapped", 0);
		return response;
	}

}

@Override
public String saveAttachDettachEmployee(MpgSchemeBillGroupModel mpgSchemeBillGroupModel) {
	String result = "N";

	// public String saveAttachDettachEmployeeBillGroup(String sevaarthId,int empid,
	// int billGroupId,String status)
	String lStrDcpsEmpIdstoBeDetached = mpgSchemeBillGroupModel.getDcpsEmpIdstoBeDetached();// StringUtility.getParameter("dcpsEmpIdstoBeDetached",
																							// request);
	String[] lStrArrDcpsEmpIdstoBeDetached = lStrDcpsEmpIdstoBeDetached.split("~");
	Long[] lLongArrDcpsEmpIdstoBeDetached = new Long[lStrArrDcpsEmpIdstoBeDetached.length];
	for (Integer lInt = 0; lInt < lStrArrDcpsEmpIdstoBeDetached.length; lInt++) {
		if (lStrArrDcpsEmpIdstoBeDetached[lInt] != "" && !lStrArrDcpsEmpIdstoBeDetached[lInt].equals("")) {
			lLongArrDcpsEmpIdstoBeDetached[lInt] = Long.valueOf(lStrArrDcpsEmpIdstoBeDetached[lInt]);
			result = ddoBillGroupRepo.saveAttachDettachEmployeeBillGroup(mpgSchemeBillGroupModel.getSevaarthId(),
					lLongArrDcpsEmpIdstoBeDetached[lInt], mpgSchemeBillGroupModel.getSchemebillGroupId(),
					"Detach");// updateBillNoInPayroll(lLongArrDcpsEmpIdstoBeDetached[lInt], null, "Detach");
		}
	}

	String lStrDcpsEmpIdstoBeAttached = mpgSchemeBillGroupModel.getDcpsEmpIdstoBeAttached(); // StringUtility.getParameter("dcpsEmpIdstoBeAttached",
																								// request);
	String[] lStrArrDcpsEmpIdstoBeAttached = lStrDcpsEmpIdstoBeAttached.split("~");
	Long[] lLongArrDcpsEmpIdstoBeAttached = new Long[lStrArrDcpsEmpIdstoBeAttached.length];
	for (Integer lInt = 0; lInt < lStrArrDcpsEmpIdstoBeAttached.length; lInt++) {
		if (lStrArrDcpsEmpIdstoBeAttached[lInt] != "" && !lStrArrDcpsEmpIdstoBeAttached[lInt].equals("")) {
			lLongArrDcpsEmpIdstoBeAttached[lInt] = Long.valueOf(lStrArrDcpsEmpIdstoBeAttached[lInt]);
			// lObjDdoBillGroupDAO.updateBillNoInPayroll(lLongArrDcpsEmpIdstoBeAttached[lInt],
			// lLongbillGroupId, "Attach");
			result = ddoBillGroupRepo.saveAttachDettachEmployeeBillGroup(mpgSchemeBillGroupModel.getSevaarthId(),
					lLongArrDcpsEmpIdstoBeAttached[lInt], mpgSchemeBillGroupModel.getSchemebillGroupId(),
					"Attach");
		}
	}



	return result;
}

@Override
public MstDcpsBillGroup findAllMpgSchemeBillGroupbyParameter(Long valueOf) {
	// TODO Auto-generated method stub
	MstDcpsBillGroup mpgSchemeBillGroupEntity = ddoBillGroupRepo.findMpgSchemeBillGroupBySchemeBillGroupId(valueOf);
	return mpgSchemeBillGroupEntity;
}

@Override
public MstDcpsBillGroup findAllMpgSchemeBillGroupbyParameter(int parseInt) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public List<Object[]> isPaybillIsInProcess(String sevaarthId) {
	// TODO Auto-generated method stub
	return ddoBillGroupRepo.isPaybillIsInProcess(sevaarthId);
}

@Override
public int deleteEmpMpgDdoAllowDeduc(String sevaarthId) {
	// TODO Auto-generated method stub
	ddoBillGroupRepo.deleteEmpMpgDdoAllowDeduc(sevaarthId);
	return 0;
}

@Override
public int saveEmpMpgDdoAllowDeduc(Object allow_deduct_id, Long empId, String sevaarthId,
		String effectiveDate) {
	ddoBillGroupRepo.saveEmpMpgDdoAllowDeduc(allow_deduct_id, empId,sevaarthId,effectiveDate);
	return 0;
}

@Override
public List<Object[]> empEligibilityForAllowAndDeductCheckBoxId(String id)
{
	List<Object[]> deptEligibilityForAllowAndDeductEntity = ddoBillGroupRepo.empEligibilityForAllowAndDeductCheckBoxId(id);
	return deptEligibilityForAllowAndDeductEntity;
}

@Override
public List<Object[]> findAllMpgSchemeBillGroupbyParameter1(int id) {
	// TODO Auto-generated method stub
	List<Object[]> deptEligibilityForAllowAndDeductEntity = ddoBillGroupRepo.findMpgSchemeBillGroupBySchemeBillGroupId1(id);
	return deptEligibilityForAllowAndDeductEntity;
}

@Override
public Object findattachpostlist(String userName, String billgrpId) {
	// TODO Auto-generated method stub
	return ddoBillGroupRepo.findattachpostlist(userName, billgrpId);
}

@Override
public Object finddetachpostlist(String userName, String billgrpId) {
	// TODO Auto-generated method stub
	return ddoBillGroupRepo.finddetachpostlist(userName, billgrpId);
}

@Override
public String saveAttachDettachPost(MpgSchemeBillGroupModel mpgSchemeBillGroupModel) {

	String result = "N";
	String lStrPostIdstoBeDetached = mpgSchemeBillGroupModel.getPostIdstoBeDetached();
																							
	String[] lStrArrDcpsEmpIdstoBeDetached = lStrPostIdstoBeDetached.split("~");
	Long[] lLongArrDcpsEmpIdstoBeDetached = new Long[lStrArrDcpsEmpIdstoBeDetached.length];
	for (Integer lInt = 0; lInt < lStrArrDcpsEmpIdstoBeDetached.length; lInt++) {
		if (lStrArrDcpsEmpIdstoBeDetached[lInt] != "" && !lStrArrDcpsEmpIdstoBeDetached[lInt].equals("")) {
			lLongArrDcpsEmpIdstoBeDetached[lInt] = Long.valueOf(lStrArrDcpsEmpIdstoBeDetached[lInt]);
			result = ddoBillGroupRepo.saveAttachDettachPostToBillGroup(mpgSchemeBillGroupModel.getSevaarthId(),
					lLongArrDcpsEmpIdstoBeDetached[lInt], mpgSchemeBillGroupModel.getSchemebillGroupId(),
					"Detach");
		}
	}

	String lStrPostIdstoBeattached = mpgSchemeBillGroupModel.getPostIdstoBeAttached(); 
																							
	String[] lStrArrPostIdstoBeAttached = lStrPostIdstoBeattached.split("~");
	Long[] lLongArrPostIdstoBeAttached = new Long[lStrArrPostIdstoBeAttached.length];
	for (Integer lInt = 0; lInt < lStrArrPostIdstoBeAttached.length; lInt++) {
		if (lStrArrPostIdstoBeAttached[lInt] != "" && !lStrArrPostIdstoBeAttached[lInt].equals("")) {
			lLongArrPostIdstoBeAttached[lInt] = Long.valueOf(lStrArrPostIdstoBeAttached[lInt]);
			result = ddoBillGroupRepo.saveAttachDettachPostToBillGroup(mpgSchemeBillGroupModel.getSevaarthId(),
					lLongArrPostIdstoBeAttached[lInt], mpgSchemeBillGroupModel.getSchemebillGroupId(),
					"Attach");
		}
	}

	return result;

}

@Override
public MstDcpsBillGroup findBillGroupDtls(Long billgroupid) {
	MstDcpsBillGroup mstBillGroupEntity = ddoBillGroupRepo.findDcpsBillGroupById(billgroupid);
	return mstBillGroupEntity;
}

@Override
public void deleteBillGroup(MstDcpsBillGroup mstBillGroupEntity) {
	ddoBillGroupRepo.deleteBillGroup(mstBillGroupEntity);
}
}



