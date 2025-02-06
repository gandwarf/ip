package alpha.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
/**
 * Represents a task with a specific deadline.
 * In addition to a name (inherited from {@link Task}),
 * this class maintains a {@code deadline} string.
 */
public class Deadline extends Task {

    /**
     * The deadline or due date associated with this task.
     */
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy, h:mma",
            Locale.ENGLISH);
    private final LocalDateTime deadline;

    /**
     * Constructs a new {@code Deadline} task with the specified
     * task name and deadline string.
     *
     * @param taskName The name or description of the task.
     * @param deadline The deadline or due date for the task.
     */
    public Deadline(String taskName, String deadline) {
        super(taskName);
        try {
            this.deadline = LocalDateTime.parse(deadline, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Use yyyy-MM-dd HHmm");
        }
    }

    /**
     * Returns a string representation of this deadline task,
     * including the task type indicator <b>[D]</b>, the base task
     * description (from {@link Task#toString()}), and the deadline
     * in a <code>(by: ...)</code> format.
     *
     * @return A string describing this deadline task, for example:
     *         <code>[D][X] Finish project (by: Friday)</code>.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.format(OUTPUT_FORMAT) + ")";
    }

    /**
     * Returns a string in the format used to store this task in a file.
     * Typically used by the application to save tasks to disk.
     *
     * @return A string combining the task name and deadline, separated by " | ".
     */
    public String getFileFormat() {
        return this.taskName + " | " + this.deadline.format(INPUT_FORMAT);
    }
}

