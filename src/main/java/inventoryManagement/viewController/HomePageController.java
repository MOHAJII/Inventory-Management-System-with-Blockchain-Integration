package inventoryManagement.viewController;

import inventoryManagement.dao.entities.Product;
import inventoryManagement.dao.entities.enums.UserRole;
import inventoryManagement.service.InventoryService;
import inventoryManagement.service.ProductService;
import inventoryManagement.service.SessionManager;
import io.github.cdimascio.dotenv.Dotenv;
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
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {

    private String sessionId;
    private final SessionManager sessionManager;

    @FXML
    private ButtonBar dashboardBtn, historyBtn, settingBtn;

    @FXML
    private StackPane contentArea;

    public HomePageController() {
        Dotenv dotenv = Dotenv.load();
        String REDIS_URI = dotenv.get("REDIS_URI");
        this.sessionManager = new SessionManager(REDIS_URI, 3600);
    }

    public void openDashboardPage(Event event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/dashboard-page.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        contentArea.getChildren().setAll(anchorPane);
    }

    public void openProductPage(Event event) throws IOException {
        if (getUserRole().equals(UserRole.PRODUCT_MANAGER.toString()) || getUserRole().equals(UserRole.ADMIN.toString())) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/product-page.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            contentArea.getChildren().setAll(anchorPane);
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/not-authorized.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            contentArea.getChildren().setAll(anchorPane);
        }
    }

    public void openInventoryPage(Event event) throws IOException {
        if (getUserRole().equals(UserRole.INVENTORY_MANAGER.toString()) || getUserRole().equals(UserRole.ADMIN.toString())) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/inventory-page.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            contentArea.getChildren().setAll(anchorPane);
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/not-authorized.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            contentArea.getChildren().setAll(anchorPane);
        }
    }

    public void openHistoryPage(Event event) throws IOException {
        if (getUserRole().equals(UserRole.ADMIN.toString())) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/history-page.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            contentArea.getChildren().setAll(anchorPane);
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/not-authorized.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            contentArea.getChildren().setAll(anchorPane);
        }

    }

    public void openSettingPage(Event event) throws IOException {
        if (getUserRole().equals(UserRole.ADMIN.toString())) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/settings-page.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            contentArea.getChildren().setAll(anchorPane);
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/not-authorized.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            contentArea.getChildren().setAll(anchorPane);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            openDashboardPage(null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(sessionId);
    }

    private String getUserRole() {
        return sessionManager.getSession(sessionId).get("role");
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
