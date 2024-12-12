package com.mahait.gov.in.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.entity.QualificationEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class QualificationRepositoryImpl implements QualificationRepository {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public QualificationEntity findQualificationByidForDelete(long id) {
		QualificationEntity objMenu = null;
		Session currentSession = entityManager.unwrap(Session.class);
		objMenu = currentSession.get(QualificationEntity.class, id);
		return objMenu;
	}

	@Override
	public void deleteQualification(QualificationEntity objMenu) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.delete(objMenu);

	}

	@Override
	public QualificationEntity findQualificationByIdForEdit(long id) {
		QualificationEntity objMenu = null;
		Session currentSession = entityManager.unwrap(Session.class);
		objMenu = currentSession.get(QualificationEntity.class, id);
		return objMenu;
	}

	@Override
	public void updateQualification(QualificationEntity objMenu) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.update(objMenu);
	}

	@Override
	public List<QualificationEntity> lstAllQualification() {
		Session currentSession = entityManager.unwrap(Session.class);
		String hql = "SELECT * FROM Qualification";
		NativeQuery<QualificationEntity> query = currentSession.createNativeQuery(hql, QualificationEntity.class);
		return query.list();
	}

	@Override
	public QualificationEntity save(QualificationEntity qualificationEntity) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(qualificationEntity);
		currentSession.flush();
		currentSession.clear();
		return qualificationEntity;
	}

}
