package net.changwoo.x1wins.dao;

import java.util.List;

import net.changwoo.x1wins.entity.Bbs;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class BbsDao extends GenericDaoImpl<Bbs, Integer> {

	@Autowired
	BbsDao(SessionFactory sf) {
		super(sf);
	}

	@Override
	protected Class<Bbs> getEntityClass() {
		return Bbs.class;
	}
	
	
	private DetachedCriteria getBbsListCriteria(int bbsnum, int pageNum){
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Bbs.class, "B");
//		Criteria criteria = createCriteria(Bbs.class, "B");
		detachedCriteria.createCriteria("config", "C");
		detachedCriteria.add(Restrictions.eq("C.bbsnum", bbsnum)).addOrder(Order.desc("B.num"));
		return detachedCriteria;
		
		
//		return getSession().createCriteria(Bbs.class, "B").createCriteria("config", "C")
//				.add(Restrictions.eq("C.bbsnum", bbsnum)).addOrder(Order.desc("B.num"));
	}
	
	public List findList(int bbsnum, int pageNum, int perPage) throws Exception{
		
		DetachedCriteria detachedCriteria = getBbsListCriteria(bbsnum, pageNum);
		detachedCriteria.setResultTransformer(DetachedCriteria.DISTINCT_ROOT_ENTITY);
		int startRow = (pageNum-1)*perPage;
		List<Bbs> list = getHibernateTemplate().findByCriteria(detachedCriteria, startRow, perPage);
		
		return list;
		
	}
	
	public int findListSize(int bbsnum, int pageNum) throws Exception{
		
//		Criteria criteria = getBbsListCriteria(bbsnum, pageNum);
//		
//		return criteria.list().size();
		DetachedCriteria detachedCriteria = getBbsListCriteria(bbsnum, pageNum);
		
		return getHibernateTemplate().findByCriteria(detachedCriteria).size();
	}
}
