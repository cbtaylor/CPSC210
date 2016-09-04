package ca.ubc.cpcs210.payment.model;

/**
 * A debit card payment with a bank.
 * @author Gail Murphy
 */
public class DebitCard extends ElectronicPayment {
	
	/**
	 * INVARIANT: In addition to superclasses invariants, cardNumber > 0 && pin > 0
	 */
	
	// The number of the debit card
	private int cardNumber;
	
	// The pin number for the debit card
	private int pin;
	
	/**
	 * Constructor. Remember the debit card information needed to process a payment.
	 * Not a very good class because it remembers the PIN of the debit card too - a privacy
	 * and security nightmare.
	 * 
	 */
	public DebitCard(int cardNumber, int pin) {
		this.cardNumber = cardNumber;
		this.pin = pin;
	}

	@Override
	public PaymentRecord processPayment(double amount) {
		generateTransactionNumber();
		// If the transaction number is divisable by 3, say an error occurred
		if (getTransactionNumber() % 3 == 0)
			return new PaymentRecord();
		else {
			// Contact debit card provider and actually do debit. 
			return new PaymentRecord("debit", amount, getTransactionNumber());
		}
	}

}
