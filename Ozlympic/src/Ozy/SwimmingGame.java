//Author: Thao Hoang - s3393317
package Ozy;

import java.util.ArrayList;

public class SwimmingGame extends Game{
	public SwimmingGame(String id, int officialID) {
		super(id, officialID);
	}
	ArrayList<Athlete> swimmerList = new ArrayList<Athlete>();
	ArrayList<Athlete> superAthList = new ArrayList<Athlete>();
	
	private ArrayList<Athlete> athleteParticipateList = new ArrayList<Athlete>();
	public ArrayList<Athlete> getAthleteParticipantList(){
		return athleteParticipateList;
	}
	//Select random athletes and display
	public void DisplayParticipantsList(){
		super.addAthleteToItsList(swimmerList,superAthList,Swimmer.class,"swimmer");
		super.pickAthlete(swimmerList,superAthList,athleteParticipateList);
		super.GetAthName(athleteParticipateList);
	}
	//Run the game
	public void StartGame(){
		super.Run(athleteParticipateList);
	}
}
