<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.tap.model.User" %>

<%
User user = (User)session.getAttribute("user");
if(user == null){
    response.sendRedirect("login.jsp");
    return;
}
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Profile | FoodieExpress</title>

<link rel="stylesheet" href="profile.css">
<link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;800&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"/>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>


</head>
<body>

<div class="hero-section"></div>

<div class="profile-card" id="profileCard">

    <div class="avatar bounce-in">👤</div>

    <h2 class="name fade-in"><%= user.getUserName() %></h2>
    <p class="email fade-in"><%= user.getEmail() %></p>

    <div class="menu fade-up">
        <a href="edit-profile.jsp" class="menu-item">
            <span class="icon">📝</span>
            Edit Profile
            <div class="arrow">›</div>
        </a>

        <a href="orders.jsp" class="menu-item">
            <span class="icon">🍟</span>
            My Orders
            <div class="arrow">›</div>
        </a>

       <!-- Address update 
        <a href="addresses.jsp" class="menu-item">
            <span class="icon">📌</span>
            Saved Addresses
            <div class="arrow">›</div>
        </a>
        -->

        <a href="payments.jsp" class="menu-item">
            <span class="icon">💳</span>
            Payments
            <div class="arrow">›</div>
        </a>
    </div>

    <div class="btn-group slide-up">
    <a href="AfterLoginHome.jsp" class="btn-home">← Back Home</a>
    <a href="login.html" class="btn-logout">Logout</a>
</div>

    

</div>

<script>
    document.addEventListener("DOMContentLoaded", () => {
        document.querySelector(".profile-card").classList.add("show");
    });
</script>

<%
    String success = request.getParameter("success");
    if(success != null && success.equals("1")) {
%>
<script>
Swal.fire({
    title: 'Profile Updated! 🎉',
    text: 'Your details have been saved successfully!',
    icon: 'success',
    confirmButtonColor: '#5ac8ff',
    background: '#161b25',
    color: '#e8edf6',
    showClass: {
        popup: 'animate__animated animate__fadeInDown'
    },
    hideClass: {
        popup: 'animate__animated animate__fadeOutUp'
    }
});

// Remove ?success=1 to prevent duplicate popup
if (window.location.search.includes("success=1")) {
    window.history.replaceState(null, null, "profile.jsp");
}
</script>
<% } %>

</body>
</html>
