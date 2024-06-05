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
<%-- session.getAttribute("user_details").getUserName() sent to clnt using the out.print --%>
<h4>Hello, ${sessionScope.user_details.name}</h4>
<% session.invalidate(); %>
<h5>You Have Logged Out...</h5>
<h5>
	<a href="login.jsp">Visit Again</a>
</h5>
</body>
</html>