//Author: Thao Hoang
package Ozy;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Game {
	public String id;
	public int officialID;
	public int totalAthlete;
	public int noOfNormalAthlete;
	public int noOfSuperAthlete;

	ArrayList<Athlete> athleteParticipateList = new ArrayList<Athlete>();

	public ArrayList<Athlete> getAthleteParticipantList() {
		return athleteParticipateList;
	}

	public Game(String id, int officialID) {
		this.id = id;
		this.officialID = officialID;
	}

	public String getId() {
		return id;
	}

	public int getOfficialID() {
		return officialID;
	}

	public abstract void DisplayParticipantsList();

	public abstract void StartGame();

	// Generate total number of Athlete Participate (min 4, max 8)
	public int genTotalAthelete() {
		totalAthlete = ThreadLocalRandom.current().nextInt(4, 9);
		return totalAthlete;
	}

	// Generate number of normal Athlete participate in a Game from
	// 0-totalAthlete
	public int genNoOfNormalAthelete() {
		noOfNormalAthlete = ThreadLocalRandom.current().nextInt(0, totalAthlete + 1);
		return noOfNormalAthlete;
	}

	// Get number of Super Athlete participate in a Game
	public int calNoOfSuperAthlete() {
		noOfSuperAthlete = totalAthlete - noOfNormalAthlete;
		return noOfSuperAthlete;
	}

	public void addAthleteToItsList(ArrayList<Athlete> normalAthList, ArrayList<Athlete> superAthList,
			Class<?> className, String superAthType) {

		Records rec = new Records();
		ArrayList<Athlete> list = rec.recordsKeeper(superAthType);
		for (int i = 0; i < list.size(); i++) {
			// pick random noOfSprinter in Sprinter
			if (className.isInstance(list.get(i))) {
				normalAthList.add(list.get(i));
			}
			// pick random noOfSuperAthlete in SuperAthlete
			if (list.get(i) instanceof SuperAthlete) {
				superAthList.add(list.get(i));
			}
		}
	}

	public void pickAthlete(ArrayList<Athlete> normalAthList, ArrayList<Athlete> superAthList,
			ArrayList<Athlete> athleteParticipateList) {
		genTotalAthelete();
		int noOfNormalAth = genNoOfNormalAthelete();
		int noOgSuperAth = calNoOfSuperAthlete();

		// Pick random index form sprinterList
		final int[] indexOfSprinters = new Random().ints(0, normalAthList.size()).distinct().limit(noOfNormalAth)
				.toArray();
		// Pick random index from superAthleteList
		final int[] indexOfSuperAthlete = new Random().ints(0, superAthList.size()).distinct().limit(noOgSuperAth)
				.toArray();

		// Get random sprinters from sprinterList based on random idex array
		for (int i = 0; i < indexOfSprinters.length; i++) {
			athleteParticipateList.add(normalAthList.get(indexOfSprinters[i]));
		}
		// Get random super athlete from superAthList based on random idex array
		for (int i = 0; i < indexOfSuperAthlete.length; i++) {
			athleteParticipateList.add(superAthList.get(indexOfSuperAthlete[i]));
		}

	}
	//Get Information of athletes who participate in the game
	public void GetAthName(ArrayList<Athlete> athleteParticipateList) {
		String Athletetype = "";
		for (int i = 0; i < athleteParticipateList.size(); i++) {
			if (athleteParticipateList.get(i) instanceof Swimmer) {
				Athletetype = "Swimmer";
			} else if (athleteParticipateList.get(i) instanceof Sprinter) {
				Athletetype = "Sprinter";
			} else if (athleteParticipateList.get(i) instanceof Cyclist) {
				Athletetype = "Cylist";
			} else if (athleteParticipateList.get(i) instanceof SuperAthlete) {
				Athletetype = "Super Athlete";
			}
			System.out.print("ID: " + athleteParticipateList.get(i).getId());
			System.out.print(", Name: " + athleteParticipateList.get(i).getName());
			System.out.print(", Age: " + athleteParticipateList.get(i).getAge());
			System.out.print(", State: " + athleteParticipateList.get(i).getState());
			System.out.println(", Athelete Type: " + Athletetype);
		}
	}
	//Call the complete method of each athlete to get their finish time
	public void Run(ArrayList<Athlete> athleteParticipateList) {
		for (int i = 0; i < athleteParticipateList.size(); i++) {
			double timeToComplt = athleteParticipateList.get(i).complete();
			athleteParticipateList.get(i).setTimeToComplete(timeToComplt);
		}
		//Official will sort the list by their complete time and display
		Official off = new Official();
		off.SummarizeScore(athleteParticipateList);
		for (int i = 0; i < athleteParticipateList.size(); i++) {
			System.out.print(i + 1 + ". " + athleteParticipateList.get(i).getName() + ", Complete Time: ");
			System.out.println(athleteParticipateList.get(i).getTimeToComplete());
		}
	}
}
