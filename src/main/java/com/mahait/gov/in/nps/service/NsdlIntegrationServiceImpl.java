package com.mahait.gov.in.nps.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mahait.gov.in.nps.entity.MstEmployeeNPSEntity;
import com.mahait.gov.in.nps.entity.TrnNpsRegFileEntity;
import com.mahait.gov.in.nps.repository.NsdlIntegrationRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class NsdlIntegrationServiceImpl implements NsdlIntegrationService{
	@Autowired
	NsdlIntegrationRepo  nsdlIntegrationRepo; 

	@Override
	public List<TrnNpsRegFileEntity> getBatchList() {
		return nsdlIntegrationRepo.getBatchList();
	}

	@Override
	public List<MstEmployeeNPSEntity> findNpsEmpDtlById(int id) {
		return nsdlIntegrationRepo.findNpsEmpDtlById(id);
	}

}
