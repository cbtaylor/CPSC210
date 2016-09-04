package ca.ubc.cs.cpsc210.meetup.util;

import android.graphics.Color;
import android.preference.PreferenceFragment;
import android.os.Bundle;
import ca.ubc.cs.cpsc210.meetup.R;

public class SettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.settings);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getView().setBackgroundColor(Color.WHITE);
    }

}
