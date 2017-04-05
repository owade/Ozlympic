package Ozy;

public abstract class Athlete extends Person {
	private int id;
	private String name;
	private int age;
	private String state;
	private static int points ;
	
	public Athlete(int id, String name, int age, String state , int points){
		this.id = id;
		this.name = name;
		this.age = age;
		this.state = state;
		Athlete.points = 0 ;
	}
	
	public Athlete(String name, int age, String state){
		this.name = name;
		this.age = age;
		this.state = state;
	}
	public Athlete(){
	}
	
	public int getId(){return id;}
	public String getName(){return name;}
	public int getAge(){return age;}
	public String getState(){return state;}
	
	public abstract int compete();
	
	//public abstract Double Complete();
}
