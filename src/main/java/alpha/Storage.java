package alpha;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import alpha.task.Deadline;
import alpha.task.Event;
import alpha.task.Task;
import alpha.task.ToDo;

/**
 * Manages data persistence for the Alpha application by reading and writing
 * tasks to an external file. This class provides methods to save a list of
 * tasks and load them back into memory.
 */
public class Storage {

    /**
     * Represents the current working directory of the user.
     */
    private static final Path HOME = Paths.get("").toAbsolutePath();

    /**
     * Path to the directory where data files will be stored.
     */
    private static final Path DATA_PATH = HOME.resolve("data");

    /**
     * Path to the specific data file where tasks are saved.
     */
    private static final Path FILE_PATH = DATA_PATH.resolve("alpha.txt");

    /**
     * Constructs a {@code Storage} object for handling task data.
     */
    public Storage() {
    }

    /**
     * Saves the provided list of tasks to the data file. If the directories
     * do not exist, they are created automatically before writing.
     *
     * @param tasks The list of tasks to be written to the file.
     */
    public void save(ArrayList<Task> tasks) {
        try {
            if (!Files.exists(DATA_PATH)) {
                Files.createDirectories(DATA_PATH);
            }
            try (BufferedWriter writer = Files.newBufferedWriter(FILE_PATH)) {
                tasks.stream()
                        .map(this::taskToFileFormat)
                        .forEach(line -> {
                            try {
                                writer.write(line);
                                writer.newLine();
                            } catch (IOException e) {
                                throw new RuntimeException("Error writing task to file", e);
                            }
                        });
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the data file into memory. If the file does not exist,
     * an empty list of tasks is returned.
     *
     * @return A list of tasks read from the data file.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        if (!Files.exists(FILE_PATH)) {
            return tasks;
        }
        try (Scanner scanner = new Scanner(Files.newBufferedReader(FILE_PATH))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = parseTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Converts a task into a string format suitable for saving to the data file.
     *
     * @param task The task to be converted.
     * @return A formatted string representing the task data.
     */
    protected String taskToFileFormat(Task task) {
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
     * Parses a single line of text to reconstruct the corresponding task object.
     *
     * @param line A formatted string representing a task.
     * @return The {@code Task} object derived from the given string,
     *         or {@code null} if the line is invalid or cannot be parsed.
     */
    protected Task parseTask(String line) {
        try {
            String[] parts = line.split("\\|");
            String type = parts[0].trim();
            boolean isDone = "1".equals(parts[1].trim());
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
}
