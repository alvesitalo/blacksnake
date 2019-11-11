package snake;

import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Font;

public class GameOver {
	private String title;
	private String subtitle;
	private ImageIcon scoreIcon;
	private int score;
	private ImageIcon hiscoreIcon;
	private int hiscore;
	private String phrase;
	private String phrase2;
	
	private Font font1;
	private Font font2;
	private int width;
	private int height;

	public GameOver(int points, int highscore) {
		title = "game over";
		subtitle = "blacksnake is dead";
		scoreIcon = new ImageIcon("assets/game/score-icon-inverted.png");
		score = points;
		hiscoreIcon = new ImageIcon("assets/game/highscore-icon-inverted.png");
		hiscore = highscore;
		phrase = "enter to go to menu";
		phrase2 = "space to play again";
		width = GameWindow.width;
		height = GameWindow.height;
	}
	
	public void show(Component c, Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);

		g.setColor(Color.white);

		font1 = new Font("Bradley Gratis", Font.PLAIN, 150);
		int a = new gameFont("assets/fonts/bradley-gratis.ttf").getMetrics(g, font1);
		g.setFont(font1);
		g.drawString(title, 215, 120 + (a - 30));

		font2 = new Font("Mops", Font.PLAIN, 45);
		int b = new gameFont("assets/fonts/Mops.ttf").getMetrics(g, font2);
		g.setFont(font2);
		g.drawString(subtitle, 310, 230 + (b - 8));
		
		scoreIcon.paintIcon(c, g, 285, 355);
		hiscoreIcon.paintIcon(c, g, 490, 355);
		g.setFont(new Font("Mops", Font.PLAIN, 48));
		g.drawString("" + score, 355, 375 + (b - 12));
		g.drawString("" + hiscore, 565, 375 + (b - 12));
		
		g.setFont(font2);
		g.drawString(phrase, 300, 505 + (b - 8));
		g.drawString(phrase2, 305, 540 + (b - 8));
	}
}