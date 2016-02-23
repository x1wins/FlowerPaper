package net.changwoo.x1wins.dao;

import java.util.List;

import net.changwoo.x1wins.entity.User;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends GenericDaoImpl<User, Integer> {

	@Autowired
	UserDao(SessionFactory sf) {
		super(sf);
	}

	@Override
	protected Class<User> getEntityClass() {
		return User.class;
	}
	
	public List<User> findUseridAndPassword(String userid, String password){

		List user = getSession().createCriteria(User.class).add( Restrictions.eq("userid", userid ) ).add( Restrictions.eq("password", password)).list();
		
		return user;
	}
}