package ca.ubc.cpcs210.payment.model;

/**
 * Represent a payment through the credit card company Subordinate
 * @author Gail Murphy
 */
public class SubordinateCard extends CreditCard {
	
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
	public SubordinateCard(int number, String name, String expiry) {
		cardNumber = number;
		cardHolderName = name;
		expiryDate = expiry;
	}
	
	@Override
	public PaymentRecord processPayment(double amount) {
		generateTransactionNumber();
		// We need to pretend some credit card transactions don't work. Take every 11th
		// payment to process and throw it away as an error
		if (getTransactionNumber() % 11 == 0)
			return new PaymentRecord();
		else
			return new PaymentRecord("subordinate", amount, getTransactionNumber());
	}

}
