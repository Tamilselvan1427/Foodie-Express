<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Reset Password | FoodieExpress</title>
<link rel="stylesheet" href="auth.css">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body>

<div class="auth-container">
    <div class="auth-card">
        <h2>Reset Password</h2>
        <p class="text-light">Create a new strong password 🔐</p>

        <form action="reset-password" method="post">
            <div class="input-field">
                <span>🔑</span>
                <input type="password" name="pass" placeholder="New Password" required>
            </div>

            <div class="input-field">
                <span>🔐</span>
                <input type="password" name="cpass" placeholder="Confirm Password" required>
            </div>

            <button class="btn-primary">Update Password</button>
        </form>

        <a class="link" href="login.jsp">← Back to Login</a>
    </div>
</div>

<%
String error = (String)request.getAttribute("error");
if(error != null){
%>
<script>
Swal.fire('Oops!', '<%=error%>', 'error');
</script>
<%
}
%>

</body>
</html>
