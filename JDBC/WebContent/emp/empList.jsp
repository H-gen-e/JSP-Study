<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"
		 import="org.kgitbank.emp.model.EmpVO" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원 목록 페이지</title>
</head>
<body>
<h2>사원 목록</h2>
<table>
<tr>
<td>사원번호</td>
<td>이름</td>
<td>이메일</td>
<td>연락처</td>
</tr>
<c:forEach var="emp" items="${empList}">
<tr>
<td>${emp.employeeId }</td>
<td><a href="/JDBC/Emp.do?action=view&empId=${emp.employeeId }">${emp.firstName}${emp.lastName }</a></td>
<td>${emp.email }</td>
<td>${emp.phoneNumber}</td>
</tr>
</c:forEach>
</table>
</body>
</html>