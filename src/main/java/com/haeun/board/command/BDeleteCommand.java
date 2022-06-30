package com.haeun.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.haeun.board.dao.BDao;

//삭제
public class BDeleteCommand {
	public void deleteExecute(HttpServletRequest request, HttpServletResponse response) {
		String bid = request.getParameter("bid");
		
		BDao bdao = new BDao();
		bdao.delete(bid);
	}
}
