package inventoryManagement.service;

import inventoryManagement.dao.ICategoryDAO;
import inventoryManagement.dao.IProductDAO;
import inventoryManagement.dao.entities.Category;
import inventoryManagement.dao.entities.Product;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public class CategoryService {
    ICategoryDAO categoryDAO = new ICategoryDAO();

    public List<Category> getAll() {
        return categoryDAO.getAll();
    }

    public Optional<Category> getById(ObjectId id) {
        return categoryDAO.getById(id);
    }

    public void save(Category category) {
        categoryDAO.save(category);
    }

    public Optional<Category> getCategoryByName(String name) {
        return categoryDAO.getByName(name);
    }
}
