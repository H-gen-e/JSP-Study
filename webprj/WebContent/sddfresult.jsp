<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>sddf 결과 페이지</title>
</head>
<body>
<!-- 내장객체 request 를 사용하여 request 에 있는 파라미터를 받아와서 출력. -->
이름 : <%=request.getParameter("name") %><br>
비밀번호 : <%=request.getParameter("pw") %>
</body>
</html>