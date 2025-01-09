package inventoryManagement.utils;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Optional;

public class MyUtils {
    public static boolean showConfirmationAlert(String title, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);

        // Adding buttons (optional, JavaFX provides OK and Cancel by default)
        ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(yesButton, noButton);

        // Show the alert and wait for a response
        Optional<ButtonType> result = alert.showAndWait();

        // Return true if the user clicked "Yes"
        return result.isPresent() && result.get() == yesButton;
    }

    public static void showAlert(Alert.AlertType alertType, String title, String headerText, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    public static void showNotification(Stage stage, String message) {
        Label notification = new Label(message);
        notification.setStyle("-fx-background-color: #323232; -fx-text-fill: white; -fx-padding: 10; -fx-border-radius: 5; -fx-background-radius: 5;");
        notification.setOpacity(0); // Start invisible

        Popup popup = new Popup();
        popup.getContent().add(notification);
        popup.setAutoHide(true); // Close when clicked or loses focus
        popup.show(stage);

        // Fade-in and fade-out effect
        FadeTransition fadeIn = new FadeTransition(Duration.millis(300), notification);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);

        PauseTransition delay = new PauseTransition(Duration.seconds(2)); // Display duration

        FadeTransition fadeOut = new FadeTransition(Duration.millis(300), notification);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setOnFinished(e -> popup.hide());

        SequentialTransition sequentialTransition = new SequentialTransition(fadeIn, delay, fadeOut);
        sequentialTransition.play();
    }



}
