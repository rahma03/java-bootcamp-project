<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<meta name="viewport" content="width = device-width, initial-scale = 1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add New Book</title>
</head>
<body>
	<div class="w3-sidebar w3-bar-block w3-border-right w3-teal" style="display:none" id="sideBar">
  		<button onclick="w3_close()" class="w3-bar-item w3-large">&times;</button>
  		<a href="user?" class="w3-bar-item w3-button">User</a>
  		<a href="book?" class="w3-bar-item w3-button">Book</a>
	</div>
	<div class="w3-teal">
		<div class="w3-bar w3-teal">
			<button class="w3-button w3-xlarge w3-bar-item" onclick="w3_open()"><i class="fa fa-bars"></i></button>
			<div class="w3-bar-item">RMS STUDY CASE - Library</div>
		</div>
	</div>
	
	<div class="w3-card-4 w3-display-middle" style="width:50%">
			<div class="w3-container w3-teal">
  				<h1>Add Book</h1>
			</div>
			<form class="w3-container w3-border" action="book" method="post">
				<label class="w3-text-teal">Author</label>
				<input class="w3-input" type="text" name="author" required>
				<label class="w3-text-teal">Title</label>
				<input class="w3-input" type="text" name="title" required>
				<label class="w3-text-teal">Stock</label>
				<input class="w3-input w3-margin-bottom" type="number" name="stock" style="width:10%" required>
				<input class="w3-input w3-margin-bottom w3-btn w3-green" type="submit" value="Add">
				<input class="w3-input" type="hidden" name="action" value="1">
			</form>
	</div>
</body>
</html>