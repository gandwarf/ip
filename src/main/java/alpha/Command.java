package alpha;

import alpha.task.Deadline;
import alpha.task.Event;
import alpha.task.TaskList;
import alpha.task.ToDo;

public class Command {
    private final String command;

    public Command(String command) {
        this.command = command;
    }

    protected String execute(Ui ui, TaskList taskList, Storage storage) {
        StringBuilder response = new StringBuilder();
        String[] words = command.split("\\s+", 2);
        try {
            switch (words[0]) {
            case "list" -> response.append(taskList.getListString());
            case "mark" -> response.append(taskList.markTask(Integer.parseInt(words[1]) - 1));
            case "unmark" -> response.append(taskList.unmarkTask(Integer.parseInt(words[1]) - 1));
            case "todo" -> {
                ToDo task = new ToDo(words[1]);
                response.append(taskList.add(task));
            }
            case "deadline" -> {
                String[] parts = words[1].split(" /by ", 2);
                Deadline task = new Deadline(parts[0], parts[1]);
                response.append(taskList.add(task));
            }
            case "event" -> {
                String[] parts = words[1].split(" /from ", 2);
                String[] times = parts[1].split(" /to ", 2);
                Event task = new Event(parts[0], times[0], times[1]);
                response.append(taskList.add(task));
            }
            case "delete" -> response.append(taskList.deleteTask(Integer.parseInt(words[1]) - 1));
            case "find" -> response.append(taskList.findTasks(words[1]));
            case "bye" -> {
                storage.save(taskList.getTasks());
                response.append(ui.exit());
            }
            default -> response.append("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (Exception e) {
            response.append("OOPS!!! An error occurred while processing your command.");
        }
        return response.toString();
    }
}
