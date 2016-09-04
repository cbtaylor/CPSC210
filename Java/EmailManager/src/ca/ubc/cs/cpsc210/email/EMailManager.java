package ca.ubc.cs.cpsc210.email;

import java.util.List;

import ca.ubc.cs.cpsc210.addressbook.AddressBook;

/**
 * Represents an e-mail manager
 */
public class EMailManager {
	private AddressBook addressBook;
	private EMailClient mailClient;
	
	// constructor
	public EMailManager() {
		addressBook = new AddressBook();
		mailClient = new EMailClient();
	}
	
	public AddressBook getAddressBook() {
		return addressBook;
	}
	
	// send mail
	// EFFECTS: sends e-mail message msg to address book entry with given name;
	// if entry is a group, sends message to everyone in the group
	public void sendMail(String name, String msg) {
		List<String> addresses = addressBook.getAddressesForEntry(name);
		
		for(String address : addresses) {
			mailClient.sendMail(address, msg);
		}
	}
}
