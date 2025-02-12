package com.mahait.gov.in.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mahait.gov.in.common.CommonConstants;
import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.entity.CmnLocationMst;
import com.mahait.gov.in.entity.CmnLookupMst;
import com.mahait.gov.in.entity.DcpsContributionEntity;
import com.mahait.gov.in.entity.MstDcpsBillGroup;
import com.mahait.gov.in.entity.MstDcpsContriVoucherDtlEntity;
import com.mahait.gov.in.entity.MstEmployeeEntity;
import com.mahait.gov.in.entity.OrgDdoMst;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.DcpContributionModel;
import com.mahait.gov.in.model.MstSchemeModel;
import com.mahait.gov.in.repository.OnlineContributionRepo;
import com.mahait.gov.in.repository.PaybillGenerationTrnRepo;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class OnlineContributionServiceImpl implements OnlineContributionService {

	@Autowired
	OnlineContributionRepo onlineContributionRepo;

	@Autowired
	PaybillGenerationTrnRepo paybillHeadMpgRepo;

	@Override
	public List<CmnLookupMst> getPaymentTypeLst() {
		return onlineContributionRepo.getPaymentTypeLst();
	}

	@Override
	public Boolean checkIfBillAlreadyGenerated(Long billGroupId, Integer monthId, Integer finYearId, String ddoCode) {
		return onlineContributionRepo.checkIfBillAlreadyGenerated(billGroupId, monthId, finYearId, ddoCode);
	}

	@Override
	public List<DcpContributionModel> getEmpListForContribution(DcpContributionModel dcpContributionModel,
			OrgUserMst messages, String startDate) {

		List<Object[]> lstobject = onlineContributionRepo.getEmpListForContribution(dcpContributionModel, messages,
				startDate);
		List<DcpContributionModel> dcpContributionModelLst = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Date newStartDate = null;
		Date newEndDate = null;

		if (dcpContributionModel.getTypeOfPayment() != null) {
			if (dcpContributionModel.getTypeOfPayment().equals("700048")) {
				if (dcpContributionModel.getDAArrearStartDate() != null
						&& dcpContributionModel.getDAArrearEndDate() != null) {
					newStartDate = dcpContributionModel.getDAArrearStartDate();
					newEndDate = dcpContributionModel.getDAArrearEndDate();
				} else {
					int month2 = dcpContributionModel.getMonthId();
					int year2 = dcpContributionModel.getFinYearId();
					
					if (month2 < 10) {
						startDate = 20 + String.valueOf((year2 - 1)) + '-' + String.valueOf("0" + month2) + "-01";
					} else {
						startDate = 20 + String.valueOf((year2 - 1)) + '-' + String.valueOf(month2) + "-01";
					}
					
					Date date = null;
					try {
						date = sdf.parse(startDate);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(date);
					calendar.add(Calendar.MONTH, -1); 
					newStartDate = calendar.getTime();
					
					calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH)); // Set to the
					newEndDate = calendar.getTime();
					
				}
			} else if (dcpContributionModel.getTypeOfPayment().equals("700049")) {
				if (dcpContributionModel.getDAArrearStartDate() != null
						&& dcpContributionModel.getDAArrearEndDate() != null) {
					startDate = sdf.format(dcpContributionModel.getPayArrearStartDate());
					newEndDate = dcpContributionModel.getPayArrearEndDate();
				} else {
					int month2 = dcpContributionModel.getMonthId();
					int year2 = dcpContributionModel.getFinYearId();
					
					if (month2 < 10) {
						startDate = 20 + String.valueOf((year2 - 1)) + '-' + String.valueOf("0" + month2) + "-01";
					} else {
						startDate = 20 + String.valueOf((year2 - 1)) + '-' + String.valueOf(month2) + "-01";
					}
					
					Date date = null;
					try {
						date = sdf.parse(startDate);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(date);
					calendar.add(Calendar.MONTH, -1); 
					newStartDate = calendar.getTime();
					
					calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH)); // Set to the
					newEndDate = calendar.getTime();
				}
			} else {
				Date date = null;
				try {
					date = sdf.parse(startDate);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				newStartDate = calendar.getTime();
				calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH)); // Set to the
				newEndDate = calendar.getTime();
			}
		}

		for (Object object[] : lstobject) {

			Integer lInt2 = 0;
			Integer BasicPay = 0;
			Double DP = 0D;
			String lStrDP = "";
			Double DARate = 0d;
			String lStrTypeOfPayment = "";
			Double DA = 0D;
			Integer employeeContribution = 0;
			String lStrDA = "";
			Integer emplrContribution = 0;

			// Object[] object = (Object[]) empList.get(lInt1);
			DcpContributionModel dcpContributionModel1 = new DcpContributionModel();

			dcpContributionModel1.setDcpEmpId(StringHelperUtils.isNullBigInteger(object[0]).longValue());
			dcpContributionModel1.setDcpsNO(StringHelperUtils.isNullString(object[1]));
			dcpContributionModel1.setEmployeeName(String.valueOf(object[2]));
			dcpContributionModel1.setPayCommission(StringHelperUtils.isNullBigInteger(object[3]).longValue());

			if (object[4] instanceof Double) {
				dcpContributionModel1.setBasicPay(StringHelperUtils.isNullDouble(object[4]).intValue());
			} else if (object[4] instanceof BigInteger) {
				dcpContributionModel1.setBasicPay(StringHelperUtils.isNullBigInteger(object[4]).intValue());
			}else if (object[4] instanceof Integer) {
				dcpContributionModel1.setBasicPay(StringHelperUtils.isNullInt(object[4]));
			}

			BasicPay = dcpContributionModel1.getBasicPay().intValue();

			dcpContributionModel1.setDcpContributionId(StringHelperUtils.isNullBigInteger(object[5]).longValue());
			dcpContributionModel1.setTypeOfPayment(StringHelperUtils.isNullString(object[6]));

			if (object[7] instanceof BigInteger) {
				dcpContributionModel1.setMonthId(StringHelperUtils.isNullBigInteger(object[7]).intValue());
			} else {
				dcpContributionModel1.setMonthId(dcpContributionModel.getMonthId());
			}

			if (object[8] instanceof BigInteger) {
				dcpContributionModel1.setFinYearId(StringHelperUtils.isNullBigInteger(object[8]).intValue());
			} else {
				dcpContributionModel1.setFinYearId(dcpContributionModel.getFinYearId());
			}

			if (object[9] instanceof BigDecimal) {
				dcpContributionModel1.setDaRate(StringHelperUtils.isNullBigDecimal(object[9]).intValue());
			} else {
				dcpContributionModel1.setDaRate(StringHelperUtils.isNullInt(object[9]));
			}

			if (object[10] != null) {
				dcpContributionModel1.setRegStatus(StringHelperUtils.isNullInt(object[10]));
			} 
			
			
			dcpContributionModel1.setDoj(StringHelperUtils.isNullDate(object[11]));

			if (object[15] != null) {
				dcpContributionModel1.setStartDate(StringHelperUtils.isNullDate(object[15]));
			} else {
				dcpContributionModel1.setStartDate(newStartDate);
			}

			if (object[16] != null) {
				dcpContributionModel1.setEndDate(StringHelperUtils.isNullDate(object[16]));
			} else {
				dcpContributionModel1.setEndDate(newEndDate);
			}

			dcpContributionModel1.setSevaarthId(StringHelperUtils.isNullString(object[18]));

			DP = 0D;
			lStrDP = "";
			if (null != object[13]) {
				lStrDP = object[13].toString();
			}
			if (object[3].toString().equals("700015") || object[3].toString().equals("700345")) { // paycommision
				if (null != lStrDP && !"".equals(lStrDP)) {
					DP = Double.parseDouble(lStrDP);
				} else {
					DP = (double) ((BasicPay) / 2);
				}
			}

			DARate = 0.01 * Double.parseDouble(object[9].toString());
			lStrTypeOfPayment = object[6].toString();
			DA = 0D;
			employeeContribution = 0;
			emplrContribution = 0;

			lStrDA = "";

			if (null != object[12]) {
				lStrDA = object[12].toString(); // da aMOUNT
			}

			if (lStrTypeOfPayment.equals("700048")) {
				if (object[12] != null) {
					DA = Double.parseDouble(object[12].toString());
				} else {
					DA = ((BasicPay + DP) * DARate);
				}

				if (object[14] != null) {
					if (object[14] instanceof Double) {
						employeeContribution = StringHelperUtils.isNullDouble(object[14]).intValue();
					}else {
						employeeContribution = Integer.parseInt(object[14].toString());
					}
					
					
				} else {
					employeeContribution = (int) (((int) Math.ceil(BasicPay) + Math.round(DA)) * 0.10);
				}

				if (object[17] != null && !object[17].toString().equals("0.0") && !object[17].toString().equals("0")) {
					if (object[17] instanceof Double) {
						emplrContribution =  StringHelperUtils.isNullDouble(object[17]).intValue();
					}else {
						emplrContribution =  Integer.parseInt(object[17].toString());
					}
				} else {
					if ((dcpContributionModel.getFinYearId() <= 20 && dcpContributionModel.getMonthId() <= 3)
							|| dcpContributionModel.getFinYearId() < 20) { // 2019(Old 32 if ((finYearId <= 32
						emplrContribution = (int) (((int) Math.ceil(BasicPay) + Math.ceil(DP) + Math.round(DA)) * 0.10);
					}else {
						emplrContribution = (int) (((int) Math.ceil(BasicPay) + Math.ceil(DP) + Math.round(DA)) * 0.14);
					}
				}

			} else if (lStrTypeOfPayment.equals("700049")) {
				DA = 0D;

				if (object[12] != null) {
					DA = Double.parseDouble(object[12].toString());
				} else {
					DA = ((BasicPay + DP) * DARate);
				}

				if (object[14] != null) {
					if (object[14] instanceof Double) {
						employeeContribution = StringHelperUtils.isNullDouble(object[14]).intValue();
					}else {
						employeeContribution = Integer.parseInt(object[14].toString());
					}
				} else {
					employeeContribution = (int) (((int) Math.ceil(BasicPay) + Math.round(DA)) * 0.10);
				}

				if (object[17] != null && !object[17].toString().equals("0.0") && !object[17].toString().equals("0")) {
					if (object[17] instanceof Double) {
						emplrContribution =  StringHelperUtils.isNullDouble(object[17]).intValue();
					}else {
						emplrContribution =  Integer.parseInt(object[17].toString());
					}
					
				} else {
					if ((dcpContributionModel.getFinYearId() <= 20 && dcpContributionModel.getMonthId() <= 3)
							|| dcpContributionModel.getFinYearId() < 20) { // 2019(Old 32 if ((finYearId <= 32

						emplrContribution = (int) (((int) Math.ceil(BasicPay) + Math.ceil(DP) + Math.round(DA)) * 0.10);
					}else {
						emplrContribution = (int) (((int) Math.ceil(BasicPay) + Math.ceil(DP) + Math.round(DA)) * 0.14);
					}
				}

			} else {
				if (null != lStrDA && !"".equals(lStrDA)  && !lStrDA.equals("0.0")) {
					DA = Double.parseDouble(lStrDA);
				} else {
					DA = ((BasicPay + DP) * DARate);
				}

				if (null != object[14]) {
					if (object[14] instanceof Double) {
						employeeContribution = StringHelperUtils.isNullDouble(object[14]).intValue();
					}else {
						employeeContribution = Integer.parseInt(object[14].toString());
					}
				} else {
					if (object[3].toString().equals("700015")) {
						employeeContribution = (int) (((int) Math.ceil(BasicPay) + Math.ceil(DP) + Math.round(DA)) * 0.10);
					} else {
						employeeContribution = (int) (((int) Math.ceil(BasicPay) + Math.round(DA)) * 0.10);
					}
				}

				if (object[17]!=null && !object[17].toString().equals("0.0") && !object[17].toString().equals("0")) {
					if (object[17] instanceof Double) {
						emplrContribution =  StringHelperUtils.isNullDouble(object[17]).intValue();
					}else {
						emplrContribution =  Integer.parseInt(object[17].toString());
					}
					
				} else {
					if ((dcpContributionModel.getFinYearId() <= 20 && dcpContributionModel.getMonthId() <= 3)
							|| dcpContributionModel.getFinYearId() < 20) { // 2019(Old 32 if ((finYearId <= 32

						if (object[3].toString().equals("700015")) {
							emplrContribution = (int) (((int) Math.ceil(BasicPay) + Math.ceil(DP) + Math.round(DA)) * 0.10);
						} else {
							emplrContribution = (int) (((int) Math.ceil(BasicPay) + Math.round(DA)) * 0.10);
						}
					} else {
						if (object[3].toString().equals("700015")) {
							emplrContribution = (int) (((int) Math.ceil(BasicPay) + Math.ceil(DP) + Math.round(DA)) * 0.14);
						} else {
							emplrContribution = (int) (((int) Math.ceil(BasicPay) + Math.round(DA)) * 0.14);
						}

					}
				}
			}
			DA = (double) Math.round(DA);

			employeeContribution = (int) Math.round(employeeContribution);
			emplrContribution = (int) Math.round(emplrContribution);

			dcpContributionModel1.setDp(DP);
			dcpContributionModel1.setDa(DA.intValue());
			dcpContributionModel1.setEmprContribution(emplrContribution.intValue());
			dcpContributionModel1.setContribution(employeeContribution.intValue());

			dcpContributionModelLst.add(dcpContributionModel1);
		}

		return dcpContributionModelLst;
	}

	@Override
	public Long saveOrUpdate(DcpContributionModel dcpContributionModel, OrgUserMst orgUserMst) {
		
		  List<Long> idsToDelete = dcpContributionModel.getDeleteDcpContributionId();
	        if (idsToDelete != null && !idsToDelete.isEmpty()) {
	            onlineContributionRepo.deleteContributionIds(idsToDelete);
	        }
		MstDcpsContriVoucherDtlEntity mstDcpsContriVoucherDtlEntity = onlineContributionRepo
				.findMstDcpsContriVoucherDtlEntity(dcpContributionModel).orElseGet(MstDcpsContriVoucherDtlEntity::new);
		mstDcpsContriVoucherDtlEntity.setBillGroupId(dcpContributionModel.getBillGroupId());
		mstDcpsContriVoucherDtlEntity.setCreatedDate(new Timestamp((new Date().getTime())));
		mstDcpsContriVoucherDtlEntity.setCreatedPostId(dcpContributionModel.getCreatedPostId());
		mstDcpsContriVoucherDtlEntity.setDdoCode(dcpContributionModel.getDdoCode());
		mstDcpsContriVoucherDtlEntity.setMonthId(dcpContributionModel.getMonthId());
		mstDcpsContriVoucherDtlEntity.setYearId(dcpContributionModel.getFinYearId());
		// mstDcpsContriVoucherDtlEntity.setStatus('1');
		mstDcpsContriVoucherDtlEntity.setTreasuryCode(dcpContributionModel.getTreasuryCode());

		if(mstDcpsContriVoucherDtlEntity.getMstDcpsContriVoucherDtls()!=null) {
			mstDcpsContriVoucherDtlEntity.setUpdatedDate(new Timestamp(new Date().getTime()));
			mstDcpsContriVoucherDtlEntity.setUpdatedUserId(orgUserMst.getUserId());
			mstDcpsContriVoucherDtlEntity.setUpdatedPostId(orgUserMst.getUserId());
		}
		
		Long save = onlineContributionRepo.saveMstDcpsContriVoucherDtlEntity(mstDcpsContriVoucherDtlEntity);

		if(dcpContributionModel.getLstDcpContributionModel()!=null) {
			for (DcpContributionModel dcpContributionModel1 : dcpContributionModel.getLstDcpContributionModel()) {
				DcpsContributionEntity dcpsContributionEntity = onlineContributionRepo
						.findDcpsContri(dcpContributionModel1.getDcpContributionId()).orElseGet(DcpsContributionEntity::new);
				dcpsContributionEntity.setBasicPay(dcpContributionModel1.getBasicPay().doubleValue());
				dcpsContributionEntity.setBillGroupId(dcpContributionModel.getBillGroupId());
				dcpsContributionEntity.setDa(dcpContributionModel1.getDa().doubleValue());
				dcpsContributionEntity.setDp(dcpContributionModel1.getDp());
				dcpsContributionEntity.setTypeOfPayment(dcpContributionModel1.getTypeOfPayment());
				dcpsContributionEntity.setDcpEmpId(dcpContributionModel1.getDcpEmpId());
				dcpsContributionEntity.setDdoCode(dcpContributionModel1.getDdoCode());
				dcpsContributionEntity.setTreasuryCode(dcpContributionModel.getTreasuryCode());
				dcpsContributionEntity.setDelayedFinYearId(dcpContributionModel.getDelayedFinYearId());
				dcpsContributionEntity.setDelayedMonthId(dcpContributionModel.getDelayedMonthId());
				dcpsContributionEntity.setLangId(1l);
				dcpsContributionEntity.setLocId(dcpContributionModel.getLocId());
				dcpsContributionEntity.setStartDate(dcpContributionModel1.getStartDate());
				dcpsContributionEntity.setEndDate(dcpContributionModel1.getEndDate());
				dcpsContributionEntity.setFinYearId(dcpContributionModel.getFinYearId());
				dcpsContributionEntity.setMonthId(dcpContributionModel.getMonthId());
				dcpsContributionEntity.setPayCommission(dcpContributionModel1.getPayCommission().toString());
				dcpsContributionEntity.setContribution(dcpContributionModel1.getContribution().doubleValue());
				dcpsContributionEntity.setContributionEmpr(dcpContributionModel1.getEmprContribution().floatValue());
			//	dcpsContributionEntity.setRegStatus(0);
				dcpsContributionEntity.setRegStatus(1);
				dcpsContributionEntity.setDbId(99l);
				dcpsContributionEntity.setDdoCode(orgUserMst.getDdoCode());
				dcpsContributionEntity.setSevaarthId(dcpContributionModel1.getSevaarthId());
				dcpsContributionEntity.setCreatedDate(new Timestamp(new Date().getTime()));
				dcpsContributionEntity.setCreatedPostId(orgUserMst.getPostId());
				dcpsContributionEntity.setCreatedUserId(orgUserMst.getUserId());
				dcpsContributionEntity.setRltContriVoucherId(save);
				
				if(dcpsContributionEntity.getDcpContributionId()!=null) {
					dcpsContributionEntity.setUpdatedDate(new Timestamp(new Date().getTime()));
					dcpsContributionEntity.setUpdatedUserId(orgUserMst.getUserId());
					dcpsContributionEntity.setUpdatedPostId(orgUserMst.getUserId());
				}
				
				onlineContributionRepo.saveDcpsContributionEntity(dcpsContributionEntity);
			}
		}
		return save;
	}

	@Override
	public List<MstSchemeModel> getSchemeCodeByBillGroupId(String billGroupId) {

		List<Object[]> lstObject = onlineContributionRepo.getSchemeCodeByBillGroupId(billGroupId);
		List<MstSchemeModel> lstMstSchemeModel = new ArrayList<>();

		for (Object object[] : lstObject) {
			MstSchemeModel mstSchemeModel = new MstSchemeModel();
			mstSchemeModel.setSchemeCode(object[0].toString());
			mstSchemeModel.setSchemeName(object[1].toString());
			lstMstSchemeModel.add(mstSchemeModel);
		}

		return lstMstSchemeModel;
	}

	@Override
	public DcpContributionModel calculateDcpsArrear(Map<String, String> formData) {
		String fromDateStr = formData.get("startDate");
		String toDateStr = formData.get("endDate");
		String sevaarthId = formData.get("sevaarthId");
		String payCommission = formData.get("payCommission");
		// String monthId = formData.get("monthId");
		// String finYearId = formData.get("finYearId");

		double basicPay = 0d;
		Integer allowDeducCode = Integer
				.parseInt(payCommission) == (CommonConstants.PAYBILLDETAILS.COMMONCODE_PAYCOMMISSION_7PC)
						? CommonConstants.PAYBILLDETAILS.SVNPC_ALLOW_DEDUC_CODE
						: CommonConstants.PAYBILLDETAILS.SIXPC_ALLOW_DEDUC_CODE;

		DcpContributionModel totalContributionModel = new DcpContributionModel();
		MstEmployeeEntity employeeEntity = onlineContributionRepo.findEmpDtlBySevaarthId(sevaarthId);

		basicPay = Integer.parseInt(payCommission) == CommonConstants.PAYBILLDETAILS.COMMONCODE_PAYCOMMISSION_7PC
				? employeeEntity.getSevenPcBasic()
				: employeeEntity.getBasicPay();

		double totalDa = 0d;
		// double employeeContribution = 0d;
		// double employerContribution = 0d;

		double totalBasicPay = 0d;
		double totalEmployeeContribution = 0d;
		double totalEmployerContribution = 0d;

		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date fromDate = dateFormat.parse(fromDateStr);
			Date toDate = dateFormat.parse(toDateStr);

			Calendar startCal = Calendar.getInstance();
			startCal.setTime(fromDate);

			Calendar endCal = Calendar.getInstance();
			endCal.setTime(toDate);

			if (startCal.get(Calendar.YEAR) == endCal.get(Calendar.YEAR)
					&& startCal.get(Calendar.MONTH) == endCal.get(Calendar.MONTH)) {
				int daysInRange = endCal.get(Calendar.DAY_OF_MONTH) - startCal.get(Calendar.DAY_OF_MONTH) + 1;
				int totalDaysInMonth = startCal.getActualMaximum(Calendar.DAY_OF_MONTH);

				double oneMonthBasic = (basicPay * daysInRange) / totalDaysInMonth;

				Integer percentageRate = paybillHeadMpgRepo.getDaPercentageByMonthYear(fromDateStr,Integer.parseInt(payCommission), allowDeducCode);
				double daRate = 0.01 * percentageRate.doubleValue();
				double oneMonthDa = oneMonthBasic * daRate;

				double oneMonthEmployeeContribution = (Math.ceil(oneMonthBasic) + Math.round(oneMonthDa)) * 0.10;

				double oneMonthEmployerContribution = 0d;

				// if ((Integer.parseInt(finYearId) <= 20 && Integer.parseInt(monthId) <= 3) ||
				// Integer.parseInt(finYearId) < 20) {

				if ((endCal.get(Calendar.YEAR) <= 2019 && startCal.get(Calendar.MONTH) <= 3)
						|| endCal.get(Calendar.YEAR) < 2019) {
					oneMonthEmployerContribution = (Math.ceil(oneMonthBasic) + Math.round(oneMonthDa)) * 0.14;
				} else {
					oneMonthEmployerContribution = (Math.ceil(oneMonthBasic) + Math.round(oneMonthDa)) * 0.14;
				}

				totalContributionModel.setDaRate(percentageRate);

				totalContributionModel.setBasicPay((int) Math.round(oneMonthBasic));
				totalContributionModel.setDa((int) Math.round(oneMonthDa));
				totalContributionModel.setEmprContribution((int) Math.round(oneMonthEmployerContribution));
				totalContributionModel.setContribution((int) Math.round(oneMonthEmployeeContribution));

			} else {
				Calendar currentCal = (Calendar) startCal.clone();

				while (currentCal.before(endCal) || currentCal.equals(endCal)) {
					int monthDays = currentCal.getActualMaximum(Calendar.DAY_OF_MONTH);
					int daysInRange;

					if (currentCal.get(Calendar.YEAR) == startCal.get(Calendar.YEAR)
							&& currentCal.get(Calendar.MONTH) == startCal.get(Calendar.MONTH)) {
						daysInRange = monthDays - startCal.get(Calendar.DAY_OF_MONTH) + 1;
					} else if (currentCal.get(Calendar.YEAR) == endCal.get(Calendar.YEAR)
							&& currentCal.get(Calendar.MONTH) == endCal.get(Calendar.MONTH)) {
						daysInRange = endCal.get(Calendar.DAY_OF_MONTH);
					} else {
						daysInRange = monthDays;
					}

					double oneMonthBasic = (basicPay * daysInRange) / monthDays;

					Integer percentageRate = paybillHeadMpgRepo.getDaPercentageByMonthYear(
							dateFormat.format(currentCal.getTime()), Integer.parseInt(payCommission), allowDeducCode);

					double daRate = 0.01 * percentageRate.doubleValue();
					double oneMonthDa = (oneMonthBasic * daRate);

					double oneMonthEmployeeContribution = (Math.ceil(oneMonthBasic) + Math.round(oneMonthDa)) * 0.10;

					double oneMonthEmployerContribution = 0d;
					// if ((Integer.parseInt(finYearId) <= 20 && Integer.parseInt(monthId) <= 3) ||
					// Integer.parseInt(finYearId) < 20) {
					if ((endCal.get(Calendar.YEAR) <= 2019 && startCal.get(Calendar.MONTH) <= 3)
							|| endCal.get(Calendar.YEAR) < 2019) {
						oneMonthEmployerContribution = (Math.ceil(oneMonthBasic) + Math.round(oneMonthDa)) * 0.14;
					} else {
						oneMonthEmployerContribution = (Math.ceil(oneMonthBasic) + Math.round(oneMonthDa)) * 0.14;
					}

					totalDa = totalDa + oneMonthDa;
					totalBasicPay = totalBasicPay + oneMonthBasic;
					totalEmployeeContribution = totalEmployeeContribution + oneMonthEmployeeContribution;
					totalEmployerContribution = totalEmployerContribution + oneMonthEmployerContribution;
					totalContributionModel.setDaRate(percentageRate);
					currentCal.add(Calendar.MONTH, 1);
				}

				totalContributionModel.setDa((int) Math.round(totalDa));
				totalContributionModel.setContribution((int) Math.round(totalEmployeeContribution));
				totalContributionModel.setEmprContribution((int) Math.round(totalEmployerContribution));
				totalContributionModel.setBasicPay((int) Math.round(totalBasicPay));

			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return totalContributionModel;
	}

	@Override
	public double findSumContribution(String sevaarthId, String paymentType, Integer monthId, Integer yearId,
			String componentName) {
		return paybillHeadMpgRepo.findSumContribution(sevaarthId, paymentType, monthId, yearId, componentName);
	}

	@Override
	public List<CmnLocationMst> findTreasuryList(OrgUserMst messages) {
		List<Object[]> lst = onlineContributionRepo.findTreasuryList(messages);
		List<CmnLocationMst> lstCmnLocationMst = new ArrayList<>();
		for (Object object[] : lst) {
			CmnLocationMst cmnLocationMst = new CmnLocationMst();
			cmnLocationMst.setLocId(Long.valueOf(object[0].toString()));
			cmnLocationMst.setLocName(object[1].toString());
			lstCmnLocationMst.add(cmnLocationMst);
		}
		return lstCmnLocationMst;
	}

	@Override
	public List<MstDcpsBillGroup> findBillgroupList(OrgUserMst messages, Integer regStatus) {
		// TODO Auto-generated method stub
		return onlineContributionRepo.findBillgroupList(messages, regStatus);
	}

	@Override
	public List<OrgDdoMst> getAllForwardedDdo(OrgUserMst messages) {
		List<Object[]> lst = onlineContributionRepo.getAllForwardedDdo(messages);
		List<OrgDdoMst> lstOrgDdoMst = new ArrayList<>();
		for (Object object[] : lst) {
			OrgDdoMst orgDdoMst = new OrgDdoMst();
			orgDdoMst.setDdoCode(object[0].toString());
			orgDdoMst.setDdoName(object[1].toString());
			lstOrgDdoMst.add(orgDdoMst);
		}
		return lstOrgDdoMst;
	}

	@Override
	public Long approveOnlineContriEntry(DcpContributionModel dcpContributionModel, OrgUserMst messages) {
		  List<Long> idsToDelete = dcpContributionModel.getDeleteDcpContributionId();
	        if (idsToDelete != null && !idsToDelete.isEmpty()) {
	            onlineContributionRepo.deleteContributionIds(idsToDelete);
	        }
		MstDcpsContriVoucherDtlEntity mstDcpsContriVoucherDtlEntity = onlineContributionRepo
				.findMstDcpsContriVoucherDtlEntity(dcpContributionModel).orElseGet(MstDcpsContriVoucherDtlEntity::new);
		mstDcpsContriVoucherDtlEntity.setBillGroupId(dcpContributionModel.getBillGroupId());
		mstDcpsContriVoucherDtlEntity.setCreatedDate(new Timestamp((new Date().getTime())));
		mstDcpsContriVoucherDtlEntity.setCreatedPostId(dcpContributionModel.getCreatedPostId());
		mstDcpsContriVoucherDtlEntity.setDdoCode(dcpContributionModel.getDdoCode());
		mstDcpsContriVoucherDtlEntity.setMonthId(dcpContributionModel.getMonthId());
		mstDcpsContriVoucherDtlEntity.setYearId(dcpContributionModel.getFinYearId());
		// mstDcpsContriVoucherDtlEntity.setStatus('1');
		mstDcpsContriVoucherDtlEntity.setTreasuryCode(dcpContributionModel.getTreasuryCode());

		if(mstDcpsContriVoucherDtlEntity.getMstDcpsContriVoucherDtls()!=null) {
			mstDcpsContriVoucherDtlEntity.setUpdatedDate(new Timestamp(new Date().getTime()));
			mstDcpsContriVoucherDtlEntity.setUpdatedUserId(messages.getUserId());
			mstDcpsContriVoucherDtlEntity.setUpdatedPostId(messages.getUserId());
		}
		
		Long save = onlineContributionRepo.saveMstDcpsContriVoucherDtlEntity(mstDcpsContriVoucherDtlEntity);

		if(dcpContributionModel.getLstDcpContributionModel()!=null) {
			for (DcpContributionModel dcpContributionModel1 : dcpContributionModel.getLstDcpContributionModel()) {
				DcpsContributionEntity dcpsContributionEntity = onlineContributionRepo
						.findDcpsContri(dcpContributionModel1.getDcpContributionId()).orElseGet(DcpsContributionEntity::new);
			
				dcpsContributionEntity.setRegStatus(1);
				if(dcpsContributionEntity.getDcpContributionId()!=null) {
					dcpsContributionEntity.setUpdatedDate(new Timestamp(new Date().getTime()));
					dcpsContributionEntity.setUpdatedUserId(messages.getUserId());
					dcpsContributionEntity.setUpdatedPostId(messages.getUserId());
				}
				onlineContributionRepo.saveDcpsContributionEntity(dcpsContributionEntity);
			}
		}
		return save;
	}

	@Override
	public Integer addDcpsVoucherDtl(Map<String, String> formData, OrgUserMst messages) {
		// TODO Auto-generated method stub
		onlineContributionRepo.addMstDcpsContriVoucherDtlEntityVoucherDtl(formData,messages);
		onlineContributionRepo.addDcpsContributionEntityVoucherDtl(formData,messages);
		return 1;
	}

	@Override
	public MstDcpsContriVoucherDtlEntity findMstDcpsContriVoucherDtlEntity(
			DcpContributionModel dcpContributionModel) {
		MstDcpsContriVoucherDtlEntity mstDcpsContriVoucherDtlEntity = onlineContributionRepo
				.findMstDcpsContriVoucherDtlEntity(dcpContributionModel).orElseGet(MstDcpsContriVoucherDtlEntity::new);
		
		return onlineContributionRepo
				.findMstDcpsContriVoucherDtlEntity(dcpContributionModel).orElseGet(MstDcpsContriVoucherDtlEntity::new);
	}

	@Override
	public Integer rejectContribution(Map<String, String> formData, OrgUserMst messages) {
		return onlineContributionRepo.rejectContribution(formData,messages);
	}
}

/*
 * public Map<Long, Double> getDCPValues(String sevaarthId, Long monthId, Long
 * finYearId) { Map<Long, Double> contributionMap = new HashMap<>();
 * 
 * // Query for each type of payment String[] paymentTypes = {"700046",
 * "700047", "700048", "700049"}; for (String paymentType : paymentTypes) {
 * List<Double> results = repository.getContributions(sevaarthId, monthId,
 * finYearId, paymentType); if (results != null && results.size() == 2) { Double
 * contribution = results.get(0); Double contributionEmpr = results.get(1);
 * contributionMap.put(Long.valueOf(paymentType), contribution != null ?
 * contribution : 0.0); // You can also store contributionEmpr if needed } }
 * 
 * return contributionMap; }
 * 
 * @Query("SELECT SUM(d.contribution), SUM(d.contributionEmpr) " +
 * "FROM DCPContribution d " + "WHERE d.sevaarthId = :sevaarthId " +
 * "AND d.monthId = :monthId " + "AND d.finYearId = :finYearId " +
 * "AND d.typeOfPayment = :typeOfPayment " + "AND d.regStatus = '1'")
 */
