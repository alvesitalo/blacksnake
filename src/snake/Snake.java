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
	private int[] posX = new int[308];
	private int[] posY = new int[308];
	
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;
	
	public Snake() {
		name = "black";
		powerHitWalls = false;
		powerGainDoublePoints = false;
		powerCrossBoundaries = false;
		alive = true;
		resetLength();
		initPosition();
	}
	
	public boolean canHitWalls() {
		return powerHitWalls;
	}
	
	public boolean canGainDoublePoints() {
		return powerGainDoublePoints;
	}
	
	public boolean canCrossBoundaries() {
		return powerCrossBoundaries;
	}

	public int getXPos(int key) {
		return posX[key];
	}
	
	public int getYPos(int key) {
		return posY[key];
	}
	
	public int[] getXCoords() {
		return posX;
	}
	
	public int[] getYCoords() {
		return posY;
	}
	
	public void setXCoords(int[] posX) {
		this.posX = posX;
	}
	
	public void setYCoords(int[] posY) {
		this.posY = posY;
	}

	public int getLength() {
		return this.length;
	}
	
	public void setLength(int length) {
		this.length = length;
	}
	
	public void resetLength() {
		this.length = 3;
	}
	
	public void increaseLength() {
		this.length++;
	}

	public void initPosition() {
		int pos = 2;
		for (int i = 0; i < this.length; i++) {
			posX[i] = bodySize * pos;
			posY[i] = bodySize * 4;
			pos *= 2;
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
				posY[i+1] = posY[i];
			}
			for (int i = this.length; i >= 0; i--) {
				if (i == 0) {
					posX[i] = posX[i] - bodySize;
				}
				else {
					posX[i] = posX[i-1];
				}
				
				if (posX[i] < GameWindow.boundary[0]) {
					if (powerCrossBoundaries) {
						posX[i] = GameWindow.boundary[1];
					}
					else {
						alive = false;
					}
				}
			}
		}
		else if (right) {
			for (int i = this.length; i >= 0; i--) {
				posY[i+1] = posY[i];
			}
			for (int i = this.length; i >= 0; i--) {
				if (i == 0) {
					posX[i] = posX[i] + bodySize;
				}
				else {
					posX[i] = posX[i-1];
				}
				
				if (posX[i] > GameWindow.boundary[1]) {
					if (powerCrossBoundaries) {
						posX[i] = GameWindow.boundary[0];
					}
					else {
						alive = false;
					}
				}
			}
		}
		else if (up) {
			for (int i = this.length; i >= 0; i--) {
				posX[i+1] = posX[i];
			}
			for (int i = this.length; i >= 0; i--) {
				if (i == 0) {
					posY[i] = posY[i] - bodySize;
				}
				else {
					posY[i] = posY[i-1];
				}
				
				if (posY[i] < GameWindow.boundary[2]) {
					if (powerCrossBoundaries) {
						posY[i] = GameWindow.boundary[3];
					}
					else {
						alive = false;
					}
				}
			}
		}
		else if (down) {
			for (int i = this.length; i >= 0; i--) {
				posX[i+1] = posX[i];
			}
			for (int i = this.length; i >= 0; i--) {
				if (i == 0) {
					posY[i] = posY[i] + bodySize;
				}
				else {
					posY[i] = posY[i-1];
				}
				
				if (posY[i] > GameWindow.boundary[3]) {
					if (powerCrossBoundaries) {
						posY[i] = GameWindow.boundary[2];
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
				faceLeft.paintIcon(c, g, posX[i], posY[i]);
			}
			else if (i == 0 && right) {
				faceRight = new ImageIcon("assets/snakes/" + name + "-face-right.png");
				faceRight.paintIcon(c, g, posX[i], posY[i]);
			}
			else if (i == 0 && up) {
				faceUp = new ImageIcon("assets/snakes/" + name + "-face-up.png");
				faceUp.paintIcon(c, g, posX[i], posY[i]);
			}
			else if (i == 0 && down) {
				faceDown = new ImageIcon("assets/snakes/" + name + "-face-down.png");
				faceDown.paintIcon(c, g, posX[i], posY[i]);
			}

			if (i != 0) {
				body = new ImageIcon("assets/snakes/" + name + "-body.png");
				body.paintIcon(c, g, posX[i], posY[i]);
			}
		}
	}
}
