package inventoryManagement.viewController;

import inventoryManagement.dao.entities.Category;
import inventoryManagement.dao.entities.Product;
import inventoryManagement.service.CategoryService;
import inventoryManagement.service.ProductService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
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
    private TableColumn<Product, Double> priceCol, wholesaleCol;

    @FXML
    private TableView<Product> productTable;

    @FXML
    private Button modifyBtn, addBtn;

    @FXML
    private TextField searchItemField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        settingColumns();
        loadProducts();
        FilteredList<Product> filteredData = new FilteredList<>((ObservableList<Product>) products, p -> true);

        searchItemField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(product -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                return product.getName().toLowerCase().contains(newValue.toLowerCase());
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
        products = productService.getAll();
    }

    private void loadProducts() {
        productTable.getItems().clear();
        products = FXCollections.observableArrayList(productService.getAll());
    }

    private void settingColumns() {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        upccodeCol.setCellValueFactory(new PropertyValueFactory<>("UPCCode"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        categorieCol.setCellValueFactory(cellData -> {
            ObjectId categoryId = cellData.getValue().getCategoryId();
            Optional<Category> category = categoryService.getById(categoryId);
            assert category.orElse(null) != null;
            String categoryName = category.orElse(null).getName();
            return new SimpleStringProperty(categoryName);
        });
        wholesaleCol.setCellValueFactory(new PropertyValueFactory<>("wholeSalePrice"));
    }


}
