package Ozy;
import java.util.concurrent.ThreadLocalRandom;

public class Game {
	public String id;
	public String official_ID;
	public int althlete_count;
	public int totalAthlete;
	public int noOfNormalAthlete;
	public int noOfSuperAthlete;
	
	//Generate total number of Athlete Participate (min 4, max 8)
	public int genTotalAthelete(){
		totalAthlete = ThreadLocalRandom.current().nextInt(4, 9);
		return totalAthlete;
	}
	//Generate number of normal Athlete participate in a Game from 0-totalAthlete
	public int genNoOfNormalAthelete(){
		noOfNormalAthlete = ThreadLocalRandom.current().nextInt(0, totalAthlete + 1);
		return noOfNormalAthlete;
	}
	
	//Get number of Super Athlete participate in a Game
	public int calNoOfSuperAthlete(){
		noOfSuperAthlete = totalAthlete - noOfNormalAthlete;
		return noOfSuperAthlete;
	}
	
	
}
