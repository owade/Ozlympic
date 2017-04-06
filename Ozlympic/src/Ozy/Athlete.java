//Author: Felix Owade Otieno - s3579452

package Ozy;

public abstract class Athlete extends Person {
	private int point;
	private Double timeToComplete;
	
	public Athlete(int id, String name, int age, String state){
		super(id, name, age, state);
	}
	public int getPoint(){return point;}
	
	public Double getTimeToComplete(){return timeToComplete;}
	public void setTimeToComplete(Double timeToComplete){
		this.timeToComplete = timeToComplete;
	}
	public void setPoint(int point){
		this.point = point;
	}
	
	public abstract double complete();	
}