import java.util.ArrayList;
import java.util.Scanner;

public class Alpha {
    private static final ArrayList<Task> list = new ArrayList<>();

    Alpha() {}

    private static void add(Task task) {
        list.add(task); // Add task to ArrayList
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you ave " + list.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    private static void showList() {
        System.out.println("____________________________________________________________");
        if (list.isEmpty()) {
            System.out.println(" The list is empty.");
        } else {
            System.out.println(" Here are the todo borrow book");
            for (int i = 0; i < list.size(); i++) {
                System.out.println(" " + (i + 1) + "." + list.get(i)); // Display index and task
            }
        }
        System.out.println("____________________________________________________________");
    }

    private static void markTask(int index) {
        if (index >= 0 && index < list.size()) {
            Task task = list.get(index);
            if (task.isMarked()) {
                System.out.println("____________________________________________________________");
                System.out.println(" This task has been marked.");
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

    private static void unmarkTask(int index) {
        if (index >= 0 && index < list.size()) {
            Task task = list.get(index);
            if (!task.isMarked()) {
                System.out.println("____________________________________________________________");
                System.out.println(" This task has been unmarked.");
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

    private static void greeting() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Alpha");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    private static void exit() {
        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you soon!");
        System.out.println("____________________________________________________________");
    }

    private static void deleteTask(int index) {
        if (index >= 0 && index < list.size()) {
            Task removedTask = list.remove(index); // Remove the task
            System.out.println("____________________________________________________________");
            System.out.println(" Noted. I've removed this task:");
            System.out.println("   " + removedTask);
            System.out.println(" Now you have " + list.size() + " tasks in the list.");
            System.out.println("____________________________________________________________");
        } else {
            System.out.println("____________________________________________________________");
            System.out.println(" Invalid index: " + (index + 1));
            System.out.println("____________________________________________________________");
        }
    }

    private static void run() {
        Scanner in = new Scanner(System.in);
        greeting();
        String input = in.nextLine();
        while (!input.equals("bye")) {
            String[] words = input.split("\\s+", 2);
            try {
                switch (words[0]) {
                    case "list" -> showList();
                    case "mark" -> markTask(Integer.parseInt(words[1]) - 1);
                    case "unmark" -> unmarkTask(Integer.parseInt(words[1]) - 1);
                    case "todo" -> add(new ToDo(words[1]));
                    case "deadline" -> {
                        String[] parts = words[1].split(" /by ", 2);
                        add(new Deadline(parts[0], parts[1]));
                    }
                    case "event" -> {
                        String[] parts = words[1].split(" /from ", 2);
                        String[] times = parts[1].split(" /to ", 2);
                        add(new Event(parts[0], times[0], times[1]));
                    }
                    case "delete" -> deleteTask(Integer.parseInt(words[1]) - 1);
                    default -> {
                        System.out.println("____________________________________________________________");
                        System.out.println(" OOPS!!! I'm sorry, but I don't know what that means :-(");
                        System.out.println("____________________________________________________________");
                    }
                }
            } catch (Exception e) {
                System.out.println("____________________________________________________________");
                System.out.println(" OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println("____________________________________________________________");
            }
            input = in.nextLine();
        }
        exit();
    }

    public static void main(String[] args) {
        run();
    }
}
