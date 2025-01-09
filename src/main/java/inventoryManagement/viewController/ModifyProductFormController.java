package inventoryManagement.viewController;

import inventoryManagement.dao.entities.Category;
import inventoryManagement.dao.entities.Product;
import inventoryManagement.service.CategoryService;
import inventoryManagement.service.ProductService;
import inventoryManagement.utils.MyUtils;
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

public class ModifyProductFormController implements Initializable {
    private Product product;
    private final ProductService productService = new ProductService();
    private final CategoryService categoryService = new CategoryService();

    @FXML
    private Button modifyProductBtn, cancelBtn;

    @FXML
    private ComboBox<String> categoryComboBox;

    @FXML
    private TextField descriptionInput, nameInput, priceInput, upcCodeInput, wholeSaleInput;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Category> categoryList = categoryService.getAll();
        categoryList.forEach(category -> {
            categoryComboBox.getItems().add(category.getName());
        });
    }

    private void addProductToForm() {
        System.out.println(product);
        if (product != null) {
            descriptionInput.setText(product.getDescription());
            nameInput.setText(product.getName());
            priceInput.setText(String.valueOf(product.getPrice()));
            upcCodeInput.setText(product.getUPCCode());
            wholeSaleInput.setText(String.valueOf(product.getWholeSalePrice()));
        }
    }

    public void setProduct(Product product) {
        this.product = product;
        addProductToForm();
    }

    @FXML
    public void handleModifyProduct() {
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
                newProduct.setId(product.getId());
                productService.update(newProduct);
                MyUtils.showNotification((Stage) modifyProductBtn.getScene().getWindow(), "Product modified successfully");
                ((Stage) modifyProductBtn.getScene().getWindow()).close();
            }catch (NumberFormatException e) {
                MyUtils.showNotification((Stage) modifyProductBtn.getScene().getWindow(), "Invalid price format");
            }
        } else {
            MyUtils.showNotification((Stage) modifyProductBtn.getScene().getWindow(), "All fields required");
        }
    }

    @FXML
    public void handleCancel() {
        MyUtils.showNotification((Stage) modifyProductBtn.getScene().getWindow(), "Cancel");
        ((Stage) cancelBtn.getScene().getWindow()).close();
    }
}
