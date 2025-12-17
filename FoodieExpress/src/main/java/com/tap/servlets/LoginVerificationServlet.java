package com.tap.servlets;

import java.io.IOException;

import com.tap.daoimpl.UserDAOImpl;
import com.tap.model.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/Login")
public class LoginVerificationServlet  extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		UserDAOImpl userDAOImpl = new UserDAOImpl();
		
		User user = userDAOImpl.getUser(email, password);
		
		response.setContentType("text/html");
		
		
		
		if (user != null) { 
			HttpSession session = request.getSession();
            session.setAttribute("user", user); // store user object in session

            response.sendRedirect("AfterLoginHome.jsp");
		} else {
			request.setAttribute("errorMessage", "Invalid email or password!!!");
		    RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		    rd.forward(request, response);
		    
		}
	}
}
