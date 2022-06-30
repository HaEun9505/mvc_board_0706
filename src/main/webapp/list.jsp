<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글 목록</title>
</head>
<body>
	<h2>게시판 글 리스트</h2>
	<hr>
	<!-- cellpadding : 셀과 글자 사이의 간격, cellspacing :셀 간의 간격 -->
	
	<table border="1" cellpadding="0" cellspacing="0">
		<tr bgcolor="pink" align="center">
			<td>번호</td>
			<td width="400">제목</td>
			<td>글쓴이</td>
			<td>등록일</td>
			<td>조회수</td>		
		</tr>
		<tr>
			<td align="center">1</td>
			<td>안녕하세요</td>
			<td align="center">홍길동</td>
			<td align="center">2022-06-13</td>
			<td align="center">0</td>		
		</tr>
		
		<tr align="right">
			<td colspan="5">
				<input type="button" value="글쓰기" onclick="location.href='write_view.do'">
			</td>
		</tr>
	</table>
</body>
</html>