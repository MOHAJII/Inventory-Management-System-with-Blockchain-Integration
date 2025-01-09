package inventoryManagement.viewController;

import inventoryManagement.dao.entities.Inventory;
import inventoryManagement.dao.entities.Product;
import inventoryManagement.dao.entities.Transaction;
import inventoryManagement.dao.entities.enums.TransactionType;
import inventoryManagement.service.InventoryService;
import inventoryManagement.service.ProductService;
import inventoryManagement.service.TransactionService;
import inventoryManagement.utils.MyUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;

import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class ModifyInventoryFormController implements Initializable {

    private Inventory inventory;
    private final InventoryService inventoryService = new InventoryService();
    private final ProductService productService = new ProductService();
    private final TransactionService transactionService = new TransactionService();
    private String imagePath;
    @FXML
    private Button cancelBtn, decreaseQuantityBtn, increaseQuantityBtn, modifyProductBtn, uploadBtn;

    @FXML
    private ImageView billImage;

    @FXML
    private TextField updatedQuantity;

    @FXML
    private Label productName, quantityOnStock, location;

    @FXML
    void handleCancel(ActionEvent event) {
        ((Stage)updatedQuantity.getScene().getWindow()).close();
    }

    @FXML
    void handleUpdateInventory(ActionEvent event) {
        try {
            try {
                int updatedQt = Integer.parseInt(updatedQuantity.getText());
                int oldQt = inventory.getQuantity();
                int newQt = oldQt + updatedQt;
                if (!(newQt < 0)) {
                    inventory.setQuantity(newQt);
                    String transactionType = (updatedQt > 0) ? TransactionType.ADDITION.toString() : TransactionType.SALE.toString();
                    double totalValue = getProductPrice() * newQt;
                    Transaction transaction = new Transaction(inventory.getProductId(), transactionType, newQt, System.currentTimeMillis(), totalValue, null, getImagePath());
                    transactionService.save(transaction);
                    inventoryService.update(inventory);
                    ((Stage) updatedQuantity.getScene().getWindow()).close();
                } else MyUtils.showNotification((Stage) updatedQuantity.getScene().getWindow(), "Error : Negative quantity!!!!!");
            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }
        } catch (RuntimeException e) {
            MyUtils.showNotification((Stage) updatedQuantity.getScene().getWindow(), "Quantity must be a number");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updatedQuantity.setText("0");
    }

    @FXML
    void handleIncreaseQuantity(ActionEvent event) {
        try {
            int updatedQt = Integer.parseInt(updatedQuantity.getText());
            updatedQt++;
            updatedQuantity.setText(String.valueOf(updatedQt));
        } catch (NumberFormatException e) {
            MyUtils.showNotification((Stage) updatedQuantity.getScene().getWindow(), "Quantity must be a number");
        }
    }

    @FXML
    void handleDecreaseQuantity(ActionEvent event) {

        try {
            int updatedQt = Integer.parseInt(updatedQuantity.getText());
            updatedQt--;
            updatedQuantity.setText(String.valueOf(updatedQt));
        } catch (NumberFormatException e) {
            MyUtils.showNotification((Stage) updatedQuantity.getScene().getWindow(), "Quantity must be a number");
        }
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
        ObjectId productId = inventory.getProductId();
        Optional<Product> product = productService.getById(productId);
        String prodName = product.map(Product::getName).orElse("Unknown Product");
        productName.setText(prodName);
        location.setText(inventory.getLocation());
        quantityOnStock.setText(String.valueOf(inventory.getQuantity()));
    }

    private double getProductPrice() {
        ObjectId productId = inventory.getProductId();
        Optional<Product> product = productService.getById(productId);
        return product.map(Product::getPrice).orElse(0.0);
    }

    @FXML
    public void handleUploadImage() {
        // Create a FileChooser for selecting an image file
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );

        // Show the FileChooser and get the selected file
        File selectedFile = fileChooser.showOpenDialog(uploadBtn.getScene().getWindow());
        if (selectedFile != null) {
            imagePath = selectedFile.getAbsolutePath(); // Store the image path

            // Display the selected image in the ImageView
            Image image = new Image(selectedFile.toURI().toString());
            billImage.setImage(image);

            // You can now save the imagePath as part of your transaction information
            System.out.println("Selected Image Path: " + imagePath);
        }
    }

    public String getImagePath() {
        return imagePath;
    }
}
