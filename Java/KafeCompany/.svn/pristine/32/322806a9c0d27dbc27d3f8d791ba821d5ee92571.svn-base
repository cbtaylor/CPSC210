package ca.ubc.cs.cpsc210.kafe.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

import ca.ubc.cs.cpsc210.kafe.CoffeeCard;

public class CoffeeCardTests {
	private CoffeeCard card;
	
	@Before
	public void runBefore() {
		card = new CoffeeCard();
	}
	
	@Test
	public void testConstructor() {
		assertEquals(0, card.getCredits());
		assertEquals(0, card.getFreeDrinks());
		assertEquals(1, card.getBeans());
	}
	
	@Test
	public void testTopUp() {
		card.topUp(5);
		assertEquals(5, card.getCredits());
	}
	
	@Test 
	public void testPurchaseNoCredits() {
		assertFalse(card.purchaseDrink(1));
	}
	
	@Test
	public void testPurchaseNotEnoughCredits() {
		// add 1 credit, try to purchase 2-credit drink
		card.topUp(1);
		assertFalse(card.purchaseDrink(2));
	}
	
	@Test
	public void testPurchase() {
		card.topUp(5);
		assertTrue(card.purchaseDrink(5));
	}
	
	@Test
	public void testMultiplePurchase() {
		card.topUp(5);
		assertTrue(card.purchaseDrink(1));
		assertTrue(card.purchaseDrink(4));
		assertFalse(card.purchaseDrink(1));
	}
	
	@Test
	public void testEarnFreeDrink() {
		// add enough credits to purchase enough drinks to earn a free one
		card.topUp(CoffeeCard.BEANS_PER_FREE_DRINK);
		
		// buy one drink less than number needed to earn a free one
		// remember that we start with one bean
		for (int i = 0; i < CoffeeCard.BEANS_PER_FREE_DRINK - 2; i++) {
			assertTrue(card.purchaseDrink(1));
		}
		
		assertEquals(0, card.getFreeDrinks());
		
		// buy one more drink
		assertTrue(card.purchaseDrink(1));
		
		assertEquals(1, card.getFreeDrinks());
		
		// add enough credits to purchase enough drinks to earn another free one
		card.topUp(CoffeeCard.BEANS_PER_FREE_DRINK);
				
		// buy enough drinks to earn another free one
		for (int i = 0; i < CoffeeCard.BEANS_PER_FREE_DRINK; i++) {
			assertTrue(card.purchaseDrink(1));
		}		
		
		assertEquals(2, card.getFreeDrinks());
	}
	
}
