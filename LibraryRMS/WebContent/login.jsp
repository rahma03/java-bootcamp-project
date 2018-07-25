<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<title>Book Resource Management</title>
	</head>
	<body class="w3-display container" >
		<div class="w3-card-4 w3-teal w3-display-middle w3-padding-16">
			<div class="w3-container"><h1 class="w3-font-red">WELCOME TO RMS-LIBRARY</h1></div>
			<form class="w3-container " action="login" method="post">
				<label>Username</label>
				<input class="w3-input w3-margin-bottom" type="text" name="user">	
				<label>Password</label>
				<input class="w3-input w3-margin-bottom" type="password" name="pass">
				<input class="w3-input w3-margin-bottom" type="submit" value="Login">
			</form>
		</div>
	</body>
</html>