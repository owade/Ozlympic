//Author: Thao Hoang - s3393317
package Ozy;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GameEvent {
	// Store the Game list in the event
	private ArrayList<Game> EventGameList = new ArrayList<Game>();
	// Store the participant list of each game (ordered by ranking)
	private ArrayList<ArrayList<Athlete>> winnersList = new ArrayList<ArrayList<Athlete>>();
	// Store all of the participant that participate in the event
	private ArrayList<Athlete> participantsList = new ArrayList<Athlete>();
	Scanner reader = new Scanner(System.in);
	Records rec = new Records();
	ArrayList<Game> gamelist = rec.GameKeeper();
	Official of = new Official();

	public ArrayList<ArrayList<Athlete>> getWinnersList() {
		return winnersList;
	}

	public ArrayList<Athlete> getParticipantList() {
		return participantsList;
	}

	public ArrayList<Game> getGameList() {
		return EventGameList;
	}

	public void setGame(ArrayList<Game> EventGameList) {
		this.EventGameList = EventGameList;
	}

	public void setWinnerList(ArrayList<ArrayList<Athlete>> winnerList) {
		this.winnersList = winnerList;
	}

	public void setParticipantList(ArrayList<Athlete> participantsList) {
		this.participantsList = participantsList;
	}
	//Display all games and it information for the user to select
	public void DisplayAllGame() {
		String gameType = " ";
		for (int i = 0; i < gamelist.size(); i++) {
			if (gamelist.get(i) instanceof RunningGame) {
				gameType = "Running Game";
			} else if (gamelist.get(i) instanceof SwimmingGame) {
				gameType = "Swimming Game";
			} else if (gamelist.get(i) instanceof CyclingGame) {
				gameType = "Cycling Game";
			}
			System.out.println(i + 1 + ". " + "Game ID:" + gamelist.get(i).getId() + ", Game type: " + gameType);
		}
	}
	//User select and validation
	public int SelectGameToRun() {
		int input = 0;
		boolean hasError = false;
		// Validate input
			do {
				System.out.print("Enter a game number: ");
				while(!reader.hasNextInt()){
					String input1 = reader.next();
					System.err.printf("%s is not a valid number. Please input a valid number:\n", input1);
				}
				input = reader.nextInt();
				
				if ((input > 0) && (input < gamelist.size() + 1)) {
					System.out.println("You have selected the game number: " + input);
					return input - 1;
				} else if ((input < 1 ) || (input > gamelist.size())){
					System.out.println();
					System.err.println("Please enter the correct Game ID!");
					hasError = true;
				}
			} while (hasError);
		return input;
	}

	// Dipslay all Athletes of the game that user just selected
	public Game displayAthInCurrentGame(int gameChoosen, GameEvent oz) {
		Game game = null;

		System.out.println("Athletes participate in the current game:");
		if (gamelist.get(gameChoosen) instanceof RunningGame) {
			game = new RunningGame(gamelist.get(gameChoosen).getId(), gamelist.get(gameChoosen).getOfficialID());
			game.DisplayParticipantsList();
			return game;
		} else if (gamelist.get(gameChoosen) instanceof SwimmingGame) {
			game = new SwimmingGame(gamelist.get(gameChoosen).getId(), gamelist.get(gameChoosen).getOfficialID());
			game.DisplayParticipantsList();
			return game;
		} else if (gamelist.get(gameChoosen) instanceof CyclingGame) {
			game = new CyclingGame(gamelist.get(gameChoosen).getId(), gamelist.get(gameChoosen).getOfficialID());
			game.DisplayParticipantsList();
			return game;
		}
		return game;
	}
	//user input and validation
	public int predictWinnerID() {
		int predictAthId = 0;
		try {
			System.out.println("Enter your predict winner ID: ");
			predictAthId = reader.nextInt();

		} catch (InputMismatchException exception) {
			System.err.println("This is not an integer");
		}
		return predictAthId;
	}
	//Satrt the game
	public void startTheGame(Game game, GameEvent oz, int predictAthId) {
		System.out.println("Game started!");
		System.out.println("Ranking result:");
		game.StartGame();
		//Add participants to game event for display result of all athletes purpose
		//This only add the one that participate, not the whole records
		addParticipantsToEvent(game.getAthleteParticipantList(), oz);
		//Official sumarises, give score to Top 3 Athletes and display
		of.DisplayTop3Winner(game.getAthleteParticipantList(), oz);
		//Check if the user input the ID from the list
		int count = 0;
		for (int i = 0; i < game.getAthleteParticipantList().size(); i++) {
			if (game.getAthleteParticipantList().get(i).getId() == predictAthId) {
				count++;
			}
		}

		if (predictAthId == of.getWinnerId(game.getAthleteParticipantList())) {
			System.err.println("Congraturation! Your answer is correct!");
		} else if (count == 0) {
			System.err.println("You've chosen an Athelete ID that NOT participate in the game!");
		} else {
			System.err.println("Your answer is inccorect!");
		}
	}

	// Add Athlete participants to ozlympic event participant list
	public void addParticipantsToEvent(ArrayList<Athlete> currentAthlist, GameEvent oz) {
		for (int i = 0; i < currentAthlist.size(); i++) {
			int count = 0;
			for (int j = 0; j < oz.getParticipantList().size(); j++) {
				if (currentAthlist.get(i).getName() == oz.getParticipantList().get(j).getName()) {
					count++;
				}
			}
			if (count == 0) {
				oz.getParticipantList().add(currentAthlist.get(i));
			}
		}
	}
	//Interate through event participate list and get athletes points
	public void DisplayPointsOfAllAthelete(GameEvent oz) {
		System.out.println("Final ranking of all Athletes:");
		of.SummarizeFinalScore(oz.getParticipantList());
		if (oz.getParticipantList().size() != 0) {
			for (int i = 0; i < oz.getParticipantList().size(); i++) {
				System.out.print(i + 1 + ". " + oz.getParticipantList().get(i).getName());
				System.out.println(", Total Points: " + oz.getParticipantList().get(i).getPoint());
			}
		}
	}

	// Check if the new game is duplicate
	public boolean CheckDuplicateGame(int ChoosenGame) {
		for (int i = 0; i < EventGameList.size(); i++) {
			if (EventGameList.get(i).getId() == gamelist.get(ChoosenGame).getId()) {
				return true;
			}
		}
		return false;
	}

	// Reset the game
	public void ResetGame(int ChoosenGame) {
		for (int i = 0; i < EventGameList.size(); i++) {
			if (EventGameList.get(i).getId() == gamelist.get(ChoosenGame).getId()) {
				// Remove the duplicate game
				EventGameList.remove(EventGameList.get(i));
				// Remove the participant list (ordered by ranking) from that
				// game
				winnersList.remove(winnersList.get(i));
				break;
			}
		}
	}
	
	//Confirm to reset the game if duplicate
	public boolean ConfirmToReset(int ChoosenGame, ArrayList<Game> currentgamelist){
		// remove the old game if duplicate
		if (CheckDuplicateGame(ChoosenGame)) {	
			boolean InvalidComand = true;
			String continueGame = reader.nextLine();
			while (InvalidComand) {
				System.err.println("You've chosen a game that already runned, would you like to reset that game? (y/n)");	
				continueGame = reader.nextLine();
				if (continueGame.equals("y")) {
					ResetGame(ChoosenGame);
					getGameList().add(currentgamelist.get(ChoosenGame));
					return true;
				} else if (continueGame.equals("n")) {
					System.err.println("Going back to Menu...");
					return false;
				} else {
					System.err.println("Invalid choice! Please enter y/n");
					InvalidComand = true;
				}
			} 
		} else {
			// add game to game eventlist
			getGameList().add(currentgamelist.get(ChoosenGame));
		}
		return true;
	}
	//Display result of runned games include game id and the top 3 winners of that game
	public void DisplayFinalResultOfAllGame(GameEvent oz) {
		String gameType = "";
		String officialName = "";
		Records rec = new Records();
		ArrayList<Official> officialList = rec.OfficialRecordsKeeper();
		for (int i = 0; i < oz.getGameList().size(); i++) {
			if (oz.getGameList().get(i) instanceof SwimmingGame) {
				gameType = "Swimming";
			} else if (oz.getGameList().get(i) instanceof RunningGame) {
				gameType = "Running";
			} else if (oz.getGameList().get(i) instanceof CyclingGame) {
				gameType = "Cycling";
			}
			// get official game by official ID in game
			for (int o = 0; o < officialList.size(); o++) {
				if (oz.getGameList().get(i).getOfficialID() == officialList.get(o).getId()) {
					officialName = officialList.get(o).getName();
				}
			}
			System.out.print(i + 1 + ". Game ID: " + oz.getGameList().get(i).getId());
			System.out.println(", Game Type: " + gameType + ", referee Name: " + officialName);
			System.out.println("The Top 3 winners of the game: ");
			// Dispay top 3 winners of each game
			for (int j = 0; j < 3; j++) {
				System.out.println("  " + (j + 1) + ". " + oz.getWinnersList().get(i).get(j).getName());
			}
		}
	}
}