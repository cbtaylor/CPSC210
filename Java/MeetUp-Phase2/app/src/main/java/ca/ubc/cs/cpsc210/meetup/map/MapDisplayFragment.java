package ca.ubc.cs.cpsc210.meetup.map;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.osmdroid.DefaultResourceProxyImpl;
import org.osmdroid.ResourceProxy;
import org.osmdroid.api.IGeoPoint;
import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.OverlayManager;
import org.osmdroid.views.overlay.PathOverlay;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;

import ca.ubc.cs.cpsc210.meetup.R;
import ca.ubc.cs.cpsc210.meetup.exceptions.IllegalCourseTimeException;
import ca.ubc.cs.cpsc210.meetup.model.Building;
import ca.ubc.cs.cpsc210.meetup.model.Course;
import ca.ubc.cs.cpsc210.meetup.model.CourseFactory;
import ca.ubc.cs.cpsc210.meetup.model.EatingPlace;
import ca.ubc.cs.cpsc210.meetup.model.Place;
import ca.ubc.cs.cpsc210.meetup.model.PlaceFactory;
import ca.ubc.cs.cpsc210.meetup.model.Section;
import ca.ubc.cs.cpsc210.meetup.model.Student;
import ca.ubc.cs.cpsc210.meetup.model.StudentManager;
import ca.ubc.cs.cpsc210.meetup.util.CourseTime;
import ca.ubc.cs.cpsc210.meetup.util.LatLon;
import ca.ubc.cs.cpsc210.meetup.util.SchedulePlot;


/**
 * Fragment holding the map in the UI.
 */
public class MapDisplayFragment extends Fragment {

    /**
     * Log tag for LogCat messages
     */
    private final static String LOG_TAG = "MapDisplayFragment";

    /**
     * Preference manager to access user preferences
     */
    private SharedPreferences sharedPreferences;

    /**
     * 3
     * String to know whether we are dealing with MWF or TR schedule.
     * You will need to update this string based on the settings dialog at appropriate
     * points in time. See the project page for details on how to access
     * the value of a setting.
     */
    private String activeDay = "MWF";
    private String activeTime = "12";
    private String activeDistance = "500";
    private String activeFoodChoice = "coffee";

    /**
     * A central location in campus that might be handy.
     * Adjusted from (49.264865,-123.252782) so that the fountain
     * is reasonably close to the center of the map
     */
    private final static GeoPoint UBC_MARTHA_PIPER_FOUNTAIN = new GeoPoint(49.270865,
            -123.258782);

    /**
     * Meetup Service URL
     * CPSC 210 Students: Complete the string.
     */
    private final String getStudentURL = "http://kramer.nss.cs.ubc.ca:8081/getStudent";

    /**
     * FourSquare URLs. You must complete the client_id and client_secret with values
     * you sign up for.
     */
    private static String FOUR_SQUARE_URL = "https://api.foursquare.com/v2/venues/explore?";
    private static String FOUR_SQUARE_CLIENT_ID = "TC1RUUASWT4QHUANH4BUG0RBQOMPUXRJ234LXDJAKDRHOPJE";
    private static String FOUR_SQUARE_CLIENT_SECRET = "1KOLNAGLIUJ0QDMVW3NH21TTEHYCZ1LRSTJ4DXLOQSOAALYI";

    /**
     * MapQuest URL, key and options
     */
    private static String MAPQUEST_URL = "http://open.mapquestapi.com/directions/v2/route?key=";
    private static String MAPQUEST_KEY = "Fmjtd%7Cluu82luy2u%2Can%3Do5-948ada";
    private static String MAPQUEST_OPTIONS = "&outFormat=xml&routeType=pedestrian&shapeFormat=raw&generalize=10";


    /**
     * Overlays for displaying my schedules, buildings, etc.
     */
    private List<PathOverlay> scheduleOverlay;
    private ItemizedIconOverlay<OverlayItem> buildingOverlay;
    private OverlayItem selectedBuildingOnMap;

    /**
     * View that shows the map
     */
    private MapView mapView;

    /**
     * Access to domain model objects. Only store "me" in the studentManager for
     * the base project (i.e., unless you are doing bonus work).
     */
    private StudentManager studentManager = new StudentManager();
    private Student randomStudent = null;
    private static int ME_ID = 999999;
    private Student me;


    /**
     * Map controller for zooming in/out, centering
     */
    private IMapController mapController;

    // ******************** Android methods for starting, resuming, ...

