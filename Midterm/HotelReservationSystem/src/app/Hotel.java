package app;
import java.util.HashSet;
import java.util.Set;

/**
 * Represent a hotel with lots of rooms
 */
public class Hotel {

	// The rooms in the hotel
	Set<Room> rooms;

	// Constructor
	public Hotel() {
		rooms = new HashSet<Room>();
	}

	// Add a room to the hotel with certain charge rates
	// REQUIRES: r is not null AND a room with r.getNumber() is not currently in hotel
	// MODIFIES: this
	// EFFECTS: The room is added to the hotel
	public void addRoom(Room r) {
		rooms.add(r);
	}

	// Hold an arbitrary room
	// EFFECTS: If returned room is non-null, the room is available for booking.
	// If no room is available, returns null
	public Room holdRoom() {
		Room availableRoom = null;
		for (Room room: rooms) {
			if (!room.isAssigned()) {
				availableRoom = room;
				// The next statement exits the for loop as we have found a room
				break;
			}
		}
		return availableRoom;
	}

	// Book a given guest into a given room
	// REQUIRES: guest is not null and room is not null and room.isAssigned() == false
	// EFFECTS: guest.getRoom() == room and room.isAssigned() == true
	public void bookRoom(Guest guest, Room room) {
		room.assign(guest);
		guest.assign(room);
	}

	// Release a room as a guest is no longer occupying it
	// REQUIRES: guest is not null and room is not null AND room.isAssigned() == true and
	//   guest.getRoom() == room 
	// EFFECTS: room.isAssigned() == false and guest.getRoom() == null
	public void releaseRoom(Guest guest, Room room) {
		guest.leave(room);
	}

}
