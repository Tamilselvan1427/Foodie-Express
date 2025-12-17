package com.tap.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import com.tap.daoimpl.UserDAOImpl;
import com.tap.model.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html");
      
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String role = "User";

		Timestamp createdDate = new Timestamp(System.currentTimeMillis());
		Timestamp lastLoginDate = new Timestamp(System.currentTimeMillis());

		User user = new User(userName, password, email, phone, address, role, createdDate, lastLoginDate);

		UserDAOImpl userDAOImpl = new UserDAOImpl();
		int rowAffected = userDAOImpl.addUser(user);

		PrintWriter writer = response.getWriter();
		if (rowAffected == 1) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.html");
			requestDispatcher.forward(request, response);
			
		} else {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("register.html");
			requestDispatcher.forward(request, response);
		}
	}

}
