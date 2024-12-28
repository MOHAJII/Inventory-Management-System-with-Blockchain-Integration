package inventoryManagement.viewControler;

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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {

    private ObservableList<Product> productObservableList = FXCollections.observableArrayList();
    // Work with the DAO couch before adding the service :
    private ProductService productService;
    private InventoryService inventoryService;
    @FXML
    private Button inventoryButton, settingButton, supplierButton, userButton;

    @FXML
    private BarChart<String, Double> inventoryBarChat;

    @FXML
    private TableColumn<Product, String> categoryColumn;

    @FXML
    private TableColumn<Product, String> nameColumn;

    @FXML
    private TableColumn<Product, Double> priceColumn;

    @FXML
    private TableView<Product> productView;

    @FXML
    private TableColumn<Product, String> upcCodeColumn;

    @FXML
    private TableColumn<Product, Double> wholeSalePriceColumn;


    public void openInventory(Event event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/inventory-page.fxml"));
        Scene newScene = new Scene(fxmlLoader.load(), 900, 500);
        stage.setScene(newScene);
    }

    public void openSupplier(Event event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/inventory-page.fxml"));
        Scene newScene = new Scene(fxmlLoader.load(), 900, 500);
        stage.setScene(newScene);
    }

    public void openUser(Event event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/inventory-page.fxml"));
        Scene newScene = new Scene(fxmlLoader.load(), 900, 500);
        stage.setScene(newScene);
    }

    public void openSetting(Event event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/inventory-page.fxml"));
        Scene newScene = new Scene(fxmlLoader.load(), 900, 500);
        stage.setScene(newScene);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        productService = new ProductService();
        inventoryService = new InventoryService();

        productObservableList.addAll(productService.getAll());
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        upcCodeColumn.setCellValueFactory(new PropertyValueFactory<>("UPCCode"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("categoryId"));
        wholeSalePriceColumn.setCellValueFactory(new PropertyValueFactory<>("wholeSalePrice"));

        productView.setItems(productObservableList);
        inventoryBarChartInitializer();
    }

    public void inventoryBarChartInitializer() {
        XYChart.Series<String, Double> inventorySeries = new XYChart.Series<>();
        inventorySeries.setName("Inventories");
        Map<String, Double> inventoryProduct = inventoryService.getInventoryProduct();
        System.out.println(inventoryProduct.values());
        inventoryProduct.forEach((pName, val) ->{
            inventorySeries.getData().add(new XYChart.Data<>(pName, val));
        });

        inventoryBarChat.getData().add(inventorySeries);


    }








}
