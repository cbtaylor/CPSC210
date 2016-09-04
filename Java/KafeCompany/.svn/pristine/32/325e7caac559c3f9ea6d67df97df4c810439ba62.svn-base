package ca.ubc.cs.cpsc210.kafe;

/**
 * Represents the Kafe company's loyalty card
 * 
 */
public class CoffeeCard {
	// number of beans needed to earn a free drink
	public static final int BEANS_PER_FREE_DRINK = 9;  
	
	private int credits;     // number of credits on card
	private int beans;       // number of beans earned
	private int freeDrinks;  // number of free drinks available on card
	
	// constructor
	// EFFECTS: coffee card has no credits, no free drinks and one bean
	public CoffeeCard() {
		credits = 0;
		freeDrinks = 0;
		beans = 1;
	}
	
	public int getCredits() {
		return credits;
	}
	
	public int getFreeDrinks() {
		return freeDrinks;
	}
	
	public int getBeans() {
		return beans;
	}
	
	// use free drink credit
	// MODIFIES: this
	// EFFECTS: if number of free drinks is greater than zero, free drink is used and true returned;
	// otherwise false returned
	public boolean useFreeDrink() {
		if (freeDrinks > 0) {
			freeDrinks--;
			return true;
		}
		
		return false;
	}
		
	// top up credits
	// REQUIRES: numCredits > 0
	// MODIFIES: this
	// EFFECTS: adds numCredits to number of credits available on this card
	public void topUp(int numCredits) {
		credits += numCredits;
	}
	
	// purchase a drink
	// REQUIRES: numCredits > 0
	// MODIFIES: this
	// EFFECTS: if card does not have at least numCredits available, returns false;
	// otherwise deducts numCredits from credits available, updates number of beans and number
	// of free drinks available and returns true.
	public boolean purchaseDrink(int numCredits) {
		return false;  // STUB
	}
}
