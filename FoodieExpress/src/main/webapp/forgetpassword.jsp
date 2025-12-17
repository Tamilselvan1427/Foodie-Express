<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Forgot Password | FoodieExpress</title>
<link rel="stylesheet" href="auth.css">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body>

<div class="auth-container">
    <div class="auth-card">
        <h2>Forgot Password?</h2>
        <p class="text-light">Don’t worry! We’ll send you a verification code.</p>

        <form action="send-otp" method="post">
            <div class="input-field">
                <span>📧</span>
                <input type="email" name="email" placeholder="Enter your registered email" required>
            </div>

            <button class="btn-primary">Send OTP</button>
        </form>

        <a class="link" href="login.jsp">← Back to Login</a>
    </div>
</div>

<%
String error = (String)request.getAttribute("error");
String msg = (String)request.getAttribute("msg");
if(error!=null){
%>
<script>Swal.fire('Oops!', '<%=error%>', 'error');</script>
<%
} else if(msg!=null){
%>
<script>Swal.fire('Success!', '<%=msg%>', 'success');</script>
<%
}
%>

</body>
</html>
