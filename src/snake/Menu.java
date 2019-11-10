package snake;

import javax.swing.ImageIcon;

import java.awt.Component;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;

public class Menu {
	private String subtitle;
	private String title;
	private ImageIcon logo;
	private String phrase;
	private String phrase2;
	private int width;
	private int height;
	private Font font1;
	private Font font2;
	public boolean startGame;

	public Menu() {
		startGame = false;
		subtitle = "the";
		title = "blacksnake";
		logo = new ImageIcon("assets/game/snake-logo.png");
		phrase = "enter to start";
		phrase2 = "space to see highscore";
		width = GameWindow.width;
		height = GameWindow.height;
	}

	public void show(Component c, Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);

		g.setColor(Color.white);

		font1 = new Font("Bradley Gratis", Font.PLAIN, 183);
		int a = new gameFont("assets/fonts/bradley-gratis.ttf").getMetrics(g, font1);
		g.setFont(font1);
		g.drawString(title, 140, 105 + (a - 12));
		logo.paintIcon(c, g, 180, 305); //310

		font2 = new Font("Mops", Font.PLAIN, 60);
		int b = new gameFont("assets/fonts/Mops.ttf").getMetrics(g, font2);
		g.setFont(font2);
		g.drawString(subtitle, 415, 30 + (b - 12));

		g.setFont(new Font("Mops", Font.PLAIN, 48));
		g.drawString(phrase, 335, 575 + (b - 12)); //575
		g.drawString(phrase2, 260, 610 + (b - 12));

		g.dispose();
	}
}