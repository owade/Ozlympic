package Ozy;

import java.util.Random;

public class Swimmer extends Athlete {
	
	public Swimmer(int id, String name, int age, String state,int points){
		super(id, name, age, state,points);
	}
	
	public Swimmer(){
	}
	
    public int compete(){
      Random ran = new Random();
  	  return 100 + ran.nextInt(101);
    }
}
