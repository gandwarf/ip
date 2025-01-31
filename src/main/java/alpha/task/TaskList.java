package alpha.task;

import alpha.Storage;

import java.util.ArrayList;

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
    public void add(Task task) {
        tasks.add(task);
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays all tasks in the list.
     * If the list is empty, an appropriate message is shown.
     */
    public void showList() {
        System.out.println("____________________________________________________________");
        if (tasks.isEmpty()) {
            System.out.println(" The list is empty.");
        } else {
            System.out.println(" Here are the task book:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(" " + (i + 1) + ". " + tasks.get(i));
            }
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * Marks a task as done based on its index.
     *
     * @param index The index of the task to mark as done.
     */
    public void markTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            if (task.isMarked()) {
                System.out.println("____________________________________________________________");
                System.out.println(" This task has already been marked.");
                System.out.println("____________________________________________________________");
            } else {
                task.mark();
                System.out.println("____________________________________________________________");
                System.out.println(" Nice! I've marked this task as done:");
                System.out.println("   " + task);
                System.out.println("____________________________________________________________");
            }
        } else {
            System.out.println("____________________________________________________________");
            System.out.println(" Invalid index: " + (index + 1));
            System.out.println("____________________________________________________________");
        }
    }

    /**
     * Unmarks a task as not done based on its index.
     *
     * @param index The index of the task to unmark.
     */
    public void unmarkTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            if (!task.isMarked()) {
                System.out.println("____________________________________________________________");
                System.out.println(" This task has already been unmarked.");
                System.out.println("____________________________________________________________");
            } else {
                task.unMark();
                System.out.println("____________________________________________________________");
                System.out.println(" OK, I've marked this task as not done yet:");
                System.out.println("   " + task);
                System.out.println("____________________________________________________________");
            }
        } else {
            System.out.println("____________________________________________________________");
            System.out.println(" Invalid index: " + (index + 1));
            System.out.println("____________________________________________________________");
        }
    }

    /**
     * Deletes a task from the list based on its index.
     *
     * @param index The index of the task to delete.
     */
    public void deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            Task removedTask = tasks.remove(index); // Remove the task
            System.out.println("____________________________________________________________");
            System.out.println(" Noted. I've removed this task:");
            System.out.println("   " + removedTask);
            System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
            System.out.println("____________________________________________________________");
        } else {
            System.out.println("____________________________________________________________");
            System.out.println(" Invalid index: " + (index + 1));
            System.out.println("____________________________________________________________");
        }
    }

    /**
     * Finds tasks that contain the given keyword.
     *
     * @param keyword The search keyword.
     */
    public void findTasks(String keyword) {
        System.out.println("____________________________________________________________");
        System.out.println(" Here are the matching tasks in your list:");
        int count = 1;
        for (Task task : tasks) {
            if (task.toString().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(" " + count + ". " + task);
                count++;
            }
        }
        if (count == 1) {
            System.out.println(" No matching tasks found.");
        }
        System.out.println("____________________________________________________________");
    }


    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
