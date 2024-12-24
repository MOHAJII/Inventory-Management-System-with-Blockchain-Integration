package inventoryManagement.test.testDAO;

import inventoryManagement.dao.IUserDAO;
import inventoryManagement.dao.entities.User;
import inventoryManagement.dao.entities.UserRole;
import org.bson.types.ObjectId;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.Optional;

public class TestUserDAO {
    public static void main(String[] args) {
//        List<User> testUsers = List.of(
//                new User(
//                        "admin_user",
//                        "securePass123",
//                        User.UserRole.ADMIN,
//                        "admin@example.com"
//                ),
//                new User(
//
//                        "manager01",
//                        "manageIt@456",
//                        User.UserRole.PRODUCT_MANAGER,
//                        "manager01@example.com"
//                ),
//                new User(
//
//                        "inventory_guru",
//                        "inventory$Pro",
//                        User.UserRole.INVENTORY_MANAGER,
//                        "inventory.guru@example.com"
//                ),
//                new User(
//
//                        "regular_user",
//                        "userPass789",
//                        User.UserRole.GUEST,
//                        "regular_user@example.com"
//                ),
//                new User(
//
//                        "john_doe",
//                        "john12345",
//                        User.UserRole.GUEST,
//                        "john.doe@example.com"
//                ),
//                new User(
//
//                        "super_admin",
//                        "superSecret456",
//                        User.UserRole.ADMIN,
//                        "super.admin@example.com"
//                ),
//                new User(
//                        "product_specialist",
//                        "prodSpec@2024",
//                        User.UserRole.PRODUCT_MANAGER,
//                        "prod.spec@example.com"
//                ),
//                new User(
//                        "warehouse_keeper",
//                        "keepInventory99",
//                        User.UserRole.INVENTORY_MANAGER,
//                        "warehouse.keeper@example.com"
//                )
//        );

        IUserDAO userDAO = new IUserDAO();

        // Save users
//        for (User user : testUsers) {
//            userDAO.save(user); // Save each user, and MongoDB will generate the `id`
//        }

        // Get all
        System.out.println("Get ALL");
        userDAO.getAll().forEach(System.out::println);

//        System.out.println("Get by Role");
//        System.out.println("ADMINS");
//        userDAO.getByRole(User.UserRole.ADMIN).forEach(System.out::println);
//        System.out.println("InventoryManagers");
//        userDAO.getByRole(User.UserRole.INVENTORY_MANAGER).forEach(System.out::println);
//        System.out.println("ProductManagers");
//        userDAO.getByRole(User.UserRole.PRODUCT_MANAGER).forEach(System.out::println);
//        System.out.println("Guests");
//        userDAO.getByRole(User.UserRole.GUEST).forEach(System.out::println);

        System.out.println();

        // Get By id and update
        Optional<User> byId = userDAO.getById(new ObjectId("676725f65b90ef3edb8eb3f1"));
//        if (byId.isPresent()) {
//            User user = byId.get();
//            System.out.println(user.toString());
//            user.setRole(User.UserRole.GUEST);
//            Optional<User> update = userDAO.update(user);
//            User user1 = update.get();
//            System.out.println(user1.toString());
//        }

        userDAO.deleteById(new ObjectId("676725f65b90ef3edb8eb3f1"));
        userDAO.getAll().forEach(System.out::println);

        Optional<User> johnDoe = userDAO.getByUserName("john_doe");
        if (johnDoe.isPresent()) {
            System.out.println(johnDoe.toString());
            System.out.println(userDAO.existsByUserName(johnDoe.get().getUserName()));
        }

        // Test validate password
        System.out.println("Validate password");
        // user saved with MP : 1234
        User user = new User(
                "fouad",
                "1234",
                UserRole.INVENTORY_MANAGER,
                "warehouse.keeper@example.com"
        );
//        ObjectId save = userDAO.save(user);
//        System.out.println("User Id : " + save);
//        System.out.println(userDAO.validatePassword("fouad", "1234"));
        System.out.println("isExists");
        System.out.println(userDAO.existsByUserName(user.getUserName()));
    }

}
