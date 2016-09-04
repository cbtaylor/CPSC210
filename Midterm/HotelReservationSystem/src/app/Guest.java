package app;

/**
 * Represent a room guest
 */
public class Guest {

	// The room the guest is occupying (we'll ignore the dates for now!)
	private Room room;

	// The credit card the guest uses to pay for the room
	private CreditCard creditCard;

	// The name of the guest
	private String name;

	// Constructor
	// REQUIRES: name is not null and cc is not null
	// EFFECT: name and cc are remembered
	public Guest(String name, CreditCard cc) {
		this.name = name;
		creditCard = cc;
	}

	// Assign the guest to a specific room
	// REQUIRES: room is not null
	// MODIFIES: this
	// EFFECTS: guest is assigned the given room
	public void assign(Room room) {
		this.room = room;
	}

	// The guest is no longer occupying the room
	// REQUIRES: room is not null and room is the room assigned to the guest
	// MODIFIES: this
	// EFFECTS: guest is no longer occupying the room
	public void leave(Room room) {
		room.release();
		this.room = null;
	}

	// *************** Getters *****************

	public CreditCard getCreditCard() {
		return creditCard;
	}

	public Room getRoom() {
		return room;
	}

	public String getName() {
		return name;
	}
}
