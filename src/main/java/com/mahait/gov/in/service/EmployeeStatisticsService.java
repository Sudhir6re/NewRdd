package com.mahait.gov.in.service;

import java.util.List;

import com.mahait.gov.in.model.EmployeeStatisticsModel;

public interface EmployeeStatisticsService {

	public List<EmployeeStatisticsModel> findEmployeeStatistics(String DDOCode,int roleId);
}
