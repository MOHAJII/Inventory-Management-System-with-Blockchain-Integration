package inventoryManagement.dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import inventoryManagement.dao.entities.Supplier;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ISupplierDAO implements SupplierDAO {
    @Override
    public MongoCollection<Supplier> getCollection() {
        MongoDatabase mongoDatabase = DBConnection.getDatabase();
        return mongoDatabase.getCollection("suppliers", Supplier.class);
    }

    @Override
    public ObjectId save(Supplier supplier) {
        getCollection().insertOne(supplier);
        return supplier.getId();
    }

    @Override
    public Optional<Supplier> getById(ObjectId id) {
        return Optional.ofNullable(getCollection().find(Filters.eq("_id", id)).first());
    }

    @Override
    public Optional<Supplier> update(Supplier supplier) {
        return Optional.ofNullable(getCollection().findOneAndReplace(Filters.eq("_id", supplier.getId()), supplier));
    }

    @Override
    public void deleteById(ObjectId id) {
        MongoDatabase mongoDatabase = DBConnection.getDatabase();
        MongoCollection<Supplier> mongoCollection = mongoDatabase.getCollection("suppliers", Supplier.class);
        mongoCollection.findOneAndDelete(Filters.eq("_id", id));
    }

    @Override
    public List<Supplier> getAll() {
        MongoDatabase mongoDatabase = DBConnection.getDatabase();
        MongoCollection<Supplier> mongoCollection = mongoDatabase.getCollection("suppliers", Supplier.class);
        MongoCursor<Supplier> cursor = mongoCollection.find().cursor();
        List<Supplier> suppliers = new ArrayList<>();
        while (cursor.hasNext()) {
            suppliers.add(cursor.next());
        }
        return suppliers;
    }
}
