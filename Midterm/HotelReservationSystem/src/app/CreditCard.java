package app;


/**
 * Represent a credit card to use to pay for rooms, etc.
 */
public class CreditCard {

	// The limit of value of items that can be charged
	private double chargeLimit;

	// Current charges
	private double charges;

	// Constructor
	// REQUIRES: limit > 0
	// EFFECTS: The limit of the value of items to charge is remembered
	public CreditCard(double limit) {
		chargeLimit = limit;
		charges = 0;
	}

	// Approves the payment of a specified amount
	// REQUIRES: amount > 0 and (getCharges() + amount) < chargeLimit
	// MODIFIES: this
	// EFFECTS: getCharges() == getCharges() before method + amount
	public void approvePayment(double amount) throws DeclineChargeException {
		if (getCharges() + amount > chargeLimit)
			throw new DeclineChargeException();
		charges = charges + amount;
	}

	// Apply a payment against the charges
	// REQUIRES: amount > 0 AND amount <= getCharges()
	// MODIFIES: this
	// EFFECTS: getCharges() == getCharges() before method call - amount
	public void applyPayment(double amount) {
		charges = charges - amount;
	}

	// *************** Getters ****************

	public double getCharges() {
		return charges;
	}

	public double getChargeLimit() {
		return chargeLimit;
	}

}
