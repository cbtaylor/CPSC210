package ca.ubc.cpcs210.payment.model;

import java.util.Date;

/**
 * Document a payment
 * 
 * @author Gail Murphy
 */
public class PaymentRecord {

	/**
	 * INVARIANT: (error == true ** paymentNumber > 0) || 
	 *   (error == false && timeOfPayment is a valid date && 
	 *    time && typeOfPayment is a non-null value and typeOfPayment.length() > 0 && 
	 *    amountOfPayment > 0 && paymentNumber > 0)
	 */

	// Time payment was recorded
	private Date timeOfPayment;

	// Type of payment as string for now for simplicity.
	private String typeOfPayment;

	// Amount of payment
	private double amountOfPayment;

	// A unique number within the payment system to uniquely identify this
	// payment
	private int paymentNumber;

	// A transaction number possibly provided by the payment provider
	private int transactionNumber;

	// An error has occurred
	private boolean error;

	// Generate a unique payment number. Do not worry if you do not understand
	// the static modifier. You do not need to know how the unique payment
	// number
	// is generated ,just that paymnetNumber (above) will always get a unique
	// number.
	private static int currentPaymentNumber = 0;

	/**
	 * Constructor. Construct a payment record for a payment type that does not
	 * involve a transaction number from the payment provider.
	 * 
	 * REQUIRES: type is not null and type.length() > 0 && amount > 0.0
	 * MODIFIES: this 
	 * EFFECTS: timeOfPayment set to current time &&
	 *   typeOfPayment is set to type && amountOfPayment set to amount && 
	 *   error is set to false && paymentNumber > 0 && transactionNumber is set to -1
	 *   because it is not applicable
	 */
	public PaymentRecord(String type, double amount) {
		timeOfPayment = new Date();
		typeOfPayment = type;
		amountOfPayment = amount;
		transactionNumber = -1;
		generatePaymentNumber();
	}

	/**
	 * Constructor. Construct a payment record for a payment type that involves
	 * a transaction number from the payment provider.
	 * 
	 * REQUIRES: type is not null and type.length() > 0 && amount > 0.0 &&
	 *   txNum > 0 
	 * MODIFIES: this 
	 * EFFECTS: timeOfPayment set to current time &&
	 *   typeOfPayment is set to type && amountOfPayment set to amount && 
	 *   error is set to false && paymentNumber > 0 && transactionNumber is set to txNum
	 */
	public PaymentRecord(String type, double amount, int txNum) {
		timeOfPayment = new Date();
		typeOfPayment = type;
		amountOfPayment = amount;
		transactionNumber = txNum;
		generatePaymentNumber();
	}

	/**
	 * Constructor. Construct a payment record for a payment that was not
	 * successful.
	 * 
	 * REQUIRES: nothing 
	 * MODIFIES: this 
	 * EFFECTS: error is set to true
	 */
	public PaymentRecord() {
		error = true;
		generatePaymentNumber();
		typeOfPayment = new String("");
	}

	/**
	 * Determine if the payment record indicates an error occurred.
	 * 
	 * REQUIRES: nothing 
	 * MODIFIES: nothing 
	 * EFFECTS: returns whether an error is indicated on the payment record
	 */
	public boolean inError() {
		return error;
	}

	/**
	 * Getter 
	 * EFFECTS: Returns time payment occurred
	 */
	public Date getTimeOfPayment() {
		return timeOfPayment;
	}

	/**
	 * Getter 
	 * EFFECTS: Returns type of payment. Type is specified by payment provider.
	 */
	public String getTypeOfPayment() {
		return typeOfPayment;
	}

	/**
	 * Getter 
	 * EFFECTS: Returns amount of payment.
	 * 
	 * @return
	 */
	public double getAmountOfPayment() {
		return amountOfPayment;
	}

	/**
	 * Getter 
	 * EFFECTS: Returns unique number identifying payment within system
	 */
	public int getPaymentNumber() {
		return paymentNumber;
	}

	/**
	 * Getter 
	 * EFFECTS: REturns transaction number from payment provider
	 */
	public int getTransactionNumber() {
		return transactionNumber;
	}
	
	/**
	 * Return a string representation of the PaymentRecord
	 * REQUIRES: nothing
	 * MODIFIES: nothing
	 * EFFECTS: returns a string representation of the PaymentRecord
	 */
	public String toString() {
		String repesentationAsString = new String("Payment[ num=");
		if (typeOfPayment.equals("Cash"))
			repesentationAsString = repesentationAsString.concat(paymentNumber-100 + ", ");
		else 
			repesentationAsString = repesentationAsString.concat(paymentNumber + ", ");
		if ( error ) {
			repesentationAsString = repesentationAsString.concat("ERROR" + "]");
		} else {
			repesentationAsString = repesentationAsString.concat("type=" + typeOfPayment + ", ");
			repesentationAsString = repesentationAsString.concat("amt=" + amountOfPayment);
			if ( transactionNumber > 0 )
				repesentationAsString = repesentationAsString.concat(", " + "txNum=" + transactionNumber);
			repesentationAsString = repesentationAsString.concat("]");
		}
		return repesentationAsString;
	}

	/**
	 * Helper to set payment number. REQUIRES: nothing MODIFIES: this EFFECTS:
	 * paymentNumber > 0 and unique amongst all payment numbers issued
	 */
	private void generatePaymentNumber() {
		paymentNumber = ++currentPaymentNumber;
	}

}
