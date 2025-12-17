<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Verify OTP | FoodieExpress</title>
<link rel="stylesheet" href="auth.css">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body>

<div class="auth-container">
    <div class="auth-card">
        <h2>Verify OTP</h2>
        <p class="text-light">Enter the OTP sent to your email</p>

        <form action="verify-otp" method="post">
            <div class="otp-box">
                <input type="text" maxlength="6" name="otp" placeholder="Enter 6-digit OTP" required>
            </div>

            <button class="btn-primary">Verify OTP</button>
        </form>

        <a class="link" href="forgetpassword.jsp">← Change Email</a>
    </div>
</div>

<%
String error = (String)request.getAttribute("error");
String msg = (String)request.getAttribute("msg");
if(error != null){
%>
<script>
Swal.fire('Invalid OTP!', '<%=error%>', 'error');
</script>
<%
} else if(msg != null) {
%>
<script>
Swal.fire('Success!', '<%=msg%>', 'success');
</script>
<%
}
%>

</body>
</html>
