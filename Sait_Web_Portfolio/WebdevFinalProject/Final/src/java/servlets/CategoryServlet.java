/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Category;
import services.CategoryService;

/**
 *
 * @author Scott
 */
public class CategoryServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CategoryService cs = new CategoryService();
        String action = request.getParameter("action");
        if(action != null && action.equals("edit")){
            try{
                String id = request.getParameter("id");
                Category category = cs.get(Integer.parseInt(id));
                request.setAttribute("category", category);
            }catch (Exception ex){
                request.setAttribute("message", "error has occured");
            }
        }
        try {
            List<Category> categories = cs.getAll();
            request.setAttribute("categories",categories);
        } catch (Exception ex) {
            request.setAttribute("message", "error has occured");
            return;
        }
        getServletContext().getRequestDispatcher("/WEB-INF/Admin/category.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CategoryService cs = new CategoryService();
        String action = request.getParameter("action");
        if(action != null && action.equals("add")){
            try{
                String name = (String) request.getParameter("aName");
                if(name != null && !name.equals("")){
                    cs.add(name);
                }
            }catch(Exception e){
                
            }
        }
        if(action != null && action.equals("edit")){
            try{
                String id = (String) request.getParameter("eId");
                String name = (String) request.getParameter("eName");
                if(name != null && !name.equals("") && id != null && !id.equals("")){
                    cs.edit(Integer.parseInt(id),name);
                }
            }catch(Exception e){
                
            }
        }
        try {
            List<Category> categories = cs.getAll();
            request.setAttribute("categories",categories);
        } catch (Exception ex) {
            request.setAttribute("message", "error has occured");
            return;
        }
        getServletContext().getRequestDispatcher("/WEB-INF/Admin/category.jsp").forward(request, response);
    }

}
