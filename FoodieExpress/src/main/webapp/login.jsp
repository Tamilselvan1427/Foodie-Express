<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>Login Failed • FoodieExpress</title>

  <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600;700;800&display=swap" rel="stylesheet" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" />
  <link rel="stylesheet" href="loginfail.css" />
</head>

<body>

  <div class="login-wrapper fade-in">

    <form action="Login" method="post"
      class="login-box glass <%= request.getAttribute("errorMessage") != null ? "shake" : "" %>">

      <% if(request.getAttribute("errorMessage") != null) { %>
        <p class="error-msg">
          <i class="fa-solid fa-circle-exclamation"></i>
          <%= request.getAttribute("errorMessage") %>
        </p>
      <% } %>

      <h2 class="title">Welcome Back 👋</h2>
      <p class="subtitle">Login to continue your food journey!</p>

      <!-- EMAIL -->
      <div class="input-field">
        <i class="fa-solid fa-envelope"></i>
        <input type="email" name="email" placeholder="Enter your email" required>
      </div>

      <!-- PASSWORD -->
      <div class="input-field">
        <i class="fa-solid fa-lock"></i>
        <input type="password" name="password" id="pass" placeholder="Enter your password" required>
        <i class="fa-solid fa-eye" id="togglePass"></i>
      </div>

      <div class="forgot">
        <a href="forgetpassword.jsp">Forgot Password?</a>
      </div>

      <button type="submit" class="btn-login">
        <i class="fa-solid fa-right-to-bracket"></i> Login
      </button>

      <p class="register">
        New user? <a href="register.html">Create account</a>
      </p>

    </form>
  </div>

  <script>
    const pass = document.getElementById('pass');
    const toggle = document.getElementById('togglePass');

    toggle.addEventListener('click', () => {
      if (pass.type === 'password') {
        pass.type = 'text';
        toggle.classList.replace('fa-eye', 'fa-eye-slash');
      } else {
        pass.type = 'password';
        toggle.classList.replace('fa-eye-slash', 'fa-eye');
      }
    });
  </script>

</body>

</html>
