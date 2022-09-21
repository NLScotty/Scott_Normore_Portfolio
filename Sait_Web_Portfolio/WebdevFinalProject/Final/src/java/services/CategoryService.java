/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import dataaccess.CategoryDB;
import java.util.List;
import models.Category;

/**
 *
 * @author Scott
 */
public class CategoryService {
    public List<Category> getAll() throws Exception {
        CategoryDB categoryDB = new CategoryDB();
        List<Category> roles = categoryDB.getAll();
        return roles;
    }
    public Category get(int id) throws Exception{
        CategoryDB categoryDB = new CategoryDB();
        try {
            Category category = categoryDB.get(id);
            return category;
        }catch (Exception e) {
        }
        return null;
    }
    public void add(String name){
        CategoryDB categoryDB = new CategoryDB();
        Category category = new Category(0,name);
        try{
            categoryDB.insert(category);
        }catch (Exception e){
            
        }
    }
    public void edit(int id, String name){
        CategoryDB categoryDB = new CategoryDB();
        try{
            Category category= categoryDB.get(id);
            category.setCategoryName(name);
            categoryDB.update(category);
        }catch (Exception e){
            
        }
    }
}
