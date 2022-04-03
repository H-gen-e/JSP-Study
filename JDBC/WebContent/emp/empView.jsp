<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원 상세 정보</title>
</head>
<body>
<h2>사원 상세 정보</h2>
<table>
<tr><td>사원번호</td><td>${emp.employeeId}</td></tr>
<tr><td>이름</td><td>${emp.firstName}${emp.lastName}</td></tr>
<tr><td>이메일</td><td>${emp.email}</td></tr>
<tr><td>연락처</td><td>${emp.phoneNumber }</td></tr>
<tr><td>입사일</td><td>${emp.hireDate }</td></tr>
<tr><td>직무</td><td>${emp.jobId }</td></tr>
<tr><td>보너스율</td><td>${emp.commissionPct }</td></tr>
<tr><td>급여</td><td>${emp.salary }</td></tr>
<tr><td>매니저</td><td>${emp.managerId }</td></tr>
<tr><td>부서</td><td>${emp.departmentId }</td></tr>
</table>
<a href="/JDBC/Emp.do?action=update&empId=${emp.employeeId }">사원 정보 수정</a> 
<a href="/JDBC/Emp.do?action=delete&empId=${emp.employeeId }">사원 정보 삭제</a>
</body>
</html>