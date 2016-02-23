package net.changwoo.x1wins.service;

import java.util.List;

import net.changwoo.x1wins.dao.DocumentDao;
import net.changwoo.x1wins.entity.Document;
import net.changwoo.x1wins.web.UserController;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DocumentService {

	private static final Logger logger = LoggerFactory
			.getLogger(DocumentService.class);

	@Autowired
	private DocumentDao documentDao;
	
	@Transactional
	public void save(Document document) {
		documentDao.save(document);
	}
	
	@Transactional
	public List<Document> list() {
		List<Document> documents = null;
		try {
			documents = documentDao.findAll();

		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return documents;
	}
	
	@Transactional
	public Document get(Integer id) {

		return documentDao.findById(id);
	}

	@Transactional
	public void remove(Integer id) {
		
		Document document = documentDao.findById(id);
		documentDao.delete(document);
		
	}
	
}
