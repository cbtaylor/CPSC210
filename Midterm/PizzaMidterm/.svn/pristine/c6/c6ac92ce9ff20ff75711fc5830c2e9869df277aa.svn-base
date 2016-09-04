package app;

import java.util.ArrayList;
import java.util.List;

// Represents a pizza

public class Pizza {
	
	// Every pizza must have a crust
	private Crust crust;
	
	// And can optionally have ingredients
	private List<Ingredient> ingredients;

	// Constructor
	public Pizza(Crust crust) {
		ingredients = new ArrayList<Ingredient>();
		this.crust = crust;
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
	//   all ingredients with " toppedBy " between each ingredient
	public String ingredientsAsString() {
		String returnString = crust.getName();
		for (Ingredient i: ingredients) {
			returnString = returnString + " toppedBy " + i.getName();
		}
		return returnString;
	}
	
	// Return the total cost calculated for the pizza
	// EFFECTS: Return the total cost of the pizza with crust and
	//   all ingredients. Total cost >= CRUSTCOST
	public int totalCost() {
		int costOfPizza = crust.computeCost();
		for (Ingredient i: ingredients) {
			costOfPizza = costOfPizza + i.computeCost();
		}
		return costOfPizza;
	}

}
