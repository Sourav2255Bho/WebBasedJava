<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<center>
<h1 style="color : green ;">${sessionScope.user.mesg}</h1>
<h3> Hello , ${sessionScope.user.validatedUser.name} </h3>
<h5>You have logged in as ${fn:toLowerCase(sessionScope.user.validatedUser.userRole)}</h5>
</center>
</body>
</html>