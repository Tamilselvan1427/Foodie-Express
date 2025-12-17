<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.text.SimpleDateFormat,com.tap.model.Order,com.tap.model.OrderItem,com.tap.model.User,com.tap.daoimpl.OrderDAOImpl"%>

<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.html");
        return;
    }

    OrderDAOImpl dao = new OrderDAOImpl();
    List<Order> orders = dao.getAllOrderByUserId(user.getUserId());

    SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy, hh:mm a");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Orders | FoodieExpress</title>
<link rel="stylesheet" href="orders.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body>

<!-- NAVBAR -->
<header class="glass-navbar">
  <div class="nav-inner">
    <a class="brand" href="AfterLoginHome.jsp">FoodieExpress</a>

    <nav class="nav-menu" id="navMenu">
      <a href="AfterLoginHome.jsp" class="nav-link">Home</a>
      <a href="restaurant.jsp" class="nav-link">Restaurants</a>
      <a href="orders.jsp" class="nav-link active">Orders</a>
      <a href="login.html" class="nav-link logout-link">Logout</a>
    </nav>

    <button class="menu-btn" onclick="toggleMenu()">☰</button>
  </div>
</header>

<main class="page-wrap">
<h1 class="page-title">My Orders</h1>

<div class="orders-list">

<%
if (orders != null && !orders.isEmpty()) {
    for (Order o : orders) {

        List<OrderItem> items = o.getItems();
%>

<!-- ORDER CARD -->
<div class="order-card">

  <!-- MULTIPLE IMAGES -->
  <div class="order-thumb-grid">
    <%
      if (items != null && !items.isEmpty()) {
          int max = Math.min(items.size(), 3);
          for (int i = 0; i < max; i++) {
              String img = items.get(i).getImagePath();
              if (img == null || img.trim().isEmpty()) {
                  img = "images/menu/default-food.jpg";
              }
    %>
        <img src="<%= img %>" onerror="this.src='images/menu/default-food.jpg'">
    <%
          }
      } else {
    %>
        <img src="images/menu/default-food.jpg">
    <%
      }
    %>
  </div>

  <!-- BODY -->
  <div class="order-body">

    <!-- ITEM NAMES -->
    <h2 class="item-title">
      <%
        if (items != null && !items.isEmpty()) {
            for (int i = 0; i < items.size(); i++) {
                out.print(items.get(i).getItemName());
                if (i < items.size() - 1) out.print(", ");
            }
        } else {
            out.print("Items unavailable");
        }
      %>
    </h2>

    <div class="meta-row">
      <span class="order-date"><%= df.format(o.getOrderDate()) %></span>
      <span class="order-price">₹<%= o.getTotalAmount() %></span>
    </div>

    <span class="order-status delivered"><%= o.getStatus() %></span>

    <div class="actions-row">
      <a href="ReorderServlet?orderId=<%=o.getOrderId()%>" class="btn primary">Reorder</a>
      <a href="HelpServlet?orderId=<%=o.getOrderId()%>" class="btn outline">Help</a>
    </div>

  </div>
</div>

<%
    }
} else {
%>
<p class="empty">No orders yet. Start ordering 😋</p>
<%
}
%>

</div>
</main>

<script>
function toggleMenu(){
  document.getElementById("navMenu").classList.toggle("open");
}
</script>

</body>
</html>
