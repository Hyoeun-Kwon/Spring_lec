package com.springlec.base0302;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
//controller는 클라스 
@Controller
@RequestMapping("test")
public class myController {
	
	@RequestMapping("/view1")
	public String view1() {
		return "test/view1";
	}

}
