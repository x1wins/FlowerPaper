package net.changwoo.chat.dao;

import net.changwoo.chat.entity.Join;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class JoinDao extends GenericDaoImpl<Join, Integer> {

	@Autowired
	JoinDao(SessionFactory sf) {
		super(sf);
	}

	@Override
	protected Class<Join> getEntityClass() {
		return Join.class;
	}
	
}