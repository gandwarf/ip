public class Task {
    protected final String taskName;
    protected boolean done;

    Task(String taskName) {
        this.taskName = taskName;
        done = false;
    }

    protected boolean isMarked() {
        return done;
    }

    protected void mark() {
        done = true;
    }

    protected void unMark() {
        done = false;
    }

    @Override
    public String toString() {
        if (done) {
            return "[X] " + taskName;
        } else {
            return "[ ] " + taskName;
        }
    }
}

