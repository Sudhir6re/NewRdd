package com.mahait.gov.in.repository;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.common.CommonConstants;
import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.entity.CmnLookupMst;
import com.mahait.gov.in.entity.DcpsContributionEntity;
import com.mahait.gov.in.entity.MstDcpsBillGroup;
import com.mahait.gov.in.entity.MstDcpsContriVoucherDtlEntity;
import com.mahait.gov.in.entity.MstEmployeeEntity;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.entity.PaybillGenerationTrnEntity;
import com.mahait.gov.in.model.DcpContributionModel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@Repository
public class OnlineContributionRepoImpl implements OnlineContributionRepo {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<CmnLookupMst> getPaymentTypeLst() {
		String HQL = "FROM CmnLookupMst as t  where lookupId in ('700047','700048','700049')  ORDER BY t.lookupId";
		return (List<CmnLookupMst>) entityManager.createQuery(HQL).getResultList();
	}

	@Override
	public Boolean checkIfBillAlreadyGenerated(Long billGroupId, Integer monthId, Integer finYearId, String ddoCode) {
		Session ghibSession = entityManager.unwrap(Session.class);
		StringBuilder lSBQuery = new StringBuilder();
		List<PaybillGenerationTrnEntity> PaybillGenerationTrnEntityLst = new ArrayList<PaybillGenerationTrnEntity>();
		Boolean flag = false;

		lSBQuery.append(
				"FROM PaybillGenerationTrnEntity where ddoCode=:ddoCode and schemeBillgroupId = :billNo and paybillMonth = :month and paybillYear = :year and  isActive not in(8)");

		Query lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("billNo", billGroupId);
		lQuery.setParameter("month", monthId);
		lQuery.setParameter("year", finYearId);
		lQuery.setParameter("ddoCode", ddoCode);

		PaybillGenerationTrnEntityLst = lQuery.getResultList();
		if (PaybillGenerationTrnEntityLst.size() != 0) {
			flag = true;
		}
		return flag;
	}

