package alpha.task;
/**
 * Represents a simple to-do task without a specific deadline or time range.
 * Inherits from the {@link Task} class.
 */
public class ToDo extends Task {

    /**
     * Constructs a new {@code ToDo} task with the specified name.
     *
     * @param taskName The name or description of the to-do task.
     */
    public ToDo(String taskName) {
        super(taskName);
    }

    /**
     * Returns a string representation of this to-do task,
     * including the task type indicator <b>[T]</b> followed
     * by the base task description (from {@link Task#toString()}).
     *
     * @return A string describing this to-do task, for example:
     *         <code>[T][ ] Buy groceries</code>.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string in the format used to store this to-do task in a file.
     * Typically used by the application to save tasks to disk.
     *
     * @return A string containing only the task name.
     */
    public String getFileFormat() {
        return this.taskName;
    }
}
