package Ozy;

import java.util.Random;

public class Sprinter extends Athlete{
	
	public Sprinter(int id, String name, int age, String state,int points){
		super(id, name, age, state,points);
	}
	public Sprinter(){
	}
	
	public int compete(){
	  Random ran = new Random();
	  return 10 + ran.nextInt(11);
	}
}
