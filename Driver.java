package Ozy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Driver {

	public static String input, ginput,name,sName;
	public static int time;
	public static ArrayList<String> Store = new ArrayList<String>();
	public static ArrayList<String> Prediction = new ArrayList<String>();
	public static ArrayList<String> games = new ArrayList<String>();
	public String res;

	public static void Menu() {
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
				Prediction.clear();
				RunningGame.athleteParticipateList.clear();
				boolean hasMistake = false;
				Scanner greader = new Scanner(System.in);
				do {
					System.out.println("Please select a game to run");
					System.out.println("a. Running");
					System.out.println("b. Swimming");
					System.out.println("c. Cycling");
					ginput = greader.nextLine();
					if (ginput.equals("a")) {
						System.out.println("You've chosen -- Running Game --");
						System.out.println();
						Store.clear();
						Store.add("Running");
						Driver.Menu();
						// System.out.println("You've chosen running
						// game,predict a winner from the list below");
						// ArrayList<Athlete> k = c.recordsKeeper("sprinter");
						// for(Athlete h : k){
						// if(h.getClass().equals(Sprinter.class)){
						// System.out.println(h.getName());
						// }
						// }
					} else if (ginput.equals("b")) {
						System.out.println("You've chosen -- Swimming Game --");
						System.out.println();
						Store.clear();
						Store.add("Swimming");
						Driver.Menu();
					} else if (ginput.equals("c")) {
						System.out.println("You've chosen -- Cycling Game --");
						System.out.println();
						Store.clear();
						Store.add("Cycling");
						Driver.Menu();
					} else {
						System.err.println("Invalid command");
						hasMistake = true;
					}

				} while (hasMistake);
				greader.close();
				break;

			} else if (input.equals("2")) {
				if (Store.size() != 1 ) {
					System.err.println("Please select a Game first before Predicting a Winner!!");
					Driver.Menu();
				}
				if(Prediction.size()>0){
					System.err.println("You have already Predicted a winner,please countinue to step 3 to start game");
					Driver.Menu();
				}
				Scanner pred = new Scanner(System.in);
				String temp = Store.get(0);

				System.out.println("You've chosen " + temp + ",predict a winner from the list below");
				// user input
				System.out.println("Enter a name");
				if (temp.equals("Running")) {
					RunningGame rg = new RunningGame();
					rg.runPreEvent();
					String ans = pred.nextLine();
					if(myContains(RunningGame.athleteParticipateList,ans)){
						// store user prediction
						 Prediction.add(ans);
						 System.out.println("--- Thanks for your Prediction ---");
					}else{
						System.out.println("----------=========----------");
						System.err.println("Please enter a name from the Athlete's list");
						System.out.println("----------=========----------");
						RunningGame.athleteParticipateList.clear();
						Driver.Menu();
					}
				} else if (temp == "Swimming") {
					SwimmingGame sg = new SwimmingGame();
					sg.swimPreEvent();
					String ans = pred.nextLine();
					if(myContains(SwimmingGame.athleteParticipateList,ans)){
						// store user prediction
						 Prediction.add(ans);
						 System.out.println("--- Thanks for your Prediction ---");
					}else{
						System.out.println("----------=========----------");
						System.err.println("Please enter a name from the Athlete's list");
						System.out.println("----------=========----------");
						SwimmingGame.athleteParticipateList.clear();
						Driver.Menu();
					}
				} else {
					CyclingGame cg = new CyclingGame();
					cg.cyclingPreEvent();
					String ans = pred.nextLine();
					if(myContains(CyclingGame.athleteParticipateList,ans)){
						// store user prediction
						 Prediction.add(ans);
						 System.out.println("--- Thanks for your Prediction ---");
					}else{
						System.out.println("----------=========----------");
						System.err.println("Please enter a name from the Athlete's list");
						System.out.println("----------=========----------");
						CyclingGame.athleteParticipateList.clear();
						Driver.Menu();
					}
				}

				
				//String ans = pred.nextLine();
				// store user prediction
				//Prediction.add(ans);				
				Driver.Menu();

			} else if (input.equals("3")) {
				if (Prediction.size() == 0 || Store.size() == 0) {
					System.err.println("Please select a Game and Predict a winner first!!");
					Driver.Menu();
				}
				System.out.println("---- " +Store.get(0) + " Game Has been started -----");
				String name;
				int time;
				String sName = "";
				String gm = Store.get(0);
				if(gm == "Running"){
					for(Athlete i : RunningGame.athleteParticipateList){
						name = i.getName() ;
						time = i.compete();
						RunningGame.gameResultsList.put(name, time);
						System.out.print(name);
						System.out.print(" >>>> ");
						System.out.print(time);
						System.out.println("");
						System.out.println(" --------------- ");
						System.out.println("");
					}
					// Assign points to Athlete
					RunningGame.gameResultsList = sortByValues(RunningGame.gameResultsList);
					for (Map.Entry<String, Integer> entry : (RunningGame.gameResultsList).entrySet()) {
					    //System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
					    if(RunningGame.pointsHash.size() == 0){
					    	RunningGame.pointsHash.put(entry.getKey(), 5);
					    	   if(!RunningGame.AthResHash.containsKey(entry.getKey())) {
					    		RunningGame.AthResHash.put(entry.getKey(),5);
					    		}
					    		else {
					    			RunningGame.AthResHash.put(entry.getKey(), RunningGame.AthResHash.get(entry.getKey())+5);
					    		}
					    }else if(RunningGame.pointsHash.size() == 1){
					    	RunningGame.pointsHash.put(entry.getKey(), 2);
					    	if(!RunningGame.AthResHash.containsKey(entry.getKey())) {
					    		RunningGame.AthResHash.put(entry.getKey(),2);
					    		}
					    		else {
					    			RunningGame.AthResHash.put(entry.getKey(), RunningGame.AthResHash.get(entry.getKey())+2);
					    		}
					    }else if(RunningGame.pointsHash.size() == 2){
					    	RunningGame.pointsHash.put(entry.getKey(), 1);
					    	if(!RunningGame.AthResHash.containsKey(entry.getKey())) {
					    		RunningGame.AthResHash.put(entry.getKey(),1);
					    		}
					    		else {
					    			RunningGame.AthResHash.put(entry.getKey(), RunningGame.AthResHash.get(entry.getKey())+1);
					    		}
					    }else{
					    	RunningGame.pointsHash.put(entry.getKey(), 0);
					    	if(!RunningGame.AthResHash.containsKey(entry.getKey())) {
					    		RunningGame.AthResHash.put(entry.getKey(),0);
					    		}
					    		else {
					    			RunningGame.AthResHash.put(entry.getKey(), RunningGame.AthResHash.get(entry.getKey())+0);
					    		}
					    }
					}
					
					RunningGame.pointsHash = sortByValues(RunningGame.pointsHash);
					for (Map.Entry<String, Integer> entry : (RunningGame.pointsHash).entrySet()) {
					    sName = entry.getKey();
					}
					//Store game information
					int gSize = RunningGame.gameStore.size();
					RunningGame.gameStore.add(new String[]{(String.format("R0%d", gSize + 1)),sName,new Game().getRandomOfficial(Records.officials)});
					//Display Congratulations message to user
					System.out.println("Your Prediction for " + Store.get(0) + " was " + Prediction.get(Prediction.size()-1));
					if(sName.equals(Prediction.get(0))){
						System.out.println("Congragulations Your Prediction was correct");
					}else{
						System.err.println("Sorry,Your Prediction was incorrect, winner for "+ Store.get(0)+ " was " + sName);
					}
					
					RunningGame.athleteParticipateList.clear();
					RunningGame.gameResultsList.clear();
					RunningGame.pointsHash.clear();
//					Prediction.clear();
					//Store.clear();
					Driver.Menu();
				}else if(gm == "Swimming"){
					for(Athlete i : SwimmingGame.athleteParticipateList){
						name = i.getName() ;
						time = i.compete();
						SwimmingGame.gameResultsList.put(name, time);
						System.out.print(name);
						System.out.print(" >>>> ");
						System.out.print(time);
						System.out.println("");
						System.out.println(" --------------- ");
						System.out.println("");
					}
					// Assign points to Athlete
					SwimmingGame.gameResultsList = sortByValues(SwimmingGame.gameResultsList);
					for (Map.Entry<String, Integer> entry : (SwimmingGame.gameResultsList).entrySet()) {
					    //System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
					    if(SwimmingGame.pointsHash.size() == 0){
					    	SwimmingGame.pointsHash.put(entry.getKey(), 5);
					    	if(!SwimmingGame.AthResHash.containsKey(entry.getKey())) {
					    		SwimmingGame.AthResHash.put(entry.getKey(),5);
					    		}
					    		else {
					    			SwimmingGame.AthResHash.put(entry.getKey(), SwimmingGame.AthResHash.get(entry.getKey())+5);
					    		}
					    }else if(SwimmingGame.pointsHash.size() == 1){
					    	SwimmingGame.pointsHash.put(entry.getKey(), 2);
					    	if(!SwimmingGame.AthResHash.containsKey(entry.getKey())) {
					    		SwimmingGame.AthResHash.put(entry.getKey(),2);
					    		}
					    		else {
					    			SwimmingGame.AthResHash.put(entry.getKey(), SwimmingGame.AthResHash.get(entry.getKey())+2);
					    		}
					    }else if(SwimmingGame.pointsHash.size() == 2){
					    	SwimmingGame.pointsHash.put(entry.getKey(), 1);
					    	if(!SwimmingGame.AthResHash.containsKey(entry.getKey())) {
					    		SwimmingGame.AthResHash.put(entry.getKey(),1);
					    		}
					    		else {
					    			SwimmingGame.AthResHash.put(entry.getKey(), SwimmingGame.AthResHash.get(entry.getKey())+1);
					    		}
					    }else{
					    	SwimmingGame.pointsHash.put(entry.getKey(), 0);
					    	if(!SwimmingGame.AthResHash.containsKey(entry.getKey())) {
					    		SwimmingGame.AthResHash.put(entry.getKey(),0);
					    		}
					    		else {
					    			SwimmingGame.AthResHash.put(entry.getKey(), SwimmingGame.AthResHash.get(entry.getKey())+0);
					    		}
					    }
					}
					
					SwimmingGame.pointsHash = sortByValues(SwimmingGame.pointsHash);
					for (Map.Entry<String, Integer> entry : (SwimmingGame.pointsHash).entrySet()) {
					    sName = entry.getKey();
					}
					//Store game information
					int gSize = SwimmingGame.gameStore.size();
					SwimmingGame.gameStore.add(new String[]{(String.format("S0%d", gSize + 1)),sName,new Game().getRandomOfficial(Records.officials)});
					//Display Congratulations message to user
					System.out.println("Your Prediction for " + Store.get(0) + " was " + Prediction.get(0));
					if(sName.equals(Prediction.get(0))){
						System.out.println("Congragulations Your Prediction was correct");
					}else{
						System.err.println("Sorry,Your Prediction was incorrect, winner for "+ Store.get(0)+ " was " + sName);
					}
					SwimmingGame.athleteParticipateList.clear();
					SwimmingGame.gameResultsList.clear();
					SwimmingGame.pointsHash.clear();
//					Prediction.clear();
					//Store.clear();
					Driver.Menu();
       			}else if(gm == "Cycling"){
					for(Athlete i : CyclingGame.athleteParticipateList){
						name = i.getName() ;
						time = i.compete();
						CyclingGame.gameResultsList.put(name, time);
						System.out.print(name);
						System.out.print(" >>>> ");
						System.out.print(i.compete());
						System.out.println("");
						System.out.println(" --------------- ");
						System.out.println("");
					}
					// Assign points to Athlete
					CyclingGame.gameResultsList = sortByValues(CyclingGame.gameResultsList);
					for (Map.Entry<String, Integer> entry : (CyclingGame.gameResultsList).entrySet()) {
					    //System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
					    if(CyclingGame.pointsHash.size() == 0){
					    	CyclingGame.pointsHash.put(entry.getKey(), 5);
					    	if(!CyclingGame.AthResHash.containsKey(entry.getKey())) {
					    		CyclingGame.AthResHash.put(entry.getKey(),5);
					    		}
					    		else {
					    			CyclingGame.AthResHash.put(entry.getKey(), CyclingGame.AthResHash.get(entry.getKey())+5);
					    		}
					    }else if(CyclingGame.pointsHash.size() == 1){
					    	CyclingGame.pointsHash.put(entry.getKey(), 2);
					    	if(!CyclingGame.AthResHash.containsKey(entry.getKey())) {
					    		CyclingGame.AthResHash.put(entry.getKey(),2);
					    		}
					    		else {
					    			CyclingGame.AthResHash.put(entry.getKey(), CyclingGame.AthResHash.get(entry.getKey())+2);
					    		}
					    }else if(CyclingGame.pointsHash.size() == 2){
					    	CyclingGame.pointsHash.put(entry.getKey(), 1);
					    	if(!CyclingGame.AthResHash.containsKey(entry.getKey())) {
					    		CyclingGame.AthResHash.put(entry.getKey(),1);
					    		}
					    		else {
					    			CyclingGame.AthResHash.put(entry.getKey(), CyclingGame.AthResHash.get(entry.getKey())+1);
					    		}
					    }else{
					    	CyclingGame.pointsHash.put(entry.getKey(), 0);
					    	if(!CyclingGame.AthResHash.containsKey(entry.getKey())) {
					    		CyclingGame.AthResHash.put(entry.getKey(),0);
					    		}
					    		else {
					    			CyclingGame.AthResHash.put(entry.getKey(), CyclingGame.AthResHash.get(entry.getKey())+0);
					    		}
					    }
					}
					
					CyclingGame.pointsHash = sortByValues(CyclingGame.pointsHash);
					for (Map.Entry<String, Integer> entry : (CyclingGame.pointsHash).entrySet()) {
					    sName = entry.getKey();
					}
					//Store game information
					int gSize = CyclingGame.gameStore.size();
					CyclingGame.gameStore.add(new String[]{(String.format("C0%d", gSize + 1)),sName,new Game().getRandomOfficial(Records.officials)});
					//Display Congratulations message to user
					System.out.println("Your Prediction for " + Store.get(0) + " was " + Prediction.get(0));
					if(sName.equals(Prediction.get(0))){
						System.out.println("Congragulations Your Prediction was correct");
					}else{
						System.err.println("Sorry,Your Prediction was incorrect, winner for "+ Store.get(0)+ " was " + sName);
					}
					CyclingGame.athleteParticipateList.clear();
					CyclingGame.gameResultsList.clear();
					CyclingGame.pointsHash.clear();
//					Prediction.clear();
//					Store.clear();
					Driver.Menu();
	
				}
				
//				for(Athlete l : CyclingGame.athleteParticipateList){
//					System.out.println(l.getName());
//				}


				
				
			} else if (input.equals("4")) {


				//user must first play a game first inorder to avoid playing a game twice
//				if (Prediction.size() == 0 || Store.size() == 0) {
//					System.err.println("Please select a Game and Predict a winner first!!");
//					Driver.Menu();
//				}
				//get name of current game
				String cGame = Store.get(Store.size()-1); 
//				if (cGame == "Running") {
//					System.out.println(Store.get(Store.size()-1));
//					System.err.println("Please start the Running game before proceeding");
//					Store.clear();
//					Driver.Menu();
//				}else if(cGame == "Swimming") {
//					System.err.println("Please start the Swimming game before proceeding");
//					Store.clear();
//					Driver.Menu();
//				}else if(cGame == "Cycling"){
//					System.err.println("Please start the Cycling game before proceeding");
//					Store.clear();
//					Driver.Menu();
//				}
				
				//Display results of all games other than the one the user has just started
				//Run other two games automatically since user has not yet played them ......
				games.add("Running");
				games.add("Swimming");
				games.add("Cycling");
				int idx = games.indexOf(Store.get(0));
				games.remove(idx);
				
				for(int i = 0 ;i < games.size();i++){
					if(games.get(i) == "Running"){
						//System.out.println(" - Running Game -");
						RunningGame a = new RunningGame();
						a.runEvent();
					}else if(games.get(i) == "Swimming"){
						//System.out.println(" - Swimming Game -");
						SwimmingGame b = new SwimmingGame();
						b.swimEvent();
					}else if(games.get(i) == "Cycling"){
						//System.out.println(" - Cycling Game -");
						CyclingGame c = new CyclingGame();
						c.cycleEvent();
					}
				}
				
//				Display each game result if it has been played by user
				System.out.println("----");
				System.out.println("Running Games");
				if(RunningGame.gameStore.size()==0){
                	System.out.println("No running game has been played !");
                }
                for (String[] i : RunningGame.gameStore) {
					for (int j = 0; j < i.length; j++) {
						if(j == 0){
							System.out.print("GAME ID");
							System.out.print(" >>> ");
							System.out.print(i[j]);
							System.out.println("");
						}else if(j == 1){
							System.out.print(" Winner was ");
							System.out.print(" >>> ");
							System.out.print(i[j]);
							System.out.println("");
						}else{
							System.out.print(" Referee incharge >>> ");
							System.out.print(" ");
							System.out.print(i[j]);
						}
					}
					System.out.println("");
				}
                System.out.println("----");
                System.out.println("Swimming Games");
                if(SwimmingGame.gameStore.size()==0){
                	System.out.println("No swimming game has been played !");
                }
                for (String[] i : SwimmingGame.gameStore) {
					for (int j = 0; j < i.length; j++) {
						if(j == 0){
							System.out.print("GAME ID");
							System.out.print(" >>> ");
							System.out.print(i[j]);
							System.out.println("");
						}else if(j == 1){
							System.out.print(" Winner was ");
							System.out.print(" >>> ");
							System.out.print(i[j]);
							System.out.println("");
						}else{
							System.out.print(" Referee incharge >>> ");
							System.out.print(" ");
							System.out.print(i[j]);
						}
					}
					System.out.println("");
				}
                System.out.println("----");
                System.out.println("Cycling Games");
                if(CyclingGame.gameStore.size()==0){
                	System.out.println("No cycling game has been played !");
                }
                for (String[] i : CyclingGame.gameStore) {
					for (int j = 0; j < i.length; j++) {
						if(j == 0){
							System.out.print("GAME ID");
							System.out.print(" >>> ");
							System.out.print(i[j]);
							System.out.println("");
						}else if(j == 1){
							System.out.print(" Winner was ");
							System.out.print(" >>> ");
							System.out.print(i[j]);
							System.out.println("");
						}else{
							System.out.print(" Referee incharge >>> ");
							System.out.print(" ");
							System.out.print(i[j]);
						}
					}
					System.out.println("");
				}
				
				System.out.println("");
				
				
				
//				//iterate through the game in current context which has already been played
//				
//
//				//Now loop game result for the current game
//				System.out.println("--- RESULTS FOR CURRENT GAME ----");
//				System.out.println(cGame);
//				if (cGame == "Running") {
//					for (String key : RunningGame.gameResultsList.keySet()) {
//					    System.out.println(key);
//					}
//				}else if(cGame == "Swimming") {
//					for (String key : SwimmingGame.gameResultsList.keySet()) {
//					    System.out.println(key);
//					}
//				}else if(cGame == "Cycling"){
//					for (String key : CyclingGame.gameResultsList.keySet()) {
//					    System.out.println(key);
//					}
//				}else{
//					System.err.println("- YOU HAVE NOT PLAYED ANY GAME YET -");
//				}

				
				Driver.Menu();
				
			} else if (input.equals("5")) {
				//Ensure User Goes through Step 1 to 4 so that games data can be generated .....
//				if (RunningGame.AthResHash.size() == 0 ||CyclingGame.AthResHash.size() == 0 ||SwimmingGame.AthResHash.size() == 0) {
//					System.err.println("You are required to follow steps 1 - 4 first ...");
//					Driver.Menu();
//				}
				
				
               //Display points of all athletes --
				System.out.println("------------------");
				System.out.println("Displaying points of all " + RunningGame.AthResHash.size() + " Sprinters");
				for (Map.Entry<String, Integer> entry : RunningGame.AthResHash.entrySet()) {
				    String key = entry.getKey();
				    int value = entry.getValue();
				    System.out.println(key + " === " + value + " points");
				}
				System.out.println("------------------");
				System.out.println("Displaying points of all " + SwimmingGame.AthResHash.size() + " Swimmers");
				for (Map.Entry<String, Integer> entry : SwimmingGame.AthResHash.entrySet()) {
				    String key = entry.getKey();
				    int value = entry.getValue();
				    System.out.println(key + " === " + value + " points");
				}
				System.out.println("------------------");
				System.out.println("Displaying points of all " + CyclingGame.AthResHash.size() + " Cyclists");
				for (Map.Entry<String, Integer> entry : CyclingGame.AthResHash.entrySet()) {
				    String key = entry.getKey();
				    int value = entry.getValue();
				    System.out.println(key + " === " + value + " points");
				}
				
				
//				//Display results of other 2 games other than the one the user has just started
//				games.add("Running");
//				games.add("Swimming");
//				games.add("Cycling");
//				int idx = games.indexOf(Store.get(0));
//				games.remove(idx);
//				
//				for(int i = 0 ;i < games.size();i++){
//					if(games.get(i) == "Running"){
//						System.out.println(" - Running Game -");
//						RunningGame a = new RunningGame();
//						a.runPreEvent();
//						for(Athlete l : RunningGame.athleteParticipateList){
//							name = l.getName() ;
//							time = l.compete();
//							RunningGame.gameResultsList.put(name, time);
//							System.out.print(name);
//							System.out.print(" >>>> ");
//							System.out.print(time);
//							System.out.println("");
//							System.out.println(" --------------- ");
//							System.out.println("");
//						}
//						// Assign points to Athlete
//						RunningGame.gameResultsList = sortByValues(RunningGame.gameResultsList);
//						for (Map.Entry<String, Integer> entry : (RunningGame.gameResultsList).entrySet()) {
//						    //System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
//						    if(RunningGame.pointsHash.size() == 0){
//						    	RunningGame.pointsHash.put(entry.getKey(), 5);
//						    }else if(RunningGame.pointsHash.size() == 1){
//						    	RunningGame.pointsHash.put(entry.getKey(), 2);
//						    }else if(RunningGame.pointsHash.size() == 2){
//						    	RunningGame.pointsHash.put(entry.getKey(), 1);
//						    }else{
//						    	RunningGame.pointsHash.put(entry.getKey(), 0);
//						    }
//						}
//						
//						RunningGame.pointsHash = sortByValues(RunningGame.pointsHash);
//						for (Map.Entry<String, Integer> entry : (RunningGame.pointsHash).entrySet()) {
//						    sName = entry.getKey();
//						}
//						//Display Congratulations message to user
//						System.out.println("Your Prediction for " + Store.get(0) + " was " + Prediction.get(0));
//						if(sName.equals(Prediction.get(0))){
//							System.out.println("Congragulations Your Prediction was correct");
//						}else{
//							System.err.println("Sorry,Your Prediction was incorrect, winner for "+ Store.get(0)+ " was " + sName);
//						}
//						RunningGame.athleteParticipateList.clear();
//						RunningGame.gameResultsList.clear();
//						RunningGame.pointsHash.clear();
//						Prediction.clear();
//						Store.clear();
//						Driver.Menu();
//					}else if(games.get(i) == "Swimming"){
//						System.out.println(" - Swimming Game -");
//						SwimmingGame b = new SwimmingGame();
//						b.swimPreEvent();
//						for(Athlete m : SwimmingGame.athleteParticipateList){
//							name = m.getName() ;
//							time = m.compete();
//							SwimmingGame.gameResultsList.put(name, time);
//							System.out.print(name);
//							System.out.print(" >>>> ");
//							System.out.print(time);
//							System.out.println("");
//							System.out.println(" --------------- ");
//							System.out.println("");
//						}
//						// Assign points to Athlete
//						SwimmingGame.gameResultsList = sortByValues(SwimmingGame.gameResultsList);
//						for (Map.Entry<String, Integer> entry : (SwimmingGame.gameResultsList).entrySet()) {
//						    //System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
//						    if(SwimmingGame.pointsHash.size() == 0){
//						    	SwimmingGame.pointsHash.put(entry.getKey(), 5);
//						    }else if(SwimmingGame.pointsHash.size() == 1){
//						    	SwimmingGame.pointsHash.put(entry.getKey(), 2);
//						    }else if(SwimmingGame.pointsHash.size() == 2){
//						    	SwimmingGame.pointsHash.put(entry.getKey(), 1);
//						    }else{
//						    	SwimmingGame.pointsHash.put(entry.getKey(), 0);
//						    }
//						}
//						
//						SwimmingGame.pointsHash = sortByValues(SwimmingGame.pointsHash);
//						for (Map.Entry<String, Integer> entry : (SwimmingGame.pointsHash).entrySet()) {
//						    sName = entry.getKey();
//						}
//						//Display Congratulations message to user
//						System.out.println("Your Prediction for " + Store.get(0) + " was " + Prediction.get(0));
//						if(sName.equals(Prediction.get(0))){
//							System.out.println("Congragulations Your Prediction was correct");
//						}else{
//							System.err.println("Sorry,Your Prediction was incorrect, winner for "+ Store.get(0)+ " was " + sName);
//						}
//						SwimmingGame.athleteParticipateList.clear();
//						SwimmingGame.gameResultsList.clear();
//						SwimmingGame.pointsHash.clear();
//						Prediction.clear();
//						Store.clear();
//						Driver.Menu();
//					}else if(games.get(i) == "Cycling"){
//						System.out.println(" - Cycling Game -");
//						CyclingGame c = new CyclingGame();
//						c.cyclingPreEvent();
//						for(Athlete v : CyclingGame.athleteParticipateList){
//							name = v.getName() ;
//							time = v.compete();
//							CyclingGame.gameResultsList.put(name, time);
//							System.out.print(name);
//							System.out.print(" >>>> ");
//							System.out.print(v.compete());
//							System.out.println("");
//							System.out.println(" --------------- ");
//							System.out.println("");
//						}
//						// Assign points to Athlete
//						CyclingGame.gameResultsList = sortByValues(CyclingGame.gameResultsList);
//						for (Map.Entry<String, Integer> entry : (CyclingGame.gameResultsList).entrySet()) {
//						    //System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
//						    if(CyclingGame.pointsHash.size() == 0){
//						    	CyclingGame.pointsHash.put(entry.getKey(), 5);
//						    }else if(CyclingGame.pointsHash.size() == 1){
//						    	CyclingGame.pointsHash.put(entry.getKey(), 2);
//						    }else if(CyclingGame.pointsHash.size() == 2){
//						    	CyclingGame.pointsHash.put(entry.getKey(), 1);
//						    }else{
//						    	CyclingGame.pointsHash.put(entry.getKey(), 0);
//						    }
//						}
//						
//						CyclingGame.pointsHash = sortByValues(CyclingGame.pointsHash);
//						for (Map.Entry<String, Integer> entry : (CyclingGame.pointsHash).entrySet()) {
//						    sName = entry.getKey();
//						}
//						//Display Congratulations message to user
//						System.out.println("Your Prediction for " + Store.get(0) + " was " + Prediction.get(0));
//						if(sName.equals(Prediction.get(0))){
//							System.out.println("Congragulations Your Prediction was correct");
//						}else{
//							System.err.println("Sorry,Your Prediction was incorrect, winner for "+ Store.get(0)+ " was " + sName);
//						}
//						CyclingGame.athleteParticipateList.clear();
//						CyclingGame.gameResultsList.clear();
//						CyclingGame.pointsHash.clear();
//						Prediction.clear();
//						Store.clear();
//						Driver.Menu();
//					}
//				}
//				
//				
//				Driver.Menu();
//				
//			
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
    
	public static boolean myContains(ArrayList<Athlete> data,String comp){
		for(Athlete i : data){
			if (i.getName().equals(comp)){
				return true;
			}
		}
		return false;
	}
	
	
	
	 public static HashMap<String, Integer> sortByValues(HashMap map) { 
	       List list = new LinkedList(map.entrySet());
	       // Defined Custom Comparator here
	       Collections.sort(list, new Comparator() {
	            public int compare(Object o1, Object o2) {
	               return ((Comparable) ((Map.Entry) (o1)).getValue())
	                  .compareTo(((Map.Entry) (o2)).getValue());
	            }
	       });

	       // Here I am copying the sorted list in HashMap
	       // using LinkedHashMap to preserve the insertion order
	       HashMap sortedHashMap = new LinkedHashMap();
	       for (Iterator it = list.iterator(); it.hasNext();) {
	              Map.Entry entry = (Map.Entry) it.next();
	              sortedHashMap.put(entry.getKey(), entry.getValue());
	       } 
	       return sortedHashMap;
	  }
}
