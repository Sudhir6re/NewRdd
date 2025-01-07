package com.mahait.gov.in.repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.common.CommonConstants;
import com.mahait.gov.in.entity.BillStatusMstEntity;
import com.mahait.gov.in.entity.CmnLookupMst;
import com.mahait.gov.in.entity.MstBankBranchEntity;
import com.mahait.gov.in.entity.MstBankEntity;
import com.mahait.gov.in.entity.MstCommonEntity;
import com.mahait.gov.in.entity.MstMonthEntity;
import com.mahait.gov.in.entity.MstRoleEntity;
import com.mahait.gov.in.entity.MstYearEntity;
import com.mahait.gov.in.entity.ReligionMstEntity;
import com.mahait.gov.in.model.MstDesnModel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@SuppressWarnings("unchecked")
@Repository
public class CommonHomeMethodsRepoImpl implements CommonHomeMethodsRepo {
	// protected final Log logger = LogFactory.getLog(getClass());
	@PersistenceContext
	EntityManager manager;

	@Override
	public List<Object[]> findRoleLevelMstList() {

		Session currentSession = manager.unwrap(Session.class);
		String hql = "SELECT " + "a.id, " + "a.role_id, " + "a.role_name, " + "a.role_description " + "FROM "
				+ "role_mst a where a.role_id in('1','2','5','6','8','9','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24','25','26','27','29','30','31','32','33','34') "
				+ "ORDER BY " + "a.id ";
		Query query = currentSession.createNativeQuery(hql);
		return (List<Object[]>) query.list();
	}

	@Override
	public List<Object[]> findMenuNameByRoleID(int levelRoleVal) {

		Session currentSession = manager.unwrap(Session.class);
		String hql = "SELECT a.menu_code, " + "a.menu_name_english, " + "a.menu_name_marathi " + "FROM   menu_mst a, "
				+ "menu_role_mapping b " + "WHERE  a.menu_code = b.menu_code " + "AND b.role_Id = '" + levelRoleVal
				+ "' " + "AND b.is_active = '1' " + "GROUP  BY a.menu_code, " + "a.menu_name_english, "
				+ "a.menu_name_marathi " + "ORDER  BY a.menu_code, " + "a.menu_name_english, "
				+ "a.menu_name_marathi; ";
		Query query = currentSession.createNativeQuery(hql);
		return (List<Object[]>) query.list();

	}

	@Override
	public List<Object[]> findSubMenuByRoleID(int levelRoleVal) {

		Session currentSession = manager.unwrap(Session.class);
		String hql = "select sub_menu_id,menu_code,role_id,sub_menu_name_english,sub_menu_name_marathi,	controller_name,link_name  from sub_menu_mst where role_Id='"
				+ levelRoleVal + "' and is_active='" + 1 + "' order by sub_menu_id ";
		Query query = currentSession.createNativeQuery(hql);
		//return (List<Object[]>) query.list();
		
		return query.getResultList(); 

	}

	@Override
	public List<Object[]> findAllMenu() {
		Session currentSession = manager.unwrap(Session.class);
		String hql = "select a.menu_id,a.menu_code, a.menu_name_english,a.menu_name_marathi,a.is_active from menu_mst a order by a.menu_id";
		Query query = currentSession.createNativeQuery(hql);
		return (List<Object[]>) query.list();

	}

	@Override
	public List<Object[]> findAllRole() {
		Session currentSession = manager.unwrap(Session.class);
		String hql = "select a.role_id as id, a.role_id,a.role_name, a.role_description,a.is_active from role_mst a order by a.role_id";
		Query query = currentSession.createNativeQuery(hql);
		return (List<Object[]>) query.list();

	}

