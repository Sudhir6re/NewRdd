package com.mahait.gov.in.service;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mahait.gov.in.entity.CmnLanguageMst;
import com.mahait.gov.in.entity.CmnLocationMst;
import com.mahait.gov.in.entity.CmnLookupMst;
import com.mahait.gov.in.entity.DdoOffice;
import com.mahait.gov.in.entity.HrPayOfficepostMpg;
import com.mahait.gov.in.entity.HrPayOrderHeadMpg;
import com.mahait.gov.in.entity.HrPayOrderHeadPostMpg;
import com.mahait.gov.in.entity.HrPayOrderMst;
import com.mahait.gov.in.entity.HrPayPsrPostMpg;
import com.mahait.gov.in.entity.MstDesignationEntity;
import com.mahait.gov.in.entity.OrgDdoMst;
import com.mahait.gov.in.entity.OrgPostDetailsRlt;
import com.mahait.gov.in.entity.OrgPostMst;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.entity.SubjectPostMpg;
import com.mahait.gov.in.model.PostEntryModel;
import com.mahait.gov.in.model.UserPostCustomVO;
import com.mahait.gov.in.repository.CmnLanguageMstRepository;
import com.mahait.gov.in.repository.CmnLocationMstRepository;
import com.mahait.gov.in.repository.CmnLookupMstRepository;
import com.mahait.gov.in.repository.EntryOfPostsRepo;
import com.mahait.gov.in.repository.OrgPostMstRepository;
import com.mahait.gov.in.repository.OrganizationInstInfoRepo;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class EntryOfPostsServiceImpl implements EntryOfPostsService {

	@Autowired
	EntryOfPostsRepo entryOfPostsRepo;

	@Autowired
	CmnLocationMstRepository cmnLocationMstRepository;

	@Autowired
	CmnLanguageMstRepository cmnLanguageMstRepository;

	@Autowired
	CmnLookupMstRepository cmnLookupMstRepository;

	@Autowired
	OrgPostMstRepository orgPostMstRepository;

	@Autowired
	private OrganizationInstInfoRepo organizationInstInfoRepo;

	@Override
	public List<MstDesignationEntity> getActiveDesig(Long lLngFieldDept) {
		return entryOfPostsRepo.getActiveDesig(lLngFieldDept);
	}

	@Override
	public List getAllBillsFromLocation(Long locId) {
		return entryOfPostsRepo.getAllBillsFromLocation(locId);
	}

	@Override
	public List<OrgDdoMst> getDDOCodeByLoggedInlocId(long locId) {
		return entryOfPostsRepo.getDDOCodeByLoggedInlocId(locId);
	}

	@Override
	public List getAllBranchList(long l) {
		// TODO Auto-generated method stub
		return entryOfPostsRepo.getAllBranchList(l);
	}

	@Override
	public List<HrPayOrderMst> getAllOrderData(long locId, String ddoCode) {
		return entryOfPostsRepo.getAllOrderData(locId, ddoCode);
	}

	@Override
	public List getAllOfficesFromDDO(String ddoCode) {
		return entryOfPostsRepo.getAllOfficesFromDDO(ddoCode);
	}

	@Override
	public List<Object[]> getSubjectList() {
		return entryOfPostsRepo.getSubjectList();
	}

	@Override
	public List getSubOfficesFromDDONew(Long postId) {
		return entryOfPostsRepo.getSubOfficesFromDDONew(postId);
	}

	@Override
	public String districtName(String ddoCode) {
		return entryOfPostsRepo.districtName(ddoCode);
	}

	@Override
	public List allTaluka(String districtID) {
		return entryOfPostsRepo.allTaluka(districtID);
	}

	@Override
	public List getSubDDOsOffc(long loggedInPostId, String talukaId, String ddoSelected) {
		return entryOfPostsRepo.getSubDDOsOffc(loggedInPostId, talukaId, ddoSelected);
	}

	@Override
	public OrgPostMst savePost(OrgPostMst orgPostMst) {
		return entryOfPostsRepo.savePost(orgPostMst);
	}

	@Override
	public Long savePostDetails(OrgPostDetailsRlt orgPostDetailsRlt) {
		return entryOfPostsRepo.savePostDetails(orgPostDetailsRlt);
	}

	@Override
	public Long saveHrPayOfficepostMpg(HrPayOfficepostMpg hrPayOfficepostMpg) {
		return entryOfPostsRepo.savePostDetails(hrPayOfficepostMpg);
	}

	@Override
	public List getSubLocationDDOs(BigInteger loggedInPostId) {
		return entryOfPostsRepo.getSubLocationDDOs(loggedInPostId);
	}

	@Override
	public List getFilterDdoCode(String locationcodeArray) {
		return entryOfPostsRepo.getFilterDdoCode(locationcodeArray);
	}

	@Override
	public List getSubDDOsOffc(BigInteger loggedInPostId, String talukaId, String ddoSelected) {
		return entryOfPostsRepo.getSubDDOsOffc(loggedInPostId, talukaId, ddoSelected);
	}

	@Override
	public void savePostEntry(PostEntryModel postEntryModel, long locId, BigInteger loggedInPostId,
			OrgUserMst messages) {
		long userID = messages.getUserId();

		OrgPostMst orgPostMst = entryOfPostsRepo.findPost(loggedInPostId.longValue());

		CmnLanguageMst cmnLanguageMst = cmnLanguageMstRepository.findByLangId(1l);
		CmnLookupMst lObjCmnLookupMst = cmnLookupMstRepository.findByLookupId(1l);
		CmnLocationMst cmnLocationMst = cmnLocationMstRepository.findByLocId(locId);

		long nextPsr = entryOfPostsRepo.getNextPsrNo();

		long setPostId = loggedInPostId.longValue();
		String ddoCode = postEntryModel.getDdoCode();
		if (!ddoCode.equals("-1") && !ddoCode.equals("") && ddoCode != null) {
			List DDOdtls = entryOfPostsRepo.getDDODtls(ddoCode);
			String strLngFieldDept = "";
			Iterator IT = DDOdtls.iterator();
			Object o[] = null;
			String locIdStr = "";
			String strPostId = "";
			while (IT.hasNext()) {
				o = (Object[]) IT.next();
				locIdStr = o[0].toString();
				strLngFieldDept = o[1].toString();
				strPostId = o[2].toString();

			}
			locId = Long.parseLong(locIdStr);
			setPostId = Long.parseLong(strPostId);
		}
		long officeId = postEntryModel.getOfficeCmb();
		long billId = 0;
		long noOfPost = postEntryModel.getPostNumber();
		long orderId = postEntryModel.getOrderCmb();
		String desgnCode = postEntryModel.getDesignationCmb();

		MstDesignationEntity desgnMst = entryOfPostsRepo.finddesignationByCode(Long.valueOf(desgnCode));

		long desgnId = Long.parseLong(desgnCode);
		Date startDate = postEntryModel.getStartDate();
		Date endDate = postEntryModel.getEndDate();
		Date tempEndDate = postEntryModel.getTempEndDate();
		Date orderDate = postEntryModel.getOrderDate();
		String postType = postEntryModel.getTempPostTypeCmbBox();
		String remarks = postEntryModel.getRemarks();
		// Date oldOrderDate = postEntryModel.getOldOrderDate();
		// String oldOrderCmb = "";
		// Date newOrderDate = "";
		// String newOrderId = "";
		// String tempTypePost = "";
		// String Permenant = "";
		// String subPostTypeTemp = "";
		// String subPostTypePerm = "";
		String subjectSel = postEntryModel.getSubjectCmb();
		long subFieldDeptId = postEntryModel.getCmbSubFieldDept();

		String cmprColumn[] = { "orderId" };
		Object cmprValues[] = { orderId };

		List orderHeadList = entryOfPostsRepo.findHrPayOrderHeadMpg(orderId);

		HrPayOrderHeadMpg orderHeadMpg = new HrPayOrderHeadMpg();
		long orderHeadId = 0;
		if (orderHeadList.size() == 0) {
			orderHeadMpg = new HrPayOrderHeadMpg();
			orderHeadMpg.setOrderHeadId(orderHeadId);
			orderHeadMpg.setOrderId(orderId);
			orderHeadMpg.setSubheadId(null);
			orderHeadMpg.setCreatedDate(new Date());
			orderHeadMpg.setOrgUserMstByCreatedBy(messages);
			orderHeadMpg.setOrgPostMstByCreatedByPost(orgPostMst);
			orderHeadMpg.setTrnCounter(1);
			entryOfPostsRepo.create(orderHeadMpg);
		} else {
			orderHeadMpg = (HrPayOrderHeadMpg) orderHeadList.get(0);
			orderHeadId = orderHeadMpg.getOrderHeadId();
		}
		long postId = 0;
		long postDtlId = 0;
		long psrPostId = 0;
		long orderHeadPostId = 0;
		HrPayOrderMst hrPayOrderMst = null;
		OrgPostMst orgPostMaster = null;
		OrgPostDetailsRlt orgPostDtlRlt = null;
		HrPayPsrPostMpg postPsrMpg = null;
		HrPayOrderHeadPostMpg orderHeadPostmpg = null;

		// CmnLookupMst cmnLookupMst = null;
		String CmnPId = "10001198129";
		String CmnTId = "10001198130";
		String CmnSId = "10001198155";

		if (postType.equals(CmnPId) || postType.equals(CmnSId)) {

			for (int postCount = 1; postCount <= noOfPost; postCount++) {

				orgPostMaster = new OrgPostMst();

				orgPostMaster.setPostId(postId);
				// orgPostMaster.setParentPostId(-1);
				// orgPostMaster.setPostLevelId(1);
				orgPostMaster.setStartDate(new Timestamp(startDate.getTime()));
				if (endDate != null) {
					orgPostMaster.setEndDate(new Timestamp(endDate.getTime()));
				}
				orgPostMaster.setCmnLookupMst(lObjCmnLookupMst);
				orgPostMaster.setDsgnCode(desgnCode);
				orgPostMaster.setActivateFlag(1l);
				orgPostMaster.setCreatedDate(new Timestamp(new Date().getTime()));
				orgPostMaster.setCreatedBy(messages);
				orgPostMaster.setCreatedByPost(orgPostMst);
				orgPostMaster.setLocationCode(String.valueOf(locId));

				// orgPostMaster.setPostTypeLookupId(lObjCmnLookupMst);
				// orgPostMstDaoImpl.create(orgPostMaster);

				orgPostMaster = entryOfPostsRepo.savePost(orgPostMaster);

				SubjectPostMpg subjectPostMpg = new SubjectPostMpg();

				subjectPostMpg.setPostId(postId);
				subjectPostMpg.setSubjectName(subjectSel);

				entryOfPostsRepo.submitSubject(subjectPostMpg);
				postPsrMpg = new HrPayPsrPostMpg();
				postPsrMpg.setPsrPostId(psrPostId);
				postPsrMpg.setPsrId(nextPsr);
				postPsrMpg.setPostId(postId);
				if (billId <= 0)
					postPsrMpg.setBillNo(null);
				else
					postPsrMpg.setBillNo(billId);
				postPsrMpg.setLocId(locId);
				entryOfPostsRepo.save(postPsrMpg);

				orgPostDtlRlt = new OrgPostDetailsRlt();
				orgPostDtlRlt.setPostDetailId(postDtlId);
				orgPostDtlRlt.setOrgPostMst(orgPostMaster);
				orgPostDtlRlt.setCreatedByPost(orgPostMst);
				orgPostDtlRlt.setCreatedBy(messages);
				orgPostDtlRlt.setCmnLanguageMst(cmnLanguageMst);
				orgPostDtlRlt.setOrgDesignationMst(desgnMst);
				orgPostDtlRlt.setCreatedDate(new Timestamp(new Date().getTime()));
				orgPostDtlRlt.setPostName(desgnMst.getDesgination().concat(String.valueOf(nextPsr)));
				orgPostDtlRlt.setPostShortName(desgnMst.getDesignationShortName().concat(String.valueOf(nextPsr)));
				orgPostDtlRlt.setCmnLocationMst(cmnLocationMst);

				entryOfPostsRepo.savePostDtls(orgPostDtlRlt);

				orderHeadPostmpg = new HrPayOrderHeadPostMpg();
				orderHeadPostmpg.setOrderHeadPostId(orderHeadPostId);
				orderHeadPostmpg.setOrderHeadId(orderHeadId);
				orderHeadPostmpg.setOrgUserMstByCreatedBy(messages);
				orderHeadPostmpg.setOrgPostMstByCreatedByPost(orgPostMst);
				orderHeadPostmpg.setPostId(postId);
				orderHeadPostmpg.setCreatedDate(new Date());

				entryOfPostsRepo.save(orderHeadPostmpg);

				HrPayOfficepostMpg hrOfficePostMpg = new HrPayOfficepostMpg();

				DdoOffice ddoOffice = new DdoOffice();

				// hrOfficePostMpg.setOfficePostId(officePostId);
				hrOfficePostMpg.setDdoOffice(ddoOffice);
				hrOfficePostMpg.setOrgPostMstByPostId(orgPostMaster);

				hrOfficePostMpg.setStartDate(new Timestamp(startDate.getTime()));
				hrOfficePostMpg.setCreatedBy(messages);
				hrOfficePostMpg.setCreatedDate(new Date());
				hrOfficePostMpg.setCreatedByPost(orgPostMst);
				entryOfPostsRepo.save(hrOfficePostMpg);
				nextPsr++;
			}
		} else if (postType.equals(CmnTId)) {
			if (postType.equals("3")) {
				for (int postCount = 1; postCount <= noOfPost; postCount++) {

					SubjectPostMpg subjectPostMpg1 = new SubjectPostMpg();

					orgPostMaster.setPostId(postId);
					// orgPostMaster.setParentPostId(-1);
					// orgPostMaster.setPostLevelId(1);
					orgPostMaster.setStartDate(new Timestamp(startDate.getTime()));
					if (endDate != null) {
						orgPostMaster.setEndDate(new Timestamp(endDate.getTime()));
					}
					orgPostMaster.setCmnLookupMst(lObjCmnLookupMst);
					orgPostMaster.setDsgnCode(desgnCode);
					orgPostMaster.setActivateFlag(1l);
					orgPostMaster.setCreatedDate(new Timestamp(new Date().getTime()));
					orgPostMaster.setCreatedBy(messages);
					orgPostMaster.setCreatedByPost(orgPostMst);
					orgPostMaster.setLocationCode(String.valueOf(locId));
					
					orgPostMaster=entryOfPostsRepo.savePost(orgPostMaster);
					Long pId =orgPostMaster.getPostId();

					subjectPostMpg1.setPostId(postId);
					subjectPostMpg1.setSubjectName(subjectSel);

					entryOfPostsRepo.submitSubject(subjectPostMpg1);

					// #########################
					postPsrMpg = new HrPayPsrPostMpg();
					postPsrMpg.setPsrId(nextPsr);
					postPsrMpg.setPostId(postId);
					if (billId <= 0)
						postPsrMpg.setBillNo(null);
					else
						postPsrMpg.setBillNo(billId);
					postPsrMpg.setLocId(locId);
					entryOfPostsRepo.save(postPsrMpg);

					orgPostDtlRlt = new OrgPostDetailsRlt();
					orgPostDtlRlt.setPostDetailId(postDtlId);
					orgPostDtlRlt.setOrgPostMst(orgPostMaster);
					orgPostDtlRlt.setCreatedByPost(orgPostMst);
					orgPostDtlRlt.setCreatedBy(messages);

					orgPostDtlRlt.setCmnLanguageMst(cmnLanguageMst);
					orgPostDtlRlt.setOrgDesignationMst(desgnMst);
					orgPostDtlRlt.setCreatedDate(new Timestamp(new Date().getTime()));
					orgPostDtlRlt.setPostName(desgnMst.getDesgination().concat(String.valueOf(nextPsr)));
					orgPostDtlRlt.setPostShortName(desgnMst.getDesignationShortName().concat(String.valueOf(nextPsr)));

					orgPostDtlRlt.setCmnLocationMst(cmnLocationMst);

					entryOfPostsRepo.savePostDtls(orgPostDtlRlt);

					orderHeadPostmpg = new HrPayOrderHeadPostMpg();
					orderHeadPostmpg.setOrderHeadPostId(orderHeadPostId);
					orderHeadPostmpg.setOrderHeadId(orderHeadId);
					orderHeadPostmpg.setOrgUserMstByCreatedBy(messages);
					orderHeadPostmpg.setOrgPostMstByCreatedByPost(orgPostMst);
					orderHeadPostmpg.setPostId(postId);
					orderHeadPostmpg.setCreatedDate(new Date());

					entryOfPostsRepo.save(orderHeadPostmpg);

					HrPayOfficepostMpg hrOfficePostMpg = new HrPayOfficepostMpg();

					DdoOffice ddoOffice = new DdoOffice();

					ddoOffice = entryOfPostsRepo.findOfficeByfficeId(officeId);

					// hrOfficePostMpg.setOfficePostId(officePostId);
					hrOfficePostMpg.setDdoOffice(ddoOffice);
					hrOfficePostMpg.setOrgPostMstByPostId(orgPostMaster);
					hrOfficePostMpg.setStartDate(new Timestamp(startDate.getTime()));
					hrOfficePostMpg.setCreatedBy(messages);
					hrOfficePostMpg.setCreatedDate(new Date());
					hrOfficePostMpg.setCreatedByPost(orgPostMst);
					entryOfPostsRepo.save(hrOfficePostMpg);
					nextPsr++;

				}
			} else if (postType.equals("4")) {

				for (int postCount = 1; postCount <= noOfPost; postCount++) {

					/*
					 * hrPayOrderMst = ordermstDaoImpl.read(Long.parseLong(newOrderId));
					 * hrPayOrderMst.setOrderDate(StringUtility.convertStringToDate(newOrderDate));
					 * hrPayOrderMst.setUpdatedDate(new Date());
					 * hrPayOrderMst.setOrgPostMstByUpdatedByPost(orgPostMst);
					 * hrPayOrderMst.setOrgUserMstByUpdatedBy(messages);
					 * ordermstDaoImpl.update(hrPayOrderMst);
					 */
				}
				// msg = 1;
			}

		}

		/*
		 * long scaleId = 1; GenericDaoHibernateImpl genDao = new
		 * GenericDaoHibernateImpl(HrEisScaleMst.class);
		 * genDao.setSessionFactory(serv.getSessionFactory()); HrEisScaleMst
		 * hrEisScaleMst = (HrEisScaleMst) genDao.read(scaleId); CmnLookupMstDAOImpl
		 * cmnDao = new CmnLookupMstDAOImpl(CmnLookupMst.class,
		 * serv.getSessionFactory()); GradeMasterDAO gradeDao = new
		 * GradeMasterDAO(OrgGradeMst.class, serv.getSessionFactory());
		 * 
		 * DdoOffice ddoOffice = new DdoOffice(); DdoOfficeDAOImpl ddoOfficeDAOImpl =
		 * new DdoOfficeDAOImpl(DdoOffice.class, serv.getSessionFactory()); ddoOffice =
		 * (DdoOffice) ddoOfficeDAOImpl.read(officeId); CmnLocationMstDaoImpl
		 * cmnLocMstLevelOne = new CmnLocationMstDaoImpl(CmnLocationMst.class,
		 * serv.getSessionFactory()); CmnLocationMst cmnLocationMst =
		 * cmnLocMstLevelOne.read(ddoOffice.getLocId()); // added by vaibhav tyagi : end
		 * long parentLocId = cmnLocationMst.getParentLocId();
		 * //logger.info("designation id is -----" + desgnMst.getDsgnCode()); //
		 * logger.info("parentLocId--" + parentLocId);
		 * 
		 * 
		 * 
		 * AdminOrgPostDtlDaoImpl adminOrgPostDtlDaoImpl = new
		 * AdminOrgPostDtlDaoImpl(OrgPostMst.class, serv.getSessionFactory());
		 * List<MstPayrollDesignationMst> lst =
		 * adminOrgPostDtlDaoImpl.getMstDcpsDsgnObject(parentLocId,
		 * desgnMst.getDsgnId()); logger.info("Size of MstPayrollDesignationMst List --"
		 * + lst.size()); MstPayrollDesignationMst mst = new MstPayrollDesignationMst();
		 * if (lst != null && lst.size() > 0) mst = lst.get(0);
		 * 
		 * logger.info("Size of MstPayrollDesignationMst List--" + mst.getDesigId() +
		 * "--cadreTypeId--" + mst.getCadreTypeId()); long cadreTypeId =
		 * mst.getCadreTypeId(); long grpName = Long.parseLong(new
		 * Long(cadreTypeId).toString()); logger.info("grpName--" + grpName);
		 * CmnLookupMst loonkupGrd = cmnDao.read(grpName); genDao = new
		 * GenericDaoHibernateImpl(OrgGradeMst.class);
		 * genDao.setSessionFactory(serv.getSessionFactory());
		 * logger.info("designation id is -----" + desgnMst.getDsgnCode()); long gradeId
		 * = Long.parseLong(loonkupGrd.getLookupShortName().toString());
		 * 
		 * logger.info("gradeId--" + gradeId); OrgGradeMst orgGradeMst = (OrgGradeMst)
		 * genDao.read(gradeId); GradDesgScaleMapDAO sgdDao = new
		 * GradDesgScaleMapDAO(HrEisSgdMpg.class, serv.getSessionFactory()); long dbId =
		 * Long.parseLong(loginMap.get("dbId").toString()); CmnDatabaseMstDaoImpl
		 * cmnDatabaseMstDaoImpl = new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,
		 * serv.getSessionFactory()); CmnDatabaseMst cmnDatabaseMst = (CmnDatabaseMst)
		 * cmnDatabaseMstDaoImpl.read(Long.valueOf(dbId)); long loggedInpostId =
		 * Long.parseLong(loginMap.get("loggedInPost").toString()); OrgPostMst
		 * loggedInOrgPostMst = orgPostMstDaoImpl.read(loggedInpostId); long
		 * loggedInUser =
		 * StringUtility.convertToLong(loginMap.get("userId").toString()); OrgUserMst
		 * loggedInOrgUserMst = orgUserMstDaoImpl.read(loggedInUser);
		 * 
		 * HrEisGdMpg gdMpg = null; HrEisSgdMpg sgdMpg = null; List<HrEisGdMpg> gdList =
		 * gradeDao.getDuplicateData(gradeId, desgnMst.getDsgnId(),
		 * cmnLocationMst.getLocId()); if (gdList != null && gdList.size() > 0) { gdMpg
		 * = gdList.get(0);
		 * 
		 * long gdId = gdMpg.getGdMapId(); logger.info("gdId--" + gdId);
		 * 
		 * List<HrEisSgdMpg> sgdMpglist = sgdDao.getScaleGradeDesgMasterData(gdId,
		 * scaleId, cmnLocationMst.getLocId()); logger.info("designation id is -----" +
		 * desgnMst.getDsgnCode()); if (sgdMpglist != null && sgdMpglist.size() > 0) {
		 * sgdMpg = sgdMpglist.get(0); logger.info("sgdMpg Exissts-----" +
		 * sgdMpg.getSgdMapId());
		 * 
		 * } else { // insert in sgd mapping sgdMpg = new HrEisSgdMpg(); long sgdId =
		 * idGen.PKGenerator("HR_EIS_SGD_MPG", objectArgs); sgdMpg.setSgdMapId(sgdId);
		 * sgdMpg.setCmnDatabaseMst(cmnDatabaseMst); //
		 * sgdMpg.setCmnLocationMst(hrEisScaleMst.getCmnLocationMst());
		 * sgdMpg.setCmnLocationMst(cmnLocationMst); sgdMpg.setCreatedDate(new
		 * java.util.Date()); sgdMpg.setHrEisGdMpg(gdMpg);
		 * sgdMpg.setHrEisScaleMst(hrEisScaleMst);
		 * sgdMpg.setOrgPostMstByCreatedByPost(loggedInOrgPostMst);
		 * sgdMpg.setOrgUserMstByCreatedBy(loggedInOrgUserMst); long sgdMpgCreated =
		 * sgdDao.create(sgdMpg); logger.info(" HR_EIS_SGD_MPG created " +
		 * sgdMpgCreated); }
		 * 
		 * } else { // insert gdmpg gdMpg = new HrEisGdMpg(); long gdId =
		 * idGen.PKGenerator("HR_EIS_GD_MPG", objectArgs);
		 * gdMpg.setCmnDatabaseMst(cmnDatabaseMst); //
		 * gdMpg.setCmnLocationMst(hrEisScaleMst.getCmnLocationMst());
		 * gdMpg.setCmnLocationMst(cmnLocationMst);
		 * 
		 * gdMpg.setCreatedDate(new java.sql.Date()); gdMpg.setGdMapId(gdId);
		 * gdMpg.setOrgDesignationMst(desgnMst); gdMpg.setOrgGradeMst(orgGradeMst);
		 * gdMpg.setOrgPostMstByCreatedByPost(loggedInOrgPostMst);
		 * gdMpg.setOrgUserMstByCreatedBy(loggedInOrgUserMst); genDao.create(gdMpg);
		 * logger.info("gd Created " + genDao.create(gdMpg) +
		 * " desig is is ****************" +
		 * gdMpg.getOrgDesignationMst().getDsgnCode());
		 * 
		 * 
		 * sgdMpg = new HrEisSgdMpg(); sgdMpg.setSgdMapId(sgdId);
		 * sgdMpg.setCmnDatabaseMst(cmnDatabaseMst);
		 * sgdMpg.setCmnLocationMst(cmnLocationMst); sgdMpg.setCreatedDate(new
		 * java.util.Date()); sgdMpg.setHrEisGdMpg(gdMpg);
		 * sgdMpg.setHrEisScaleMst(hrEisScaleMst);
		 * sgdMpg.setOrgPostMstByCreatedByPost(loggedInOrgPostMst);
		 * sgdMpg.setOrgUserMstByCreatedBy(loggedInOrgUserMst); long sgdMpgCreatedTwo =
		 * sgdDao.create(sgdMpg);
		 */
	}

	@Override
	public void savePostEntryDtl(PostEntryModel postEntryModel, long locId, BigInteger loggedInPostId,
			OrgUserMst messages) {
		// long userID = messages.getUserId();

		OrgPostMst orgPostMst = entryOfPostsRepo.findPost(loggedInPostId.longValue());

		CmnLanguageMst cmnLanguageMst = cmnLanguageMstRepository.findByLangId(1l);
		CmnLookupMst lObjCmnLookupMst = cmnLookupMstRepository
				.findByLookupId(Long.valueOf(postEntryModel.getPostTypeCmbBox()));

		OrgDdoMst orgDdoMst = organizationInstInfoRepo.findDDOInfo(postEntryModel.getDdoCode());

		List<CmnLocationMst> lstCmnLocationMst=entryOfPostsRepo.findByLocId(Long.valueOf(orgDdoMst.getLocationCode()));
		
		CmnLocationMst cmnLocationMst = null;
		if(lstCmnLocationMst.size()>0) {
			 cmnLocationMst = lstCmnLocationMst.get(0);
		}else {
			cmnLocationMst=new CmnLocationMst();
		}
		

		long nextPsr = entryOfPostsRepo.getNextPsrNo();

		// long setPostId = loggedInPostId.longValue();
		// String ddoCode = messages.getUserName();

		String desgnCode = postEntryModel.getDesignationCmb();

		MstDesignationEntity desgnMst = entryOfPostsRepo.finddesignationByCode(Long.valueOf(desgnCode));

		// long desgnId = Long.parseLong(desgnCode);

		for (int postCount = 1; postCount <= postEntryModel.getPostNumber(); postCount++) {

			OrgPostMst newOrgPostMst = new OrgPostMst();
			newOrgPostMst.setStartDate(new Timestamp(postEntryModel.getStartDate().getTime()));
			if (postEntryModel.getEndDate() != null) {
				newOrgPostMst.setEndDate(new Timestamp(postEntryModel.getEndDate().getTime()));
			}
			newOrgPostMst.setCmnLookupMst(lObjCmnLookupMst);
			newOrgPostMst.setDsgnCode(desgnCode);
			newOrgPostMst.setActivateFlag(1l);
			newOrgPostMst.setCreatedDate(new Timestamp(new Date().getTime()));
			newOrgPostMst.setCreatedBy(messages);
			newOrgPostMst.setCreatedByPost(orgPostMst);
			newOrgPostMst.setLocationCode(String.valueOf(orgDdoMst.getLocationCode()));
			newOrgPostMst.setParentPostId(-1l);
			newOrgPostMst.setPostLevelId(1l);
			newOrgPostMst.setStatusLookupId(13l);
			
			newOrgPostMst.setPostId(orgPostMstRepository.findMaxPostId()+1);

			newOrgPostMst.setOfficeId(postEntryModel.getOfficeCmb());

			newOrgPostMst.setPostTypeLookupId(lObjCmnLookupMst);
			newOrgPostMst.setOrderId(postEntryModel.getOrderCmb());
			newOrgPostMst.setOrderDate(postEntryModel.getOrderDate());
			newOrgPostMst.setDdoCode(postEntryModel.getDdoCode());

			

			OrgPostMst pg1 = entryOfPostsRepo.savePost(newOrgPostMst);
			Long postId = pg1.getPostId();

			/*
			 * SubjectPostMpg subjectPostMpg = new SubjectPostMpg();
			 * 
			 * subjectPostMpg.setPostId(postId);
			 * subjectPostMpg.setSubjectName(postEntryModel.getSubjectCmb());
			 * 
			 * entryOfPostsRepo.submitSubject(subjectPostMpg);
			 */

			HrPayPsrPostMpg postPsrMpg = new HrPayPsrPostMpg();
			postPsrMpg.setPsrId(nextPsr);
			postPsrMpg.setPostId(postId);
			postPsrMpg.setLocId(orgDdoMst.getLocationCode()==null?0l:Long.valueOf(orgDdoMst.getLocationCode()));
			entryOfPostsRepo.save(postPsrMpg);

			OrgPostDetailsRlt orgPostDtlRlt = new OrgPostDetailsRlt();
			orgPostDtlRlt.setOrgPostMst(pg1);
			orgPostDtlRlt.setCreatedByPost(orgPostMst);
			orgPostDtlRlt.setCreatedBy(messages);
			orgPostDtlRlt.setCmnLanguageMst(cmnLanguageMst);
			orgPostDtlRlt.setOrgDesignationMst(desgnMst);
			orgPostDtlRlt.setCreatedDate(new Timestamp(new Date().getTime()));
			orgPostDtlRlt.setPostName(desgnMst.getDesgination().concat(String.valueOf(nextPsr)));
			orgPostDtlRlt.setPostShortName(desgnMst.getDesignationShortName().concat(String.valueOf(nextPsr)));
			orgPostDtlRlt.setCmnLocationMst(cmnLocationMst);
			orgPostDtlRlt.setIsVancant(0);
			entryOfPostsRepo.savePostDtls(orgPostDtlRlt);

			nextPsr++;
		}

	}

	@Override
	public List<HrPayOrderMst> findGrOrderDetails(Long grOrderId) {
		// TODO Auto-generated method stub
		return entryOfPostsRepo.findGrOrderDetails(grOrderId);
	}

	@Override
	public List getPostNameForDisplay(String ddoCode) {
		List postNameList = entryOfPostsRepo.getPostNameForDisplay(ddoCode);

		List post = new ArrayList();
		String empFullName = "";
		String postType = "";
		String subjectName = "";
		String postLookupId = "";
		String permenantlookupId = "10001198129";
		String temparerylookupId = "10001198130";
		String statutorylookupId = "10001198155";

		if (postNameList != null) {
			for (int i = 0; i < postNameList.size(); i++) {
				UserPostCustomVO customVO = new UserPostCustomVO();

				Object rowList[] = (Object[]) postNameList.get(i);

				String postName = rowList[0].toString();
				// customVO.setPostname(postName);

				if (rowList[6] != null && !(rowList[6].toString().trim()).equals("")) {
					postLookupId = rowList[6].toString();
				}
				if (postLookupId.equals("10001198129")) {
					postName = postName.concat("P");
				} else if (postLookupId.equals("10001198130")) {
					postName = postName.concat("T");
				} else if (postLookupId.equals("10001198155")) {
					postName = postName.concat("S");
				} else {
					postName = postName;
				}
				customVO.setPostname(postName);

				long postId = Long.parseLong(rowList[1].toString());
				customVO.setPostId(postId);
				if (rowList[2] != null && !(rowList[2].toString().trim()).equals("")) {
					empFullName = rowList[2].toString();
				} else {
					empFullName = "VACANT";
				}
				customVO.setEmpFullName(empFullName);

				String dsgnName = rowList[3].toString();
				if (rowList[3] != null && !(rowList[3].toString().trim()).equals("")) {
					customVO.setDsgnname(dsgnName);
				}
				String BillNo = " ";
				String PsrNo = " ";

				if (rowList[4] != null) {
					PsrNo = rowList[4].toString();
				}
				customVO.setPsrNo(PsrNo);

				if (rowList[5] != null) {
					BillNo = rowList[5].toString();
				}
				customVO.setBillNo(BillNo);

				// logger.info("===> BillNo :: "+BillNo);
				if (rowList[7] != null && !(rowList[7].toString().trim()).equals("")) {
					postType = rowList[7].toString();
				} else {
					postType = "VACANT";
				}
				
								
				customVO.setDdoCode(rowList[8].toString());
				

				customVO.setPostType(postType);

				post.add(customVO);
			}
		}

		return post;
	}

	@Override
	public List<OrgPostDetailsRlt> searchPostListByGrOrderId(Long locId, Long orderId) {
		// TODO Auto-generated method stub
		// entryOfPostsRepo.searchPostListByGrOrderId(orderId);
		List customizedPostList = new ArrayList();
		String regex = "(?<=[\\D])(?=\\d)";
		List postList = entryOfPostsRepo.searchPostListByGrOrderId(orderId);
		Integer totalRecords = postList.size();
		HrPayOrderMst hrPayOrderMst = entryOfPostsRepo.find(orderId);
		Date currentOrderDate = hrPayOrderMst.getOrderDate();

		String postStartDate = null;

		for (int i = 0; i < totalRecords; i++) {
			OrgPostDetailsRlt postDetailsRlt = new OrgPostDetailsRlt();
			OrgPostDetailsRlt postDetailsRltNonPer = new OrgPostDetailsRlt();
			postDetailsRlt = (OrgPostDetailsRlt) postList.get(i);

			String postName = postDetailsRlt.getPostName();

			if (postName.indexOf("_") != -1) {
				String postNameArr[] = postName.split("_");
				postDetailsRltNonPer.setPostName(postNameArr[0].toString() + "	(" + postNameArr[1].toString() + ")");
			} else {
				String postNameArr[] = postName.split(regex);
				postDetailsRltNonPer.setPostName(postNameArr[0].toString() + "	(" + postNameArr[1].toString() + ")");
			}

			postDetailsRltNonPer.setPostDetailId(postDetailsRlt.getPostDetailId());
			postDetailsRltNonPer.setPostShortName(postDetailsRlt.getPostShortName());
			postDetailsRltNonPer.setOrgDesignationMst(postDetailsRlt.getOrgDesignationMst());
			postDetailsRltNonPer.setOrgPostMst(postDetailsRlt.getOrgPostMst());

			postDetailsRltNonPer.setCmnBranchMst(postDetailsRlt.getCmnBranchMst());
			postDetailsRltNonPer.setCmnLanguageMst(postDetailsRlt.getCmnLanguageMst());
			postDetailsRltNonPer.setCmnLocationMst(postDetailsRlt.getCmnLocationMst());
			postDetailsRltNonPer.setCreatedDate(postDetailsRlt.getCreatedDate());

			customizedPostList.add(postDetailsRltNonPer);
		}
		return customizedPostList;

	}

	/*
	 * @Override public List<HrPayOrderMst> getAllOrderDataByDate(long locId, String
	 * todaysDate) { return
	 * entryOfPostsRepo.getAllOrderDataByDate(locId,todaysDate); }
	 */
	@Override
	public List<HrPayOrderMst> getAllOrderDataByDate(long locId, String todaysDate, String ddoCode) {
		return entryOfPostsRepo.getAllOrderDataByDate(locId, todaysDate, ddoCode);
	}

	@Override
	public List getExpiryData(long locId, String ddoCode) {
		return entryOfPostsRepo.getExpiryData(locId, ddoCode);
	}

	@Override
	public void renewPostEntry(PostEntryModel postEntryModel, long locId, BigInteger loggedInPostId,
			OrgUserMst messages) {
		if (postEntryModel.getPostIdsToBeAttached() != " ") {  //99100001001649
			String[] lStrArrPostIdsToBeAttached = postEntryModel.getPostIdsToBeAttached().split("~");
			Long[] lLongArrPostIdsToBeAttached = new Long[lStrArrPostIdsToBeAttached.length];
			for (Integer lInt = 0; lInt < lStrArrPostIdsToBeAttached.length; lInt++) {
				if (lStrArrPostIdsToBeAttached[lInt] != "") {
					lLongArrPostIdsToBeAttached[lInt] = Long.valueOf(lStrArrPostIdsToBeAttached[lInt]);
					OrgPostMst orgPostMst = orgPostMstRepository.findByPostId(lLongArrPostIdsToBeAttached[lInt]);
					orgPostMst.setOrderDate(new Timestamp(postEntryModel.getRenewalStartDate().getTime()));
					orgPostMst.setStartDate(new Timestamp(postEntryModel.getRenewalPostStartDate().getTime()));
					orgPostMst.setEndDate(new Timestamp(postEntryModel.getRenewalEndDate().getTime()));
					orgPostMst.setOrderId(postEntryModel.getCmbNewOrder());
					orgPostMstRepository.save(orgPostMst);
				}
			}
		}
	}

	@Override
	public List findLevel1DddoByDdoCode(String ddoCode) {
		return entryOfPostsRepo.findLevel1DddoByDdoCode(ddoCode);
	}

	@Override
	public HrPayOrderMst findOrderMasterById(long oldGrOrderId) {
		return entryOfPostsRepo.findOrderMasterById(oldGrOrderId);
	}

	@Override
	public List<Object[]> findLevelDdoCodeByDistrict(String districtId, OrgUserMst messages) {
		return entryOfPostsRepo.findLevelDdoCodeByDistrict(districtId,messages);
	}

	@Override
	public List<MstDesignationEntity> findAllDesignation() {
		return entryOfPostsRepo.findAllDesignation();
	}

	@Override
	public List<MstDesignationEntity> getDesignationLstByDdoCode(String ddoCode) {
		List<OrgDdoMst> lst=entryOfPostsRepo.findDdoDetailByDdoCode(ddoCode);
		if(lst.size()>0) {
			return entryOfPostsRepo.getDesignationLstByDdoCode(lst.get(0).getHodLocCode());
		}else {
			return entryOfPostsRepo.getDesignationLstByDdoCode(ddoCode);
		}
	}

}
