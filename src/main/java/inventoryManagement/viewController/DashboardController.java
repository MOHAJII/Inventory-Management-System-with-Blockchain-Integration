package inventoryManagement.viewController;

import inventoryManagement.dao.entities.Product;
import inventoryManagement.service.InventoryService;
import inventoryManagement.service.ProductService;
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

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    private ObservableList<Product> productObservableList = FXCollections.observableArrayList();
    private ProductService productService;
    private InventoryService inventoryService;

    @FXML
    private Label turnOver, totalStock;

    @FXML
    private BarChart<String, Double> inventoryBarChat;

    @FXML
    private TableColumn<Product, String> nameColumn;

    @FXML
    private TableColumn<Product, Double> priceColumn;

    @FXML
    private TableView<Product> productView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        productService = new ProductService();
        inventoryService = new InventoryService();

        productObservableList.addAll(productService.getAll());
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        productView.setItems(productObservableList);
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
