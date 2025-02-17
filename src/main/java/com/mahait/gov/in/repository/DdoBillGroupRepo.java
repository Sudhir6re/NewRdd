package com.mahait.gov.in.repository;

import java.math.BigInteger;
import java.util.List;

import com.mahait.gov.in.entity.MstDcpsBillGroup;
import com.mahait.gov.in.entity.MstEmployeeEntity;
import com.mahait.gov.in.entity.MstScheme;
import com.mahait.gov.in.entity.RltBillgroupClassgroup;


public interface DdoBillGroupRepo {
	
	public List<MstDcpsBillGroup> lstBillName(String username);

	public long saveBillGroupMaintainance(MstDcpsBillGroup mstDcpsBillGroup);

	public List<MstScheme> getSchemeCodeAgainstName(String schemeId);

	public List<Object[]> findAllEmployeesByDDOName(String ddoCode);

	public List<MstEmployeeEntity> firstgetfindAllEmployeeByddoCode(String ddocode);

	public List<Object[]> findAttachedEmployee(String ddocode, String scmebillgroupid);

	public List<Object[]> findDettachEmployee(String ddocode, String scmebillgroupid);

	public List<Object[]> getBillDtlsForAlreadySaved(String billGrpId);

	public int checkGroupExistsOrNotForBG(Long long1);

	public void deleteClassGroupsForGivenBGId(Long long1);

	public long saveDcpsBillGroupMpg(RltBillgroupClassgroup rltBillgroupClassgroup);

	public MstDcpsBillGroup findDcpsBillGroupById(Long billGroupId);

	public String saveAttachDettachEmployeeBillGroup(String sevaarthId,Long lLongArrDcpsEmpIdstoBeDetached, Long long1,String status);

	public MstDcpsBillGroup findMpgSchemeBillGroupBySchemeBillGroupId(Long valueOf);

	public List<Object[]> isPaybillIsInProcess(String sevaarthId);

	public int deleteEmpMpgDdoAllowDeduc(String sevaarthId);

	public int saveEmpMpgDdoAllowDeduc(Object allow_deduct_id, Long empId, String sevaarthId,
			String effectiveDate);

	public List<Object[]> empEligibilityForAllowAndDeductCheckBoxId(String id);

	public List<Object[]> findMpgSchemeBillGroupBySchemeBillGroupId1(int id);

	public List getBillgroupDtlsForAlreadySaved(String billGrpId);

	public List<Object[]> findattachpostlist(String userName, String billgrpId);

	public List<Object[]> finddetachpostlist(String userName, String billgrpId);

	public String saveAttachDettachPostToBillGroup(String sevaarthId, Long lLongArrDcpsEmpIdstoBeDetached, Long schemebillGroupId,
			String status);

}
