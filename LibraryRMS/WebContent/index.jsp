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
<body>
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
	<h1>WELCOME ${requestScope['user']}</h1>
	
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