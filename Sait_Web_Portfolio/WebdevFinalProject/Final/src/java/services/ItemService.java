package services;

import dataaccess.CategoryDB;
import dataaccess.ItemDB;
import dataaccess.UserDB;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;
import models.Category;
import models.Item;
import models.User;

public class ItemService {
    public Item get(int id) throws Exception {
        ItemDB itemDB = new ItemDB();
        Item item = itemDB.get(id);
        return item;
    }
    
    public Collection<Item> getAll(String email) throws Exception {
        ItemDB itemDB = new ItemDB();
        Collection<Item> items = itemDB.getAll(email);
        return items;
    }
    
    public void insertItem(String ownerEmail, int itemCategory, String itemName, double itemPrice) throws Exception{
        Item item = new Item(0,itemName,itemPrice);
        CategoryDB catdb = new CategoryDB();
        item.setCategory(catdb.get(itemCategory));
        UserDB userDB = new UserDB();        
        User user = userDB.get(ownerEmail);
        item.setOwner(user);
        ItemDB itemDB = new ItemDB();
        itemDB.insert(item);
    }
    
    public void updateItem(int itemId, String name, Category category, double price) throws Exception{
        ItemDB itemDB = new ItemDB();
        Item item = itemDB.get(itemId);
        item.setItemName(name);
        item.setCategory(category);
        item.setPrice(price);
        itemDB.update(item);
    }
    
    public void deleteItem(int itemId) throws Exception{
        ItemDB itemDB = new ItemDB();
        Item item = itemDB.get(itemId);
        itemDB.delete(item);
    }
    
    public List<Item> filteredList(String value){
        ItemDB itemDB = new ItemDB();
        List<Item> itemList;
        try{
            itemList = itemDB.getEverything();
        }catch(Exception ex){
            return null;
        }
        if(value.equals("")){
            return itemList;
        }
        List<Item> filteredList = new ArrayList<Item>();
        
        for(Item item: itemList){
            if(item.getItemName().toUpperCase().contains(value.toUpperCase())){
                filteredList.add(item);
            }
        }
        return filteredList;
    }
}
