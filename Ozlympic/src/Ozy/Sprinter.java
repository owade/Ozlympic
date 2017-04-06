//Author: Felix Owade Otieno - s3579452
package Ozy;
import java.util.concurrent.ThreadLocalRandom;

public class Sprinter extends Athlete{
	
	public Sprinter(int id, String name, int age, String state){
		super(id, name, age, state);
	}
	
	public double complete(){
		//generate random random number between 10-20 
		double timeToComplete = ThreadLocalRandom.current().nextDouble(10, 21);
		return timeToComplete;
	}

}