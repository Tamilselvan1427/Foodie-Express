<%@ page import="java.util.List" %>
<%@ page import="com.tap.daoimpl.RestaurantDAOImpl" %>
<%@ page import="com.tap.model.Restaurant" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Restaurants - FoodieExpress</title>
  <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600;700&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="restaurant.css" />
</head>
<body>

  <div class="page-wrapper">
    <h1 class="page-title">🍽️ Restaurants Near You</h1>
    
    

    <div class="restaurant-grid">
      <%
        RestaurantDAOImpl dao = new RestaurantDAOImpl();
        List<Restaurant> restaurants = dao.getAllRestaurant();

        for (Restaurant r : restaurants) {
      %>
        <div class="restaurant-card">
        <a href="MenuServlet?restaurantId=<%= r.getRestaurantId() %>" style="text-decoration: none;">
          <img src="<%= r.getImagePath() %>" alt="<%= r.getRestaurantName() %>" class="restaurant-img" />
          <div class="restaurant-content">
            <h2><%= r.getRestaurantName() %></h2>
            <p class="cuisine"><%= r.getCuisineType() %></p>
            <div class="meta">
              <span class="rating">⭐ <%= r.getRating() %></span>
              <span class="eta">⏱️ <%= r.getEta() %> mins</span>
            </div>
            <p class="address"><%= r.getAddress() %></p>
          </div>
          </a>
        </div>
      <% } %>
    </div>
  </div>

</body>
</html>
