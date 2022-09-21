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

public class AuthorizeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        if(email==null){
            response.sendRedirect("login");
            return;
        }
        getServletContext().getRequestDispatcher("/WEB-INF/authorize.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        AccountService as = new AccountService();
        User user = as.getUser(email);
        String input = request.getParameter("code");
        try{
            int code = Integer.parseInt(input);
            if(as.validateTwoFA(user, code)){
                response.sendRedirect("home");
                return;
            }
        }catch(Exception e){
            
        }
        request.setAttribute("message","please try again");
        getServletContext().getRequestDispatcher("/WEB-INF/authorize.jsp").forward(request, response);
    }


}
