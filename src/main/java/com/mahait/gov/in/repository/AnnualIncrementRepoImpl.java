package com.mahait.gov.in.repository;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.entity.EmployeeIncrementEntity;
import com.mahait.gov.in.entity.MstEmployeeEntity;
import com.mahait.gov.in.entity.MstPayCommissionEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

@Repository
public class AnnualIncrementRepoImpl implements AnnualIncrementRepo {

	// protected final Log logger = LogFactory.getLog(getClass());
	@PersistenceContext
	EntityManager manager;

	@SuppressWarnings("unchecked")
	@Override
	public List<MstPayCommissionEntity> findAppPayCommssion() {
		Session currentSession = manager.unwrap(Session.class);
		String HQL = "FROM MstPayCommissionEntity";
		return (List<MstPayCommissionEntity>) currentSession.createQuery(HQL, MstPayCommissionEntity.class).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findAllEmpForDue(String id, Long payCommision,String ddoCode) {
		Session currentSession = manager.unwrap(Session.class);
		String hql = "select DISTINCT a.employee_id,a.sevaarth_id,a.employee_full_name_en,case when a.pay_commission_code=8 then \r\n" + 
				"a.seven_pc_basic else a.basic_pay end,a.seven_pc_level from employee_mst a\r\n" + 
				"inner join mst_dcps_bill_group b on a.billgroup_id = b.bill_group_id\r\n" + 
				"left join employee_increment d on d.sevaarth_id = a.sevaarth_id\r\n" + 
				"where b.bill_group_id ='"+id+"' and a.pay_commission_code='"+payCommision+"'\r\n" + 
				"and (CURRENT_DATE-d.effective_from_date>=335 or effective_from_date is null) and a.ddo_code= '"+ddoCode+"' \r\n" + 
				"and (d.effective_from_date <= CURRENT_DATE  or d.effective_from_date IS NULL) and a.emp_service_end_date >= current_date";
					//	+ " AND (d.sevaarth_id NOT IN (SELECT sevaarth_id    FROM employee_increment  WHERE effective_from_date <= '2023-07-01' AND sevaarth_id != ''  AND sevaarth_id != '0') OR d.sevaarth_id IS NULL) and a.emp_service_end_date >= current_date";
		Query query = currentSession.createNativeQuery(hql);
		System.out.println("findAllEmpForDue------" + hql);
		return query.list();
	}

	@Override
	public Long saveAnnualIncrement(EmployeeIncrementEntity employeeIncrementEntity) {
		Session currentSession = manager.unwrap(Session.class);
		Serializable saveId = (Serializable) currentSession.save(employeeIncrementEntity);
		return (Long) saveId;
	}

	@Override
	public List<Object[]> findNextMatrix(Integer empIds, Integer basicPay, Integer level) {
		Session currentSession = manager.unwrap(Session.class);
		String hql = "select s_" + level
				+ ",state_matrix_7pc_id from state_matrix_7pc_mst where state_matrix_7pc_id in(select state_matrix_7pc_id+1 from state_matrix_7pc_mst where   s_"
				+ level + " =  '" + basicPay + "' )";
		System.out.println("findNextMatrix------" + hql);
		Query query = currentSession.createNativeQuery(hql);
		return query.list();
	}

	@Override
	public List<Object[]> getEmpDataForIncrementApproval(String userName) {
		Session currentSession = manager.unwrap(Session.class);
		String hql = "select distinct increment_order_date,increment_order_no,d.office_name,c.ddo_code_level1 from employee_increment a \r\n"
				+ "inner join employee_mst b on b.sevaarth_id = a.sevaarth_id\r\n"
				+ "inner join ddo_map_rlt c on c.ddo_code_level1 = b.ddo_code\r\n"
				+ "inner join ddo_reg_mst d on d.ddo_code = c.ddo_code_level2 where c.ddo_code_level2='" + userName
				+ "' and a.is_Active = '1' and increment_order_no <>'0' ";
		System.out.println("getEmpDataForIncrementApproval------" + hql);
		Query query = currentSession.createNativeQuery(hql);
		return query.list();
	}

	@Override
	public List<Object[]> lstEmpforMTR21(String orderNo,String orderDate,String ddoCode) {
		Session currentSession = manager.unwrap(Session.class);
		
		String hql=null;
		
		hql = "select distinct b.employee_full_name_en,c.post_short_name,a.current_basic_pay,b.sevaarth_id,a.effective_from_date,\r\n" + 
				"a.to_increment_date,a.increment_basic_pay_sal from employee_increment a \r\n"
				+ "inner join employee_mst b on a.sevaarth_id = b.sevaarth_id \r\n"
				+ "inner join post_details_rlt c on b.post_detail_id = c.post_details_id  where increment_order_no = '" + orderNo + "' "
						+ " and increment_order_date = '"+orderDate+"' and b.ddo_code='"+ddoCode+"'";
		
		
		System.out.println("lstEmpforMTR21------" + hql);
		Query query = currentSession.createNativeQuery(hql);
		return query.list();
	}

	@Override
	public String officeName(String userName) {
		Session currentSession = manager.unwrap(Session.class);
		String hql = "select ddo_name from ddo_reg_mst  where ddo_code  = '" + userName + "'";
		Query query = currentSession.createNativeQuery(hql);
		return query.list().get(0).toString();
	}

	@Override
	public List<EmployeeIncrementEntity> findEmp(String orderNo) {
		EmployeeIncrementEntity objDept = null;
		String HQL = "FROM EmployeeIncrementEntity as t where incrementOrderNo = '" + orderNo + "' ";
		List<EmployeeIncrementEntity> lstAllowanceDeductionMstEntity = (List<EmployeeIncrementEntity>) manager
				.createQuery(HQL).getResultList();
		return lstAllowanceDeductionMstEntity;
	}

	@Override
	public Serializable approveAnnualIncrement(EmployeeIncrementEntity obj) {
		Session currentSession = manager.unwrap(Session.class);
		Serializable saveId = 1;
		currentSession.update(obj);
		return (Integer) saveId;
	}

	@Override
	public List<Object[]> findOrderNoAlreadyExists(String certificateNumber) {
		Session currentSession = manager.unwrap(Session.class);
		String hql = "select increment_order_no,increment_order_date from employee_increment  where  increment_order_no ='"
				+ certificateNumber + "'";
		System.out.println("findOrderNoAlreadyExists------" + hql);
		Query query = currentSession.createNativeQuery(hql);
		return query.list();
	}

	@Override
	public List<Object[]> getEmpStatus(String userName) {
		Session currentSession = manager.unwrap(Session.class);
		String hql = "select distinct increment_order_no,increment_order_date,is_active from employee_increment where ddo_code ='"
				+ userName + "'  and increment_order_no<>'0' and increment_order_no!=''";
		System.out.println("getEmpStatus------" + hql);
		Query query = currentSession.createNativeQuery(hql);
		return query.list();
	}

	@Override
	public MstEmployeeEntity findEmpByEmpId(Long employeeId) {
		// TODO Auto-generated method stub
		Session currentSession = manager.unwrap(Session.class);
		String hql = "From MstEmployeeEntity as t where t.employeeId = '"+ employeeId+"'";
		Query query = currentSession.createNativeQuery(hql);
		return manager.find(MstEmployeeEntity.class, employeeId);
	}

	@Override
	public void updateEmpBasicPay(MstEmployeeEntity mstEmployeeEntity) {
		// TODO Auto-generated method stub
		Session currentSession = manager.unwrap(Session.class);
		currentSession.update(mstEmployeeEntity);
	}

	@Override
	public EmployeeIncrementEntity getPreIncDtsByempId(int empId1) {
		// TODO Auto-generated method stub
		Session currentSession = manager.unwrap(Session.class);
		EmployeeIncrementEntity employee = null;
		Query query = currentSession
				.createQuery("SELECT e FROM EmployeeIncrementEntity e where e.isActive='2' and e.employeeId=" + empId1
						+ "  order by employee_id desc");
		query.setMaxResults(1);
		try {
			employee = (EmployeeIncrementEntity) query.getSingleResult();
		} catch (NoResultException nre) {
			employee = null;
		}

		return employee;
	}

	@Override
	public void updateEmpIncrementToDate(EmployeeIncrementEntity previousIncrementDtlsObj) {
		// TODO Auto-generated method stub
		Session currentSession = manager.unwrap(Session.class);
		currentSession.update(previousIncrementDtlsObj);
	}

	@Override
	public String oldMatrixId(String sevaarthIds1, String basicPays1) {
		Session currentSession = manager.unwrap(Session.class);
		String level = "select seven_pc_level from employee_mst where sevaarth_id = '" + sevaarthIds1 + "' ";
		Query query1 = currentSession.createNativeQuery(level);
		BigInteger Level = (BigInteger) query1.list().get(0);
		String Query = "select state_matrix_7pc_id from state_matrix_7pc_mst where s_" + Level + " = '" + basicPays1
				+ "'";
		Query query = currentSession.createNativeQuery(Query);
		System.out.println("oldMatrixId------" + Query);
		return query.list().get(0).toString();
	}

	@Override
	public String newMatrixId(String sevaarthIds1, String basicPaysIncrementes1) {
		Session currentSession = manager.unwrap(Session.class);
		String level = "select seven_pc_level from employee_mst where sevaarth_id = '" + sevaarthIds1 + "' ";
		Query query1 = currentSession.createNativeQuery(level);
		BigInteger Level = (BigInteger) query1.list().get(0);
		String Query = "select state_matrix_7pc_id from state_matrix_7pc_mst where s_" + Level + " = '"
				+ basicPaysIncrementes1 + "'";
		Query query = currentSession.createNativeQuery(Query);
		System.out.println("newMatrixId------" + Query);
		return query.list().get(0).toString();
	}

	@Override
	public void updateSevenPcBasicMst(MstEmployeeEntity mstEmployeeEntity) {
		// TODO Auto-generated method stub
		Session currentSession = manager.unwrap(Session.class);
		currentSession.update(mstEmployeeEntity);
	}

	@Override
	public MstEmployeeEntity getSevenPcBasicDetails(String sevaarthIds1) {
		String HQL = "FROM MstEmployeeEntity as a where a.sevaarthId = '"+sevaarthIds1+"' ";
		return (MstEmployeeEntity) manager.createQuery(HQL).getSingleResult();
	}

	@Override
	public EmployeeIncrementEntity findOldAnnualIncrement(Integer employeeId) {
		Session currentSession = manager.unwrap(Session.class);
		EmployeeIncrementEntity employee = null;
		Query query = currentSession
				.createQuery("SELECT e FROM EmployeeIncrementEntity e where e.isActive='3' and e.employeeId=" + employeeId
						+ "  order by employee_id desc");
		query.setMaxResults(1);
		try {
			employee = (EmployeeIncrementEntity) query.getSingleResult();
		} catch (NoResultException nre) {
			employee = null;
		}

		return employee;
	}

	@Override
	public String getEmpCountAgainstOrderNo(String orderNo,String userName) {
		Session currentSession = manager.unwrap(Session.class);
		String level = "select count(distinct employee_full_name_en) from employee_increment a inner join employee_mst b on a.sevaarth_id = b.sevaarth_id  where \r\n" + 
				" increment_order_no ='"+orderNo+"' and b.ddo_code = '"+userName+"'";
		Query query1 = currentSession.createNativeQuery(level);
		System.out.println("getEmpCountAgainstOrderNo------" + query1);
		return query1.list().get(0).toString();
	}

}
