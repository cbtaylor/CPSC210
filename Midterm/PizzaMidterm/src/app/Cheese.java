package app;

// Represent the cheese for a pizza

public class Cheese extends Ingredient implements Surchargeable {

	// A surcharge for cheese above the cost
	private int surcharge;

	// Constructor
	// EFFECTS: name is set to cheese and surcharge is set to 0
	public Cheese() {
		name = "cheese";
		surcharge = 0;
	}

	@Override
	public void setSurcharge(int extraCharge) {
		surcharge = extraCharge;
	}

	@Override
	public int computeCost() {
		return surcharge;
	}

}
