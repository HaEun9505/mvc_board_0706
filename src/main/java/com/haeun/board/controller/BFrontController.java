package com.haeun.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.haeun.board.command.BListCommand;
import com.haeun.board.command.BWriteCommand;
import com.haeun.board.dao.BDao;

/**
 * Servlet implementation class BFrontController
 */
@WebServlet("*.do")	//Controller에서 특정확장자로 요청한것을 잡아줌
public class BFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response);
	}

	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();		//요청주소저장(/mvc_boardProject_0630/write.do)
		String conPath = request.getContextPath();	//컨텍스트패스저장(/mvc_boardProject_0630)
		String command = uri.substring(conPath.length());	//실제 주소 요청 분기
		
		String view = null;
		
		//"write_view.do"와 일치하면
		//요청을 받은 페이지에 command(주소 요청 분기)해서
		//request객체로 실어서 view("/write_view.jsp")로 전달
		if(command.equals("/write_view.do")) {
			
			view = "write_view.jsp";
		}else if(command.equals("/write.do")) {
			//글쓰기 명령이 실행(write command만 실행)
			request.setCharacterEncoding("utf-8");
			
			//BWriteCommand 클래스 객체 선언
			BWriteCommand comm = new BWriteCommand();
			comm.writeExecute(request, response);
			
			view = "list.do";
		}else if(command.equals("/list.do")) {
			//글 리스트 불러오기 명령이(list command) 실행
			
			BListCommand comm = new BListCommand();
			comm.listExecute(request,response);
			
			view = "list.jsp";
		}
		
		//페이지로 이동
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
		
	}

}