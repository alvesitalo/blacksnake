package snake;

import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Font;

public class MenuHighScores {
	private String title;
	private String subtitle;
	private String column;
	private String column2;
	private ImageIcon snakeIcon;
	private String phrase;

	private Player[] players;
	private int players_num;

	private gameFont font1;
	private gameFont font2;
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
		initItems();
	}

	private void initItems() {
		font1 = new gameFont("assets/fonts/bradley-gratis.ttf");
		font2 = new gameFont("assets/fonts/mops.ttf");
	}

	public void show(Component c, Graphics2D g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);

		g.setColor(Color.white);

		Font bradley = new Font("Bradley Gratis", Font.PLAIN, 100);
		int a = font1.getMetrics(g, bradley);
		g.setFont(bradley);
		g.drawString(title, 280, 75 + (a - 5));

		Font mops = new Font("Mops", Font.PLAIN, 36);
		int b = font2.getMetrics(g, mops);
		g.setFont(mops);
		g.drawString(subtitle, 385, 165 + (b - 5));

		Font mops2 = new Font("Mops", Font.PLAIN, 50);
		int d = font2.getMetrics(g, mops2);
		g.setFont(mops2);
		g.drawString(column, 170, 255 + (d - 15));
		g.drawString(column2, 630, 255 + (d - 15));

		snakeIcon.paintIcon(c, g, 355, 340);
		
		g.setFont(mops);

		for(int n = 0; n < players_num; n++) {
			g.drawString(players[n].getName(), 170, (325 + 20) + (26 * n) + (15 * n));
			g.drawString(players[n].getScore() + "", 630, (325 + 20) + (26 * n) + (15 * n));
		}
		
		g.setFont(mops);
		g.drawString(phrase, 320, 625 + (b - 5));
	}
}