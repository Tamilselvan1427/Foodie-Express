package com.tap.servlets;


import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/verify-otp")
public class VerifyOTPServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("otp") == null) {
            request.setAttribute("error", "OTP expired! Request again.");
            request.getRequestDispatcher("forgetpassword.jsp").forward(request, response);
            return;
        }

        long otpTime = (long) session.getAttribute("otpTime");
        long now = System.currentTimeMillis();

        if (now - otpTime > 5 * 60 * 1000) {
            session.invalidate();
            request.setAttribute("error", "OTP expired! Request again.");
            request.getRequestDispatcher("forgetpassword.jsp").forward(request, response);
            return;
        }

        int sentOtp = (int) session.getAttribute("otp");
        int userOtp = Integer.parseInt(request.getParameter("otp"));

        if (sentOtp == userOtp) {
            response.sendRedirect("resetpassword.jsp");
        } else {
            request.setAttribute("error", "Incorrect OTP!");
            request.getRequestDispatcher("verifyotp.jsp").forward(request, response);
        }
    }
}
