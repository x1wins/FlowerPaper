package net.changwoo.app.bbs.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.changwoo.app.bbs.dao.BbsDao;
import net.changwoo.app.bbs.entity.Bbs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BbsServiceImpl implements IBbsService {

	@Autowired
	private BbsDao bbsDao;
	
	public void saveBbs(Bbs bbs, int bbsnum, HttpServletRequest request)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void updateBbs(Bbs bbs, int bbsnum, HttpServletRequest request)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void delete(int bbsnum, int num) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void findListAndPaging(int bbsnum, int pageNum, int perPage,
			Map model, HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public Bbs findDetail(int num) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean validSignin(int bbsnum, HttpServletRequest request)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean validOwn(int num, HttpServletRequest request)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean validRead(int bbsnum, HttpServletRequest request)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean validWrite(int bbsnum, HttpServletRequest request)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}