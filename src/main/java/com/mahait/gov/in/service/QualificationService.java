package com.mahait.gov.in.service;

import java.util.List;

import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.entity.QualificationEntity;

import jakarta.validation.Valid;

public interface QualificationService {

	String findQualificationByIdForDelete(long id);

	Object findQualificationbyidForEdit(long id);

	String saveEditQualification(@Valid QualificationEntity qualificationEntity, OrgUserMst orgUserMst);

	Object saveQualification(QualificationEntity qualificationEntity);

	List<QualificationEntity> lstAllQualification();

}
