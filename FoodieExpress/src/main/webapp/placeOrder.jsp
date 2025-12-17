<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.tap.model.CartItem" %>
<%@ page import="com.tap.daoimpl.Cart" %>

<%
    Cart cart = (Cart) session.getAttribute("cart");
    if (cart == null) {
        cart = new Cart();
    }

    Map<Integer, CartItem> items = (Map<Integer, CartItem>) cart.getCart();
    double total = 0;
    for (CartItem item : items.values()) {
        total += item.getPrice() * item.getQuantity();
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>FoodieExpress | Place Order</title>
  <link rel="stylesheet" href="placeOrder.css"/>
</head>
<body>

  <!-- ======= NAVBAR ======= -->
  <header class="navbar">
    <div class="logo">
      <span class="brand">🍴 FoodieExpress</span>
    </div>
    <nav class="nav-links">
      <a href="AfterLoginHome.jsp">Home</a>
      <a href="restaurantServlet">Restaurants</a>
      <a href="cart.jsp" class="btn-nav">Cart</a>
    </nav>
  </header>

  <!-- ======= MAIN SECTION ======= -->
  <main class="order-container">

    <!-- LEFT: DELIVERY FORM -->
    <section class="order-form glass">
      <h2>🛵 Delivery & Payment Details</h2>
      <form action="checkOutServlet" method="post" class="checkout-form">

        <div class="form-group">
          <label>Full Name</label>
          <input type="text" name="fullname" required placeholder="Enter your full name">
        </div>

        <div class="form-row">
          <div class="form-group">
            <label>Phone</label>
            <input type="text" name="phone" maxlength="10" required placeholder="10-digit number">
          </div>
          <div class="form-group">
            <label>Pincode</label>
            <input type="text" name="pincode" maxlength="6" required placeholder="6-digit pincode">
          </div>
        </div>

        <div class="form-group">
          <label>Complete Address</label>
          <textarea name="address" rows="3" required placeholder="Flat No, Street, Landmark"></textarea>
        </div>

        <div class="form-row">
          <div class="form-group">
            <label>City</label>
            <input type="text" name="city" required placeholder="City">
          </div>
          <div class="form-group">
            <label>State</label>
            <input type="text" name="state" required placeholder="State">
          </div>
        </div>

        <div class="form-group">
          <label>Delivery Note (Optional)</label>
          <textarea name="note" rows="2" placeholder="E.g. Leave near gate / call before arrival"></textarea>
        </div>

        <h3>💳 Payment Method</h3>
        <div class="payment-options">
          <label class="payment-card">
            <input type="radio" name="payment" value="UPI" required>
            <div class="card-content">
              <img src="https://cdn-icons-png.flaticon.com/512/825/825454.png" alt="UPI">
              <span>UPI / Wallet</span>
            </div>
          </label>

          <label class="payment-card">
            <input type="radio" name="payment" value="Card">
            <div class="card-content">
              <img src="https://cdn-icons-png.flaticon.com/512/2331/2331940.png" alt="Card">
              <span>Credit / Debit Card</span>
            </div>
          </label>

          <label class="payment-card">
            <input type="radio" name="payment" value="COD">
            <div class="card-content">
              <img src="https://cdn-icons-png.flaticon.com/512/3135/3135706.png" alt="COD">
              <span>Cash on Delivery</span>
            </div>
          </label>
        </div>

        <button type="submit" class="btn primary confirm">Confirm Order ✅</button>
      </form>
    </section>

    <!-- RIGHT: SUMMARY -->
    <aside class="order-summary glass">
      <h3>🧾 Order Summary</h3>
      <div class="summary-items">
        <% for (CartItem item : items.values()) { %>
          <div class="summary-row">
            <span><%= item.getItemName() %> × <%= item.getQuantity() %></span>
            <span>₹<%= String.format("%.2f", item.getPrice() * item.getQuantity()) %></span>
          </div>
        <% } %>
      </div>
      <div class="summary-row"><span>Delivery Fee</span><span>₹20.00</span></div>
      <div class="summary-row total"><span>Total</span><span>₹<%= String.format("%.2f", total + 20) %></span></div>
    </aside>
  </main>

  <!-- ======= FOOTER ======= -->
  <footer class="footer">
    © <%= java.time.Year.now() %> FoodieExpress • Crafted with ❤️ by Tamil Selvan
  </footer>

</body>
</html>
