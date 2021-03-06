package com.springlec.base0401;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	//form태그 만들기 귀차나서 함
	@RequestMapping("board/confirmId")//라고 입력했다! 근데 입력시에 form태그로 값을 달고 들어온다
	public String confirmId(HttpServletRequest httpServletRequest, Model model) {
		//생성자에 데이터를 가져오는 부분과, 주는 부분이 되어있어야함 
		//httpServletRequest로 값을 받고. Model로 값을 넘겨준다.
		String id = httpServletRequest.getParameter("id");
		String pw = httpServletRequest.getParameter("pw");
		
		model.addAttribute("id", id);
		model.addAttribute("pw",pw);
		
		return "board/resultId";
	}
	
	@RequestMapping("board/checkId")
	public String checkId(@RequestParam("id") String id, @RequestParam("pw") int pw, Model model) {
		model.addAttribute("id", id);
		model.addAttribute("pw", pw);
		return "board/resultId";
	}
}
