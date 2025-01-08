package inventoryManagement;

import com.mongodb.ConnectionString;
import io.github.cdimascio.dotenv.Dotenv;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Test extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/login-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 450);
        stage.setScene(scene);
        stage.setTitle("Inventory Mangement");
        stage.setMinWidth(700);
        stage.setMinHeight(500);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}