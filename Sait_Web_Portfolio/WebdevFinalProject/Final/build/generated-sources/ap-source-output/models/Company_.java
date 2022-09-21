package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.User;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2022-04-20T11:35:26")
@StaticMetamodel(Company.class)
public class Company_ { 

    public static volatile SingularAttribute<Company, Integer> companyId;
    public static volatile SingularAttribute<Company, String> companyName;
    public static volatile CollectionAttribute<Company, User> userCollection;

}