package mastermindMenu.feedbackTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mastermindMenu.feedback.FeedbackPanel;

class mastermindFeedbackTest {
	
	private FeedbackPanel feedbackPanel;

    @BeforeEach
    void setUp() {
        feedbackPanel = new FeedbackPanel();
    }

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
