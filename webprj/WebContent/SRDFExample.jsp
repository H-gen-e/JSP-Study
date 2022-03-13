<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>SendRedirect</h3>
<form action="/SRDF.do" method="post">
이름 : <input type="text" name="name"><br>
비밀번호 : <input type="password" name="pw"><br>
<input type="hidden" name="action" value="sr"> <!-- 동일한  서블릿의 url을 사용하는 서로 다른 동작을 구분하기 위한 파라미터. --> 
<input type="submit" value="전송">
</form><br><br>

<h3>RequestDispatcherforward</h3>
<form action="/SRDF.do" method="post">
이름 : <input type="text" name="name"><br>
비밀번호 : <input type="password" name="pw"><br>
<input type="hidden" name="action" value="df"> <!-- 동일한  서블릿의 url을 사용하는 서로 다른 동작을 구분하기 위한 파라미터. -->  
<input type="submit" value="전송">
</form>
</body>
</html>