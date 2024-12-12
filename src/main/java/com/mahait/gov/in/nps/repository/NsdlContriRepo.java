package com.mahait.gov.in.nps.repository;

import java.util.List;

public interface NsdlContriRepo {

	List<Object[]> getNsdlContriDetails(Integer monthId, Integer yearId, int role_id, String userName);

	List<Object[]> getNsdlContriDetailsDivisionWise(Integer month, Integer year, int role_id, String userName);

	List<Object[]> getNsdlContriDetailsDeptWise(int deptCode, int month, int year);

}
