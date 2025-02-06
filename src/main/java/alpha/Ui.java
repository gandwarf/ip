package alpha;

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
}
