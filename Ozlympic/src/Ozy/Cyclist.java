//Author: Felix Owade Otieno
package Ozy;

import java.util.concurrent.ThreadLocalRandom;

public class Cyclist extends Athlete{
	
	public Cyclist(int id, String name, int age, String state){
		super(id, name, age, state);
	}
	public double complete(){
		//generate random random number between 500-800
		double timeToComplete = ThreadLocalRandom.current().nextDouble(500, 801);
		return timeToComplete;
	}
}