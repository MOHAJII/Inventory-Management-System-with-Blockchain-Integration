package inventoryManagement.viewController;

import inventoryManagement.dao.entities.Inventory;
import inventoryManagement.dao.entities.Product;
import inventoryManagement.service.InventoryService;
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

public class AddInventoryFormController implements Initializable {

    private final ProductService productService = new ProductService();
    private final InventoryService inventoryService = new InventoryService();

    @FXML
    private Button addBtn, cancelBtn;

    @FXML
    private TextField locationField, openingStockField, reorderQuantityField, reorderThresholdField;

    @FXML
    private ComboBox<String> productCombobox;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCombobox();
    }

    @FXML
    private void handleAddProduct() {
        String productName = productCombobox.getSelectionModel().getSelectedItem();
        String location = locationField.getText();
        String reorderQuantity = reorderQuantityField.getText();
        String reorderThreshold = reorderThresholdField.getText();
        String openingQuantity = openingStockField.getText();


        if (!location.isEmpty() && productName != null && !reorderQuantity.isEmpty() && !reorderThreshold.isEmpty()&& !openingQuantity.isEmpty()) {
            try {
                int reorderQ = Integer.parseInt(reorderQuantity);
                int reorderTh = Integer.parseInt(reorderThreshold);
                int openingSt = Integer.parseInt(openingQuantity);
                if (reorderQ >= 0 && reorderTh > 0 && openingSt >= 0) {
                    Optional<Product> product = productService.getByName(productName);
                    ObjectId productId = product.map(Product::getId).orElse(null);
                    Inventory newInventory = new Inventory(productId, location, openingSt, reorderTh, reorderQ);
                    if (!inventoryService.isExist(newInventory)){
                        inventoryService.save(newInventory);
                        MyUtils.showNotification((Stage) addBtn.getScene().getWindow(), "Inventory added successfully!");
                        ((Stage) addBtn.getScene().getWindow()).close();
                    } else {
                        MyUtils.showNotification((Stage) addBtn.getScene().getWindow(), "Inventory already exists!");
                    }
                } else {
                    MyUtils.showNotification((Stage) addBtn.getScene().getWindow(), "Invalid input! quantities must be >= 0");
                }
            }catch (NumberFormatException e) {
                MyUtils.showNotification((Stage) addBtn.getScene().getWindow(), "Invalid quantities format");
            }
        } else {
            MyUtils.showNotification((Stage) addBtn.getScene().getWindow(), "All fields required");
        }
    }

    @FXML
    public void handleCancel() {
        MyUtils.showNotification((Stage) addBtn.getScene().getWindow(), "Cancel");
        ((Stage) cancelBtn.getScene().getWindow()).close();
    }

    private void setCombobox() {
        productCombobox.getItems().clear();
        List<Product> products = productService.getAll();
        for (Product product : products) {
            productCombobox.getItems().add(product.getName());
        }
    }
}
