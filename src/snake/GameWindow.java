package snake;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Toolkit;

import java.util.Random;

public class GameWindow extends JPanel implements KeyListener, ActionListener, Runnable {
	private Snake snake;
	private Enemy[] enemies;
	private int enemies_num;
	private Fruit food;
	private Fruit food2;

	private ImageIcon scoreIcon;
	private ImageIcon hiscoreIcon;
	private ImageIcon gameArea;

	private int gridSize = 35;
	public static int width = 925; // 910
	public static int height = 730; // 700
	public static int[] boundary = new int[4];
	
	private gameFile file;
	private String playerName;
	private int delay;
	private int score;
	private int highscore;

	private boolean running;
	private State screen;
	private boolean openPopup;
	private Random random = new Random();

	private gameSound screenChanged;
	private gameSound hitFruitSound;
	private gameSound snakeChangedSound;
	private gameSound gameOverSound;

	public GameWindow() {
		screen = State.Menu;
		initItems();
		initWorld();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		setDoubleBuffered(true);
		new Thread(this).start();
	}

	@Override
	public void run() {
		while (running) {
			try {
				Thread.sleep(delay);
				snake.updatePosition();
				collisionWithFruitsEvents();
				repaint();
			} catch (InterruptedException e) {
				e.printStackTrace();
				return;
			}
		}
	}

	private enum State {
		Menu,
		Highscore,
		Game,
		GameOver;
	}
	
	private void initItems() {
		new gameFont("assets/fonts/mops.ttf");
		new gameFont("assets/fonts/bradley-gratis.ttf");

		screenChanged = new gameSound("assets/sounds/screen_changed.wav");
		hitFruitSound = new gameSound("assets/sounds/fruit.wav");
		snakeChangedSound = new gameSound("assets/sounds/new_snake.wav");
		gameOverSound = new gameSound("assets/sounds/game_over.wav");

		scoreIcon = new ImageIcon("assets/game/score-icon.png");
		hiscoreIcon = new ImageIcon("assets/game/highscore-icon.png");
		gameArea = new ImageIcon("assets/game/game-frame.png");

		running = true;
		boundary[0] = 70;
		boundary[1] = boundary[0] + (gameArea.getIconWidth() - 10) - gridSize;
		boundary[2] = 140;
		boundary[3] = boundary[2] + (gameArea.getIconHeight() - 10) - gridSize;
	}
	
	private void initWorld() {
		file = new gameFile();
		snake = new Snake();
		enemies = new Enemy[10];
		enemies_num = 0;
		food = new Fruit();
		food2 = null;
		delay = 170;
		score = 0;
		highscore = file.getHighScore();
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		new gameGraphics(g2);

		if (screen == State.Menu) {
			new Menu().show(this, g2);
		}
		else if (screen == State.Highscore) {
			new MenuHighScores(file).show(this, g2);
		}
		else if (screen == State.Game) {
			initGameGUI(g2);

			for (int a = 0; a < enemies_num; a++) {
				enemies[a].showEnemy(this, g2);

				if (enemies[a].getXPos() == snake.getXPos(0) && enemies[a].getYPos() == snake.getYPos(0)) {
					if (!snake.canHitEnemies()) {
						snake.die();
					}
				}
			}
			
			for (int b = 2; b < snake.getLength(); b++) {
				if (snake.getXPos(b) == snake.getXPos(0) && snake.getYPos(b) == snake.getYPos(0)) {
					snake.die();
				}
			}

			if (!snake.isAlive()) {
				snake.stopWalking();
				screen = State.GameOver;

				try {
					gameOverSound.play();
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			if (food2 != null)
			food2.showFruit(this, g2);
			
			food.showFruit(this, g2);
			snake.updateSprites(this, g2);
		}

		if (screen == State.GameOver) {
			new GameOver(score, highscore).show(this, g2);
			
			if (!openPopup) {
				openPopup = true;
				
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						if (playerName == null)
						playerName = JOptionPane.showInputDialog(null, "Insert your name:", 
									 "Player", JOptionPane.QUESTION_MESSAGE);
						
						if (playerName != null)
						file.addPlayer(playerName, score);
					}
				});
			}
		}
		else {
			openPopup = false;
		}
		
		Toolkit.getDefaultToolkit().sync();
		g2.dispose();
	}

	private void initGameGUI(Graphics2D g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);
		
		g.setColor(Color.black);
		g.setFont(new Font("Mops", Font.PLAIN, 65));

		scoreIcon.paintIcon(this, g, gridSize * 2, 50);
		g.drawString(score + "", 125, 60 + gridSize);

		hiscoreIcon.paintIcon(this, g, 780, 50);
		g.drawString(highscore + "", 700, 60 + gridSize);

		g.setFont(new Font("Bradley Gratis", Font.PLAIN, 70));
		g.drawString(snake.getName(), 400, 50 + (gridSize + 10));

		gameArea.paintIcon(this, g, boundary[0] - 5, boundary[2] - 5);
	}

	private void collisionWithFruitsEvents() {
		if (snakeHitFruit(food) || snakeHitFruit(food2)) {
			if (score >= 10) {
				if (score % 10 == 8 && delay > 80) {
					delay -= 10;
				}
			}

			if (score >= 40 && enemies_num < 10) {
				if (score % 3 == 0) {
					enemies[enemies_num] = new Enemy("spike");
					enemies_num++;
				}
			}
		}
	}
	
	private boolean snakeHitFruit(Fruit fruit) {
		if (fruit != null) {
			if (fruit.getXPos() == snake.getXPos(0) && fruit.getYPos() == snake.getYPos(0)) {
				if (fruit.getName() == "bomb") {
					snake.die();
				}
				else if (fruit.getName() == "big" && snake.getName() != "kitty") {
					snake = new kittySnake(snake);
					snakeChangedSound.play();
				}
				else if (fruit.getName() == "decrease" && snake.getName() != "star") {
					snake = new starSnake(snake);
					snakeChangedSound.play();
				}
				else {
					hitFruitSound.play();
				}
				
				if (snake.canGainDoublePoints()) {
					score += fruit.getPoints() * 2;
				}
				else {
					score += fruit.getPoints();
				}
				
				if (fruit.getName() != "decrease") {
					snake.increaseLength();
				}
				else {
					snake.resetLength();
				}
				
				randFruits();
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
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyboard = e.getKeyCode();
		int enter = KeyEvent.VK_ENTER;
		int space = KeyEvent.VK_SPACE;

		if (screen == State.Menu && keyboard == enter) {
			screen = State.Game;
			screenChanged.play();
			initWorld();
		}
		else if (screen == State.Menu && keyboard == space) {
			screen = State.Highscore;
			screenChanged.play();
		}
		else if (screen == State.Highscore && keyboard == enter) {
			screen = State.Menu;
			screenChanged.play();
		}
		else if (screen == State.GameOver && keyboard == enter) {
			screen = State.Menu;
			screenChanged.play();
		}
		else if (screen == State.GameOver && keyboard == space) {
			screen = State.Game;
			screenChanged.play();
			initWorld();
		}

		snake.updateWalking(keyboard);
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}
}