<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Your Profile - SOCIAL MEDIA</title>
	<style type = "text/css">
		<%@ include file = "main.css"%>
	</style>
</head>
<body>
<center>
	<div id = "p">
		<div id = "picDiv">
			<img id = "profilePic" src = "data:image/jpg;base64,${basedPic}"/>
		</div>
		<div><h1>
			${first} ${last}
			
			</h1>
		</div>
		<br>
		<div><h5>
			${college}
			</h5>
		</div>
		<div>
			<h5>
			${highschool}
			</h5>
		</div>
		<div>
			<h5>
			${hometown}
			</h5>
		</div>
		<div>
			<h5>
			${dob}
			</h5>
		</div>
		<div>
			<h5>
			${email}
			</h5>
		</div>
		<div>
			<h5>
			${phone_number}
			</h5>
		</div>
		<div>
			<h5>
			${highschool}
			</h5>
		</div>
	</div>
	
	<div id = "logOutDiv">
		<form action = "logout" method = "post">
			<div class = form-actions>					
				<input type="submit" value="Log Out" class = "myButton" name = "logout">			
			</div>
		</form>		
	</div>
	<div id = "profileButtonDiv">
		<form action = "viewEdit" method = "post">
			<div class = form-actions>
				<input type = "submit" value = "Edit Profile" class = "myButton" name = "submit"/>		
			</div>	
		</form>
	</div>	
</center>
</body>
</html>