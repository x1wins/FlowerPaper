package net.changwoo.x1wins.web;

import java.sql.Blob;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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

	
	@RequestMapping(value = "/list/{protocol}", method = RequestMethod.GET)
	public String doList(Map model, @PathVariable String protocol) {
	 
	    try {
	    	
	    	List list = userService.findAll();
	    	model.put("list", list);
	 
	    } catch (Exception e) {
	    	logger.debug(e.toString());
	    }
	 
	    return protocol + "View";
	}
	
	@RequestMapping(value = "/detail/{protocol}", method = RequestMethod.GET)
	public String doDetail(Map model, @PathVariable String protocol) {
		
		try {
			
			User user = userService.findUser(1);
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
	public String showSignUpForm(Map model) {
		User user = new User();
		model.put("user", user);
    	model.put("menu", "signup");

		return "user/signup.tiles";
	}

	// Process the form.
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
//	public String processSignUpForm(@Valid User user, @RequestParam("file") MultipartFile file, BindingResult result
	public String processSignUpForm(@Valid User user, BindingResult result
			,Map model, @RequestParam("file") MultipartFile file, HttpServletRequest request) {

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
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug(e.toString());
		}

		return "user/signup_success.tiles";
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
	public String showSignInForm(Map model) {
		Signin signin = new Signin();
		model.put("signin", signin);
    	model.put("menu", "signin");

		return "user/signin.tiles";
	}

	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public String processSignInForm(@Valid Signin signin, BindingResult result,
			Map model, HttpServletRequest request) {

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
		} catch (Exception e) {
			logger.debug(e.toString());
		}

		return "user/signin_success.tiles";
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
		 
		 try{
			 
			 userService.removeSignOutSession(request);
			 response.sendRedirect(request.getContextPath()+"/index");
			 
		 }catch (Exception e) {
			// TODO: handle exception
			 logger.debug(e.toString());
		}
	
		 return null;
//	 return "redicte:/user/signin";
	 }

}