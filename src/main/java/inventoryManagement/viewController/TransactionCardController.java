package inventoryManagement.viewController;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class TransactionCardController implements Initializable {

    @FXML
    private ImageView billImage;

    @FXML
    private Label quantity, transactionDate, transactionType, userName;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setData(int quantity, String transactionDate, String transactionType, String userName, String billImagePath) {
        System.out.println(billImagePath);
            Image image = new Image(new File(billImagePath).toURI().toString());
            billImage.setImage(image);
        this.quantity.setText(String.valueOf(quantity));
        this.transactionDate.setText(transactionDate);
        this.transactionType.setText(transactionType);
        this.userName.setText(userName);
    }
}
