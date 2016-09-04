package app;

// A topping on the pizza

public abstract class Topping extends Ingredient implements Surchargeable {
	
	// An extra charge for toppings
	private int surcharge;

	@Override
	public void setSurcharge(int extraCharge) {
		surcharge = extraCharge;
	}
	
	@Override
	public int computeCost() {
		return surcharge * cost;
	}
	
}
