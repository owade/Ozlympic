//Author: Felix Owade Otieno - s3579452
package Ozy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Driver {
	public static String input;
	public static GameEvent oz = new GameEvent();
	static Records rec = new Records();
	static ArrayList<Game> gamelist = rec.GameKeeper();
	static Game game;
	int gamechoosen;
	int predictWinnerId;
	
	//Make sure user follow step 1->2->3 in the menu
	boolean step1 = true;
	boolean step2 = false;
	boolean step3 = false;

	public void Menu() {
		Scanner reader = new Scanner(System.in);
		boolean hasError = false;
		
		do {
			System.out.println();
			System.out.println("Ozlympic Game");
			System.out.println("======================================");
			System.out.println("1. Select a game to run");
			System.out.println("2. Predict the winner of the game");
			System.out.println("3. Start the game");
			System.out.println("4. Display the final results of all games");
			System.out.println("5. Display the points of all athletes");
			System.out.println("6. Exit");
			System.out.println();

			System.out.print("Enter an option: _");
			input = reader.nextLine();

			if (input.equals("1")) {
				if (step1 == false) {
					informCurrentStep();
					Menu();
				} else {
					oz.DisplayAllGame();
					gamechoosen = oz.SelectGameToRun();
					// Check if duplicate game then ask user to reset
					boolean reset = oz.ConfirmToReset(gamechoosen, gamelist);
					if(reset == true){
						step1 = false;
						step2 = true;
					}else{
						step1 = true;
						step2 = false;
					}
					Menu();
				}
				break;
			} else if (input.equals("2")) {
				if (step2 == false) {
					informCurrentStep();
					Menu();
				} else {
					game = oz.displayAthInCurrentGame(gamechoosen, oz);
					predictWinnerId = oz.predictWinnerID();
					step2 = false;
					step3 = true;
					Menu();
				}
				break;
			} else if (input.equals("3")) {
				if (step3 == false) {
					informCurrentStep();
					Menu();
				} else {
					oz.startTheGame(game, oz, predictWinnerId);
					step3 = false;
					step1 = true;
					Menu();
				}
				break;
			} else if (input.equals("4")) {
				if(checkCompleteStep()){
					oz.DisplayFinalResultOfAllGame(oz);
				}else{
					informCurrentStep();
				}
				Menu();
				break;
			} else if (input.equals("5")) {
				if(checkCompleteStep()){
					oz.DisplayPointsOfAllAthelete(oz);
				}else{
					informCurrentStep();
				}
				Menu();
				break;
			} else if (input.equals("6")) {
				System.out.println("Chosen To Exit , Goodbye");
				System.exit(0);
			} else {
				System.err.println("Invalid command");
				hasError = true;
			}

		} while (hasError);
		reader.close();
	}
	//To inform what step user should go to continue the game
	public void informCurrentStep(){
		if(step1 == true){
			System.err.println("Please select a game to run first (step 1)");
		}else if(step2 == true){
			System.err.println("Please Predict the winner of the game (step 2)");
		}else if(step3 == true){
			System.err.println("Please start a game you have selected (step 3)");
		}
	}
	
	//Check if user could go to step 4 or 5
	public boolean checkCompleteStep(){
		if(step1 == true && step2 == false && step3 == false){
			return true;
		}
		return false;
	}
	

}
