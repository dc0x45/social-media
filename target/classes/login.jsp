<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<style type = "text/css">
		<%@ include file = "main.css"%>
	</style>
<title>Login to SOCIAL MEDIA</title>
</head>
<body>
<center>
	<div>
		<h1>SOCIAL MEDIA, INC.</h1>
		<form action = "entity" method = "post">
			<div>
				<label>Username: </label><input type = "text" name = "username"/>
			</div>
			<br>
			<div>
				<label>Password: </label><input type = "password" name = "password" />
			</div>
			<br>
			<div class = form-actions>
				<input type = "submit" value = "Log In" name = "submit"/>		
			</div>	
		</form>
	</div>
</center>
</body>
</html>