package data;

import java.util.Scanner;

public class NextGame 
{
	Sequence currSequence = new Sequence();
	int turn = 1;
	
	public NextGame() {}
	
	public void runGame(HighScores scoreDriver)
	{
		printStartingText();
		currSequence = new Sequence();
				
		printCurrentTurn();
		Scanner scnr = new Scanner(System.in);
		String[] userGuess = scnr.nextLine().split(" ");
		int correctCount = currSequence.checkSequence(userGuess);

		if (correctCount == -1)
			turn--;
		
		if (correctCount > -1)
			System.out.printf("You have %d numbers correct\n", correctCount);
		
		while (correctCount != -2 && correctCount < 5)
		{
			turn++;
			printCurrentTurn();
			userGuess = scnr.nextLine().split(" ");
			correctCount = currSequence.checkSequence(userGuess);
			
			if (correctCount == -1)
				turn--;
			
			if (correctCount > -1)
				System.out.printf("You have %d numbers correct\n", correctCount);
		}
		
		if (correctCount == 5)
		{			
			System.out.printf("\nYou guessed the sequence in %d turns\n\n", turn);
			
			currSequence.printSequence();
			
			System.out.printf("\nPlease enter your name to save your score: ");
			String name = scnr.nextLine();
			scnr.close();
			
			scoreDriver.newScore(turn, name);
		}
		
	}
	
	
	public void printCurrentTurn()
	{
		System.out.printf("== Turn %d == Number Sequence: ", turn);
	}
	
	public void printCurrentSequence()
	{
		currSequence.printSequence();
	}
	
	public void printStartingText()
	{
		System.out.printf("Game: Who's Next\nObjective: Identify the Sequence of 5 numbers between 1 and 5 using the fewest turns. If you wish\n"
				+ "to quit guessing and give up, enter a ZERO for one of your guesses and the game will display the\n"
				+ "solution and quit.\n GOOD LUCK!!!\n\n");
	}
}
