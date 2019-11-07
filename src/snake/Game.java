package snake;

import javax.swing.JFrame;

public class Game {
	JFrame window = new JFrame("Blacksnake");
	GameWindow gameloop = new GameWindow();

	public void run () {
		window.add(gameloop);
		window.setBounds(10, 10, 925, 730);
		window.setResizable(false);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new Game().run();
	}
}
