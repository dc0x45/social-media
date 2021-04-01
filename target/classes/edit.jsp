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
				
					<label>Update Password: <input type = "password" name = "hashedPassword" value = "" /></label><br>
					<label>First Name: <input type = "text" name = "first" value = ${first} /></label><br>
					<label>Last Name: <input type = "text" name = "last" value = ${last} /></label><br>
					<label>College Name: <input type = "text" name = "college" value = ${college} /></label><br>		
					<label>High School Name: <input type = "text" name = "highschool" value = ${highschool} /></label><br>			
					<label>Birthday: <input type = "text" name = "dob" value = ${dob} readonly/></label><br>					
					<label>Email Address: <input type = "text" name = "email" value = ${email} /></label><br>	
					<label>Phone Number: <input type = "text" name = "phone_number" value = ${phone_number} /></label><br>		
   						 
					<div class = form-actions>
						<input type = "submit" value = "Save" name = "submit"/>
					</div>		
					<br>
				</form>	
				<form action = "upload" method = "post" enctype = "multipart/form-data">
					<label>Upload new picture: <input type="file" name="basedPic"></label>	
					<div class = form-actions>
						<input type="submit" value="Upload" name = "upload">		
					</div>
					 
				</form>		
		</div>
		</center>
	</body>	
</html>