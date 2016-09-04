package app;

/**
 * Represent a room
 */
public class Room {

	// The room number
	private int number;

	// The rate to charge out the room
	private double rate;

	// The guest occupying the room
	private Guest guest;

	// Constructor
	// REQUIRES: number > 0 and rate > 0
	// EFFECTS: Remember the room number and rate
	public Room(int number, double rate) {
		this.number = number;
		this.rate = rate;
		guest = null;
	}

	// Assign a particular guest to the room
	// REQUIRES: guest is not null and isAssigned() == false
	// MODIFIES: this
	// EFFECTS: isAssigned() == true and guest is remembered
	public void assign(Guest guest) {
		this.guest = guest;
	}

	// Release the guest from the room
	// REQUIRES: isAssigned() == true
	// MODIFIES: this
	// EFFECTS: isAssigned() == false
	public void release() {
		guest = null;
	}

	// Is the room assigned to a guest or available?
	// EFFECTS: true if room is assigned, false otherwise
	public boolean isAssigned() {
		if (guest == null)
			return false;
		return true;
	}

	// ********** Getters ***************

	public double getRate() {
		return rate;
	}
	
	public int getNumber() {
		return number;
	}

	// ************ hashCode() and equals() *************

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + number;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Room other = (Room) obj;
		if (number != other.number)
			return false;
		return true;
	}

}