	@Override
	public List<Object[]> findAllSubMenu() {
		Session currentSession = manager.unwrap(Session.class);
		String hql = "select a.sub_menu_id, d.role_name, b.menu_name_english, a.sub_menu_name_english,a.sub_menu_name_marathi,a.controller_name,a.link_name,a.is_active, "
				+ "b.menu_code as menu_code, d.role_id  " + "FROM sub_menu_mst a, " + "menu_mst b, "
				+ "menu_role_mapping c, " + "role_mst d " + "WHERE a.menu_code  = b.menu_code AND "
				+ "a.role_id = c.role_id AND " + "a.role_id = d.role_id AND " + "b.menu_code = c.menu_code AND "
				+ "c.role_id = d.role_id AND  a.is_active='1' " + "ORDER BY a.sub_menu_id, d.role_name ";
		Query query = currentSession.createNativeQuery(hql);
		return (List<Object[]>) query.list();
	}

	@Override
	public List<Object[]> findAllMenuRoleMapping() {
		Session currentSession = manager.unwrap(Session.class);
		String hql = "select c.menu_map_id,a.menu_name_english,a.menu_name_marathi,b.role_name,c.is_active, a.menu_code as menu_code, b.role_id FROM "
				+ "menu_mst a , role_mst b, menu_role_mapping c "
				+ "WHERE a.menu_code = c.menu_code AND b.role_id = c.role_id ORDER BY c.menu_map_id";
		Query query = currentSession.createNativeQuery(hql);
		return (List<Object[]>) query.list();
	}

	@Override
	public MstRoleEntity findMstRoleId(int roleId) {
		MstRoleEntity objDept = null;
		Session currentSession = manager.unwrap(Session.class);
		objDept = currentSession.get(MstRoleEntity.class, roleId);
		return objDept;
	}

	@Override
	public void updateMstRoleStatus(MstRoleEntity objDeptForReject) {
		Session currentSession = manager.unwrap(Session.class);
		currentSession.update(objDeptForReject);

	}

	@Override
	public MstRoleEntity findroleById(Integer roleId) {
		// TODO Auto-generated method stub

		MstRoleEntity objrole = null;
		Session currentSession = manager.unwrap(Session.class);
		objrole = currentSession.get(MstRoleEntity.class, roleId);
		return objrole;

	}

	@Override
	public void updaterole(MstRoleEntity objrole) {
		// TODO Auto-generated method stub
		Session currentSession = manager.unwrap(Session.class);
		currentSession.update(objrole);

	}

	@Override
	public int saveMstRole(MstRoleEntity mstRoleEntity) {
		Session currentSession = manager.unwrap(Session.class);
		return (int) currentSession.save(mstRoleEntity);
	}

	@Override
	public List<MstCommonEntity> findCommonMstByCommonCode(String commoncodeStatus) {
		String HQL = "FROM MstCommonEntity as t  WHERE t.commonCode='" + commoncodeStatus + "' ORDER BY t.commonId ";
		return (List<MstCommonEntity>) manager.createQuery(HQL).getResultList();
	}

	@Override
	public List<MstBankEntity> findBankName() {
		String HQL = "FROM MstBankEntity as t";
		return (List<MstBankEntity>) manager.createQuery(HQL).getResultList();
	}

	@Override
	public List<Object[]> findDesignation() {
		Session currentSession = manager.unwrap(Session.class);
		String hql = "SELECT a.designation_id,\r\n" + "a.designation_code,\r\n" + "a.designation_name, \r\n"
				+ "a.designation_short_name, \r\n" + "a.is_active,b.cadre_name,a.cadre_code  \r\n"
				+ "FROM   designation_mst a left join cadre_mst b on a.cadre_code = b.cadre_code  ";
		Query query = currentSession.createNativeQuery(hql);
		return (List<Object[]>) query.list();
	}

	@Override
	public List<Object[]> lstGetAllDistrict() {
		Session currentSession = manager.unwrap(Session.class);
		String hql = "SELECT DISTRICT_ID,DISTRICT_NAME,DISTRICT_CODE FROM CMN_DISTRICT_MST where lang_id = 1";

		Query query = currentSession.createNativeQuery(hql);
		return (List<Object[]>) query.list();
	}

