package alpha;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * Handles user interface interactions for the Alpha application.
 * It offers methods to display greeting and exit messages.
 */
public class Ui {

    /**
     * Constructs a new {@code Ui} instance.
     */
    public Ui() {
    }

    /**
     * Prints a greeting message to welcome the user.
     */
    protected String greeting() {
        return "Hello, I'm Alpha\n" + "What can I do for you?\n";
    }

    /**
     * Prints a farewell message when the application is about to close.
     */
    protected String exit() {
        return "Goodbye, hope to see you soon!\n";
    }

    /**
     * Displays a confirmation dialog when a duplicate task is detected,
     * asking the user whether they still want to add it.
     *
     * @param message The message displaying details about the duplicate task.
     * @return "y" if the user confirms (clicks "OK"), otherwise "n" (clicks "Cancel" or closes the dialog).
     */
    public String confirmUserAction(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Duplicate Task Detected");
        alert.setHeaderText("This task already exists!");
        alert.setContentText(message + "\nDo you still want to add it?");
        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK ? "y" : "n";
    }
}
