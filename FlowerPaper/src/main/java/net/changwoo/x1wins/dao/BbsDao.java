package net.changwoo.x1wins.dao;

import java.util.List;

import net.changwoo.x1wins.entity.Bbs;
import net.changwoo.x1wins.entity.Reply;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class BbsDao extends GenericDaoImpl<Bbs, Integer> {


	private static final Logger logger = LoggerFactory
			.getLogger(BbsDao.class);

	
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
		detachedCriteria.setResultTransformer(DetachedCriteria.DISTINCT_ROOT_ENTITY);
		detachedCriteria.add(Restrictions.eq("C.bbsnum", bbsnum)).addOrder(Order.desc("B.num"));
		return detachedCriteria;
		
		
//		return getSession().createCriteria(Bbs.class, "B").createCriteria("config", "C")
//				.add(Restrictions.eq("C.bbsnum", bbsnum)).addOrder(Order.desc("B.num"));
	}
	
	public Bbs findBbsDetailById(int id) {

		Bbs bbs = null;
		try{
			
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Bbs.class, "B");
//			detachedCriteria.createCriteria("replys","R");//if subclass not exist not is null exception
			detachedCriteria.add(Restrictions.eq("B.num", id));
//			detachedCriteria.addOrder(Order.desc("R.rnum"));//subclass orderby not woring
			bbs = (Bbs) getHibernateTemplate().findByCriteria(detachedCriteria).get(0);

			for (Reply replys : bbs.getReplys()) {
				logger.debug("replys.getRnum() : " + replys.getRnum());
			}
			
		} catch (Exception e) {
			logger.debug(e.toString());
		}
		
		return bbs;
	}
	
	public List findList(int bbsnum) throws Exception{
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Bbs.class, "B");
		detachedCriteria.setResultTransformer(DetachedCriteria.DISTINCT_ROOT_ENTITY);
		detachedCriteria.createCriteria("config", "C");
		detachedCriteria.add(Restrictions.eq("C.bbsnum", bbsnum)).addOrder(Order.desc("B.num"));
		
//		int startRow = (pageNum-1)*perPage;
//		logger.debug("startRow : "+String.valueOf(startRow));
//		List<Bbs> list = getHibernateTemplate().findByCriteria(detachedCriteria, startRow, perPage);
		List<Bbs> list = getHibernateTemplate().findByCriteria(detachedCriteria);
		
//		if(list.size()<perPage){
//			list = getHibernateTemplate().findByCriteria(detachedCriteria, startRow, perPage+perPage-list.size());
//		}
		
		return list;
		
	}
}
