package cards;

public interface CreditCard {
	
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
	boolean authorizeAmount(double amount);
	
	/*
	 * Determine if card is in authorized state
	 * 
	 * EFFECTS: returns true if authorized, false otherwise.
	 */
	boolean isAuthorized();
	
	/*
	 * Gets authorized amount
	 * 
	 * EFFECTS: returns amount currently authorized
	 */
	double getAmountAuthorized();
	
	/*
	 * Complete an authorized payment by charging card.
	 * 
	 * REQUIRES: this is in authorized state
	 * MODIFIES: this
	 * EFFECTS: card charged for amount, this is no longer authorized to charge any amount
	 * and authorized amount is reset to zero.
	 */
	void completePayment();
}
