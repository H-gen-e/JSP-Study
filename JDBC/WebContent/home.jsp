<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"
		 import="org.kgitbank.emp.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
</head>
<body>
<h2>안녕하세요. 직원관리 시스템에 오신걸 환영합니다.</h2>
<c:if test="${!empty sessionScope.userId}">
1. <a href="/JDBC/Emp.do?action=list">사원 목록 조회</a><br>
2. <a href="/JDBC/Emp.do?action=insert">사원 정보 입력</a><br>
3. <a href="/JDBC/Login.do"><button>로그아웃</button></a><br>
4. <a href="/JDBC/Mem.do?acion=update"><button>회원 정보 수정</button></a><br>
5. <a href="/JDBC/Mem.do?action=delete"><button>회원 정보 탈퇴</button></a>
</c:if>

<c:if test="${empty sessionScope.userId}">
1. <a href="/JDBC/login.jsp?action=login">로그인</a><br>
2. <a href="/JDBC/memInsert.jsp">회원가입</a>
</c:if>
</body>
</html>  