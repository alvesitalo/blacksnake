package snake;

import javax.swing.ImageIcon;

import java.util.Random;

public class Fruit extends ImageIcon {
	private String name;
	private int size = 35;
	private int points;
	private Random random = new Random();
	private int[] coordsX = new int[22];
	private int[] coordsY = new int[14];
	private int posX;
	private int posY;
	
	public Fruit() {
		super("assets/fruits/basic-fruit.png");
		name = "basic";
		points = 1;
		initCoords();
		randCoords();
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
}
