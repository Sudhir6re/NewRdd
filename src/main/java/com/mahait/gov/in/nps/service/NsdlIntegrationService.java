package com.mahait.gov.in.nps.service;

import java.util.List;

import com.mahait.gov.in.nps.entity.MstEmployeeNPSEntity;
import com.mahait.gov.in.nps.entity.TrnNpsRegFileEntity;

public interface NsdlIntegrationService {

	public List<TrnNpsRegFileEntity> getBatchList();

	public List<MstEmployeeNPSEntity> findNpsEmpDtlById(int id);

}
