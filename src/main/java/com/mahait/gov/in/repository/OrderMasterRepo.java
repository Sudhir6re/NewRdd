package com.mahait.gov.in.repository;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.mahait.gov.in.entity.CmnTalukaMst;
import com.mahait.gov.in.entity.GROrderDocumentEntity;
import com.mahait.gov.in.entity.HrPayOrderMst;

public interface OrderMasterRepo {

	List<Object[]> findAllEmployee(String userName);

	List<Object[]> getSubGRType(long parentGrpId);

	String getDistrictId(String ddoCode);

	List<CmnTalukaMst> gettalukalst(String districtId);

	List<Long> findUsertype(String ddoCode);

	Integer saveGrOrder(HrPayOrderMst payOrderMst, MultipartFile[] files);

	Long saveAdvanceDocuments(GROrderDocumentEntity grOrderDocumentEntity);

	List<Long> getSubDDOs(Long postId);

	List<Object[]> getsancOrderLst(String ddo);

	List<Object[]> getddoOff(String locationcodeArray);

	List<Object[]> getInstitutionLst(String ddoCode);

	Long saveMstGrOrder(HrPayOrderMst payOrderMst);

}
