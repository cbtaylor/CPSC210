package ca.ubc.cs.cpsc210.meetup.parser;

import java.io.Reader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import ca.ubc.cs.cpsc210.meetup.model.EatingPlace;
import ca.ubc.cs.cpsc210.meetup.model.PlaceFactory;
import ca.ubc.cs.cpsc210.meetup.util.LatLon;

/**
 * Foursquare location result parse (JSON)
 */
public class PlacesParser {

	/**
	 * Parse JSON from Foursquare output stored into a file REQUIRES: input is a
	 * file with valid data EFFECTS: parsed data is put into PlaceFactory
	 */

	public PlacesParser() {
		// constructor
	}

	public void parse(Reader input) {
		// TODO: Complete the implementation of this method. Handle any
		// exceptions by printing out the stack trace


		PlaceFactory placeFactory = PlaceFactory.getInstance();
		
		JSONTokener obj = new JSONTokener(input);

		try {
			while (obj.more()) {
				JSONObject jObj = (JSONObject) obj.nextValue();
				JSONObject response = jObj.getJSONObject("response");
				JSONArray venues = response.getJSONArray("venues");
				//System.out.println("length: " + venues.length());
				for (int i=0; i<venues.length(); i++) {
					JSONObject kp = venues.getJSONObject(i);
					//System.out.println(kp.getString("name"));
					JSONObject location = kp.getJSONObject("location");
					//System.out.println(location.getDouble("lat"));
					//System.out.println(location.getDouble("lng"));
					LatLon l = new LatLon(location.getDouble("lat"), 
							location.getDouble("lng"));
					EatingPlace ep = new EatingPlace(kp.getString("name"), l);
					placeFactory.add(ep);
				}

			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
