package com.mahait.gov.in.service;

import java.util.List;
import java.util.Map;

import com.mahait.gov.in.entity.MstDcpsBillGroup;
import com.mahait.gov.in.entity.MstEmployeeEntity;
import com.mahait.gov.in.entity.MstScheme;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.BillgroupMaintainanceModel;
import com.mahait.gov.in.model.MpgSchemeBillGroupModel;
import com.mahait.gov.in.model.MstEmployeeModel;

public interface DdoBillGroupService {

	public List<MstDcpsBillGroup> lstBillName(String string);



	public long saveBillGroupMaintainance(BillgroupMaintainanceModel billgroupMaintainanceModel, OrgUserMst messages, long locId);



	public List<MstScheme> getSchemeCodeAgainstName(String schemeId);



	public List<MstEmployeeModel> findAllEmployeesByDDOName(String ddoCode);


	public List<MstEmployeeEntity> firstgetfindAllEmployeeByddoCode(String ddoCode);



	public MstDcpsBillGroup findAllMpgSchemeBillGroupbyParameter(int parseInt);



	public List<Object[]> findAttachedEmployee(String userName, String string);



	public Object findDettachEmployee(String userName, String string);



//	public List<Object[]> getBillDtlsForAlreadySaved(String billGrpId);
	Map<String, Object> getBillDtlsForAlreadySaved(String billGrpId);


	public String saveAttachDettachEmployee(MpgSchemeBillGroupModel mpgSchemeBillGroupModel);



	public MstDcpsBillGroup findAllMpgSchemeBillGroupbyParameter(Long valueOf);



	public List<Object[]> isPaybillIsInProcess(String sevaarthId);



	public int deleteEmpMpgDdoAllowDeduc(String sevaarthId);



	public int saveEmpMpgDdoAllowDeduc(Object object, Long empId, String sevaarthId,
			String effectiveDate);



	public List<Object[]> empEligibilityForAllowAndDeductCheckBoxId(String sevaarth_id);



	public List<Object[]> findAllMpgSchemeBillGroupbyParameter1(int input);



	public Object findattachpostlist(String userName, String billgrpId);



	public Object finddetachpostlist(String userName, String billgrpId);



	public String saveAttachDettachPost(MpgSchemeBillGroupModel mpgSchemeBillGroupModel);
}
