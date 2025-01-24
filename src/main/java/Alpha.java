import java.util.ArrayList;
import java.util.Scanner;

public class Alpha {
    private static final ArrayList<Task> list = new ArrayList<>();

    Alpha() {}

    private static void add(Task task) {
        list.add(task); // Add task to ArrayList
        System.out.println("____________________________________________________________");
        System.out.println(" added: " + task);
        System.out.println("____________________________________________________________");
    }

    private static void showList() {
        System.out.println("____________________________________________________________");
        if (list.isEmpty()) {
            System.out.println(" The list is empty.");
        } else {
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

    private static void blah() {
        System.out.println("____________________________________________________________");
        System.out.println(" blah");
        System.out.println("____________________________________________________________");
    }

    private static void exit() {
        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you soon!");
        System.out.println("____________________________________________________________");
    }

    private static void run() {
        Scanner in = new Scanner(System.in);
        greeting();
        String input = in.nextLine();
        String[] words = input.split("\\s+");
        while (!input.equals("bye")) {
            switch (words[0]) {
                case "list" -> showList();
                case "blah" -> blah();
                case "mark" -> {
                    try {
                        int index = Integer.parseInt(words[1]) - 1; // Convert to 0-based index
                        markTask(index);
                    } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                        System.out.println("____________________________________________________________");
                        System.out.println(" Invalid format. Use: mark <index>");
                        System.out.println("____________________________________________________________");
                    }
                }
                case "unmark" -> {
                    try {
                        int index = Integer.parseInt(words[1]) - 1; // Convert to 0-based index
                        unmarkTask(index);
                    } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                        System.out.println("____________________________________________________________");
                        System.out.println(" Invalid format. Use: unmark <index>");
                        System.out.println("____________________________________________________________");
                    }
                }
                default -> {
                    String description = String.join(" ", words);
                    add(new Task(description));
                }
            }
            input = in.nextLine();
            words = input.split("\\s+");
        }
        exit();
    }

    public static void main(String[] args) {
        run();
    }
}
