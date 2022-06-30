package com.haeun.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.haeun.board.dao.BDao;

public class BWriteCommand {
	public void writeExecute(HttpServletRequest request, HttpServletResponse response) {
		
		String bname=request.getParameter("bname");
		String btitle=request.getParameter("btitle");
		String bcontent=request.getParameter("bcontent");
		
		BDao bdao = new BDao();
		//하나씩 호출해서 DB에 넣기
		bdao.write(bname, btitle, bcontent);
		
		
	}
}
