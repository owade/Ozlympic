package Ozy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Driver {
	    public static String input;
		public void Menu(){
			Scanner reader = new Scanner(System.in);
			boolean hasError = false;
			do {
				System.out.println("Ozlympic Game");
				System.out.println("======================================");
				System.out.println("1. Select a game to run");
				System.out.println("2. Predict the winner of the game");
				System.out.println("3. Start the game");
				System.out.println("4. Display the final results of all games");
				System.out.println("5. Display the points of all athletes");
				System.out.println("6. Exit");		
				System.out.println();
				 
				System.out.println("Enter an option: _");	

				input = reader.nextLine();

				if (input.equals("1")) {
					//testing 
					Records rec = new Records();
					ArrayList <Athlete> list = rec.recordsKeeper("sprinter");
					for(int i = 0; i< list.size(); i++){
						System.out.println(list.get(i).complete());
					}
					
					System.out.println("Chosen 1");
					break;
				} else if (input.equals("2")) {
					//do-something
					System.out.println("Chosen 2");
					break;
				} else if (input.equals("3")) {
					//do-something
					System.out.println("Chosen 3");
					break;
				} else if (input.equals("4")) {
					System.out.println("Chosen 4");
					//do-something
					break;
				} else if (input.equals("5")) {
					System.out.println("Chosen 5");
					//do-something
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
					
		} 

