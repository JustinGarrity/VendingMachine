package vdcom;

import java.util.Scanner;

public class VendingMachineController {
	
	private int currentMenu;
	private int selection;
	private VendingMachine controlledVM;
	
	private static VendingMachineController instance;
	private VendingMachineController() {
		currentMenu = 0;
		selection = 0;
		controlledVM = VendingMachine.getInstance();
		
	}
	
	public static VendingMachineController getInstance() {
		if(instance == null)
			instance = new  VendingMachineController();
		return instance;
		
	}
	
	public void controlVM() {
		Scanner scanner = new Scanner(System.in);
		int selection;
		
		controlLoop:
		while(true) {
			System.out.print("\n===== Select menu =====");
			controlledVM .printMenu();
			selection = scanner.nextInt();
			
			switch(controlledVM.getStatus()) {
			case 0:
				if(selection == 3)break controlLoop;
				if(selection > 0 && selection < 3)
					controlledVM.setStatus(selection);
				break;
			case 1:
				if(selection == 1 ) controlledVM.insertCoin();
				else if(selection == 2)controlledVM.returnChange();
				else if(selection == 3)controlledVM.getItemOut();
				else if(selection == 4)controlledVM.listItems();
				else controlledVM.setStatus(0);
				break;
				
			case 2:
				if(selection == 1 ) controlledVM.addItem();
				else if(selection == 2)controlledVM.removeItem();
				else if(selection == 3)controlledVM.refillInventory();
				else if(selection == 4)controlledVM.listItems();
				else controlledVM.setStatus(0);
				break;
				
			
			}
			
		}
	}

}
