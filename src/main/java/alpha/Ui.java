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
    protected void greeting() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Alpha");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints a farewell message when the application is about to close.
     */
    protected void exit() {
        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you soon!");
        System.out.println("____________________________________________________________");
    }
}
