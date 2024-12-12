package com.mahait.gov.in.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.entity.MstDesignationEntity;


@Repository
public interface MstDesignationRepository extends JpaRepository<MstDesignationEntity, Long>{
	
    @Query("SELECT d FROM MstDesignationEntity d WHERE LOWER(d.desgination) LIKE LOWER(CONCAT(:desgn, '%'))")
    List<MstDesignationEntity> findByDsgnNameIgnoreCaseContaining(@Param("desgn") String desgn);

	MstDesignationEntity findByDesginationId(Long lLngDsgnId);

}
