/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Company;
import models.Role;
import models.User;
import services.AccountService;
import services.CompanyService;
import services.RoleService;

/**
 *
 * @author Scott
 */
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountService as = new AccountService();
        RoleService rs = new RoleService();
        CompanyService cs = new CompanyService();
        String action = request.getParameter("action");
        if(action != null && action.equals("edit")){
            try{
                String email = request.getParameter("email").replace(" ", "+");
                if(email != null){
                    User user = as.getUser(email);
                    request.setAttribute("eUser",user);
                }
            }catch(Exception ex){
                System.out.println(ex);
                request.setAttribute("message","error has occured");
            }
        }
        if(action != null && action.equals("delete")){
            try{
                String email = request.getParameter("email").replace(" ", "+");
                if(email != null){
                    User user = as.getUser(email);
                    as.deleteUser(user);
                }
            }catch(Exception ex){
                System.out.println(ex);
                request.setAttribute("message","error has occured");
            }
        }  
       if(action != null && action.equals("toggle")){
            try{
                String email = request.getParameter("email").replace(" ", "+");
                if(email != null){
                    User user = as.getUser(email);
                    as.toggleStatus(user);
                }
            }catch(Exception ex){
                System.out.println(ex);
                request.setAttribute("message","error has occured");
            }
       }
        try{
            List<Role> roles = rs.getAll();
            request.setAttribute("roles",roles);
            List<User> users = as.getAll();
            request.setAttribute("users",users);
            List<Company> companies = cs.getAll();
            request.setAttribute("companies",companies);
        }catch(Exception ex){
            System.out.println(ex);
            request.setAttribute("message","could not connect to database");
        }
        getServletContext().getRequestDispatcher("/WEB-INF/Admin/accountmanager.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountService as = new AccountService();
        RoleService rs = new RoleService();
        CompanyService cs = new CompanyService();
        String action = request.getParameter("action");
        
        if (action!=null && action.equals("edit")){
            String email = (String) request.getParameter("eEmail");
            String fName = (String) request.getParameter("eFName");
            String lName = (String) request.getParameter("eLName");
            String pass = (String) request.getParameter("ePassword");
            String roleID = (String) request.getParameter("eRole");
            String companyID = (String) request.getParameter("eCompany");
            if(email!=null && fName!=null && lName!=null && pass!=null && roleID!=null && companyID!=null && !email.equals("") && !fName.equals("") && !lName.equals("") && !pass.equals("") && !roleID.equals("") && !companyID.equals("")){
                try{
                    as.updateUser(email, fName, lName, pass, Integer.parseInt(roleID), Integer.parseInt(companyID));
                }catch(Exception e){
                    request.setAttribute("message","error has occured");
                }
            }
        }
        if (action!=null && action.equals("add")){
            String email = (String) request.getParameter("aEmail");
            String fName = (String) request.getParameter("aFName");
            String lName = (String) request.getParameter("aLName");
            String pass = (String) request.getParameter("aPassword");
            String roleID = (String) request.getParameter("aRole");
            String companyID = (String) request.getParameter("aCompany");
            if(email!=null && fName!=null && lName!=null && pass!=null && roleID!=null && companyID!=null){
                try{
                    as.addUser(email, fName, lName, pass, Integer.parseInt(roleID), Integer.parseInt(companyID));
                }catch(Exception e){
                    request.setAttribute("message","error has occured");
                }
            }
        }
        
        try{
            List<Role> roles = rs.getAll();
            request.setAttribute("roles",roles);
            List<User> users = as.getAll();
            request.setAttribute("users",users);
            List<Company> companies = cs.getAll();
            request.setAttribute("companies",companies);
        }catch(Exception ex){
            System.out.println(ex);
            request.setAttribute("message","could not connect to database");
        }
        getServletContext().getRequestDispatcher("/WEB-INF/Admin/accountmanager.jsp").forward(request, response);
    }
}
