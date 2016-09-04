package app;

// Represent special cheese that costs more

public class SpecialCheese extends Cheese {
	
	// Constructor
	// EFFECTS: Name is set to "specialName cheese" and surcharge is initialized
	public SpecialCheese(String specialName) {
		super();
		name = specialName + " " + name;
	}
	
	@Override
	public int computeCost() {
		return super.computeCost() + cost;
	}

}
