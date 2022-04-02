<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"
		 import="org.kgitbank.emp.model.*"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원 정보 <%=request.getAttribute("emp")==null? "입력" : "수정" %></title>
</head>
<body>
<%List<JobVO> jobList = (List<JobVO>)request.getAttribute("jobList");
List<EmpVO> manList = (List<EmpVO>)request.getAttribute("manList");
List<DeptVO> deptList = (List<DeptVO>)request.getAttribute("deptList");
EmpVO emp = (EmpVO)request.getAttribute("emp"); 
%>

<h2>사원 정보 <%=emp==null ? "입력" : "수정" %></h2>
<form action="/JDBC/Emp.do" method="post">
<table>
<%if(emp==null) { %>

<tr><td>사원번호</td><td><input type="text" name="empId"></td></tr>
<tr><td>이름, 성</td><td><input type="text" name="firstName"><input type="text" name="lastName"></td></tr>
<tr><td>이메일</td><td><input type="text" name="email"></td></tr>
<tr><td>연락처</td><td><input type="text" name="phoneNumber"></td></tr>
<tr><td>입사일</td><td><input type="date" name="hireDate"></td></tr>
<tr><td>직무</td><td>
<select name="jobId">
<%for(JobVO job : jobList) {%>
<option value="<%=job.getJobId() %>"><%=job.getJobTitle() %>
<%} %>
</select></td></tr>
<tr><td>급여</td><td><input type="text" name="salary"></td></tr>
<tr><td>보너스율</td><td><input type="number" name="commissionPct" min="0" max="0.99" step="0.05"></td></tr>
<tr><td>매니저</td><td>
<select name="managerId">
<%for(EmpVO man : manList) {%>
<option value="<%=man.getEmployeeId() %>"><%=man.getFirstName() %>
<%} %>
</select></td></tr>
<tr><td>부서</td><td>
<select name="departmentId">
<%for(DeptVO dept : deptList) {%>
<option value="<%=dept.getDepartmentId() %>"><%=dept.getDepartmentName() %>
<%} %>
</select></td></tr>
<tr><td><input type="submit" value="입력"></td><td><input type="reset" value="취소"></td></tr>

<%} else { %>

<tr><td>사원번호</td><td><input type="text" name="empId" value="<%=emp.getEmployeeId()%>" readonly></td></tr>
<tr><td>이름, 성</td><td><input type="text" name="firstName" value="<%=emp.getFirstName()%>"><input type="text" name="lastName" value="<%=emp.getLastName()%>"></td></tr>
<tr><td>이메일</td><td><input type="text" name="email" value="<%=emp.getEmail()%>"></td></tr>
<tr><td>연락처</td><td><input type="text" name="phoneNumber" value="<%=emp.getPhoneNumber()%>"></td></tr>
<tr><td>입사일</td><td><input type="date" name="hireDate" value="<%=emp.getHireDate()%>"></td></tr>
<tr><td>직무</td><td>
<select name="jobId">
<%for(JobVO job : jobList) {%>
<option value="<%=job.getJobId() %>" <%=emp.getJobId().equals(job.getJobId()) ? "selected" : "" %>><%=job.getJobTitle() %>
<%} %>
</select></td></tr>
<tr><td>급여</td><td><input type="text" name="salary" value="<%=emp.getSalary()%>"></td></tr>
<tr><td>보너스율</td><td><input type="number" name="commissionPct" min="0" max="0.99" step="0.05" value="<%=emp.getCommissionPct()%>"></td></tr>
<tr><td>매니저</td><td>
<select name="managerId">
<%for(EmpVO man : manList) {%>
<option value="<%=man.getEmployeeId() %>" <%=emp.getManagerId()==man.getEmployeeId() ? "selected" : "" %>><%=man.getFirstName() %>
<%} %>
</select></td></tr>
<tr><td>부서</td><td>
<select name="departmentId">
<%for(DeptVO dept : deptList) {%>
<option value="<%=dept.getDepartmentId() %>" <%=emp.getDepartmentId()==dept.getDepartmentId() ? "selected" : "" %>><%=dept.getDepartmentName() %>
<%} %>
</select></td></tr>
<tr><td><input type="submit" value="수정"></td><td><input type="reset" value="취소"></td></tr>

<%} %>

</table>
<input type="hidden" name="action" value="<%=request.getAttribute("action")%>">
</form>
</body>
</html>