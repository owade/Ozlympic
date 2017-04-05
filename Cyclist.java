package Ozy;

import java.util.Random;

public class Cyclist extends Athlete {

	public Cyclist(int id, String name, int age, String state,int points){
		super(id, name, age, state,points);
	}
    
	public Cyclist(){
		
	}
	public int compete() {
		Random ran = new Random();
		return 500 + ran.nextInt(301);
	}

}
