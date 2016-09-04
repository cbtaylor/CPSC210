package ca.ubc.cpcs210.payment.model;

/**
 * Represent an electronic payment. All electronic payments have transaction
 * numbers.
 * 
 * @author Gail Murphy
 */
public abstract class ElectronicPayment implements Payment {
	
	/**
	 * INVARIANT: transactionNumber > 0
	 */

	// Represent a unique transaction number. If you don't understand the static
	// modifer, don't worry - just understand transaction number will be unique
	// within the system.
	private int transactionNumber;
	private static int currentTransactionNumber = 0;

	/**
	 * Retrieve the transaction number for the payment.
	 * 
	 * REQUIRES: nothing
	 * MODIFIES: nothing
	 * RETURNS: the transaction number for the payment
	 */
	public int getTransactionNumber() {
		return transactionNumber;
	}

	/**
	 * A transaction number should be returned by the payment processor but for now
	 * we'll just make a number to represent the transaction.
	 * 
	 * REQUIRES: nothing
	 * MODIFIES: this
	 * EFFECTS: transactionNumber is set to a unique number and is greater than 0
	 */
	void generateTransactionNumber() {
		currentTransactionNumber = currentTransactionNumber + 1;
		transactionNumber = currentTransactionNumber;
	}

}
