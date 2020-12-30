package snake;

import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
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
	
	private gameFont font1;
	private gameFont font2;
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

		Font bradley = new Font("Bradley Gratis", Font.PLAIN, 150);
		int a = font1.getMetrics(g, bradley);
		g.setFont(bradley);
		g.drawString(title, 215, 120 + (a - 30));

		Font mops = new Font("mops", Font.PLAIN, 45);
		int b = font2.getMetrics(g, mops);
		g.setFont(mops);
		g.drawString(subtitle, 310, 230 + (b - 8));
		
		scoreIcon.paintIcon(c, g, 285, 355);
		hiscoreIcon.paintIcon(c, g, 490, 355);
		g.setFont(new Font("mops", Font.PLAIN, 48));
		g.drawString("" + score, 355, 375 + (b - 12));
		g.drawString("" + hiscore, 565, 375 + (b - 12));
		
		g.setFont(mops);
		g.drawString(phrase, 300, 505 + (b - 8));
		g.drawString(phrase2, 305, 540 + (b - 8));
	}
}