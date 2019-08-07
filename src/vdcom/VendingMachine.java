package vdcom;

import java.util.Scanner;

import cocacola.Beverage;

public class VendingMachine {
	
	private int  deposit;
	private Beverage[] items;
	private int [] inventory;
	private int numOfItems;
	private int maxItems;
	private int status;
	
	private static VendingMachine instance;
	private VendingMachine() {
		maxItems = 11;
		deposit = 0;
		items = new Beverage[maxItems];
		
		for (int i = 0 ; i < maxItems; i++) {
			items[i] = new Beverage();
			
		}
		
		inventory = new int[maxItems];
		numOfItems = 0;
		status = 0;
		
	}
	
	public static VendingMachine getInstance() {
		if(instance == null)
			instance = new VendingMachine();
		
		return instance;
	}
	
	public int getStatus() {
		return status;
		
	}
	
	public boolean setStatus (int inputStatus) {
		if(inputStatus < 0 || inputStatus > 2 ) {
			System.out.println("This menu is not allowed...");
			return false;
		}
		
		status = inputStatus;
		return  true;
		
	}
	
	public void insertCoin() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("insert coin(1~10000: ");
		int amount = scanner.nextInt();
		
		if (amount <= 0 || amount > 10000) {
			System.out.println("insert a proper coin");
			return;
		}
		
		deposit += amount;
	}
	
	public int returnChange() {
		int returnedChange = deposit;
		System.out.println("returned change: " + returnedChange);
		deposit = 0;
		
		return returnedChange;
	}
	
	public void getItemOut() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Input the item's name: ");
		String name = scanner .nextLine();
		
		int target = isThere(name);
		if(target == -1) return;
		
		if(deposit < items[target].getPrice()) {
			System.out.println("You should put more coins...");
			return;
		}
		
		if(inventory[target] <= 0) {
			System.out.println("It is out of stock now...");
			return;
		}
		
		deposit -= items[target].getPrice();
		inventory[target]--;
		System.out.println(items[target].getName()+ " is got out");
		return;
		
		
	}
	
	public void addItem() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Input the name of an item to add: ");
		String name = scanner.nextLine();
		System.out.print("Input the price of the item: ");
		int price = scanner.nextInt();
		
		if(numOfItems == maxItems) {
			System.out.println("The inventory is full...");
			return;
		}
		
		inventory[numOfItems] = 0;
		items[numOfItems].setName(name);
		items[numOfItems].setPrice(price);
		numOfItems++;
	}
	
	public void removeItem() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Inout the name of an item ot remove: ");
		String name = scanner.nextLine();
		
		int target = isThere(name);
		if(target == -1 )return;
		
		for(int i = target; i < maxItems - 1; i++) {
			items[i]= items[i +1];
			inventory[i] = inventory[i+1];
			
		}
		numOfItems--;
		System.out.println(name + " was removed... ");
		return;
	}
	
	public void refillInventory() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Input the name of an item to refill its inventory: ");
		String name = scanner.nextLine();
		System.out.print("Input the quantity fo the item to refill: ");
		int quantity = scanner.nextInt();
		
		if(quantity <= 0) {
			System.out.println("This quantity is inappropriate");
			return;
		}
		
		int target = isThere(name);
		if(target == -1 ) return;
		inventory[target] += quantity;
		System.out.println("The refilled quantity is " + inventory[target]);
		return;
		
	}
	
	private int isThere(String name) {
		if(numOfItems == 0) {
			System.out.println("There is no item in the inventory");
			return -1;
			
		}
		
		int target = -1;
		for(int i = 0; i < numOfItems; i++) {
			if(name.contentEquals(items[i].getName()))
				target =i;
			
		}
		if(target == -1) {
			System.out.println("There is no item named as " + name);
			return -1;
		}
		return target;
			
	}
	
	public void listItems() {
		if(numOfItems == 0 ) {
			System.out.println("There is no item in the invenotry");
			return;
		}
		
		System.out.println("Product name 	Price 	Inventory");
		for(int i = 0; i < numOfItems; i++)
			System.out.println(items[i].getName() + "  	" + items[i].getPrice() + " 	"+ inventory[i]);
		
	}
	
	public void printMenu() {
		if(status == 0) {
			System.out.println("-----Initial Menu-----");
			System.out.println("(1) Start As an User");
			System.out.println("(2) Start As an Admin");
			System.out.println("(3) to quit");
		}
		else if(status == 1) {
			System.out.println("-----User Menu-----");
			System.out.println("	Current deposit: " + deposit);
			System.out.println("(1) Insert Coin");
			System.out.println("(2) Return Change");
			System.out.println("(3) Get an Item");
			System.out.println("(4) List Items");
			System.out.println("(else) Return to Initial Menu");
		}
		else if(status == 2) {
			System.out.println("-----Admin Menu-----");
			System.out.println("(1) Add an Item");
			System.out.println("(2) Remove an Item");
			System.out.println("(3) Refill Invenotroy");
			System.out.println("(4) List Items");
			System.out.println("(else) Return to Initial Menu");
		}
	}

}
