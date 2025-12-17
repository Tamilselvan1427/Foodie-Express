package com.tap.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.tap.daoimpl.RestaurantDAOImpl;
import com.tap.model.Restaurant;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/restaurantServlet")
public class RestaurantServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		RestaurantDAOImpl restaurants = new RestaurantDAOImpl();
//		
//		List<Restaurant> allRestaurant = restaurants.getAllRestaurant();
//		
//		for(Restaurant res:allRestaurant) {
//			System.out.println(res.getImagePath());
//		}
		
		
//		response.setContentType("text/html");
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("restaurant.jsp");
		requestDispatcher.forward(request, response);
		
		
		
	
		
		
		
		
	}
	
	

}