	@Override
	public List<Object[]> lstGetAllTaluka() {
		Session currentSession = manager.unwrap(Session.class);
		String hql = "SELECT TALUKA_ID,TALUKA_NAME,TALUKA_CODE FROM CMN_TALUKA_MST";

		Query query = currentSession.createNativeQuery(hql);
		return (List<Object[]>) query.list();
	}

	@Override
	public List<Object[]> lstGetAllVillage() {
		Session currentSession = manager.unwrap(Session.class);
		String hql = "SELECT VILLAGE_ID,VILLAGE_NAME,VILLAGE_CODE FROM CMN_VILLAGE_MST";

		Query query = currentSession.createNativeQuery(hql);
		return (List<Object[]>) query.list();
	}

	@Override
	public List<Object[]> lstGetAllCity() {
		Session currentSession = manager.unwrap(Session.class);
		String hql = "SELECT CITY_ID, CITY_NAME,CITY_CODE,CITY_CLASS FROM CMN_CITY_MST";

		Query query = currentSession.createNativeQuery(hql);
		return (List<Object[]>) query.list();
	}

	@Override
	public List<Object[]> findAllBankBranchList(int bankCode) {
		Session currentSession = manager.unwrap(Session.class);
		String hql = "select * from bank_branch_mst where bank_code = " + bankCode + " order by bank_branch_name asc";
		Query query = currentSession.createNativeQuery(hql);
		return query.list();
	}

	@Override
	public Object getIfscCodeByBranchId(int branchId) {
		String HQL = "FROM MstBankBranchEntity as t where t.bankBranchId=" + branchId;
		return (List<MstBankBranchEntity>) manager.createQuery(HQL).getResultList();
	}

	@Override
	public List<Object[]> getBankBranch(String bankId) {
		Session hibSession = manager.unwrap(Session.class);
		Query query = hibSession.createNativeQuery(
				"select bank_branch_id,bank_branch_name,bank_branch_code from bank_branch_mst where bank_code="
						+ bankId);
		List<Object[]> lstbankbranchdata = query.list();
		return lstbankbranchdata;
	}

	@Override
	public List<Object[]> retriveUserdetails(Long userId) {
		Session currentSession = manager.unwrap(Session.class);
		String hql = "select a.ddo_code,a.location_code,a.post_id \r\n"
				+ " from org_ddo_mst a inner join org_post_mst b on a.location_code=b.location_code \r\n"
				+ " inner join org_user_mst c on c.ddo_code=a.ddo_code  where c.user_id=" + userId;
		Query query = currentSession.createNativeQuery(hql);
		return (List<Object[]>) query.list();
	}

	@Override
	public List<ReligionMstEntity> fetchAllReligions() {
		// TODO Auto-generated method stub
		String HQL = "FROM ReligionMstEntity as t ORDER BY t.religionId ";
		return (List<ReligionMstEntity>) manager.createQuery(HQL).getResultList();
	}

	@Override
	public List<MstDesnModel> findDesignation(String userName) {
		Session currentSession = manager.unwrap(Session.class);
		String hql = "select * from DESIGNATION_MST";
		Query query = currentSession.createNativeQuery(hql);
		return (List<MstDesnModel>) query.list();
	}


	@Override
	public List<MstMonthEntity> lstGetAllMonths() {
		String HQL = "FROM MstMonthEntity as t ORDER BY t.monthId";
		return (List<MstMonthEntity>) manager.createQuery(HQL).getResultList();
	}

