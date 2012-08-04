package net.changwoo.app.bbs.dao;

import java.util.List;

import net.changwoo.app.bbs.entity.Bbs;
import net.changwoo.app.common.dao.GenericDaoImpl;

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
		detachedCriteria.createCriteria("config", "C");
		detachedCriteria.add(Restrictions.eq("C.bbsnum", bbsnum)).addOrder(Order.desc("B.num"));
		return detachedCriteria;
		
	}
	
	public List findList(int bbsnum, int pageNum, int perPage) throws Exception{
		
		DetachedCriteria detachedCriteria = getBbsListCriteria(bbsnum, pageNum);
		int startRow = (pageNum-1)*perPage;
		List<Bbs> list = getHibernateTemplate().findByCriteria(detachedCriteria, startRow, perPage);
		return list;
		
	}
	
	public int findListSize(int bbsnum, int pageNum) throws Exception{
		
		DetachedCriteria detachedCriteria = getBbsListCriteria(bbsnum, pageNum);
		return getHibernateTemplate().findByCriteria(detachedCriteria).size();
	}
}
