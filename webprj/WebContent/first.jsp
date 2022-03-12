<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% int ran = (int)(Math.random()*9+1); %>
안녕하세요! 오늘의 행운 숫자는 <%=ran %> 입니다. 좋은 하루 되세요.
</body>
</html>