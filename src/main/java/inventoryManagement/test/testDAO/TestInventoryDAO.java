package inventoryManagement.test.testDAO;

import inventoryManagement.dao.IInventoryDAO;
import inventoryManagement.dao.entities.Inventory;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class TestInventoryDAO {
    public static void main(String[] args) {
        IInventoryDAO inventoryDAO = new IInventoryDAO();
        List<Inventory> inventoryList = new ArrayList<>();
        inventoryList.add(new Inventory(
                new ObjectId("64cd77f2f5f33c2b8df3a202"),
                "Warehouse A",
                50,
                20,
                30
        ));
        inventoryList.add(new Inventory(
                new ObjectId("64cd77f2f5f33c2b8df3a204"),
                "Store B",
                15,
                10,
                25
        ));
        inventoryList.add(new Inventory(
                new ObjectId("64cd77f2f5f33c2b8df3a206"),
                "Central Warehouse",
                200,
                50,
                100
        ));
        inventoryList.add(new Inventory(
                new ObjectId("64cd77f2f5f33c2b8df3a208"),
                "Store C",
                8,
                10,
                15
        ));
        inventoryList.add(new Inventory(
                new ObjectId("64cd77f2f5f33c2b8df3a210"),
                "Regional Hub D",
                120,
                30,
                50
        ));

        // Save

        //inventoryList.forEach(inventoryDAO::save);

        // GET ALL
        inventoryDAO.getAll().forEach(System.out::println);

        // getById
        System.out.println("Product before updating : ");
        inventoryDAO.getById(new ObjectId("6767f38aa99d8348b7c1e08e")).ifPresent(System.out::println);

        // Update methodes
        System.out.println("update quantity");
        inventoryDAO.updateQuantity(new ObjectId("6767f38aa99d8348b7c1e08e"), 20).ifPresent(System.out::println);

        System.out.println("update reaorderThreshold");
        inventoryDAO.updateReorderThreshold(new ObjectId("6767f38aa99d8348b7c1e08e"), 1000).ifPresent(System.out::println);

        System.out.println("update reorderQuantity");
        inventoryDAO.updateReorderQuantity(new ObjectId("6767f38aa99d8348b7c1e08e"), 1000).ifPresent(System.out::println);

        System.out.println("update location");
        inventoryDAO.updateLocation(new ObjectId("6767f38aa99d8348b7c1e08e"), "Mohammedia").ifPresent(System.out::println);

        System.out.println("Get by product id");
        inventoryDAO.getByProductId(new ObjectId("64cd77f2f5f33c2b8df3a202")).ifPresent(System.out::println);

        System.out.println("Get by location");
        inventoryDAO.getByLocation("Mohammedia").ifPresent(System.out::println);
    }
}
