package com.mahait.gov.in.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.mahait.gov.in.entity.CmnLookupMst;
import com.mahait.gov.in.entity.DcpsContributionEntity;
import com.mahait.gov.in.entity.MstDcpsBillGroup;
import com.mahait.gov.in.entity.MstDcpsContriVoucherDtlEntity;
import com.mahait.gov.in.entity.MstEmployeeEntity;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.entity.PaybillGenerationTrnEntity;
import com.mahait.gov.in.model.DcpContributionModel;

public interface OnlineContributionRepo {

	List<CmnLookupMst> getPaymentTypeLst();

	Boolean checkIfBillAlreadyGenerated(Long billGroupId, Integer monthId, Integer finYearId, String ddoCode);

	List<Object[]> getEmpListForContribution(DcpContributionModel dcpContributionModel, OrgUserMst messages,
			String startDate);

	Long saveMstDcpsContriVoucherDtlEntity(MstDcpsContriVoucherDtlEntity mstDcpsContriVoucherDtlEntity);

	void saveDcpsContributionEntity(DcpsContributionEntity dcpsContributionEntity);

	List<Object[]> getSchemeCodeByBillGroupId(String billGroupId);

	MstEmployeeEntity findEmpDtlBySevaarthId(String sevaarthId);

	List<Object[]> findTreasuryList(OrgUserMst messages);

	List<MstDcpsBillGroup> findBillgroupList(OrgUserMst messages, Integer regStatus);

	Optional<DcpsContributionEntity> findDcpsContri(Long dcpContributionId);

	Optional<MstDcpsContriVoucherDtlEntity> findMstDcpsContriVoucherDtlEntity(
			DcpContributionModel dcpContributionModel);

	void deleteContributionIds(List<Long> idsToDelete);

	Optional<DcpsContributionEntity> findDcpsContributionEntity(PaybillGenerationTrnEntity paybillGenerationTrnEntity);

	List<Object[]> getAllForwardedDdo(OrgUserMst messages);


	void addDcpsContributionEntityVoucherDtl(Map<String, String> formData, OrgUserMst messages);

	void addMstDcpsContriVoucherDtlEntityVoucherDtl(Map<String, String> formData, OrgUserMst messages);

	Integer rejectContribution(Map<String, String> formData, OrgUserMst messages);

}
