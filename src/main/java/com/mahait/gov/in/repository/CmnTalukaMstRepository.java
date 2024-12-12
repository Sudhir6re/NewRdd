package com.mahait.gov.in.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.entity.CmnTalukaMst;
import com.mahait.gov.in.entity.OrgUserMst;

@Repository
public interface CmnTalukaMstRepository extends JpaRepository<CmnTalukaMst, Long> {

	List<CmnTalukaMst> findByDistrictIdOrderByTalukaName(Long districtId);
	
/*    List<Object[]> retrieveDistrictOfficeList(OrgUserMst messages, Long ofcId);
*/



}
