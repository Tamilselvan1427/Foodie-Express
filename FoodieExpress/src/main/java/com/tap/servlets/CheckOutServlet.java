package com.tap.servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Map;

import com.tap.daoimpl.Cart;
import com.tap.daoimpl.OrderDAOImpl;
import com.tap.daoimpl.OrderItemDAOImpl;
import com.tap.model.CartItem;
import com.tap.model.Order;
import com.tap.model.OrderItem;
import com.tap.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/checkOutServlet")
public class CheckOutServlet extends HttpServlet{
	private OrderDAOImpl orderDAOImpl;
	OrderItemDAOImpl orderItemDAOImpl;
	
	@Override
	public void init() throws ServletException {
		 orderDAOImpl = new OrderDAOImpl();
		 orderItemDAOImpl = new OrderItemDAOImpl();
	}
	
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession session = request.getSession();
		Cart cart = (Cart)session.getAttribute("cart");
		User user = (User)session.getAttribute("user");
		
		if(cart!=null && user!=null) {
			String address = request.getParameter("address");
			String paymentMethod = request.getParameter("payment");
			
			Map<Integer, CartItem> items = (Map<Integer, CartItem>) cart.getCart();
		    double total = 0;
		    for (CartItem item : items.values()) {
		        total += item.getPrice() * item.getQuantity();
		    }
			
			Order order = new Order();
			order.setUserId(user.getUserId());
			order.setRestaurantId((int)session.getAttribute("restaurantId"));
			order.setOrderDate(new Timestamp(System.currentTimeMillis()));
			order.setStatus("Delivered");
			order.setPaymentMode(paymentMethod);
			order.setTotalAmount(total);
			
			orderDAOImpl.addOrder(order);
			int orderId = order.getOrderId();
			
			
			for (CartItem item : items.values()) {

			    int menuId = item.getItemId();     
			    int quantity = item.getQuantity();
			    double price = item.getPrice();
			    String itemName = item.getItemName();
			    String imagePath = item.getImagePath();
			    

			    double totalPrice = price * quantity;

			    OrderItem orderItem = new OrderItem(orderId, menuId,  quantity, totalPrice, itemName, imagePath);

			    orderItemDAOImpl.addOrderItem(orderItem);
			}

			
			session.setAttribute("order", order);
			session.removeAttribute("cart");
			
			response.sendRedirect("orderSuccess.jsp?orderId=" + orderId);

			
		}
		else {
			response.sendRedirect("login.jsp");
		}
		
		
		
		
		
		
		
		
		
		

		
		
		
	}

}
