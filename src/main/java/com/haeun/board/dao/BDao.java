package com.haeun.board.dao;

//실제 데이터(DB)와 연결을 담당하는 DAO
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.haeun.board.dto.BDto;

public class BDao {

   private DataSource datasource;      // Server => context.xml 확인

   public BDao() {
      
      try {
         Context context = new InitialContext();
         datasource = (DataSource) context.lookup("java:comp/env/jdbc/odbo"); // 형변환 필요
      }
      catch (Exception e) {
         e.printStackTrace();
      }
   }
   
   public void write(String bname, String btitle, String bcontent) { //insert, 매개변수 선언
      
      Connection conn = null;
      //statement를 상속받는 인터페이스로 SQL구문을 실행시키는 기능을 갖는 객체
      PreparedStatement pstmt = null;      
      
      try {
         String sql = "INSERT INTO mvc_board(bname, btitle, bcontent, bhit) VALUES (?, ?, ?, 0)"; 
         // PreparedStatement는 값에 뭐가 들어올지 모를 때 parameter값에 '?'를 씀, 조회수는 0부터 시작
         conn = datasource.getConnection();
         pstmt = conn.prepareStatement(sql);   
         
         // pstmt는 자리값이 0이 아닌 1부터 시작, 매개변수값으로 setting
         pstmt.setString(1, bname);
         pstmt.setString(2, btitle);
         pstmt.setString(3, bcontent);
         
         pstmt.executeUpdate();    // sql 실행
      }
      catch(Exception e) {
         e.printStackTrace();
      }
      finally {
         try {
            if (pstmt != null) {
               pstmt.close();
            }
            if (conn != null) {
               conn.close();
            }
            
         }
         catch(Exception e) {
            e.printStackTrace();
         }
      }
   }
   //DB에 있는 글 목록을 모두 가져옴(반환값)
   public ArrayList<BDto> list() {		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<BDto> bdtos = new ArrayList<BDto>();
		
		String sql = "SELECT * FROM mvc_board ORDER BY bid DESC";
		
		try {			
			conn = datasource.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();//sql 실행
			
			while(rs.next()) {
				int bid = rs.getInt("bid");
				String bname = rs.getString("bname");
				String btitle = rs.getString("btitle");
				String bcontent = rs.getString("bcontent");
				Timestamp bdate = rs.getTimestamp("bdate");
				int bhit = rs.getInt("bhit");
				
				BDto bdto = new BDto(bid, bname, btitle, bcontent, bhit, bdate);
				bdtos.add(bdto);
			}			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return bdtos;		
	}
   
   public BDto contentView(String boardid) {	//리스트 이름을 contentView로 지정
	   
	  //sql = "SELECT * FROM mvc_board";
	  Connection conn = null;
	  //statement를 상속받는 인터페이스로 SQL구문을 실행시키는 기능을 갖는 객체
	  PreparedStatement pstmt = null; 
	  ResultSet rs = null;
	  BDto bdto = null;
	  
	  String sql = "SELECT * FROM mvc_board WHERE bid = ? ";
      
	  try {
	         // PreparedStatement는 값에 뭐가 들어올지 모를 때 parameter값에 '?'를 씀, 조회수는 0부터 시작
	         conn = datasource.getConnection();
	         pstmt = conn.prepareStatement(sql);
	         
	         pstmt.setString(1, boardid);
	         
	         rs = pstmt.executeQuery();    // sql 실행(select문 rs객체로 반환)
	         
	         //처음 위치 필드명에서 다음 포지션으로 이동(없으면 false)
	         while(rs.next()) {	//값이 없을때까지
	        	 int bid = rs.getInt("bid");
	        	 String bname = rs.getString("bname");
	        	 String btitle = rs.getString("btitle");
	        	 String bcontent = rs.getString("bcontent");
	        	 int bhit = rs.getInt("bhit");
	        	 Timestamp bdate = rs.getTimestamp("bdate");
	        	
	        	 
	        	 bdto = new BDto(bid, bname, btitle, bcontent, bhit, bdate);
	         }
	      }catch(Exception e) {
	         e.printStackTrace();
	      }
	      finally {
	         try {
	        	if (rs != null) {
		               rs.close();
	        	}
	        	 if (pstmt != null) {
	               pstmt.close();
	            }
	            if (conn != null) {
	               conn.close();
	            }
	            
	         }
	         catch(Exception e) {
	            e.printStackTrace();
	         }
	     }
	  	return bdto;   
   }
   public void modify(String bname, String btitle, String bcontent, String bid) { //insert, 매개변수 선언
	      
      Connection conn = null;
      //statement를 상속받는 인터페이스로 SQL구문을 실행시키는 기능을 갖는 객체
      PreparedStatement pstmt = null;      
      
      try {
         String sql = "UPDATE mvc_board SET bname=?, btitle=?, bcontent=? WHERE bid=?"; 
         // PreparedStatement는 값에 뭐가 들어올지 모를 때 parameter값에 '?'를 씀, 조회수는 0부터 시작
         conn = datasource.getConnection();
         pstmt = conn.prepareStatement(sql);   
         
         // pstmt는 자리값이 0이 아닌 1부터 시작, 매개변수값으로 setting
         pstmt.setString(1, bname);
         pstmt.setString(2, btitle);
         pstmt.setString(3, bcontent);
         pstmt.setString(4, bid);
         
         pstmt.executeUpdate();    // sql 실행
      }
      catch(Exception e) {
         e.printStackTrace();
      }finally {
         try {
            if (pstmt != null) {
               pstmt.close();
            }
            if (conn != null) {
               conn.close();
            }
            
         }
         catch(Exception e) {
            e.printStackTrace();
         }
      }
   }
   public void delete(String bid) { //insert, 매개변수 선언
	      
	      Connection conn = null;
	      //statement를 상속받는 인터페이스로 SQL구문을 실행시키는 기능을 갖는 객체
	      PreparedStatement pstmt = null;      
	      
	      try {
	         String sql = "DELETE mvc_board WHERE bid=?"; 
	         // PreparedStatement는 값에 뭐가 들어올지 모를 때 parameter값에 '?'를 씀, 조회수는 0부터 시작
	         conn = datasource.getConnection();
	         pstmt = conn.prepareStatement(sql);   
	         
	         // pstmt는 자리값이 0이 아닌 1부터 시작, 매개변수값으로 setting
	         pstmt.setString(1, bid);
	         
	         pstmt.executeUpdate();    // sql 실행
	      }
	      catch(Exception e) {
	         e.printStackTrace();
	      }
	      finally {
	         try {
	            if (pstmt != null) {
	               pstmt.close();
	            }
	            if (conn != null) {
	               conn.close();
	            }
	            
	         }
	         catch(Exception e) {
	            e.printStackTrace();
	         }
	      }
	   }
   
}