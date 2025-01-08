package inventoryManagement.viewController;

import inventoryManagement.dao.entities.Category;
import inventoryManagement.dao.entities.Product;
import inventoryManagement.service.CategoryService;
import inventoryManagement.service.ProductService;
import inventoryManagement.utils.Utils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.bson.types.ObjectId;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddProductFormController implements Initializable {
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

        if (!name.isEmpty() && category != null && !description.isEmpty() && !upcCode.isEmpty() && !wholeSale.isEmpty() && !price.isEmpty()) {
            try {
                double priceDouble = Double.parseDouble(price);
                double wholeSaleDouble = Double.parseDouble(wholeSale);
                Optional<Category> category1 = categoryService.getCategoryByName(category);
                ObjectId categoryId = category1.map(Category::getId).orElse(null);
                Product newProduct = new Product(upcCode, description, name, categoryId, null, priceDouble, wholeSaleDouble);
                productService.save(newProduct);
                Utils.showNotification((Stage) addProductBtn.getScene().getWindow(), "Product added successfully!");
                ((Stage) addProductBtn.getScene().getWindow()).close();
            }catch (NumberFormatException e) {
                Utils.showNotification((Stage) addProductBtn.getScene().getWindow(), "Invalid price format");
            }
        } else {
            Utils.showNotification((Stage) addProductBtn.getScene().getWindow(), "All fields required");
        }
    }

    @FXML
    public void handleCancel() {
        Utils.showNotification((Stage) addProductBtn.getScene().getWindow(), "Cancel");
        ((Stage) cancelBtn.getScene().getWindow()).close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        categoryComboBox.getItems().clear();
        List<Category> categoryList = categoryService.getAll();
        categoryList.forEach(category -> {
            categoryComboBox.getItems().add(category.getName());
        });
    }
}
