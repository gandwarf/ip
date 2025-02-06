package alpha.task;
/**
 * Represents a generic task with a name and a completion status.
 */
public class Task {

    /**
     * The name or description of this task.
     */
    protected final String taskName;

    /**
     * Indicates whether this task is marked as done.
     */
    protected boolean isDone;

    /**
     * Constructs a new {@code Task} with the specified name.
     * The task is initially not done.
     *
     * @param taskName The name or description of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        isDone = false;
    }

    /**
     * Returns whether this task is marked as done.
     *
     * @return {@code true} if the task is marked as done; {@code false} otherwise.
     */
    public boolean isMarked() {
        return isDone;
    }

    /**
     * Marks this task as done.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Unmarks this task (marks it as not done).
     */
    public void unMark() {
        isDone = false;
    }

    /**
     * Returns a string representation of this task, indicating
     * whether it is done or not.
     * <p>
     * For example:
     * <ul>
     *   <li>{@code "[X] Task Name"} if it is done.</li>
     *   <li>{@code "[ ] Task Name"} if it is not done.</li>
     * </ul>
     *
     * @return A string describing this task.
     */
    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + taskName;
        } else {
            return "[ ] " + taskName;
        }
    }
}
