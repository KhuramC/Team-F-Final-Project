package battleshipMenu.view;

import java.awt.*;

public class DrawGrid {

    public static void drawGridLines(Graphics g, int numRows, int numCols, int cellSize, int startX, int startY) {
    	Graphics2D g2d = (Graphics2D) g;
    	g2d.setStroke(new BasicStroke(2));
    	g.setColor(Color.BLACK);
        // Draw horizontal lines
        for (int row = 0; row <= numRows; row++) {
            int y = startY + row * cellSize;
            g.drawLine(startX, y, startX + numCols * cellSize, y);
        }
        // Draw vertical lines
        for (int col = 0; col <= numCols; col++) {
            int x = startX + col * cellSize;
            g.drawLine(x, startY, x, startY + numRows * cellSize);
        }
    }
}
