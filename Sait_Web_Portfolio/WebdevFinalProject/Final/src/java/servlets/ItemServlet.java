/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Category;
import models.Item;
import services.CategoryService;
import services.ItemService;

/**
 *
 * @author Scott
 */
@WebServlet(name = "ItemServlet", urlPatterns = {"/item"})
public class ItemServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ItemService is = new ItemService();
        CategoryService cs = new CategoryService();
        String action = request.getParameter("action");
        if(action != null && action.equals("edit")){
            String id = request.getParameter("id");
            try{
                Item item = is.get(Integer.parseInt(id));
                request.setAttribute("eItem", item);
            }catch (Exception ex){
                request.setAttribute("message", "error has occured");
            }
        }else if(action != null && action.equals("delete")){
            String id = request.getParameter("id");
            try {
                HttpSession session = request.getSession();
                String email = (String) session.getAttribute("email");
                Item item = is.get(Integer.parseInt(id));
                String ownerEmail = item.getOwner().getEmail();
                if(ownerEmail.equals(email)){
                    is.deleteItem(item.getItemId());
                }
                else{
                    throw new Exception();
                }
            } catch (Exception ex) {
                request.setAttribute("message", "error has occured");
            }
        }
        try {
            HttpSession session = request.getSession();
            String email = (String) session.getAttribute("email");
            Collection<Item> items = is.getAll(email);
            List<Category> categories = cs.getAll();
            request.setAttribute("items", items);
            request.setAttribute("categories",categories);
        } catch (Exception ex) {
            request.setAttribute("message", "error has occured");
            getServletContext().getRequestDispatcher("/WEB-INF/User/items.jsp").forward(request, response);
            return;
        }
        getServletContext().getRequestDispatcher("/WEB-INF/User/items.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ItemService is = new ItemService();
        CategoryService cs = new CategoryService();
        String action = request.getParameter("action");
        if(action != null && action.equals("edit")){
            try{
                HttpSession session = request.getSession();
                String email = (String) session.getAttribute("email");
                String id = (String) request.getParameter("eId");
                String name = (String) request.getParameter("eName");
                String catId = (String) request.getParameter("eCategory");
                Category cat = cs.get(Integer.parseInt(catId));
                String price = (String) request.getParameter("ePrice");
                Item item = is.get(Integer.parseInt(id));
                String ownerEmail = item.getOwner().getEmail();
                if(email != null && id != null && !id.equals("") && name != null && !name.equals("") && catId != null && !catId.equals("") && price != null && !price.equals("") && ownerEmail.equals(email)){
                    is.updateItem(item.getItemId(), name, cat, Double.parseDouble(price));
                }else{
                    throw new Exception();
                }
            }catch(Exception e){
                request.setAttribute("message", "error has occured");
            }
        }
        else if (action != null && action.equals("add")){
            try{
                HttpSession session = request.getSession();
                String email = (String) session.getAttribute("email");
                String cat = (String) request.getParameter("aCategory");
                String name = (String) request.getParameter("aName");
                String price = (String) request.getParameter("aPrice");
                if(email != null && name != null && !name.equals("") && cat != null && !cat.equals("") && price != null && !price.equals("")){
                    is.insertItem(email, Integer.parseInt(cat), name, Double.parseDouble(price));
                }else{
                    throw new Exception();
                }
            }catch(Exception e){
                request.setAttribute("message", "error has occured");
            }
        }
        
        try {
            HttpSession session = request.getSession();
            String email = (String) session.getAttribute("email");
            Collection<Item> items = is.getAll(email);
            List<Category> categories = cs.getAll();
            request.setAttribute("items", items);
            request.setAttribute("categories",categories);
        } catch (Exception ex) {
            request.setAttribute("message", "error has occured");
            getServletContext().getRequestDispatcher("/WEB-INF/User/items.jsp").forward(request, response);
            return;
        }
        getServletContext().getRequestDispatcher("/WEB-INF/User/items.jsp").forward(request, response);
    }

}
