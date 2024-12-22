package inventoryManagement.test.testDAO;

import inventoryManagement.dao.ICategoryDAO;
import inventoryManagement.dao.entities.Category;
import org.bson.types.ObjectId;

import java.util.Optional;

public class TestCategoryDAO {
    public static void main(String[] args) {
        ICategoryDAO categoryDAO = new ICategoryDAO();
        Category category = new Category();
        category.setName("Eau minérale");
        category.setDescription("Eau menierale valble pour le boire");

//        ObjectId save = categoryDAO.save(category);
//        System.out.println("Category Id : " + save);
//
//        // get by Id
//        Optional<Category> byId = categoryDAO.getById(save);
//        if (byId.isPresent())
//            System.out.println(byId.get());

        // GetAll
        categoryDAO.getAll().forEach(System.out::println);

        // getById
        Optional<Category> byId = categoryDAO.getById(new ObjectId("6767dca66390af74dd3dcb70"));
        if (byId.isPresent()) {
            byId.get().setDescription("Pas d'eau");
            categoryDAO.update(byId.get());
        }

        categoryDAO.getAll().forEach(System.out::println);

        // delet By id
        categoryDAO.deleteById(new ObjectId("6767dca66390af74dd3dcb70"));

        categoryDAO.getAll().forEach(System.out::println);



    }
}
