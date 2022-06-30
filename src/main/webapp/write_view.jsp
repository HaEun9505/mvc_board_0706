<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글쓰기</title>
</head>
<body>
	<h2>게시판 글쓰기</h2>
	<hr>
	<!-- cellspacing :셀 간의 간격, cellpadding : 셀과 글자 사이의 간격 -->
	<table width="500" cellpadding="0" cellspacing="0" border="1">
		<form action="write.do" method="post">
		<tr>
			<td>제목</td>
			<td><input type="text" name="btitle" size="60"></td>
		</tr>
		<tr>
			<td>작성자</td>
			<td><input type="text" name="bname" size="60"></td>
		</tr>
		<tr>
			<td>내용</td>
			<td>
				<textarea rows="10" cols="50" name="bcontent"></textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="글 등록">&nbsp;&nbsp;
				<input type="button" value="글 목록" onclick="location.href='list.do'">
			</td>
		</tr>
		</form>
	</table>
</body>
</html>