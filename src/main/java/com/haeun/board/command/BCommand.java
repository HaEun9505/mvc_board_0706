package com.haeun.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface BCommand {
	//추상메소드
	void excute(HttpServletRequest request,HttpServletResponse response);
	
}