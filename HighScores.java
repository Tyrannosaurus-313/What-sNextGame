package data;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class HighScores
{
	String scoreFile = "Highscores.txt";
	Score[] scores = new Score[5];
	
	public HighScores()
	{
		updateScores();
	}
	
	public void sortScores(Score[] scores)
	{
		Score temp;	
		for (int i = 0; i < scores.length - 1; i++)
		{
			for (int j = 0; j < scores.length - i - 1; j++)
			{
				if (scores[j] != null && scores[j + 1] != null && scores[j].score > scores[j + 1].score)
				{
					temp = scores[j];
					scores[j] = scores[j + 1];
					scores[j + 1] = temp;
				}
			}
		}
	}
	
	public void updateScores()
	{
		try
		{
			Scanner scnr = new Scanner(new FileReader(scoreFile));
			readInScores(scnr);
			scnr.close();
			sortScores(scores);
		}
		catch (FileNotFoundException ex)
		{
			ex.printStackTrace();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public void readInScores(Scanner scoreReader)
	{
		for (int i = 0; i < scores.length && scoreReader.hasNextLine(); i++)
		{
			String[] line = scoreReader.nextLine().split("-");
			scores[i] = new Score(Integer.parseInt(line[1]), line[0]);
		}
	}
	
	public void writeOutScores(Score[] scoreList)
	{
		try
		{
			PrintWriter writer = new PrintWriter(scoreFile);
			sortScores(scoreList);
			for (int i = 0; i < scores.length && scoreList[i] != null; i++)
			{
				writer.printf("%s-%d\n", scoreList[i].playerName, scoreList[i].score);
			}
			writer.close();
		}
		catch (FileNotFoundException ex)
		{
			ex.printStackTrace();
		}
	}
	
	public void printScores()
	{
		System.out.printf("\nHigh Scores \n--------------- \n");
		for (int i = 0; i < scores.length; i++)
		{
			if (scores[i] != null)
				System.out.printf("%2d - %s\n", scores[i].score, scores[i].playerName);
		}
	}
	
	public void newScore(int score, String playerName)
	{
		sortScores(scores);
		if (playerName != "" && scoreIsHighscore(score))
		{
			Score[] tempScores = new Score[scores.length + 1];
			for (int i = 0; i < tempScores.length; i++)
			{
				if (scores.length <= i || scores[i] == null)
				{
					tempScores[i] = new Score(score, playerName);
					break;					
				}
				else
				{
					tempScores[i] = scores[i];
				}
			}
			writeOutScores(tempScores);
		}
		updateScores();
	}
	
	public boolean scoreIsHighscore(int score)
	{		
		Score currentLastScore = scores[scores.length - 1];		
		if ((currentLastScore == null || score < currentLastScore.score) && score > 0)
		{
			return true;
		}
		return false;
	}
}