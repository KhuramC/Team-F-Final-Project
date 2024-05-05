package battleshipMenu.model;
/**
 * Enum representing the different sizes of the game map.
 * 
 * @author Roney
 */
public enum MapSize {
    SMALL_7x7(7, 7),
    NORMAL_10x10(10, 10),
    LARGE_13x13(13, 13);

    private final int rows;
    private final int cols;
    /**
     * Constructor for MapSize enum.
     * @param rows The number of rows in the map.
     * @param cols The number of columns in the map.
     * 
     * @author Roney
     */
    MapSize(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }
    /**
     * Gets the number of rows in the map.
     * @return The number of rows.
     * 
     * @author Roney
     */
    public int getRows() {
        return rows;
    }
    /**
     * Gets the number of columns in the map.
     * @return The number of columns.
     * 
     * @author Roney
     */

    public int getCols() {
        return cols;
    }
    /**
     * Converts a string representation of map size to the corresponding MapSize enum value.
     * @param size The string representation of map size.
     * @return The MapSize enum value corresponding to the input string.
     * @throws IllegalArgumentException If the input size string is invalid.
     * 
     * @author Roney
     */
    public static MapSize fromString(String size) {
        switch (size) {
            case "Small (7x7)":
                return SMALL_7x7;
            case "Normal (10x10)":
                return NORMAL_10x10;
            case "Large (13x13)":
                return LARGE_13x13;
            default:
                throw new IllegalArgumentException("Invalid board size: " + size);
        }
    }
}
