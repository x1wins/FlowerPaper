package net.changwoo.x1wins.web;

import java.sql.Blob;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import net.changwoo.x1wins.entity.Response;
import net.changwoo.x1wins.entity.Signin;
import net.changwoo.x1wins.entity.User;
import net.changwoo.x1wins.service.UserService;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/user")
// @SessionAttributes("user")
public class UserController {

	private static final Logger logger = LoggerFactory
			.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	
	@RequestMapping(value = "/list.{protocol}", method = RequestMethod.GET)
	public String doList(Map model, @PathVariable String protocol, HttpServletRequest request) {
    	
		Response response = new Response();
	    try {
	    	
	    	
	    	HttpSession session = request.getSession(false);
			if(session == null) {
				
				response.setStatus("FAIL");
//				response.setResult(resultMap);
			} else {
			
				String userid = session.getAttribute("userid").toString();
				logger.info("session userid "+userid);
				logger.info("session getid() "+session.getId());
				
				List list = userService.findAll();
		    	model.put("list", list);
			}
	    	
	 
	    } catch (Exception e) {
	    	logger.debug(e.toString());
	    	response.setStatus("FAIL");
	    }
	 
	    return protocol + "View";
	}
	
	@RequestMapping(value = "/{usernum}/detail.{protocol}", method = RequestMethod.GET)
	public String doDetail(@PathVariable("usernum") int usernum, Map model, @PathVariable String protocol) {
		
		try {
			
			User user = userService.findUser(usernum);
			model.put("user", user);
			
		} catch (Exception e) {
			logger.debug(e.toString());
		}
		
		return protocol + "View";
	}
	
	/**
	 * sign up
	 * 
	 * @param locale
	 * @param model
	 * @return
	 */
	// Display the form on the get request
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String showSignUpForm(Map model, HttpServletRequest request) {
		
		String currentUrl = request.getParameter("currentUrl").toString();
		logger.debug("currentUrl : "+currentUrl);
		
		User user = new User();
		model.put("user", user);
    	model.put("menu", "signup");
		model.put("currentUrl", currentUrl);
		
		return "user/signup.tiles";
	}
	
