package test;

import static org.junit.Assert.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.junit.*;

import app.*;

// Test the Pizza class

public class PizzaTest {

	// A pizza to use
	private Pizza pizza;

	@Before
	public void runBefore() {
		pizza = new Pizza();
	}

	@Test
	public void testEmptyPizzaCost() {
		assertEquals(5, pizza.totalCost());
	}

	@Test
	public void testEmptyPizzaName() {
		assertEquals("crust", pizza.ingredientsAsString());
	}

	@Test
	public void testPizzaWithJustSauceDifferingCostsOfSauce() {
		testPizzaWithJustSauce(2);
		// Need a new pizza between the two cost tests
		pizza = new Pizza();
		testPizzaWithJustSauce(10);
	}

	// A helper method for testing
	private void testPizzaWithJustSauce(int costOfSauce) {
		int costOfEmptyPizza = pizza.totalCost();
		Sauce sauce = new Sauce();
		sauce.setCost(costOfSauce);
		pizza.addIngredient(sauce);
		assertEquals(costOfEmptyPizza + costOfSauce, pizza.totalCost());
		assertEquals("crust toppedBy sauce", pizza.ingredientsAsString());
	}

	@Test
	public void testPizzaWithSauceAndCheeseDifferingCostsAndNames() {
		int costOfEmptyPizza = pizza.totalCost();
		Sauce sauce = new Sauce();
		sauce.setCost(5);
		pizza.addIngredient(sauce);
		Cheese cheese = new Cheese();
		cheese.setSurcharge(12);
		pizza.addIngredient(cheese);
		assertEquals(costOfEmptyPizza + 5 + 12, pizza.totalCost());
		assertEquals("crust toppedBy sauce" + " toppedBy cheese",
				pizza.ingredientsAsString());
	}

	@Test
	public void testPizzaWithSauceAndSpecialCheeseDifferingCostsAndNames() {
		testPizzaWithSauceAndSpecialCheese(5, 10, 10, "feta");
		pizza = new Pizza();
		testPizzaWithSauceAndSpecialCheese(10, 20, 10, "feta");
		pizza = new Pizza();
		testPizzaWithSauceAndSpecialCheese(10, 20, 10, "cheddar");
	}

	// A helper method for testing
	private void testPizzaWithSauceAndSpecialCheese(int costOfSauce,
			int cheeseSurcharge, int costOfCheese, String cheeseName) {
		int costOfEmptyPizza = pizza.totalCost();
		Sauce sauce = new Sauce();
		sauce.setCost(costOfSauce);
		pizza.addIngredient(sauce);
		SpecialCheese cheese = new SpecialCheese(cheeseName);
		cheese.setSurcharge(cheeseSurcharge);
		cheese.setCost(costOfCheese);
		pizza.addIngredient(cheese);
		assertEquals(costOfEmptyPizza + costOfSauce + costOfCheese
				+ cheeseSurcharge, pizza.totalCost());
		assertEquals("crust toppedBy sauce" + " toppedBy " + cheeseName
				+ " cheese", pizza.ingredientsAsString());
	}

	@Test
	public void testPizzaWithSauceCheeseAndOneTopping() {
		Topping topping = new Tomatoes();
		testPizzaWithSauceCheeseAndOneTopping(5, 10, 10, "feta", topping, 2, 2);
	}

	// A helper method for testing
	private void testPizzaWithSauceCheeseAndOneTopping(int costOfSauce,
			int cheeseSurcharge, int costOfCheese, String cheeseName,
			Topping topping, int toppingCost, int surchargeCost) {
		int costOfEmptyPizza = pizza.totalCost();
		Sauce sauce = new Sauce();
		sauce.setCost(costOfSauce);
		pizza.addIngredient(sauce);
		SpecialCheese cheese = new SpecialCheese(cheeseName);
		cheese.setSurcharge(cheeseSurcharge);
		cheese.setCost(costOfCheese);
		pizza.addIngredient(cheese);
		topping.setSurcharge(surchargeCost);
		topping.setCost(toppingCost);
		pizza.addIngredient(topping);
		assertEquals(costOfEmptyPizza + costOfSauce + costOfCheese
				+ cheeseSurcharge + (surchargeCost * toppingCost),
				pizza.totalCost());
		assertEquals("crust toppedBy sauce" + " toppedBy " + cheeseName
				+ " cheese" + " toppedBy " + topping.getName(),
				pizza.ingredientsAsString());
	}

}
