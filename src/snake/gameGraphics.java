package snake;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class gameGraphics {
	public gameGraphics() {
	}
	
	public gameGraphics(Graphics2D g2) {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	}

	public void printGrid(Graphics g, int gridSize, int size) {
		 for (int x = 0; x <= size; x += gridSize)
			 for (int y = 0; y <= size; y += gridSize) 
				 g.drawRect(x, y, gridSize, gridSize);
	}
}