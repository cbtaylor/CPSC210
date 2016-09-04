package ca.ubc.cs.cpsc210.meetup.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class App {

	public static void main(String[] args) {
		String stringToParse = "";
		try {
			stringToParse = makeRoutingCall("https://api.foursquare.com/v2/venues/search?client_id=TC1RUUASWT4QHUANH4BUG0RBQOMPUXRJ234LXDJAKDRHOPJE&client_secret=1KOLNAGLIUJ0QDMVW3NH21TTEHYCZ1LRSTJ4DXLOQSOAALYI&v=20130815&ll=49.261474,-123.248060&query=coffee&limit=5");
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			System.out.println("malformed");
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println("IO error");
			e1.printStackTrace();
		}
		
		System.out.println("string is: " + stringToParse);
		PlaceFactory placeFactory = PlaceFactory.getInstance();

		JSONTokener tokener = new JSONTokener(stringToParse);

		try {
			while (tokener.more()) {
				
				JSONObject jObj = (JSONObject) tokener.nextValue();
				System.out.println(jObj.getString("FirstName"));
				System.out.println(jObj.getString("LastName"));
				System.out.println(jObj.getString("Id"));
				
				JSONArray sections = jObj.getJSONArray("Sections");
				
				for (int i=0; i<sections.length(); i++) {
					JSONObject section = sections.getJSONObject(i);
					System.out.println(section.get("CourseName"));
					System.out.println(section.get("CourseNumber"));
					System.out.println(section.get("SectionName"));
					System.out.println("-----");
				}
				
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
    private static String makeRoutingCall(String httpRequest) throws MalformedURLException, IOException {
        URL url = new URL(httpRequest);
        HttpURLConnection client = (HttpURLConnection) url.openConnection();
        InputStream in = client.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String returnString = "";
        while (br.ready()) {
            returnString += br.readLine();
            System.out.println("another line");
        }

        client.disconnect();
        return returnString;
    }
}
