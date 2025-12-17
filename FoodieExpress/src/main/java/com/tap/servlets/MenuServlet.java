package com.tap.servlets;

import java.io.IOException;
import java.util.List;

import com.tap.daoimpl.MenuDAOImpl;
import com.tap.daoimpl.RestaurantDAOImpl;
import com.tap.model.Menu;
import com.tap.model.Restaurant;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/MenuServlet")
public class MenuServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int restaurantId = Integer.parseInt(request.getParameter("restaurantId"));
		
		
		
		MenuDAOImpl menuDAOImpl = new MenuDAOImpl();
		List<Menu> allMenuByRestaurantId = menuDAOImpl.getAllMenuByRestaurantId(restaurantId);
		
		RestaurantDAOImpl restaurants = new RestaurantDAOImpl();
		
		Restaurant restaurant = restaurants.getRestaurant(restaurantId);
		
		request.setAttribute("allMenuByRestaurantId", allMenuByRestaurantId);
		request.setAttribute("restaurant", restaurant);
		
		
		
		
//		for(Menu menu:allMenuByRestaurantId) {
//			System.out.println(menu);
//		}
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("menu.jsp");
		requestDispatcher.forward(request, response);
		
		
		
		
		
		
		
	}

}
