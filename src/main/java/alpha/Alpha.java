package alpha;

import alpha.task.Deadline;
import alpha.task.Event;
import alpha.task.TaskList;
import alpha.task.ToDo;

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
    public String getResponse(String input) {
        StringBuilder response = new StringBuilder();
        String[] words = input.split("\\s+", 2);
        try {
            switch (words[0]) {
            case "list" -> response.append(taskList.getListString());
            case "mark" -> response.append(taskList.markTask(Integer.parseInt(words[1]) - 1));
            case "unmark" -> response.append(taskList.unmarkTask(Integer.parseInt(words[1]) - 1));
            case "todo" -> {
                ToDo task = new ToDo(words[1]);
                response.append(taskList.add(task));
            }
            case "deadline" -> {
                String[] parts = words[1].split(" /by ", 2);
                Deadline task = new Deadline(parts[0], parts[1]);
                response.append(taskList.add(task));
            }
            case "event" -> {
                String[] parts = words[1].split(" /from ", 2);
                String[] times = parts[1].split(" /to ", 2);
                Event task = new Event(parts[0], times[0], times[1]);
                response.append(taskList.add(task));
            }
            case "delete" -> response.append(taskList.deleteTask(Integer.parseInt(words[1]) - 1));
            case "find" -> response.append(taskList.findTasks(words[1]));
            case "bye" -> response.append(ui.exit());
            default -> response.append("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (Exception e) {
            response.append("OOPS!!! An error occurred while processing your command.");
        }
        return response.toString();
    }
}

