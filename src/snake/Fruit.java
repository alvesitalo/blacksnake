package snake;

import javax.swing.ImageIcon;

import java.awt.Component;
import java.awt.Graphics;

import java.util.Random;

public class Fruit {
	protected ImageIcon icon;
	protected String name;
	private int size = 35;
	protected int points;
	private Random random = new Random();
	private int[] coordsX = new int[22];
	private int[] coordsY = new int[14];
	private int posX;
	private int posY;
	
	public Fruit() {
		name = "simple";
		setIcon();
		points = 2;
		initCoords();
		randCoords();
	}

	public String getName() {
		return name;
	}

	public int getPoints() {
		return points;
	}
	
	public int getXPos() {
		return posX;
	}

	public int getYPos() {
		return posY;
	}

	public void setIcon() {
		icon = new ImageIcon("assets/fruits/" + name + "-fruit.png");
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public void initCoords() {
		int left = 2;

		for(int i = 0; i < 22; i++) {
			coordsX[i] = size * left;
			left++;
		}

		int top = 4;

		for(int i = 0; i < 14; i++) {
			coordsY[i] = size * top;
			top++;
		}
	}
	
	public void randCoords() {
		int x = random.nextInt(22);
		int y = random.nextInt(14);

		posX = coordsX[x];
		posY = coordsY[y];
	}

	public void setXPos(int x) {
		posX = x;
	}

	public void setYPos(int y) {
		posY = y;
	}

	public void showFruit(Component c, Graphics g) {
		icon.paintIcon(c, g, posX, posY);
	}
}
