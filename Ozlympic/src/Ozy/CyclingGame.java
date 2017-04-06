//Author: Thao Hoang - 3393317
package Ozy;

import java.util.ArrayList;

public class CyclingGame extends Game{
	public CyclingGame(String id, int officialID) {
		super(id, officialID);
	}
	ArrayList<Athlete> cyclistList = new ArrayList<Athlete>();
	ArrayList<Athlete> superAthList = new ArrayList<Athlete>();
	
	ArrayList<Athlete> athleteParticipateList = new ArrayList<Athlete>();
	//Select random athletes and display
	public void DisplayParticipantsList(){
		super.addAthleteToItsList(cyclistList,superAthList,Cyclist.class,"cyclist");
		super.pickAthlete(cyclistList,superAthList,athleteParticipateList);
		super.GetAthName(athleteParticipateList);
	}
	//Run the game
	public void StartGame(){
		super.Run(athleteParticipateList);
	}
}
