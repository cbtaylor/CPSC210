package ca.ubc.cpsc210.payment.application;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.MalformedInputException;

import ca.ubc.cpcs210.payment.model.ASIVCard;
import ca.ubc.cpcs210.payment.model.AuditTrail;
import ca.ubc.cpcs210.payment.model.Cash;
import ca.ubc.cpcs210.payment.model.DebitCard;
import ca.ubc.cpcs210.payment.model.InternetPayment;
import ca.ubc.cpcs210.payment.model.PalPay;
import ca.ubc.cpcs210.payment.model.PaymentRecord;
import ca.ubc.cpcs210.payment.model.SubordinateCard;

/**
 * Provide a sample use of the payment system.
 * 
 * @author Gail Murphy
 */
public class Main {

	/**
	 * Code that runs when you execute Main as a Java applications
	 */
	public static void main(String[] args) {
		// Ensure we have an audit trail
		AuditTrail trail = new AuditTrail();

		// Generate some sample payments of different types
		try {
			generateCreditCardPayments(trail);
			generateDebitCardPayments(trail);
			generateInternetPayments(trail);
			generateCashPayments(trail);
		} catch (Exception e) {
			// Terminate the program after writing a sort of nice error message.
			System.err.println("Unfortunately something went wrong. Quitting.");
			System.exit(-1);
		}

		// Print out the audit trail to see what happened
		for (PaymentRecord record : trail) {
			System.out.println(record.toString());
		}

	}

	/**
	 * Generate some pretend internet payments
	 * 
	 * @param auditTrail
	 *            The audit trail in which to place payment records
	 */
	private static void generateInternetPayments(AuditTrail auditTrail)
			throws MalformedURLException {
		for (int i = 0; i < 3; i++) {
			PalPay palPayPayment = new PalPay();
			PaymentRecord record = palPayPayment.processPayment(Math.random()
					* i);
			auditTrail.addPaymentRecord(record);

			InternetPayment internetPayment = palPayPayment;
			URL url = internetPayment.retrieveURLForProvider();
		}
	}

	/**
	 * Generate some pretend credit card payments
	 * 
	 * @param auditTrail
	 *            The audit trail in which to place payment records
	 */
	private static void generateCreditCardPayments(AuditTrail auditTrail) {

		int creditCardNum = 200;
		for (int i = 0; i < 8; i++) {
			if (i % 2 == 0) {
				SubordinateCard sCard = new SubordinateCard(200 + i, "Me",
						"02/10");
				PaymentRecord record = sCard.processPayment(Math.random() * i);
				auditTrail.addPaymentRecord(record);
			} else {
				ASIVCard aCard = new ASIVCard(200 + i, "Me", "02/10");
				Double amount = new Double(Math.random() * i);
				String amountAsString = amount.toString();
				PaymentRecord record = aCard.processPayment(amountAsString);
				auditTrail.addPaymentRecord(record);
			}
		}
	}

	private static void generateDebitCardPayments(AuditTrail auditTrail) {
		for (int i = 0; i < 5; i++) {
			DebitCard dCard = new DebitCard(i, i + 1);
			PaymentRecord record = dCard.processPayment(Math.random() * i);
			auditTrail.addPaymentRecord(record);
		}

	}

	private static void generateCashPayments(AuditTrail auditTrail) {
		for (int i = 0; i < 9; i++) {
			Cash c = new Cash();
			PaymentRecord record = c.processPayment(Math.random() * i);
			auditTrail.addPaymentRecord(record);
		}
	}

}
