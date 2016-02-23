package net.changwoo.x1wins.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.changwoo.x1wins.dao.BbsDao;
import net.changwoo.x1wins.dao.ReplyDao;
import net.changwoo.x1wins.dao.UserDao;
import net.changwoo.x1wins.entity.Bbs;
import net.changwoo.x1wins.entity.Reply;
import net.changwoo.x1wins.entity.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReplyService {

	private static final Logger logger = LoggerFactory
			.getLogger(ReplyService.class);

	@Autowired
	private ReplyDao replyDao;
	
	@Autowired
	private BbsDao bbsDao;
	
	@Autowired
	private UserDao userDao;
	
	@Transactional
	public void saveReply(Reply reply, int bbsnum, HttpServletRequest request) throws Exception {

		
		HttpSession session = request.getSession(false);
		String userid;
		if (session.getAttribute("userid") != null){
			userid = session.getAttribute("userid").toString();
		}else{
			userid = "illegal userid";
		}
		List userList = userDao.findAllByProperty("userid", userid);
		User user;
		if(userList.size()<=0){
			String message = "not exist userid";
			throw new Exception(message);
		}else{
			user = (User) userList.get(0);
		}
		List bbsList = bbsDao.findAllByProperty("num", bbsnum);
		Bbs bbs;
		if(bbsList.size()<=0){
			String message = "not exist bbs";
			throw new Exception(message);
		}else{
			bbs = (Bbs) bbsList.get(0);
		}
		
		reply.setIp(request.getRemoteAddr());
		reply.setUserid(userid);
//		reply.setUser(user);
		reply.setBbs(bbs);
		
		replyDao.saveOrUpdate(reply);
		
	}
	
	
	@Transactional
	public void delete(int replynum, int num)throws Exception {

		Reply reply = replyDao.findById(num);
		replyDao.delete(reply);
		
	}
	
	@Transactional
	public List<Reply> findReplyList(int bbsnum)throws Exception {

		//reply list
		List<Reply> list = replyDao.findReplyList(bbsnum); 
		logger.debug("size : "+list.size());
		logger.debug("list : "+list);
		return list;
	}
	
	@Transactional
	public boolean validSignin(int replynum, HttpServletRequest request) throws Exception{
		
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
	
	@Transactional
	public boolean validOwn(int num, HttpServletRequest request) throws Exception{
		
		boolean resultBoolean = false;
		
		HttpSession session = request.getSession(false);
//		if (request.getSession(false) != null){
		logger.debug("login id "+session.getAttribute("userid"));
		if (session.getAttribute("userid") != null){
			//login y
			logger.debug("login y ");
			Reply reply = replyDao.findById(num);
			
//			String replyUserid = reply.getUser().getUserid();
//			String sessionUserid = session.getAttribute("userid").toString();
//			resultBoolean = replyUserid.equals(sessionUserid);
			
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
	
}
