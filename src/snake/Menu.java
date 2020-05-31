package snake;

import javax.swing.ImageIcon;

import java.awt.Component;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Font;

public class Menu {
	private String subtitle;
	private String title;
	private ImageIcon logo;
	private String phrase;
	private String phrase2;
	
	private gameFont font1;
	private gameFont font2;
	private int width;
	private int height;

	public Menu() {
		subtitle = "the";
		title = "blacksnake";
		logo = new ImageIcon("assets/game/snake-logo.png");
		phrase = "enter to start";
		phrase2 = "space to see highscore";
		width = GameWindow.width;
		height = GameWindow.height;
		initItems();
	}

	private void initItems() {
		font1 = new gameFont("assets/fonts/bradley-gratis.ttf");
		font2 = new gameFont("assets/fonts/Mops.ttf");
	}

	public void show(Component c, Graphics2D g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);

		g.setColor(Color.white);

		Font bradley = new Font("Bradley Gratis", Font.PLAIN, 183);
		int a = font1.getMetrics(g, bradley);
		g.setFont(bradley);
		g.drawString(title, 140, 105 + (a - 12));
		logo.paintIcon(c, g, 180, 300);

		Font mops = new Font("Mops", Font.PLAIN, 60);
		int b = font2.getMetrics(g, mops);
		g.setFont(mops);
		g.drawString(subtitle, 410, 30 + (b - 12));

		g.setFont(new Font("Mops", Font.PLAIN, 48));
		g.drawString(phrase, 335, 570 + (b - 12));
		g.drawString(phrase2, 260, 610 + (b - 12));
	}
}