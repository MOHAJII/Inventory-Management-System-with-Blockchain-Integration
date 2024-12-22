package inventoryManagement.controller;

import inventoryManagement.dao.IUserDAO;
import inventoryManagement.dao.entities.User;
import org.mindrot.jbcrypt.BCrypt;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class LoginControl {
    private IUserDAO userDAO;

    public LoginControl() {
        this.userDAO = new IUserDAO();
    }

    public LoginControl(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public LoginResult isLogin(String userName, String password) {
        Optional<User> user = userDAO.getByUserName(userName);
        // If user is not found
        if (user.isEmpty()) {
            return new LoginResult(LoginErrorType.USER_NOT_FOUND, false);
        }

        // If password does not match
        if (!BCrypt.checkpw(password, user.get().getPassword())) {
            return new LoginResult(LoginErrorType.PASSWORD_INCORRECT, false);
        }

        // Successful login
        return new LoginResult(null, true);
    }
}
