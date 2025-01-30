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
    protected boolean done;

    /**
     * Constructs a new {@code Task} with the specified name.
     * The task is initially not done.
     *
     * @param taskName The name or description of the task.
     */
    Task(String taskName) {
        this.taskName = taskName;
        done = false;
    }

    /**
     * Returns whether this task is marked as done.
     *
     * @return {@code true} if the task is marked as done; {@code false} otherwise.
     */
    protected boolean isMarked() {
        return done;
    }

    /**
     * Marks this task as done.
     */
    protected void mark() {
        done = true;
    }

    /**
     * Unmarks this task (marks it as not done).
     */
    protected void unMark() {
        done = false;
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
        if (done) {
            return "[X] " + taskName;
        } else {
            return "[ ] " + taskName;
        }
    }
}
