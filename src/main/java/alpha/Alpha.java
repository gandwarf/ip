package alpha;

import alpha.task.TaskList;
import alpha.task.ToDo;
import alpha.task.Deadline;
import alpha.task.Event;

import java.util.Scanner;

/**
 * The {@code Alpha} class is a console-based task manager application. It supports
 * creating, listing, marking, unmarking, and deleting different types of tasks
 * (ToDo, Deadline, Event). Tasks are persisted to and loaded from a file.
 */
public class Alpha {

    private final Ui ui;
    private final Storage storage;
    private final TaskList taskList;

    public Alpha() {
        ui = new Ui();
        storage = new Storage();
        taskList = new TaskList(storage);
    }

    /**
     * The main loop of the program that processes user commands from standard input:
     * <ul>
     *   <li><b>list</b> - Show all tasks.</li>
     *   <li><b>mark</b> <i>index</i> - Mark the task at the given index.</li>
     *   <li><b>unmark</b> <i>index</i> - Unmark the task at the given index.</li>
     *   <li><b>todo</b> <i>description</i> - Add a ToDo task.</li>
     *   <li><b>deadline</b> <i>description</i> /by <i>deadline</i> - Add a Deadline task.</li>
     *   <li><b>event</b> <i>description</i> /from <i>start</i> /to <i>end</i> - Add an Event task.</li>
     *   <li><b>delete</b> <i>index</i> - Delete the task at the given index.</li>
     *   <li><b>bye</b> - Exit the program.</li>
     * </ul>
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
     * The application's entry point. Creates an {@code Alpha} instance and runs it.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        Alpha alpha = new Alpha();
        alpha.run();
    }
}