	@Override
	public List<MstYearEntity> lstGetAllYears() {
		String HQL = "FROM MstYearEntity as t ORDER BY t.yearEnglish desc ";
		return (List<MstYearEntity>) manager.createQuery(HQL).getResultList();

	}
	@Override
	public Date findbillCreateDate(Long billNumber) {
		Session currentSession = manager.unwrap(Session.class);
		List list = new ArrayList();
		Date rtnStr = null;
		StringBuffer query = new StringBuffer();
		query.append("select created_date from paybill_generation_trn  where    paybill_generation_trn_id  ='"+billNumber+"' limit 1 ");
		Query hsqlQuery = currentSession.createNativeQuery(query.toString());
		list = hsqlQuery.list();
		if (list != null && list.size() > 0)
			rtnStr = (Date) list.get(0);
		return rtnStr;
	}
	@Override
	public List<Object[]> findDetailsBillNumber(Long billNumber) {
		Session currentSession = manager.unwrap(Session.class);
		String HQL = "select paybill_month,paybill_year from paybill_generation_trn  where paybill_generation_trn_id = '"+billNumber+"'";
		Query query = currentSession.createNativeQuery(HQL);
		return query.list();
	}

	@Override
	public List<Object[]> findyearinfo(BigInteger yearcurr) {
		Session currentSession = manager.unwrap(Session.class);
		String HQL = "select * from year_mst  where year_id  = '" + yearcurr + "' ";
		Query query = currentSession.createNativeQuery(HQL);
		return query.list();
	}
	
	@Override
	public List<Object[]> findmonthinfo(BigInteger month) {
		Session currentSession = manager.unwrap(Session.class);
		String HQL = "select * from month_mst     where month_id   ='" + month + "' ";
		Query query = currentSession.createNativeQuery(HQL);
		return query.list();
	}
	
	@Override
	public String getOffice(String userName) {


		Session currentSession = manager.unwrap(Session.class);
		List list = new ArrayList();
		String rtnStr = null;
		StringBuffer query = new StringBuffer();
		query.append("select off_name from mst_dcps_ddo_office where ddo_code  ='" + userName + "' ");
		Query hsqlQuery = currentSession.createNativeQuery(query.toString());
		list = hsqlQuery.list();

		if (list != null && list.size() > 0)
			rtnStr = list.get(0).toString();

		return rtnStr;

	}
	
	@Override
	public List<Object[]> findLookUpNameDesc(String commoncodeSalutations) {
		// TODO Auto-generated method stub
	Session currentSession = manager.unwrap(Session.class);
		if(commoncodeSalutations == "AccountMaintainedByForDCPSEmp")
		{
		String hql = "SELECT O1.LOOKUP_ID,O1.lookup_name,O1.lookup_desc FROM CMN_LOOKUP_MST O1, CMN_LOOKUP_MST O2 WHERE O1.PARENT_LOOKUP_ID = O2.LOOKUP_ID \r\n"
				+ " AND O2.LOOKUP_NAME = '"+commoncodeSalutations+"' and O1.lookup_id in (700179,700343,10001198172,700344,10001198187) ORDER BY O1.ORDER_NO desc,O1.LOOKUP_ID";
		Query query = currentSession.createNativeQuery(hql);
		return (List<Object[]>) query.list();
		}else
		{
			String hql = "SELECT O1.LOOKUP_ID,O1.lookup_name,O1.lookup_desc FROM CMN_LOOKUP_MST O1, CMN_LOOKUP_MST O2 WHERE O1.PARENT_LOOKUP_ID = O2.LOOKUP_ID \r\n"
					+ " AND O2.LOOKUP_NAME = '"+commoncodeSalutations+"' ORDER BY O1.ORDER_NO desc,O1.LOOKUP_ID";
			Query query = currentSession.createNativeQuery(hql);
			return (List<Object[]>) query.list();
		}
		
		
	}
			
			

	@Override
	public List<BillStatusMstEntity> lstGetAllBillStatusForConsolidatePaybill() {
		String HQL = "FROM BillStatusMstEntity as t  WHERE t.billStatusCode in (5,6,13) and t.isActive = 'Y' ORDER BY t.billStatusId";
		return (List<BillStatusMstEntity>) manager.createQuery(HQL).getResultList();
	}

