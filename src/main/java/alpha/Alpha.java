package alpha;

import alpha.task.TaskList;

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
    protected final Ui ui;
    /**
     * Storages all user data.
     */
    protected final Storage storage;
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
    public String getResponse(String input) {
        return new Command(input).execute(ui, taskList, storage);
    }
}

