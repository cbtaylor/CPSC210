package ca.ubc.cpsc210.account.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ca.ubc.cpsc210.account.Account;

/*
 * NOTE: this is not a complete set of tests.
 * These tests serves to illustrate why REQUIRES clauses
 * don't lead to robust class design.
 */
public class AccountTests {
	private static final double DELTA = 0.000001;
	private Account a;
	
	@Before
	public void runBeforeTests() {
		a = new Account("Joe", 0.00);
	}
	
	@Test
	public void testDeposit() {
		// Let's ignore the precondition on the deposit
		// method and see what happens 
		// (test will pass but should it?)
		a.deposit(-1000000.00);
		assertEquals(-1000000.00, a.getBalance(), DELTA);
	}
	
	@Test
	public void testWithdraw() {
		// Let's ignore the requires clause on the withdraw method
		// - just how much cash can we withdraw from an account with zero balance?
		// (test will pass but should it?)
		a.withdraw(2000000.00);
		assertEquals(-2000000.00, a.getBalance(), DELTA);
	}
	
}
