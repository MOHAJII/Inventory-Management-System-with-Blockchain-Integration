package inventoryManagement.test.testDAO;

import inventoryManagement.dao.ICategoryDAO;
import inventoryManagement.dao.entities.Category;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestCategoryDAO {
    public static void main(String[] args) {
        ICategoryDAO categoryDAO = new ICategoryDAO();
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Bottled Water", "Different sizes of bottled water for daily use."));
        categories.add(new Category("Water Dispensers", "Electric and manual dispensers for convenience."));
        categories.add(new Category("Accessories", "Reusable caps, filters, and other water accessories."));
        categories.add(new Category("Delivery Packages", "Subscription plans for regular water delivery."));
        categories.add(new Category("Flavored Water", "Refreshing flavored water options."));
        categories.add(new Category("Sparkling Water", "Carbonated water in various sizes."));
        categories.add(new Category("Industrial Supplies", "Bulk water containers for industrial needs."));
        categories.add(new Category("Custom Branding", "Personalized water bottles for businesses and events."));
        categories.add(new Category("Eco-Friendly Products", "Sustainable and refillable water products."));

        for (Category category : categories) {
            categoryDAO.save(category);
        }


    }
}
