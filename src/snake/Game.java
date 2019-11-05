package snake;

import javax.swing.JFrame;

public class Game {
	JFrame window = new JFrame("Blacksnake");

	public void run () {
		window.add(new GameWindow());
		window.setBounds(10, 10, 925, 735);
		window.setResizable(false);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new Game().run();
	}
}
