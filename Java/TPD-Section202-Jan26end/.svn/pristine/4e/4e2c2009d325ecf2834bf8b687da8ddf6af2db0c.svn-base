package cards;

public class SubordinateCard implements CreditCard {

	private boolean authorized;
	private double amountAuthorized;
	
	/*
	 * Authorize a charge for a specified amount, if it 
	 * is not already in authorized state.
	 * 
	 * REQUIRES: amount >= 100
	 * MODIFIES: this
	 * EFFECTS: this is in authorized state for a certain amount;
	 * returns true if amount is authorized, false if card is already
	 * in authorized state.
	 */
	@Override
	public boolean authorizeAmount(double amount) {
		//We can only authorize the mount if the card
		//is NOT currently waiting to process an
		//authorized payment
		if (!authorized) {
			amountAuthorized = amount;
			authorized = true;
			return true;
		}
		return false;
	}

	@Override
	public boolean isAuthorized() {
		return authorized;
	}

	@Override
	public double getAmountAuthorized() {
		return amountAuthorized;
	}

	@Override
	public void completePayment() {
		authorized=false;
		amountAuthorized=0.0;
	}

}
