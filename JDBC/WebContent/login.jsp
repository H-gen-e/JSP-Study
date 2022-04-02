<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
</head>
<body>
<h3>로그인</h3>
<form action="/JDBC/Login.do" method="post">
아이디 : <input type="text" name="userId"><br>
비밀번호 : <input type="password" name="password"><br>
<input type="submit" value="로그인"><input type="reset" value="취소">
</form>
<%=request.getAttribute("message")==null ? "" : request.getAttribute("message") %>
</body>
</html>