	@Override
	public List<Object[]> getEmpListForContribution(DcpContributionModel dcpContributionModel, OrgUserMst messages,
			String startDate) {
		Session ghibSession = entityManager.unwrap(Session.class);
		Integer roleId = messages.getMstRoleEntity().getRoleId(); // 2 DDO 3 DDOAST
		String useType = dcpContributionModel.getUseType();

		String lStrTypeOfPaymentMaster = dcpContributionModel.getTypeOfPayment();
		int delayedFinYearId = dcpContributionModel.getDelayedFinYearId();
		int delayedMonthId = dcpContributionModel.getDelayedMonthId();
		int finYearId = dcpContributionModel.getFinYearId();
		int monthId = dcpContributionModel.getMonthId();
		Long billGroupId = dcpContributionModel.getBillGroupId();

		String ddoCode = roleId == 3 ? messages.getDdoCode() : dcpContributionModel.getDdoCode();

		StringBuilder SBQuery = new StringBuilder();
		Double lDoubleDefaultDArateForNon5th6thPC = 58d;
		List empList = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		if (roleId == 3) {
			String paymentType = dcpContributionModel.getTypeOfPayment();
			if (paymentType != null) {
				switch (paymentType) {
				case "700048":
					startDate = getStartDate(dcpContributionModel.getDAArrearStartDate(), monthId, finYearId, sdf);
					break;
				case "700049":
					startDate = getStartDate(dcpContributionModel.getPayArrearStartDate(), monthId, finYearId, sdf);
					break;
				default:
					startDate = createStartDate(delayedMonthId, delayedFinYearId);
					break;
				}
			}
		} else {
			startDate = createStartDate(monthId, finYearId);
		}

		try {
			SBQuery.append("select Em.employee_id,Em.dcps_no,Em.employee_full_name_en,Em.pay_commission_code,"
					+ " CASE " + "        WHEN Em.pay_commission_code = '"
					+ CommonConstants.PAYBILLDETAILS.COMMONCODE_PAYCOMMISSION_7PC
					+ "' THEN COALESCE(CO.BASIC_PAY, EM.seven_pc_basic)\r\n"
					+ "        ELSE COALESCE(CO.BASIC_PAY, EM.BASIC_PAY)\r\n" + "    END AS BASIC_PAY,"
					+ "COALESCE(CO.DCPS_CONTRIBUTION_ID,0) as DCPS_CONTRIBUTION_ID,COALESCE(CO.TYPE_OF_PAYMENT,'"
					+ dcpContributionModel.getTypeOfPayment()
					+ "') as TYPE_OF_PAYMENT,COALESCE(CO.MONTH_ID,0) as MONTH_ID,COALESCE(CO.FIN_YEAR_ID,0) as FIN_YEAR_ID,");

			SBQuery.append(" COALESCE(CASE WHEN DA.percentage = 0 THEN NULL ELSE DA.percentage END, "
					+ lDoubleDefaultDArateForNon5th6thPC + ") AS percentage, ");
			SBQuery.append("CO.REG_STATUS,EM.DOJ,CO.DA,CO.DP,CO.CONTRIBUTION,");

			SBQuery.append(
					" CO.startDate StartDate,CO.endDate,COALESCE(CO.NPS_EMPLR_CONTRI_DED,0) as NPS_EMPLR_CONTRI_DED,Em.sevaarth_id FROM employee_mst EM ");

			SBQuery.append(" LEFT OUTER JOIN TRN_DCPS_CONTRIBUTION CO ON EM.employee_id=CO.DCPS_EMP_ID AND CO.MONTH_ID="
					+ monthId + "" + " AND CO.FIN_YEAR_ID=" + finYearId + " AND CO.DDO_CODE = '" + ddoCode + "'");

			if (roleId == 3) {
				SBQuery.append(" AND CO.TYPE_OF_PAYMENT = '" + lStrTypeOfPaymentMaster.trim() + "'");
			}

			if ((roleId == 3 && (useType.equals("ViewAll")) && lStrTypeOfPaymentMaster.equals("700047"))) {
				if (delayedMonthId != 0 && delayedFinYearId != 0) {
					SBQuery.append(" AND CO.DELAYED_MONTH_ID = " + delayedMonthId);
					SBQuery.append(" AND CO.DELAYED_FIN_YEAR_ID = " + delayedFinYearId);
				}
			}

			SBQuery.append(
					" LEFT OUTER JOIN mst_dcps_contri_voucher_dtls CV ON CV.treasury_code=CO.TREASURY_CODE AND CV.month_id=CO.MONTH_ID "
							+ " AND CV.year_id = CO.FIN_YEAR_ID AND CV.bill_group_id=CO.BILL_GROUP_ID AND CV.ddo_code = CO.ddo_code");

			SBQuery.append(
					" LEFT JOIN ALLOWANCE_DEDUCTION_WISE_RULE_MST DA ON DA.PAY_COMMISSION_CODE = EM.PAY_COMMISSION_CODE AND  (('"
							+ startDate + "' BETWEEN DA.start_date AND DA.end_date) OR ('" + startDate
							+ "' >= DA.start_date and DA.end_date IS NULL))"
							+ " AND DA.department_allowdeduc_code = (CASE WHEN EM.PAY_COMMISSION_CODE = 700005 THEN "
							+ CommonConstants.PAYBILLDETAILS.SVNPC_ALLOW_DEDUC_CODE + " ELSE "
							+ CommonConstants.PAYBILLDETAILS.SIXPC_ALLOW_DEDUC_CODE + " END)");

			// Code Added to show employees of valid post and service only for first online
			// contribution entry
			if (roleId == 3 && (useType.equals("ViewAll"))) {
				SBQuery.append(" join org_post_mst OP on OP.post_id = EM.post_detail_id and OP.ACTIVATE_FLAG = 1");
			}

			if ((roleId == 3 && (useType.equals("ViewAll")))) {
				SBQuery.append(
						" join employee_allowdeduc_mpg HRCGM on HRCGM.employee_id = EM.employee_id and HRCGM.IS_ACTIVE = '1' ");
				SBQuery.append(
						" join department_allowdeduc_mst HPDT on HPDT.department_allowdeduc_code = HRCGM.department_allowdeduc_code ");

				if (lStrTypeOfPaymentMaster.equals("700046")) // Regular
				{
					SBQuery.append(" AND HPDT.department_allowdeduc_code = 50 "); // DCPS 59
				}
				if (lStrTypeOfPaymentMaster.equals("700047")) // Delayed
				{
					SBQuery.append(" AND HPDT.department_allowdeduc_code = 52 "); // 120 DCPS Delayed
				}
				if (lStrTypeOfPaymentMaster.equals("700048")) // DA Arrear
				{
					SBQuery.append(" AND HPDT.department_allowdeduc_code = 51 "); // 122 DCPS DA
				}
				if (lStrTypeOfPaymentMaster.equals("700049")) // Pay Arrear
				{
					SBQuery.append(" AND HPDT.department_allowdeduc_code = 46 "); // 121 DCPS Pay
				}
			}

			if ((roleId == 3 && (useType.equals("ViewAll")))) {
				SBQuery.append(" WHERE EM.DDO_CODE='" + ddoCode + "'");
			} else {
				SBQuery.append(" WHERE CO.DDO_CODE='" + ddoCode + "'");
			}

			if ((roleId == 2)) {
				SBQuery.append(" AND (CO.BILL_GROUP_ID=" + billGroupId + ")");
			} else {
				SBQuery.append(" AND (EM.BILLGROUP_ID=" + billGroupId + ")");
			}

			// Code Added to show employees of valid post and service and date of joining
			// and DCPS employee only for first online contribution entry
			if ((roleId == 3 && (useType.equals("ViewAll")))) {
				SBQuery.append(" AND EM.is_active=1 AND EM.dcps_gpf_flag = 'Y' AND EM.DOJ <'" + startDate + "'");
				SBQuery.append(
						" AND ( EM.EMP_SERVICE_END_DATE is null or EM.EMP_SERVICE_END_DATE > '" + startDate + "' )");
				SBQuery.append(" AND (EM.SUPER_ANN_DATE is null or EM.SUPER_ANN_DATE >'" + startDate + "' )");
				SBQuery.append(
						" AND (OP.END_DATE is null or OP.END_DATE > '" + startDate + "' ) AND OP.ACTIVATE_FLAG = 1 ");
			}

			if (useType.equals("ViewAll")) {
				SBQuery.append(" AND (CO.REG_STATUS IS NULL OR CO.REG_STATUS = 0) ");
			} else if (roleId == 2 && useType.equals("ViewForwarded")) {
				SBQuery.append(" AND CO.REG_STATUS = 0"); // for Online
			} else if (roleId == 2 && useType.equals("ViewApproved")) {
				SBQuery.append(" AND CO.REG_STATUS in (1,3)"); // for Online
			}
			if (roleId == 3 && useType.equals("ViewRejected")) {
				SBQuery.append(" AND CO.REG_STATUS = -3"); // 3 for Online
			}
			SBQuery.append(" Order By \n");

			SBQuery.append(" EM.employee_full_name_en ASC");

			Query stQuery = ghibSession.createNativeQuery(SBQuery.toString());

			empList = stQuery.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return empList;
	}

	@Override
	public Long saveMstDcpsContriVoucherDtlEntity(MstDcpsContriVoucherDtlEntity mstDcpsContriVoucherDtlEntity) {
		Session ghibSession = entityManager.unwrap(Session.class);
		MstDcpsContriVoucherDtlEntity mergedEntity = (MstDcpsContriVoucherDtlEntity) ghibSession
				.merge(mstDcpsContriVoucherDtlEntity);
		return mergedEntity.getMstDcpsContriVoucherDtls();
	}

	@Override
	public void saveDcpsContributionEntity(DcpsContributionEntity dcpsContributionEntity) {
		Session ghibSession = entityManager.unwrap(Session.class);
		ghibSession.saveOrUpdate(dcpsContributionEntity);
	}

	@Override
	public List<Object[]> getSchemeCodeByBillGroupId(String billGroupId) {
		Session currentSession = entityManager.unwrap(Session.class);
		String hql = "select a.scheme_code,a.scheme_name from mst_scheme a inner join MST_DCPS_BILL_GROUP b on a.scheme_code=b.scheme_code where b.BILL_GROUP_ID="
				+ billGroupId;
		Query query = currentSession.createNativeQuery(hql);
		return query.getResultList();
	}

	@Override
	public MstEmployeeEntity findEmpDtlBySevaarthId(String sevaarthId) {
		StringBuilder sbQuery = new StringBuilder();
		sbQuery.append("SELECT Em FROM MstEmployeeEntity Em WHERE Em.sevaarthId = :sevaarthId");
		TypedQuery<MstEmployeeEntity> query = entityManager.createQuery(sbQuery.toString(), MstEmployeeEntity.class);
		query.setParameter("sevaarthId", sevaarthId.toString()); // Optionally specify type
		try {
			return query.getSingleResult(); // Get a single result
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public List<Object[]> findTreasuryList(OrgUserMst messages) {
		Session currentSession = entityManager.unwrap(Session.class);
		StringBuilder sb = new StringBuilder();
		sb.append(" select  b.loc_id,b.loc_name from RLT_DDO_ORG a");
		sb.append(" inner join cmn_location_mst b on a.LOCATION_CODE=b.location_code");
		sb.append(" where a.ddo_code=:ddo_code");

		Query query = currentSession.createNativeQuery(sb.toString());
		query.setParameter("ddo_code", messages.getDdoCode());
		return query.getResultList();

	}

	@Override
	public List<MstDcpsBillGroup> findBillgroupList(OrgUserMst messages, Integer regStatus) {
		Session currentSession = entityManager.unwrap(Session.class);
		List<MstDcpsBillGroup> lstMstDcpsBillGroup = new ArrayList<>();

		// 1 -3 approved 2 forwarded -3 rejected

		if (messages.getMstRoleEntity().getRoleId() == 2) {
			String sql = " SELECT a.bill_group_id, a.description FROM MST_DCPS_BILL_GROUP a "
					+ " INNER JOIN TRN_DCPS_CONTRIBUTION b ON a.bill_group_id = b.BILL_GROUP_ID "
					+ " WHERE a.ddo_code in(select zp_ddo_code from rlt_zp_ddo_map where rept_ddo_code= :ddoCode) AND b.reg_Status = :regStatus "
					+ " GROUP BY a.bill_group_id, a.description " + " ORDER BY a.description DESC";

			Query query = currentSession.createNativeQuery(sql).setParameter("ddoCode", messages.getDdoCode())
					.setParameter("regStatus", regStatus);

			List<Object[]> lstprop = query.getResultList();

			for (Object[] objLst : lstprop) {
				MstDcpsBillGroup mstDcpsBillGroup = new MstDcpsBillGroup();
				mstDcpsBillGroup.setDcpsDdoBillGroupId(StringHelperUtils.isNullBigInteger(objLst[0]).longValue());
				mstDcpsBillGroup.setDescription(StringHelperUtils.isNullString(objLst[1]));
				lstMstDcpsBillGroup.add(mstDcpsBillGroup);
			}
			return lstMstDcpsBillGroup;
		} else {
			String HQL = "FROM MstDcpsBillGroup t WHERE t.dcpsDdoCode = :ddoCode ORDER BY t.dcpsDdoBillGroupId";
			return (List<MstDcpsBillGroup>) entityManager.createQuery(HQL)
					.setParameter("ddoCode", messages.getDdoCode()).getResultList();
		}
	}

	@Override
	public Optional<DcpsContributionEntity> findDcpsContri(Long dcpContributionId) {
		Session ghibSession = entityManager.unwrap(Session.class);

		if (dcpContributionId == null) {
			return Optional.empty(); // Return empty if the ID is null
		}
		DcpsContributionEntity entity = ghibSession.find(DcpsContributionEntity.class, dcpContributionId);
		return Optional.ofNullable(entity);
	}

	@SuppressWarnings("deprecation")
	@Override
	public Optional<MstDcpsContriVoucherDtlEntity> findMstDcpsContriVoucherDtlEntity(
			DcpContributionModel dcpContributionModel) {
		Session ghibSession = entityManager.unwrap(Session.class);
		StringBuilder lSBQuery = new StringBuilder();
		lSBQuery.append(
				"FROM MstDcpsContriVoucherDtlEntity WHERE ddoCode = :ddoCode AND yearId = :yearId AND monthId = :monthId AND treasuryCode = :treasuryCode AND billGroupId = :billGroupId");
		Query lQuery = ghibSession.createQuery(lSBQuery.toString(),
				MstDcpsContriVoucherDtlEntity.class);
		lQuery.setParameter("yearId", dcpContributionModel.getFinYearId());
		lQuery.setParameter("monthId", dcpContributionModel.getMonthId());
		lQuery.setParameter("treasuryCode", dcpContributionModel.getTreasuryCode());
		lQuery.setParameter("ddoCode", dcpContributionModel.getDdoCode());
		lQuery.setParameter("billGroupId", dcpContributionModel.getBillGroupId());

		List<MstDcpsContriVoucherDtlEntity> lstMstDcpsContriVoucherDtlEntity = lQuery.getResultList();
		return lstMstDcpsContriVoucherDtlEntity.stream().findFirst();
	}

	@Override
	public void deleteContributionIds(List<Long> idsToDelete) {
		Session session = entityManager.unwrap(Session.class);
		String hql = "DELETE FROM DcpsContributionEntity WHERE dcpContributionId IN :idsToDelete";
		Query query = session.createQuery(hql);
		query.setParameter("idsToDelete", idsToDelete);
		query.executeUpdate();
	}

	@SuppressWarnings("deprecation")
	public Optional<DcpsContributionEntity> findDcpsContributionEntity(
			PaybillGenerationTrnEntity paybillGenerationTrnEntity) {
		Session ghibSession = entityManager.unwrap(Session.class);
		StringBuilder lSBQuery = new StringBuilder();
		lSBQuery.append(
				"FROM DcpsContributionEntity WHERE ddoCode = :ddoCode AND finYearId = :yearId AND monthId = :monthId AND billGroupId = :billGroupId");
		Query lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("yearId", paybillGenerationTrnEntity.getPaybillYear());
		lQuery.setParameter("monthId", paybillGenerationTrnEntity.getPaybillMonth());
		lQuery.setParameter("ddoCode", paybillGenerationTrnEntity.getDdoCode());
		lQuery.setParameter("billGroupId", paybillGenerationTrnEntity.getSchemeBillgroupId());

		List<DcpsContributionEntity> lstDcpsContributionEntity = lQuery.getResultList();
		return lstDcpsContributionEntity.stream().findFirst();
	}

	@Override
	public List<Object[]> getAllForwardedDdo(OrgUserMst messages) {
		Session currentSession = entityManager.unwrap(Session.class);
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT DISTINCT DM.ddo_Code,DM.ddo_Name FROM  Org_Ddo_Mst DM  ");
		sb.append(" JOIN  Rlt_Ddo_Org RO ON RO.ddo_Code = DM.ddo_Code ");
		sb.append(" JOIN  cmn_location_mst LM ON LM.location_Code = DM.location_Code ");
		sb.append(" JOIN Trn_Dcps_Contribution VC ON VC.ddo_Code = DM.ddo_Code WHERE   DM.ddo_Code IN ");
		sb.append("(SELECT  ZP_DDO_CODE FROM  Rlt_zp_Ddo_Map WHERE ");
		if (messages.getMstRoleEntity().getRoleId() == 2) {
			sb.append(" rept_ddo_code=:reptDdoCode)  ");
		}
		sb.append("  AND DM.ddo_Name IS NOT NULL   ");
		sb.append("  AND VC.reg_Status IN  (0,1,3,4) ");
		sb.append(" AND VC.ddo_Code = DM.ddo_Code ");
		sb.append(" order by DM.ddo_Code ASC ");
		Query query = currentSession.createNativeQuery(sb.toString());
		query.setParameter("reptDdoCode", messages.getDdoCode());
		return query.getResultList();
	}

	@Override
	public void addDcpsContributionEntityVoucherDtl(Map<String, String> formData, OrgUserMst messages) {
		Session currentSession = entityManager.unwrap(Session.class);

		Long billGroupId = Long.valueOf(formData.get("billGroupId"));
		Long voucherNo = Long.valueOf(formData.get("voucherNo"));
		Long yearId = Long.valueOf(formData.get("finYearId"));
		Long monthId = Long.valueOf(formData.get("monthId"));
		String ddoCode = formData.get("ddoCode");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = sdf.parse(formData.get("voucherDate"));
		} catch (ParseException e) {
			throw new IllegalArgumentException("Invalid start date format", e);
		}
		Timestamp voucherDate = new Timestamp(date.getTime());

		StringBuilder lSBQuery = new StringBuilder();
		lSBQuery.append(
				" update TRN_DCPS_CONTRIBUTION set VOUCHER_NO = :voucherNo , VOUCHER_DATE = :voucherDate,reg_status='1'  where BILL_GROUP_ID = :billGroupId and MONTH_ID = :monthId");
		lSBQuery.append(" and FIN_YEAR_ID = :yearId and DDO_CODE = :ddoCode");

		Query lQuery = currentSession.createNativeQuery(lSBQuery.toString());
		lQuery.setParameter("billGroupId", billGroupId);
		lQuery.setParameter("monthId", monthId);
		lQuery.setParameter("yearId", yearId);
		lQuery.setParameter("ddoCode", ddoCode);
		lQuery.setParameter("voucherNo", voucherNo);
		lQuery.setParameter("voucherDate", voucherDate);
		// lQuery.setParameter("regStatus", 1);
		lQuery.executeUpdate();
	}

	@Override
	public void addMstDcpsContriVoucherDtlEntityVoucherDtl(Map<String, String> formData, OrgUserMst messages) {
		Session currentSession = entityManager.unwrap(Session.class);

		Long billGroupId = Long.valueOf(formData.get("billGroupId"));
		Long voucherNo = Long.valueOf(formData.get("voucherNo"));
		Integer yearId = Integer.valueOf(formData.get("finYearId"));
		Integer monthId = Integer.valueOf(formData.get("monthId"));
		String ddoCode = formData.get("ddoCode");

		Timestamp updateDdate = new Timestamp(new Date().getTime());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = sdf.parse(formData.get("voucherDate"));
		} catch (ParseException e) {
			throw new IllegalArgumentException("Invalid start date format", e);
		}
		Timestamp voucherDate = new Timestamp(date.getTime());
		Timestamp updatedDate = new Timestamp(new Date().getTime());

		StringBuilder lSBQuery = new StringBuilder();
		lSBQuery.append(
				" update MST_DCPS_CONTRI_VOUCHER_DTLS SET VOUCHER_NO = :voucherNo , VOUCHER_DATE = :voucherDate,status='1',voucher_status=1,UPDATED_USER_ID=:updatedUserId,UPDATED_DATE=:updatedDate   ");
		lSBQuery.append(
				"  WHERE BILL_GROUP_ID = :billGroupId and MONTH_ID = :monthId and YEAR_ID = :yearId and DDO_CODE = :ddoCode ");

		Query lQuery = currentSession.createNativeQuery(lSBQuery.toString());
		lQuery.setParameter("billGroupId", billGroupId);
		lQuery.setParameter("monthId", monthId);
		lQuery.setParameter("yearId", yearId);
		lQuery.setParameter("ddoCode", ddoCode);
		lQuery.setParameter("voucherNo", voucherNo);
		lQuery.setParameter("voucherDate", voucherDate);
		lQuery.setParameter("updatedDate", updatedDate);
		lQuery.setParameter("updatedUserId", messages.getUserId());

		// lQuery.setParameter("status", '1');
		// lQuery.setParameter("voucherStatus", new BigInteger("1"));
		lQuery.executeUpdate();
	}

	public String createStartDate(int month2, int year2) {
		String startDate = null;
		if (month2 < 10) {
			startDate = 20 + String.valueOf(year2 - 1) + '-' + String.valueOf("0" + month2) + "-01";
		} else {
			startDate = 20 + String.valueOf(year2 - 1) + '-' + String.valueOf(month2) + "-01";
		}
		return startDate;
	}

	private String getStartDate(Date existingStartDate, int month, int year, SimpleDateFormat sdf) {
		return (existingStartDate != null) ? sdf.format(existingStartDate) : createStartDate(month, year);
	}

	@Override
	public Integer rejectContribution(Map<String, String> formData, OrgUserMst messages) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Long billGroupId = Long.valueOf(formData.get("billGroupId"));
		Integer yearId = Integer.valueOf(formData.get("finYearId"));
		Integer monthId = Integer.valueOf(formData.get("monthId"));
		String ddoCode = formData.get("ddoCode");

		Timestamp updatedDate = new Timestamp(new Date().getTime());

		StringBuilder lSBQuery = new StringBuilder();
		lSBQuery.append(
				" update MST_DCPS_CONTRI_VOUCHER_DTLS SET status='0',voucher_status=-3,UPDATED_USER_ID=:updatedUserId,UPDATED_DATE=:updatedDate  ");
		lSBQuery.append(
				"  WHERE BILL_GROUP_ID = :billGroupId and MONTH_ID = :monthId and YEAR_ID = :yearId and DDO_CODE = :ddoCode ");

		System.out.println();

		Query lQuery = currentSession.createNativeQuery(lSBQuery.toString());
		lQuery.setParameter("billGroupId", billGroupId);
		lQuery.setParameter("monthId", monthId);
		lQuery.setParameter("yearId", yearId);
		lQuery.setParameter("ddoCode", ddoCode);
		lQuery.setParameter("updatedDate", updatedDate);
		lQuery.setParameter("updatedUserId", messages.getUserId());
		 lQuery.executeUpdate();

		StringBuilder lSBQuery1 = new StringBuilder();
		lSBQuery1.append(
				" update TRN_DCPS_CONTRIBUTION set reg_status='-3',UPDATED_DATE=:updatedDate,UPDATED_USER_ID=:updatedUserId  where BILL_GROUP_ID = :billGroupId and MONTH_ID = :monthId");
		lSBQuery1.append(" and FIN_YEAR_ID = :yearId and DDO_CODE = :ddoCode");

		Query  lQuery1 = currentSession.createNativeQuery(lSBQuery1.toString());
	    lQuery1.setParameter("billGroupId", billGroupId);
	    lQuery1.setParameter("monthId", monthId);
	    lQuery1.setParameter("yearId", yearId);
	    lQuery1.setParameter("ddoCode", ddoCode);
	    lQuery1.setParameter("updatedDate", updatedDate);
	    lQuery1.setParameter("updatedUserId", messages.getUserId());
		return lQuery1.executeUpdate();
	}

}
