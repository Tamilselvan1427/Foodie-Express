<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page
	import="com.tap.daoimpl.MenuDAOImpl,com.tap.model.Menu,java.util.List,com.tap.daoimpl.RestaurantDAOImpl,com.tap.model.Restaurant"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title><%
    Restaurant restaurantTitle = (Restaurant) request.getAttribute("restaurant");
    if (restaurantTitle != null) {
%><%= restaurantTitle.getRestaurantName() %> Menu — FoodieExpress<%
    } else {
%>Menu — FoodieExpress<%
    }
%></title>
<link
	href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap"
	rel="stylesheet" />
<link rel="stylesheet" href="menu.css" />
</head>
<body>

	<%
	List<Menu> allMenuByRestaurantId = (List<Menu>) request.getAttribute("allMenuByRestaurantId");
	Restaurant restaurant = (Restaurant) request.getAttribute("restaurant");

	// ======= SAFE BANNER IMAGE HANDLING =======
	String bannerImg = null;
	if (restaurant != null) {
		bannerImg = restaurant.getImagePath();
	}
	if (bannerImg == null || bannerImg.trim().isEmpty()) {
		// fallback image (put one default banner image in this path)
		bannerImg = "images/restaurants/default-banner.jpg";
	}
	%>

	<!-- ============ RESTAURANT BANNER ============ -->
<section class="restaurant-banner"
	style="background-image: url('<%=bannerImg%>');">

	<div class="banner-overlay">
		<h1 class="restaurant-name"><%= restaurant.getRestaurantName() %></h1>
		<p class="restaurant-desc"><%= restaurant.getCuisineType() %></p>

		<p class="restaurant-location">📍 <%= restaurant.getAddress() %></p>

		<p class="restaurant-meta">
			⭐ <%= restaurant.getRating() %> • ⏱️ <%= restaurant.getEta() %> min
		</p>
	</div>

</section>

	<!-- ============ MENU SECTION ============ -->
	<section class="menu-section">
		<div class="container">
			<h2 class="menu-title">Menu</h2>

			<div class="menu-grid">

				<%
				if (allMenuByRestaurantId != null) {
					for (Menu menu : allMenuByRestaurantId) {
				%>

				<div class="menu-item">
					<div class="image-wrapper">
						<%
						String menuImg = menu.getImagePath();
						if (menuImg == null || menuImg.trim().isEmpty()) {
							menuImg = "images/menu/Default-Food.jpg"; // keep a default here
						}
						%>
						<img src="<%=menuImg%>"
							alt="<%=menu.getItemName()%>"
							onerror="this.onerror=null;this.src='images/menu/Default-Food.jpg';" />
					</div>

					<div class="item-details">
						<h3><%=menu.getItemName()%></h3>
						<p><%=menu.getDescription()%></p>

						<div class="item-footer">
							<span class="price">₹<%=menu.getPrice()%></span>

							<form action="CartServlet" method="post">
								<input type="hidden" name="restaurantId"
									value="<%= (restaurant != null) ? restaurant.getRestaurantId() : 0 %>">
								<input type="hidden" name="itemId"
									value="<%=menu.getMenuId()%>">
								<input type="hidden" name="quantity" value="1">
								<input type="hidden" name="action" value="add">
								<button type="submit" class="add-btn">Add</button>
							</form>
						</div>
					</div>
				</div>

				<%
					}
				} else {
				%>
				<p style="color:#9aa7bf;">No menu items available.</p>
				<%
				}
				%>

			</div>
		</div>
	</section>

	<!-- ============ FOOTER ============ -->
	<footer class="footer">
		<p>© 2025 FoodieExpress • Love at first bite!</p>
	</footer>

</body>
</html>
