package net.changwoo.x1wins.dao;

import java.util.List;

import net.changwoo.x1wins.entity.Reply;
import net.changwoo.x1wins.web.BbsController;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class ReplyDao extends GenericDaoImpl<Reply, Integer> {

    private static final Logger logger = LoggerFactory.getLogger(ReplyDao.class);

	
	@Autowired
	ReplyDao(SessionFactory sf) {
		super(sf);
	}

	@Override
	protected Class<Reply> getEntityClass() {
		return Reply.class;
	}
	
	
	public List<Reply> findReplyList(int bbsnum) throws Exception{
		
		/*
		 * Hibernate: select this_.rnum as rnum6_3_, this_.content as
		 * content6_3_, this_.ip as ip6_3_, this_.regdate as regdate6_3_,
		 * this_.status as status6_3_, this_1_.num as num7_3_, this_2_.num as
		 * num8_3_, b1_.num as num0_0_, b1_.content as content0_0_, b1_.count as
		 * count0_0_, b1_.ip as ip0_0_, b1_.regdate as regdate0_0_, b1_.status
		 * as status0_0_, b1_.subject as subject0_0_, b1_.userid as userid0_0_,
		 * b1_1_.bbsnum as bbsnum1_0_, config5_.bbsnum as bbsnum2_1_,
		 * config5_.bbsname as bbsname2_1_, config5_.listTypeNum as
		 * listType3_2_1_, config5_.publicYn as publicYn2_1_, config5_.readFrom
		 * as readFrom2_1_, config5_.readUntil as readUntil2_1_,
		 * config5_.replyYn as replyYn2_1_, config5_.userid as userid2_1_,
		 * config5_.writeFrom as writeFrom2_1_, config5_.writeUntil as
		 * writeUntil2_1_, u2_.num as num9_2_, u2_.content as content9_2_,
		 * u2_.content_type as content3_9_2_, u2_.email as email9_2_,
		 * u2_.filename as filename9_2_, u2_.filenum as filenum9_2_, u2_.level
		 * as level9_2_, u2_.name as name9_2_, u2_.password as password9_2_,
		 * u2_.phone as phone9_2_, u2_.regdate as regdate9_2_, u2_.status as
		 * status9_2_, u2_.userid as userid9_2_ from reply this_ left outer join
		 * bbs_reply this_1_ on this_.rnum=this_1_.rnum left outer join
		 * reply_user this_2_ on this_.rnum=this_2_.rnum inner join bbs b1_ on
		 * this_1_.num=b1_.num left outer join bbs_config b1_1_ on
		 * b1_.num=b1_1_.num left outer join config config5_ on
		 * b1_1_.bbsnum=config5_.bbsnum inner join user u2_ on
		 * this_2_.num=u2_.num where b1_.num=? order by this_.rnum desc
		 */
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Reply.class, "A");
		detachedCriteria.createCriteria("bbs", "B");
		detachedCriteria.add(Restrictions.eq("B.num", 1)).addOrder(Order.desc("A.rnum"));
		
		
//		List<Reply> list = criteria.list();
//		List<Reply> list = getHibernateTemplate().findByCriteria(detachedCriteria);
		List<Reply> list = getHibernateTemplate().findByCriteria(detachedCriteria, 0, 10);
		
		logger.debug("size : "+list.size());
		logger.debug("list : "+list);
		
		return list;
	}
		
}
