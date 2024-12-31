package inventoryManagement.viewController;

import inventoryManagement.dao.entities.Product;
import inventoryManagement.service.InventoryService;
import inventoryManagement.service.ProductService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {

    private ObservableList<Product> productObservableList = FXCollections.observableArrayList();
    private ProductService productService;
    private InventoryService inventoryService;

    @FXML
    private ButtonBar dashboardBtn, historyBtn, settingBtn;

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

    @FXML
    private AnchorPane contentArea;

    public void openDashboardPage(Event event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/home-page.fxml"));
        Scene newScene = new Scene(fxmlLoader.load(), 900, 500);
        stage.setScene(newScene);
    }

    public void openProductPage(Event event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/product-page.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        contentArea.getChildren().setAll(anchorPane);
    }

    public void openInventoryPage(Event event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/inventory-page.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        contentArea.getChildren().setAll(anchorPane);
    }


    public void openHistoryPage(Event event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/history-page.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        contentArea.getChildren().setAll(anchorPane);
    }

    public void openSettingPage(Event event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/setting-page.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        contentArea.getChildren().setAll(anchorPane);
    }

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
