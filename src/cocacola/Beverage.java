package cocacola;

public class Beverage {
	
	private String name;
	private int price;
	
	
	public int getPrice() {
		return price;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String inputName) {
		name = inputName;
	}
	
	public void setPrice (int inputPrice) {
		if(inputPrice <= 0) {
			System.out.println("This price is inappropriate...");
			return;
		}
		
		price = inputPrice;
	}

	
}
