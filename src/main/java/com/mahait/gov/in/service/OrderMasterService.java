package com.mahait.gov.in.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.mahait.gov.in.entity.CmnTalukaMst;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.EmpWiseCityClassModel;
import com.mahait.gov.in.model.MstGrOrderModel;

public interface OrderMasterService {

	List<EmpWiseCityClassModel> findAllEmployee(String userName);

	List<Object[]> getSubGRType(long l);

	String getDistrictId(String ddoCode);

	List<CmnTalukaMst> gettalukalst(String districtId);

	List<Long> findUsertype(String ddoCode);

	Long saveMstGrOrder(MstGrOrderModel mstGrOrderModel, MultipartFile[] files, OrgUserMst messages);

	List<Long> getSubDDOs(Long postId);

	List<MstGrOrderModel> getsancOrderLst(String locationcodeArray);

	Object getddoOff(String locationcodeArray);

	List<MstGrOrderModel> getInstitutionLst(String ddoCode);

	
}
