package inventoryManagement.dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import inventoryManagement.dao.entities.Product;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class IProductDAO implements ProductDAO {
    @Override
    public MongoCollection<Product> getCollection() {
        MongoDatabase mongoDatabase = DBConnection.getDatabase();
        return mongoDatabase.getCollection("products", Product.class);
    }

    @Override
    public ObjectId save(Product product) {
        getCollection().insertOne(product);
        return product.getId();
    }

    @Override
    public Optional<Product> getById(ObjectId id) {
        return Optional.ofNullable(getCollection().find(Filters.eq("_id", id)).first());
    }

    @Override
    public Optional<Product> update(Product product) {
        return Optional.ofNullable(getCollection().findOneAndReplace(Filters.eq("_id", product.getId()), product));
    }

    @Override
    public void deleteById(ObjectId id) {
        getCollection().findOneAndDelete(Filters.eq("_id", id));
    }

    @Override
    public List<Product> getAll() {
        MongoCursor<Product> cursor = getCollection().find().cursor();
        List<Product> products = new ArrayList<>();
        while (cursor.hasNext()) {
            products.add(cursor.next());
        }
        return products;
    }


    @Override
    public List<Product> getByCategory(ObjectId categoryId) {
        MongoCursor<Product> cursor = getCollection().find(Filters.eq("categoryId", categoryId)).cursor();
        List<Product> products = new ArrayList<>();
        while (cursor.hasNext()) {
            products.add(cursor.next());
        }
        return products;
    }

    @Override
    public Optional<Product> getByName(String name) {
        return Optional.ofNullable(getCollection().find(Filters.eq("name", name)).first());
    }
}


