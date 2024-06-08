<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%-- Create JB instance w/o java code : jsp:useBean --%>
<%-- session.setAttribute("user", new UserBean()) --%>
<jsp:useBean id="user" class="beans.UserBean" scope="session"/>
<%-- Invoke ALL MATCHING setters of JB --%>
<jsp:setProperty property="*" name="user"/>
<body>
<%-- Invoke B.L method of the JB w/o scriplet --%>>
<%-- session.getAttribute("user").authenticateUser() --%>
<h5> Navigational outcome : ${sessionScope.user.authenticateUser()} </h5>
</body>
</html>