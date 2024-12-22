package inventoryManagement.dao;

import inventoryManagement.dao.entities.Inventory;
import org.bson.types.ObjectId;

import java.util.Optional;

public interface InventoryDAO extends DAO<Inventory, ObjectId> {
    public Optional<Inventory> getByProductId(ObjectId productId);
    public Optional<Inventory> getByLocation(String location);

    public Optional<Inventory> updateQuantity(ObjectId id, int quantity);
    public Optional<Inventory> updateLocation(ObjectId id, String location);
    public Optional<Inventory> updateReorderThreshold(Object id, int reorderThreshold);
    public Optional<Inventory> updateReorderQuantity(Object id, int reorderQuantity);

}
