package ca.ubc.cs.cpsc210.addressbook;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents an address book for e-mail addresses
 *
 */
public class AddressBook {
	private Set<Entry> entries;
	
	// constructor
	// EFFECTS: address book has no entries
	public AddressBook() {
		entries = new HashSet<Entry>();
	}
	
	// add a new contact
	// MODIFIES: this
	// EFFECTS: adds a new contact with given name and e-mail address, if there isn't
	// already an entry with the given name; if an entry with the given name already
	// exists, it's replaced with the new contact.
	public void addContact(String name, String eMailAddress) {
		Contact c = new Contact(name, eMailAddress);
		
		checkDuplicate(name);
		
		entries.add(c);
	}
	
	// add a new group
	// MODIFIES: this
	// EFFECTS: adds a new group with the given name containing the selected list of entries,
	// if there isn't already an entry with the given name; if an entry with the given
	// name already exists, it's replaced with the new group.
	public void addGroup(String name, List<Entry> selected) {
		Group g = new Group(name);  
		
		checkDuplicate(name);
		
		for(Entry next : selected) {
			g.addEntry(next);
		}
		
		entries.add(g);
	}
	
	// get list of e-mail addresses for entry with given name.
	// EFFECTS: if address book has an entry with the given name, returns a list of
	// e-mail addresses for that entry (will contain single address if entry is a Contact or
	// address for every member of group if entry is a Group); otherwise returns null
	public List<String> getAddressesForEntry(String name) {
		for(Entry next : entries) {
			String nextName = next.getName();
			if (nextName.equals(name))
				return next.getAddressList();
		}
		
		return null;
	}
	
	// how many entries in this address book?
	// EFFECTS: returns number of entries in this address book
	public int getNumEntries() {
		return entries.size();
	}
	
	// do we already have an entry with given name?
	// MODIFIES: this
	// EFFECTS: if address book has an entry with given name, remove it
	private void checkDuplicate(String name) {
		// use a Contact to check for duplicate but could use a Group
		Contact c = new Contact(name); 
		
		if (entries.contains(c))
			entries.remove(c);
	}
}
