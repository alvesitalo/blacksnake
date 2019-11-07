package snake;

import javax.swing.ImageIcon;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Snake {
	private ImageIcon faceLeft;
	private ImageIcon faceRight;
	private ImageIcon faceUp;
	private ImageIcon faceDown;
	private ImageIcon body;

	protected String name;
	private int bodySize = 35;
	protected int length;
	protected boolean powerHitWalls;
	protected boolean powerGainDoublePoints;
	protected boolean powerCrossBoundaries;
	protected boolean alive;
	private int[] coordsX = new int[308];
	private int[] coordsY = new int[308];
	
	protected boolean left = false;
	protected boolean right = false;
	protected boolean up = false;
	protected boolean down = false;
	
	public Snake() {
		name = "black";
		resetLength();
		powerHitWalls = false;
		powerGainDoublePoints = false;
		powerCrossBoundaries = false;
		alive = true;
		initPosition();
	}

	public String getName() {
		return name;
	}

	public int getLength() {
		return this.length;
	}

	public boolean canHitWalls() {
		return this.powerHitWalls;
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

	public boolean isLeft() {
		return this.left;
	}

	public boolean isRight() {
		return this.right;
	}

	public boolean isUp() {
		return this.up;
	}

	public boolean isDown() {
		return this.down;
	}

	public void resetLength() {
		this.length = 3;
	}
	
	public void increaseLength() {
		this.length++;
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

		right = true;
	}

	public void stopWalking() {
		this.left = false;
		this.right = false;
		this.up = false;
		this.down = false;
	}

	public void updateWalking(int keycode) {
		if (keycode == KeyEvent.VK_LEFT) {
			left = true;
			
			if (!right) {
				left = true;
			}
			else {
				left = false;
				right = true;
			}
			
			up = false;
			down = false;
		}

		if (keycode == KeyEvent.VK_RIGHT) {
			right = true;
			
			if (!left) {
				right = true;
			}
			else {
				right = false;
				left = true;
			}
			
			up = false;
			down = false;
		}
		
		if (keycode == KeyEvent.VK_UP) {
			up = true;
			
			if (!down) {
				up = true;
			}
			else {
				up = false;
				down = true;
			}
			
			left = false;
			right = false;
		}
		
		if (keycode == KeyEvent.VK_DOWN) {
			down = true;
			
			if (!up) {
				down = true;
			}
			else {
				up = true;
				down = false;
			}
			
			left = false;
			right = false;
		}
	}

	public void updatePosition() {
		if (left) {
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
					if (powerCrossBoundaries) {
						coordsX[i] = GameWindow.boundary[1];
					}
					else {
						alive = false;
					}
				}
			}
		}
		else if (right) {
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
					if (powerCrossBoundaries) {
						coordsX[i] = GameWindow.boundary[0];
					}
					else {
						alive = false;
					}
				}
			}
		}
		else if (up) {
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
					if (powerCrossBoundaries) {
						coordsY[i] = GameWindow.boundary[3];
					}
					else {
						alive = false;
					}
				}
			}
		}
		else if (down) {
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
					if (powerCrossBoundaries) {
						coordsY[i] = GameWindow.boundary[2];
					}
					else {
						alive = false;
					}
				}
			}
		}
	}

	public void updateSprites(Component c, Graphics g) {
		for (int i = 0; i < this.length; i++) {
			if (i == 0 && left) {
				faceLeft = new ImageIcon("assets/snakes/" + name + "-face-left.png");
				faceLeft.paintIcon(c, g, coordsX[i], coordsY[i]);
			}
			else if (i == 0 && right) {
				faceRight = new ImageIcon("assets/snakes/" + name + "-face-right.png");
				faceRight.paintIcon(c, g, coordsX[i], coordsY[i]);
			}
			else if (i == 0 && up) {
				faceUp = new ImageIcon("assets/snakes/" + name + "-face-up.png");
				faceUp.paintIcon(c, g, coordsX[i], coordsY[i]);
			}
			else if (i == 0 && down) {
				faceDown = new ImageIcon("assets/snakes/" + name + "-face-down.png");
				faceDown.paintIcon(c, g, coordsX[i], coordsY[i]);
			}

			if (i != 0) {
				body = new ImageIcon("assets/snakes/" + name + "-body.png");
				body.paintIcon(c, g, coordsX[i], coordsY[i]);
			}
		}
	}
}
