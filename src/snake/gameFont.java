package snake;

import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.FontFormatException;

import java.io.File;
import java.io.IOException;

public class gameFont {
	public gameFont() {
	}

	public gameFont(String pathname) {
		try {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(pathname)));
		
		} catch (IOException|FontFormatException e) {
			e.printStackTrace();
		}
	}

	public int getMetrics(Graphics g, Font f) {
		FontMetrics metrics = g.getFontMetrics(f);
		return metrics.getAscent();
	}
}