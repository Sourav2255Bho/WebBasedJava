package com.app.pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

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
@WebServlet("/voter_register")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDaoImpl userDao; 

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		try {
			userDao = new UserDaoImpl();
		} catch (Exception e) {
			throw new ServletException("In init method "+ getClass(), e);
		}
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		try {
			userDao.cleanUp();
		} catch (Exception e) {
			System.out.println("In Destroy method");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		try(PrintWriter pw = response.getWriter()){
			String fname = request.getParameter("fn");
			String lname = request.getParameter("ln");
			String email = request.getParameter("em");
			String pass = request.getParameter("pass");
			String dob = request.getParameter("dob");
			if(LocalDate.parse(dob).isBefore(LocalDate.now().minusYears(21))){
				User newUser = new User(fname, lname, email, pass, Date.valueOf(LocalDate.parse(dob)));
				pw.print("<center><h1>"+userDao.voterRegistration(newUser)+"</h1><a href='login.html'>Return to Login Page</center>");
				
			}
			else {
				pw.print("<center><h1>Registration Unsuccessfull</h1><a href='login.html'>Retry</a></center>");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ServletException("In doPost method "+ getClass(), e);
		}
	}

}
