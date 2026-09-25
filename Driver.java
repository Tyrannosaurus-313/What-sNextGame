package data;

public class Driver {
	public static void main(String[] args) {
		HighScores scoreDriver = new HighScores();
		NextGame game = new NextGame();
		game.runGame(scoreDriver);
		scoreDriver.printScores();	
	}
}
