/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import dataaccess.CompanyDB;
import java.util.List;
import models.Company;

/**
 *
 * @author Scott
 */
public class CompanyService {
    public List<Company> getAll() throws Exception {
        CompanyDB companyDB = new CompanyDB();
        List<Company> roles = companyDB.getAll();
        return roles;
    }
    public Company get(int id) throws Exception{
        CompanyDB companyDB = new CompanyDB();
        try {
            Company company = companyDB.get(id);
            return company;
        }catch (Exception e) {
        }
        return null;
    }
}
