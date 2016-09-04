package ca.ubc.cs.cpsc210.meetup.test;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Set;

import org.junit.Test;

import ca.ubc.cs.cpsc210.meetup.model.Place;
import ca.ubc.cs.cpsc210.meetup.model.PlaceFactory;
import ca.ubc.cs.cpsc210.meetup.parser.PlacesParser;

public class PlaceTest {
	
	// The file to find the data
	private static String PLACESFILENAME = "data/Places.json";

	@Test
	public void testPlacesParsing() {
		FileReader input;
		try {
			// Parse the data
			input = new FileReader(PLACESFILENAME);
			PlacesParser parser = new PlacesParser();
			parser.parse(input);

			// Check the input is correctly parsed
			Set<Place> places = PlaceFactory.getInstance().get("Koerner's Pub");
			assertTrue(places != null);
			assertTrue(places.size() == 1);
			places = PlaceFactory.getInstance().get("Starbucks");
			assertTrue(places != null);
			assertTrue(places.size() == 1);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
