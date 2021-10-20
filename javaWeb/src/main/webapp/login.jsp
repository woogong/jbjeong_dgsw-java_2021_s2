<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	label {
		display: inline-block;
		width: 120px;
		margin-right: 10px;
		text-align: right;
	}
	
	div {
		margin: 10px;
	}
	
</style>
</head>
<body>

	<form method="post" action="login.do">
		<div>
			<div>
				<label>아이디 </label>
				<input type="text" name="id">
			</div>
			<div>
				<label>비밀번호 </label>
				<input type="password" name="password">
			</div>
			<div>
				<label> </label>
				<input type="submit" value="로그인">
			</div>
		</div>
	</form>

</body>
</html>