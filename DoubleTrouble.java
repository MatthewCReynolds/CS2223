/*
 * @author Matthew Reynolds
 * @date 3 November 2022
 * @assignment HW1
 */


import java.util.Scanner;
import java.security.SecureRandom;

public class DoubleTrouble {
	
	//The name of the game is actually Nim, or jiǎn-shízi if you believe the chinese game which this
	//closely resembles is actually the game this originated from. It was named Nim by Charles L Bolton.
	
	static Scanner input = new Scanner(System.in);
	static SecureRandom rand = new SecureRandom();
	
	public static void main(String[] args) {
		startgame();
	}
	
	public static void startgame() {
		
		//Setting up new Game
		System.out.println("Welcome to Nim!");
		System.out.println("There are 3 piles you get to pick from, orange, yellow, and green.\nThere are"
				+ " three pieces in the green pile, seven pieces in the yellow pile, and five pieces"
				+ " in the orange pile. \nYou can only remove pieces from one colour at a time.\nYou can take"
				+ " as many pieces as you want from any one pile on your turn.\nThe player to pick the last"
				+ " piece wins.");
		System.out.println("Do you want to play a tournament? Y/N");
		String tournament = input.nextLine();
		int computerWins = 0, playerWins = 0;
		
		//Setting up tournament
		int rounds = 0;
		if (tournament.equals("Y")){
				System.out.println("How many round would you like to play? Must be an odd Number");
				while (rounds <=0) {
					rounds = input.nextInt();
					if ((rounds <= 0) || (rounds % 2 == 0)) {
						System.out.println("The number you gave was not a valid number. Try Again");
						rounds = 0;
					}
				}
		}
		else //If not a tournament, only play one game.
			rounds = 1;
		System.out.println("Do you want to play first? true or false");
		boolean playerTurn = input.nextBoolean();
		
		//Play Game
		for(int i = 0; i < rounds; i++) { // Might be an error
			int green = 3,  yellow = 7, orange = 5; // Setting up colour piles
			if (rounds > 1) // Output current game score between player & computer
				System.out.println("The current tournament score is: " + playerWins + "-" + computerWins);
			System.out.println("You have " + (rounds - (i+1)) + " games left after this."); // Might be an error
			while ((green + yellow + orange) > 0) {
				System.out.println("There are " + green + " green pieces left. "
									+ "There are " + yellow + " yellow pieces left. "
									+ "There are " + orange + " orange peices left."); // Telling player # remaining
				if (playerTurn) { // Player turn
					System.out.println("Pick a colour pile to choose from"); // Picking colour
					
					String colourPick;
                    do
                    {
                        colourPick = input.nextLine();
                        if (colourPick.isEmpty()) {
                            continue;
                        }
                        if (!(colourPick.equals("yellow") || colourPick.equals("orange") || colourPick.equals("green")))
                        {
                            System.out.println("Input is not an acceptable colour. Try again.");     
                        }
                        else {
                            break;
                        }
                    } while(true);
					
					int amount = 0;
					switch (colourPick) {
						case "green": amount = green; break;
						case "yellow": amount = yellow; break;
						case "orange": amount = orange;
					}
					System.out.println("Pick a amount you want to remove"); // Picking amount
					int numRemove = input.nextInt();
					while ((numRemove > amount) || (numRemove < 1)){
						System.out.println("Input is not an acceptable amount. Try again.");
						numRemove = input.nextInt();
					}
					switch (colourPick) { //Removing player's pick from piles.
						case "green":  green  -= numRemove; break;
						case "yellow": yellow -= numRemove; break;
						case "orange": orange -= numRemove;
					}
					System.out.println("You have removed: " + numRemove
									+ " from the " + colourPick + " pile"); //Telling Player their move.
					
					//Check if that was final piece
					if ((green + yellow + orange) == 0) {
						System.out.println("You Win.");
						playerWins++;
					}
					
					//Setting it to computer turn
					playerTurn = false;
					
					
				} else { // Computer turn
					boolean winnable = (orange ^ yellow ^ green) != 0; //Checks if its not a 0 position
					
					//Check if there's only 1 pile
					if ((orange + green) == 0) {
						System.out.println("Computer has taken the final " + yellow + " peices from yellow.");
						yellow -= yellow;
					} else if ((orange + yellow) == 0) {
						System.out.println("Computer has taken the final " + green + " peices from green.");
						green -= green;
					} else if ((green + yellow) == 0) {
						System.out.println("Computer has taken the final " + orange + " peices from orange.");
						orange -= orange;
					}
					
					//Otherwise, check if game is winnable
					else if (winnable) {
						int removeG = yellow^orange;
						int removeY = green^orange;
						int removeO = green^yellow;
						
						
						if (removeO <= orange) {
							orange -= removeO;
							System.out.println("Computer has removed: " + removeO + " from Orange.");
						} else if (removeY <= yellow) {
							yellow -= removeY;
							System.out.println("Computer has removed: " + removeY + " from Yellow.");
						} else if (removeG <= green) {
							green -= removeG;
							System.out.println("Computer has removed: " + removeG + " from Green.");
						}
						
					} else { // Losing position, so make random move
						String[] colours = {"yellow", "orange", "green"};
						int[] amounts = {yellow, orange, green};
						int colourChoice = rand.nextInt(3);
						do {
							if (amounts[colourChoice] == 0) 
								colourChoice = rand.nextInt(3);
							else
								break;
						} while(true);
						int removeAmount = rand.nextInt(amounts[colourChoice]);
						removeAmount++;
						switch (colours[colourChoice]) { //Removing player's pick from piles.
							case "green":  green  -= removeAmount; break;
							case "yellow": yellow -= removeAmount; break;
							case "orange": orange -= removeAmount;
						}
						System.out.println("Computer has removed: " + removeAmount
								+ " from the " + colours[colourChoice] + " pile"); //Telling Player the computer's move.
						
					}
					
					
					//Check if that was final piece
					if ((green + yellow + orange) == 0) {
						System.out.println("Player loses.");
						computerWins++;
					}
					
					//Setting it to player turn
					playerTurn = true;
				}
				
				
			} 
		}
		System.out.println("Thanks for playing.");
		
	}
}
