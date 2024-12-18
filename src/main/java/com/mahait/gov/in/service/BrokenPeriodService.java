package com.mahait.gov.in.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mahait.gov.in.entity.BrokenPeriodEntity;
import com.mahait.gov.in.model.BrokenPeriodModel;
import com.mahait.gov.in.model.BrokenPeriodResponseModel;

public interface BrokenPeriodService {
	public List<BrokenPeriodResponseModel> brokenPeriod(BrokenPeriodModel brokenPeriodModel,List<BrokenPeriodResponseModel>  brokenPeriodResponseModel);
	public List<BrokenPeriodResponseModel>   calculateEmployeeSalary(String sevaarthid,List<BrokenPeriodResponseModel>  brokenPeriodResponseModel,HashMap hmInputParam);
	public BrokenPeriodEntity[] generateBrokenPeriodPayVOList(Map inputMap) throws Exception;
	public Map generateMap(Map inputMap);
	public List<BrokenPeriodResponseModel>   saveBrokenPeriodPay(String sevaarthid,List<BrokenPeriodResponseModel>  brokenPeriodResponseModel,Map hmInputParam);
	public String isEmpMappedWithBillGroup(String sevaarthId);
	public int getSevaarthIdMappedWithBill(String sevaarthid,int month,int year,String ddoName);
}
