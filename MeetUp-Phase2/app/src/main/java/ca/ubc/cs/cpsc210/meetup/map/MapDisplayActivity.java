package ca.ubc.cs.cpsc210.meetup.map;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import ca.ubc.cs.cpsc210.meetup.R;
import ca.ubc.cs.cpsc210.meetup.util.SettingsActivity;

/**
 * Activity that controls MapDisplayFragment
 */
public class MapDisplayActivity extends Activity {
    private MapDisplayFragment fragment;
    private DrawerLayout sideDrawerMenu;
    private ListView sideDrawerMenuList;

    /**
     * Log tag for LogCat messages
     */
    private final static String LOG_TAG = "MapDisplayActivity";


    private String[] menuItems = {"Clear", "Show My Schedule", "Get Random Schedule", "Find Meetup Place",
            "Get Places", "Settings"};


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.side_drawer_menu);

        fragment = (MapDisplayFragment) getFragmentManager()
                .findFragmentById(R.id.content_frame);

        if (fragment == null) {
            fragment = new MapDisplayFragment();

            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.content_frame, fragment);
            transaction.commit();
        }

        sideDrawerMenu = (DrawerLayout) findViewById(R.id.drawer_layout);
        sideDrawerMenuList = (ListView) findViewById(R.id.left_drawer);


        // Set the adapter for the list view
        sideDrawerMenuList.setAdapter(SideDrawerItemAdapter);
        // Set the list's click listener
        sideDrawerMenuList.setOnItemClickListener(new SideDrawerClickListener());

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setIcon(android.R.color.transparent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    private class SideDrawerClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    //** Swaps fragments in the main content view */
    private void selectItem(int position) {
        switch (position) {
            case 0:
                // Clear
                //          fragment.clear();
                Log.d(LOG_TAG, "Clear the map!");
                fragment.clearSchedules();
                break;
            case 1:
                // Show my schedule
                Log.d(LOG_TAG, "Show my schedule!");
                fragment.showMySchedule();
                break;
            case 2:
                // Get Random Friend Schedule
                Log.d(LOG_TAG, "Show friend schedule");
                fragment.showRandomStudentsSchedule();
                break;
            case 3:
                // Find place
                Log.d(LOG_TAG, "Find place to meetup!");
                fragment.findMeetupPlace();
                break;
            case 4:
                // Get places
                Log.d(LOG_TAG, "Initialize places");
                fragment.initializePlaces();
                break;
            case 5:
                // Settings
                startActivityForResult(new Intent(this, SettingsActivity.class),1);
                break;
        }

        // Highlight the selected item, update the title, and close the drawer
        sideDrawerMenuList.setItemChecked(position, true);
        sideDrawerMenu.closeDrawer(sideDrawerMenuList);
    }

    private BaseAdapter SideDrawerItemAdapter = new BaseAdapter() {

        @Override
        public int getCount() {
            return menuItems.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View retval = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.side_drawer_item, null);

            TextView title = (TextView) retval
                    .findViewById(R.id.drawer_item_content);
            ImageView icon = (ImageView) retval
                    .findViewById(R.id.drawer_item_icon);

            String menuItem = menuItems[position];
            title.setText(menuItem);

            switch (position) {
                case 0:
                    icon.setImageResource(R.drawable.ic_action_cancel);
                    break;
                case 1:
                    icon.setImageResource(R.drawable.ic_action_settings);
                    break;
                case 2:
                    icon.setImageResource(R.drawable.ic_action_settings);
                    break;
                case 3:
                    icon.setImageResource(R.drawable.ic_action_place);
                    break;
                case 4:
                    icon.setImageResource(R.drawable.ic_action_place);
                    break;
                case 5:
                    icon.setImageResource(R.drawable.ic_action_settings);
                    break;
            }

            return retval;
        }

    };

}
