package inventoryManagement.viewController;

import inventoryManagement.dao.entities.Product;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class ProductCardController {

    @FXML
    private Label productDescription, productName, productPrice;

    @FXML
    private ImageView productImage;

    public void setData(Product product) {
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(product.getImageSrc())));
        productDescription.setText(product.getDescription());
        productName.setText(product.getName());
        productPrice.setText(Double.toString(product.getPrice()));
        productImage.setImage(image);
    }

}
