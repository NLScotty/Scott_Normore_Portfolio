package services;

import dataaccess.CompanyDB;
import dataaccess.ItemDB;
import dataaccess.RoleDB;
import dataaccess.UserDB;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Company;
import models.Item;
import models.Role;
import models.User;

public class AccountService {
    
    public User login(String email, String password, String path) {
        UserDB userDB = new UserDB();
        try {
            User user = userDB.get(email);
            if(user.getActive()==false){
                return null;
            }
            if (password.equals(user.getPassword())) {
                return user;
            }
        } catch (Exception e) {
        }
        
        return null;
    }
    
    public User getUser(String email){
        UserDB userDB = new UserDB();
        try {
            User user = userDB.get(email);
            return user;
        }catch (Exception e) {
        }
        return null;
    }
    
    public List<User> getAll() throws Exception{
        UserDB userDB = new UserDB();
        return userDB.getAll();
    }
    
    public void updateAccountProfile(User user,String fname, String lname){
        user.setFirstName(fname);
        user.setLastName(lname);
        UserDB userdb = new UserDB();
        userdb.update(user);
    }
    public void updateAccountPassword(User user,String newp, String oldp) throws Exception{
        if(user.getPassword().equals(oldp)){
            UserDB userdb = new UserDB();
            user.setPassword(newp);
            user.setSessionPasswordUuid(null);
            user.setLinkPasswordUuid(null);
            userdb.update(user);
        }else{
            throw new Exception("Password Mismatch");
        }
    }
    public void toggleTwoFactorAuthorization(User user){
        if(user.getTwoFactorEnabled() == false){
            UserDB userdb = new UserDB();
            user.setTwoFactorEnabled(true);
            userdb.update(user);
        }else{
            UserDB userdb = new UserDB();
            user.setTwoFactorEnabled(false);
            userdb.update(user);
        }
    }
    public void deactivate(User user){
        user.setActive(false);
        UserDB userdb = new UserDB();
        userdb.update(user);
    }
    
    public boolean registerNewUser(String email, String first_name, String last_name, String password, String uuid){
        User user = new User(email, false, first_name, last_name, password, false);
        user.setRegistrationUuid(uuid);
        Role role = new RoleDB().get(2);
        user.setRole(role);
        UserDB userdb = new UserDB();
        userdb.insert(user);
        return true;
    }
    
    public User getByRegisrationUUID(String uuid) throws Exception {
        UserDB userDB = new UserDB();
        List<User> userlist = userDB.getByRegistrationUUID(uuid);
        if(userlist.size() == 1 && uuid != null && !uuid.equals("")){
            return userlist.iterator().next();
        }
        return null;
    }
    
    public void activateUser(User user){
        user.setActive(true);
        user.setRegistrationUuid(null);
        UserDB userdb = new UserDB();
        userdb.update(user);
    }
    
    public void deleteUser(User user){
        ItemDB itemdb = new ItemDB();
        for(Item item: user.getItemCollection()){
            try{
                itemdb.delete(item);
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
        UserDB userdb = new UserDB();
        userdb.deleteUser(user);
    }
    
    public void toggleStatus(User user){
        if(user.getActive() == true){
            user.setActive(false);
        }else{
            user.setActive(true);
        }
        UserDB userdb= new UserDB();
        userdb.update(user);
    }
    
    public void updateUser(String email, String fName, String lName, String password, int roleId, int companyId) throws Exception{
        UserDB userDB = new UserDB();
        RoleDB roleDB = new RoleDB();
        User user = userDB.get(email);
        Role role = roleDB.get(roleId);
        if(user==null){
            throw new Exception("No such email");
        }
        if(role == null){
            throw new Exception("No such role");
        }
        user.setFirstName(fName);
        user.setLastName(lName);
        user.setPassword(password);
        user.setRole(role);
        
        if(companyId == 0){
            user.setCompanyId(null);
        }else{
            CompanyDB companyDB = new CompanyDB();
            Company company = companyDB.get(companyId);
            if(company == null){
                throw new Exception("No such company");
            }
            user.setCompanyId(company);
        }
        userDB.update(user);
    }
    
    public void addUser(String email, String fName, String lName, String password, int roleId, int companyId) throws Exception{
        UserDB userDB = new UserDB();
        RoleDB roleDB = new RoleDB();
        if(userDB.get(email)!=null){
            throw new Exception("user already exists");
        }
        User user = new User(email, true, fName, lName, password, false);
        Role role = roleDB.get(roleId);
        if(role==null){
            throw new Exception("role does not exist");
        }
        user.setRole(role);
        if(companyId == 0){
            user.setCompanyId(null);
        }else{
            CompanyDB companyDB = new CompanyDB();
            Company company = companyDB.get(companyId);
            if(company == null){
                throw new Exception("No such company");
            }
            user.setCompanyId(company);
        }
        userDB.insert(user);
    }
    
    public Collection<User> getByCompany(Company company){
        UserDB userDB = new UserDB();
        return userDB.getByCompany(company.getCompanyId());
    }
    
    public void updatePasswordUUID(String email, String sessUUID,String emailUUID){
        UserDB userDB = new UserDB();
        User user = userDB.get(email);
        user.setSessionPasswordUuid(sessUUID);
        user.setLinkPasswordUuid(emailUUID);
        userDB.update(user);
    }
    
    public User validateUUID(String linkuuid, String sessuuid) throws Exception {
        UserDB userDB = new UserDB();
        List<User> userlist = userDB.getByLinkUUID(linkuuid);
        if(userlist.size() == 1 && linkuuid != null && !linkuuid.equals("")){
            User user = userlist.iterator().next();
            if(user.getSessionPasswordUuid().equals(sessuuid)){
                return user;
            }
        }
        return null;
    }
    
    public int setTwoFA(User user){
        UserDB userDB=new UserDB();
        Random rnd = new Random();
        int code =rnd.nextInt(999999);
        user.setTwoFactorNumber(code);
        userDB.update(user);
        return code;
    }
    
    public boolean validateTwoFA(User user, int code){
        if(user.getTwoFactorNumber()==code){
            UserDB userdb= new UserDB();
            user.setTwoFactorNumber(null);
            userdb.update(user);
            return true;
        }
        return false;
    }
}
