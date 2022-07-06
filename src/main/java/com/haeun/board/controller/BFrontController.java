package com.haeun.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.haeun.board.command.BCommand;
import com.haeun.board.command.BContentviewCommand;
import com.haeun.board.command.BDeleteCommand;
import com.haeun.board.command.BListCommand;
import com.haeun.board.command.BModifyCommand;
import com.haeun.board.command.BWriteCommand;
import com.haeun.board.dao.BDao;

/**
 * Servlet implementation class BFrontController
 */
@WebServlet("*.do")	//Controller에서 특정확장자로 요청한것을 잡아줌 -> command 호출
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
		
		actionDo(request, response);
	}

	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();		//요청주소저장(/mvc_boardProject_0630/write.do)
		String conPath = request.getContextPath();	//컨텍스트패스저장(/mvc_boardProject_0630)
		String command = uri.substring(conPath.length());	//실제 주소 요청 분기(command 분기)
		
		String view = null;
		BCommand comm=null;
		//"write_view.do"와 일치하면
		//요청을 받은 페이지에 command(주소 요청 분기)해서
		//request객체로 실어서 view("/write_view.jsp")로 전달
		if(command.equals("/write_view.do")) {
			
			view = "write_view.jsp";
		}else if(command.equals("/write.do")) {
			//글쓰기 명령이 실행(write command만 실행)
			request.setCharacterEncoding("utf-8");
			
			//BWriteCommand 클래스 객체 선언(호출)
			comm = new BWriteCommand();//인터페이스
			comm.excute(request, response);
			
			view = "list.do";
		}else if(command.equals("/list.do")) {
			//글 리스트 불러오기 명령이(list command) 실행
			
			//BListCommand 클래스 객체 선언(호출)
			comm = new BListCommand();
			comm.excute(request, response);
			
			view = "list.jsp";
			//response.sendRedirect(view); //데이터가 셋팅된 request 객체를 사용하지 못함
		}else if(command.equals("/content_view.do")) {	//content_view.do 요청
			
			
			//BContentView 클래스 객체 선언(호출)
			comm = new BContentviewCommand();
			comm.excute(request, response);
			
			view = "content_view.jsp";
		}else if(command.equals("/modify.do")) {	//content_view.do 요청
			
			request.setCharacterEncoding("utf-8");
			
			
			//BModifyCommand 클래스 객체 선언(호출)
			comm = new BModifyCommand();
			comm.excute(request, response);
			
			//수정하면 글 목록 페이지로 이동
			view = "list.do";
		}else if(command.equals("/delete.do")) {
			
			//BDeleteCommand 클래스 객체 선언(호출)
			comm = new BDeleteCommand();
			comm.excute(request, response);
				
			//삭제하면 글 목록 페이지로 이동
			view = "list.do";
		}
		//페이지로 이동((기존)request객체가 실린 채로 전달)
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
		
	}
}