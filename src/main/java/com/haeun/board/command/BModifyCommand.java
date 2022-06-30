package com.haeun.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.haeun.board.dao.BDao;
//수정
public class BModifyCommand {
	
	public void modifyExecute(HttpServletRequest request, HttpServletResponse response) {
		String btitle = request.getParameter("btitle");
		String bname = request.getParameter("bname");
		String bcontent = request.getParameter("bcontent");
		String bid = request.getParameter("bid");
		
		BDao bdao = new BDao();
		bdao.modify(bname, btitle, bcontent, bid);
		
	}
	
	
}
