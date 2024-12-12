package com.mahait.gov.in.repository;

import java.util.List;

public interface EmployeeStatisticsRepo {
	
	public List<Object[]> findEmployeeStatistics(String DDOCode,int roleId);

}
