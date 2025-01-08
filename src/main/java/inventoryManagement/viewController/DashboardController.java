package inventoryManagement.viewController;

import inventoryManagement.dao.entities.Inventory;
import inventoryManagement.dao.entities.Product;
import inventoryManagement.service.InventoryService;
import inventoryManagement.service.ProductService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.bson.types.ObjectId;

import java.net.URL;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    private ObservableList<Inventory> inventoryObservableList = FXCollections.observableArrayList();
    private final ProductService productService;
    private final InventoryService inventoryService;

    @FXML
    private Label turnOver, totalStock;

    @FXML
    private BarChart<String, Double> inventoryBarChat;

    @FXML
    private TableColumn<Inventory, String> nameCol;

    @FXML
    private TableColumn<Inventory, Double> productStockCol;

    @FXML
    private TableView<Inventory> productView;

    public DashboardController() {
        productService = new ProductService();
        inventoryService = new InventoryService();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        turnOver.setText(Double.toString(inventoryService.getTotalAmount()));
        totalStock.setText(Double.toString(inventoryService.getAllInventories()));
        inventoryObservableList.addAll(inventoryService.getAll());
        nameCol.setCellValueFactory(cellData -> {
            ObjectId productId = cellData.getValue().getProductId();
            Optional<Product> product = productService.getById(productId);
            String productName = product.map(Product::getName).orElse("Unknown Product");
            return new SimpleStringProperty(productName);
        });
        productStockCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        productView.setItems(inventoryObservableList);
        inventoryBarChartInitializer();
    }

    public void inventoryBarChartInitializer() {
        XYChart.Series<String, Double> inventorySeries = new XYChart.Series<>();
        inventorySeries.setName("Inventories");
        Map<String, Double> inventoryProduct = inventoryService.getInventoryProduct();
        System.out.println(inventoryProduct.values());
        inventoryProduct.forEach((pName, val) -> {
            inventorySeries.getData().add(new XYChart.Data<>(pName, val));
        });
        inventoryBarChat.getData().add(inventorySeries);
    }
}
