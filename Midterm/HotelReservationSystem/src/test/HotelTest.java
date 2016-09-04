package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import app.CreditCard;
import app.Guest;
import app.Hotel;
import app.Room;

public class HotelTest {

	// The hotel to test
	private Hotel hotel;

	@Before
	public void runBefore() {
		hotel = new Hotel();
		// Make a boutique hotel with three rooms
		hotel.addRoom(new Room(1, 120.0));
		hotel.addRoom(new Room(1, 130.0));
		hotel.addRoom(new Room(1, 140.0));
	}

	@Test
	public void testMakeReservationShouldWork() {
		// Hold a room, one should be available
		Room room = hotel.holdRoom();
		assertTrue(room != null);
		// Create a guest for the room
		Guest guest = new Guest("Elisa", new CreditCard(500));
		// Approve the payment for the room
		CreditCard c = guest.getCreditCard();
		c.approvePayment(room.getRate());
		// Book the room
		hotel.bookRoom(guest, room);
		c.applyPayment(room.getRate());
	}

	@Test
	public void testMakeReservationOverLimit() {

		// THIS TEST SHOULD NOT PASS AS THE ROOM IS MORE THAN THE
		// CREDITCARD LIMIT. HOW CAN YOU MAKE CREDIT CARD MORE ROBUST
		// TO SIGNAL THE ERROR AND HOW SHOULD THIS TEST CHANGE?

		// Hold a room, one should be available
		Room room = hotel.holdRoom();
		assertTrue(room != null);
		// Create a guest for the room
		Guest guest = new Guest("Elisa", new CreditCard(100));
		// Approve the payment for the room
		CreditCard c = guest.getCreditCard();
		c.approvePayment(room.getRate());
		// Book the room
		hotel.bookRoom(guest, room);
		c.applyPayment(room.getRate());
	}

	@Test
	public void testAssignAGuestAnUnassignedRoom() {
		// Hold a room, one should be available
		Room room = hotel.holdRoom();
		assertTrue(room != null);
		// Create a guest for the room
		Guest guest = new Guest("Gail", new CreditCard(500));
		// Book the room
		hotel.bookRoom(guest, room);
	}

	@Test
	public void testAssignAGuestAnAssignedRoom() {
		
		// THIS TEST SHOULD NOT PASS AS THE ROOM IS ALREADY ASSIGNED.
		//  HOW CAN YOU MAKE HOTEL MORE ROBUST TO SIGNAL THE ERROR AND 
		// HOW SHOULD THIS TEST CHANGE?
		
		// Hold a room, one should be available
		Room room = hotel.holdRoom();
		assertTrue(room != null);
		// Create a guest for the room
		Guest guest1 = new Guest("Gail", new CreditCard(500));
		// Book the room
		hotel.bookRoom(guest1, room);
		// Create a second guest
		Guest guest2 = new Guest("Elisa", new CreditCard(1000));
		// Book the same room
		hotel.bookRoom(guest2, room);
	}

}