	@Override
	public List<Object[]> getBillsForConsolidation(String billStatus, Integer roleId, String userName, int yearName,
			int monthName) {
		Session currentSession = manager.unwrap(Session.class);
		String HQL = "select a.paybill_generation_trn_id,ddd.bill_description,dd.scheme_code,dd.scheme_name,a.bill_gross_amt,a.bill_net_amount,a.is_active,a.ddo_code,a.no_of_employee from paybill_generation_trn a inner join \r\n"
				+ "scheme_billgroup_mpg b on a.scheme_billgroup_id = b.bill_group_id \r\n"
				+ "inner join ddo_map_rlt c on b.ddo_map_id = c.ddo_map_id \r\n"
				+ "inner join scheme_mst dd on dd.scheme_id = b.scheme_id \r\n"
				+ "inner join bill_group_mst ddd on b.bill_group_id = ddd.bill_group_id inner join ddo_reg_mst cccc on a.ddo_code = cccc.ddo_code and cccc.ddo_reg_id = ddo_code_user_id1  where a.ddo_code IN (select aa.ddo_code from ddo_reg_mst a inner join\r\n"
				+ "ddo_map_rlt b on  a.ddo_reg_id= b.ddo_code_user_id2\r\n"
				+ "inner join ddo_reg_mst aa on aa.ddo_reg_id = b.ddo_code_user_id1\r\n" + "where a.level_hierarchy = '"
				+ roleId + "' and a.ddo_code = '" + userName + "') " + " and a.is_active in (6,10, 11,12,14) "
				+ " and a.paybill_month=" + monthName + " and a.paybill_year =" // and dd.scheme_code='" + schemeCode +
																				// "'
				+ yearName + " order by paybill_generation_trn_id desc";

		Query query = currentSession.createNativeQuery(HQL);
		return query.list();
	}

	@Override
	public String findCodeSeq(String deptAllowDedCode, String deptAllowDedMst) {
		Session currentSession = manager.unwrap(Session.class);
		String HQL = "select max(" + deptAllowDedCode + ")+1 codeseq from " + deptAllowDedMst;
		// logger.info(">>>"+HQL);
		String strclmncodeseq = null;
		Query query = currentSession.createNativeQuery(HQL);
		if (query.list().get(0) != null)
			strclmncodeseq = query.list().get(0).toString();
		else
			strclmncodeseq = "1";
		return strclmncodeseq;
	}

	@Override
	public List<Object[]> getCityClassByCity(String city) {
		Session currentSession = manager.unwrap(Session.class);
		String HQL = "select * from cmn_city_mst where city_code  ='" + city + "' ";
		Query query = currentSession.createNativeQuery(HQL);
		return query.list();
	}

	@Override
	public String findbillGrpname(Long billNumber) {
		Session currentSession = manager.unwrap(Session.class);
		List list = new ArrayList();
		String rtnStr = null;
		StringBuffer query = new StringBuffer();
		query.append("select description from mst_dcps_bill_group where bill_group_id in (select scheme_billgroup_id from paybill_generation_trn " + 
				" where paybill_generation_trn_id='"+billNumber+"') ");
		Query hsqlQuery = currentSession.createNativeQuery(query.toString());
		list = hsqlQuery.list();

		if (list != null && list.size() > 0)
			rtnStr = list.get(0).toString();
		return rtnStr;
	}

	@Override
	public List<MstBankBranchEntity> findbankBranch() {
		// TODO Auto-generated method stub
		String HQL = "FROM MstBankBranchEntity as t";
		return (List<MstBankBranchEntity>) manager.createQuery(HQL).getResultList();
	}

	@Override
	public List<CmnLookupMst> getLookupValues(String lookupName, int english) {
		Session currentSession = manager.unwrap(Session.class);
		Query lQuery = currentSession.createQuery(CommonConstants.LookUpQuery.GET_LOOK_UP_VALUES);
		lQuery.setParameter("lookupName", lookupName);
		lQuery.setParameter("langId", english);
		
		//lQuery.setString("lookupName", lookupName);
		//lQuery.setLong("langId", english);
		lQuery.setCacheable(true).setCacheRegion("ecache_lookup");
		return lQuery.list();
	}

}
