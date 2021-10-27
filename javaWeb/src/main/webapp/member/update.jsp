<%@page import="kr.hs.dgsw.java.web.service.MemberServiceImpl"%>
<%@page import="kr.hs.dgsw.java.web.service.MemberService"%>
<%@page import="kr.hs.dgsw.java.web.domain.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String sMemberIdx = request.getParameter("memberIdx");
	int memberIdx = Integer.parseInt(sMemberIdx);
	Member member = null;
	
	try {
		MemberService service = new MemberServiceImpl();
		System.out.println("memberIDx " + memberIdx);
		
		member = service.getMember(memberIdx);
		
	} catch (Exception e) {
		
	}
	
	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 변경</title>

<style>
	h1 {
		width: 400px;
		margin: 10px auto 10px auto;
	}

	.wrap {
		width: 400px;
		border: 1px solid #CCC;
		padding: 10px;
		margin: 15px auto 10px auto;
	}
	
	.wrap>div {
		padding: 5px;
	}	
	
	.wrap>div>label {
		display: inline-block;
		width: 120px;
		margin-right: 10px;
	}

</style>

</head>
<body>

	<h1>회원 정보 변경</h1>
	
	<form method="POST" action="update.do">
		<div class="wrap">
			<input type="hidden" name="memberIdx" value="<%= member.getMemberIdx() %>">
			
			<div>
				<label>아이디</label>
				<input type="text" name="email" value="<%= member.getEmail() %>" readonly>
			</div>
	
			<div>
				<label>이름</label>
				<input type="text" name="name" value="<%= member.getName() %>">
			</div>
	
			<div>
				<label>연락처</label>
				<input type="text" name="contact" value="<%= member.getContact() %>">
			</div>
	
			<div>
				<label>생년월일</label>
				<input type="date" name="birthday" value="<%= member.getBirthday() %>">
			</div>
			
			<div>
				<label></label>
				<input type="submit" value="정보변경">
			</div>
		
		</div>	
	</form>

</body>
</html>