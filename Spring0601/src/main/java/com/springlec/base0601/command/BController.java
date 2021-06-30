package com.springlec.base0601.command;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BController {
	
	BCommand command = null;
	
	@RequestMapping("/list")
	public String list(Model model) {
		System.out.println("list");
		command = new BListCommand();
		command.execute(model);
		
		return "list";
	}

}//=-----
