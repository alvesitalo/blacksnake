package snake;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Arrays;
import java.util.Comparator;

public class gameFile {
	private Player[] players;
	private int players_num;
	private BufferedReader bufferedReader;
	private BufferedWriter bufferedWriter;
	private String inOutputFile = "gamefile.blacksnake";
	private int hiscore;

	public gameFile() {
		players = new Player[7];
		players_num = 0;
		hiscore = 0;
		
		try {
			readFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Player[] getPlayers() {
		return players;
	}
	
	public int getPlayersNum() {
		return players_num;
	}
	
	public int getHighScore() {
		return hiscore;
	}
	
	public void addPlayer(String name, int score) {
		name = stringNormalize(name);

		if (players_num == 7) {
			players_num--;

			if (score >= players[players_num].getScore()) {
				players[players_num] = null;
				players[players_num] = new Player(name, score);
			}
			
			players_num++;
		}
		else {
			players[players_num] = new Player(name, score);
			players_num++;
		}
		
		sortPlayers();
		writeFile();
	}

	private String stringNormalize(String name) {
		name = name.toLowerCase();

		if (name.length() > 14){
			name = name.substring(0, 14);
		}
		
		return name;
	}
	
	private void sortPlayers() {
		Arrays.sort(players, new Comparator<Player>() {
			@Override
			public int compare(Player player1, Player player2) {
				if (player1 != null && player2 != null) {
					return player2.getScore() - player1.getScore();
				}
				
				return 0;
			}
		});
	}

	public void readFile() throws IOException, FileNotFoundException {
		try {
			bufferedReader = null;
			bufferedReader = new BufferedReader(new FileReader(inOutputFile));
			
			String line = bufferedReader.readLine();
			String name = "";
			int score = 0;
			
			while (line != null) {
				name = stringNormalize(line);
				
				line = bufferedReader.readLine();

				if (line != null && line != "") {
					score = Integer.valueOf(line);
				}
				
				players[players_num] = new Player(name, score);
				players_num++;
				
				line = bufferedReader.readLine();
			}
			
			bufferedReader.close();
			sortPlayers();

			if (players_num > 0) {
				hiscore = Integer.valueOf(players[0].getScore());
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeFile() {
		try {
			bufferedWriter = null;
			bufferedWriter = new BufferedWriter(new FileWriter(inOutputFile));
			
			String score;
			
			for(int i = 0; i < players_num; i++) {
				bufferedWriter.write(players[i].getName());
				bufferedWriter.newLine();
				
				score = String.valueOf(players[i].getScore());
				bufferedWriter.write(score);
				bufferedWriter.newLine();
			}
			
			bufferedWriter.flush();
			bufferedWriter.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}