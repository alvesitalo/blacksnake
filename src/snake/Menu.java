package snake;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Font;

public class Menu extends JPanel implements KeyListener {
	private String subtitle;
	private String title;
	private ImageIcon logo;
	private String phrase;
	private String phrase2;
	private int width;
	private int height;
	private Font font1;
	private Font font2;

	public Menu() {
		subtitle = "the";
		title = "blacksnake";
		logo = new ImageIcon("assets/game/snake-logo.png");
		phrase = "enter to start";
		phrase2 = "space to see highscore";
		width = GameWindow.width;
		height = GameWindow.height;
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		// set graphics quality
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

		g2.setColor(Color.black);
		g2.fillRect(0, 0, width, height);

		g2.setColor(Color.white);

		font1 = new Font("Bradley Gratis", Font.PLAIN, 183);
		int d = new gameFont("assets/fonts/bradley-gratis.ttf").getMetrics(g2, font1);
		g2.setFont(font1);
		g2.drawString(title, 140, 105 + (d - 12));
		logo.paintIcon(this, g2, 180, 305);//310

		font2 = new Font("Mops", Font.PLAIN, 60);
		int e = new gameFont("assets/fonts/Mops.ttf").getMetrics(g2, font2);
		g2.setFont(font2);
		g2.drawString(subtitle, 415, 30 + (e - 12));

		g2.setFont(new Font("Mops", Font.PLAIN, 48));
		g2.drawString(phrase, 335, 575 + (e - 12)); //575
		g2.drawString(phrase2, 260, 610 + (e - 12));

		//printGrid(g2);

		g2.dispose();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	private void printGrid(Graphics g) {
		g.setColor(Color.white);
		 for (int x = 0; x <= width; x += 35)
			 for (int y = 0; y <= height; y += 35) 
				 g.drawRect(x, y, 35, 35);
	}
}