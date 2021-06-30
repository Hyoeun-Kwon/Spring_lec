package com.springlec.base0602.command;

import org.springframework.ui.Model;

public interface BCommand {
	
	//3. s공통적으로 쓰는 method 이름 (model에 값넣어서 가져와야함 )
	void execute(Model model);	
	
	
}