	// Process the form.
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String processSignUpForm(@Valid User user, BindingResult result
			,Map model, @RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {

		String currentUrl = request.getParameter("currentUrl").toString();
		
		if (result.hasErrors()) {
			return "user/signup.tiles";
		}
		
		try {
			
			if (userService.isValidSignup(user) == false) {
				result.rejectValue("userid", "DuplicationId", "warnning");
				return "user/signup.tiles";
			}else{
				
				//sign in success and creating session
				userService.createSigninSession(request, user.getUserid());

				//수정 예정
				Blob blob = Hibernate.createBlob(file.getInputStream());
				user.setFilename(file.getOriginalFilename());
				user.setContent(blob);
				user.setContentType(file.getContentType());
				
				// Add the saved validationForm to the model
				model.put("user", user);
				
				userService.saveUser(user);
				
				response.sendRedirect(currentUrl);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug(e.toString());
		}

//		return "user/signup_success.tiles";
		return null;
	}
	
	@RequestMapping(value = "/signup.json", method = RequestMethod.POST)
	public ModelAndView processSignUpData(@Valid User user, BindingResult result
			,Map model, HttpServletRequest request) {
//, @RequestParam("file") MultipartFile file
		
		Map resultMap = new HashMap();
    	Response response = new Response();
    	
		try {

			if (userService.isValidSignup(user) == false) {

				result.rejectValue("userid", "DuplicationId", "warnning");
				response.setStatus("FAIL");
				response.setResult(result);
				
			} else {

				// sign in success and creating session
				userService.createSigninSession(request, user.getUserid());

				// 수정 예정
//				Blob blob = Hibernate.createBlob(file.getInputStream());
//				user.setFilename(file.getOriginalFilename());
//				user.setContent(blob);
//				user.setContentType(file.getContentType());

				userService.saveUser(user);
				
				response.setStatus("SUCCESS");
				response.setResult(user);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug(e.toString());
			response.setStatus("FAIL");
			response.setResult(e.toString());

		}
		
//		return "user/signin_success.tiles";
		ModelAndView modelAndView = getModelAndView(response, "json");
		return modelAndView;
	}

	/**
	 * sign in
	 * 
	 * @param locale
	 * @param model
	 * @return
	 */
	// Display the form on the get request
	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public String showSignInForm(Map model, HttpServletRequest request) {
		
		String currentUrl = request.getParameter("currentUrl").toString();
		
		Signin signin = new Signin();
		model.put("signin", signin);
    	model.put("menu", "signin");
    	model.put("currentUrl", currentUrl);

		return "user/signin.tiles";
	}

	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public String processSignInForm(@Valid Signin signin, BindingResult result,
			Map model, HttpServletRequest request, HttpServletResponse response) {

		String currentUrl = request.getParameter("currentUrl").toString();
		logger.debug("currentUrl : "+currentUrl);
		
		try {
			if (result.hasErrors()) {
				return "user/signin.tiles";
			}
			// Add the saved validationForm to the model

			if (userService.isValidSignin(signin) == false) {
				result.rejectValue("userid", "WrongIdPw", "warnning");
				return "user/signin.tiles";
			}else{
				
				//sign in success and creating session
				userService.createSigninSession(request, signin.getUserid());

			}

			model.put("signin", signin);
			response.sendRedirect(currentUrl);
			
		} catch (Exception e) {
			logger.debug(e.toString());
		}

//		return "user/signin_success.tiles";
		return null;
	}
	
	@RequestMapping(value = "/signin.json", method = RequestMethod.POST)
	public ModelAndView processSignInData(@Valid Signin signin, BindingResult result,
			Map model, HttpServletRequest request) {
		
		Map resultMap = new HashMap();
    	Response response = new Response();
    	
		try {

			// Add the saved validationForm to the model
			
			if (userService.isValidSignin(signin) == false) {
				result.rejectValue("userid", "WrongIdPw", "warnning");
				
				response.setStatus("FAIL");
				response.setResult(result);
				
			}else{
				
				//sign in success and creating session
				String sessionId = userService.createSigninSession(request, signin.getUserid());
				response.setStatus("SUCCESS");
				response.setResult(sessionId);
				
				String userid = signin.getUserid();
				String username = userService.findUsernameByUserid(userid).getName();
				signin.setUsername(username);
			}
			
			model.put("signin", signin);
			
		} catch (Exception e) {
			logger.debug(e.toString());
		}
		
//		return "user/signin_success.tiles";
		ModelAndView modelAndView = getModelAndView(response, "json");
		return modelAndView;
	}

	/**
	 * sign out
	 * 
	 * @param locale
	 * @param model
	 * @return
	 */
	 @RequestMapping(value = "/signout", method = RequestMethod.GET)
	 public String doSignOut(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		 logger.debug("sign out " + locale.toString());
		 
		 String currentUrl = request.getParameter("currentUrl").toString();
		 
		 try{
			 
			 userService.removeSignOutSession(request);
//			 response.sendRedirect(request.getContextPath()+"/index");
			 response.sendRedirect(currentUrl);
			 
		 }catch (Exception e) {
			// TODO: handle exception
			 logger.debug(e.toString());
		}
	
		 return null;
//		 return "redirect:"+currentUrl;
	 }
	 
	 
	 /**
		 * modify sign up
		 * 
		 * @param locale
		 * @param model
		 * @return
		 */
		// Display the form on the get request
		@RequestMapping(value = "/signupModify", method = RequestMethod.GET)
		public String showModifySignUpForm(Map model, HttpServletRequest request) {
			
			String currentUrl = request.getParameter("currentUrl").toString();
			logger.debug("currentUrl : "+currentUrl);
			
			User user = new User();
			model.put("user", user);
	    	model.put("menu", "signup");
			model.put("currentUrl", currentUrl);
			
			return "user/signup.tiles";
		}

		// Process the form.
		@RequestMapping(value = "/signupModify", method = RequestMethod.POST)
		public String processModifySignUpForm(@Valid User user, BindingResult result
				,Map model, @RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {

			String currentUrl = request.getParameter("currentUrl").toString();
			
			if (result.hasErrors()) {
				return "user/signup.tiles";
			}
			
			try {
				
				if (userService.isValidSignup(user) == false) {
					result.rejectValue("userid", "DuplicationId", "warnning");
					return "user/signup.tiles";
				}else{
					
					//sign in success and creating session
					userService.createSigninSession(request, user.getUserid());

					//수정 예정
					Blob blob = Hibernate.createBlob(file.getInputStream());
					user.setFilename(file.getOriginalFilename());
					user.setContent(blob);
					user.setContentType(file.getContentType());
					
					// Add the saved validationForm to the model
					model.put("user", user);
					
					userService.saveUser(user);
					
					response.sendRedirect(currentUrl);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.debug(e.toString());
			}

//			return "user/signup_success.tiles";
			return null;
		}

		public ModelAndView getModelAndView(Object attributeValue, String type)
		{
			ModelAndView modelAndView = new ModelAndView();
			
			try{
				modelAndView.addObject(attributeValue);
				
				if(!type.equals("json")&&!type.equals("xml")){
					type = "json";
				}
				
				modelAndView.setViewName(type + "View");
				
			}catch(Exception e){
				logger.debug(e.toString());
			}
			return modelAndView;
		}
}