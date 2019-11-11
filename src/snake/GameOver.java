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
        phrase = "insert your name:";
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
		g.drawString(title, 215, 135 + (a - 65));

		font2 = new Font("Mops", Font.PLAIN, 45);
		int b = new gameFont("assets/fonts/Mops.ttf").getMetrics(g, font2);
		g.setFont(font2);
        g.drawString(subtitle, 305, 210 + (b - 8));
        
        scoreIcon.paintIcon(c, g, 285, 350);
        hiscoreIcon.paintIcon(c, g, 490, 350);
		g.setFont(new Font("Mops", Font.PLAIN, 48));
		g.drawString("" + score, 355, 370 + (b - 12));
        g.drawString("" + hiscore, 565, 370 + (b - 12));
        
		g.setFont(font2);
        g.drawString(phrase, 320, 500 + (b - 8));
	}
}