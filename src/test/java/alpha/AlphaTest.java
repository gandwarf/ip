package alpha;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class AlphaTest {

    @Test
    void testRunWithUserInput() {
        Alpha alpha = new Alpha();

        // Simulate user input
        String response1 = alpha.getResponse("todo Read book");
        String response2 = alpha.getResponse("list");
        String response3 = alpha.getResponse("bye");

        // Validate responses
        assertEquals("Got it. I've added this task:" + System.lineSeparator()
                        + "[T][ ] Read book" + System.lineSeparator()
                        + "Now you have 7 tasks in the list.",
                response1.trim(), "Task addition response is incorrect.");

        assertTrue(response2.contains("Here are the tasks in your list:"), "Task list response is incorrect.");
        assertTrue(response2.contains("[T][ ] Read book"), "Task list does not contain the added task.");

        assertEquals("Bye. Hope to see you soon!", response3.trim(), "Exit response is incorrect.");
    }
}
