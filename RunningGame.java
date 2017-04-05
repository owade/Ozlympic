package Ozy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.SortedMap;

public class RunningGame extends Game{
	public int totalAthlete = super.genTotalAthelete();
	public int noOfSprinter = super.genNoOfNormalAthelete();
	public int noOfSuperAthlete = super.calNoOfSuperAthlete();
	
	ArrayList<Athlete> sprinterList = new ArrayList<Athlete>();
	ArrayList<Athlete> superAthList = new ArrayList<Athlete>();
	
	public static HashMap<String, Integer> gameResultsList = new HashMap<String, Integer>();
	public static HashMap<String, Integer> AthResHash = new HashMap<String, Integer>();
	public static HashMap<String, Integer> pointsHash = new HashMap<String, Integer>();	
	public static ArrayList<String[]> gameStore = new ArrayList<String[]>();	
    public static ArrayList<Athlete> athleteParticipateList = new ArrayList<Athlete>();
	//TO-DO
	//Sort athleteParticipateList according to their runtime
	//Pick 3 first runner
	//return to Official to summarize the game
//    public void getName(){
//    	addAthleteToItsList();
//    	pickAthlete();
//    	GetAthName();
//    }
    
    public void runPreEvent(){
		addAthleteToItsList();
		pickAthlete();
		DisplayRunners();
	}
    
	public void runEvent(){
		addAthleteToItsList();
		pickAthlete();
		Run();
	}
	
	public void addAthleteToItsList(){
		
		Records rec = new Records();
		ArrayList <Athlete> list = rec.recordsKeeper("sprinter");
		for(int i = 0; i< list.size(); i++){
			//pick random noOfSprinter in Sprinter
			if(list.get(i) instanceof Sprinter){
				sprinterList.add(list.get(i));
			}
			//pick random noOfSuperAthlete in SuperAthlete
			if(list.get(i) instanceof SuperAthlete){
				superAthList.add(list.get(i));
			}
		}
	}
	
	public void pickAthlete(){
		//Pick random index form sprinterList
		final int[] indexOfSprinters = new Random().ints(0, sprinterList.size()).distinct().limit(noOfSprinter).toArray();
		//Pick random index from superAthleteList
		final int[] indexOfSuperAthlete = new Random().ints(0, superAthList.size()).distinct().limit(noOfSuperAthlete).toArray();
		
		//Get random sprinters from sprinterList based on random index array
		for(int i = 0; i < indexOfSprinters.length; i++){
			athleteParticipateList.add(sprinterList.get(indexOfSprinters[i]));
		}
		//Get random super athlete from superAthList based on random index array
		for(int i = 0; i < indexOfSuperAthlete.length; i++){
			athleteParticipateList.add(superAthList.get(indexOfSuperAthlete[i]));
		}	
	}
	
//	public void GetAthName(){
//		for(int i = 0 ; i < athleteParticipateList.size();i++){
//			System.out.println(athleteParticipateList.get(i).getName());
//		}
//	}
	public void DisplayRunners(){
		System.out.println("Total Participants: " + totalAthlete);
		System.out.println("Number of Actual Sprinters: " + noOfSprinter);
		System.out.println("Number of Super Athletes: " + noOfSuperAthlete);
		for(int i = 0; i < athleteParticipateList.size();i++){
			System.out.println(athleteParticipateList.get(i).getName());
		}
	}
	
	
	public void Run(){
		for(int i = 0; i < athleteParticipateList.size();i++){
			athleteParticipateList.get(i).compete();
		}
	}
	
}