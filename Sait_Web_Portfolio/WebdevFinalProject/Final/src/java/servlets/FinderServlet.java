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
import models.Item;
import services.ItemService;

/**
 *
 * @author Scott
 */
public class FinderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ItemService is = new ItemService();
        try{
            List<Item> items = is.filteredList("");
            request.setAttribute("items", items);
        }catch(Exception e){
            
        }
        getServletContext().getRequestDispatcher("/WEB-INF/Admin/finder.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String value = (String) request.getParameter("value");
        ItemService is = new ItemService();
        try{
            List<Item> items = is.filteredList(value);
            request.setAttribute("items", items);
        }catch(Exception e){
            
        }
        getServletContext().getRequestDispatcher("/WEB-INF/Admin/finder.jsp").forward(request, response);
    }

}
