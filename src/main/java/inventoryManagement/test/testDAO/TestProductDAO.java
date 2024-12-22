package inventoryManagement.test.testDAO;

import inventoryManagement.dao.IProductDAO;
import inventoryManagement.dao.entities.Product;
import org.bson.types.ObjectId;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


public class TestProductDAO {


    public static void main(String[] args) {
        IProductDAO productDAO = new IProductDAO();
        // Some data set
        // Simulate ObjectId for categoryId and suppliersId
        ObjectId categoryId = new ObjectId(); // Example category ID
        ObjectId supplier1 = new ObjectId();  // Example supplier ID 1
        ObjectId supplier2 = new ObjectId();  // Example supplier ID 2

        // Create sample products with price and wholeSalePrice
        Product product1 = new Product(
                "123456789012",  // UPC Code
                "Product 1",     // Product Name
                "This is the description for product 1.",  // Description
                categoryId,  // Category ID
                Arrays.asList(supplier1, supplier2),  // Suppliers
                19.99,       // Retail Price
                10.50        // Wholesale Price
        );

        Product product2 = new Product(
                "987654321098",  // UPC Code
                "Product 2",     // Product Name
                "This is the description for product 2.",  // Description
                categoryId,  // Category ID
                Arrays.asList(supplier1),  // Suppliers
                29.99,       // Retail Price
                15.75        // Wholesale Price
        );

        Product product3 = new Product(
                "112233445566",  // UPC Code
                "Product 3",     // Product Name
                "This is the description for product 3.",  // Description
                categoryId,  // Category ID
                Arrays.asList(supplier2),  // Suppliers
                39.99,       // Retail Price
                20.00        // Wholesale Price
        );

        // Test save
//        productDAO.save(product1);
//        productDAO.save(product2);
//        productDAO.save(product3);

        // Delete By ID :
//        productDAO.deleteById(new ObjectId("67672224c8d4d51ca8ea0a3d"));

        // Get by id and update
        Optional<Product> byId = productDAO.getById(new ObjectId("67672224c8d4d51ca8ea0a3e"));
        if (byId.isPresent())
        {   Product product = byId.get();
            // Change price
            System.out.println(byId.toString());
            product.setPrice(200);
            productDAO.update(product);

        }
        else System.out.println("Not found");

        List<Product> byCategory = productDAO.getByCategory(new ObjectId("67672221c8d4d51ca8ea0a39"));
        byCategory.forEach(System.out::println);
        // Test Get
        productDAO.getAll().forEach(product ->
                System.out.println(product.toString()));

    }
}