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
    private String inputFile = "assets/game/savefile.blacksnake";
    private String outputFile = "assets/game/savefile.blacksnake";
    private int hiscore;

    gameFile() {
    	players = new Player[7];
    	players_num = 0;
    	
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
    	writeFile();
	}
	
    public void setPlayers(Player[] players) {
		this.players = players;
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
            bufferedReader = new BufferedReader(new FileReader(inputFile));
            String line = bufferedReader.readLine();
            
            String name;
            int score = 0;

            while (line != null) {
				name = line;
				
                line = bufferedReader.readLine();
                score = Integer.valueOf(line);
                
                players[players_num] = new Player(name, score);
                players_num++;
                
                line = bufferedReader.readLine();
			}

			bufferedReader.close();
			sortPlayers();
            hiscore = Integer.valueOf(players[0].getScore());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeFile() {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(inputFile));
            
            String fileContent = "This is a sample text.";
            
            bufferedWriter.write(fileContent);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


