<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String reason = request.getParameter("reason");
	if (reason == null) {
		reason = "";
	}
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<p>회원 가입에 실패했습니다.</p>
	<p><%= reason %></p>
	
</body>
</html>