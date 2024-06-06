<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h5>From the next page</h5>
	<%-- Send product details to clnt --%>
	<h5>Product Details : ${requestScope.product_details}</h5>
	<h5>Param : ${param}</h5>
</body>
</html>