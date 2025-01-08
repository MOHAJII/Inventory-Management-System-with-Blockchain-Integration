package inventoryManagement.dao;

import inventoryManagement.dao.entities.Product;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface ProductDAO extends DAO<Product, ObjectId> {
    public List<Product> getByCategory(ObjectId categoryId);
    public Optional<Product> getByName(String name);

}


