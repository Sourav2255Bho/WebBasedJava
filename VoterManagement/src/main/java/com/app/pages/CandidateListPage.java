package com.app.pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.app.dao.CandidateDaoImpli;
import com.app.entities.Candidate;
import com.app.entities.User;

/**
 * Servlet implementation class CandidateListPage
 */
@WebServlet("/candidate_list")
public class CandidateListPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		
		try(PrintWriter pw = response.getWriter()){
			HttpSession session = request.getSession();
			
			User userInfo = (User)session.getAttribute("user_details");
			
			if(userInfo != null) {
				CandidateDaoImpli candidateDao = (CandidateDaoImpli)session.getAttribute("candidateDao");
				List<Candidate> candidateList = candidateDao.getAllCandidates();
				pw.print("<center><h1>Hello "+userInfo.getFirstName()+" "+userInfo.getLastName()+"</h1><h2>Candidate List</h2><form action='logout' method='post'>");
				for(Candidate c : candidateList) {
					pw.print("<div><input type='radio' name='candidate_id' value='"+c.getCanId()+"'>"+c.getCanName()+"</input></div>");
				}
				
				pw.print("<input type='submit' value='Cast A Vote'></input></form></center>");
			}
			
		} catch (SQLException e) {
			throw new ServletException("In Doget Method "+ getClass(), e);
		}
	}

}
