package com.mahait.gov.in.repository;

import java.util.List;

public interface LocationMasterRepo {

	List<Object[]> findAllStates(int countryId);

	List<Object[]> findAllDistricts(long stateId);

}
