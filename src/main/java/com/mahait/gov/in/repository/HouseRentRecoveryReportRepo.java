package com.mahait.gov.in.repository;

import java.util.List;

public interface HouseRentRecoveryReportRepo {

	List<Object[]> findHRRDetails(Integer yearId, Integer monthId, Long billGroup, String ddoCode);

}
