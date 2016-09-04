package test;

import static org.junit.Assert.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.junit.*;

import app.*;

// Test the Pizza class

public class PizzaTest {


	@Test
	public void testPizzaWithGFCrust() {
		GlutenFreeCrust crust = new GlutenFreeCrust();
		crust.setCost(20);
		crust.setSurcharge(20);
		Pizza pizza = new Pizza(crust);
		assertEquals(40, pizza.totalCost());
	}

	
}
