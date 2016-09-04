package app;

// Represent an ingredient for a pizza

public abstract class Ingredient implements Chargeable {
	
	// The name of the ingredient
	protected String name;
	
	// The cost of the ingredient
	protected int cost;
	
	// Return the name of the ingredient
	// EFFECTS: Returns the name of the ingredient
	public String getName() {
		return name;
	}
	
	@Override
	public void setCost(int cost) {
		this.cost = cost;
	}

	@Override
	public int computeCost() {
		return cost;
	}
}
