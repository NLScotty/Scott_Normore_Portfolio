package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import services.AccountService;
import services.GmailService;
import utilities.CookieUtil;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate(); // just by going to the login page the user is logged out :-) 
        
        Cookie[] cookies = request.getCookies();
        String email = CookieUtil.getCookieValue(cookies, "email");
        request.setAttribute("email", email);
        
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        // save email to a cookie
        Cookie cookie = new Cookie("email", email);
        cookie.setMaxAge(60 * 60 * 24 * 365 * 3);
        response.addCookie(cookie);
        
        AccountService as = new AccountService();
        String path = getServletContext().getRealPath("/WEB-INF");
        User user = as.login(email, password, path);
        
        if (user == null) {
            request.setAttribute("email", email);
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }
        if(user.getTwoFactorEnabled()){
            int number = as.setTwoFA(user);
            HttpSession session = request.getSession();
            session.setAttribute("email", email);
            
            String body = "<b>Hello "+user.getFirstName() + " " + user.getLastName()+"</b><br>";
            body+="Your link to activate your account is: "+number;
            try{
                GmailService.sendMail(email, "Hello "+user.getFirstName() + " " + user.getLastName(),body,true);
            }catch(Exception e){
                
            }
            response.sendRedirect("authorize");
            return;
        }
        
        HttpSession session = request.getSession();
        session.setAttribute("email", email);
        response.sendRedirect("home");
    }
}
