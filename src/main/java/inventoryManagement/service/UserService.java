package inventoryManagement.service;

import inventoryManagement.dao.IUserDAO;
import inventoryManagement.dao.entities.User;
import org.bson.types.ObjectId;

import java.util.Optional;

public class UserService {
    private final IUserDAO userDAO;

    public UserService() {
        this.userDAO = new IUserDAO();
    }

    public User getByUsername(String userName) {
        Optional<User> userOptional = userDAO.getByUserName(userName);
        return userOptional.orElse(null);

    }
}
