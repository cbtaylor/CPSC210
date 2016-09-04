package ca.ubc.cpcs210.payment.model;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * A provider on the internet called PalPay.
 * @author Gail Murphy
 */
public class PalPay extends ElectronicPayment implements InternetPayment {
	
	@Override
	public URL retrieveURLForProvider() throws MalformedURLException {
		return new URL ("http://www.palpay.something");
	}

	@Override
	public PaymentRecord processPayment(double amount) {
		generateTransactionNumber();
		return new PaymentRecord("PalPay", amount, getTransactionNumber());
	}



}
