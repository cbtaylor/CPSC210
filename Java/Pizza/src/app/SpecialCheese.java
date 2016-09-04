package app;

public class SpecialCheese extends Cheese {

	public SpecialCheese(String specialName) {
		// EFFECTS: Name is set to 'specialName cheese' where specialName is the value
		// of the parameter to the constructor
		name = specialName + " cheese";
	}
	
	@Override
	public int computeCost() {
		return cost + super.computeCost();
	}

}
