package Midterm;

import java.io.*;
import java.util.*;

public class Driver {

	static BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
	static MyStack<Request> pending = new MyStack<Request>();
	static QueueArrayBased<String> queue = new QueueArrayBased<String>();
	static ArrayList<Request> requests = new ArrayList<Request>();
	private static int apples;
	private static int pears;
	private static int peaches;
	private static int served = 0;
	
	public static void main(String[] args) throws IOException {
		welcome();
	}
	
	public static void welcome() throws IOException{
		System.out.print("Welcome to the Fruit Center!\n"
				+ "Enter number of apples in stock: ");
		String num = read.readLine().trim();
		if(!numCheck(num)){
			System.out.println("Please try again with a valid selection.\n");
			welcome();
		}
		apples = Integer.parseInt(num);
		
		System.out.print("Enter number of pears in stock: ");
		num = read.readLine().trim();
		if(!numCheck(num)){
			System.out.println("Please try again with a valid selection.\n");
			welcome();
		}
		pears = Integer.parseInt(num);
		
		System.out.print("Enter number of peaches in stock: ");
		num = read.readLine().trim();
		if(!numCheck(num)){
			System.out.println("Please try again with a valid selection.\n");
			welcome();
		}
		peaches = Integer.parseInt(num);
		
		System.out.println();
		menu();
	}
	
	public static void menu() throws IOException
	{
		System.out.print("Select from the following menu:"
				+ "\n\t1. Customer enters with a request to buy."
				+ "\n\t2. Customer enters with fruit to sell."
				+ "\n\t3. Customer with request to buy is served."
				+ "\n\t4. Display customers waiting to be served and their requests."
				+ "\n\t5. Display pending requests waiting to be processed."
				+ "\n\t6. Process pending requests."
				+ "\n\t7. Display the number of customers that have been served."
				+ "\n\t8. Exit."
				+ "\nMake your menu selection now: ");
		String numCheck = read.readLine().trim();
		if(numCheck.matches("[\\D]+") || (Integer.parseInt(numCheck)<1 || Integer.parseInt(numCheck)>8)){
			System.out.println("Please try again with a valid selection.\n");
			menu();
		}
		else{
			int num = Integer.parseInt(numCheck);
			System.out.println(num);
			switch(num) {
			case 1: buy();
				break;
			case 2: sell();
				break;
			case 3: serveBuy();
				break;
			case 4: displayAll();
				break;
			case 5:	displayRequests();
				break;
			case 6:	process();
				break;
			case 7: displayServed();
				break;
			case 8: System.out.println("Ending program...Good Bye"); System.exit(0);
				break;
			}
		}
	}

	private static void buy() throws IOException {
		System.out.print("Welcome! Your name, please: ");
		String name = read.readLine();
		System.out.println(name);
		System.out.print("How many apples do you want: ");
		String apples = read.readLine().trim();
		if(!numCheck(apples)){
			System.out.println("Please try again with a real number.\n");
			menu();
		}
		System.out.println(apples);
		System.out.print("How many pears do you want: ");
		String pears = read.readLine().trim();
		if(!numCheck(pears)){
			System.out.println("Please try again with a real number.\n");
			menu();
		}
		System.out.println(pears);
		System.out.print("How many peaches do you want: ");
		String peaches = read.readLine().trim();
		if(!numCheck(peaches)){
			System.out.println("Please try again with a real number.\n");
			menu();
		}
		System.out.println(peaches);
		System.out.println(name + " requesting " + apples + " apples, " + pears + " pears, and " + peaches + " peaches is now waiting.\n");
		queue.enqueue(name);
		Request request = new Request(name, Integer.parseInt(apples), Integer.parseInt(pears), Integer.parseInt(peaches));
		requests.add(request);;
		menu();
	}

	private static void sell() throws IOException {
		System.out.print("Welcome! Your name, please: ");
		String name = read.readLine();
		System.out.println(name);
		System.out.print("How many apples do you have: ");
		String apple = read.readLine().trim();
		if(!numCheck(apple)){
			System.out.println("Please try again with a real number.\n");
			menu();
		}
		System.out.println(apple);
		
		System.out.print("How many pears do you have: ");
		String pear = read.readLine().trim();
		if(!numCheck(pear)){
			System.out.println("Please try again with a real number.\n");
			menu();
		}
		System.out.println(pear);
		System.out.print("How many peaches do you have: ");
		String peach = read.readLine().trim();
		if(!numCheck(peach)){
			System.out.println("Please try again with a real number.\n");
			menu();
		}
		System.out.println(peach);
		System.out.println("Thanks " + name + ", here is your reciept for the  "
		+ apple + " apples, " + pear + " pears, and " + peach + " peaches!\n");
		apples += Integer.parseInt(apple);
		pears += Integer.parseInt(pear);
		peaches += Integer.parseInt(peach);
		menu();
	}

	private static void serveBuy() throws IOException {
		if(queue.isEmpty()){
			System.out.println("No customer is waiting to be served!\n");
			menu();
		}
		for(Request r : requests){
			if(queue.peek().equals(r.getName())){
				if(r.getApples() > apples || r.getPears() > pears || r.getPeaches() > peaches){
					System.out.println(r.getName() + "'s request for " + r.getApples() + "apples, "
							+ r.getPears() + " pears, and " + r.getPeaches() + " peaches is now pending."
							+ " Goodbye " + r.getName() + ". We will let you know when your "
							+ "request is processed!\n");
					queue.dequeue();
					pending.push(r);
					requests.remove(r);
					menu();
				}
				else{
					System.out.println(r.getName() + " is leaving with " + r.getApples() + " apples, "
					+ r.getPears() + " pears, and " + r.getPeaches() + "peaches.\n");
					apples -= r.getApples();
					pears -= r.getPears();
					peaches -= r.getPeaches();
					queue.dequeue();
					requests.remove(r);
					served++;
					menu();
				}
			}
		}
	}

	private static void displayAll() throws IOException {
		if(queue.isEmpty()){
			System.out.println("No customers waiting to be served!\n");
			menu();
		}
		for(Request r : requests){
			System.out.println(r.toString());
		}
		System.out.println();
		menu();
	}

	private static void displayRequests() throws IOException {
		if(pending.isEmpty()){
			System.out.println("No customer requests are pending!\n");
			menu();
		}
		System.out.println("The following request is pending:\n" + pending.peek().toString());
		System.out.println();
		menu();
	}

	private static void process() throws IOException {
		boolean end = false;
		if(pending.isEmpty()){
			end = true;
			System.out.println("No requests to process!");
		}
		while(!end){
			Request r = pending.peek();
			if(pending.peek().getApples() <= apples && pending.peek().getPears() <= pears && pending.peek().getPeaches() <= peaches){
				System.out.println(r.getName() + "'s request for " + r.getApples() + " apples, "
						+ r.getPears() + " pears, and " + r.getPeaches() + "peaches has been processed and the customer has been notified.\n");
						apples -= r.getApples();
						pears -= r.getPears();
						peaches -= r.getPeaches();
						pending.pop();
						served++;
			}
			else{
				end = true;
				System.out.println();
			}
		}
		menu();
	}

	private static void displayServed() throws IOException {
		if(served==0){
			System.out.println("No customers have been served yet.\n");
			menu();
		}
		System.out.println(served + " customers have been served.\n");
		menu();
	}
	
	private static boolean numCheck(String str){
		if(str.matches("[\\D]+") || (Integer.parseInt(str)<0)){
			return false;
		}
		return true;
	}
}