package alpha;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Alpha alpha;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/ifan.png"));
    private Image alphaImage = new Image(this.getClass().getResourceAsStream("/images/undeadmask.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Alpha instance */
    public void setAlpha(Alpha a) {
        alpha = a;
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(alpha.ui.greeting(), alphaImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = alpha.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, alphaImage)
        );
        userInput.clear();
        if (input.trim().equalsIgnoreCase("bye")) {
            Platform.runLater(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {}
                Platform.exit();
                System.exit(0);
            });
        }
    }
}
