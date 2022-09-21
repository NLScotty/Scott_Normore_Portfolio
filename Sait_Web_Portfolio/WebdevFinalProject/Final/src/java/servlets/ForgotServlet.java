/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.User;
import services.AccountService;
import services.GmailService;
import utilities.CookieUtil;

/**
 *
 * @author Scott
 */
@WebServlet(name = "ForgotServlet", urlPatterns = {"/forgot"})
public class ForgotServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        if(uuid != null){
            request.setAttribute("uuid",uuid);
            getServletContext().getRequestDispatcher("/WEB-INF/reset.jsp").forward(request, response);
        }else{
            getServletContext().getRequestDispatcher("/WEB-INF/forgot.jsp").forward(request, response);
            System.out.println();   
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String emaileduuid = request.getParameter("uuid");
        
        if(email != null){
            try{
                request.setAttribute("message","Email has been sent, check your inbox for a message!");
                AccountService as = new AccountService();
                User account = as.getUser(email);
                //(String email, boolean active, String firstName, String lastName, String password,String reset_password_uuid
                String emailuuid = UUID.randomUUID().toString();
                String sessuuid = UUID.randomUUID().toString();
                //boolean updated = service.update(account.getEmail(),account.getActive(),account.getFirstName(),account.getLastName(),account.getPassword(),account.getRole(), uuid);
                if(account != null){
                    as.updatePasswordUUID(email, sessuuid, emailuuid);
                    Cookie cookie = new Cookie("sessuuid", sessuuid);
                    cookie.setMaxAge(60 * 60 * 24 * 365 * 3);
                    response.addCookie(cookie);
                    String url = request.getRequestURL().toString();
                    String link = url +"?uuid="+emailuuid;
                    String body = "<b>Hello "+account.getFirstName() + " " + account.getLastName()+"</b><br>";
                    body+="Your link to reset your password is: <a href="+link+">"+link+"</a>";
                    GmailService.sendMail(account.getEmail(), "Hello "+account.getFirstName() + " " + account.getLastName(),body,true);
                }
            }catch(Exception E){
                System.out.println();
                System.out.println();
                System.out.println(E.getStackTrace());
            }
        }
        if(password != null){
            try{
                Cookie[] cookies = request.getCookies();
                String sessuuid = CookieUtil.getCookieValue(cookies, "sessuuid");
                request.setAttribute("message","Your password has been reset, return to login page to the login page!");
                AccountService as= new AccountService();
                User account = as.validateUUID(emaileduuid, sessuuid);
                if(account != null && !password.equals("")){
                    as.updateAccountPassword(account, password, account.getPassword());
                }
            }catch(Exception E){
                System.out.println();
                System.out.println();
                System.out.println(E.getStackTrace());
            }
        }
        getServletContext().getRequestDispatcher("/WEB-INF/forgot.jsp").forward(request, response);
    }
    
}
