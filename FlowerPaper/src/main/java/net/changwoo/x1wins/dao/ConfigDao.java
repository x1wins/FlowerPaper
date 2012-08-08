package net.changwoo.x1wins.dao;

import net.changwoo.x1wins.entity.Config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class ConfigDao extends GenericDaoImpl<Config, Integer> {

	@Autowired
	ConfigDao(SessionFactory sf) {
		super(sf);
	}

	@Override
	protected Class<Config> getEntityClass() {
		return Config.class;
	}
	
}
