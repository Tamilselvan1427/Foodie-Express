<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String orderId = request.getParameter("orderId");
    if (orderId == null) orderId = "FE" + (int)(Math.random() * 10000);
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Order Success | FoodieExpress</title>
  <link rel="stylesheet" href="orderSuccess.css">
</head>
<body>

  <!-- ===== NAVBAR ===== -->
  <header class="navbar">
    <div class="logo">🍴 FoodieExpress</div>
    <nav class="nav-links">
      <a href="AfterLoginHome.jsp">Home</a>
      <a href="restaurantServlet">Restaurants</a>
      <a href="LogoutServlet" class="logout-btn">Logout</a>
    </nav>
  </header>

  <!-- ===== MAIN CONTENT ===== -->
  <main class="success-page">
    <video autoplay loop muted playsinline class="background-video">
      <source src="videos/delivery.mp4" type="video/mp4">
    </video>
    <div class="overlay"></div>

    <div class="content">
      <div class="check-icon">
        <div class="tick-mark">✔</div>
      </div>

      <h1>Order Placed Successfully!</h1>
      <p>Your delicious food is being prepared and will be delivered soon 🍕</p>

      <div class="order-card">
        <p><strong>Order ID:</strong> #<%= orderId %></p>
        <p><strong>Status:</strong> Confirmed ✅</p>
        <p><strong>Estimated Delivery:</strong> 20–30 mins</p>
      </div>

      <div class="actions">
        <a href="trackOrder.jsp?orderId=<%= orderId %>" class="btn primary">Track Order</a>
        <a href="AfterLoginHome.jsp" class="btn secondary">Back to Home</a>
      </div>
    </div>
  </main>

  <footer class="footer">
    © <%= java.time.Year.now() %> FoodieExpress • Delivered with ❤️ by Tamil Selvan
  </footer>

</body>
</html>
