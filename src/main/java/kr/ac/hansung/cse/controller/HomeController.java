package kr.ac.hansung.cse.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest request) {
		
		Logger logger = LoggerFactory.getLogger("kr.ac.hansung.cse.controller.HomeController");
		// kr.ac.hansung.cse 이름의 logger를 상속받기 때문에 level = debug 이며 Console, File, File에 출력한다.
//		Logger logger = LoggerFactory.getLogger(HomeController.class);
		
		/* logger는 디버깅 뿐만 아니라, client 정보를 출력 가능하다.(인자에 HttpServletRequest추가) */
		String url = request.getRequestURL().toString();
		String clientIpAddress = request.getRemoteAddr(); // client ip address
		
		logger.info("request url : " + url);
		logger.info("client ip : " + clientIpAddress);
		
		/*logger.trace("trace level : hello"); // 현재 debug level이기에 출력 안됨
		logger.debug("debug level : hello");
		logger.info("info level : hello");
		logger.warn("warn level : hello");
		logger.error("error level : hello");*/
		
		return "home";
	}
	
}
