package data;

import java.util.Random;

public class Sequence 
{
	public Random randy = new Random();
	public int[] sequence = new int[5];

	public Sequence()
	{
		createSequence();
	}
	public Sequence(int Seed)
	{
		randy.setSeed(Seed);
		createSequence();
	}
	
	public void createSequence()
	{
		int value = 1;
		int j;
		int offset;
		for (; value <= 5; value++)
		{
			offset = randy.nextInt(sequence.length);
			for (j = 0; j < 5; j++)
			{
				int loc = (j + offset) % 5;
				if (sequence[loc] == 0)
				{
					sequence[loc] = value;
					break;
				}
			}	
		}
	}
	
	public int checkSequence(String[] currGuess)
	{
		int correctCount = 0;
		for (int i = 0; i < sequence.length; i++)
		{
			if (currGuess.length == 0 || currGuess[i].length() == 0)
			{
				System.out.printf("Empty value, please try again\n");
				return -1;
			}
			
			char val = currGuess[i].charAt(0);
			
			if (val == '0')
			{
				return -2; // -2 is a forced exit value for the while loop running the game
			}
			else if (val < '1' || val > '9')
			{
				System.out.printf("Value not an Int, please try again\n");
				return -1;
			}
			else if (currGuess.length < sequence.length)
			{
				System.out.printf("Current Guess had fewer elements than the Sequence, please try again\n");
				return -1;
			}
			else
			{
				if (sequence[i] == Integer.parseInt(currGuess[i]))
					correctCount++;				
			}
		}
		return correctCount;
	}
	
	public void printSequence()
	{
		String fin = "| ";
		for (int i = 0; i < sequence.length; i++)
		{
			fin += sequence[i] + " | ";
		}
		System.out.printf("Game Number Sequence\n---------------------\n%s\n---------------------\n", fin);
	}

}
