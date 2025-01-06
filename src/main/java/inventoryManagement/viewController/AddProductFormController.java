package inventoryManagement.viewController;

import inventoryManagement.dao.entities.Category;
import inventoryManagement.dao.entities.Product;
import inventoryManagement.service.CategoryService;
import inventoryManagement.service.ProductService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.bson.types.ObjectId;

import java.util.Optional;

public class AddProductFormController {
    private final ProductService productService = new ProductService();
    private final CategoryService categoryService = new CategoryService();

    @FXML
    private Button addProductBtn, cancelBtn;

    @FXML
    private TextField descriptionInput, nameInput, priceInput, upcCodeInput, wholeSaleInput;

    @FXML
    private ComboBox<String> categoryComboBox;

    @FXML
    private void handleAddProduct() {
        String name = nameInput.getText();
        String category = categoryComboBox.getSelectionModel().getSelectedItem();
        String description = descriptionInput.getText();
        String upcCode = upcCodeInput.getText();
        String wholeSale = wholeSaleInput.getText();
        String price = priceInput.getText();

        if (!name.isEmpty() || !category.isEmpty() || !description.isEmpty() || !upcCode.isEmpty() || !wholeSale.isEmpty() || !price.isEmpty()) {
            try {
                double priceDouble = Double.parseDouble(price);
                double wholeSaleDouble = Double.parseDouble(wholeSale);
                Optional<Category> category1 = categoryService.getCategoryByName(category);
                // ObjectId categoryId = category1.get().getId();
                Product newProduct = new Product(upcCode, description, name, null, null, priceDouble, wholeSaleDouble);
                productService.save(newProduct);
                ((Stage) nameInput.getScene().getWindow()).close();
            }catch (NumberFormatException e) {
                System.out.println("Invalid price format");
            }
        } else {
            System.out.println("All fields are required");
        }
    }
}
