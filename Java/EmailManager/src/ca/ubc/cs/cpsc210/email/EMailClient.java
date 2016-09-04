package ca.ubc.cs.cpsc210.email;

/**
 * A simple e-mail client for sending mail
 */
public class EMailClient {
	
	// send mail
	// EFFECTS: if address is valid, msg sent to address
	public void sendMail(String address, String msg) {
		if (isValid(address))
			processMail(address, msg);	
	}
	
	// process mail
	// EFFECTS: send msg to address
	private void processMail(String address, String msg) {
		//TODO: implement send mail
	}
	
	// is address valid?
	// EFFECTS: returns true if address is a valid e-mail address
	private boolean isValid(String address) {
		// very simple validation mechanism
		return address.contains("@");
	}
}
