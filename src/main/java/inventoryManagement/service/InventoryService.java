package inventoryManagement.service;

import inventoryManagement.dao.IInventoryDAO;
import inventoryManagement.dao.IProductDAO;
import inventoryManagement.dao.entities.Inventory;
import inventoryManagement.dao.entities.Product;

import java.util.*;

public class InventoryService {
    IInventoryDAO inventoryDAO = new IInventoryDAO();
    ProductService productService = new ProductService();
    public double getAllInventories() {
        List<Inventory> inventories = inventoryDAO.getAll();
        double totalInventories = 0.0;
        for (Inventory i : inventories) {
            totalInventories += i.getQuantity();
        }
        return totalInventories;
    }

    public double getTotalAmount() {
        List<Inventory> inventories = inventoryDAO.getAll();
        double totalAmount = 0.0;
        for (Inventory inventory : inventories) {
            Optional<Product> product = productService.getById(inventory.getProductId());
            if (product.isPresent())
                totalAmount += inventory.getQuantity() * product.get().getPrice();
        }
        return totalAmount;
    }

    public Map<String, Double> getInventoryProduct() {
        List<Inventory> inventories = inventoryDAO.getAll();
        Map<String, Double> invPerProduct = new HashMap<>();
        for (Inventory inventory : inventories) {
            Optional<Product> product = productService.getById(inventory.getProductId());
            product.ifPresent(System.out::println);
            product.ifPresent(value -> invPerProduct.put(value.getName(), (double) inventory.getQuantity()));
        }

        return invPerProduct;
    }
}
