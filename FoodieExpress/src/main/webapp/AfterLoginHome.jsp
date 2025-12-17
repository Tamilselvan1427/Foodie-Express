<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="com.tap.model.User, java.util.Map, com.tap.model.CartItem, com.tap.daoimpl.Cart"%>

<%
User loggedUser = (User) session.getAttribute("user");
Cart cart = (Cart) session.getAttribute("cart");
int size = 0;
if (cart != null) {
	Map<Integer, CartItem> items = (Map<Integer, CartItem>) cart.getCart();
	size = items.size();
}
if (loggedUser == null) {
	response.sendRedirect("login.jsp");
	return;
}
%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>FoodieExpress — Order Food Online</title>

<!-- Fonts + Icons -->
<link
	href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap"
	rel="stylesheet" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" />

<!-- Main CSS (save as AfterLoginHome.css) -->
<link rel="stylesheet" href="AfterLoginHome.css" />
</head>

<body>

	<!-- NAVBAR -->
	<nav class="nav glass">
		<div class="container nav-wrap">

			<a href="#" class="brand"> <span class="brand-badge">FE</span> <span
				class="brand-name">FoodieExpress</span>
			</a>

			<div class="nav-right">
				<a class="nav-link" href="restaurantServlet"><i
					class="fa-solid fa-bowl-food"></i> Restaurants</a> <a class="nav-link"
					href="#offers"><i class="fa-solid fa-tag"></i> Offers</a> <a
					class="nav-link" href="OrdersServlet"><i
					class="fa-solid fa-bag-shopping"></i> Orders</a> <a class="nav-link"
					href="cart.jsp"><i class="fa-solid fa-cart-shopping"></i> Cart
					<span class="badge" id="badgeId"><%=size%></span></a>

				<!-- PROFILE DROPDOWN (BIGGER / ZOMATO STYLE) -->
				<div class="user-dropdown" id="userDropdown">
					<button class="user-btn" id="userBtn" aria-haspopup="true"
						aria-expanded="false">
						<i class="fa-solid fa-user"></i> <span><%=loggedUser.getUserName()%></span>
						<i class="fa-solid fa-chevron-down"></i>
					</button>

					<div class="dropdown-menu glass" id="dropdownMenu" role="menu"
						aria-hidden="true">
						<div class="user-info-block">
							<h4><%=loggedUser.getUserName()%></h4>
							<p><%=loggedUser.getEmail()%></p>
							<p><%=loggedUser.getPhone()%></p>
						</div>

						<a href="profile.jsp"><i class="fa-solid fa-id-card"></i> My
							Profile</a> <a href="orders.jsp"><i class="fa-solid fa-clock"></i>
							My Orders</a> <a href="cart.jsp"><i
							class="fa-solid fa-cart-shopping"></i> My Cart</a>

						<form action="login.html" method="post" style="margin: 0;">
							<button class="logout-btn" type="submit">
								<i class="fa-solid fa-right-from-bracket"></i> Logout
							</button>
						</form>
					</div>
				</div>
				<!-- END PROFILE DROPDOWN -->

			</div>
		</div>
	</nav>

	<!-- HERO -->
	<header class="hero">
		<div class="container hero-grid">

			<div class="hero-text reveal">
				<h1 class="title">
					Welcome back, <span class="accent"><%=loggedUser.getUserName()%></span>!
				</h1>

				<p class="subtitle">Order from top-rated restaurants near you.
					Safe • Fast • Tasty.</p>

				<form class="search glass" action="restaurantServlet" method="get">
					<input class="input" name="q"
						placeholder="Search restaurants or dishes" /> <input
						class="input" name="location" placeholder="Enter your location">
					<button class="btn primary" type="submit">
						<i class="fa-solid fa-magnifying-glass"></i> Find Food
					</button>
				</form>

				<div class="badges">
					<span class="chip">⚡ Fast Delivery</span> <span class="chip">⭐
						Top Rated</span> <span class="chip">🛡️ Hygiene Assured</span>
				</div>
			</div>

			<div class="hero-visual">
				<img class="hero-main"
					src="https://images.unsplash.com/photo-1544025162-d76694265947?auto=format&w=1200"
					alt="Food Delivery Banner" />

				<figure class="polaroid">
					<img
						src="https://images.unsplash.com/photo-1515003197210-e0cd71810b5f?auto=format&w=800"
						alt="Biriyani">
					<figcaption>Chef’s Special</figcaption>
				</figure>
			</div>

		</div>
	</header>

	<!-- CATEGORIES -->
	<section id="categories" class="categories">
		<div class="container">
			<h2 class="section-title">Explore Popular Foods</h2>

			<div class="category-grid">

				<div class="category-card lift">
					<img src="images/categories/Biryani.jpg">
					<h3>Biryani</h3>
				</div>

				<div class="category-card lift">
					<img src="images/categories/Burger.jpg">
					<h3>Burger</h3>
				</div>

				<div class="category-card lift">
					<img src="images/categories/Pizza.jpg">
					<h3>Pizza</h3>
				</div>

				<div class="category-card lift">
					<img src="images/categories/Cake.jpg">
					<h3>Cake</h3>
				</div>

				<div class="category-card lift">
					<img src="images/categories/Rolls.jpg">
					<h3>Rolls</h3>
				</div>

				<div class="category-card lift">
					<img src="images/categories/South Indian.jpg">
					<h3>South Indian</h3>
				</div>

				<div class="category-card lift">
					<img src="images/categories/Chinese.jpg">
					<h3>Chinese</h3>
				</div>

				<div class="category-card lift">
					<img src="images/categories/Pasta.jpg">
					<h3>Pasta</h3>
				</div>

				<div class="category-card lift">
					<img src="images/categories/Salad.jpg">
					<h3>Salad</h3>
				</div>

				<div class="category-card lift">
					<img src="images/categories/Pastry.jpg">
					<h3>Pastry</h3>
				</div>

				<div class="category-card lift">
					<img src="images/categories/Shakes.jpg">
					<h3>Shakes</h3>
				</div>

				<div class="category-card lift">
					<img src="images/categories/Pure Veg.jpg">
					<h3>Pure Veg</h3>
				</div>


			</div>
		</div>
	</section>

	<!-- OFFERS -->
	<section id="offers" class="offers">
		<div class="container">
			<h2 class="section-title">🔥 Exclusive Deals Today</h2>

			<div class="offer-row">

				<article class="offer-card reveal hover-glow">
					<img src="images/offers/Biryani Feast Combo.jpg" />
					<div class="offer-info glass">
						<h3>60% OFF</h3>
						<p>Biryani Feast Combo</p>
					</div>
				</article>

				<article class="offer-card reveal hover-glow">
					<img src="images/offers/Burger Mania.jpg" />
					<div class="offer-info glass">
						<h3>BUY 1 GET 1</h3>
						<p>Burger Mania</p>
					</div>
				</article>

				<article class="offer-card reveal hover-glow">
					<img
						src="https://images.unsplash.com/photo-1525755662778-989d0524087e?auto=format&w=900" />
					<div class="offer-info glass">
						<h3>₹120 OFF</h3>
						<p>First Order Bonus</p>
					</div>
				</article>

			</div>
		</div>
	</section>

	<!-- FOOTER -->
	<footer class="footer glass">
		<div class="container footer-flex">

			<div class="footer-col">
				<h2 class="footer-logo">FoodieExpress 🍽️</h2>
				<p class="footer-tag">Craving something delicious? We deliver
					happiness.</p>
			</div>

			<div class="footer-col">
				<h3>Contact</h3>
				<p>👤 Tamil Selvan J</p>
				<p>📞 79047 47778</p>
				<p>📧 tamilselvan20030427@gmail.com</p>
			</div>

			<div class="footer-col">
				<h3>Follow me</h3>
				<div class="social-row">
					<a href="https://www.instagram.com/call_me_bhagavan/?hl=en"><i class="fa-brands fa-instagram"></i> Instagram</a> <a
						href="#"><i class="fa-brands fa-x-twitter"></i> X</a> <a href="#"><i
						class="fa-brands fa-github"></i> GitHub</a>
				</div>
			</div>

		</div>

		<div class="footer-bottom">
			© <span id="year"></span> FoodieExpress — Built by Tamil Selvan ❤️
		</div>
	</footer>

	<script>
		// year
		document.getElementById('year').textContent = new Date().getFullYear();

		// dropdown toggle + accessible close
		(function() {
			const userBtn = document.getElementById('userBtn');
			const dropdown = document.getElementById('dropdownMenu');

			function show() {
				dropdown.classList.add('show');
				dropdown.setAttribute('aria-hidden', 'false');
				userBtn.setAttribute('aria-expanded', 'true');
			}
			function hide() {
				dropdown.classList.remove('show');
				dropdown.setAttribute('aria-hidden', 'true');
				userBtn.setAttribute('aria-expanded', 'false');
			}

			userBtn.addEventListener('click', function(e) {
				e.stopPropagation();
				if (dropdown.classList.contains('show'))
					hide();
				else
					show();
			});

			// click outside to close
			document.addEventListener('click', function(e) {
				if (!e.target.closest('.user-dropdown'))
					hide();
			});

			// Esc to close
			document.addEventListener('keydown', function(e) {
				if (e.key === 'Escape')
					hide();
			});
		})();
	</script>

</body>
</html>
