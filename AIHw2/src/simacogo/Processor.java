package simacogo;

import java.util.Scanner;

public class Processor {

	public static void main(String[] args) {
		/*This is the processor for the game. 
		 * It takes input from a human player and makes the appropriate calls to move the game along.
		 */
		
		Scanner input=new Scanner(System.in);
		System.out.println("Welcome to Simacogo! My name is Max.  Let me know what level you woul like me to play at...");
		System.out.println("(Please select type an integer between 1 and 5)");
		int level=input.nextInt();//Set the AI level
		MinMaxAI MAX=new MinMaxAI(level);
		Board game=new Board();//create the board
		game.toString();//print out the empty board.
		while(game.gameOver()==false){
			System.out.println("Your move");
			int slot=input.nextInt()-1;//get human input
			game.humanPlace(slot);
			game.toString();//print out board
			if(game.gameOver()==true)break;//check for end game
			System.out.println("Score O: "+game.getHumanScore()+"  X: "+game.getAIScore());//AI calculations
			System.out.println('\n');
			System.out.println("My turn now. Hmmmm, let me think...");
			game.AIPlace(MAX.minMaxCall(game));
			game.toString();
			System.out.println("Score O: "+game.getHumanScore()+"  X: "+game.getAIScore());
		}
		
		//End game states
		if(game.getHumanScore()>game.getAIScore()){
			System.out.println("OH NO! I LOST!");
			System.out.println("Final Score O: "+game.getHumanScore()+" X: "+game.getAIScore());
		}
		else{
			System.out.println("You Loose! Chalk another one up for the machines!");
			System.out.println("Final Score O: "+game.getHumanScore()+" X: "+game.getAIScore());
		}

	}

}
