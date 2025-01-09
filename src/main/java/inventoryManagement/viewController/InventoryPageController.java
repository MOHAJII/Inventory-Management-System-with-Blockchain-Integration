package inventoryManagement.viewController;

import inventoryManagement.dao.entities.Inventory;
import inventoryManagement.dao.entities.Product;
import inventoryManagement.service.InventoryService;
import inventoryManagement.service.ProductService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class InventoryPageController implements Initializable{

    private final ProductService productService = new ProductService();
    private final InventoryService inventoryService = new InventoryService();
    private List<Inventory> inventories;

    @FXML
    private Button addInventoryBtn, deleteBtn, modifyBtn;

    @FXML
    private TableColumn<Inventory, String> productNameCol, locationCol;

    @FXML
    private TableView<Inventory> inventoryTable;

    @FXML
    private TableColumn<Inventory, Double> quantityCol, reorderQuantityCol, reorderThresholdCol;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setColumns();
        inventories = loadInventories();
        System.out.println(inventories);
        inventoryTable.setItems(FXCollections.observableList(inventories));
        inventoryTable.setRowFactory(tv -> {
            TableRow<Inventory> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getClickCount() == 2) {
                    Inventory selectedInventory = row.getItem();
                    openUpdateInventoryForm(selectedInventory);
                }
            });
            return row;
        });
    }

    private void setColumns() {
        productNameCol.setCellValueFactory(cellData -> {
            ObjectId productId = cellData.getValue().getProductId();
            Optional<Product> product = productService.getById(productId);
            String productName = product.map(Product::getName).orElse("Unknown Product");
            return new SimpleStringProperty(productName);
        });
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        reorderQuantityCol.setCellValueFactory(new PropertyValueFactory<>("reorderQuantity"));
        reorderThresholdCol.setCellValueFactory(new PropertyValueFactory<>("reorderThreshold"));


    }


    @FXML
    private void showAddInventoryForm() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/add-inventory-form.fxml"));
        Parent parent = loader.load();

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Add Inventory");
        stage.setScene(new Scene(parent));
        stage.showAndWait();
        inventories = loadInventories();
        inventoryTable.setItems(FXCollections.observableList(inventories));
    }

    private void openUpdateInventoryForm(Inventory inventory) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/modify-inventory-form.fxml"));
            Parent root = loader.load();

            // Get the controller and pass the selected inventory
            ModifyInventoryFormController controller = loader.getController();
            controller.setInventory(inventory);

            // Create a new stage for the update form
            Stage stage = new Stage();
            stage.setTitle("Update Inventory");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait(); // Block interaction with the parent window until this one is closed
            inventories = loadInventories();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Inventory> loadInventories() {
        return FXCollections.observableArrayList(inventoryService.getAll());
    }
}
