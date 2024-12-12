package com.mahait.gov.in.nps.service;
import java.util.List;

import com.mahait.gov.in.nps.model.NSDLDetailsModel;

public interface NsdlContriService {

	List<NSDLDetailsModel> getNsdlContriDetails(Integer monthId, Integer yearId, int role_id, String userName);

	List<NSDLDetailsModel> getNsdlContriDetailsDivisionWise(Integer month, Integer year, int role_id, String userName);

	List<NSDLDetailsModel> getNsdlContriDetailsDeptWise(int deptCode, int month, int year);

}
