package com.mahait.gov.in.repository;

import java.util.List;

public interface GPFAbstractReportRepo {

	List<Object[]> findgpfRptDtls(Integer monthId, Integer yearId, Long billGroup);

}
