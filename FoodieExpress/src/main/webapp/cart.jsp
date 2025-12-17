<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.tap.model.CartItem" %>
<%@ page import="com.tap.daoimpl.Cart" %>

<%
    Cart cart = (Cart) session.getAttribute("cart");
    if (cart == null) {
        cart = new Cart();
        session.setAttribute("cart", cart);
    }

    Map<Integer, CartItem> items = (Map<Integer, CartItem>) cart.getCart();
    double total = 0;
    Integer restaurantId = (Integer) session.getAttribute("restaurantId");
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>FoodieExpress | My Cart</title>
  <link rel="stylesheet" href="cart.css">
</head>
<body>

  <!-- ================= NAVBAR ================= -->
  <header class="navbar">
    <div class="logo">🍴 FoodieExpress</div>
    <nav class="nav-links">
      <a href="AfterLoginHome.jsp">Home</a>
      <a href="restaurantServlet">Restaurants</a>
      <a href="login.html" class="logout">Logout</a>
    </nav>
  </header>

  <!-- ================= MAIN CART SECTION ================= -->
  <main class="cart-wrapper">

    <% if (items == null || items.isEmpty()) { %>
      <div class="empty-cart">
        <img src="https://cdn-icons-png.flaticon.com/512/11329/11329060.png" alt="Empty Cart">
        <h2>Your Cart is Empty</h2>
        <p>Looks like you haven’t added anything yet!</p>
        <a href="restaurantServlet" class="btn link">🍽️ Browse Restaurants</a>
      </div>
    <% } else { %>

    <div class="cart-container">
      <!-- LEFT SIDE (CART ITEMS) -->
      <section class="cart-left">
        <div class="cart-header">
          <h2>Your Order</h2>
          <form action="CartServlet" method="post">
            <input type="hidden" name="action" value="clearAll"/>
            <input type="hidden" name="restaurantId" value="<%= restaurantId %>"/>
            <button type="submit" class="btn danger small">Clear All</button>
          </form>
        </div>

        <% for (CartItem item : items.values()) {
             double itemTotal = item.getPrice() * item.getQuantity();
             total += itemTotal;
        %>
        <div class="cart-item">
          <div class="item-info">
            <h3><%= item.getItemName() %></h3>
            <p>₹<%= item.getPrice() %> each</p>
          </div>

          <div class="item-actions">
            <form action="CartServlet" method="post">
              <input type="hidden" name="action" value="update"/>
              <input type="hidden" name="itemId" value="<%= item.getItemId() %>"/>
              <input type="hidden" name="restaurantId" value="<%= restaurantId %>"/>
              <input type="hidden" name="quantity" value="<%= item.getQuantity() - 1 %>"/>
              <button type="submit" class="qty-btn">−</button>
            </form>

            <span class="qty"><%= item.getQuantity() %></span>

            <form action="CartServlet" method="post">
              <input type="hidden" name="action" value="update"/>
              <input type="hidden" name="itemId" value="<%= item.getItemId() %>"/>
              <input type="hidden" name="restaurantId" value="<%= restaurantId %>"/>
              <input type="hidden" name="quantity" value="<%= item.getQuantity() + 1 %>"/>
              <button type="submit" class="qty-btn">+</button>
            </form>
          </div>

          <div class="item-price">₹<%= String.format("%.2f", itemTotal) %></div>

          <form action="CartServlet" method="post">
            <input type="hidden" name="action" value="remove"/>
            <input type="hidden" name="itemId" value="<%= item.getItemId() %>"/>
            <input type="hidden" name="restaurantId" value="<%= restaurantId %>"/>
            <button type="submit" class="remove-btn">×</button>
          </form>
        </div>
        <% } %>

        <!-- Add More Menu Button -->
        <div class="add-more">
   
          <a href="MenuServlet?restaurantId=<%= restaurantId %>" class="btn secondary">🍽️ Add More Menu</a>
         
          
        </div>

      </section>

      <!-- RIGHT SIDE (SUMMARY) -->
      <aside class="cart-summary">
        <h3>Bill Summary</h3>
        <div class="summary-row"><span>Subtotal</span><span>₹<%= String.format("%.2f", total) %></span></div>
        <div class="summary-row"><span>Delivery Fee</span><span>₹20.00</span></div>
        <div class="summary-row total"><span>Total</span><span>₹<%= String.format("%.2f", total + 20) %></span></div>
        <a href="placeOrder.jsp"><button class="btn primary checkout">Proceed to Checkout</button></a>
        
      </aside>
    </div>

    <% } %>
  </main>

  <footer class="footer">
    © <%= java.time.Year.now() %> FoodieExpress • Crafted with ❤️ by Tamil Selvan
  </footer>

</body>
</html>
