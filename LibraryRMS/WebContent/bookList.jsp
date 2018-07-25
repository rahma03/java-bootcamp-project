<%@page import="com.mitrais.bootcamp.model.Book"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*"%>
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
<title>List Of Book</title>
</head>
<body class="w3-light-teal">
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
	<a href="addBook.jsp" class="w3-button w3-red"><b>+</b></a>
	<table class="w3-table-all w3-hoverable" border="1">

		<tr class="w3-blue">
			<td>Author</td>
			<td>Book Name</td>
			<td>Stock</td>
			<td>&nbsp</td>
		</tr>
		<%
			ArrayList arrayList = (ArrayList) request.getAttribute("listBook");

			for (Iterator iterator = arrayList.iterator(); iterator.hasNext();) {
				Book element = (Book) iterator.next();

				out.println("<tr>");
				out.println("<td>" + element.getAuthor() + "</td>");
				out.println("<td>" + element.getTitle() + "</td>");
				out.println("<td>" + element.getStock() + "</td>");
				
				out.println("<td><form action=\"editBook.jsp\" method=\"post\">"+
						"<input type=\"hidden\" name=\"id\" value=\""+element.getId()+"\">"+
						"<input type=\"hidden\" name=\"author\" value=\""+element.getAuthor()+"\">"+
						"<input type=\"hidden\" name=\"title\" value=\""+element.getTitle()+"\">"+
						"<input type=\"hidden\" name=\"stock\" value=\""+element.getStock()+"\">"+
						"<input type=\"hidden\" name=\"action\" value=\"2\">"+
						"<input class=\"w3-button w3-small w3-blue w3-hover-grey w3-margin-right\" type=\"submit\" value=\"Edit\"></form>"+
						"<form action=\"book\" method=\"post\">"+
						"<input type=\"hidden\" name=\"action\" value=\"3\">"+
						"<input type=\"hidden\" name=\"id\" value=\""+element.getId()+"\">"+
						"<input class=\"w3-button w3-small w3-blue w3-hover-grey w3-margin-right\" type=\"submit\" value=\"Delete\">"+
						"</form>");
				out.println("</td>");
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