package app;

public abstract class Topping extends Ingredient implements Surchargeable {

	// A surcharge for topping above the cost
	private int surcharge;
	
	@Override
	public void setSurcharge(int extraCharge) {
		surcharge = extraCharge;

	}
	
	@Override
	public int computeCost() {
		return cost * surcharge;
	}
		
}
