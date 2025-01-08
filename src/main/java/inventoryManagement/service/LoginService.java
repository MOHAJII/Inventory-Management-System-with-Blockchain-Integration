package inventoryManagement.service;

import inventoryManagement.dao.IUserDAO;

public class LoginService {
    private final IUserDAO userDAO;

    public LoginService() {
        this.userDAO = new IUserDAO();
    }

    public boolean isExist(String userName) {
        return userDAO.existsByUserName(userName);
    }

    public boolean isLogin(String userName, String password) {
        return userDAO.validatePassword(userName, password);
    }
}
