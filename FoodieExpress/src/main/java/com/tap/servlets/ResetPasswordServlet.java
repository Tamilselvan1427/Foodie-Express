package com.tap.servlets;


import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import com.tap.daoimpl.UserDAOImpl;

@WebServlet("/reset-password")
public class ResetPasswordServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("otpEmail") == null) {
            request.setAttribute("error", "Session expired!");
            request.getRequestDispatcher("forgetpassword.jsp").forward(request, response);
            return;
        }

        String email = (String) session.getAttribute("otpEmail");
        String pass = request.getParameter("pass");
        String cpass = request.getParameter("cpass");

        if (!pass.equals(cpass)) {
            request.setAttribute("error", "Passwords do not match!");
            request.getRequestDispatcher("resetpassword.jsp").forward(request, response);
            return;
        }

        UserDAOImpl dao = new UserDAOImpl();
        if (dao.updatePassword(email, pass)) {
            session.invalidate();
            response.sendRedirect("login.jsp?msg=password-updated");
        } else {
            request.setAttribute("error", "Failed to update password!");
            request.getRequestDispatcher("resetpassword.jsp").forward(request, response);
        }
    }
}
