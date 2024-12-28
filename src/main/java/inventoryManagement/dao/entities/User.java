package inventoryManagement.dao.entities;

import inventoryManagement.dao.entities.enums.UserRole;
import org.bson.types.ObjectId;
import org.mindrot.jbcrypt.BCrypt;

public class User {
    private ObjectId id;
    private String userName;
    private String password;
    private UserRole role;
    private String email;

    public User() {
    }

    public User(String userName, String password, UserRole role, String email) {
        this.userName = userName;
        setPassword(password);
        this.role = role;
        this.email = email;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password != null && !password.startsWith("$2a$")) { // BCrypt hashed passwords start with "$2a$"
            this.password = BCrypt.hashpw(password, BCrypt.gensalt());
        } else {
            this.password = password; // Keep already hashed passwords unchanged
        }    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", email='" + email + '\'' +
                '}';
    }



}
