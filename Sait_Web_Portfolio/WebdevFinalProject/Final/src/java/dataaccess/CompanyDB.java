/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import models.Company;

/**
 *
 * @author Scott
 */
public class CompanyDB {
    public Company get(int companyId) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            Company company = em.find(Company.class, companyId);
            return company;
        } finally { 
            em.close();
        }
    }
    public List<Company> getAll() throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            Query query=em.createNamedQuery("Company.findAll");
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
