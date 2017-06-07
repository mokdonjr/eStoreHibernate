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
		// kr.ac.hansung.cse �̸��� logger�� ��ӹޱ� ������ level = debug �̸� Console, File, File�� ����Ѵ�.
//		Logger logger = LoggerFactory.getLogger(HomeController.class);
		
		/* logger�� ����� �Ӹ� �ƴ϶�, client ������ ��� �����ϴ�.(���ڿ� HttpServletRequest�߰�) */
		String url = request.getRequestURL().toString();
		String clientIpAddress = request.getRemoteAddr(); // client ip address
		
		logger.info("request url : " + url);
		logger.info("client ip : " + clientIpAddress);
		
		/*logger.trace("trace level : hello"); // ���� debug level�̱⿡ ��� �ȵ�
		logger.debug("debug level : hello");
		logger.info("info level : hello");
		logger.warn("warn level : hello");
		logger.error("error level : hello");*/
		
		return "home";
	}
	
}
