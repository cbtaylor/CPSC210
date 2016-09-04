package ca.ubc.cpcs210.payment.model;

/** 
 * Represent a certain kind of credit card called a ASIVCard
 * @author Gail Murphy
 */
public class ASIVCard extends CreditCard {
	
	/**
	 *  INVARIANT: In addition to superclasses invariants,
	 *   cardNumber is > 0 && cardHolderName is not null && cardHolderName.length() > 0
	 *  && expiryDate is a legal DDMM
	 */
	
	// The number of the card
	private int cardNumber;
	
	// The name of the cardholder
	private String cardHolderName;
	
	// The expiry date as DDMM
	private String expiryDate;
	
	/**
	 * Constructor. 
	 * REQUIRES: number > 0 && name is not null and name.length() > 0 and expiry is in 
	 *    correct format.
	 * MODIFIES: this
	 * EFFECTS: cardNumber is set to number and cardHolderName is set to name and
	 *   expiryDate is set to expiry
	 */
	public ASIVCard(int number, String name, String expiry) {
		cardNumber = number;
		cardHolderName = name;
		expiryDate = expiry;
	}

	@Override
	public PaymentRecord processPayment(double amount) {
		generateTransactionNumber();
		// We need to pretend some credit card transactions don't work. Take every 4th
		// payment to process and throw it away as an error
		if (getTransactionNumber() % 4 == 0)
			return new PaymentRecord();
		else
			return new PaymentRecord("asiv", amount, getTransactionNumber());
	}
	
	/**
	 * Process a given payment.
     * REQUIRES: amount > 0
     * MODIFIES: nothing
     * EFFECTS: returns a record of the payment which might indicate an error if one occurred
	 */
	public PaymentRecord processPayment(String amount) {
		Double d = new Double(amount);
		return processPayment(d);
	}

}
