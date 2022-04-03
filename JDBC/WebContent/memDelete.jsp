<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"
		 import="org.kgitbank.emp.model.*"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 삭제 페이지</title>
</head>
<body>
<br><br>
<h3>${userId}</h3>의 데이터를 정말 삭제하시겠습니까?
<br><br>
<form action="/JDBC/Mem.do" method="post">
<input type="hidden" name="action" value="delete">
탈퇴하시려면 비밀번호를 입력해 주세요.<br>
비밀번호 : <input type="password" name="password">&nbsp;
<input type="submit" value="삭제"><input type="reset" value="취소" onclick="history.back();">
<br>
${message}
</form>
</body>
</html>