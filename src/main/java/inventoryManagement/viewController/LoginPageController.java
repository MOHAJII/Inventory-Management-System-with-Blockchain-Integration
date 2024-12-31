package inventoryManagement.viewController;

import inventoryManagement.service.LoginService;
import javafx.animation.*;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class LoginPageController {
    @FXML
    private Label errorLabel;

    @FXML
    private TextField passwordField;

    @FXML
    private Button signInButton;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField userNameField;


    public void onSignIn(Event event) throws IOException {
        String userName = userNameField.getText();
        String password = passwordField.getText();
        LoginService loginService = new LoginService();

        if (!userName.isEmpty() && !password.isEmpty()) {
            if (loginService.isExist(userName) && loginService.isLogin(userName, password)) {
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/home-page.fxml"));
                Scene newScene = new Scene(fxmlLoader.load(), 900, 500);
                stage.setScene(newScene);
            } else if (!loginService.isExist(userName)) {
                displayErrorWithStyle("User Not found");
            } else displayErrorWithStyle("Password incorrect");
        } else {
            displayErrorWithStyle("User name and password is required");
        }


    }

    private void displayErrorWithStyle(String message) {
        errorLabel.setText(message);
        errorLabel.setVisible(true);

        // Shake Effect
        TranslateTransition shake = new TranslateTransition(Duration.millis(100), errorLabel);
        shake.setFromX(-10);
        shake.setToX(10);
        shake.setCycleCount(6);
        shake.setAutoReverse(true);

        // Fade-in Effect
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), errorLabel);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);

        // Background Highlight
        errorLabel.setStyle("-fx-background-color: lightcoral; -fx-text-fill: white;");
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(event -> errorLabel.setStyle("-fx-background-color: transparent; -fx-text-fill: red;"));

        // Combine all animations
        SequentialTransition animation = new SequentialTransition(fadeIn, shake, pause);
        animation.play();
    }
}