package com.tap.servlets;

import java.io.IOException;

import com.tap.daoimpl.Cart;
import com.tap.daoimpl.MenuDAOImpl;
import com.tap.model.CartItem;
import com.tap.model.Menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/CartServlet")
public class CartServelt extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

      
        Cart cart = (Cart) session.getAttribute("cart");

       
        String restaurantIdParam = request.getParameter("restaurantId");
        int newRestaurantId = -1;

        if (restaurantIdParam != null && !restaurantIdParam.isEmpty()) {
            try {
                newRestaurantId = Integer.parseInt(restaurantIdParam);
            } catch (NumberFormatException e) {
                newRestaurantId = -1;
            }
        } else {
            Integer savedId = (Integer) session.getAttribute("restaurantId");
            newRestaurantId = (savedId != null) ? savedId : -1;
        }

       
        Integer currentRestaurantIdObj = (Integer) session.getAttribute("restaurantId");
        int currentRestaurantId = (currentRestaurantIdObj != null) ? currentRestaurantIdObj : -1;

        // Create or reset cart when switching restaurants
        if (cart == null || currentRestaurantId != newRestaurantId) {
            cart = new Cart();
            session.setAttribute("cart", cart);
            session.setAttribute("restaurantId", newRestaurantId);
            System.out.println("New cart created or restaurant switched: " + newRestaurantId);
        }

        String action = request.getParameter("action");

        if ("add".equalsIgnoreCase(action)) {
            addItemToCart(request, cart);
        } else if ("update".equalsIgnoreCase(action)) {
            updateCartItem(request, cart);
        } else if ("remove".equalsIgnoreCase(action)) {
            removeItemFromCart(request, cart);
        }
        else if (action.equalsIgnoreCase("clearAll")) {
            cart.clearAllItems();
        }


        response.sendRedirect("cart.jsp");
    }

    private void addItemToCart(HttpServletRequest request, Cart cart) {
        try {
            int itemId = Integer.parseInt(request.getParameter("itemId"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            MenuDAOImpl menuDAOImpl = new MenuDAOImpl();
            Menu menu = menuDAOImpl.getMenu(itemId);

            if (menu != null) {
                String itemName = menu.getItemName();
                double price = menu.getPrice();

                String imagePath = menu.getImagePath(); // from DB

                CartItem item = new CartItem(
                        itemId,
                        itemName,
                        price,
                        quantity,
                        imagePath
                );
                cart.addCartItem(item);

                

                System.out.println("Added to cart: " + itemName + " (Qty: " + quantity + ")");
            } else {
                System.out.println("Menu not found for itemId: " + itemId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error adding item to cart: " + e.getMessage());
        }
    }

    // Update quantity
    private void updateCartItem(HttpServletRequest request, Cart cart) {
        try {
            int itemId = Integer.parseInt(request.getParameter("itemId"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            cart.updateCartItem(itemId, quantity);
            System.out.println("Updated item " + itemId + " → Qty: " + quantity);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error updating item: " + e.getMessage());
        }
    }

    //Remove item from cart
    private void removeItemFromCart(HttpServletRequest request, Cart cart) {
        try {
            int itemId = Integer.parseInt(request.getParameter("itemId"));
            cart.removeCartItem(itemId);
            System.out.println("Removed item from cart: " + itemId);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error removing item: " + e.getMessage());
        }
    }
    
}
