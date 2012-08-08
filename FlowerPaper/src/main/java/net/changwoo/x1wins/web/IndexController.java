package net.changwoo.x1wins.web;

import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 
/**
 * Handles requests for the application home page.
 */
@Controller
public class IndexController {
     
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
    private static String menu = "index";
     
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String doSignInGetMethod(Locale locale, Map model) {
        
    	logger.debug("this.toString() "+this.toString());
    	model.put("menu", "index");
        return "index.tiles";
    }
}