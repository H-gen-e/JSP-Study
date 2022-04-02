<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"
		 import="org.kgitbank.emp.model.EmpVO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원 목록 페이지</title>
</head>
<body>
<h2>사원 목록</h2>
<%List<EmpVO> empList = (List<EmpVO>)request.getAttribute("empList");%>
<table>
<tr>
<td>사원번호</td>
<td>이름</td>
<td>이메일</td>
<td>연락처</td>
</tr>
<%for(EmpVO emp : empList) { %>
<tr>
<td><%=emp.getEmployeeId() %></td>
<td><a href="/JDBC/Emp.do?action=view&empId=<%=emp.getEmployeeId()%>"><%=emp.getFirstName() %><%=emp.getLastName() %></a></td>
<td><%=emp.getEmail() %></td>
<td><%=emp.getPhoneNumber() %></td>
</tr>
<%} %>
</table>
</body>
</html>