package inventoryManagement.service;

import inventoryManagement.dao.IInventoryDAO;
import inventoryManagement.dao.IProductDAO;
import inventoryManagement.dao.entities.Inventory;
import inventoryManagement.dao.entities.Product;
import org.bson.types.ObjectId;

import java.util.*;

public class InventoryService {
    IInventoryDAO inventoryDAO = new IInventoryDAO();
    ProductService productService = new ProductService();

    public List<Inventory> getAll() {
        return inventoryDAO.getAll();
    }

    public Optional<Inventory> getById(ObjectId id) {
        return inventoryDAO.getById(id);
    }

    public void save(Inventory inventory) {
        inventoryDAO.save(inventory);
    }

    public Optional<Inventory> update(Inventory inventory) {
        return inventoryDAO.update(inventory);
    }

    public void delete(Inventory inventory) {
        inventoryDAO.deleteById(inventory.getId());
    }

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

    public boolean isExist(Inventory inventory) {
        Optional<Inventory> inventory1 = inventoryDAO.getByProductId(inventory.getProductId());
        if (inventory1.isPresent()) {
            Optional<Inventory> inventory2 = inventoryDAO.getByLocation(inventory.getLocation());
            return inventory2.isPresent();
        }
        return false;
    }

    public List<ObjectId> getOutOfStockInventories() {
        return inventoryDAO.getOutStockInventories();
    }

    public List<ObjectId> getLowStockInventories() {
        return inventoryDAO.getLowStockInventories();
    }

    public boolean isOutOfStock(ObjectId id) {
        return inventoryDAO.isOutOfStock(id);
    }

    public boolean isLowStock(ObjectId id) {
        return inventoryDAO.isLowStock(id);
    }


}
