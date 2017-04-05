package Ozy;

import java.util.ArrayList;

public class Records {
	public static String[] officials = {"James","Peter","Luke","Joseph","Mark","Luke","John"};
	public ArrayList<Athlete> recordsKeeper(String SuperAlthType){
		ArrayList <Athlete> ath = new ArrayList<Athlete>();	
		//Sprinter records
		ath.add(new Sprinter(1,"Acton",20,"VIC",0));
		ath.add(new Sprinter(2,"Babs",21,"NSW",0));
		ath.add(new Sprinter(3,"Cade",19,"NT",0));
		ath.add(new Sprinter(4,"Cadman",22,"AAT",0));
		ath.add(new Sprinter(5,"Cadoc",23,"VIC",0));
		ath.add(new Sprinter(6,"Dado",24,"VIC",0));
		ath.add(new Sprinter(7,"Dacey",21,"NSW",0));
		ath.add(new Sprinter(8,"Dabria",23,"VIC",0));
		ath.add(new Sprinter(9,"Dada",25,"QLD",0));
		ath.add(new Sprinter(10,"Eadric",26,"VIC",0));
		//Swimmer records
		ath.add(new Swimmer(11,"Eachann",21,"VIC",0));
		ath.add(new Swimmer(12,"Eagle",23,"VIC",0));
		ath.add(new Swimmer(13,"Fabio",18,"WA",0));
		ath.add(new Swimmer(14,"Fai",27,"VIC",0));
		ath.add(new Swimmer(15,"Gabby",24,"SA",0));
		ath.add(new Swimmer(16,"Bable",23,"VIC",0));
		ath.add(new Swimmer(17,"Gael",25,"VIC",0));
		ath.add(new Swimmer(18,"Hackett",22,"QLD",0));
		ath.add(new Swimmer(19,"Hadwin",25,"VIC",0));
		ath.add(new Swimmer(20,"Hades",21,"NSW",0));
		//Cyclist records
		ath.add(new Cyclist(21,"Iakopa",24,"VIC",0));
		ath.add(new Cyclist(22,"Ian",26,"JBT",0));
		ath.add(new Cyclist(23,"Ianthe",19,"VIC",0));
		ath.add(new Cyclist(24,"Ib",23,"SA",0));
		ath.add(new Cyclist(25,"Ibbie",22,"VIC",0));
		ath.add(new Cyclist(26,"Ibrahim",29,"VIC",0));
		ath.add(new Cyclist(27,"Icarus",24,"WA",0));
		ath.add(new Cyclist(28,"Dabria",25,"VIC",0));
		ath.add(new Cyclist(29,"Ichabod",25,"QLD",0));
		ath.add(new Cyclist(30,"Ichiro",22,"VIC",0));
		//Super Athlete records
		ath.add(new SuperAthlete(SuperAlthType,31,"Jaakko",25,"VIC",0));
		ath.add(new SuperAthlete(SuperAlthType,32,"Jabaru",24,"ACT",0));
		ath.add(new SuperAthlete(SuperAlthType,33,"Jabez",22,"VIC",0));
		ath.add(new SuperAthlete(SuperAlthType,34,"Jabir",25,"JBT",0));
		ath.add(new SuperAthlete(SuperAlthType,35,"Jac",23,"VIC",0));
		ath.add(new SuperAthlete(SuperAlthType,36,"Jacinta",24,"VIC",0));
		ath.add(new SuperAthlete(SuperAlthType,37,"Jack",21,"QLD",0));
		ath.add(new SuperAthlete(SuperAlthType,38,"Jackie",23,"NSW",0));
		ath.add(new SuperAthlete(SuperAlthType,39,"Pace",25,"VIC",0));
		ath.add(new SuperAthlete(SuperAlthType,40,"Paco",26,"VIC",0));
		
		return ath;
	}
	
}