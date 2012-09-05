package net.changwoo.x1wins.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class CurrentUrlInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory
			.getLogger(CurrentUrlInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		
		try{
			//현재 주소 알아오기 
			String currentUrl = request.getRequestURL()
					+ ((request.getQueryString()) == null
							|| (request.getQueryString()).equals("") ? "" : "?"
							+ request.getQueryString());

//			logger.debug("parameter : "+request.getQueryString().toString().indexOf("currentUrl"));
			
			// 요청 페이지가 login 인 경우 true 처리
			if(request.getRequestURI().indexOf("/user/signup")>=0 || request.getRequestURI().indexOf("/user/signin")>=0)
				return true;
			
			request.setAttribute("currentUrl", currentUrl);
			return true;

		}catch(Exception e){
			logger.debug(e.toString());
			return false;
		}
		
	}
}