    // You should not need to touch this method
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getActivity());
        scheduleOverlay = new ArrayList<PathOverlay>();

        // You need to setup the courses for the app to know about. Ideally
        // we would access a web service like the UBC student information system
        // but that is not currently possible
        initializeCourses();

        // Initialize the data for the "me" schedule. Note that this will be
        // hard-coded for now
        initializeMySchedule();

        // You are going to need an overlay to draw buildings and locations on the map
        buildingOverlay = createBuildingOverlay();
    }

    // You should not need to touch this method
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK)
            return;
    }

    // You should not need to touch this method
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (mapView == null) {
            mapView = new MapView(getActivity(), null);

            mapView.setTileSource(TileSourceFactory.MAPNIK);
            mapView.setClickable(true);
            mapView.setBuiltInZoomControls(true);
            mapView.setMultiTouchControls(true);

            mapController = mapView.getController();
            mapController.setZoom(mapView.getMaxZoomLevel() - 2);
            mapController.setCenter(UBC_MARTHA_PIPER_FOUNTAIN);
        }

        return mapView;
    }

    // You should not need to touch this method
    @Override
    public void onDestroyView() {
        Log.d(LOG_TAG, "onDestroyView");
        ((ViewGroup) mapView.getParent()).removeView(mapView);
        super.onDestroyView();
    }

    // You should not need to touch this method
    @Override
    public void onDestroy() {
        Log.d(LOG_TAG, "onDestroy");
        super.onDestroy();
    }

    // You should not need to touch this method
    @Override
    public void onResume() {
        Log.d(LOG_TAG, "onResume");
        super.onResume();
    }

    // You should not need to touch this method
    @Override
    public void onPause() {
        Log.d(LOG_TAG, "onPause");
        super.onPause();
    }

    /**
     * Save map's zoom level and centre. You should not need to
     * touch this method
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.d(LOG_TAG, "onSaveInstanceState");
        super.onSaveInstanceState(outState);

        if (mapView != null) {
            outState.putInt("zoomLevel", mapView.getZoomLevel());
            IGeoPoint cntr = mapView.getMapCenter();
            outState.putInt("latE6", cntr.getLatitudeE6());
            outState.putInt("lonE6", cntr.getLongitudeE6());
            Log.i("MapSave", "Zoom: " + mapView.getZoomLevel());
        }
    }

    // ****************** App Functionality

    /**
     * Show my schedule on the map. Every time "me"'s schedule shows, the map
     * should be cleared of all existing schedules, buildings, meetup locations, etc.
     */
    public void showMySchedule() {

        // CPSC 210 Students: You must complete the implementation of this method.
        // The very last part of the method should call the asynchronous
        // task (which you will also write the code for) to plot the route
        // for "me"'s schedule for the day of the week set in the Settings

        // Asynchronous tasks are a bit onerous to deal with. In order to provide
        // all information needed in one object to plot "me"'s route, we
        // create a SchedulePlot object and pass it to the asynchrous task.
        // See the project page for more details.

        clearSchedules();
        activeDay = sharedPreferences.getString("dayOfWeek", "TR");
        Log.d(LOG_TAG, "Day is: " + activeDay);

        String prettyDay = "";
        if (activeDay.equals("MWF")) {
            prettyDay = "Mon/Wed/Fri";
        }
        if (activeDay.equals("TR")) {
            prettyDay = "Tues/Thur";
        }

        int numSections = me.getSchedule().getSections(activeDay).size();

        if (numSections == 0) {
            AlertDialog message = createSimpleDialog("No classes " + prettyDay + ".", "Whoops!");
            message.show();
        } else {

            if (numSections == 1) {
                AlertDialog message = createSimpleDialog("Only one class " + prettyDay + ".", "FYI");
                message.show();
            }

            SchedulePlot mySchedulePlot = new SchedulePlot(me.getSchedule().getSections(activeDay),
                    me.getFirstName(), "RED", R.drawable.ic_maps_place_red);


            // Get a routing between these points. This line of code creates and calls
            // an asynchronous task to do the calls to MapQuest to determine a route
            // and plots the route.

            new GetRoutingForSchedule().execute(mySchedulePlot);
        }
    }

    /**
     * Retrieve a random student's schedule from the Meetup web service and
     * plot a route for the schedule on the map. The plot should be for
     * the given day of the week as determined when "me"'s schedule
     * was plotted.
     */
    public void showRandomStudentsSchedule() {
        // To get a random student's schedule, we have to call the MeetUp web service.
        // Calling this web service requires a network access to we have to
        // do this in an asynchronous task. See below in this class for where
        // you need to implement methods for performing the network access
        // and plotting.


        new GetRandomSchedule().execute();


    }

    /**
     * Clear all schedules on the map
     */
    public void clearSchedules() {
        randomStudent = null;
        OverlayManager om = mapView.getOverlayManager();
        om.clear();
        scheduleOverlay.clear();
        buildingOverlay.removeAllItems();
        om.addAll(scheduleOverlay);
        om.add(buildingOverlay);
        mapView.invalidate();
    }

    /**
     * Find all possible locations at which "me" and random student could meet
     * up for the set day of the week and the set time to meet and the set
     * distance either "me" or random is willing to travel to meet.
     * A meetup is only possible if both "me" and random are free at the
     * time specified in the settings and each of us must have at least an hour
     * (>= 60 minutes) free. You should display dialog boxes if there are
     * conditions under which no meetup can happen (e.g., me or random is
     * in class at the specified time)
     */
    public void findMeetupPlace() {

        // CPSC 210 students: you must complete this method

        boolean isMeetUpPossible = true;
        boolean isMeFree = false;
        boolean isRandomFree = false;

        if (randomStudent == null) {
            Log.d(LOG_TAG, "no random student schedule");
            AlertDialog message = createSimpleDialog("You need to get a random student schedule first.", "Whoops!");
            message.show();
        } else {
            if (randomStudent.getSchedule().getSections(activeDay).size() == 0 ||
                    me.getSchedule().getSections(activeDay).size() == 0) {
                Log.d(LOG_TAG, "one (or both) student has no classes that day");
                AlertDialog message = createSimpleDialog("Not both on campus that day.", "Uh oh!");
                message.show();
            } else {
                activeDay = sharedPreferences.getString("dayOfWeek", "MWF");
                Log.d(LOG_TAG, "Day is: " + activeDay);
                activeTime = sharedPreferences.getString("timeOfDay", "12");
                Log.d(LOG_TAG, "Meet time is: " + activeTime);
                activeDistance = sharedPreferences.getString("placeDistance", "500");
                Log.d(LOG_TAG, "Distance is: " + activeDistance);
                activeFoodChoice = sharedPreferences.getString("foodChoice", "food");
                Log.d(LOG_TAG, "Food Choice is: " + activeFoodChoice);

                for (Section s : me.getSchedule().getSections(activeDay)) {
                    Log.d(LOG_TAG, "me: " + s.getCourseTime().toString());
                }
                for (Section s : randomStudent.getSchedule().getSections(activeDay)) {
                    Log.d(LOG_TAG, "random: " + s.getCourseTime().toString());
                }
                Log.d(LOG_TAG, "me: " + me.getSchedule().getStartTimesOfBreaks(activeDay).toString());
                Log.d(LOG_TAG, "random: " + randomStudent.getSchedule().getStartTimesOfBreaks(activeDay).toString());

                int meStartMinutes = me.getSchedule().getSections(activeDay).first().getCourseTime().getStartTimeInMinutes();
                int meEndMinutes = me.getSchedule().getSections(activeDay).last().getCourseTime().getEndTimeInMinutes();
                int randomStartMinutes = randomStudent.getSchedule().getSections(activeDay).first().getCourseTime().getStartTimeInMinutes();
                int randomEndMinutes = randomStudent.getSchedule().getSections(activeDay).last().getCourseTime().getEndTimeInMinutes();
                int meetupTimeMinutes = Integer.parseInt(activeTime) * 60;

                // if meetup time is before either student has a class meetup is not possible
                if (meetupTimeMinutes < meStartMinutes || meetupTimeMinutes < randomStartMinutes) {
                    isMeetUpPossible = false;
                    AlertDialog message = createSimpleDialog("Meetup time is too early for one or both students.", "Sorry!");
                    message.show();
                }
                if (meetupTimeMinutes > meEndMinutes && meetupTimeMinutes > randomEndMinutes) {
                    Log.d(LOG_TAG, "meetup time after both students are done for the day");
                    isMeetUpPossible = true;
                } else {

                    Section lastSection;
                    Section nextSection;

                    Log.d(LOG_TAG, "Iterator test ------------------");
                    Iterator itr = me.getSchedule().getSections(activeDay).iterator();
                    lastSection = (Section) itr.next();
                    while (itr.hasNext()) {
                        nextSection = (Section) itr.next();
                        Log.d(LOG_TAG, lastSection.getCourseTime().getEndTime());
                        int breakLength = nextSection.getCourseTime().getStartTimeInMinutes() - lastSection.getCourseTime().getEndTimeInMinutes();
                        Log.d(LOG_TAG, "break: " + breakLength);
                        if (breakLength > 60 && meetupTimeMinutes > lastSection.getCourseTime().getEndTimeInMinutes()
                                && meetupTimeMinutes < nextSection.getCourseTime().getStartTimeInMinutes()) {
                            isMeFree = true;
                            Log.d(LOG_TAG, "Yes! A meetup is possible for me.");
                        }
                        Log.d(LOG_TAG, nextSection.getCourseTime().getStartTime());
                        lastSection = nextSection;
                    }

                    Iterator itr2 = randomStudent.getSchedule().getSections(activeDay).iterator();
                    lastSection = (Section) itr2.next();
                    while (itr2.hasNext()) {
                        nextSection = (Section) itr2.next();
                        Log.d(LOG_TAG, lastSection.getCourseTime().getEndTime());
                        int breakLength = nextSection.getCourseTime().getStartTimeInMinutes() - lastSection.getCourseTime().getEndTimeInMinutes();
                        Log.d(LOG_TAG, "break: " + breakLength);
                        if (breakLength > 60 && meetupTimeMinutes > lastSection.getCourseTime().getEndTimeInMinutes()
                                && meetupTimeMinutes < nextSection.getCourseTime().getStartTimeInMinutes()) {
                            isRandomFree = true;
                            Log.d(LOG_TAG, "Yes! A meetup is possible for random.");
                        }
                        Log.d(LOG_TAG, nextSection.getCourseTime().getStartTime());
                        lastSection = nextSection;
                    }

                    for (Section s : me.getSchedule().getSections(activeDay)) {
                        Log.d(LOG_TAG, "minutes: " + s.getCourseTime().getStartTimeInMinutes() + "-" + s.getCourseTime().getEndTimeInMinutes());
                        if (s.getCourseTime().getStartTimeInMinutes() <= meetupTimeMinutes && meetupTimeMinutes <= s.getCourseTime().getEndTimeInMinutes()) {
                            isMeetUpPossible = false;
                        }
                    }
                    for (Section s : randomStudent.getSchedule().getSections(activeDay)) {
                        Log.d(LOG_TAG, "minutes: " + s.getCourseTime().getStartTimeInMinutes() + "-" + s.getCourseTime().getEndTimeInMinutes());
                        if (s.getCourseTime().getStartTimeInMinutes() <= meetupTimeMinutes && meetupTimeMinutes <= s.getCourseTime().getEndTimeInMinutes()) {
                            isMeetUpPossible = false;
                        }
                    }
                }

                if (!isMeetUpPossible) {
                    AlertDialog message = createSimpleDialog("Meetup is not possible.", "Sorry!");
                    message.show();
                }

                if (isMeetUpPossible) {
                    // get the places and find those that are suitable close to both students
                    PlaceFactory placeFactory = PlaceFactory.getInstance();
                    Log.d(LOG_TAG, "where is me? " + me.getSchedule().whereAmI(activeDay, activeTime).getName());
                    Log.d(LOG_TAG, "where is random? " + randomStudent.getSchedule().whereAmI(activeDay, activeTime).getName());
                    Log.d(LOG_TAG, "number of places: " + placeFactory.findPlacesWithinDistance(me.getSchedule().whereAmI(activeDay, activeTime).getLatLon(), Integer.parseInt(activeDistance)).size());

                    HashSet<Place> myPlaces = (HashSet<Place>) placeFactory.findPlacesWithinDistance(me.getSchedule().whereAmI(activeDay, activeTime).getLatLon(), Integer.parseInt(activeDistance));
                    HashSet<Place> randomPlaces = (HashSet<Place>) placeFactory.findPlacesWithinDistance(randomStudent.getSchedule().whereAmI(activeDay, activeTime).getLatLon(), Integer.parseInt(activeDistance));

                    // select places that are within the specified distance of both students
                    HashSet<Place> placesToMeet = new HashSet<>();
                    for (Place p : myPlaces) {
                        if (randomPlaces.contains(p)) {
                            placesToMeet.add(p);
                        }
                    }
                    Log.d(LOG_TAG, "number of places to meet: " + placesToMeet.size());

                    if (placesToMeet.size() == 0) {
                        AlertDialog message = createSimpleDialog("No suitable places.", "Sorry!");
                        message.show();
                    } else {

                        LatLon myLatLon = me.getSchedule().whereAmI(activeDay, activeTime).getLatLon();
                        LatLon randomLatLon = randomStudent.getSchedule().whereAmI(activeDay, activeTime).getLatLon();

                        if (activeFoodChoice.equals("food")) {
                            for (Place p : placesToMeet) {
                                Building building = new Building(p.getName(), p.getLatLon());
                                Double myDistance = LatLon.distanceBetweenTwoLatLon(p.getLatLon(), myLatLon);
                                Double randomDistance = LatLon.distanceBetweenTwoLatLon(p.getLatLon(), randomLatLon);
                                String message = "Food place\n";
                                message += "Your distance: " + myDistance.intValue() + "m\n";
                                message += "Their distance: " + randomDistance.intValue() + "m";
                                plotABuilding(building, p.getName(), message, R.drawable.ic_maps_local_food);
                            }
                        } else {
                            for (Place p : placesToMeet) {
                                Building building = new Building(p.getName(), p.getLatLon());
                                Double myDistance = LatLon.distanceBetweenTwoLatLon(p.getLatLon(), myLatLon);
                                Double randomDistance = LatLon.distanceBetweenTwoLatLon(p.getLatLon(), randomLatLon);
                                String message = "Coffee place\n";
                                message += "Your distance: " + myDistance.intValue() + "m\n";
                                message += "Their distance: " + randomDistance.intValue() + "m";
                                plotABuilding(building, p.getName(), message, R.drawable.ic_maps_local_cafe);
                            }
                        }

                        OverlayManager om = mapView.getOverlayManager();
                        om.add(buildingOverlay);
                        mapView.invalidate();
                    }
                }
            }
        }

    }

    /**
     * Initialize the PlaceFactory with information from FourSquare
     */
    public void initializePlaces() {
        // CPSC 210 Students: You should not need to touch this method, but
        // you will have to implement GetPlaces below.

        Log.d(LOG_TAG, "about to call GetPlaces().execute()");
        new GetPlaces().execute();
    }


    /**
     * Plot all buildings referred to in the given information about plotting
     * a schedule.
     *
     * @param schedulePlot All information about the schedule and route to plot.
     */
    private void plotBuildings(SchedulePlot schedulePlot) {

        // CPSC 210 Students: Complete this method by plotting each building in the
        // schedulePlot with an appropriate message displayed

        for (Section s : schedulePlot.getSections()) {
//
            String title = schedulePlot.getName() + "'s schedule";
            String building = "Building: " + s.getBuilding().getName();
            String course = "Course: " + s.getCourse().getCode() + " " + s.getCourse().getNumber();
            String section = "Section: " + s.getName();
            String time = "Time: " + s.getCourseTime().getStartTime().toString() + " - " +
                    s.getCourseTime().getEndTime().toString();
            String message = building + "\n" + course + "\n" + section + "\n" + time;

            plotABuilding(s.getBuilding(), title, message, schedulePlot.getIcon());
        }
        // CPSC 210 Students: You will need to ensure the buildingOverlay is in
        // the overlayManager. The following code achieves this. You should not likely
        // need to touch it
        OverlayManager om = mapView.getOverlayManager();
        om.add(buildingOverlay);

    }

    /**
     * Plot a building onto the map
     *
     * @param building      The building to put on the map
     * @param title         The title to put in the dialog box when the building is tapped on the map
     * @param msg           The message to display when the building is tapped
     * @param drawableToUse The icon to use. Can be R.drawable.ic_action_place (or any icon in the res/drawable directory)
     */
    private void plotABuilding(Building building, String title, String msg, int drawableToUse) {
        // CPSC 210 Students: You should not need to touch this method
        OverlayItem buildingItem = new OverlayItem(title, msg,
                new GeoPoint(building.getLatLon().getLatitude(), building.getLatLon().getLongitude()));

        //Create new marker
        Drawable icon = this.getResources().getDrawable(drawableToUse);

        //Set the bounding for the drawable
        icon.setBounds(
                0 - icon.getIntrinsicWidth() / 2, 0 - icon.getIntrinsicHeight(),
                icon.getIntrinsicWidth() / 2, 0);

        //Set the new marker to the overlay
        buildingItem.setMarker(icon);
        buildingOverlay.addItem(buildingItem);
    }


    /**
     * Initialize your schedule by coding it directly in. This is the schedule
     * that will appear on the map when you select "Show My Schedule".
     */
    private void initializeMySchedule() {
        // CPSC 210 Students; Implement this method
        // MWF                 TR

        studentManager.addStudent("Taylor", "Brian", ME_ID);
        me = studentManager.get(ME_ID);
        studentManager.addSectionToSchedule(ME_ID, "COGS", 200, "002");
        studentManager.addSectionToSchedule(ME_ID, "COMM", 340, "001");
        studentManager.addSectionToSchedule(ME_ID, "LAW", 208, "101");
        studentManager.addSectionToSchedule(ME_ID, "MATH", 347, "001");
        studentManager.addSectionToSchedule(ME_ID, "EDUC", 441, "201");
    }

    /**
     * Helper to create simple alert dialog to display message
     *
     * @param msg message to display in alert dialog
     * @return the alert dialog
     */
    private AlertDialog createSimpleDialog(String msg, String title) {
        // CPSC 210 Students; You should not need to modify this method
        AlertDialog.Builder dialogBldr = new AlertDialog.Builder(getActivity());
        dialogBldr.setMessage(msg);
        dialogBldr.setTitle(title);
        dialogBldr.setNeutralButton(R.string.ok, null);

        return dialogBldr.create();
    }

    /**
     * Create the overlay used for buildings. CPSC 210 students, you should not need to
     * touch this method.
     *
     * @return An overlay
     */
    private ItemizedIconOverlay<OverlayItem> createBuildingOverlay() {
        ResourceProxy rp = new DefaultResourceProxyImpl(getActivity());

        ItemizedIconOverlay.OnItemGestureListener<OverlayItem> gestureListener =
                new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {

                    /**
                     * Display building description in dialog box when user taps stop.
                     *
                     * @param index
                     *            index of item tapped
                     * @param oi
                     *            the OverlayItem that was tapped
                     * @return true to indicate that tap event has been handled
                     */
                    @Override
                    public boolean onItemSingleTapUp(int index, OverlayItem oi) {

                        new AlertDialog.Builder(getActivity())
                                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface arg0, int arg1) {
                                        if (selectedBuildingOnMap != null) {
                                            mapView.invalidate();
                                        }
                                    }
                                }).setTitle(oi.getTitle()).setMessage(oi.getSnippet())
                                .show();

                        selectedBuildingOnMap = oi;
                        mapView.invalidate();
                        return true;
                    }

                    @Override
                    public boolean onItemLongPress(int index, OverlayItem oi) {
                        // do nothing
                        return false;
                    }
                };

        return new ItemizedIconOverlay<OverlayItem>(
                new ArrayList<OverlayItem>(), getResources().getDrawable(
                R.drawable.ic_action_place), gestureListener, rp);
    }


    /**
     * Create overlay with a specific color
     *
     * @param colour A string with a hex colour value
     */
    private PathOverlay createPathOverlay(String colour) {
        // CPSC 210 Students, you should not need to touch this method
        PathOverlay po = new PathOverlay(Color.parseColor(colour),
                getActivity());
        Paint pathPaint = new Paint();
        pathPaint.setColor(Color.parseColor(colour));
        pathPaint.setStrokeWidth(4.0f);
        pathPaint.setStyle(Paint.Style.STROKE);
        po.setPaint(pathPaint);
        return po;
    }

    // *********************** Asynchronous tasks

    /**
     * This asynchronous task is responsible for contacting the Meetup web service
     * for the schedule of a random student. The task must plot the retrieved
     * student's route for the schedule on the map in a different colour than the "me" schedule
     * or must display a dialog box that a schedule was not retrieved.
     */
    private class GetRandomSchedule extends AsyncTask<Void, Void, SchedulePlot> {

        // Some overview explanation of asynchronous tasks is on the project web page.

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected SchedulePlot doInBackground(Void... params) {

            // CPSC 210 Students: You must complete this method. It needs to
            // contact the Meetup web service to get a random student's schedule.
            // If it is successful in retrieving a student and their schedule,
            // it needs to remember the student in the randomStudent field
            // and it needs to create and return a schedulePlot object with
            // all relevant information for being ready to retrieve the route
            // and plot the route for the schedule. If no random student is
            // retrieved, return null.
            //
            // Note, leave all determination of routing and plotting until
            // the onPostExecute method below.

            String getStudentString = "";
            String firstName = "";
            String lastName = "";
            String id = "";

            try {
                getStudentString = makeRoutingCall(getStudentURL);
            } catch (IOException e) {
                e.printStackTrace();
            }

            JSONTokener jtoken = new JSONTokener(getStudentString);
            try {
                while (jtoken.more()) {
                    JSONObject jObj = (JSONObject) jtoken.nextValue();
                    firstName = jObj.getString("FirstName");
                    lastName = jObj.getString("LastName");
                    id = jObj.getString("Id");

                    int idnum = Integer.parseInt(id);

                    studentManager.addStudent(lastName, firstName, idnum);
                    randomStudent = studentManager.get(idnum);

                    JSONArray sections = jObj.getJSONArray("Sections");

                    for (int i = 0; i < sections.length(); i++) {
                        JSONObject section = sections.getJSONObject(i);
                        String courseName = section.get("CourseName").toString();
                        int courseNumber = Integer.parseInt(section.get("CourseNumber").toString());
                        String sectionName = section.get("SectionName").toString();
                        studentManager.addSectionToSchedule(idnum, courseName, courseNumber, sectionName);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


            activeDay = sharedPreferences.getString("dayOfWeek", "TR");


            SchedulePlot randomSchedulePlot =
                    new SchedulePlot(randomStudent.getSchedule().getSections(activeDay),
                            randomStudent.getFirstName(), "BLUE", R.drawable.ic_maps_place_blue);

            return randomSchedulePlot;
        }

        @Override
        protected void onPostExecute(SchedulePlot schedulePlot) {
            // CPSC 210 students: When this method is called, it will be passed
            // whatever schedulePlot object you created (if any) in doBackground
            // above. Use it to plot the route.

            int numSections = randomStudent.getSchedule().getSections(activeDay).size();
            String prettyDay = "";
            if (activeDay.equals("MWF")) {
                prettyDay = "Mon/Wed/Fri";
            }
            if (activeDay.equals("TR")) {
                prettyDay = "Tues/Thur";
            }

            if (numSections == 0) {
                AlertDialog message = createSimpleDialog("No classes " + prettyDay + ".", "Whoops!");
                message.show();
            } else {
                if (numSections == 1) {
                    AlertDialog message = createSimpleDialog("Only one class " + prettyDay + ".", "FYI");
                    message.show();
                }

                new GetRoutingForSchedule().execute(schedulePlot);
            }
        }
    }

    /**
     * This asynchronous task is responsible for contacting the MapQuest web service
     * to retrieve a route between the buildings on the schedule and for plotting any
     * determined route on the map.
     */
    private class GetRoutingForSchedule extends AsyncTask<SchedulePlot, Void, SchedulePlot> {

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected SchedulePlot doInBackground(SchedulePlot... params) {

            // The params[0] element contains the schedulePlot object
            SchedulePlot scheduleToPlot = params[0];

            // CPSC 210 Students: Complete this method. This method should
            // call the MapQuest webservice to retrieve a List<GeoPoint>
            // that forms the routing between the buildings on the
            // schedule. The List<GeoPoint> should be put into
            // scheduleToPlot object.
            List<GeoPoint> route = new ArrayList<GeoPoint>();

            SortedSet<Section> sections = scheduleToPlot.getSections();
            ArrayList<LatLon> latLonPts = new ArrayList<LatLon>();
            for (Section s : sections) {
                Log.d(LOG_TAG, s.getBuilding().getName());
                Log.d(LOG_TAG, s.getCourseTime().toString());
                latLonPts.add(s.getBuilding().getLatLon());
            }


            // get the LatLon of the first building of the day
            LatLon from = latLonPts.get(0);
            latLonPts.remove(0);

            String from_lat = String.valueOf(from.getLatitude());
            String from_lon = String.valueOf(from.getLongitude());

            // start making the httpCall string
            String httpCall = MAPQUEST_URL + MAPQUEST_KEY + MAPQUEST_OPTIONS +
                    "&from=" + from_lat + "," + from_lon;
            Log.d(LOG_TAG, httpCall);

            // finish making the httpCall string
            for (LatLon pt : latLonPts) {
                httpCall = httpCall + "&to=" + pt.getLatitude() + "," + pt.getLongitude();
                Log.d(LOG_TAG, httpCall);
            }


            String mapquestCall = "";

            try {
                mapquestCall = makeRoutingCall(httpCall);
                Log.d(LOG_TAG, mapquestCall);
            } catch (IOException e) {
                AlertDialog message = createSimpleDialog("Problem retrieving directions from MapQuest.", "Whoops!");
                message.show();
            }

            try {
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = factory.newPullParser();
                parser.setInput(new StringReader(mapquestCall));

                mapquestParser(route, parser);
            } catch (XmlPullParserException e) {
                AlertDialog message = createSimpleDialog("Problem parsing directions from MapQuest.", "Whoops!");
                message.show();
            } catch (IOException e) {
                AlertDialog message = createSimpleDialog("Problem with MapQuest.", "Whoops!");
                message.show();
            }

            Log.d(LOG_TAG, "finished parsing");

            scheduleToPlot.setRoute(route);


            return scheduleToPlot;
        }

        private void mapquestParser(List<GeoPoint> route, XmlPullParser parser)
                throws XmlPullParserException, IOException {
            double lat = 0.0;
            double lng = 0.0;

            int eventType = parser.next();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                eventType = parser.next();

                if (eventType == XmlPullParser.START_TAG &&
                        "shapePoints".equals(parser.getName())) {
                    Log.d(LOG_TAG, "found shapePoints");
                    eventType = parser.next();

                    while (eventType != XmlPullParser.END_DOCUMENT) {


                        if (eventType == XmlPullParser.START_TAG &&
                                "latLng".equals(parser.getName())) {
                            Log.d(LOG_TAG, "found latLng");
                            eventType = parser.next();
                        }
                        if (eventType == XmlPullParser.START_TAG &&
                                "lat".equals(parser.getName())) {
                            eventType = parser.next();
                            Log.d(LOG_TAG, "found lat: " + parser.getText());
                            lat = Double.parseDouble(parser.getText());
                        }
                        if (eventType == XmlPullParser.START_TAG &&
                                "lng".equals(parser.getName())) {
                            eventType = parser.next();
                            Log.d(LOG_TAG, "found lng: " + parser.getText());
                            lng = Double.parseDouble(parser.getText());
                        }
                        if (eventType == XmlPullParser.END_TAG &&
                                "latLng".equals(parser.getName())) {
                            route.add(new GeoPoint(lat, lng));
                            eventType = parser.next();
                            Log.d(LOG_TAG, "added new Geopoint to route: " + lat + ", " + lng);
                        }
                        eventType = parser.next();
                    }
                }
            }
        }


        @Override
        protected void onPostExecute(SchedulePlot schedulePlot) {

            // CPSC 210 Students: This method should plot the route onto the map
            // with the given line colour specified in schedulePlot. If there is
            // no route to plot, a dialog box should be displayed.


            // To actually make something show on the map, you can use overlays.
            // For instance, the following code should show a line on a map
            PathOverlay po = createPathOverlay(schedulePlot.getColourOfLine());
            List<GeoPoint> route = schedulePlot.getRoute();

            for (GeoPoint gp : route) {
                po.addPoint(gp.getLatitudeE6(), gp.getLongitudeE6());
            }
            // po.addPoints(schedulePlot.getRoute());

            // po.addPoint(new GeoPoint(49.269258,-123.254784)); // one end of line (Buchanan)
            // po.addPoint(new GeoPoint(49.261474,-123.248060)); // second end of line (DMP)
            scheduleOverlay.add(po);

            OverlayManager om = mapView.getOverlayManager();
            om.addAll(scheduleOverlay);

            plotBuildings(schedulePlot);

            mapView.invalidate(); // cause map to redraw


        }

    }

    /**
     * An example helper method to call a web service
     */
    private String makeRoutingCall(String httpRequest) throws MalformedURLException, IOException {
        URL url = new URL(httpRequest);
        HttpURLConnection client = (HttpURLConnection) url.openConnection();
        InputStream in = client.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String returnString = "";
        while (br.ready()) {
            returnString += br.readLine();
        }

        client.disconnect();
        return returnString;
    }

    /**
     * This asynchronous task is responsible for contacting the FourSquare web service
     * to retrieve all places around UBC that have to do with food. It should load
     * any determined places into PlaceFactory and then display a dialog box of how it did
     */
    private class GetPlaces extends AsyncTask<Void, Void, String> {

        protected String doInBackground(Void... params) {

            // CPSC 210 Students: Complete this method to retrieve a string
            // of JSON from FourSquare. Return the string from this method
            String fourSquareCall = "";
            fourSquareCall += FOUR_SQUARE_URL;
            fourSquareCall += "client_id=";
            fourSquareCall += FOUR_SQUARE_CLIENT_ID;
            fourSquareCall += "&client_secret=";
            fourSquareCall += FOUR_SQUARE_CLIENT_SECRET;
            fourSquareCall += "&v=20130815";
            fourSquareCall += "&ll=49.264719,-123.251480";
            fourSquareCall += "&query=";
            fourSquareCall += sharedPreferences.getString("foodChoice", "food");
            fourSquareCall += "&radius=2500";

            Log.d(LOG_TAG, "fourSquareCall: " + fourSquareCall);

            String fourSquareJSON = "";

            try {
                fourSquareJSON = makeRoutingCall(fourSquareCall);
                Log.d(LOG_TAG, fourSquareJSON);
            } catch (IOException e) {
                AlertDialog message = createSimpleDialog("Problem retrieving venues from FourSquare.", "Whoops!");
                message.show();
            }

            return fourSquareJSON;
        }

        protected void onPostExecute(String jSONOfPlaces) {

            // CPSC 210 Students: Given JSON from FourQuest, parse it and load
            // PlaceFactory

            PlaceFactory placeFactory = PlaceFactory.getInstance();
            String name = "";
            double lat = 0.0;
            double lng = 0.0;

            JSONTokener jtoken = new JSONTokener(jSONOfPlaces);
            try {
                while (jtoken.more()) {
                    JSONObject jObj = (JSONObject) jtoken.nextValue();
                    JSONObject response = jObj.optJSONObject("response");
                    JSONArray groups = response.optJSONArray("groups");
                    JSONObject g = groups.optJSONObject(0);
                    JSONArray items = g.getJSONArray("items");
                    Log.d(LOG_TAG, "length of items array: " + items.length());

                    for (int i = 0; i < items.length(); i++) {
                        JSONObject entry = items.getJSONObject(i);
                        JSONObject venue = entry.getJSONObject("venue");
                        name = venue.getString("name");
                        Log.d(LOG_TAG, "found: " + name);
                        JSONObject location = venue.getJSONObject("location");
                        lat = location.getDouble("lat");
                        lng = location.getDouble("lng");
                        Log.d(LOG_TAG, "lat/lng: " + lat + "," + lng);
                        EatingPlace ep = new EatingPlace(name, new LatLon(lat, lng));
                        placeFactory.add(ep);

                    }
                }
            } catch (JSONException e) {
                AlertDialog message = createSimpleDialog("Problem parsing info from FourSquare.", "Whoops!");
                Log.d(LOG_TAG, e.toString());
                message.show();
            }


        }
    }

    /**
     * Initialize the CourseFactory with some courses.
     */
    private void initializeCourses() {
        // CPSC 210 Students: You can change this data if you desire.
        CourseFactory courseFactory = CourseFactory.getInstance();

        Building dmpBuilding = new Building("DMP", new LatLon(49.261474, -123.248060));

        Course cpsc210 = courseFactory.getCourse("CPSC", 210);
        Section aSection = new Section("202", "MWF", "12:00", "12:50", dmpBuilding);
        cpsc210.addSection(aSection);
        aSection.setCourse(cpsc210);
        aSection = new Section("201", "MWF", "16:00", "16:50", dmpBuilding);
        cpsc210.addSection(aSection);
        aSection.setCourse(cpsc210);
        aSection = new Section("BCS", "MWF", "12:00", "12:50", dmpBuilding);
        cpsc210.addSection(aSection);
        aSection.setCourse(cpsc210);

        Course engl222 = courseFactory.getCourse("ENGL", 222);
        aSection = new Section("007", "MWF", "14:00", "14:50", new Building("Buchanan", new LatLon(49.269258, -123.254784)));
        engl222.addSection(aSection);
        aSection.setCourse(engl222);

        Course scie220 = courseFactory.getCourse("SCIE", 220);
        aSection = new Section("200", "MWF", "15:00", "15:50", new Building("Swing", new LatLon(49.262786, -123.255044)));
        scie220.addSection(aSection);
        aSection.setCourse(scie220);

        Course math200 = courseFactory.getCourse("MATH", 200);
        aSection = new Section("201", "MWF", "09:00", "09:50", new Building("Buchanan", new LatLon(49.269258, -123.254784)));
        math200.addSection(aSection);
        aSection.setCourse(math200);

        Course fren102 = courseFactory.getCourse("FREN", 102);
        aSection = new Section("202", "MWF", "11:00", "11:50", new Building("Barber", new LatLon(49.267442, -123.252471)));
        fren102.addSection(aSection);
        aSection.setCourse(fren102);

        Course japn103 = courseFactory.getCourse("JAPN", 103);
        aSection = new Section("002", "MWF", "10:00", "11:50", new Building("Buchanan", new LatLon(49.269258, -123.254784)));
        japn103.addSection(aSection);
        aSection.setCourse(japn103);

        Course scie113 = courseFactory.getCourse("SCIE", 113);
        aSection = new Section("213", "MWF", "13:00", "13:50", new Building("Swing", new LatLon(49.262786, -123.255044)));
        scie113.addSection(aSection);
        aSection.setCourse(scie113);

        Course micb308 = courseFactory.getCourse("MICB", 308);
        aSection = new Section("201", "MWF", "12:00", "12:50", new Building("Woodward", new LatLon(49.264704, -123.247536)));
        micb308.addSection(aSection);
        aSection.setCourse(micb308);

        Course math221 = courseFactory.getCourse("MATH", 221);
        aSection = new Section("202", "TR", "11:00", "12:20", new Building("Klinck", new LatLon(49.266112, -123.254776)));
        math221.addSection(aSection);
        aSection.setCourse(math221);

        Course phys203 = courseFactory.getCourse("PHYS", 203);
        aSection = new Section("201", "TR", "09:30", "10:50", new Building("Hennings", new LatLon(49.266400, -123.252047)));
        phys203.addSection(aSection);
        aSection.setCourse(phys203);

        Course crwr209 = courseFactory.getCourse("CRWR", 209);
        aSection = new Section("002", "TR", "12:30", "13:50", new Building("Geography", new LatLon(49.266039, -123.256129)));
        crwr209.addSection(aSection);
        aSection.setCourse(crwr209);

        Course fnh330 = courseFactory.getCourse("FNH", 330);
        aSection = new Section("002", "TR", "15:00", "16:20", new Building("MacMillian", new LatLon(49.261167, -123.251157)));
        fnh330.addSection(aSection);
        aSection.setCourse(fnh330);

        Course cpsc499 = courseFactory.getCourse("CPSC", 430);
        aSection = new Section("201", "TR", "16:20", "17:50", new Building("Liu", new LatLon(49.267632, -123.259334)));
        cpsc499.addSection(aSection);
        aSection.setCourse(cpsc499);

        Course chem250 = courseFactory.getCourse("CHEM", 250);
        aSection = new Section("203", "TR", "10:00", "11:20", new Building("Klinck", new LatLon(49.266112, -123.254776)));
        chem250.addSection(aSection);
        aSection.setCourse(chem250);

        Course eosc222 = courseFactory.getCourse("EOSC", 222);
        aSection = new Section("200", "TR", "11:00", "12:20", new Building("ESB", new LatLon(49.262866, -123.25323)));
        eosc222.addSection(aSection);
        aSection.setCourse(eosc222);

        Course biol201 = courseFactory.getCourse("BIOL", 201);
        aSection = new Section("201", "TR", "14:00", "15:20", new Building("BioSci", new LatLon(49.263920, -123.251552)));
        biol201.addSection(aSection);
        aSection.setCourse(biol201);

        Course cogs200 = courseFactory.getCourse("COGS", 200);
        aSection = new Section("002", "TR", "14:00", "15:20", new Building("FSC", new LatLon(49.260462, -123.248583)));
        cogs200.addSection(aSection);
        aSection.setCourse(cogs200);

        Course comm340 = courseFactory.getCourse("COMM", 340);
        aSection = new Section("001", "TR", "16:00", "17:20", new Building("Angus", new LatLon(49.265076, -123.253797)));
        comm340.addSection(aSection);
        aSection.setCourse(comm340);

        Course law208 = courseFactory.getCourse("LAW", 208);
        aSection = new Section("101", "MWF", "10:00", "10:50", new Building("Allard", new LatLon(49.269697, -123.253153)));
        law208.addSection(aSection);
        aSection.setCourse(law208);

        Course math347 = courseFactory.getCourse("MATH", 347);
        aSection = new Section("001", "MWF", "11:00", "11:50", new Building("Math", new LatLon(49.266427, -123.255621)));
        math347.addSection(aSection);
        aSection.setCourse(math347);

        Course educ441 = courseFactory.getCourse("EDUC", 441);
        aSection = new Section("201", "MWF", "13:00", "13:50", new Building("Scarfe", new LatLon(49.264012, -123.252928)));
        educ441.addSection(aSection);
        aSection.setCourse(educ441);
    }

}