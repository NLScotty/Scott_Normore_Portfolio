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
@WebServlet(name = "AccountServlet", urlPatterns = {"/account"})
public class AccountServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action != null && action.equals("updateprofile")){
            getServletContext().getRequestDispatcher("/WEB-INF/User/updateinfo.jsp").forward(request, response);
        }
        else if(action != null && action.equals("updatepassword")){
            getServletContext().getRequestDispatcher("/WEB-INF/User/updatepassword.jsp").forward(request, response);
        }
        else if(action != null && action.equals("toggle2fa")){
            AccountService as = new AccountService();
            HttpSession session = request.getSession();
            String email = (String) session.getAttribute("email");
            User user = as.getUser(email);
            as.toggleTwoFactorAuthorization(user);
            request.setAttribute("fname", user.getFirstName());
            request.setAttribute("lname", user.getLastName());
            request.setAttribute("twoenabled", user.getTwoFactorEnabled());
            getServletContext().getRequestDispatcher("/WEB-INF/User/account.jsp").forward(request, response);
        }
        else if(action != null && action.equals("deactivate")){
            AccountService as = new AccountService();
            HttpSession session = request.getSession();
            String email = (String) session.getAttribute("email");
            as.deactivate(as.getUser(email));
            session.invalidate();
            response.sendRedirect("login");
        }
        else{
            HttpSession session = request.getSession();
            String email = (String) session.getAttribute("email");
            AccountService as = new AccountService();
            User user = as.getUser(email);
            request.setAttribute("fname", user.getFirstName());
            request.setAttribute("lname", user.getLastName());
            request.setAttribute("twoenabled", user.getTwoFactorEnabled());
            getServletContext().getRequestDispatcher("/WEB-INF/User/account.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        String action = request.getParameter("action");
        AccountService as = new AccountService();
        User user = as.getUser(email);
        if(action != null && action.equals("updateprofile")){
            String firstName = request.getParameter("fname");
            String lastName = request.getParameter("lname");
            if(firstName!=null && !firstName.equals("") && lastName!=null && !lastName.equals("")){
                as.updateAccountProfile(user, firstName, lastName);
            }
            request.setAttribute("fname", user.getFirstName());
            request.setAttribute("lname", user.getLastName());
            request.setAttribute("twoenabled", user.getTwoFactorEnabled());
            getServletContext().getRequestDispatcher("/WEB-INF/User/account.jsp").forward(request, response);
        }
        else if(action != null && action.equals("updatepassword")){
            try{
                String oldPass = request.getParameter("oldpassword");
                String newPass = request.getParameter("newpassword");
                if(newPass ==  null || newPass.equals("")){
                    throw new Exception("Invalid Password");
                }
                as.updateAccountPassword(user, newPass, oldPass);
            }catch(Exception e){
                request.setAttribute("message", e.getMessage());
                getServletContext().getRequestDispatcher("/WEB-INF/User/updatepassword.jsp").forward(request, response);
                return;
            }
            request.setAttribute("fname", user.getFirstName());
            request.setAttribute("lname", user.getLastName());
            request.setAttribute("twoenabled", user.getTwoFactorEnabled());
            getServletContext().getRequestDispatcher("/WEB-INF/User/account.jsp").forward(request, response);
        }
        else{
            request.setAttribute("fname", user.getFirstName());
            request.setAttribute("lname", user.getLastName());
            request.setAttribute("twoenabled", user.getTwoFactorEnabled());
            getServletContext().getRequestDispatcher("/WEB-INF/User/account.jsp").forward(request, response);
        }
    }

}
