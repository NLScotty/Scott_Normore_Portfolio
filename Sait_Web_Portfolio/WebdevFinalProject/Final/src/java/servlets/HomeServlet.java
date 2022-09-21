/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import services.AccountService;

/**
 *
 * @author Scott
 */
@WebServlet(name = "HomeServlet", urlPatterns = {"/home"})
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            HttpSession session = request.getSession();
            String email = (String) session.getAttribute("email");
            AccountService as= new AccountService();
            User user = as.getUser(email);
            if (user.getRole().getRoleId() == 1) {
                getServletContext().getRequestDispatcher("/WEB-INF/Admin/adminhome.jsp").forward(request, response);
            }else if(user.getRole().getRoleId() == 3){
                getServletContext().getRequestDispatcher("/WEB-INF/Company/companyhome.jsp").forward(request, response);
            }else{
                getServletContext().getRequestDispatcher("/WEB-INF/User/userhome.jsp").forward(request, response);
            }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }


}
