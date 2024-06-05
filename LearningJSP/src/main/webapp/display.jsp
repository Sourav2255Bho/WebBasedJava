<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% 
		String email = request.getParameter("em");
		String pass = request.getParameter("pass");
		//send the same to clnt
		out.print("<h5>Email : "+email+"</h5>");
		out.print("<h5>Password : "+pass+"</h5>");
	%>
	<!-- Scriplets -->
	<h1>Testing Scriplets</h1>
	<h1>Email : 
		<%
		out.print(email);
		%>
	</h1>
	<h1>Password : 
		<%
		out.print(pass);
		%>
	</h1>
	<h1>
		<%=
			12*34*100
		%>
	</h1>
	
	<hr>
	<!-- JSP Expressions -->
	<h1>Testing JSP Expression</h1>
	<h5>Email : <%= request.getParameter("em") %></h5>
	<h5>PassWord : <%= request.getParameter("pass") %></h5>
	
	<hr>
	<!-- Expression Language Syntax ${expr to evaluate} will evaluate the expr to string and then send it to the client browser -->
	<h1>Testing El Syntax</h1>
	<h5>Email : ${param.em}</h5>
	<!-- It is an unordered, unsorted map (like Hash map) -->
	
</body>
</html>