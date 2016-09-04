package app;

public class GlutenFreeCrust extends Crust implements Surchargeable {
	
	// Constructor
	// EFFECTS: The name is set to gluten free crust
	public GlutenFreeCrust(){
		super();
		name = "gluten free crust";
		cost = 20;
	}

	@Override
	public void setSurcharge(int extraCharge) {
		cost = cost + extraCharge;

	}

}
