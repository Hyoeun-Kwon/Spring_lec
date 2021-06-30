package com.springlec.base0602.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.springlec.base0602.dao.BDao;
import com.springlec.base0602.dto.BDto;

public class BContentCommand implements BCommand {
	
	private BDao dao = null;
	@Autowired
	public void setDao(BDao dao) {
		this.dao = dao;
	}

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap(); //이렇게 해야 데이터 값을 받아올 수 있다 
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String sbId = request.getParameter("bId");
		//BDao dao = new BDao();
		BDto dtos = dao.contentView(sbId);
		model.addAttribute("content_view", dtos);

	}

}
