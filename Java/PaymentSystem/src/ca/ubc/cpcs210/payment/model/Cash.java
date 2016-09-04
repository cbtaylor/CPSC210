package ca.ubc.cpcs210.payment.model;

/**
 * A cash payment.
 * @author Gail Murphy
 */
public class Cash implements Payment {

	@Override
	public PaymentRecord processPayment(double amount) {
		return new PaymentRecord("Cash", amount);	
	}

}
