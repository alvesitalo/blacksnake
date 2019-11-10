package snake;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import java.awt.Dimension;

public class Game {
	private JFrame window;
	private GameWindow gameloop;

	public Game() {
		window = new JFrame("Blacksnake");
		gameloop = new GameWindow();
	}

	public void run() {
		window.add(gameloop);
		window.setBounds(10, 10, GameWindow.width + 15, GameWindow.height + 20);
		window.setResizable(false);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new Game().run();
	}
}
