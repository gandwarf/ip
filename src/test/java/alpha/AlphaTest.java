package alpha;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import static org.junit.jupiter.api.Assertions.*;

class AlphaTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStream, true, StandardCharsets.UTF_8));
    }

    @Test
    void testRunWithUserInput() {
        String simulatedInput = "todo Read book\nlist\nbye\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes(StandardCharsets.UTF_8)));

        Alpha alpha = new Alpha();
        alpha.run();

        String consoleOutput = outputStream.toString();


        assertTrue(consoleOutput.contains("Hello! I'm Alpha"), "Program startup greeting is missing.");
        assertTrue(consoleOutput.contains("Got it. I've added this task:"), "Task addition message is missing.");
        assertTrue(consoleOutput.contains("[T][ ] Read book"), "Task was not added correctly.");
        assertTrue(consoleOutput.contains("Here are the task book"), "Task list message is missing.");
        assertTrue(consoleOutput.contains("Bye. Hope to see you soon!"), "Exit message is missing.");
    }
}
