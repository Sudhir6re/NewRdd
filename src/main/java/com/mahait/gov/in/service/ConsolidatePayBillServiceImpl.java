package com.mahait.gov.in.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.entity.ConsolidatePayBillTrnEntity;
import com.mahait.gov.in.entity.ConsolidatePayBillTrnMpgEntity;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.entity.PaybillGenerationTrnEntity;
import com.mahait.gov.in.model.ConsolidatePayBillModel;
import com.mahait.gov.in.repository.ConsolidatePayBillRepo;

@Service
@Transactional
public class ConsolidatePayBillServiceImpl implements ConsolidatePayBillService {

	@Autowired
	ConsolidatePayBillRepo consolidatePayBillRepo;

	@Override
	public List<ConsolidatePayBillModel> fetchDDOLst(String ddoCode) {

		List<Object[]> lstprop = consolidatePayBillRepo.fetchDDOLst(ddoCode);
		List<ConsolidatePayBillModel> lstObj = new ArrayList<>();
		if (!lstprop.isEmpty()) {
			for (Object[] objLst : lstprop) {
				ConsolidatePayBillModel obj = new ConsolidatePayBillModel();
				// c.ddo_code,a.off_name
				obj.setDdoCode(StringHelperUtils.isNullString(objLst[0]));
				obj.setOfficeName(StringHelperUtils.isNullString(objLst[1]));

				lstObj.add(obj);
			}
		}
		return lstObj;

	}

	@Override
	public List<ConsolidatePayBillModel> searchConsolidatedPaybill(ConsolidatePayBillModel consolidatePayBillModel,
			OrgUserMst messages) {
		
		List<ConsolidatePayBillModel> lstObj = new ArrayList<>();
		
		for(ConsolidatePayBillModel model : consolidatePayBillModel.getLstCons()) {
			if(model.getCheckboxid()!=null) {
			if(model.getCheckboxid()==true) {
				
				List<Object[]> lstprop = consolidatePayBillRepo.fetchConsolidateDetails(model.getDdoCode(),consolidatePayBillModel.getMonthName(),consolidatePayBillModel.getYearName(),consolidatePayBillModel.getSchemeCode());
					if (!lstprop.isEmpty()) {
						for (Object[] objLst : lstprop) {
							ConsolidatePayBillModel obj = new ConsolidatePayBillModel();
							obj.setDdoCode(StringHelperUtils.isNullString(objLst[0]));
							obj.setBillNo(StringHelperUtils.isNullLong(objLst[1]));
							obj.setBillDesc(StringHelperUtils.isNullString(objLst[2]));
							obj.setBillGrossAmt(StringHelperUtils.isNullDouble(objLst[3]));
							obj.setBillNetAmt(StringHelperUtils.isNullDouble(objLst[4]));
							lstObj.add(obj);
						}
					}
				}
			}
			}
		return lstObj;
		
	}

