<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Test1</title>
</head>
<body>
<%
int result =100/0;
pageContext.setAttribute("res", result);
%>
<h5>Result : ${pageScope.res} </h5>
<!-- ${res} This will also take the same time cause pageScope is the first place the WC Checks the attribute -->
</body>
</html>