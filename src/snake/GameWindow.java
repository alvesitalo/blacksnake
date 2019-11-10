package snake;

import java.awt.Canvas;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;

import java.util.Random;

public class GameWindow extends Canvas implements KeyListener, ActionListener, Runnable {
	private Snake snake;
	private Enemy[] enemies = new Enemy[10];
	private int enemies_num = 0;
	private Fruit food;
	private Fruit food2;

	private ImageIcon scoreIcon;
	private ImageIcon hiScoreIcon;
	private ImageIcon gameArea;

	private int gridSize = 35;
	public static int width = 910;
	public static int height = 710;
	public static int[] boundary = new int[4];
	
	private Random random = new Random();
	private int delay = 180;
	private int score = 0;

	private boolean running;
	private boolean menu;
	private boolean gameWindow;
	private boolean gameOver;
	
	public GameWindow() {
		running = true;
		menu = true;
		gameWindow = false;
		gameOver = false;
		initItems();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		new Thread(this).start();
	}

	@Override
	public void run() {
		while(running) {
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

	public void update(Graphics g) {
		Graphics offgc;
		Image offscreen = null;

		offscreen = createImage(width, height);
		offgc = offscreen.getGraphics();

		offgc.setColor(getBackground());
		offgc.fillRect(0, 0, width, height);
		offgc.setColor(getForeground());

		paint(offgc);
		
		g.drawImage(offscreen, 0, 0, this);
	}
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		new gameGraphics(g2);

		if (menu) {
			snake.stopWalking();
			new Menu().show(this, g2);
		}

		if (gameWindow) {
			initGameGUI(g2);
			
			if (collisionSnakeFruit(food) || collisionSnakeFruit(food2)) {
				if (enemies_num < 10) {
					enemies[enemies_num] = new Enemy("spike");
					enemies_num++;
				}
			}

			for (int a = 0; a < enemies_num; a++) {
				if (enemies[a].getXPos() == snake.getXPos(0) && enemies[a].getYPos() == snake.getYPos(0)) {
					if (!snake.canHitEnemies()) {
						snake.die();
					}
				}

				enemies[a].showEnemy(this, g2);
			}
			
			for (int b = 1; b < snake.getLength(); b++) {
				if (snake.getXPos(b) == snake.getXPos(0) && snake.getYPos(b) == snake.getYPos(0)) {
					snake.die();
				}
			}

			if (!snake.isAlive()) {
				snake.stopWalking();
				gameWindow = false;
				gameOver = true;
			}
			
			if (food2 != null)
			food2.showFruit(this, g2);
			
			food.showFruit(this, g2);
			snake.updateSprites(this, g2);
		}

		if (gameOver) {
			menu = true;
			gameWindow = false;
			new Menu().show(this, g2);
		}

		g2.dispose();
	}

	private void initGameGUI(Graphics2D g2) {	
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
	
	private boolean collisionSnakeFruit(Fruit fruit) {
		if (fruit != null) {
			if (fruit.getXPos() == snake.getXPos(0) && fruit.getYPos() == snake.getYPos(0)) {
				if (fruit.getName() == "bomb") {
					snake.die();
				}
				else if (fruit.getName() == "big") {
					snake = new kittySnake(snake);
				}
				else if (fruit.getName() == "decrease") {
					snake.resetLength();
					snake = new starSnake(snake);
				}
				
				if (snake.canGainDoublePoints()) {
					score += fruit.getPoints() * 2;
				}
				else {
					score += fruit.getPoints();
				}
				
				randFruits();
				snake.increaseLength();
				
				if (score >= 15) {
					if (score % 3 == 0 && delay > 80) {
						delay -= 10;
					}
				}
				
				return true;
			}
		}

		return false;
	}

	private void randFruits() {
		boolean decrease = random.nextInt(10) == 0;
		boolean big = random.nextInt(4) == 0;
		boolean bomb = random.nextInt(4) == 0;
		
		if (decrease) {
			food = new decreaseFruit();
		}
		else if (bomb) {
			food = new bombFruit();
		}
		else if (big) {
			food = new bigFruit();
		}
		else {
			food = new Fruit();
		}
		
		if (food.getName() == "bomb" || food.getName() == "decrease") {
			food2 = new Fruit();
		}
		else {
			food.randCoords();
			food2 = null;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		snake.updatePosition();
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER && menu) {
			menu = false;
			gameWindow = true;
			gameOver = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_SPACE && !snake.isAlive()) {
			snake = new Snake();
			score = 0;
			repaint();
		}

		snake.updateWalking(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}
}