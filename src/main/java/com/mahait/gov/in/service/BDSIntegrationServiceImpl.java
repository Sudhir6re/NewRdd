package com.mahait.gov.in.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mahait.gov.in.entity.BeamsIntegrationEntity;
import com.mahait.gov.in.entity.ConsolidatePayBillTrnEntity;
import com.mahait.gov.in.entity.PaybillGenerationTrnEntity;
import com.mahait.gov.in.repository.BDSIntegrationRepo;

@Service
@Transactional
public class BDSIntegrationServiceImpl implements BDSIntegrationService {
	@Autowired
	BDSIntegrationRepo bdsintegrationrepo;
	public List<Object[]> getPaybillDtls(Long consolidatePaybillTrnId){			
		return (List<Object[]>) bdsintegrationrepo.getBillDtls(consolidatePaybillTrnId);
	}
	public PaybillGenerationTrnEntity getConsPaybillDtls(Long consolidatePaybillTrnId,String statusCode){
		PaybillGenerationTrnEntity objPaybillGenerationTrnEntity = bdsintegrationrepo.getConsBillDtls(consolidatePaybillTrnId);
		/*if (objPaybillGenerationTrnEntity != null) {
			objPaybillGenerationTrnEntity.setStatus(12); // Change Statement Forwarded
			objPaybillGenerationTrnEntity.setUpdatedDate(new Date());
			bdsintegrationrepo.updategetBillDtls(objPaybillGenerationTrnEntity);
		}*/
		
		if (objPaybillGenerationTrnEntity != null) {
			if(!statusCode.equals("00"))
			{
				//objPaybillGenerationTrnEntity.setStatus(11); // Change Statement Forwarded
				objPaybillGenerationTrnEntity.setIsActive(12);//approved by beams
			}
			//objPaybillGenerationTrnEntity.setStatus(12); // Change Statement Forwarded
			objPaybillGenerationTrnEntity.setIsActive(11);//rejected by beams
			objPaybillGenerationTrnEntity.setUpdatedDate(new Date());
			bdsintegrationrepo.updategetBillDtls(objPaybillGenerationTrnEntity);
		}
		
		return objPaybillGenerationTrnEntity;
	}
		
		
	public List<String> getData(BeamsIntegrationEntity beamsIntegrationEntity,List<String> lst){		
		return bdsintegrationrepo.getData(beamsIntegrationEntity,lst);
	}
	
	public List<Object[]> getheadcodePF(Long consolidatePaybillTrnId){			
		return (List<Object[]>) bdsintegrationrepo.getheadpf(consolidatePaybillTrnId);
	}
	@Override
	public boolean isEkuber(String ddoCode) {
		// TODO Auto-generated method stub
		return bdsintegrationrepo.isEkuber(ddoCode);
	}
	
	@Override
	public ConsolidatePayBillTrnEntity getConsolidatedPaybillDtls(Long consolidatePaybillTrnId, String statusCode) {

		ConsolidatePayBillTrnEntity consolidatePayBillTrnEntity = bdsintegrationrepo.getConsolidatedPaybillDtls(consolidatePaybillTrnId);
		/*if (objPaybillGenerationTrnEntity != null) {
			objPaybillGenerationTrnEntity.setStatus(12); // Change Statement Forwarded
			objPaybillGenerationTrnEntity.setUpdatedDate(new Date());
			bdsintegrationrepo.updategetBillDtls(objPaybillGenerationTrnEntity);
		}*/
		
		if (consolidatePayBillTrnEntity != null) {
			if(!statusCode.equals("00"))
			{
				//objPaybillGenerationTrnEntity.setStatus(11); // Change Statement Forwarded
				consolidatePayBillTrnEntity.setIsActive(12);//approved by beams
			}
			//objPaybillGenerationTrnEntity.setStatus(12); // Change Statement Forwarded
			consolidatePayBillTrnEntity.setIsActive(11);//rejected by beams
			consolidatePayBillTrnEntity.setUpdatedDate(new Date());
			bdsintegrationrepo.updategetBillDtls(consolidatePayBillTrnEntity);
		}
		
		return consolidatePayBillTrnEntity;
	
	}
	@Override
	public String getBEAMSDdoCode(String ddocode) {
		// TODO Auto-generated method stub
		return bdsintegrationrepo.getBEAMSDdoCode(ddocode) ;
	}
	
	
}
