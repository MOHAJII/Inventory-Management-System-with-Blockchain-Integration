package inventoryManagement.dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import inventoryManagement.dao.entities.Order;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class IOrderDAO implements OrderDAO {
    @Override
    public MongoCollection<Order> getCollection() {
        MongoDatabase mongoDatabase = DBConnection.getDatabase();
        return mongoDatabase.getCollection("orders", Order.class);
    }

    @Override
    public ObjectId save(Order order) {
        getCollection().insertOne(order);
        return order.getId();
    }

    @Override
    public Optional<Order> getById(ObjectId id) {
        return Optional.ofNullable(getCollection().find(Filters.eq("_id", id)).first());
    }

    @Override
    public Optional<Order> update(Order order) {
        return Optional.ofNullable(getCollection().findOneAndReplace(Filters.eq("_id", order.getId()), order));
    }

    @Override
    public void deleteById(ObjectId id) {
        MongoDatabase mongoDatabase = DBConnection.getDatabase();
        MongoCollection<Order> mongoCollection = mongoDatabase.getCollection("orders", Order.class);
        mongoCollection.findOneAndDelete(Filters.eq("_id", id));
    }

    @Override
    public List<Order> getAll() {
        MongoDatabase mongoDatabase = DBConnection.getDatabase();
        MongoCollection<Order> mongoCollection = mongoDatabase.getCollection("orders", Order.class);
        MongoCursor<Order> cursor = mongoCollection.find().cursor();
        List<Order> orders = new ArrayList<>();
        while (cursor.hasNext()) {
            orders.add(cursor.next());
        }
        return orders;
    }
}
