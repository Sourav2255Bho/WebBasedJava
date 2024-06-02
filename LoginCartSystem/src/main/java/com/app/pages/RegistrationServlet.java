package com.app.pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.dao.UserDaoImpl;
import com.app.entities.User;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet(value = "/register_validation", loadOnStartup = 1)
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDaoImpl userDao;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		try {
			userDao  =new UserDaoImpl();
		}catch(Exception e) {
			throw new ServletException("err in init of "+ getClass(), e);
		}
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		
		try {
			userDao.cleanUp();
		}catch (Exception e) {
			System.out.println("Err in Destroy !");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		try(PrintWriter pw = response.getWriter()){
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			if(userDao.registration(name, email, password)) {
				pw.print("<h1>Registration Successfull !!!</h1><a href='login.html'>Go Back</a>");
			}
			else {
				pw.print("<h1>Registration Unsuccessfull !!!</h1><a href='login.html'>Retry</a>");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ServletException("Err in doPost of"+ getClass(),e);
		}
	}

}
