package com.springlec.base0502;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RedirectController {

	@RequestMapping("studentConfirm")
	public String studentRedirect(HttpServletRequest httpServletRequest, Model model) {
		
		String id = httpServletRequest.getParameter("id");
		if (id.equals("abc")) {
			return "redirect:studentOK"; // studentOK로 redirect 해라라는 
		}//if
		return "redirect:studentNG";	
	
	}
	
	// redirect:(Mapping이름)
	// redirect : -> jsp로 가는게 아니라 controller에서 studentOK Mapping으로 가는거임
	// Rm 에서는 이제 받아오는건 위에서 받아왔고 보내기만 하면 되기때문에! Model 만 있으면 된다.
	
	
	@RequestMapping("studentOK")
	public String ok(Model model) {
		return "student/studentOk";
	}
	
	
	@RequestMapping("studentNG")
	public String ng(Model model) {
		return "student/studentNG";
	}
	
	
	
}