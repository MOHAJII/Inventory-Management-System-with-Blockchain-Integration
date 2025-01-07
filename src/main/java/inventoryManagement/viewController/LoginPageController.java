package inventoryManagement.viewController;

import inventoryManagement.dao.entities.User;
import inventoryManagement.service.LoginService;
import inventoryManagement.service.SessionManager;
import inventoryManagement.service.UserService;
import io.github.cdimascio.dotenv.Dotenv;
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

    private final SessionManager sessionManager;
    private final UserService userService;

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

    public LoginPageController() {
        Dotenv dotenv = Dotenv.load();
        String REDIS_URI = dotenv.get("REDIS_URI");
        this.sessionManager = new SessionManager(REDIS_URI, 3600);
        userService = new UserService();
    }

    public void onSignIn(Event event) throws IOException {
        String userName = userNameField.getText();
        String password = passwordField.getText();
        LoginService loginService = new LoginService();

        if (!userName.isEmpty() && !password.isEmpty()) {
            if (loginService.isExist(userName) && loginService.isLogin(userName, password)) {
                User user = userService.getByUsername(userName);
                String sessionId = sessionManager.createSession(user);
                System.out.println(sessionId);
                redirectToHomePage(sessionId);
            } else if (!loginService.isExist(userName)) {
                displayErrorWithStyle("User Not found");
            } else displayErrorWithStyle("Password incorrect");
        } else {
            displayErrorWithStyle("User name and password is required");
        }
    }

    private void redirectToHomePage(String sessionId) {
        try {
            Stage stage = (Stage) userNameField.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/home-page.fxml"));
            Scene newScene = new Scene(fxmlLoader.load(), 1024, 600);

            HomePageController homePageController = fxmlLoader.getController();
            homePageController.setSessionId(sessionId);

            stage.setScene(newScene);
        } catch (IOException e) {
            throw new RuntimeException(e);
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