package Ozy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class CyclingGame extends Game {
	public int totalAthlete = super.genTotalAthelete();
	public int noOfCyclist = super.genNoOfNormalAthelete();
	public int noOfSuperAthlete = super.calNoOfSuperAthlete();

	ArrayList<Athlete> cyclistList = new ArrayList<Athlete>();
	ArrayList<Athlete> superAthList = new ArrayList<Athlete>();

	public static HashMap<String, Integer> gameResultsList = new HashMap<String, Integer>();
	public static HashMap<String, Integer> AthResHash = new HashMap<String, Integer>();
	public static HashMap<String, Integer> pointsHash = new HashMap<String, Integer>();
	public static ArrayList<Athlete> athleteParticipateList = new ArrayList<Athlete>();
	public static ArrayList<String[]> gameStore = new ArrayList<String[]>();	
	// TO-DO
	// Sort athleteParticipateList according to their runtime
	// Pick 3 first cyclists
	// return to Official to summarize the game
	// public void getName(){
	// addAthleteToItsList();
	// pickAthlete();
	// GetAthName();
	// }

	public  void cyclingPreEvent() {
		addAthleteToItsList();
		pickAthlete();
		DisplayCyclists();
	}

	public void cycleEvent() {
		addAthleteToItsList();
		pickAthlete();
		Cycle();
	}

	public void addAthleteToItsList() {

		Records rec = new Records();
		ArrayList<Athlete> list = rec.recordsKeeper("cyclist");
		for (int i = 0; i < list.size(); i++) {
			// pick random noOfSprinter in Sprinter
			if (list.get(i) instanceof Cyclist) {
				cyclistList.add(list.get(i));
			}
			// pick random noOfSuperAthlete in SuperAthlete
			if (list.get(i) instanceof SuperAthlete) {
				superAthList.add(list.get(i));
			}
		}
	}

	public void pickAthlete() {
		// Pick random index form cyclistList
		final int[] indexOfCyclists = new Random().ints(0, cyclistList.size()).distinct().limit(noOfCyclist).toArray();
		// Pick random index from superAthleteList
		final int[] indexOfSuperAthlete = new Random().ints(0, superAthList.size()).distinct().limit(noOfSuperAthlete)
				.toArray();

		// Get random sprinters from sprinterList based on random index array
		for (int i = 0; i < indexOfCyclists.length; i++) {
			athleteParticipateList.add(cyclistList.get(indexOfCyclists[i]));
		}
		// Get random super athlete from superAthList based on random index
		// array
		for (int i = 0; i < indexOfSuperAthlete.length; i++) {
			athleteParticipateList.add(superAthList.get(indexOfSuperAthlete[i]));
		}
	}

	// public void GetAthName(){
	// for(int i = 0 ; i < athleteParticipateList.size();i++){
	// System.out.println(athleteParticipateList.get(i).getName());
	// }
	// }
	public void DisplayCyclists() {
		System.out.println("Total Participants: " + totalAthlete);
		System.out.println("Number of Actual Cyclists: " + noOfCyclist);
		System.out.println("Number of Super Athletes: " + noOfSuperAthlete);
		for (int i = 0; i < athleteParticipateList.size(); i++) {
			System.out.println(athleteParticipateList.get(i).getName());
		}
	}

	public void Cycle(){
		for(int i = 0; i < athleteParticipateList.size();i++){
			athleteParticipateList.get(i).compete();
		}
	}
}
