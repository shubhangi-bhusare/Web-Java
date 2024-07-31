package com.app.pages;

import static com.app.utils.DBUtils.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.app.dao.UserDaoImpl;
import com.app.entities.User;

/**
 * Servlet implementation class VoterRegistrationServlet
 */
//@WebServlet("/voter_register")
public class VoterRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDaoImpl userDao;

	/**
	 * @see Servlet#init()
	 */
	public void init() throws ServletException {
		try {
			ServletConfig config = getServletConfig();
			// open cn
			// open db connection once
			openConnection(config.getInitParameter("db_url"), config.getInitParameter("user_name"),
					config.getInitParameter("password"));

			// create dao instance
			userDao = new UserDaoImpl();
		} catch (Exception e) {
			throw new ServletException("err in init of " + getClass(), e);
		}
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		try {
			userDao.cleanUp();
			closeConnection();
		} catch (Exception e) {
			System.out.println("err in destroy of " + getClass());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. set cont type
		response.setContentType("text/html");
		try (PrintWriter pw = response.getWriter()) {
			// 2. get Http session
			HttpSession session = request.getSession();
			// 3 get req parameters
			String fName = request.getParameter("fn");
			String lName = request.getParameter("ln");
			String email = request.getParameter("em");
			String password = request.getParameter("pass");
			LocalDate birthDate = LocalDate.parse(request.getParameter("dob"));
			int age=Period.between(birthDate, LocalDate.now()).getYears();
			if(age >= 21) {
				//create voter instance
				User newVoter=new User(fName, lName, email, 
						password,Date.valueOf(birthDate));
				//assign the role as voter
			//	newVoter.setRole("voter"); not needed !!!!
				//invoke user dao's method for inserting voter details
				String regStatus = userDao.voterRegistration(newVoter);
				pw.print("<h5> "+regStatus+"</h5>");
				//discard session
				session.invalidate();
				pw.print("<h5> Proceed to <a href='login.html'>Login</a></h5>");
				
			} else {
				pw.print("<h5>Voter registration failed , Invalid Age !!!!!</h5>");
				pw.print("<h5> <a href='voter_registration.html'>Retry Registration</a></h5>");
				
			}

		} catch (Exception e) {
			throw new ServletException("err in do-post of "+getClass(), e);
		}
	}

}
