package inventoryManagement.service;

import inventoryManagement.dao.IProductDAO;
import inventoryManagement.dao.entities.Product;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public class ProductService {
    IProductDAO productDAO = new IProductDAO();

    public List<Product> getAll() {
        return productDAO.getAll();
    }

    public Optional<Product> getById(ObjectId id) {
        return productDAO.getById(id);
    }

    public void save(Product product) {
        productDAO.save(product);
    }
}
