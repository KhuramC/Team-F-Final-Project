package battleshipMenu.model;

public enum MapSize {
    SMALL_7x7(7, 7),
    NORMAL_10x10(10, 10),
    LARGE_13x13(13, 13);

    private final int rows;
    private final int cols;

    MapSize(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

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
