package inventoryManagement.viewControler;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HomePageController {

    @FXML
    private Button inventoryButton, settingButton, supplierButton, userButton;

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
}
