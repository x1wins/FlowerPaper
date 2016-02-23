package net.changwoo.x1wins.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.changwoo.x1wins.dao.UserDao;
import net.changwoo.x1wins.entity.Signin;
import net.changwoo.x1wins.entity.User;
import net.changwoo.x1wins.web.UserController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

	private static final Logger logger = LoggerFactory
			.getLogger(UserService.class);

	@Autowired
	private UserDao userDao;

	@Transactional
	public void saveUser(User user) throws Exception {
//		Date date = new Date();
//		user.setRegdate(date);
		// user.setLevel(1);
		// user.setStatus(1);
		userDao.save(user);
	}
	
	@Transactional
	public boolean isValidSignin(Signin signin) throws Exception {

		String userid = signin.getUserid();
		String password = signin.getPassword();
		
		List list = userDao.findUseridAndPassword(userid, password);
		
		if(list.size() > 0){
			return true;
		}else{
			return false;
		}
		
	}
	
	@Transactional
	public boolean isValidSignup(User user) throws Exception {

		int count = userDao.findAllByProperty("userid", user.getUserid()).size();
		
		if(count == 0){
			return true;
		}else{
			return false;
		}
	}
	
	@Transactional
	public User findUser(int id) throws Exception {
		
		return userDao.findById(id);
		
	}
	
	@Transactional
	public User findUsernameByUserid(String userid) throws Exception {
		
		return userDao.findAllByProperty("userid", userid).get(0);
		
	}
	
	
	@Transactional
	public List findAll() throws Exception {
		
		return userDao.findAll();
		
	}
	
	public String createSigninSession(HttpServletRequest request, String userid) throws Exception {
		HttpSession session = request.getSession(true);
		session.setAttribute("userid", userid);
		
		logger.debug("id creating userid : "+session.getAttribute("userid")+" session.getId() "+session.getId() +" JSESSIONID "+ session.getAttribute("JSESSIONID"));
		return session.getId();
	}
	
	public void removeSignOutSession(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession(false);
		session.removeAttribute("userid");
		logger.debug("id remove userid : "+session.getAttribute("userid"));
		
	}
	
}