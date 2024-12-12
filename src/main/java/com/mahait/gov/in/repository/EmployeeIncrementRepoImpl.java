package com.mahait.gov.in.repository;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.entity.EmployeeIncrementEntity;
import com.mahait.gov.in.entity.MstEmployeeEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;


@Repository
public class EmployeeIncrementRepoImpl implements EmployeeIncrementRepo {
	
	@PersistenceContext
	EntityManager entityManager;


	@Override
	public String officeName(String userName) {
		Session currentSession = entityManager.unwrap(Session.class);
		String hql = "select ddo_name from ddo_reg_mst  where ddo_code  = '" + userName + "'";
		Query query = currentSession.createNativeQuery(hql);
		return query.getResultList().get(0).toString();
	}


	@Override
	public List<Object[]> lstEmpforMTR21(String orderNo, int levelRoleVal, String ddoCode) {//,String locId
		Session currentSession = entityManager.unwrap(Session.class);
		
		String hql=null;
		if(levelRoleVal==1)
		{
			hql ="select o.EMP_FNAME,o.EMP_MNAME,o.EMP_LNAME,d.DSGN_NAME,fix.PAY_FIX_DATE,fix.OLD_BASIC,fix.NEW_BASIC,fix.NXT_INCR_DATE,fix.INCR_ORDER_DATE,fix.PAY_COMMISSION_ID\r\n" + 
					"FROM ORG_EMP_MST o,HR_EIS_EMP_MST e,ORG_USER_MST u,ORG_USERPOST_RLT uu,ORG_POST_DETAILS_RLT post,ORG_DESIGNATION_MST d,HR_EIS_OTHER_DTLS ooo,HR_PAYFIX_MST fix\r\n" + 
					"where o.EMP_ID=e.EMP_MPG_ID and o.USER_ID=u.USER_ID and u.USER_ID=uu.USER_ID and uu.ACTIVATE_FLAG = 1 and post.POST_ID = uu.POST_ID and fix.USER_ID=u.USER_ID and \r\n" + 
					"post.DSGN_ID=d.DSGN_ID and fix.INCR_CERTI_ORDER_NO= '"+orderNo+"'  ";//and post.LOC_ID ='"+locId+"'
		}else {
			hql ="select o.EMP_FNAME,o.EMP_MNAME,o.EMP_LNAME,d.DSGN_NAME,fix.PAY_FIX_DATE,fix.OLD_BASIC,fix.NEW_BASIC,fix.NXT_INCR_DATE,fix.INCR_ORDER_DATE,fix.PAY_COMMISSION_ID\r\n" + 
					"FROM ORG_EMP_MST o,HR_EIS_EMP_MST e,ORG_USER_MST u,ORG_USERPOST_RLT uu,ORG_POST_DETAILS_RLT post,ORG_DESIGNATION_MST d,HR_EIS_OTHER_DTLS ooo,HR_PAYFIX_MST fix\r\n" + 
					"where o.EMP_ID=e.EMP_MPG_ID and o.USER_ID=u.USER_ID and u.USER_ID=uu.USER_ID and uu.ACTIVATE_FLAG = 1 and post.POST_ID = uu.POST_ID and fix.USER_ID=u.USER_ID and \r\n" + 
					"post.DSGN_ID=d.DSGN_ID and fix.INCR_CERTI_ORDER_NO= '"+orderNo+"' ";//and post.LOC_ID = '"+locId+"'
		}
		
		System.out.println("lstEmpforMTR21------" + hql);
		Query query = currentSession.createNativeQuery(hql);
		return query.getResultList();
	}


	@Override
	public List<Object[]> getIncrementDataForReptDDO(String userName, String currYear) {
		Session currentSession = entityManager.unwrap(Session.class);
		String hql = "SELECT\r\n" + 
				"        distinct p.increment_order_no, \r\n" + 
				"        p.increment_order_date,\r\n" + 
				"        p.is_active,\r\n" + 
				"        zp.zp_ddo_code,\r\n" + 
				"        ddo.ddo_office   \r\n" + 
				"    FROM\r\n" + 
				"        RLT_ZP_DDO_MAP zp,\r\n" + 
				"        ORG_DDO_MST ddo,\r\n" + 
				"        employee_increment p  \r\n" + 
				"    where\r\n" + 
				"        zp.ZP_DDO_CODE = ddo.DDO_CODE \r\n" + 
				"        and p.is_active = '1'\r\n" + 
				"        and zp.ZP_DDO_CODE = p.DDO_CODE \r\n" + 
				"        and zp.REPT_DDO_CODE ='"+userName+"'  order by zp.zp_ddo_code";//and year(p.CREATED_DATE)="+currYear+"
		System.out.println("getEmpDataForIncrementApproval------" + hql);
		Query query = currentSession.createNativeQuery(hql);
		return query.getResultList();
	}


	@Override
	public List<EmployeeIncrementEntity> findEmp(String orderNo) {
		EmployeeIncrementEntity objDept = null;
		String HQL = "FROM EmployeeIncrementEntity as t where incrementOrderNo = '" + orderNo + "' ";
		List<EmployeeIncrementEntity> lstAllowanceDeductionMstEntity = (List<EmployeeIncrementEntity>) entityManager
				.createQuery(HQL).getResultList();
		return lstAllowanceDeductionMstEntity;
	}


	@Override
	public void updateEmpBasicPay(MstEmployeeEntity mstEmployeeEntity) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.update(mstEmployeeEntity);
	}


	@Override
	public Serializable approveAnnualIncrement(EmployeeIncrementEntity employeeIncrementEntity) {
		Session currentSession = entityManager.unwrap(Session.class);
		Serializable saveId = 1;
		currentSession.update(employeeIncrementEntity);
		return (Integer) saveId;
	}


	@Override
	public MstEmployeeEntity findEmpByEmpId(Long employeeId) {
		Session currentSession = entityManager.unwrap(Session.class);
		String hql = "From MstEmployeeEntity as t where t.employeeId = '"+ employeeId+"'";
		Query query = currentSession.createNativeQuery(hql);
		return entityManager.find(MstEmployeeEntity.class, employeeId);
	}



	

}
