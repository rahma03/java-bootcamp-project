<%@page import="com.mitrais.bootcamp.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String title = request.getParameter("title");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<base href="<%=basePath%>">
<title>Book edit page</title>
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
	<div class="w3-container w3-display-middle">
  		<div class="w3-card-4">
			<div class="w3-container w3-teal">
  				<h1>Edit Book</h1>
			</div>
			<form class="w3-container" id="editForm" action="book" method="post">
				<label class="w3-text-teal">Author</label>
				<input class="w3-input" type="text" name="author" value="<%=request.getParameter("author") %>" required>
				<label class="w3-text-teal">Title</label>
				<input class="w3-input" type="text" name="title" maxlength="50" size="100" value="<%=title%>" required>
				<label class="w3-text-teal">Stock</label>
				<input class="w3-input w3-margin-bottom" type="number" name="stock" style="width:10%" value="<%=request.getParameter("stock")%>">
				<input class="w3-input w3-margin-bottom w3-btn w3-green" type="submit" value="Edit">
				<input class="w3-input" type="hidden" name="action" value="2">
				<input class="w3-input" type="hidden" name="id" value="<%=request.getParameter("id")%>">
			</form>
		</div>
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