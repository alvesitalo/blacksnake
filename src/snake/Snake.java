package snake;

import javax.swing.ImageIcon;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Snake {
	private ImageIcon head;
	private ImageIcon body;

	protected String name;
	private int bodySize = 35;
	protected int length;
	protected State direction;
	
	protected boolean powerHitEnemies;
	protected boolean powerGainDoublePoints;
	protected boolean powerCrossBoundaries;
	protected boolean alive;
	private int[] coordsX = new int[308];
	private int[] coordsY = new int[308];
	
	public Snake() {
		this.name = "black";
		resetLength();
		this.powerHitEnemies = false;
		this.powerGainDoublePoints = false;
		this.powerCrossBoundaries = false;
		this.alive = true;
		initPosition();
	}
	
	private enum State {
		Left,
		Right,
		Up,
		Down,
		None;
	}

	public String getName() {
		return this.name;
	}

	public int getLength() {
		return this.length;
	}

	public boolean canHitEnemies() {
		return this.powerHitEnemies;
	}
	
	public boolean canGainDoublePoints() {
		return this.powerGainDoublePoints;
	}
	
	public boolean canCrossBoundaries() {
		return this.powerCrossBoundaries;
	}

	public boolean isAlive() {
		return this.alive;
	}
	
	public int[] getXCoords() {
		return coordsX;
	}
	
	public int[] getYCoords() {
		return coordsY;
	}
	
	public int getXPos(int key) {
		return coordsX[key];
	}
	
	public int getYPos(int key) {
		return coordsY[key];
	}

	public State getDirection() {
		return this.direction;
	}

	public void resetLength() {
		this.length = 3;
	}
	
	public void increaseLength() {
		this.length++;
	}

	public void die() {
		this.alive = false;
	}

	public void setXCoords(int[] coordsX) {
		this.coordsX = coordsX;
	}
	
	public void setYCoords(int[] coordsY) {
		this.coordsY = coordsY;
	}

	public void initPosition() {
		int pos = 2;
		for (int i = this.length - 1; i >= 0; i--) {
			coordsX[i] = bodySize * pos;
			coordsY[i] = bodySize * 4;
			pos++;
		}

		this.direction = State.Right;
	}

	public void stopWalking() {
		this.direction = State.None;
	}

	public void updateWalking(int keycode) {
		if (keycode == KeyEvent.VK_LEFT) {
			if (direction != State.Right && getYPos(0) != getYPos(1)) {
				this.direction = State.Left;
			}
		}
		else if (keycode == KeyEvent.VK_RIGHT) {
			if (direction != State.Left && getYPos(0) != getYPos(1)) {
				this.direction = State.Right;
			}
		}
		else if (keycode == KeyEvent.VK_UP) {
			if (direction != State.Down && getXPos(0) != getXPos(1)) {
				this.direction = State.Up;
			}
		}
		else if (keycode == KeyEvent.VK_DOWN) {
			if (direction != State.Up && getXPos(0) != getXPos(1)) {
				this.direction = State.Down;
			}
		}
	}

	public void updatePosition() {
		if (direction == State.Left) {
			for (int i = this.length; i >= 0; i--) {
				coordsY[i+1] = coordsY[i];
			}

			for (int i = this.length; i >= 0; i--) {
				if (i == 0) {
					coordsX[i] = coordsX[i] - bodySize;
				}
				else {
					coordsX[i] = coordsX[i-1];
				}
				
				if (coordsX[i] < GameWindow.boundary[0]) {
					if (!powerCrossBoundaries) {
						alive = false;
					}
					coordsX[i] = GameWindow.boundary[1];
				}
			}
		}
		else if (direction == State.Right) {
			for (int i = this.length; i >= 0; i--) {
				coordsY[i+1] = coordsY[i];
			}

			for (int i = this.length; i >= 0; i--) {
				if (i == 0) {
					coordsX[i] = coordsX[i] + bodySize;
				}
				else {
					coordsX[i] = coordsX[i-1];
				}
				
				if (coordsX[i] > GameWindow.boundary[1]) {
					if (!powerCrossBoundaries) {
						alive = false;
					}
					coordsX[i] = GameWindow.boundary[0];
				}
			}
		}
		else if (direction == State.Up) {
			for (int i = this.length; i >= 0; i--) {
				coordsX[i+1] = coordsX[i];
			}

			for (int i = this.length; i >= 0; i--) {
				if (i == 0) {
					coordsY[i] = coordsY[i] - bodySize;
				}
				else {
					coordsY[i] = coordsY[i-1];
				}
				
				if (coordsY[i] < GameWindow.boundary[2]) {
					if (!powerCrossBoundaries) {
						alive = false;
					}
					coordsY[i] = GameWindow.boundary[3];
				}
			}
		}
		else if (direction == State.Down) {
			for (int i = this.length; i >= 0; i--) {
				coordsX[i+1] = coordsX[i];
			}

			for (int i = this.length; i >= 0; i--) {
				if (i == 0) {
					coordsY[i] = coordsY[i] + bodySize;
				}
				else {
					coordsY[i] = coordsY[i-1];
				}
				
				if (coordsY[i] > GameWindow.boundary[3]) {
					if (!powerCrossBoundaries) {
						alive = false;
					}
					coordsY[i] = GameWindow.boundary[2];
				}
			}
		}
	}

	public void updateSprites(Component c, Graphics g) {
		if (direction == State.Left) {
			head = new ImageIcon("assets/snakes/" + name + "-head-left.png");
		}
		else if (direction == State.Right) {
			head = new ImageIcon("assets/snakes/" + name + "-head-right.png");
		}
		else if (direction == State.Up) {
			head = new ImageIcon("assets/snakes/" + name + "-head-up.png");
		}
		else if (direction == State.Down) {
			head = new ImageIcon("assets/snakes/" + name + "-head-down.png");
		}
		head.paintIcon(c, g, coordsX[0], coordsY[0]);

		body = new ImageIcon("assets/snakes/" + name + "-body.png");
		for (int i = 1; i < this.length; i++) {
			body.paintIcon(c, g, coordsX[i], coordsY[i]);
		}
	}
}
