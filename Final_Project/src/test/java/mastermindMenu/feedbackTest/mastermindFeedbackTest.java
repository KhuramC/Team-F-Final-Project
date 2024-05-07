package mastermindMenu.feedbackTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mastermindMenu.feedback.FeedbackPanel;

/**
 * Tests for the FeedbackPanel class of the Mastermind game.
 * This class ensures that the feedback functionality (adding and clearing feedback) works as expected.
 * @author Alon B.
 */
class mastermindFeedbackTest {
	
	private FeedbackPanel feedbackPanel;

	/**
     * Sets up a new FeedbackPanel before each test to ensure a clean state.
     */
    @BeforeEach
    void setUp() {
        feedbackPanel = new FeedbackPanel();
    }

    /**
     * Tests that feedback text is appended correctly to the FeedbackPanel.
     * It checks for the correct initial state, single append, and multiple appends.
     */
    @Test
    void testAppendFeedback() {
        // Initially, the text area should be empty
        assertEquals("", feedbackPanel.getFeedbackArea().getText(), "Initially, FeedbackArea should be empty");

        // Append a single line of feedback and verify
        feedbackPanel.appendFeedback("Test feedback 1");
        assertEquals("Test feedback 1\n", feedbackPanel.getFeedbackArea().getText(), "Feedback should match");

        // Append another line of feedback and verify
        feedbackPanel.appendFeedback("Test feedback 2");
        assertEquals("Test feedback 1\nTest feedback 2\n", feedbackPanel.getFeedbackArea().getText(), "Feedback should append correctly");
    }

    /**
     * Tests that the FeedbackPanel can be cleared of all feedback.
     * It verifies the functionality by first appending text and then ensuring the panel is empty after clearing.
     */
    @Test
    void testClearFeedback() {
        // Add some text first
        feedbackPanel.appendFeedback("Test feedback 1");
        feedbackPanel.appendFeedback("Test feedback 2");

        // Clear the text area
        feedbackPanel.clearFeedback();

        // Verify it is empty
        assertEquals("", feedbackPanel.getFeedbackArea().getText(), "FeedbackArea should be empty after clear");
    }

}
