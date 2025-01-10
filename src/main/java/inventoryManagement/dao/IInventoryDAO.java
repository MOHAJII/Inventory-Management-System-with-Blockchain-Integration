package inventoryManagement.dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.model.Updates;
import inventoryManagement.dao.entities.Inventory;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class IInventoryDAO implements InventoryDAO {
    private FindOneAndUpdateOptions options = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);
    @Override
    public MongoCollection<Inventory> getCollection() {
        MongoDatabase mongoDatabase = DBConnection.getDatabase();
        return mongoDatabase.getCollection("inventories", Inventory.class);
    }

    @Override
    public ObjectId save(Inventory inventory) {
        getCollection().insertOne(inventory);
        return inventory.getId();
    }

    @Override
    public Optional<Inventory> getById(ObjectId id) {
        return Optional.ofNullable(getCollection().find(Filters.eq("_id", id)).first());
    }

    @Override
    public Optional<Inventory> update(Inventory inventory) {
        return Optional.ofNullable(getCollection().findOneAndReplace(Filters.eq("_id", inventory.getId()), inventory));
    }

    @Override
    public void deleteById(ObjectId id) {
        getCollection().findOneAndDelete(Filters.eq("_id", id));
    }

    @Override
    public List<Inventory> getAll() {
        MongoCursor<Inventory> cursor = getCollection().find().cursor();
        List<Inventory> inventories = new ArrayList<>();
        while (cursor.hasNext()) {
            inventories.add(cursor.next());
        }
        return inventories;
    }

    @Override
    public Optional<Inventory> getByProductId(ObjectId productId) {
        return Optional.ofNullable(getCollection().find(Filters.eq("productId", productId)).first());
    }

    @Override
    public Optional<Inventory> getByLocation(String location) {
        return Optional.ofNullable(getCollection().find(Filters.eq("location", location)).first());
    }

    @Override
    public Optional<Inventory> updateQuantity(ObjectId id, int quantity) {
        return Optional.ofNullable(getCollection().findOneAndUpdate(Filters.eq("_id", id), Updates.set("quantity", quantity), options));
    }

    @Override
    public Optional<Inventory> updateLocation(ObjectId id, String location) {
        return Optional.ofNullable(getCollection().findOneAndUpdate(Filters.eq("_id", id), Updates.set("location", location), options));
    }

    @Override
    public Optional<Inventory> updateReorderThreshold(Object id, int reorderThreshold) {
        return Optional.ofNullable(getCollection().findOneAndUpdate(Filters.eq("_id", id), Updates.set("reorderThreshold", reorderThreshold), options));
    }

    @Override
    public Optional<Inventory> updateReorderQuantity(Object id, int reorderQuantity) {
        return Optional.ofNullable(getCollection().findOneAndUpdate(Filters.eq("_id", id), Updates.set("reorderQuantity", reorderQuantity), options));
    }

    @Override
    public List<ObjectId> getOutStockInventories() {
        List<Inventory> inventories = getAll();
        return inventories.stream()
                .filter(Inventory::isOutOfStock)
                .map(Inventory::getId)
                .collect(Collectors.toList());
    }

    @Override
    public List<ObjectId> getLowStockInventories() {
        List<Inventory> inventories = getAll();
        return inventories.stream()
                .filter(Inventory::isLowStock)
                .map(Inventory::getId)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isOutOfStock(ObjectId id) {
        Optional<Inventory> optionalInventory = getById(id);
        Inventory inventory = optionalInventory.orElse(null);
        assert inventory != null;
        return inventory.isOutOfStock();
    }

    @Override
    public boolean isLowStock(ObjectId id) {
        Optional<Inventory> optionalInventory = getById(id);
        Inventory inventory = optionalInventory.orElse(null);
        assert inventory != null;
        return inventory.isLowStock();
    }
}
