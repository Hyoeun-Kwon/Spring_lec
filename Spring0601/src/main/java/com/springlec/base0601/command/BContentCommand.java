package com.springlec.base0601.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.springlec.base0601.dao.BDao;
import com.springlec.base0601.dto.BDto;

public class BContentCommand implements BCommand {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap(); //이렇게 해야 데이터 값을 받아올 수 있다 
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String sbId = request.getParameter("bId");
		BDao dao = new BDao();
		BDto dtos = dao.contentView(sbId);
		model.addAttribute("content_view", dtos);

	}

}
