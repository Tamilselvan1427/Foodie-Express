package com.tap.servlets;



import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import com.tap.daoimpl.UserDAOImpl;
import com.tap.model.User;

@WebServlet("/send-otp")
public class SendOTPServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");

        UserDAOImpl userDAO = new UserDAOImpl();
        User user = userDAO.getUserByEmail(email);
        

        if (user == null) {
            request.setAttribute("error", "Email not registered!");
            request.getRequestDispatcher("forgetpassword.jsp").forward(request, response);
            return;
        }

        // Generate OTP
        int otp = new Random().nextInt(900000) + 100000;

        HttpSession session = request.getSession();
        session.setAttribute("otp", otp);
        session.setAttribute("otpEmail", email);
        session.setAttribute("otpTime", System.currentTimeMillis()); // expiry

        // Email Details
        final String fromEmail = "tamilselvan20030427@gmail.com";
        final String appPassword = "jmqrmirrfsxncnxb";
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session mailSession = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, appPassword);
            }
        });

        try {
        	Message message = new MimeMessage(mailSession);
        	message.setFrom(new InternetAddress(fromEmail, "FoodieExpress"));
        	message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
        	message.setSubject("FoodieExpress - OTP for Password Reset");

        	// 🔥 Beautiful HTML email
        	String htmlContent = ""
        	    + "<!DOCTYPE html>"
        	    + "<html>"
        	    + "<head>"
        	    + "<meta charset='UTF-8' />"
        	    + "<title>FoodieExpress OTP</title>"
        	    + "</head>"
        	    + "<body style=\"margin:0;padding:0;background:#0f172a;font-family:Arial,Helvetica,sans-serif;\">"
        	    + "  <div style=\"max-width:480px;margin:30px auto;background:#020617;border-radius:16px;"
        	    + "              border:1px solid #1e293b;padding:24px 22px;color:#e5e7eb;\">"
        	    + "    <div style=\"text-align:center;margin-bottom:18px;\">"
        	    + "      <div style=\"font-size:22px;font-weight:700;color:#22d3ee;\">FoodieExpress</div>"
        	    + "      <div style=\"font-size:12px;color:#9ca3af;margin-top:4px;\">Secure Password Reset</div>"
        	    + "    </div>"
        	    + "    <div style=\"font-size:14px;color:#d1d5db;margin-bottom:10px;\">"
        	    + "      Hi there,<br/><br/>"
        	    + "      Use the OTP below to reset your FoodieExpress account password."
        	    + "    </div>"
        	    + "    <div style=\"text-align:center;margin:18px 0;\">"
        	    + "      <div style=\"display:inline-block;padding:12px 24px;border-radius:999px;"
        	    + "                  border:1px solid #22d3ee;background:#020617;font-size:24px;"
        	    + "                  letter-spacing:4px;font-weight:700;color:#e5e7eb;\">"
        	    +           otp
        	    + "      </div>"
        	    + "    </div>"
        	    + "    <div style=\"font-size:12px;color:#9ca3af;margin-top:6px;\">"
        	    + "      This OTP is valid for <strong>5 minutes</strong>."
        	    + "    </div>"
        	    + "    <div style=\"font-size:12px;color:#6b7280;margin-top:10px;\">"
        	    + "      If you didn’t request a password reset, you can safely ignore this email."
        	    + "    </div>"
        	    + "    <hr style=\"border:none;border-top:1px solid #1f2937;margin:18px 0;\"/>"
        	    + "    <div style=\"font-size:11px;color:#6b7280;text-align:center;\">"
        	    + "      © 2025 FoodieExpress. All rights reserved."
        	    + "    </div>"
        	    + "  </div>"
        	    + "</body>"
        	    + "</html>";

        	message.setContent(htmlContent, "text/html; charset=utf-8");

        	Transport.send(message);


            request.setAttribute("msg", "OTP sent to your email!");
            request.getRequestDispatcher("verifyotp.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "OTP sending failed!");
            request.getRequestDispatcher("forgetpassword.jsp").forward(request, response);
        }
    }
}
