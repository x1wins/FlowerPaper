package net.changwoo.x1wins.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import net.changwoo.x1wins.service.FileService;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * @author JOKe
 * @param <E>
 * @param <PK>
 */
public abstract class GenericDaoImpl<E, PK extends Serializable> extends
		HibernateDaoSupport implements GenericDao<E, PK> {
	
	private static final Logger logger = LoggerFactory
			.getLogger(GenericDaoImpl.class);
	
	@SuppressWarnings("unchecked")
	protected GenericDaoImpl(SessionFactory sf) {
		setSessionFactory(sf);
	}
	
	@SuppressWarnings("unchecked")
	public PK save(E newInstance) {
		return (PK) getHibernateTemplate().save(newInstance);
	}

	@SuppressWarnings("unchecked")
	public E findById(PK id) {
		return (E) getHibernateTemplate().get(getEntityClass(), id);
	}

	@SuppressWarnings("unchecked")
	public List<E> findAll() {
		return getHibernateTemplate().findByCriteria(createDetachedCriteria());
	}

	@SuppressWarnings("unchecked")
	public List<E> findAllByProperty(String propertyName, Object value) {
		DetachedCriteria criteria = createDetachedCriteria();
		criteria.add(Restrictions.eq(propertyName, value));
		return getHibernateTemplate().findByCriteria(criteria);
	}
	
	@SuppressWarnings("unchecked")
	public List<E> findAllByProperty(List<Map> list) throws Exception {
		
		DetachedCriteria criteria = createDetachedCriteria();
		for(int i=0; i<list.size(); i++){
			
			Map<String, Object> map = (Map<String, Object>) list.get(i);
			String propertyName = (String) map.get("propertyName");
			Object value = map.get("value");
			
			logger.debug("propertyName "+ propertyName + "value "+ value +" count "+list.size());
			
			criteria.add(Restrictions.eq(propertyName, value));
			
		}
		return getHibernateTemplate().findByCriteria(criteria);
	}

	public List<E> findByExample(E object) {
		List<E> resultList = getHibernateTemplate().findByExample(object, 0, 1);
		return resultList;
	}

	public List<E> findByExample(E object, int firstResult, int maxResults) {
		List<E> resultList = getHibernateTemplate().findByExample(object,
				firstResult, maxResults);
		return resultList;
	}

	public void update(E transientObject) {
		getHibernateTemplate().update(transientObject);
	}

	public void saveOrUpdate(E transientObject) {
		getHibernateTemplate().saveOrUpdate(transientObject);
	}

	public void delete(E persistentObject) {
		getHibernateTemplate().delete(persistentObject);
	}

	protected abstract Class<E> getEntityClass();

	protected DetachedCriteria createDetachedCriteria() {
		return DetachedCriteria.forClass(getEntityClass());
	}
}