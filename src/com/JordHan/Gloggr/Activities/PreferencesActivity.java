package com.JordHan.Gloggr.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.widget.Toast;

import com.JordHan.Gloggr.R;
import com.JordHan.Gloggr.Activities.Notification.AboutAppActivity;
import com.JordHan.Gloggr.Activities.Notification.AboutDeveloperActivity;

public class PreferencesActivity extends PreferenceActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); // Push instance of activity to super
		
		super.setTitle(getString(R.string.preferences_title)); // Set title

		// Setup preference interface using resource
		addPreferencesFromResource(R.xml.preferences);

		// Code for custom preference click methods
		// Find resources
		Preference prefHeaderImage = (Preference) findPreference("header_image_uri");
		Preference prefAboutApp = (Preference) findPreference("prefAboutApp");
		Preference prefAboutDeveloper = (Preference) findPreference("prefAboutDeveloper");

		// Setup onClick methods
		prefHeaderImage.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			public boolean onPreferenceClick(Preference preference) {
				Toast.makeText(getApplicationContext(), "Ability to change header image is not currently implemented.\nSorry for any inconvenience.", Toast.LENGTH_SHORT).show();
				
				return true;
			}
		});
		
		prefAboutApp.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			public boolean onPreferenceClick(Preference preference) {
				Intent activityToOpen = new Intent(getBaseContext(), AboutAppActivity.class);
				startActivity(activityToOpen);
				
				return false;
			}
		});
		
		prefAboutDeveloper.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			public boolean onPreferenceClick(Preference preference) {
				Intent activityToOpen = new Intent(getBaseContext(), AboutDeveloperActivity.class);
				startActivity(activityToOpen);
				
				return false;
			}
		});
	}
}
