package connect4Menu.view.observerinterfaces;

/**
 * An interface for an Observer that tracks when a column is now invalid to select.
 * @author Khuram C.
 */
public interface IInvalidColsObserver {

	/**
	 * Updates the button associated with the given column number.
	 * @param invalidColNum number of now invalid column.
	 * @return boolean detailing successful update.
	 */
	public boolean updateButton(int invalidColNum);

}
