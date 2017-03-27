package Ozy;


public class SuperAthlete extends Athlete{
	private Athlete ath;
	private Swimmer swimmer;
	private Sprinter sprinter = new Sprinter();
	private Cyclist cyclist;
	public String athleteType;

	public SuperAthlete(Swimmer swimmer, String athleteType,int id, String name, int age, String state){
		super(id, name, age, state);
		this.swimmer = swimmer;
	}

	public SuperAthlete(Cyclist cyclist, String athleteType,int id, String name, int age, String state){
		super(id, name, age, state);
		this.cyclist = cyclist;
		this.athleteType = athleteType;
	}
	
	public SuperAthlete(String athleteType, int id, String name, int age, String state){
		super(id, name, age, state);
		this.athleteType = athleteType;
	}
	
	public double complete(){
		if (athleteType == "sprinter"){
			System.out.println("I'm a Super Runner!");
			return sprinter.complete();
		}else if(athleteType == "cyclist"){
			System.out.println("I'm a Super Cyclist!");
			return this.cyclist.complete();
		}else if(athleteType == "swimmer"){
			System.out.println("I'm a Super Swimmer!");
			return this.swimmer.complete();
		}
		return 0;
	}
}
