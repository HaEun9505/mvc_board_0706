package com.haeun.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.haeun.board.dao.BDao;

//삭제(return값 x)
public class BDeleteCommand implements BCommand{
//	public void deleteExecute(HttpServletRequest request, HttpServletResponse response) {
//		String bid = request.getParameter("bid");
//		
//		BDao bdao = new BDao();
//		bdao.delete(bid);	//delete메소드 호출
//	}

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) {
		String bid = request.getParameter("bid");
		
		BDao bdao = new BDao();
		bdao.delete(bid);	//delete메소드 호출
		
	}
}