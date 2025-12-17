package com.tap.servlets;

import java.io.IOException;
import java.util.List;

import com.tap.daoimpl.OrderDAOImpl;
import com.tap.model.Order;
import com.tap.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/OrdersServlet")
public class OrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		if(user == null) {
			response.sendRedirect("login.html");
			return;
		}

		OrderDAOImpl dao = new OrderDAOImpl();
		List<Order> orders = dao.getAllOrderByUserId(user.getUserId());

		request.setAttribute("orders", orders);
		request.getRequestDispatcher("orders.jsp").forward(request, response);
	}
}
