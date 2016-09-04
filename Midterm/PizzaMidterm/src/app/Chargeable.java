package app;

// Enables an item to have a cost

public interface Chargeable {

	// Set the cost of the item
	// REQUIRES: cost >= 0
	// MODFIIES: this (when implemented by a class)
	// EFFECTS: the cost is maintained by this
	public void setCost(int cost);

	// Returns the cost of this item
	// EFFECTS: returns the cost of the item and cost >= 0
	public int computeCost();

}
