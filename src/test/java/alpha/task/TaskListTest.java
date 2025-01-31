package alpha.task;

import alpha.Storage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList(new Storage());
        taskList.getTasks().clear();
    }

    @Test
    void testAddTask() {
        Task todo = new ToDo("Read book");
        taskList.add(todo);

        List<Task> tasks = taskList.getTasks();
        assertEquals(1, tasks.size(), "Task list should contain 1 task");
        assertEquals(todo, tasks.get(0), "The first task should be 'Read book'");
    }
}
