package com.mahait.gov.in.repository;

import java.io.Serializable;
import java.util.List;

public interface NewRegDDORepo {

	List<Object[]> findAllEmployee(String userName);

	///MstEmployeeEntity findEmpData(Integer employeeId);

	///Serializable updateheadOfAccCodeHistory(HeadOfAccCodeHistory headOfAccCodeHistory);

	///Serializable headofAccCode(MstEmployeeEntity mstEmployeeEntity);

	///List<MstCityClassEntity> findCityClasssLst();

	///List<MstDistrictEntity> lstGetAllDistrict();

	List<Object[]> getTalukaByDistrictId(int districtId);

	///List<MstTalukaEntity> lstGetAllTaluka();

	///Serializable updateCityClass(MstEmployeeEntity mstEmployeeEntity);

	List<Object[]> findLvl1DDOCode(String userName);

	List<Object[]> findEmpLst(String userName);

}
