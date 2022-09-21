package dataaccess;

import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import models.Category;

public class CategoryDB {

    public Category get(int categoryId) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            Category category = em.find(Category.class, categoryId);
            return category;
        } finally { 
            em.close();
        }
    }
    public List<Category> getAll() throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            Query query=em.createNamedQuery("Category.findAll");
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public void insert(Category category){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try{
            trans.begin();
            em.persist(category);
            trans.commit();
        } catch (Exception ex){
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public void update(Category category){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            trans.begin();
            em.merge(category);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
        } finally {
            em.close();
        }
    } 
    
}
