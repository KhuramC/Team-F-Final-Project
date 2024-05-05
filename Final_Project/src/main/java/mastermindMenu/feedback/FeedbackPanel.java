package mastermindMenu.feedback;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * This class provides a scrollable feedback panel using a JTextArea.
 * @author Alon B.
 */
public class FeedbackPanel extends JScrollPane {
	    private static final long serialVersionUID = 1L;
		private JTextArea feedbackArea;

		
	    /**
	     * Constructs a FeedbackPanel.
	     * Initializes the JTextArea with a fixed size and makes it non-editable.
	     */
	    public FeedbackPanel() {
	        feedbackArea = new JTextArea(5, 30);
	        feedbackArea.setEditable(false);
	        setViewportView(feedbackArea);
	    }
	    
	    /**
	     * Appends a given string of feedback to the text area.
	     * Each message is appended in a new line.
	     * @param feedback The feedback message to be added to the feedback area.
	     */
	    public void appendFeedback(String feedback) {
	        feedbackArea.append(feedback + "\n");
	    }
	    
	    public void clearFeedback() {
	        feedbackArea.setText("");
	    }
	}
