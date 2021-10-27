<%@page import="kr.hs.dgsw.java.web.service.SessionManagerMaker"%>
<%@page import="kr.hs.dgsw.java.web.service.SessionManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	SessionManager sessionManager = SessionManagerMaker.make();
	
	if (!sessionManager.isAuthorized(request)) {
		response.sendRedirect("login.jsp");
	}
	
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	님의 아이디는 <strong><%= sessionManager.getId(request) %></strong>입니다. <br>
	<a href="logout.do">로그아웃</a>  <%= session.getId() %>
</body>
</html>