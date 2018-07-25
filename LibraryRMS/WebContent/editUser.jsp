<%@page import="com.mitrais.bootcamp.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String alamat = request.getParameter("address");
	boolean block_status = Integer.parseInt(request.getParameter("block_status"))==1?true:false;
	boolean login_status = Integer.parseInt(request.getParameter("login_status"))==1?true:false;
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
  		<a href="bookList.jsp" class="w3-bar-item w3-button">Book</a>
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
  				<h1>Add User</h1>
			</div>
			<form class="w3-container" id="editForm" action="user" method="post">
				<label class="w3-text-teal">Fullname</label>
				<input class="w3-input" type="text" name="fullname" value="<%=request.getParameter("fullname") %>" required>
				<label class="w3-text-teal">Address</label>
				<input class="w3-input" type="text" name="address" maxlength="50" size="100" value="<%=alamat%>" required>
				<label class="w3-text-teal">Username</label>
				<input class="w3-input" type="text" name="user" value="<%=request.getParameter("user") %>" required>
				<label class="w3-text-teal">Password</label>
				<input class="w3-input" type="password" name="pass" required>
				<label class="w3-text-teal">Birthdate</label>
				<input class="w3-input w3-margin-bottom" type="date" name="dob" value="<%=request.getParameter("dob")%>" required>
				<label class="w3-text-teal">Pin Try</label>
				<input class="w3-input w3-margin-bottom" type="number" name="pin_try" style="width:10%" value="<%=request.getParameter("pin_try")%>">
				<label class="w3-text-teal">Login Status</label>
				<select class="w3-input w3-margin-bottom" name="login_status" required>
					<%if(login_status){
						out.println("<option value=\"1\" selected=\"selected\">Login</option>");
						out.println("<option value=\"0\" >Logout</option>");
					}else{
						out.println("<option value=\"1\" >Login</option>");
						out.println("<option value=\"0\" selected=\"selected\">Logout</option>");
					}
						%>
				</select>
				<%-- <input class="w3-input w3-margin-bottom" type="checkbox" name="login_status" checked="<%=login_status%>"> --%>
				<label class="w3-text-teal">Block Status</label>
				<select class="w3-input w3-margin-bottom" name="block_status" required>
					<%if(block_status){
						out.println("<option value=\"1\" selected=\"selected\">Blocked</option>");
						out.println("<option value=\"0\" >Unblock</option>");
					}else{
						out.println("<option value=\"1\" >Blocked</option>");
						out.println("<option value=\"0\" selected=\"selected\">Unblock</option>");
					}
						%>
				</select>
				<%-- <input class="w3-input w3-margin-bottom" type="checkbox" name="block_status" checked="<%=login_status%>"> --%>
				<input class="w3-input w3-margin-bottom w3-btn w3-green" type="submit" value="Edit">
				<input class="w3-input" type="hidden" name="action" value="2">
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
	function validateForm() {
	    var x = document.forms["editForm"]["fullname"].value;
	    if (x == "") {
	        alert("Name must be filled out");
	        return false;
	    }
	}
	</script>
</body>
</html>