package net.changwoo.x1wins.dao;

import net.changwoo.x1wins.entity.Portfolio;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PortfolioDao extends GenericDaoImpl<Portfolio, Integer> {

	@Autowired
	PortfolioDao(SessionFactory sf) {
		super(sf);
	}

	@Override
	protected Class<Portfolio> getEntityClass() {
		return Portfolio.class;
	}
	
	
}