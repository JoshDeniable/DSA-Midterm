package Midterm;

public class Request {

	protected String name;
	protected int apples;
	protected int pears;
	protected int peaches;
	
	public Request(String name, int apple, int pear, int peach){
		this.name = name;
		apples = apple;
		pears = pear;
		peaches = peach;
	}

	public String getName() {
		return name;
	}
	public int getApples() {
		return apples;
	}
	public int getPears() {
		return pears;
	}
	public int getPeaches() {
		return peaches;
	}
	
	public String toString(){
		return this.name + " requesting " + apples + " apples, " + pears + " pears, and " + peaches + " peaches.";
	}
}
