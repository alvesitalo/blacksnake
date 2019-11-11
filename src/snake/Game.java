package snake;

import javax.swing.JFrame;

public class Game {
	private JFrame window;
	private GameWindow gameloop;

	public Game() {
		window = new JFrame("Blacksnake");
		gameloop = new GameWindow();
	}

	public void run() {
		window.add(gameloop);
		window.setBounds(10, 10, GameWindow.width, GameWindow.height);
		window.setResizable(false);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new Game().run();
	}
}
