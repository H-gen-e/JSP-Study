<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.kgitbank.emp.model.EmpVO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원 상세 정보</title>
</head>
<body>
<h2>사원 상세 정보</h2>
<%EmpVO emp = (EmpVO)request.getAttribute("emp");%>
<table>
<tr><td>사원번호</td><td><%=emp.getEmployeeId() %></td></tr>
<tr><td>이름</td><td><%=emp.getFirstName() %><%=emp.getLastName() %></td></tr>
<tr><td>이메일</td><td><%=emp.getEmail() %></td></tr>
<tr><td>연락처</td><td><%=emp.getPhoneNumber() %></td></tr>
<tr><td>입사일</td><td><%=emp.getHireDate() %></td></tr>
<tr><td>직무</td><td><%=emp.getJobId() %></td></tr>
<tr><td>보너스율</td><td><%=emp.getCommissionPct()%></td></tr>
<tr><td>급여</td><td><%=emp.getSalary() %></td></tr>
<tr><td>매니저</td><td><%=emp.getManagerId() %></td></tr>
<tr><td>부서</td><td><%=emp.getDepartmentId() %></td></tr>
</table>
<a href="/JDBC/Emp.do?action=update&empId=<%=emp.getEmployeeId()%>">사원 정보 수정</a> <a href="?JDBC/Emp.do?action=delete">사원 정보 삭제</a>
</body>
</html>