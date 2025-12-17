<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.tap.model.User" %>

<%
User user = (User)session.getAttribute("user");
if(user == null){
    response.sendRedirect("login.jsp");
    return;
}
%>

<!DOCTYPE html>
<html>
<head>
<title>Edit Profile | FoodieExpress</title>
<link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600;700&display=swap" rel="stylesheet">
<link rel="stylesheet" href="edit-profile.css">


</head>

<body>

<div class="edit-container">
    <div class="edit-card animate-card">

        <h2 class="title">Update Profile ✨</h2>

        <form action="UpdateProfileServlet" method="post">

            <label>Name</label>
            <input type="text" name="name" value="<%=user.getUserName()%>" required>

            <label>Phone</label>
            <input type="text" name="phone" value="<%=user.getPhone()%>" maxlength="10" required>

            <label>Address</label>
            <textarea name="address" required><%=user.getAddress()%></textarea>

            <button class="btn-save">Save Changes</button>
        </form>

        <a class="back-link" href="profile.jsp">← Back to Profile</a>

    </div>
</div>




</body>
</html>
