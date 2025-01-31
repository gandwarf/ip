package alpha;

/**
 * The {@code Ui} class handles the user interface interactions.
 * It provides methods to display a greeting message and an exit message.
 */
public class Ui {

    /**
     * Constructs a {@code Ui} instance.
     */
    public Ui() {}

    /**
     * Displays a greeting message to the user.
     */
    protected void greeting() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Alpha");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays an exit message when the user leaves.
     */
    protected void exit() {
        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you soon!");
        System.out.println("____________________________________________________________");
    }
}
