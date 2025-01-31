package alpha;

import alpha.task.Task;
import alpha.task.ToDo;
import alpha.task.Deadline;
import alpha.task.Event;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The {@code Storage} class handles reading from and writing to a file
 * to persist task data. It provides methods to save tasks to a file
 * and load them back into memory.
 */
public class Storage {
    private static final Path HOME = Paths.get("").toAbsolutePath();
    private static final Path DATA_PATH = HOME.resolve("data");
    private static final Path FILE_PATH = DATA_PATH.resolve("alpha.txt");

    /**
     * Constructs a {@code Storage} instance.
     */
    public Storage() {}

    /**
     * Saves the given list of tasks to a file.
     *
     * @param tasks The list of tasks to be saved.
     */
    protected void save(ArrayList<Task> tasks) {
        try {
            if (!Files.exists(DATA_PATH)) {
                Files.createDirectories(DATA_PATH);
            }
            try (BufferedWriter writer = Files.newBufferedWriter(FILE_PATH)) {
                for (Task task : tasks) {
                    writer.write(taskToFileFormat(task));
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the file, if it exists.
     *
     * @return A list of tasks read from the file.
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
     * Converts a {@code Task} into a formatted string for file storage.
     *
     * @param task The task to be converted.
     * @return A formatted string representing the task.
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
     * Parses a task from a formatted string.
     *
     * @param line The formatted task string.
     * @return The corresponding {@code Task} object, or {@code null} if parsing fails.
     */
    protected Task parseTask(String line) {
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
}
