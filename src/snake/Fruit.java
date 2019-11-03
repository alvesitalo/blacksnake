package snake;

import javax.swing.ImageIcon;

import java.awt.Component;
import java.awt.Graphics;

import java.util.Random;

public class Fruit {
	private ImageIcon image;
	private String type;
	private int points = 1;
	private int Xpos;
	private int Ypos;
	
	public Fruit(Component c, Graphics g, int x, int y) {
		Xpos = x;
		Ypos = y;
		image = new ImageIcon("src/assets/enemy.png");
		image.paintIcon(c, g, Xpos, Ypos);
	}
	
	private int [] enemyXpos = {75, 100, 125, 150, 175, 200, 225, 250, 300, 325, 350, 375, 400, 425, 450, 
			475, 500, 525, 550, 575, 600, 625, 650, 675, 700, 725, 750, 775, 800, 825, 850};
	private int [] enemyYpos = {75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 
				475, 500, 525, 550, 575, 600, 625};
	
	private Random random = new Random();
	private int xpos = random.nextInt(34);
	private int ypos = random.nextInt(23);
	
	if (enemyXpos[xpos] == snakeX[0] && enemyYpos[ypos] == snakeY[0]) {
		score++;
		lengthofsnake++;
		xpos = random.nextInt(34);
		ypos = random.nextInt(23);
	}
	
	
}
