package com.springlec.base0601.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.springlec.base0601.dao.BDao;

public class BModifyCommand implements BCommand {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		//보안 강화를 위해 map <key, value>
				//model 이 박스에 한번 더 있다.
		Map<String, Object> map = model.asMap(); //이렇게 해야 데이터 값을 받아올 수 있다 
		HttpServletRequest request = (HttpServletRequest) map.get("request_m");
		
		String bId = request.getParameter("bId");
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		
		//-------------------data  가져왔음
		////-------------------data  넣기
		
		BDao dao = new BDao();
		dao.modify(bId,bName, bTitle, bContent);
	}

}
