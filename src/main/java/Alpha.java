import java.util.Scanner;

public class Alpha {
    private static void greeting() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Alpha");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    private static void showList() {
        System.out.println("____________________________________________________________");
        System.out.println(" list");
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
