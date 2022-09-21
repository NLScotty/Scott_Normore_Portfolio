package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.Company;
import models.Item;
import models.Role;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2022-04-20T11:35:26")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> lastName;
    public static volatile SingularAttribute<User, String> linkPasswordUuid;
    public static volatile SingularAttribute<User, Role> role;
    public static volatile CollectionAttribute<User, Item> itemCollection;
    public static volatile SingularAttribute<User, Boolean> active;
    public static volatile SingularAttribute<User, String> sessionPasswordUuid;
    public static volatile SingularAttribute<User, String> firstName;
    public static volatile SingularAttribute<User, Boolean> twoFactorEnabled;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, String> registrationUuid;
    public static volatile SingularAttribute<User, Company> company;
    public static volatile SingularAttribute<User, Integer> twoFactorNumber;
    public static volatile SingularAttribute<User, String> email;

}