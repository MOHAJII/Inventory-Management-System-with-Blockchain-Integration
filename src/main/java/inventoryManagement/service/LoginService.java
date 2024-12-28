package inventoryManagement.service;

import inventoryManagement.dao.IUserDAO;
import inventoryManagement.dao.entities.User;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Optional;

public class LoginService {
    private IUserDAO userDAO;

    public LoginService() {
        this.userDAO = new IUserDAO();
    }

    public LoginService(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean isExist(String userName) {
        return userDAO.existsByUserName(userName);
    }

    public boolean isLogin(String userName, String password) {
        return userDAO.validatePassword(userName, password);
    }
}
