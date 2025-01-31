package com.mahait.gov.in.repository;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.PayBillViewApprDelBillModel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

///import com.mahait.gov.in.entity.DcpsCalculationEntity;

@Repository
public class PayBillViewApprDelBillRepoImpl implements PayBillViewApprDelBillRepo {
	// protected final Log logger = LogFactory.getLog(getClass());

	@PersistenceContext
	EntityManager entityManager;

	/*
	 * @Autowired SessionFactory sessionFactory;
	 */

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findAlllstGenerateBillDetailsAgainstDDO(String ddoCode, int month) {

		Date d = new Date();
		int currYear = d.getYear();
		currYear = currYear + 1900;
		currYear = currYear - 2000;
		
		currYear=currYear+1;

		Session currentSession = entityManager.unwrap(Session.class);
		String HQL = "select a.paybill_generation_trn_id,b.description,b.scheme_code,b.scheme_name,a.bill_gross_amt,\r\n"
				+ "a.bill_net_amount,a.is_active,a.no_of_employee,a.auth_no from paybill_generation_trn a inner join\r\n"
				+ "mst_dcps_bill_group b on a.scheme_billgroup_id = b.bill_group_id \r\n"
				+ "inner join rlt_zp_ddo_map c on b.ddo_code = c.zp_ddo_code \r\n" + "where\r\n" + "a.ddo_code = '"
				+ ddoCode + "' and a.is_active <>8 and a.paybill_month = " + month + " \r\n" + "and a.paybill_year="
				+ currYear + " order by paybill_generation_trn_id desc";

		Query query = currentSession.createNativeQuery(HQL);

		/*
		 * String HQL11 =
		 * "FROM MpgSchemeBillGroupEntity as t where t.isActive = '1' and t.ddoMapId = "
		 * +query.uniqueResult()+" ORDER BY t.billDescription ";
		 */
		return query.list();
	}

