/**
 * Represents a task with a specific deadline.
 * In addition to a name (inherited from {@link Task}),
 * this class maintains a {@code deadline} string.
 */
public class Deadline extends Task {

    /**
     * The deadline or due date associated with this task.
     */
    private final String deadline;

    /**
     * Constructs a new {@code Deadline} task with the specified
     * task name and deadline string.
     *
     * @param taskName The name or description of the task.
     * @param deadline The deadline or due date for the task.
     */
    Deadline(String taskName, String deadline) {
        super(taskName);
        this.deadline = deadline;
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
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }

    /**
     * Returns a string in the format used to store this task in a file.
     * Typically used by the application to save tasks to disk.
     *
     * @return A string combining the task name and deadline, separated by " | ".
     */
    public String getFileFormat() {
        return this.taskName + " | " + this.deadline;
    }
}

