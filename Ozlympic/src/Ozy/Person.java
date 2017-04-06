//Author: Owade Felix - s3579452
package Ozy;

public class Person {
	private int id;
	private String name;
	private int age;
	private String state;
	
	public Person(int id, String name, int age, String state){
		this.id = id;
		this.name = name;
		this.age = age;
		this.state = state;
	}
	public Person(){
		
	}
	public int getId(){return id;}
	public String getName(){return name;}
	public int getAge(){return age;}
	public String getState(){return state;}
}