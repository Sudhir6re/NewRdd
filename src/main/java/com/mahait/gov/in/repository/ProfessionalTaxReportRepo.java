package com.mahait.gov.in.repository;

import java.util.List;

public interface ProfessionalTaxReportRepo {

	List<Object[]> findProfessionalTaxDtls(Integer yearId, Integer monthId, Long billGroup, String ddoCode);

}
