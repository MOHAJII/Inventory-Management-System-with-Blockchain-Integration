package inventoryManagement.dao;

import inventoryManagement.dao.entities.Product;
import org.bson.types.ObjectId;

import java.util.List;

public interface ProductDAO extends DAO<Product, ObjectId> {
    public List<Product> getByCategory(ObjectId categoryId);

}


