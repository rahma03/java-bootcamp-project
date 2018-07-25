<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>Add User</title>
</head>
<body class="w3-display container">
	<div class="w3-sidebar w3-bar-block w3-border-right w3-teal" style="display:none" id="sideBar">
  		<button onclick="w3_close()" class="w3-bar-item w3-large">&times;</button>
  		<a href="user?" class="w3-bar-item w3-button">User</a>
  		<a href="book?" class="w3-bar-item w3-button">Data</a>
  		<a href="#" class="w3-bar-item w3-button">Link 3</a>
	</div>
	<div class="w3-teal">
		<div class="w3-bar w3-teal">
			<button class="w3-button w3-xlarge w3-bar-item" onclick="w3_open()"><i class="fa fa-bars"></i></button>
			<div class="w3-bar-item">RMS STUDY CASE - Library</div>
			<a href="login?" class="w3-bar-item w3-button w3-grey w3-right">Logout</a>
		</div>
	</div>
	
	<div class="w3-card-4 w3-display-middle" style="width:50%">
			<div class="w3-container w3-teal">
  				<h1>Add User</h1>
			</div>
			<form class="w3-container w3-border" action="user" method="post">
				<label class="w3-text-teal">Fullname</label>
				<input class="w3-input" type="text" name="fullname" required>
				<label class="w3-text-teal">Address</label>
				<input class="w3-input" type="text" name="address" required>
				<label class="w3-text-teal">Username</label>
				<input class="w3-input" type="text" name="user" required>
				<label class="w3-text-teal">Password</label>
				<input class="w3-input" type="password" name="pass" required>
				<label class="w3-text-teal">Birthdate</label>
				<input class="w3-input w3-margin-bottom" type="date" name="dob" required>
				<input class="w3-input w3-margin-bottom w3-btn w3-green" type="submit" value="Add">
				<input class="w3-input" type="hidden" name="action" value="1">
			</form>
	</div>
	
	<script>
	function w3_open() {
		document.getElementById("sideBar").style.display = "block";
		}
	function w3_close() {
		document.getElementById("sideBar").style.display = "none";
		}
	</script>
</body>
</html>