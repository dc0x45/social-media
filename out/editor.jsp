<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Edit your SOCIAL MEDIA</title>
		<style type = "text/css">
			<%@ include file = "main.css"%>
		</style>
	</head>
	<body>
	<center>
		<div>
			<h1>Edit Profile</h1>
		</div>
		<div>
				<form action = "edit" method ="post">
				
<!-- 					<label>Update Password<input type = "password" name = "hashedPassword" value = ${hashedPassword} /></label> -->
<!-- 					<label>First Name<input type = "text" name = "first" value = ${profile.first} /></label> -->
<!-- 					<label>Last Name<input type = "text" name = "last" value = ${profile.last} /></label>					 -->
<!-- 					<label>College Name<input type = "text" name = "college" value = ${profile.college} /></label>					 -->
<!-- 					<label>High School Name<input type = "text" name = "highschool" value = ${profile.highschool} /></label>					 -->
<!-- 					<label>Birthday<input type = "text" name = "dob" value = ${profile.dob.toString()} readonly/></label>										 -->
<!-- 					<label>Email Address<input type = "text" name = "email" value = ${profile.email} /></label>					 -->
<!-- 					<label>Phone Number<input type = "text" name = "phone_number" value = ${profile.phone_number} /></label>					 -->
<!-- 					<label>Last Login<input type = "text" name = "lastLogin" value = ${profile.lastLogin.toString()} readonly/></label>					 -->
<!-- 					<label>Last Modified<input type = "text" name = "lastEdit" value = ${profile.lastEdit.toString()} readonly /></label>					 -->
   						 
					<div class = form-actions>
						<input type = "submit" value = "Save" name = "submit"/>
					</div>		
				</form>	
				<form action = "UploadServlet" method = "post" enctype = "multipart/form-data">
					<label>Upload a new picture<input type="file" name="file"></label>	
					<div class = form-actions>
						<input type="submit" value="upload" name = "upload">		
					</div>
					 
				</form>		
		</div>
		</center>
	</body>	
</html>