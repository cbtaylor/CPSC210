package ca.ubc.cpcs210.payment.model;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Represent a payment through an internet payment provider
 * @author Gail Murphy
 */
public interface InternetPayment {
	
	/**
	 * Return the URL for the provider on the internet
	 * 
	 * REQUIRES: nothing
	 * MODIFIES: nothing
	 * EFFECTS: the URL for the provider
	 */
	public URL retrieveURLForProvider() throws MalformedURLException;
}
