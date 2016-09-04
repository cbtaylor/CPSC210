package tests;

import cards.CreditCard;
import cards.SubordinateCard;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import cards.ASIVCard;
public class CreditCardTests {

	private static final double DELTA = 0.000001;
	private CreditCard aCreditCard; //DECLARATION sets up
								//APPARENT TYPE.
	
	@Before
	public void beforeRunningTest() {
		aCreditCard = new ASIVCard();  //ACTUAL TYPE.
									//runtime type
									//set upon instantiation.
		aCreditCard.authorizeAmount(100.00);
		//aCreditCard.expressAuthorize(); //will no longer work!
	}
	
	/*
	 * Test authorizeAmount when parameter in bounds
	 * and card is not currently authorized
	 */
	@Test
	public void testAuthorizeAmount() {
		assertTrue(aCreditCard.authorizeAmount(100.0));
		assertTrue(aCreditCard.isAuthorized());
		assertEquals(100.0, aCreditCard.getAmountAuthorized(), DELTA);
		aCreditCard.completePayment();
		
		assertTrue(aCreditCard.authorizeAmount(750.0));
		assertTrue(aCreditCard.isAuthorized());
		assertEquals(750.0, aCreditCard.getAmountAuthorized(), DELTA);		
	}
	
	/*
	 * Test authorizeAmount when card already authorized
	 */
	@Test
	public void testAuthorizePaymentWhenAuthorized() {
		assertTrue(aCreditCard.authorizeAmount(150.0));
		assertFalse(aCreditCard.authorizeAmount(200.0));
	}
	
	/*
	 * Test completePayment
	 */
	@Test
	public void testCompletePayment() {
		assertTrue(aCreditCard.authorizeAmount(150.0));
		aCreditCard.completePayment();
		assertFalse(aCreditCard.isAuthorized());
		assertEquals(0.0, aCreditCard.getAmountAuthorized(), DELTA);
	}
}
