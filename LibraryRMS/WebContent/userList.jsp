<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.mitrais.bootcamp.model.User"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>Master User</title>
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
	<a href="addUser.jsp" class="w3-button w3-red">add</a>
	
	<table class="w3-table-all w3-hoverable" border="1">

		<tr class="w3-blue">
			<td>Username</td>
			<td>Fullname</td>
			<td>Address</td>
			<td>Birthdate</td>
			<td>Login</td>
			<td>Blocked</td>
			<td>&nbsp</td>
		</tr>
		<%
			ArrayList arrayList = (ArrayList) request.getAttribute("listUser");
			if(arrayList!=null){

				for (Iterator iterator = arrayList.iterator(); iterator.hasNext();) {
					User element = (User) iterator.next();

					out.println("<tr>");
					out.println("<td>" + element.getUsername() + "</td>");
					out.println("<td>" + element.getFullName() + "</td>");
					out.println("<td>" + element.getAlamat() +"</td>");
					out.println("<td>" + element.getBirthdate().toString() +"</td>");
					out.println("<td>" + (element.getLogin_status()==1 ? "true":"false") +"</td>");
					out.println("<td>" + (element.getBlock_status()==1 ? "true":"false") +"</td>");
					/* out.println("<td><form action=\"user\" method=\"post\"><a class=\"w3-button w3-small w3-blue w3-hover-grey w3-margin-right\" href=\"editUser.jsp?user="+element.getUsername()+"&address="+element.getAlamat()+
							"&fullname="+element.getFullName()+"&dob="+(new SimpleDateFormat("yyyy-MM-dd")).format(element.getBirthdate())+"&pin_try="+element.getPin_try()+
							"&block_status="+element.getBlock_status()+"&login_status="+element.getLogin_status()+"\">Edit</a>"); */
					
					out.println("<td><form action=\"editUser.jsp\" method=\"post\">"+
							"<input type=\"hidden\" name=\"fullname\" value=\""+element.getFullName()+"\">"+
							"<input type=\"hidden\" name=\"dob\" value=\""+(new SimpleDateFormat("yyyy-MM-dd")).format(element.getBirthdate())+"\">"+
							"<input type=\"hidden\" name=\"address\" value=\""+element.getAlamat()+"\">"+
							"<input type=\"hidden\" name=\"pin_try\" value=\""+element.getPin_try()+"\">"+
							"<input type=\"hidden\" name=\"login_status\" value=\""+element.getLogin_status()+"\">"+
							"<input type=\"hidden\" name=\"block_status\" value=\""+element.getBlock_status()+"\">"+
							"<input type=\"hidden\" name=\"action\" value=\"2\">"+
							"<input type=\"hidden\" name=\"user\" value=\""+element.getUsername()+"\">"+
							"<input class=\"w3-button w3-small w3-blue w3-hover-grey w3-margin-right\" type=\"submit\" value=\"Edit\"></form>"+
							"<form action=\"user\" method=\"post\">"+
							"<input type=\"hidden\" name=\"action\" value=\"3\">"+
							"<input type=\"hidden\" name=\"user\" value=\""+element.getUsername()+"\">"+
							"<input class=\"w3-button w3-small w3-blue w3-hover-grey w3-margin-right\" type=\"submit\" value=\"Delete\">"+
							"</form>");
					out.println("</td>");
				
				/* 
				"<a class=\"w3-button w3-blue w3-hover-grey w3-margin-right\" href =\"bookEditController?do=edit&id="
						+ element.getUsername() + "\">Edit</a><a class=\"w3-button w3-blue w3-hover-grey\" href =\"bookEditController?do=delete&id=" + element.getUsername()
						+ "\">Delete</a></td>"); */
					out.println("</tr>");
				}
			}
		%>
	</table>
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