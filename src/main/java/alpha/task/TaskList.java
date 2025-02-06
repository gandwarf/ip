package alpha.task;

import java.util.ArrayList;

import alpha.Storage;

/**
 * Represents a list of tasks. Provides methods to add, remove, and modify tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs a {@code TaskList} instance by loading tasks from storage.
     *
     * @param storage The storage handler to load tasks from.
     */
    public TaskList(Storage storage) {
        tasks = storage.load();
    }

    /**
     * Adds a new task to the list and displays a confirmation message.
     *
     * @param task The task to be added.
     */
    public String add(Task task) {
        tasks.add(task);
        return "Got it. I've added this task:\n" + task + "\nNow you have " + tasks.size() + " tasks in the list.";
    }
    /**
     * Marks a task as done based on its index.
     *
     * @param index The index of the task to mark as done.
     */
    public String markTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            if (task.isMarked()) {
                return "This task is already marked.";
            } else {
                task.mark();
                return "Nice! I've marked this task as done:\n" + task;
            }
        } else {
            return "Invalid index: " + (index + 1);
        }
    }


    /**
     * Unmarks a task as not done based on its index.
     *
     * @param index The index of the task to unmark.
     */
    public String unmarkTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            if (!task.isMarked()) {
                return "This task is already unmarked.";
            } else {
                task.unMark();
                return "OK, I've marked this task as not done yet:\n" + task;
            }
        } else {
            return "Invalid index: " + (index + 1);
        }
    }


    /**
     * Deletes a task from the list based on its index.
     *
     * @param index The index of the task to delete.
     */
    public String deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            Task removedTask = tasks.remove(index);
            return "Noted. I've removed this task:\n" + removedTask + "\nNow you have " + tasks.size()
                    + " tasks in the list.";
        } else {
            return "Invalid index: " + (index + 1);
        }
    }

    /**
     * Finds tasks that contain the given keyword.
     *
     * @param keyword The search keyword.
     */
    public String findTasks(String keyword) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");
        int count = 1;
        for (Task task : tasks) {
            if (task.toString().toLowerCase().contains(keyword.toLowerCase())) {
                sb.append(count).append(". ").append(task).append("\n");
                count++;
            }
        }
        if (count == 1) {
            sb.append("No matching tasks found.");
        }
        return sb.toString();
    }
    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public String getListString() {
        StringBuilder sb = new StringBuilder();
        if (tasks.isEmpty()) {
            sb.append("The list is empty.");
        } else {
            sb.append("Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                sb.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
            }
        }
        return sb.toString();
    }
}
