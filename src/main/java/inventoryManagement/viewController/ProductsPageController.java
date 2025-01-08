package inventoryManagement.viewController;

import inventoryManagement.dao.entities.Category;
import inventoryManagement.dao.entities.Product;
import inventoryManagement.service.CategoryService;
import inventoryManagement.service.ProductService;
import inventoryManagement.utils.Utils;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.bson.types.ObjectId;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ProductsPageController implements Initializable {

    private final ProductService productService = new ProductService();
    private final CategoryService categoryService = new CategoryService();

    private List<Product> products;

    @FXML
    private TableColumn<Product, String> categorieCol, descriptionCol, nameCol, upccodeCol;

    @FXML
    private TableColumn<Product, Void> modifyCol, deleteCol;

    @FXML
    private TableColumn<Product, Double> priceCol, wholesaleCol;

    @FXML
    private TableView<Product> productTable;

    @FXML
    private Button modifyBtn, addBtn, deleteBtn;

    @FXML
    private TextField searchItemField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        settingColumns();
        products = loadProducts();
        FilteredList<Product> filteredData = new FilteredList<>((ObservableList<Product>) products, p -> true);

        searchItemField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(product -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                return product.getName().toLowerCase().contains(newValue.toLowerCase())
                        || product.getDescription().toLowerCase().contains(newValue.toLowerCase());
            });
        });

        productTable.setItems(filteredData);
    }

    @FXML
    private void showAddProductForm() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/add-product-form.fxml"));
        Parent parent = loader.load();

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Add Product");
        stage.setScene(new Scene(parent));
        stage.showAndWait();
        products = loadProducts();
        productTable.setItems(FXCollections.observableList(products));
    }

    @FXML
    private void handleModifyProduct() throws IOException {
        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/modify-product-form.fxml"));
            Parent parent = loader.load();

            ModifyProductFormController modifyProductFormController = loader.getController();
            modifyProductFormController.setProduct(selectedProduct);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Modify Product");
            stage.setScene(new Scene(parent));
            stage.showAndWait();
            products = loadProducts();
            productTable.setItems(FXCollections.observableList(products));
        }
    }

    @FXML
    public void handleDeleteProduct() {
        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            boolean confirmed = Utils.showConfirmationAlert(
                    "Delete Confirmation",
                    "Are you sure you want to delete this item?",
                    "This action cannot be undone."
            );
            if (confirmed) {
                productService.delete(selectedProduct);
                Utils.showNotification((Stage) addBtn.getScene().getWindow(), "Product deleted");
                products = loadProducts();
                productTable.setItems(FXCollections.observableList(products));
            }
        }

    }

    private List<Product> loadProducts() {
        return FXCollections.observableArrayList(productService.getAll());
    }

    private void settingColumns() {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        upccodeCol.setCellValueFactory(new PropertyValueFactory<>("UPCCode"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        categorieCol.setCellValueFactory(cellData -> {
            ObjectId categoryId = cellData.getValue().getCategoryId();
            Optional<Category> category = categoryService.getById(categoryId);
            String categoryName = category.map(Category::getName).orElse("Unknown Category");
            return new SimpleStringProperty(categoryName);
        });
        wholesaleCol.setCellValueFactory(new PropertyValueFactory<>("wholeSalePrice"));
    }
}