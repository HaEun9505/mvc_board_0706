package com.haeun.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.haeun.board.dao.BDao;
import com.haeun.board.dto.BDto;
//추상메소드 상속받음
public class BContentviewCommand implements BCommand {
//	public void viewExcute(HttpServletRequest request, HttpServletResponse response) {
//		
//		String bid = request.getParameter("bid");
//		
//		BDao bdao = new BDao(); 
//		BDto bdto = bdao.contentView(bid);
//		
//		//request 객체 셋팅
//		request.setAttribute("contentView", bdto);
//	}

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) {
		
		String bid = request.getParameter("bid");
		
		BDao bdao = new BDao(); 
		BDto bdto = bdao.contentView(bid);
		
		//request 객체 셋팅
		request.setAttribute("contentView", bdto);
		
	}
}
