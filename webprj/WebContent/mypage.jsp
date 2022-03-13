<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
</head>
<body>
<%if(session.getAttribute("id")==null){
	response.sendRedirect("/login.jsp");
	} else { %>
		안녕하세요. <%=session.getAttribute("id") %>님 환영합니다.<br>
		<br><a href="Login.do"><button>로그아웃</button></a>
	<%} %>
</body>
</html>