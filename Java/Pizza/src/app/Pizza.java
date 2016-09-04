package app;

import java.util.ArrayList;
import java.util.List;

// Represents a pizza

public class Pizza {

	// Set the cost of a crust
	static private int CRUSTCOST = 5;

	// Every pizza must have a crust
	private Crust crust;

	// And can optionally have ingredients
	private List<Ingredient> ingredients;
	
	private int totalCost;

	// Constructor
	public Pizza() {
		// TODO: Complete the implementation of this method
		crust = new Crust();
		ingredients = new ArrayList<Ingredient>();
		totalCost = CRUSTCOST;
	}

	// Add an ingredient
	// REQUIRES: newIngredient is not null
	// MODIFIES: this
	// EFFECTS: newIngredient is added to end of list of ingredients
	public void addIngredient(Ingredient newIngredient) {
		ingredients.add(newIngredient);
	}

	// Returns all of the ingredients on pizza, including crust, as a a string
	// EFFECTS: the returned string starts with "crust" followed by
	// all ingredients with " toppedBy " between each ingredient
	public String ingredientsAsString() {
		String output = "crust";
		for ( Ingredient topping : ingredients ) {
			output += " toppedBy ";
			output += topping.getName();
		}
		return output;
	}

	// Return the total cost calculated for the pizza
	// EFFECTS: Return the total cost of the pizza with crust and
	// all ingredients. Total cost >= CRUSTCOST
	public int totalCost() {
		for ( Ingredient topping : ingredients ) {
			totalCost += topping.computeCost();
		}
		return totalCost;
	}

}
