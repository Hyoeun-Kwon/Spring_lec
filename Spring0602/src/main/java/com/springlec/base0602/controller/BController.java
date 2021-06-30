package com.springlec.base0602.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springlec.base0602.command.BCommand;
import com.springlec.base0602.command.BContentCommand;
import com.springlec.base0602.command.BDeleteCommand;
import com.springlec.base0602.command.BListCommand;
import com.springlec.base0602.command.BModifyCommand;
import com.springlec.base0602.command.BWriteCommand;

@Controller
public class BController {
	
	BCommand command = null;
	private BCommand listCommand = null;
	private BCommand writeCommand =null;
	private BCommand contentCommand = null;
	private BCommand modifyCommand = null;
	private BCommand deleteCommand = null;
	
	@Autowired
	//매개변수로 불러온 애들이 servlet-context에서 가져 온 것 (name들)
	public void auto(BCommand list, BCommand write, BCommand content, BCommand modify, BCommand delete) {
		this.listCommand = list;
		this.writeCommand = write;
		this.contentCommand = content;
		this.modifyCommand = modify;
		this.deleteCommand = delete;
		
	}
	
	
	@RequestMapping("/list")
	public String list(Model model) {
		System.out.println("list()");
		//command = new BListCommand();
		//command.execute(model);
		listCommand.execute(model);
		return "list";
	}
	
	@RequestMapping("/write_view")
	public String write_view(Model model) {
		System.out.println("write_view()");
		return "write_view";
	}
	
	@RequestMapping("/write")
	public String write(HttpServletRequest request, Model model) {
		System.out.println("write()");
		
		model.addAttribute("request", request);
		//command = new BWriteCommand();
		//command.execute(model);
		writeCommand.execute(model);
		
		//입력후 list로 다시 감
		return "redirect:list";
		
	}
	
	@RequestMapping("/content_view")
	public String content_view(HttpServletRequest request, Model model) {
		System.out.println("content_view()");
		model.addAttribute("request", request);
		//command =new BContentCommand();
		//command.execute(model);
		contentCommand.execute(model);
		return "content_view";
	}
	
	@RequestMapping("/modify")
	public String modify(HttpServletRequest request, Model model) {
		System.out.println("modify()");
		
		model.addAttribute("request_m", request);
		//command = new BModifyCommand();
		//command.execute(model);
		modifyCommand.execute(model);
		//입력후 list로 다시 감
		return "redirect:list";
		
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		System.out.println("delete()");
		
		model.addAttribute("request_d", request);
		//command = new BDeleteCommand();
		//command.execute(model);
		deleteCommand.execute(model);
		//입력후 list로 다시 감
		return "redirect:list";
		
	}

}//=-----
