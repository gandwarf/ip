import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The {@code Alpha} class is a console-based task manager application. It supports
 * creating, listing, marking, unmarking, and deleting different types of tasks
 * (ToDo, Deadline, Event). Tasks are persisted to and loaded from a file.
 */
public class Alpha {

    /**
     * The absolute path to the current project directory.
     */
    private static final Path HOME = Paths.get("").toAbsolutePath();

    /**
     * The path to the data directory under the current project directory.
     */
    private static final Path DATA_PATH = HOME.resolve("data");

    /**
     * The path to the file where tasks are saved.
     */
    private static final Path FILE_PATH = DATA_PATH.resolve("alpha.txt");

    /**
     * An {@code ArrayList} holding all the tasks managed by this application.
     */
    private final ArrayList<Task> list;

    /**
     * Constructs an {@code Alpha} instance. Initializes the internal task list
     * and loads existing tasks from the data file if present.
     */
    public Alpha() {
        list = new ArrayList<>();
        loadTasksFromFile();
    }

    /**
     * Saves all tasks in the task list to the file specified by {@code FILE_PATH}.
     * Creates the data directory if it does not exist.
     */
    private void saveTasksToFile() {
        try {
            if (!Files.exists(DATA_PATH)) {
                Files.createDirectories(DATA_PATH);
            }
            BufferedWriter writer = Files.newBufferedWriter(FILE_PATH);
            for (Task task : list) {
                writer.write(taskToFileFormat(task));
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the file specified by {@code FILE_PATH}. If the file does
     * not exist, it does nothing. Corrupted lines are skipped.
     */
    private void loadTasksFromFile() {
        if (!Files.exists(FILE_PATH)) {
            return;
        }
        try (Scanner scanner = new Scanner(Files.newBufferedReader(FILE_PATH))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = parseTask(line);
                if (task != null) {
                    list.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }

    /**
     * Parses a line from the saved file into a {@link Task} object. The file line
     * is expected to be in the format: <br>
     * <code>[TaskType] | [isDone] | [description] | [additional fields...]</code>
     *
     * @param line The line read from the file.
     * @return A {@code Task} object if parsing is successful, or {@code null} otherwise.
     */
    private Task parseTask(String line) {
        try {
            String[] parts = line.split("\\|");
            String type = parts[0].trim();
            boolean isDone = parts[1].trim().equals("1");
            String description = parts[2].trim();

            Task task = switch (type) {
                case "T" -> new ToDo(description);
                case "D" -> new Deadline(description, parts[3].trim());
                case "E" -> new Event(description, parts[3].trim(), parts[4].trim());
                default -> {
                    System.out.println("Unknown task type: " + type);
                    yield null;
                }
            };

            if (isDone && task != null) {
                task.mark();
            }
            return task;
        } catch (Exception e) {
            System.out.println("Skipping corrupted line: " + line);
            return null;
        }
    }

    /**
     * Converts a {@link Task} into its file storage string representation.
     *
     * @param task The {@code Task} to be converted.
     * @return A string representing this {@code Task} in file format.
     */
    private String taskToFileFormat(Task task) {
        String done = task.isMarked() ? "1" : "0";
        if (task instanceof ToDo) {
            return "T | " + done + " | " + ((ToDo) task).getFileFormat();
        } else if (task instanceof Deadline d) {
            return "D | " + done + " | " + d.getFileFormat();
        } else if (task instanceof Event e) {
            return "E | " + done + " | " + e.getFileFormat();
        }
        return "";
    }

    /**
     * Adds a new {@link Task} to the internal list and prints a confirmation message.
     *
     * @param task The {@code Task} to add.
     */
    private void add(Task task) {
        list.add(task);
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you ave " + list.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays the current list of tasks. If the list is empty, notifies the user.
     */
    private void showList() {
        System.out.println("____________________________________________________________");
        if (list.isEmpty()) {
            System.out.println(" The list is empty.");
        } else {
            System.out.println(" Here are the todo borrow book");
            for (int i = 0; i < list.size(); i++) {
                System.out.println(" " + (i + 1) + "." + list.get(i));
            }
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param index The zero-based index of the task to mark.
     */
    private void markTask(int index) {
        if (index >= 0 && index < list.size()) {
            Task task = list.get(index);
            if (task.isMarked()) {
                System.out.println("____________________________________________________________");
                System.out.println(" This task has been marked.");
                System.out.println("____________________________________________________________");
            } else {
                task.mark();
                System.out.println("____________________________________________________________");
                System.out.println(" Nice! I've marked this task as done:");
                System.out.println("   " + task);
                System.out.println("____________________________________________________________");
            }
        } else {
            System.out.println("____________________________________________________________");
            System.out.println(" Invalid index: " + (index + 1));
            System.out.println("____________________________________________________________");
        }
    }

    /**
     * Unmarks the task at the specified index as not done.
     *
     * @param index The zero-based index of the task to unmark.
     */
    private void unmarkTask(int index) {
        if (index >= 0 && index < list.size()) {
            Task task = list.get(index);
            if (!task.isMarked()) {
                System.out.println("____________________________________________________________");
                System.out.println(" This task has been unmarked.");
                System.out.println("____________________________________________________________");
            } else {
                task.unMark();
                System.out.println("____________________________________________________________");
                System.out.println(" OK, I've marked this task as not done yet:");
                System.out.println("   " + task);
                System.out.println("____________________________________________________________");
            }
        } else {
            System.out.println("____________________________________________________________");
            System.out.println(" Invalid index: " + (index + 1));
            System.out.println("____________________________________________________________");
        }
    }

    /**
     * Prints a greeting message at the start of the application.
     */
    private void greeting() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Alpha");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints a farewell message before the application terminates.
     */
    private void exit() {
        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you soon!");
        System.out.println("____________________________________________________________");
    }

    /**
     * Deletes the task at the specified index from the internal list and
     * prints a confirmation message.
     *
     * @param index The zero-based index of the task to delete.
     */
    private void deleteTask(int index) {
        if (index >= 0 && index < list.size()) {
            Task removedTask = list.remove(index); // Remove the task
            System.out.println("____________________________________________________________");
            System.out.println(" Noted. I've removed this task:");
            System.out.println("   " + removedTask);
            System.out.println(" Now you have " + list.size() + " tasks in the list.");
            System.out.println("____________________________________________________________");
        } else {
            System.out.println("____________________________________________________________");
            System.out.println(" Invalid index: " + (index + 1));
            System.out.println("____________________________________________________________");
        }
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
    private void run() {
        Scanner in = new Scanner(System.in);
        greeting();
        String input = in.nextLine();
        while (!input.equals("bye")) {
            String[] words = input.split("\\s+", 2);
            try {
                switch (words[0]) {
                    case "list" -> showList();
                    case "mark" -> markTask(Integer.parseInt(words[1]) - 1);
                    case "unmark" -> unmarkTask(Integer.parseInt(words[1]) - 1);
                    case "todo" -> add(new ToDo(words[1]));
                    case "deadline" -> {
                        String[] parts = words[1].split(" /by ", 2);
                        add(new Deadline(parts[0], parts[1]));
                    }
                    case "event" -> {
                        String[] parts = words[1].split(" /from ", 2);
                        String[] times = parts[1].split(" /to ", 2);
                        add(new Event(parts[0], times[0], times[1]));
                    }
                    case "delete" -> deleteTask(Integer.parseInt(words[1]) - 1);
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
        saveTasksToFile();
        exit();
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


