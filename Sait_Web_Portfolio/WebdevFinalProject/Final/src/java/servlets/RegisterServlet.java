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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.User;
import services.AccountService;
import services.GmailService;

/**
 *
 * @author Scott
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        if(uuid!=null){
            try{
                AccountService as = new AccountService();
                User user = as.getByRegisrationUUID(uuid);
                if(user!=null){
                    as.activateUser(user);
                }
                getServletContext().getRequestDispatcher("/WEB-INF/activated.jsp").forward(request, response);
                return;
            }catch(Exception e){
                
            }
        }
        getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountService as = new AccountService();
        String fname = (String) request.getParameter("fname");
        String lname = (String) request.getParameter("lname");
        String email = (String) request.getParameter("email");
        String pass = (String) request.getParameter("password");
        String cpass = (String) request.getParameter("confirmpassword");
        try{
            if(fname!=null && lname!=null && email!= null && pass!=null && cpass !=null && !fname.equals("") && !lname.equals("") && !email.equals("") && !pass.equals("")&& !cpass.equals("")){
                if(!pass.equals(cpass)){
                    throw new Exception("password mismatch");
                }
                if(as.getUser(email) == null){
                    String uuid = UUID.randomUUID().toString();
                    boolean updated = as.registerNewUser(email, fname, lname, pass, uuid);
                    if(updated == true){
                        String url = request.getRequestURL().toString();
                        String link = url +"?uuid="+uuid;
                        String body = "<b>Hello "+fname + " " + lname+"</b><br>";
                        body+="Your link to activate your account is: <a href="+link+">"+link+"</a>";
                        GmailService.sendMail(email, "Hello "+fname + " " + lname,body,true);
                    } 
                    getServletContext().getRequestDispatcher("/WEB-INF/registered.jsp").forward(request, response);
                    return;
                }else{
                    throw new Exception("error has occured");
                }
            }else{
                throw new Exception("missing fields");
            }
        }catch(Exception e){
            request.setAttribute("message", e.getMessage());
            request.setAttribute("fName", (String) request.getParameter("fname"));
            request.setAttribute("lName", (String) request.getParameter("lname"));
            request.setAttribute("Email", (String) request.getParameter("email"));
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        }
    }

}
