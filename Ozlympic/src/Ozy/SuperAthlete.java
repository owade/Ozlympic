//Author: Thao Hoang - s3393317
package Ozy;

import java.util.concurrent.ThreadLocalRandom;

public class SuperAthlete extends Athlete{
	public String athleteType;
	
	public SuperAthlete(String athleteType, int id, String name, int age, String state){
		super(id, name, age, state);
		this.athleteType = athleteType;
	}
	
	//Depend on Athlete type and generate different timeToComplete
	public double complete(){
		double timeToComplete;
		if (athleteType == "sprinter"){
			timeToComplete = ThreadLocalRandom.current().nextDouble(10, 21);
			return timeToComplete;
		}else if(athleteType == "cyclist"){
			timeToComplete = ThreadLocalRandom.current().nextDouble(500, 801);
			return timeToComplete;
		}else if(athleteType == "swimmer"){
			timeToComplete = ThreadLocalRandom.current().nextDouble(100, 201);
			return timeToComplete;
		}
		return 0;
	}
}
