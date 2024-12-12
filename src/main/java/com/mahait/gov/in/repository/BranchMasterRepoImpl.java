package com.mahait.gov.in.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.entity.MstBankBranchEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.validation.Valid;

@Repository
public class BranchMasterRepoImpl implements BranchMasterRepo {

	@PersistenceContext
	EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<MstBankBranchEntity> listOfBranch() {
		Session currentSession = entityManager.unwrap(Session.class);
		String HQL = "select a.*,b.bank_name from bank_branch_mst a inner join bank_mst b on a.bank_code=b.bank_code";
		Query query = currentSession.createNativeQuery(HQL);
		List<Object[]> lstobj = query.getResultList();

		List<MstBankBranchEntity> lstMstBankBranchEntity = new ArrayList();
		for (Object obj[] : lstobj) {
			MstBankBranchEntity mstBankBranchEntity = new MstBankBranchEntity();
			/*
			 * BigDecimal branchId = (BigDecimal) obj[0];
			 * mstBankBranchEntity.setBankBranchId(branchId.longValue());
			 */

			mstBankBranchEntity.setBankBranchId(StringHelperUtils.isNullBigInteger(obj[0]).longValue());
			mstBankBranchEntity.setBankBranchCode(StringHelperUtils.isNullBigInteger(obj[2]).longValue());
			mstBankBranchEntity.setBankBranchName(StringHelperUtils.isNullString(obj[3]));
			mstBankBranchEntity.setBankBranchShortName(StringHelperUtils.isNullString(obj[4]));
			mstBankBranchEntity.setBankName(StringHelperUtils.isNullString(obj[18]));
			mstBankBranchEntity.setIsActive(StringHelperUtils.isNullCharacter(obj[12]));
			mstBankBranchEntity.setIfscCode(StringHelperUtils.isNullString(obj[11]));
			mstBankBranchEntity.setBankBranchAddress(StringHelperUtils.isNullString(obj[1]));
			mstBankBranchEntity.setMicrCode(StringHelperUtils.isNullString(obj[14]));
			mstBankBranchEntity.setBankCode(StringHelperUtils.isNullBigInteger(obj[6]).longValue());

			lstMstBankBranchEntity.add(mstBankBranchEntity);
		}
		return lstMstBankBranchEntity;
	}

	@Override
	public int saveBankBranch(@Valid MstBankBranchEntity mstBankBranchEntity) {
		Session currentSession = entityManager.unwrap(Session.class);
		Serializable saveId = (Serializable) currentSession.save(mstBankBranchEntity);
		return (Integer) saveId;
	}

	@Override
	public MstBankBranchEntity findBankBranchById(Long bankBranchId) {
		Session currentSession = entityManager.unwrap(Session.class);
		MstBankBranchEntity mstBankBranchEntity = currentSession.get(MstBankBranchEntity.class, bankBranchId);
		return mstBankBranchEntity;
	}

	@Override
	public Serializable updateBankBranch(MstBankBranchEntity brachobject) {
		Session currentSession = entityManager.unwrap(Session.class);
		Serializable saveId = 1;
		currentSession.update(brachobject);
		return (Integer) saveId;
	}

	@Override
	public List<Long> validateIFSCCode(Integer bankcode, String ifscCode) {
		Session currentSession = entityManager.unwrap(Session.class);
		String sql = "SELECT COUNT(*) FROM bank_branch_mst WHERE ifsc_code = :ifscCode";
		Query query = currentSession.createNativeQuery(sql);
		query.setParameter("ifscCode", ifscCode);
		List<Long> lstresult = query.getResultList();
		return lstresult;
	}

}
