package connect4Menu.exceptions;

/**
 * A custom Exception for whenever an invalid number has been chosen for the
 * timer length.
 * 
 * @author Khuram C.
 */
public class InvalidTimerTimeException extends Exception {

	public InvalidTimerTimeException(int min, int max) {
		super("Please input a valid integer from " + min + " to " + max + " inclusive.");
	}

}
