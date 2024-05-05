package mastermindMenu.feedback;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class FeedbackPanel extends JScrollPane {
	    private static final long serialVersionUID = 1L;
		private JTextArea feedbackArea;

	    public FeedbackPanel() {
	        feedbackArea = new JTextArea(5, 30);
	        feedbackArea.setEditable(false);
	        setViewportView(feedbackArea);
	    }

	    public void appendFeedback(String feedback) {
	        feedbackArea.append(feedback + "\n");
	    }
	}
