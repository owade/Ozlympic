package Ozy;


public class SuperAthlete extends Athlete{
	private Athlete ath;
	private Swimmer swimmer = new Swimmer();
	private Sprinter sprinter = new Sprinter();
	private Cyclist cyclist = new Cyclist();
	public String athleteType;

//	public SuperAthlete(Swimmer swimmer, String athleteType,int id, String name, int age, String state){
//		super(id, name, age, state);
//		this.swimmer = swimmer;
//	}

//	public SuperAthlete(Cyclist cyclist, String athleteType,int id, String name, int age, String state){
//		super(id, name, age, state);
//		this.cyclist = cyclist;
//		this.athleteType = athleteType;
//	}
	
	public SuperAthlete(String athleteType, int id, String name, int age, String state,int points){
		super(id, name, age, state,points);
		this.athleteType = athleteType;
	}
	
	public int compete(){
		if (athleteType == "sprinter"){
			//System.out.println("I'm a Super Runner!");
			return sprinter.compete();
		}else if(athleteType == "cyclist"){
			//System.out.println("I'm a Super Cyclist!");
			return this.cyclist.compete();
		}else if(athleteType == "swimmer"){
			//System.out.println("I'm a Super Swimmer!");
			return this.swimmer.compete();
		}
		return 0;
	}
}