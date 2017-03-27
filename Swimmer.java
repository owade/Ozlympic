package Ozy;

import java.util.concurrent.ThreadLocalRandom;

public class Swimmer extends Athlete{
	
	public Swimmer(int id, String name, int age, String state){
		super(id, name, age, state);
	}
	public double complete(){
		//generate random random number between 100-200
		double timeToComplete = ThreadLocalRandom.current().nextDouble(100, 201);
		return timeToComplete;
	}
}