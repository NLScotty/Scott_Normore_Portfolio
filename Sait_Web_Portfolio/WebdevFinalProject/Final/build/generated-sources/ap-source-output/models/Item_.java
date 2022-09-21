package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.Category;
import models.User;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2022-04-20T11:35:26")
@StaticMetamodel(Item.class)
public class Item_ { 

    public static volatile SingularAttribute<Item, User> owner;
    public static volatile SingularAttribute<Item, Integer> itemId;
    public static volatile SingularAttribute<Item, String> itemName;
    public static volatile SingularAttribute<Item, Double> price;
    public static volatile SingularAttribute<Item, Category> category;

}