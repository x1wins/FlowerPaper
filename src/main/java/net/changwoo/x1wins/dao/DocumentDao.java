package net.changwoo.x1wins.dao;

import net.changwoo.x1wins.entity.Document;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DocumentDao extends GenericDaoImpl<Document, Integer> {

	@Autowired
	DocumentDao(SessionFactory sf) {
		super(sf);
	}

	@Override
	protected Class<Document> getEntityClass() {
		return Document.class;
	}
	
}