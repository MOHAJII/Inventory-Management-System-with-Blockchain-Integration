package inventoryManagement.dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import inventoryManagement.dao.entities.Category;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ICategoryDAO implements CategoryDAO {
    @Override
    public MongoCollection<Category> getCollection() {
        MongoDatabase mongoDatabase = DBConnection.getDatabase();
        return mongoDatabase.getCollection("categories", Category.class);
    }

    @Override
    public ObjectId save(Category category) {
        getCollection().insertOne(category);
        return category.getId();
    }

    @Override
    public Optional<Category> getById(ObjectId id) {
        return Optional.ofNullable(getCollection().find(Filters.eq("_id", id)).first());
    }

    @Override
    public Optional<Category> update(Category category) {
        return Optional.ofNullable(getCollection().findOneAndReplace(Filters.eq("_id", category.getId()), category));
    }

    @Override
    public void deleteById(ObjectId id) {
        getCollection().findOneAndDelete(Filters.eq("_id", id));
    }

    @Override
    public List<Category> getAll() {
        MongoCursor<Category> cursor = getCollection().find().cursor();
        List<Category> categories = new ArrayList<>();
        while (cursor.hasNext()) {
            categories.add(cursor.next());
        }
        return categories;
    }

    @Override
    public Optional<Category> getByName(String categoryName) {
        return Optional.ofNullable(getCollection().find(Filters.eq("name", categoryName)).first());
    }
}
