package com.mahait.gov.in.repository;

import java.util.List;

public interface IncomeTaxReportRepo {

	List<Object[]> findIncomeTaxDtls(Integer yearId, Integer monthId, Long billGroup, String ddoCode);

}
