package com.mahait.gov.in.repository;

import java.util.List;

import com.mahait.gov.in.entity.QualificationEntity;

public interface QualificationRepository {

	QualificationEntity findQualificationByidForDelete(long id);

	void deleteQualification(QualificationEntity objMenu);

	QualificationEntity findQualificationByIdForEdit(long id);

	void updateQualification(QualificationEntity objMenu);

	List<QualificationEntity> lstAllQualification();
	
	QualificationEntity save(QualificationEntity qualificationEntity);

}
