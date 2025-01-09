package inventoryManagement.viewController;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class StatusPageController implements Initializable {

    private WebEngine webEngine;
    @FXML
    private WebView webView;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        webEngine = webView.getEngine();
        loadPage();
    }

    private void loadPage() {
        webEngine.load("https://www.google.com/");
    }
}
