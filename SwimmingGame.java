package Ozy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class SwimmingGame extends Game {
	public  int totalAthlete = super.genTotalAthelete();
	public  int noOfSwimmer = super.genNoOfNormalAthelete();
	public  int noOfSuperAthlete = super.calNoOfSuperAthlete();
	
	ArrayList<Athlete> swimmerList = new ArrayList<Athlete>();
	ArrayList<Athlete> superAthList = new ArrayList<Athlete>();
	
	public static HashMap<String, Integer> gameResultsList = new HashMap<String, Integer>();
	public static HashMap<String, Integer> AthResHash = new HashMap<String, Integer>();
	public static HashMap<String, Integer> pointsHash = new HashMap<String, Integer>();
    public static ArrayList<Athlete> athleteParticipateList = new ArrayList<Athlete>();
	public static ArrayList<String[]> gameStore = new ArrayList<String[]>();	
	//TO-DO
	//Sort athleteParticipateList according to their runtime
	//Pick 3 first swimmers
	//return to Official to summarize the game
//    public void getName(){
//    	addAthleteToItsList();
//    	pickAthlete();
//    	GetAthName();
//    }
    
    public void swimPreEvent(){
		addAthleteToItsList();
		pickAthlete();
		DisplaySwimmers();
	}
    
	public void swimEvent(){
		addAthleteToItsList();
		pickAthlete();
		Swim();
	}
	
	public void addAthleteToItsList(){
		
		Records rec = new Records();
		ArrayList <Athlete> list = rec.recordsKeeper("swimmer");
		for(int i = 0; i< list.size(); i++){
			//pick random noOfSprinter in Sprinter
			if(list.get(i) instanceof Swimmer){
				swimmerList.add(list.get(i));
			}
			//pick random noOfSuperAthlete in SuperAthlete
			if(list.get(i) instanceof SuperAthlete){
				superAthList.add(list.get(i));
			}
		}
	}
	
	public void pickAthlete(){
		//Pick random index form sprinterList
		final int[] indexOfSwimmers = new Random().ints(0, swimmerList.size()).distinct().limit(noOfSwimmer).toArray();
		//Pick random index from superAthleteList
		final int[] indexOfSuperAthlete = new Random().ints(0, superAthList.size()).distinct().limit(noOfSuperAthlete).toArray();
		
		//Get random sprinters from sprinterList based on random index array
		for(int i = 0; i < indexOfSwimmers.length; i++){
			athleteParticipateList.add(swimmerList.get(indexOfSwimmers[i]));
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
	public void DisplaySwimmers(){
		System.out.println("Total Participants: " + totalAthlete);
		System.out.println("Number of Actual Swimmers: " + noOfSwimmer);
		System.out.println("Number of Super Athletes: " + noOfSuperAthlete);
		for(int i = 0; i < athleteParticipateList.size();i++){
			System.out.println(athleteParticipateList.get(i).getName());
		}
	}
	
	
	public void Swim(){
		for(int i = 0; i < athleteParticipateList.size();i++){
			athleteParticipateList.get(i).compete();
		}
	}
}
