package alpha.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
/**
 * Represents an event which occurs within a specific time range.
 * In addition to a name (inherited from {@link Task}),
 * this class maintains a start time and an end time.
 */
public class Event extends Task {

    /**
     * The start time of this event.
     */
    private final LocalDateTime from;

    /**
     * The end time of this event.
     */
    private final LocalDateTime to;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy, h:mma",
            Locale.ENGLISH);

    /**
     * Constructs a new {@code Event} task with the specified
     * task name, start time, and end time.
     *
     * @param taskName The name or description of the event.
     * @param from The starting time of the event.
     * @param to The ending time of the event.
     */
    public Event(String taskName, String from, String to) {
        super(taskName);
        try {
            this.from = LocalDateTime.parse(from, INPUT_FORMAT);
            this.to = LocalDateTime.parse(to, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Use yyyy-MM-dd HHmm");
        }
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
        return "[E]" + super.toString() + " (from: " +
                from.format(OUTPUT_FORMAT) + " to: " + to.format(OUTPUT_FORMAT) + ")";
    }

    /**
     * Returns a string in the format used to store this event in a file.
     * Typically used by the application to save tasks to disk.
     *
     * @return A string combining the task name, start time, and end time,
     *         each separated by <code>" | "</code>.
     */
    public String getFileFormat() {
        return this.taskName + " | " +
                this.from.format(INPUT_FORMAT) + " | " + this.to.format(INPUT_FORMAT);
    }
}
