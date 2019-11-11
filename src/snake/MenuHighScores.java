package snake;

import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Font;

public class MenuHighScores {
	private String title;
	private String subtitle;
	private String column;
	private String column2;
	private ImageIcon snakeIcon;
	private String phrase;

	private Font font1;
	private Font font2;
	private Font font3;
	private Player[] players;
	private int players_num;
	private int width;
	private int height;

	public MenuHighScores(gameFile file) {
		players = file.getPlayers();
		players_num = file.getPlayersNum();
		title = "blacksnake";
		subtitle = "high scores";
		column = "player";
		column2 = "points";
		snakeIcon = new ImageIcon("assets/game/snake-icon.png");
		phrase = "enter to go back menu";
		width = GameWindow.width;
		height = GameWindow.height;
	}

	public void show(Component c, Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);

		g.setColor(Color.white);

		font1 = new Font("Bradley Gratis", Font.PLAIN, 100);
		int a = new gameFont("assets/fonts/bradley-gratis.ttf").getMetrics(g, font1);
		g.setFont(font1);
		g.drawString(title, 280, 75 + (a - 5));

		font2 = new Font("Mops", Font.PLAIN, 36);
		int b = new gameFont("assets/fonts/Mops.ttf").getMetrics(g, font2);
		g.setFont(font2);
		g.drawString(subtitle, 385, 165 + (b - 5));

		font3 = new Font("Mops", Font.PLAIN, 50);
		int d = new gameFont("assets/fonts/Mops.ttf").getMetrics(g, font3);
		g.setFont(font3);
		g.drawString(column, 170, 255 + (d - 15));
		g.drawString(column2, 630, 255 + (d - 15));

		snakeIcon.paintIcon(c, g, 355, 340);
		
		g.setFont(font2);
		int line = 0;
		
		for(int i = 0; i < players_num; i++) {
			g.drawString(players[i].getName(), 170, (325 + 20) + (26 * line) + (15 * line));
			g.drawString("" + players[i].getScore(), 630, (325 + 20) + (26 * line) + (15 * line));
			line++;
		}
		
		g.setFont(font2);
		g.drawString(phrase, 320, 625 + (b - 5));
	}
}