package alpha;

import alpha.task.TaskList;
import alpha.task.ToDo;
import alpha.task.Deadline;
import alpha.task.Event;

import java.util.Scanner;

/**
 * Represents the main entry point of the Alpha task manager application.
 * <p>
 * This console-based application supports various commands to manage tasks:
 * listing, marking, unmarking, creating, and deleting different types of tasks
 * (ToDo, Deadline, Event). It also handles the saving and loading of tasks.
 */
public class Alpha {

    /**
     * Handles all user interaction (input and output) within the application.
     */
    private final Ui ui;

    /**
     * Responsible for saving and loading tasks from the storage file.
     */
    private final Storage storage;

    /**
     * Maintains and manipulates the list of tasks in memory.
     */
    private final TaskList taskList;

    /**
     * Constructs an instance of {@code Alpha}, initializing the user interface,
     * storage, and task list components.
     */
    public Alpha() {
        ui = new Ui();
        storage = new Storage();
        taskList = new TaskList(storage);
    }

    /**
     * Continuously reads user commands from the standard input and executes them
     * until the "bye" command is encountered. Supported commands include:
     * <ul>
     *     <li><b>list</b>: Display all tasks.</li>
     *     <li><b>mark &lt;index&gt;</b>: Mark the task at the specified index.</li>
     *     <li><b>unmark &lt;index&gt;</b>: Unmark the task at the specified index.</li>
     *     <li><b>todo &lt;description&gt;</b>: Add a new ToDo task.</li>
     *     <li><b>deadline &lt;description&gt; /by &lt;deadline&gt;</b>: Add a new Deadline task.</li>
     *     <li><b>event &lt;description&gt; /from &lt;start&gt; /to &lt;end&gt;</b>: Add a new Event task.</li>
     *     <li><b>delete &lt;index&gt;</b>: Delete the task at the specified index.</li>
     *     <li><b>bye</b>: Exit the application.</li>
     * </ul>
     * <p>
     * If an invalid command is entered or if an exception occurs while processing
     * a command, an error message is displayed.
     * </p>
     */
    public void run() {
        Scanner in = new Scanner(System.in);
        ui.greeting();
        String input = in.nextLine();
        while (!input.equals("bye")) {
            String[] words = input.split("\\s+", 2);
            try {
                switch (words[0]) {
                    case "list" -> taskList.showList();
                    case "mark" -> taskList.markTask(Integer.parseInt(words[1]) - 1);
                    case "unmark" -> taskList.unmarkTask(Integer.parseInt(words[1]) - 1);
                    case "todo" -> taskList.add(new ToDo(words[1]));
                    case "deadline" -> {
                        String[] parts = words[1].split(" /by ", 2);
                        taskList.add(new Deadline(parts[0], parts[1]));
                    }
                    case "event" -> {
                        String[] parts = words[1].split(" /from ", 2);
                        String[] times = parts[1].split(" /to ", 2);
                        taskList.add(new Event(parts[0], times[0], times[1]));
                    }
                    case "delete" -> taskList.deleteTask(Integer.parseInt(words[1]) - 1);
                    default -> {
                        System.out.println("____________________________________________________________");
                        System.out.println(" OOPS!!! I'm sorry, but I don't know what that means :-(");
                        System.out.println("____________________________________________________________");
                    }
                }
            } catch (Exception e) {
                System.out.println("____________________________________________________________");
                System.out.println(" OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println("____________________________________________________________");
            }
            input = in.nextLine();
        }
        storage.save(taskList.getTasks());
        ui.exit();
    }

    /**
     * Launches the Alpha application by creating an {@code Alpha} instance
     * and invoking its {@link #run()} method.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        Alpha alpha = new Alpha();
        alpha.run();
    }
}
