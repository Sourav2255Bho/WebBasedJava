<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h5>Session Id : ${cookie.JSESSIONID.value}</h5>
	<%-- <%= session.getId() %>--%>
	<h5><a href="login.jsp">User Login</a></h5>
	<h5>
		<a href="test1.jsp">Error Handling in JSP</a>
	</h5>
</body>
</html>