package inventoryManagement.viewController;

import inventoryManagement.dao.entities.Product;
import inventoryManagement.service.ProductService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductPageTestController implements Initializable {

    private final ProductService productService = new ProductService();

    @FXML
    private GridPane productContainer;
    private Product product;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
