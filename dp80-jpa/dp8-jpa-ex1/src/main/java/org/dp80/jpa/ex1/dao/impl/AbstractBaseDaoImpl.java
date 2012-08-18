
package org.dp80.jpa.ex1.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.dp80.jpa.ex1.dao.BaseDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public abstract class AbstractBaseDaoImpl<T> implements BaseDao<T> {
	
	@PersistenceContext
	protected EntityManager	entityManager;
	
	@Override
	@Transactional
	public void save(T entity) {
	
		this.entityManager.persist(entity);
	}
	
	@Override
	public T get(Long id) {
	
		return this.entityManager
				.getReference(this.getEntityClass(), id);
	}
	
	@Override
	public List<T> getAll() {
	
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(this.getEntityClass());
		Root<T> root = criteriaQuery.from(this.getEntityClass());
		criteriaQuery.select(root);
		
		return this.entityManager.createQuery(criteriaQuery).getResultList();
	}

}
