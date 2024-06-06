<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h5>From 1st page...</h5>
<%-- create request scope attribute to store product --%>
<% 
request.setAttribute("product_details", request.getParameter("pid")+":"+request.getParameter("pName"));
%>
<%-- Server Pull : Forward --%>
<jsp:forward page="test5.jsp"/>
</body>
</html>