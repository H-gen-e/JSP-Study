<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"
		 import="org.kgitbank.emp.model.*"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원 정보 ${empty emp? "입력" : "수정"}</title>
</head>
<body>

<h2>사원 정보 ${empty emp? "입력" : "수정"}</h2>
<form action="/JDBC/Emp.do" method="post">
<table>
<tr><td>사원번호</td><td><input type="text" name="empId" value="${emp.employeeId }" ${empty emp? "" : "readonly" }></td></tr>
<tr><td>이름, 성</td><td><input type="text" name="firstName" value="${emp.firstName }"><input type="text" name="lastName" value="${emp.lastName }"></td></tr>
<tr><td>이메일</td><td><input type="text" name="email" value="${emp.email }"></td></tr>
<tr><td>연락처</td><td><input type="text" name="phoneNumber" value="${emp.phoneNumber }"></td></tr>
<tr><td>입사일</td><td><input type="date" name="hireDate" value="${emp.hireDate }"></td></tr>
<tr><td>직무</td><td>
<select name="jobId">
<c:forEach var="job" items="${jobList}">
<option value="${job.jobId }" ${emp.jobId eq job.jobId? "selected" : ""}>${job.jobTitle }
</c:forEach>
</select></td></tr>
<tr><td>급여</td><td><input type="text" name="salary" value="${emp.salary }"></td></tr>
<tr><td>보너스율</td><td><input type="number" name="commissionPct" min="0" max="0.99" step="0.05" value="${emp.commissionPct }"></td></tr>
<tr><td>매니저</td><td>
<select name="managerId">
<c:forEach var="man" items="${manList}">
<option value="${man.employeeId }" ${emp.managerId eq man.employeeId ? "selected" : ""}>${man.firstName }
</c:forEach>
</select></td></tr>
<tr><td>부서</td><td>
<select name="departmentId">
<c:forEach var="dept" items="${deptList }">
<option value="${dept.departmentId }" ${emp.departmentId eq dept.departmentId ? "selected" : ""}>${dept.departmentName }
</c:forEach>
</select></td></tr>
<tr><td><input type="submit" value="입력"></td><td><input type="reset" value="취소"></td></tr>
</table>
<input type="hidden" name="action" value="${action }">
</form>
</body>
</html>