	@Override
	public List<String> findDdoNameAgainstGivenDdo(String ddoCode, int roleId) {

		Session currentSession = entityManager.unwrap(Session.class);
		String HQL = null;
		if (roleId == 2) {

			HQL = "select CONCAT(b.zp_ddo_code, '_AST') from org_ddo_mst a inner join rlt_zp_ddo_map b on a.ddo_code=b.rept_ddo_code\r\n"
					+ "		where a.ddo_code = '" + ddoCode + "'";

		} else if (roleId == 1) {
			HQL = "select a.ddo_code from org_ddo_mst a inner join rlt_zp_ddo_map b on a.ddo_code=b.rept_ddo_code\r\n"
					+ "		where a.ddo_code = '" + ddoCode + "'";

		}

		Query query = currentSession.createNativeQuery(HQL);

		/*
		 * String HQL11 =
		 * "FROM MpgSchemeBillGroupEntity as t where t.isActive = '1' and t.ddoMapId = "
		 * +query.uniqueResult()+" ORDER BY t.billDescription ";
		 */
		
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findAlllstGenerateBillDetailsAgainstDDO2(List<String> ddoCode) {

		Date d = new Date();
		int currYear = d.getYear();
		currYear = currYear + 1900;
		currYear = currYear - 2000;

		int month = d.getMonth() + 1;

		Session currentSession = entityManager.unwrap(Session.class);
		String HQL = "select a.paybill_generation_trn_id,b.description,b.scheme_code,b.scheme_name,a.bill_gross_amt,\r\n"
				+ "a.bill_net_amount,a.is_active,a.no_of_employee,a.auth_no from paybill_generation_trn a inner join \r\n"
				+ "mst_dcps_bill_group b on a.scheme_billgroup_id = b.bill_group_id \r\n"
				+ "inner join rlt_zp_ddo_map c on b.ddo_code = c.zp_ddo_code \r\n"
				+ "inner join org_ddo_mst cccc on c.zp_ddo_code = cccc.ddo_code where a.ddo_code in :ddoCode and a.is_active = 2 and a.paybill_month = '"
				+ month + "'" + " and a.paybill_year=" + currYear + " order by paybill_generation_trn_id desc ";

		Query query = currentSession.createNativeQuery(HQL);
		System.out.println("findAlllstGenerateBillDetailsAgainstDDO2----" + HQL);
		query.setParameter("ddoCode", ddoCode);
		return query.list();
	}

	@Override
	public List<Object[]> findAllPayBillsForConsolidatedAgainstDDO(String ddoCode, int roleId) {
		Session currentSession = entityManager.unwrap(Session.class);
		String HQL = "select a.paybill_generation_trn_id,ddd.bill_description,dd.scheme_code,dd.scheme_name,a.bill_gross_amt,a.bill_net_amount,a.is_active from paybill_generation_trn a inner join \r\n"
				+ "scheme_billgroup_mpg b on a.scheme_billgroup_id = b.bill_group_id \r\n"
				+ "inner join ddo_map_rlt c on b.ddo_map_id = c.ddo_map_id \r\n"
				+ "inner join scheme_mst dd on dd.scheme_id = b.scheme_id \r\n"
				+ "inner join bill_group_mst ddd on b.bill_group_id = ddd.bill_group_id where a.ddo_code IN (select aa.ddo_code from ddo_reg_mst a inner join\r\n"
				+ "ddo_map_rlt b on  a.ddo_reg_id= b.ddo_code_user_id2\r\n"
				+ "inner join ddo_reg_mst aa on aa.ddo_reg_id = b.ddo_code_user_id1\r\n" + "where a.level_hierarchy = '"
				+ roleId + "' and a.ddo_code = '" + ddoCode
				+ "') and a.is_active =6 order by paybill_generation_trn_id desc";
		Query query = currentSession.createNativeQuery(HQL);

		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findPayBillByBillNumber(String billNumber, int paybillMonth, int paybillYear, int roleId) {
		Session currentSession = entityManager.unwrap(Session.class);
		String HQL = "";
		if (roleId == 3) {
			HQL = "select a.paybill_generation_trn_id,b.description,b.scheme_code,b.scheme_name,a.bill_gross_amt,\r\n"
					+ "a.bill_net_amount,a.is_active,a.no_of_employee,a.auth_no from paybill_generation_trn a inner join\r\n"
					+ "mst_dcps_bill_group b on a.scheme_billgroup_id = b.bill_group_id \r\n"
					+ "inner join rlt_zp_ddo_map c on b.ddo_code = c.zp_ddo_code \r\n"
					+ "inner join org_ddo_mst cccc on c.zp_ddo_code = cccc.ddo_code \r\n"
					+ "where a.is_active <>8 and \r\n" + "a.paybill_month = " + paybillMonth + " and a.paybill_year = "
					+ paybillYear + " and \r\n" + "a.scheme_billgroup_id = '" + billNumber
					+ "' order by paybill_generation_trn_id desc";
		} else {

			HQL = "select a.paybill_generation_trn_id,b.description,b.scheme_code,b.scheme_name,a.bill_gross_amt,\r\n"
					+ "a.bill_net_amount,a.is_active,a.no_of_employee,a.auth_no from paybill_generation_trn a inner join \r\n"
					+ "mst_dcps_bill_group b on a.scheme_billgroup_id = b.bill_group_id \r\n"
					+ "inner join rlt_zp_ddo_map c on b.ddo_code = c.zp_ddo_code \r\n"
					+ "inner join org_ddo_mst cccc on c.zp_ddo_code = cccc.ddo_code  \r\n"
					+ "where a.is_active in(1,2) and a.paybill_month = " + paybillMonth + " and a.paybill_year = "
					+ paybillYear + " and \r\n" + "a.scheme_billgroup_id = '" + billNumber
					+ "' order by paybill_generation_trn_id desc";

		}
		System.out.println(HQL);
		Query query = currentSession.createNativeQuery(HQL);
		/*
		 * String HQL11 =
		 * "FROM MpgSchemeBillGroupEntity as t where t.isActive = '1' and t.ddoMapId = "
		 * +query.uniqueResult()+" ORDER BY t.billDescription ";
		 */
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findPayBillByMonthYear(int paybillMonth, int paybillYear, String ddoCode, int roleId) {
		Session currentSession = entityManager.unwrap(Session.class);
		String HQL;

		if (roleId == 3) {
			HQL = "SELECT a.paybill_generation_trn_id, b.description, b.scheme_code, b.scheme_name, "
					+ "a.bill_gross_amt, a.bill_net_amount, a.is_active, a.no_of_employee, a.auth_no "
					+ "FROM paybill_generation_trn a "
					+ "INNER JOIN mst_dcps_bill_group b ON a.scheme_billgroup_id = b.bill_group_id "
					+ "INNER JOIN rlt_zp_ddo_map c ON b.ddo_code = c.zp_ddo_code "
					+ "INNER JOIN org_ddo_mst cccc ON c.zp_ddo_code = cccc.ddo_code "
					+ "WHERE a.is_active <> 8 AND a.paybill_month = :paybillMonth "
					+ "AND a.paybill_year = :paybillYear AND a.ddo_code = :ddoCode "
					+ "ORDER BY paybill_generation_trn_id DESC";
		} else {
			HQL = "SELECT a.paybill_generation_trn_id, b.description, b.scheme_code, b.scheme_name, "
					+ "a.bill_gross_amt, a.bill_net_amount, a.is_active, a.no_of_employee, a.auth_no "
					+ "FROM paybill_generation_trn a "
					+ "INNER JOIN mst_dcps_bill_group b ON a.scheme_billgroup_id = b.bill_group_id "
					+ "INNER JOIN rlt_zp_ddo_map c ON b.ddo_code = c.zp_ddo_code "
					+ "INNER JOIN org_ddo_mst cccc ON c.rept_ddo_code = cccc.ddo_code "
					+ "WHERE a.is_active IN (1, 2) AND a.paybill_month = :paybillMonth "
					+ "AND a.paybill_year = :paybillYear AND c.rept_ddo_code = :ddoCode "
					+ "ORDER BY paybill_generation_trn_id DESC";
		}

		Query query = currentSession.createNativeQuery(HQL).setParameter("paybillMonth", paybillMonth)
				.setParameter("paybillYear", paybillYear).setParameter("ddoCode", ddoCode);

		System.out.println(HQL);
		return query.list();
	}

	@Override
	public List<Object[]> findPayBillByBillNumber(PayBillViewApprDelBillModel payBillViewApprDelBillModel,
			OrgUserMst messages) {
		
		if(payBillViewApprDelBillModel.getAction()==null ) {
			Date d = new Date();
			int currYear = d.getYear();
			currYear = currYear + 1900;
			currYear = currYear - 2000;
			
			
			int month = d.getMonth() + 1;
			payBillViewApprDelBillModel.setYearName(String.valueOf((currYear+1)));
			payBillViewApprDelBillModel.setMonthName(String.valueOf(month));
		}
		
		Session currentSession = entityManager.unwrap(Session.class);
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT a.paybill_generation_trn_id, b.description, b.scheme_code, b.scheme_name,");
		sb.append("a.bill_gross_amt, a.bill_net_amount, a.is_active, a.no_of_employee, a.auth_no ");
		
		if (messages.getMstRoleEntity().getRoleId() == 3) {

			sb.append(" FROM PAYBILL_GENERATION_TRN a ");
			sb.append(" INNER JOIN  MST_DCPS_BILL_GROUP  b ON a.scheme_billgroup_id = b.bill_group_id ");
			sb.append(" INNER JOIN RLT_ZP_DDO_MAP  c ON b.ddo_code = c.zp_ddo_code ");
			sb.append(" INNER JOIN ORG_DDO_MST cccc ON c.zp_ddo_code = cccc.ddo_code ");
			sb.append(" WHERE a.is_active <> 8 AND a.paybill_month = :paybillMonth ");
			
			if(payBillViewApprDelBillModel.getYearName()!=null  && !payBillViewApprDelBillModel.getYearName().equals("0"))
			sb.append(" AND a.paybill_year = :paybillYear");
			
			sb.append(" AND a.ddo_code = :ddoCode ");
			if(payBillViewApprDelBillModel.getBillNumber()!=null && !payBillViewApprDelBillModel.getBillNumber().equals("0"))
				sb.append(" AND a.scheme_billgroup_id= :billNumber ");
			
			sb.append(" ORDER BY paybill_generation_trn_id DESC");
		} else {
			
			sb.append(" FROM PAYBILL_GENERATION_TRN a ");
			sb.append(" INNER JOIN MST_DCPS_BILL_GROUP b ON a.scheme_billgroup_id = b.bill_group_id ");
			sb.append(" INNER JOIN RLT_ZP_DDO_MAP c ON b.ddo_code = c.zp_ddo_code ");
			sb.append(" INNER JOIN ORG_DDO_MST cccc ON c.rept_ddo_code = cccc.ddo_code ");
			sb.append(" WHERE a.is_active IN (1, 2) AND a.paybill_month = :paybillMonth ");
			sb.append(" AND a.paybill_year = :paybillYear AND c.rept_ddo_code = :ddoCode ");
		
			if(payBillViewApprDelBillModel.getYearName()!=null  && !payBillViewApprDelBillModel.getYearName().equals("0"))
				sb.append(" AND a.paybill_year = :paybillYear");
			
			if(payBillViewApprDelBillModel.getBillNumber()!=null && !payBillViewApprDelBillModel.getBillNumber().equals("0")){
				sb.append(" AND a.scheme_billgroup_id= :billNumber ");
			}
			sb.append(" ORDER BY paybill_generation_trn_id DESC");
		}

		Query query = currentSession.createNativeQuery(sb.toString())
	            .setParameter("paybillMonth", Integer.parseInt(payBillViewApprDelBillModel.getMonthName()))
	            .setParameter("ddoCode", messages.getDdoCode());

	    if (payBillViewApprDelBillModel.getYearName() != null && !payBillViewApprDelBillModel.getYearName().equals("0")) {
	        query.setParameter("paybillYear",  Integer.parseInt(payBillViewApprDelBillModel.getYearName()));
	    }

	    if (payBillViewApprDelBillModel.getBillNumber() != null && !payBillViewApprDelBillModel.getBillNumber().equals("0")) {
	        query.setParameter("billNumber", Long.valueOf(payBillViewApprDelBillModel.getBillNumber()));
	    }

		System.out.println(sb.toString());
		return query.list();
	}

}
