package ca.ubc.cpsc210.account.tests;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import ca.ubc.cpsc210.account.RobustAccount;
import ca.ubc.cpsc210.account.exceptions.IllegalValueException;
import ca.ubc.cpsc210.account.exceptions.NotEnoughMoneyException;

/*
 * NOTE: this is not a complete test suite.  It serves
 * only to illustrate how to test that a method
 * throws an exception of a particular type and how
 * to test methods that catch exceptions.
 */
public class RobustAccountTests {
	
	private RobustAccount a;
	
	@Before
	public void beforeEachTest() {
		a = new RobustAccount("Joe", 100.0);
	}
	
	@Test (expected = NotEnoughMoneyException.class)
	public void withdrawTooMuchMoney() 
			throws IllegalValueException, NotEnoughMoneyException {
		a.withdraw(101.50);
		fail("If we get to this point, something is wrong - exception should have been thrown");
	}
	
	@Test (expected = IllegalValueException.class)
	public void withdrawNegativeAmount() 
			throws IllegalValueException, NotEnoughMoneyException {
		a.withdraw(-100.0);
		fail("If we get to this point, something is wrong - exception should have been thrown");
	}
	
	@Test
	public void callWithdrawTooMuchMoney() {
		try {
			withdrawMoney(101.50);
			System.out.println("Yay, I withdrew a negative amount and got away with it!");
			fail("Shouldn't have got to this point!");
		} catch (IllegalValueException e) {
			System.out.println(e.getMessage());
		} catch (NotEnoughMoneyException e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("Should print EVERY time, no matter what!");
		}
	}
	
	public void withdrawMoney(double amount) throws IllegalValueException, NotEnoughMoneyException {
		try {
			a.withdraw(amount);
		} finally {
			System.out.println("Should print EVERY time, no matter what!");
		}
	}
}
