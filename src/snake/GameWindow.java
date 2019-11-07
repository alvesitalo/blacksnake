package snake;

import javax.swing.JPanel;
import javax.swing.ImageIcon;

import java.awt.Canvas;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;

public class GameWindow extends JPanel implements KeyListener, ActionListener, Runnable {
	private Snake snake;
	private Fruit food;

	private ImageIcon scoreIcon;
	private ImageIcon hiScoreIcon;
	private ImageIcon gameArea;

	public int gridSize = 35;
	public static int width = 910;
	public static int height = 710;
	public static int[] boundary = new int[4];

	private int delay = 180;
	
	private int score = 0;
	
	public GameWindow() {
		initItems();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		new Thread(this).start();
	}

	@Override
        public void run() {
			while(true) {
			try {
				Thread.sleep(delay);
				snake.updatePosition();
				repaint();
			} catch (InterruptedException e) {
				e.printStackTrace();
				return;
			}
		}
    }
	
	private void initItems() {
		scoreIcon = new ImageIcon("assets/game/score-icon.png");
		hiScoreIcon = new ImageIcon("assets/game/highscore-icon.png");
		gameArea = new ImageIcon("assets/game/game-frame.png");
		boundary[0] = 70;
		boundary[1] = boundary[0] + (gameArea.getIconWidth() - 10) - gridSize;
		boundary[2] = 140;
		boundary[3] = boundary[2] + (gameArea.getIconHeight() - 10) - gridSize;
		snake = new Snake();
		food = new Fruit();
	}
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		initGameGUI(g2);

		if (food.getXPos() == snake.getXPos(0) && food.getYPos() == snake.getYPos(0)) {
			if (snake.canGainDoublePoints()) {
				score += food.getPoints() * 2;
			}
			else {
				score += food.getPoints();
			}
			
			food.randCoords();
			snake.increaseLength();
		}
		
		if (score == 5) {
			snake = new snakeKitty(snake);
		}
		
		for (int b = 1; b < snake.getLength(); b++) {
			if(snake.getXPos(b) == snake.getXPos(0) && snake.getYPos(b) == snake.getYPos(0) || !snake.isAlive()) {
				snake.stopWalking();
				
				g2.setColor(Color.black);
				g2.setFont(new Font("arial", Font.BOLD, 50));
				g2.drawString("Game over", 300, 300);
				
				g2.setFont(new Font("arial", Font.BOLD, 20));
				g2.drawString("Space to restart", 350, 340);
			}
		}
		
		food.paintIcon(this, g2, food.getXPos(), food.getYPos());
		snake.updateSprites(this, g2);

		//printGrid(g2);
		g2.dispose();
	}

	private void initGameGUI(Graphics2D g2) {
		// Set Graphics Quality
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		
		// Background
		g2.setColor(Color.white);
		g2.fillRect(0, 0, width, height);
		
		// Score and High Score
		new gameFont("assets/fonts/Mops.ttf");
		g2.setColor(Color.black);
		g2.setFont(new Font("Mops", Font.PLAIN, 65));

		scoreIcon.paintIcon(this, g2, gridSize * 2, 50);
		g2.drawString("" + score, 125, 60 + gridSize);

		hiScoreIcon.paintIcon(this, g2, 780, 50);
		g2.drawString("" + snake.getLength(), 705, 60 + gridSize);

		// Snake name
		new gameFont("assets/fonts/bradley-gratis.ttf");
		g2.setFont(new Font("Bradley Gratis", Font.PLAIN, 70));
		g2.drawString(snake.getName(), 400, 50 + (gridSize + 10));

		// Game area frame
		gameArea.paintIcon(this, g2, boundary[0] - 5, boundary[2] - 5);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		snake.updatePosition();
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			snake = new Snake();
			score = 0;
			repaint();
		}

		snake.updateWalking(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	private void printGrid(Graphics g) {
		g.setColor(Color.black);
		 for (int x = 0; x <= width; x += gridSize)
			 for (int y = 0; y <= height; y += gridSize) 
				 g.drawRect(x, y, gridSize, gridSize);
	}
}