package com.tap.servlets;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import com.tap.daoimpl.UserDAOImpl;
import com.tap.model.User;

@WebServlet("/UpdateProfileServlet")
public class UpdateProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");

        if(user == null) {
            response.sendRedirect("login.html");
            return;
        }

        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        user.setUserName(name);
        user.setPhone(phone);
        user.setAddress(address);

        UserDAOImpl dao = new UserDAOImpl();
        boolean updated = dao.updateUser(user);

        if(updated) {
            session.setAttribute("user", user); // refresh session data
            response.sendRedirect("profile.jsp?success=1");
        } else {
            response.sendRedirect("edit-profile.jsp?error=1");
        }
    }
}
