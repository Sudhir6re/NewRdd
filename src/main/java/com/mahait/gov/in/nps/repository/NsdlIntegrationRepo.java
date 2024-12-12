package com.mahait.gov.in.nps.repository;

import java.util.List;

import com.mahait.gov.in.nps.entity.MstEmployeeNPSEntity;
import com.mahait.gov.in.nps.entity.TrnNpsRegFileEntity;

public interface NsdlIntegrationRepo {

	public List<TrnNpsRegFileEntity> getBatchList();

	public List<MstEmployeeNPSEntity> findNpsEmpDtlById(int id);

}
