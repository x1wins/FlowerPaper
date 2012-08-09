package net.changwoo.x1wins.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.changwoo.x1wins.dao.BbsDao;
import net.changwoo.x1wins.dao.ConfigDao;
import net.changwoo.x1wins.dao.UserDao;
import net.changwoo.x1wins.entity.Bbs;
import net.changwoo.x1wins.entity.Config;
import net.changwoo.x1wins.entity.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BbsService {

	private static final Logger logger = LoggerFactory
			.getLogger(BbsService.class);

	@Autowired
	private BbsDao bbsDao;
	
	@Autowired
	private ConfigDao configDao;
	
	@Autowired
	private UserDao userDao;
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveBbs(Bbs bbs, int bbsnum, HttpServletRequest request) throws Exception {

		Config config = configDao.findAllByProperty("bbsnum", bbsnum).get(0);
//		config.setBbsnum(bbsnum);
//		config.setBbsname("test");
//		config.setUserid("tester");
		HttpSession session = request.getSession(false);
		String userid;
		if (session.getAttribute("userid") != null){
			userid = session.getAttribute("userid").toString();
		}else{
			userid = "illegal userid";
		}
		
		bbs.setIp(request.getRemoteAddr());
		bbs.setUserid(userid);
		bbs.setConfig(config);
		
		bbsDao.saveOrUpdate(bbs);
		
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateBbs(Bbs bbs, int bbsnum, HttpServletRequest request) throws Exception {
		
		int count = bbsDao.findById(bbs.getNum()).getCount();
		bbs.setCount(count);
		saveBbs(bbs, bbsnum, request);
		
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(int bbsnum, int num)throws Exception {

		Bbs bbs = bbsDao.findById(num);
//		Config config = configDao.findAllByProperty("bbsnum", bbsnum).get(0);
//		config.setBbsnum(bbsnum);
//		config.setBbsname("test");
//		config.setUserid("tester");
//		bbs.setConfig(config);
		bbsDao.delete(bbs);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void findListAndPaging(int bbsnum, int pageNum, int perPage, Map model, HttpServletRequest request)throws Exception {

		//bbs list
		List<Bbs> list = bbsDao.findList(bbsnum, pageNum, perPage); 
		
		//paging
		int rowSize = bbsDao.findListSize(bbsnum, pageNum);
		
		if(rowSize==0){
			logger.debug("rowsize is 0");
		}
		
		int pageSize = 0;
		
			
		if (rowSize % perPage == 0) {
			pageSize = rowSize / perPage;
		} else {
			pageSize = rowSize / perPage + 1;
		}
			
		
		int startPage = 0;
		int endPage = 0;
		if(pageNum>perPage){
			startPage = pageNum/perPage*perPage;
		}else{
			startPage = 1;
		}
		
		endPage = startPage+perPage;
		if(pageSize<endPage){
			endPage = pageSize;
		}
		
		String paging = "";
		//http://localhost:8080/x1wins/bbs/1/list/1
		for(int i=startPage; i<=endPage; i++){
			if(i == pageNum){
				paging += "<b>";
			}
			paging += " <a href=\""+request.getContextPath()+"/bbs/"+bbsnum+"/list/"+i+"\">"+i+"</a> ";
			if(i == pageNum){
				paging += "</b>";
			}
		}
		
		//bbs list type
		Config config = configDao.findAllByProperty("bbsnum", bbsnum).get(0);
		int listTypeNum = config.getListTypeNum();
		String bbsname = config.getBbsname();
		
//		if(rowSize>0){
//			Bbs bbs = list.get(0);
//			listTypeNum = bbs.getConfig().getListTypeNum();
//			bbsname = bbs.getConfig().getBbsname();
//		}
		
		model.put("bbsnum", bbsnum);
		model.put("bbsname", bbsname);
		model.put("list", list);
		model.put("paging", paging);
		model.put("listtypenum", listTypeNum);
		
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Bbs findDetail(int num)throws Exception {
		
		logger.debug("findDetail????");
		
		Bbs bbs = bbsDao.findById(num);
		int count = bbs.getCount();
		count++;
		bbs.setCount(count);
//		bbsDao.saveOrUpdate(bbs);
		
		
		return bbs;
		
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean validSignin(int bbsnum, HttpServletRequest request) throws Exception{
		
		boolean resultBoolean = false;
		
		HttpSession session = request.getSession(false);
//		if (request.getSession(false) != null){
		logger.debug("login id "+session.getAttribute("userid"));
		if (session.getAttribute("userid") != null){
			//login y
			logger.debug("login y ");
			
			//userid
//			String userid = session.getAttribute("userid").toString();
//			User user = userDao.findAllByProperty("userid", userid).get(0);
			resultBoolean = true;
			
		}else{
			
			resultBoolean = false;
			
		}
		
		if(resultBoolean==false){
			String message = "no sigin";
			throw new Exception(message);
		}
		
		return resultBoolean;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean validOwn(int num, HttpServletRequest request) throws Exception{
		
		boolean resultBoolean = false;
		
		HttpSession session = request.getSession(false);
//		if (request.getSession(false) != null){
		logger.debug("login id "+session.getAttribute("userid"));
		if (session.getAttribute("userid") != null){
			//login y
			logger.debug("login y ");
			Bbs bbs = bbsDao.findById(num);
			
			String bbsUserid = bbs.getUserid();
			String sessionUserid = session.getAttribute("userid").toString();
			resultBoolean = bbsUserid.equals(sessionUserid);
			
			//userid
//			String userid = session.getAttribute("userid").toString();
//			User user = userDao.findAllByProperty("userid", userid).get(0);
			
		}else{
			
			resultBoolean = false;
			
		}
		
		if(resultBoolean==false){
			String message = "no own";
			throw new Exception(message);
		}
		
		return resultBoolean;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean validRead(int bbsnum, HttpServletRequest request) throws Exception{
		
		boolean resultBoolean = false;
		String message = "";
		
		logger.debug("bbsnum "+bbsnum);
		List configList = configDao.findAllByProperty("bbsnum", bbsnum);
		
		if(configList.size()<=0){
			message = "bbs is not exist";
			throw new Exception(message);
		}
		
		Config config = (Config) configList.get(0);
		int publicYn = config.getPublicYn();
		logger.debug("publicYn "+publicYn);
		
		if(publicYn == 0){
			//no public
			message = "this bbs is close";
			resultBoolean = false;
			
		}else{
			//yes public
			
			//read avialble level
			int readFrom = config.getReadFrom();
			int readUntil = config.getReadUntil();
			
			//user level valid
			if(readFrom==0 && readUntil == 0){
				resultBoolean = true;
			}else{
			
				
				HttpSession session = request.getSession(false);
//				if (request.getSession(false) != null){
				logger.debug("login id "+session.getAttribute("userid"));
				if (session.getAttribute("userid") != null){
					//login y
					logger.debug("login y ");
					
					//userid
					String userid = session.getAttribute("userid").toString();
					User user = userDao.findAllByProperty("userid", userid).get(0);
					int level = user.getLevel();
					if(readFrom<=level && level <=readUntil){
						resultBoolean = true;
					}else{
						//fail
						resultBoolean = false;
						message = "you have level problem";
					}
					
					
				}else{
					//login n
					logger.debug("login n ");
					resultBoolean = false;
					message = "you must login";
				}
				
			}
			
			
			
			
		}
		
		if(resultBoolean==false){
			throw new Exception(message);
		}
		
		return resultBoolean;
		
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean validWrite(int bbsnum, HttpServletRequest request) throws Exception{
		
		boolean resultBoolean = false;
		String message = "";
		
		logger.debug("bbsnum "+bbsnum);
		Config config = configDao.findAllByProperty("bbsnum", bbsnum).get(0);
		int publicYn = config.getPublicYn();
		logger.debug("publicYn "+publicYn);
		
		if(publicYn == 0){
			//no public
			message = "this bbs is close";
			resultBoolean = false;
			
		}else{
			//yes public
			HttpSession session = request.getSession(false);
//			if (request.getSession(false) != null){
			logger.debug("login id "+session.getAttribute("userid"));
			if (session.getAttribute("userid") != null){
				//login y
				logger.debug("login y ");
				
				
				//read avialble level
				int writeFrom = config.getWriteFrom();
				int writeUntil = config.getWriteUntil();
				
				//userid
				String userid = session.getAttribute("userid").toString();
				User user = userDao.findAllByProperty("userid", userid).get(0);
				int level = user.getLevel();
				
				//user level valid
				if(writeFrom<=level && level<=writeUntil){
					//success
					resultBoolean = true;
				}else if(writeFrom==0 && writeUntil==0){
					//success
					resultBoolean = true;
				}else{
					//fail
					resultBoolean = false;
					message = "you have level problem";
				}
			}else{
				//login n
				logger.debug("login n ");
				resultBoolean = false;
				message = "you must login";
			}
		}
		
		if(resultBoolean==false){
			throw new Exception(message);
		}
		
		return resultBoolean;
		
	}
	
}
