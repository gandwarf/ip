/**
 * Represents an event which occurs within a specific time range.
 * In addition to a name (inherited from {@link Task}),
 * this class maintains a start time and an end time.
 */
public class Event extends Task {

    /**
     * The start time of this event.
     */
    private final String from;

    /**
     * The end time of this event.
     */
    private final String to;

    /**
     * Constructs a new {@code Event} task with the specified
     * task name, start time, and end time.
     *
     * @param taskName The name or description of the event.
     * @param from The starting time of the event.
     * @param to The ending time of the event.
     */
    Event(String taskName, String from, String to) {
        super(taskName);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of this event, including
     * the task type indicator <b>[E]</b>, the base task description
     * (from {@link Task#toString()}), and the time range in a
     * <code>(from: ... to: ...)</code> format.
     *
     * @return A string describing this event, for example:
     *         <code>[E][ ] Attend meeting (from: 2pm to: 4pm)</code>.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Returns a string in the format used to store this event in a file.
     * Typically used by the application to save tasks to disk.
     *
     * @return A string combining the task name, start time, and end time,
     *         each separated by <code>" | "</code>.
     */
    public String getFileFormat() {
        return this.taskName + " | " + this.from + " | " + this.to;
    }
}
