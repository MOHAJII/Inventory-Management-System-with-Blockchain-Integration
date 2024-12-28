package inventoryManagement.dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import inventoryManagement.dao.entities.User;
import inventoryManagement.dao.entities.enums.UserRole;
import org.bson.types.ObjectId;
import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class IUserDAO implements UserDAO {
    @Override
    public MongoCollection<User> getCollection() {
        MongoDatabase database = DBConnection.getDatabase();
        return database.getCollection("users", User.class);
    }

    @Override
    public ObjectId save(User user) {
        getCollection().insertOne(user);
        return user.getId();
    }

    @Override
    public Optional<User> getById(ObjectId id) {
        return Optional.ofNullable(getCollection().find(Filters.eq("_id", id)).first());
    }

    @Override
    public Optional<User> update(User user) {
        return Optional.ofNullable(getCollection().findOneAndReplace(Filters.eq("_id", user.getId()), user));
    }

    @Override
    public void deleteById(ObjectId id) {
        getCollection().findOneAndDelete(Filters.eq("_id", id));
    }

    @Override
    public List<User> getAll() {
        MongoCursor<User> cursor = getCollection().find().cursor();
        List<User> users = new ArrayList<>();
        while (cursor.hasNext()) {
            users.add(cursor.next());
        }
        return users;
    }

    @Override
    public Optional<User> getByUserName(String userName) {
        return Optional.ofNullable(getCollection().find(Filters.eq("userName", userName)).first());
    }

    @Override
    public boolean existsByUserName(String userName) {
        User user = getCollection().find(Filters.eq("userName", userName)).first();
        return user != null;
    }

    @Override
    public boolean validatePassword(String userName, String password) {
        Optional<User> userTest = getByUserName(userName);
        if (userTest.isPresent())
            return BCrypt.checkpw(password, userTest.get().getPassword());
        return false;
    }


    @Override
    public List<User> getByRole(UserRole userRole) {
        MongoCursor<User> userMongoCursor = getCollection().find(Filters.eq("role", userRole.toString())).cursor();
        List<User> users = new ArrayList<>();

        while (userMongoCursor.hasNext())
            users.add(userMongoCursor.next());

        return users;
    }
}
