//Author: Thao Hoang - s3393317
package Ozy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Official extends Person {

	public Official(int id, String name, int age, String state) {
		super(id, name, age, state);
	}

	public Official() {
	}

	public void SummarizeScore(ArrayList<Athlete> ath) {

		Collections.sort(ath, new Comparator<Athlete>() {
			@Override
			public int compare(Athlete ath1, Athlete ath2) {
				return ath1.getTimeToComplete().compareTo(ath2.getTimeToComplete());
			}
		});

	}

	// For Final display points of all athlete
	public void SummarizeFinalScore(ArrayList<Athlete> ath) {

		Collections.sort(ath, Collections.reverseOrder(new Comparator<Athlete>() {
			@Override
			public int compare(Athlete ath1, Athlete ath2) {
				Integer p1 = ath1.getPoint();
				Integer p2 = ath2.getPoint();
				return p1.compareTo(p2);
			}
		}));

	}

	// Get the Winner to compare with the user prediction
	public int getWinnerId(ArrayList<Athlete> ath) {
		return ath.get(0).getId();
	}

	// Display the top 3 winners of the game and give reward scores for the top 3 winner
	public void DisplayTop3Winner(ArrayList<Athlete> ath, GameEvent oz) {
		// Sort the list by complete Time then get the first 3 object in the
		// list which will be the top 3 winners
		SummarizeScore(ath);
		System.out.println("The Top 3 winners:");
		for (int i = 0; i < oz.getParticipantList().size(); i++) {

			if (oz.getParticipantList().get(i).getName() == ath.get(0).getName()) {
				//Fist winner
				System.out.println("1st: " + ath.get(0).getName());
				oz.getParticipantList().get(i).setPoint(oz.getParticipantList().get(i).getPoint() + 5);
			}
			if (oz.getParticipantList().get(i).getName() == ath.get(1).getName()) {
				//Second winner
				System.out.println("2st: " + ath.get(1).getName());
				oz.getParticipantList().get(i).setPoint(oz.getParticipantList().get(i).getPoint() + 2);
			}
			if (oz.getParticipantList().get(i).getName() == ath.get(2).getName()) {
				//Third winner
				System.out.println("3st: " + ath.get(2).getName());
				oz.getParticipantList().get(i).setPoint(oz.getParticipantList().get(i).getPoint() + 1);
			}
		}
		//Add the sorted list to event
		oz.getWinnersList().add(ath);
	}

}