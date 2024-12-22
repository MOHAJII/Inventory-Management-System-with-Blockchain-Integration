package inventoryManagement.dao;

import inventoryManagement.dao.entities.User;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface UserDAO extends DAO<User, ObjectId> {
    public Optional<User> getByUserName(String userName);
    public boolean existsByUserName(String userName);
    public boolean validatePassword(String userName, String password);
    public List<User> getByRole(User.UserRole userRole);
}
