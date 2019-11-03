package snake;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.Timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

public class GameWindow extends JPanel implements KeyListener, ActionListener {
	private ImageIcon titleImage;
	private int gridSize = 25;
	private Timer timer;
	private int delay = 120;
	
	private int[] snakeX = new int[750];
	private int[] snakeY = new int[750];
	
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;
	
	private ImageIcon rightmouth;
	private ImageIcon upmouth;
	private ImageIcon downmouth;
	private ImageIcon leftmouth;
	
	private int lengthofsnake = 3;
	private ImageIcon snakeimage;
	
	private int score = 0;
	private int moves = 0;
	
	public GameWindow() {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}
	
	public void paint(Graphics g) {
		if (moves == 0) {
			snakeX[2] = 50;
			snakeX[1] = 75;
			snakeX[0] = 100;
			
			snakeY[2] = 100;
			snakeY[1] = 100;
			snakeY[0] = 100;
		}
		
		// draw title image border
		g.setColor(Color.black);
		g.drawRect(24, 10, 851, 55);
		
		// draw the title image
		//titleImage = new ImageIcon("src/assets/snaketitle.jpg");
		//titleImage.paintIcon(this, g, 25, 11);
		
		// draw score
		g.setColor(Color.black);
		g.setFont(new Font("arial", Font.PLAIN, 14));
		g.drawString("Scores: " + score, 780, 30);
		
		// draw length
		g.setColor(Color.black);
		g.setFont(new Font("arial", Font.PLAIN, 14));
		g.drawString("Lenght: " + lengthofsnake, 780, 50);
		
		// draw length
		g.setColor(Color.black);
		g.setFont(new Font("arial", Font.PLAIN, 14));
		g.drawString("X: " + snakeX[0], 780, 70);
		
		// draw background for the gameplay
		g.setColor(Color.white);
		g.fillRect(25, 75, 850, 575);
		
		for (int b = 1; b < lengthofsnake; b++) {
			if(snakeX[b] == snakeX[0] && snakeY[b] == snakeY[0]) {
				right = false;
				left = false;
				up = false;
				down = false;
				
				g.setColor(Color.black);
				g.setFont(new Font("arial", Font.BOLD, 50));
				g.drawString("Game over", 300, 300);
				
				g.setFont(new Font("arial", Font.BOLD, 20));
				g.drawString("Space to restart", 350, 340);
			}
		}
		
		rightmouth = new ImageIcon("src/assets/rightmouth.png");
		rightmouth.paintIcon(this, g, snakeX[0], snakeY[0]);
		
		for (int a = 0; a < lengthofsnake; a++) {
			if (a == 0 && right) {
				rightmouth = new ImageIcon("src/assets/rigthmouth.png");
				rightmouth.paintIcon(this, g, snakeX[a], snakeY[a]);
			}
			if (a == 0 && left) {
				leftmouth = new ImageIcon("src/assets/leftmouth.png");
				leftmouth.paintIcon(this, g, snakeX[a], snakeY[a]);
			}
			if (a == 0 && down) {
				downmouth = new ImageIcon("src/assets/downmouth.png");
				downmouth.paintIcon(this, g, snakeX[a], snakeY[a]);
			}
			if (a == 0 && up) {
				upmouth = new ImageIcon("src/assets/upmouth.png");
				upmouth.paintIcon(this, g, snakeX[a], snakeY[a]);
			}
			if (a != 0) {
				snakeimage = new ImageIcon("src/assets/snakeimage.png");
				snakeimage.paintIcon(this, g, snakeX[a], snakeY[a]);
			}
		}
		
		// draw frame for the gameplay
		titleImage = new ImageIcon("src/assets/game-frame.png");
		titleImage.paintIcon(this, g, 45, 145);
		
		printGrid(g);
		
		g.dispose();
	}
	
	public int getCoord(int coord) {
		return coord * gridSize;
	}
	
	public void printGrid(Graphics g) {
		g.setColor(Color.black);
		 for (int x = gridSize; x <= 300; x += gridSize)
			 for (int y = 75; y <= 300; y += gridSize) 
				 g.drawRect(x, y, gridSize, gridSize);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (right) {
			for (int r = lengthofsnake; r >= 0; r--) {
				snakeY[r+1] = snakeY[r];
			}
			for (int r = lengthofsnake; r >= 0; r--) {
				if (r == 0) {
					snakeX[r] = snakeX[r] + gridSize;
				}
				else {
					snakeX[r] = snakeX[r-1];
				}
				if (snakeX[r] > 850) {
					snakeX[r] = gridSize;
				}
			}
		}
		
		if (left) {
			for (int r = lengthofsnake; r >= 0; r--) {
				snakeY[r+1] = snakeY[r];
			}
			for (int r = lengthofsnake; r >= 0; r--) {
				if (r == 0) {
					snakeX[r] = snakeX[r] - gridSize;
				}
				else {
					snakeX[r] = snakeX[r-1];
				}
				if (snakeX[r] < gridSize) {
					snakeX[r] = 850;
				}
			}
		}
		
		if (down) {
			for (int r = lengthofsnake; r >= 0; r--) {
				snakeX[r+1] = snakeX[r];
			}
			for (int r = lengthofsnake; r >= 0; r--) {
				if (r == 0) {
					snakeY[r] = snakeY[r] + gridSize;
				}
				else {
					snakeY[r] = snakeY[r-1];
				}
				if (snakeY[r] > 625) {
					snakeY[r] = 75;
				}
			}
		}
		
		if (up) {
			for (int r = lengthofsnake; r >= 0; r--) {
				snakeX[r+1] = snakeX[r];
			}
			for (int r = lengthofsnake; r >= 0; r--) {
				if (r == 0) {
					snakeY[r] = snakeY[r] - gridSize;
				}
				else {
					snakeY[r] = snakeY[r-1];
				}
				if (snakeY[r] < 75) {
					snakeY[r] = 625;
				}
			}
		}
		
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			moves = 0;
			score = 0;
			lengthofsnake = 3;
			repaint();
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			moves++;
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
		
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			moves++;
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
		
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			moves++;
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
		
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			moves++;
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

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
