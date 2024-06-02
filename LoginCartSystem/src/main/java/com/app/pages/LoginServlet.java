package com.app.pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.dao.UserDaoImpl;
import com.app.entities.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(value = "/login-validation", loadOnStartup = 1)
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDaoImpl userDao;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		try {
			userDao = new UserDaoImpl();
		}catch(Exception e){
			System.out.println(e);
			
			throw new ServletException("err in init of " + getClass(), e);
		}
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		try {
			userDao.cleanUp();
		}catch(Exception e) {
			System.out.println("err in destroy !");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		try (PrintWriter pw = response.getWriter()){
			String email = request.getParameter("email");
			String pass = request.getParameter("password");
			
			User user = userDao.signIn(email, pass);
			if(user == null) {
				pw.print("<h1>Invalid user login</h1> <a href='login.html'>Retry</a>");
			}else {
				Cookie c1 = new Cookie ("user_id", ((Integer)user.getId()).toString());
				Cookie c2 = new Cookie("user_email", user.getUsername());
			}
		} catch (Exception e) {
			throw new ServletException("in do Post "+ getClass(), e);
		}
	}

}
