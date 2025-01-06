package inventoryManagement.dao;

import inventoryManagement.dao.entities.Category;
import org.bson.types.ObjectId;

import java.util.Optional;

public interface CategoryDAO extends DAO<Category, ObjectId> {
    public Optional<Category> getByName(String categoryName);
}
