<%@page import="pojos.User"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%!
// JSP Declaration Block 
HashMap<String, User> users;
public void jspInit()
{
		System.out.println("in jsp init");
		//create empty map 
		users = new HashMap<>();
		//populate the same
		users.put("Tanvi", new User("Tanvi", "tanvi04", 23));
		users.put("Sourav",new User("Sourav", "sourav25", 24));
}
// jspDestroy can be overriden either from the same jsp declaration block or can create another one
%>
<body>
<%
//read name and password from request parameters
String name = request.getParameter("name");
String pass = request.getParameter("pass");
//validation
User  validatedUser = users.get(name);
if(validatedUser != null)
{
	if(validatedUser.getPassword().equals(pass))
	{
		//valid login : store user details uder session scope
		session.setAttribute("user_details", validatedUser);
		//clnt pull II : redirect + URL rewritting
		//Method of HttpServletResponse : public String encodeRedirectURL(String url)
		//response.sendRedirect(response.encodeRedirectURL("details.jsp"));
		RequestDispatcher rd = request.getRequestDispatcher("details.jsp");
		rd.forward(request, response);
	}else{
		%>
		<h5> 
		Invalid Password, <a href="login.jsp">Retry</a>
		</h5>
		<% }
		
}else {
%>
<h5> 
User Name Does Not Exist, Do You Want to <a href="register.jsp">Register</a>
</h5>	
<%
}
%>
</body>
<%!
public void jspDestroy()
{
	System.out.println("int jspDestroy");	
}  
%>
</html>