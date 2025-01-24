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
                System.out.println(" " + (i + 1) + ". " + list.get(i)); // Display index and task
            }
        }
        System.out.println("____________________________________________________________");
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
            if (words[0].equals("list")) {
                showList();
            } else if (words[0].equals("blah")) {
                blah();
            } else {
                String description = String.join(" ", words);
                add(new Task(description));
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
