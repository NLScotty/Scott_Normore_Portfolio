package dataaccess;

import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import models.Company;
import models.User;


public class UserDB {
    public List<User> getAll() throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            Query query=em.createNamedQuery("User.findAll");
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    public User get(String email) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            User user = em.find(User.class, email);
            return user;
        } finally {
            em.close();
        }
    }
    public void update(User user){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            trans.begin();
            em.merge(user);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
        } finally {
            em.close();
        }
    } 
    public void insert(User user){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try{
            Company company = user.getCompanyId();
            if(company!=null){
                company.getUserCollection().add(user);
                trans.begin();
                em.persist(user);
                em.merge(company);
                trans.commit();
            }else{
                trans.begin();
                em.persist(user);
                trans.commit();
            }
        } catch (Exception ex){
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public List<User> getByRegistrationUUID(String uuid) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            Query query=em.createNamedQuery("User.findByRegistrationUuid");
            query.setParameter("registrationUuid",uuid);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public Collection<User> getByCompany(int ownerId) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            Company company = em.find(Company.class, ownerId);
            return company.getUserCollection();
        } finally {
            em.close();
        }
    }
    public void deleteUser(User user){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try{
            em.detach(user);
            if(!em.contains(user)){
                user = em.merge(user);
            }
            Company company = user.getCompanyId();
            if(company!=null){
                company.getUserCollection().remove(user);
                trans.begin();
                em.remove(em.merge(user));
                trans.commit();
            }else{
                trans.begin();
                em.remove(user);
                trans.commit();
            }
        }  catch (Exception ex){
            System.out.print(ex.getMessage());
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public List<User> getByLinkUUID(String uuid) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            Query query=em.createNamedQuery("User.findByLinkPasswordUuid");
            query.setParameter("linkPasswordUuid",uuid);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
}
