package snake;

import java.awt.Color;
import javax.swing.JFrame;

public class Game {

	public static void main(String[] args) {
		JFrame obj = new JFrame();
		
		GameWindow gameplay = new GameWindow();
		obj.getContentPane().add(gameplay);
		
		obj.setBounds(10, 10, 905, 700);
		obj.setBackground(Color.white);
		obj.setResizable(false);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