	@Override
	public int saveConsolidatePayBill(ConsolidatePayBillModel consolidatePayBillModel, OrgUserMst messages) {
		
		Serializable id =0;
		Double sumGrossAmount=0d;
		Double sumNetAmount=0d;
		Double totalITamt=0d;
		Double totalGisZp=0d;
		Double incomeTax=0d;
		Double totaldcpsArr=0d;
		Double totalgis=0d;
		Double totalpt=0d;
		Double totalaccPolicy=0d;
		Double totalHrr=0d;
		Double totalDeduc=0d;
		Double totalpf=0d;
		Double dcpsarr=0d;
		Double gis=0d;
		Double gisZp=0d;
		Double pt=0d;
		Double accpolicy=0d;
		Double hrr=0d;
		Double deduc=0d;
		Double pf=0d;
		Long consolidatedPaybillTrnId=0l;
		
		ConsolidatePayBillTrnEntity objEntity = new ConsolidatePayBillTrnEntity();
		
		consolidatedPaybillTrnId = consolidatePayBillRepo.getConsolidateTrnId();
		Integer noOfEmployee=0;
		for (ConsolidatePayBillModel model : consolidatePayBillModel.getLstCons()) {
			
			sumGrossAmount +=model.getBillGrossAmt();
			sumNetAmount +=model.getBillNetAmt();
			
		
			List<PaybillGenerationTrnEntity> lst =consolidatePayBillRepo.getPaybillDtls(consolidatePayBillModel.getMonthName(),consolidatePayBillModel.getYearName(),model.getDdoCode());
			
			for (PaybillGenerationTrnEntity paybillGenerationTrnEntity : lst) {
				
				List<Object[]> lstpaybilldtls = consolidatePayBillRepo.fetchbilldts(lst.get(0).getPaybillGenerationTrnId()); 
				
				ConsolidatePayBillTrnMpgEntity consolidateTrnMpg = new ConsolidatePayBillTrnMpgEntity();
				for (Object[] obj : lstpaybilldtls) {
					noOfEmployee++;
						incomeTax =  StringHelperUtils.isNullDouble(obj[0]);
						totalITamt+=incomeTax;
					
						if(obj[1]!=null) {
							
							
							if(obj[1] instanceof Double) {
								dcpsarr= StringHelperUtils.isNullDouble(obj[1]);
							}else if(obj[1] instanceof Long) {
								dcpsarr= StringHelperUtils.isNullLong(obj[1]).doubleValue();
							}
							
							totaldcpsArr+=dcpsarr;
						}
						
							
						if(obj[2]!=null) {
							 gis= StringHelperUtils.isNullDouble(obj[2]);
								totalgis+=gis;
						}
						
										
						if(obj[3]!=null) {
							pt= StringHelperUtils.isNullDouble(obj[3]);
							totalpt+=pt;
						}
						
									
						if(obj[4]!=null) {
							accpolicy= StringHelperUtils.isNullBigDecimal(obj[4]).doubleValue();
							totalaccPolicy+=accpolicy;
						}
						
					
						
						if( obj[5]!=null) {
							hrr= StringHelperUtils.isNullDouble(obj[5]);
							totalHrr+=hrr;
							
						}
						if(obj[6]!=null) {
							deduc=(StringHelperUtils.isNullDouble(obj[6]));
							totalDeduc+=deduc;
						}
						
					
						
						if(obj[7]!=null) {
							pf= StringHelperUtils.isNullLong(obj[7]).doubleValue();
							totalpf+=pf;
						}
						
						
						if(obj[8]!=null) {
							if(obj[1] instanceof Long) {
								gisZp= StringHelperUtils.isNullLong(obj[8]).doubleValue();
								totalGisZp+=gisZp;
							}else if(obj[1] instanceof BigInteger) {
								gisZp= StringHelperUtils.isNullBigInteger(obj[8]).doubleValue();
								totalGisZp+=gisZp;
							}
							
							
							
						}
						
										
					
				}
				
				System.out.println("consolidate trn id---"+consolidatePayBillRepo.getConsolidateTrnId());
				consolidateTrnMpg.setConsolidatePaybillTrnId(consolidatedPaybillTrnId);
				consolidateTrnMpg.setDdoCode(model.getDdoCode());
				consolidateTrnMpg.setPaybillGenerationTrnId(paybillGenerationTrnEntity.getPaybillGenerationTrnId());
				paybillGenerationTrnEntity.setIsActive(9);
				id = consolidatePayBillRepo.saveConsolidatePayBillTrnMpg(consolidateTrnMpg);
				id = consolidatePayBillRepo.saveDtlsPaybillTrn(paybillGenerationTrnEntity);
				
			}
		}
		objEntity.setIt(totalITamt);
		objEntity.setDcpsArr(totaldcpsArr);
		objEntity.setIt(totalITamt);
		objEntity.setGis(totalgis);
		objEntity.setPt(totalpt);
		objEntity.setAccPolicy(totalaccPolicy);
		objEntity.setHrr(totalHrr);
		objEntity.setGisZp(totalGisZp);
		objEntity.setTotalDeduct(totalDeduc);
		objEntity.setPf(totalpf);
		objEntity.setCreatedDate(new Date());
		objEntity.setCreatedUserId(messages.getUserId());
		objEntity.setGrossAmt(sumGrossAmount);
		objEntity.setNetAmt(sumNetAmount);
		objEntity.setPaybillMonth(consolidatePayBillModel.getMonthName());
		objEntity.setPaybillYear(consolidatePayBillModel.getYearName());
		objEntity.setDdoCode(messages.getDdoCode());
		objEntity.setSchemeCode(consolidatePayBillModel.getSchemeCode());
		objEntity.setConsolidatePaybillTrnId(consolidatedPaybillTrnId);
		objEntity.setNoOfEmployee(noOfEmployee);
		objEntity.setIsActive(9);
		id = consolidatePayBillRepo.saveConsolidatePayBill(objEntity);
		
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PaybillGenerationTrnEntity rejectConsolidatePaybill(String ddoCode, Long billNo) {
		// TODO Auto-generated method stub
	
		PaybillGenerationTrnEntity paybillGenerationTrnEntity = consolidatePayBillRepo.findPaybillDtls(ddoCode,billNo);
		if (paybillGenerationTrnEntity != null) {
			paybillGenerationTrnEntity.setIsActive(7);

			consolidatePayBillRepo.updaterejectConsolidateStatus(paybillGenerationTrnEntity);
			
		}
		return paybillGenerationTrnEntity;
	
		

	}

	@Override
	public List<ConsolidatePayBillModel> fetchDDOLstForConsolidateApproval(String ddoCode) {

		List<Object[]> lstprop = consolidatePayBillRepo.fetchDDOLstForConsolidateApproval(ddoCode);
		List<ConsolidatePayBillModel> lstObj = new ArrayList<>();
		if (!lstprop.isEmpty()) {
			for (Object[] objLst : lstprop) {
				ConsolidatePayBillModel obj = new ConsolidatePayBillModel();
				BigInteger consolidateId = (BigInteger) objLst[0];
				obj.setConsolidateId(consolidateId.longValue());
				obj.setSchemeCode(StringHelperUtils.isNullString(objLst[1]));
				obj.setSubSchemeName(StringHelperUtils.isNullString(objLst[2]));
				obj.setBillGrossAmt(StringHelperUtils.isNullDouble(objLst[3]));
				obj.setBillNetAmt(StringHelperUtils.isNullDouble(objLst[4]));

				lstObj.add(obj);
			}
		}
		return lstObj;

	}

	@Override
	public int approveConsolidateBill(Long consolidateId) {
		Serializable	id = consolidatePayBillRepo.updateConsolidateapproveStatus(consolidateId);
		return (int) id;
	}

}
