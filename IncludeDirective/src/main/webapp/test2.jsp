<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>test2</title>
</head>
<body>
	<h5>From test2.jsp...</h5>
	<%-- create a page scope attribute to tore time stamp --%>
	<%
	pageContext.setAttribute("server_ts", LocalDateTime.now());
	%>
	<%-- use include directive --%>
	<%@ include file="test3.jsp" %>
</body>
</html>