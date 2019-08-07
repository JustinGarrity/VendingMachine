package runner;

import vdcom.VendingMachineController;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("\nStart Vending Machine Program...");
		
		VendingMachineController.getInstance().controlVM();
		
		System.out.println("\nExit Vending Machine Program");
		
	

	}

}
